package master;

import interfaces.Information;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.Register;

/**
 * GameMaster Esta clase funciona como SERVIDOR de TCP para los múltiples
 * jugadores y como CLIENTE de Multicast
 * <p>
 * Tendrá un número de clientes máximos
 */
public class GameMaster implements Register {

    private static GameMaster gMasterStatic;
    public User[] players;
    public static boolean hasWinner;
    private int remainingSpaces;
    private int totalRounds;
    private String ip;
    private int multicastPort = 6789;
    private int tcpPort = 6780;

    public GameMaster(int groupMaxSize, int totalRounds) {
        this.players = new User[groupMaxSize];
        this.remainingSpaces = groupMaxSize;
        this.totalRounds = totalRounds;
        try {
            InetAddress inet = InetAddress.getLocalHost();
            this.ip = inet.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            this.registry();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public GameMaster() {
        super();
    }

    public void setRemainingSpaces(int remainingSpaces) {
        this.remainingSpaces = remainingSpaces;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public void setPlayers(User[] players) {
        this.players = players;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    

    @Override
    public synchronized Information logIn(String username, String password, String ip) {
        if (this.remainingSpaces == 0)
            return null;
        User user = new User(username, password, ip);
        if (this.binarySearch(this.players, user, 0, this.players.length - 1) == -1) {
            int newPosition = this.players.length - this.remainingSpaces;
            players[newPosition] = user;
            this.remainingSpaces--;
            return new Information(this.ip, this.multicastPort, this.tcpPort);
        }
        System.out.println("Registrado");
        return null;
    }

    public void registry() throws RemoteException {
        System.setProperty("java.security.policy", "file:./src/master/master.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        LocateRegistry.createRegistry(1099);
        GameMaster engine = new GameMaster();
        engine.setRemainingSpaces(this.remainingSpaces);
        engine.setTotalRounds(this.totalRounds);
        engine.setPlayers(this.players);
        engine.setIp(this.ip);
        try {
            Register stub = (Register) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Register", stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("Funciones de RMI registradas");
    }

    private <T extends Comparable<T>> int binarySearch(T[] array, T element, int low, int high) {
        if (low > high)
            return -1;
        int middle = (high + low) / 2;
        T middleElement = array[middle];
        int comparisson = element.compareTo(middleElement);
        if (comparisson < 0) {
            return this.binarySearch(array, element, low, middle - 1);
        } else if (comparisson > 0) {
            return this.binarySearch(array, element, middle + 1, high);
        } else
            return middle;
    }

    public boolean sendMonster(int x, int y, int round) {
        boolean output = false;
        GameMaster.hasWinner = false;
        GameMaster.gMasterStatic = this;
        Monster monster = new Monster(x, y, round);
        MulticastSocket sender = null;
        try {
            InetAddress group = InetAddress.getByName(this.ip);
            sender = new MulticastSocket(6789);
            sender.joinGroup(group);
            System.out.println("Sending MULTICAST msgs");
            byte[] informationToSend = monster.getBytes();
            DatagramPacket messageOut = new DatagramPacket(informationToSend, informationToSend.length, group,
                    this.multicastPort);
            sender.send(messageOut);
            System.out.println("Message send");
            output = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (sender != null) {
                sender.close();
            }
        }
        return output;
    }

    public boolean receiveAnswer() {
        try {
            ServerSocket listenSocket = new ServerSocket(this.tcpPort);
            System.out.println("Waiting for messages...");
            Socket clientSocket = listenSocket.accept();
            Connection connection = new Connection(clientSocket);
            connection.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User findPlayer(String ip) {
        int i = GameMaster.gMasterStatic.binarySearch(GameMaster.gMasterStatic.players, new User("", "", ip), 0,
                GameMaster.gMasterStatic.players.length - 1);
        if (i != -1) {
            return GameMaster.gMasterStatic.players[i];
        }
        return null;
    }

    public static void main(String[] args) {
        GameMaster gameMaster = new GameMaster(30, 10);
    }
}