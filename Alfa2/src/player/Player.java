package player;

import interfaces.Information;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.Register;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
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

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        try {
            InetAddress inet = InetAddress.getLocalHost();
            this.ip = inet.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
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
    
    public boolean receiveMonster() {
        System.setProperty("java.net.preferIPv4Stack", "true");
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

    public static void main(String[] args) {
        Player player = new Player("Victor", "hola123");
        player.logIn();
        player.receiveMonster();
    }
}