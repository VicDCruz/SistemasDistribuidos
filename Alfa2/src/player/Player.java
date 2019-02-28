package player;

import interfaces.Information;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.Register;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import master.Monster;

/**
 * Player
 */
public class Player {

    private String name;
    private String password;
    private String ip;
    private String ipGameMaster;
    private int multicastPort;
    private String multicastIp;
    private int tcpPort;
    private Monster currentMonster; 

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        try {
            InetAddress inet = InetAddress.getLocalHost();
            this.ip = inet.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
              
        //EN UN FUTURO BORRAR
        currentMonster = new Monster(-1,-1,1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpGameMaster() {
        return ipGameMaster;
    }

    public void setIpGameMaster(String ipGameMaster) {
        this.ipGameMaster = ipGameMaster;
    }

    public int getMulticastPort() {
        return multicastPort;
    }

    public void setMulticastPort(int multicastPort) {
        this.multicastPort = multicastPort;
    }

    public String getMulticastIp() {
        return multicastIp;
    }

    public void setMulticastIp(String multicastIp) {
        this.multicastIp = multicastIp;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public void setCurrentMonster(Monster currentMonster) {
        this.currentMonster = currentMonster;
    }

    
    
    
    public void logIn() {
        System.setProperty("java.security.policy", "file:./src/player/player.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Register register = (Register) registry.lookup("Register");
            Information information = register.logIn(this.name, this.password, ip);
            if (information != null) {
                this.ipGameMaster = information.getIp();
                this.multicastPort = information.getMulticastPort();
                this.tcpPort = information.getTcpPort();
                this.multicastIp = information.getMulticastIp();
                System.out.println("Registrado con exito");
                System.out.println(this.ipGameMaster);
                System.out.println(this.multicastPort);
                System.out.println(this.tcpPort);
            } else {
                System.out.println("No se pudo registrar");
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    //UDP
    public boolean receiveMonster() {
        //System.setProperty("java.net.preferIPv4Stack", "true");
        boolean output = false;
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(this.multicastPort);
            System.out.println(this.multicastIp);
            InetAddress group = InetAddress.getByName(this.multicastIp);
            socket.joinGroup(group);
            byte[] buffer = new byte[1000];
            DatagramPacket messageIn = null;
            System.out.println("Esperando mensajes...");
            messageIn = new DatagramPacket(buffer, buffer.length);
            socket.receive(messageIn);
            this.currentMonster = new Monster(messageIn.getData());
            System.out.println("Message: " + this.currentMonster.toString() + " from: " + messageIn.getAddress());
            socket.leaveGroup(group);
            output = true;
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (socket != null)
                socket.close();
        }
        return output;
    }
    
    
    
    //============ SEGUNDO INTENTO
       public boolean receiveMonster2() {
        MulticastSocket s = null;
        try {       
            InetAddress group = InetAddress.getByName(this.multicastIp); // destination multicast group
            s = new MulticastSocket(this.multicastPort);
            s.joinGroup(group);
            System.out.println("Waiting for messages");
            byte[] buffer = new byte[1000];
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            s.receive(messageIn);
            System.out.println(messageIn.getData().toString());
            System.out.println(currentMonster.toString());
            currentMonster.deString(new String(messageIn.getData()));
            System.out.println(currentMonster.toString());
            /*
            String[] data = messageIn.getData().toString().split(",");
            int[] coordinates = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                coordinates[i] = Integer.parseInt(data[i]);
            }
*/
            System.out.println("Message: " + new String(messageIn.getData()) + " from: " + messageIn.getAddress());
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
                    
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return true;
    }

    
    

    //TCP 
    public boolean sendAnswer() {
        boolean resp = false;

        Socket s = null;
        try {
            int serverPort = tcpPort;

            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort); 
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out
                    = new ObjectOutputStream(s.getOutputStream());

            System.out.println("Mandando desde el cliente");
            out.writeObject(currentMonster);        	// UTF is a string encoding 

            Monster me = (Monster) in.readObject();
            System.out.println("Received: " + me.getIp());
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
        return resp;
    }

    public static void main(String[] args) {
        Player player = new Player("Victor", "hola123");
        player.logIn();
        while(true){
           player.receiveMonster2();
        }
  
    }
}
