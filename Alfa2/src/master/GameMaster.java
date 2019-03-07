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
import java.net.SocketException;
import java.util.Random;
import java.util.Scanner;

/**
 * GameMaster Esta clase funciona como SERVIDOR de TCP para los múltiples
 * jugadores y como CLIENTE de Multicast
 * <p>
 * Tendrá un número de clientes máximos
 */
public class GameMaster implements Register {

    private String ip;
    private String multicastIp = "228.5.60.7";
    private int multicastPort = 6789;
    private int tcpPort = 6780;
    private Monster currentMonster;

    public GameMaster(int groupMaxSize, int totalRounds) {
        Statics.players = new User[groupMaxSize];
        Statics.remainingSpaces = groupMaxSize;
        Statics.totalRounds = totalRounds;

        // TEMPORAL
        currentMonster = new Monster(-1, -1, -1);

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

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public synchronized Information logIn(String username, String password, String ip) {
        if (Statics.remainingSpaces == 0) {
            return null;
        }
        User user = new User(username, password, ip);
        if (Statics.totalPlayers == 0 || Statics.binarySearch(Statics.players, user, 0, Statics.totalPlayers) == -1) {
            int newPosition = Statics.players.length - Statics.remainingSpaces;
            Statics.players[newPosition] = user;
            Statics.remainingSpaces--;
            Statics.totalPlayers++;
            user.setTcpPort(this.tcpPort + Statics.totalPlayers);
            Statics.addUser(user);
            return new Information(this.ip, this.multicastPort, this.tcpPort, this.multicastIp, user.getId());
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

    public boolean sendMonster(Monster monster) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        boolean output = false;
        Statics.hasWinner = false;
        // Monster monster = new Monster(x, y, round);
        MulticastSocket sender = null;
        try {
            InetAddress group = InetAddress.getByName(this.multicastIp);
            sender = new MulticastSocket(this.multicastPort);
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

    // TCP
    public boolean receiveAnswer() {
        boolean resp = false;
        ReceiveAnswer ra = new ReceiveAnswer(this.tcpPort);
        ra.start();
        return resp;
    }

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        GameMaster gameMaster = new GameMaster(30, 10);
        int a, b;
        Random rand = new Random();
        while (true) {
            a = rand.nextInt(10);
            b = rand.nextInt(10);
            System.out.println("Enviando monstruo");
            gameMaster.sendMonster(new Monster(a, b, 1));
            System.out.println("Recibiendo respuesta");
            gameMaster.receiveAnswer();
        }
    }
}
