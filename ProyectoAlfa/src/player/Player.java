/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import interfaces.Credential;
import interfaces.Play;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;

/**
 *
 * @author VicDCruz
 */
public class Player {

    private InetAddress group;
    private MulticastSocket socket;
    private byte[] buffer;

    private String inetAddressNum;
    private int socketGroupNum;

    public Player() {
    }

    // Sin RMI
    public Player(String myInetAddressNum, int myGroup) {
        InetAddress inetAddress = InetAddress.getLocalHost();
        try {
            this.group = InetAddress.getByName(inetAddressNum); // destination multicast group
            this.socket = new MulticastSocket(6789);
            this.socket.joinGroup(group);
            this.buffer = new byte[1000];

        } catch (UnknownHostException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Con RMI
    public Player(String name, String password) {
        InetAddress inetAddress = InetAddress.getLocalHost();
        if (lookUpGame(name, password, inetAddress.getHostAddress())) {
            try {
                this.group = InetAddress.getByName(inetAddressNum); // destination multicast group
                this.socket = new MulticastSocket(socketGroupNum);
                this.socket.joinGroup(group);
                this.buffer = new byte[1000];

            } catch (UnknownHostException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // RMI Lookup
    public boolean lookUpGame(String playerName, String password, String ip) {
        System.setProperty("java.security.policy", "file:./src/player/player.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String name = "HitMonster";
            Registry registry = LocateRegistry.getRegistry("localhost"); // server's ip address args[0]
            Play playGame = (Play) registry.lookup(name);

            Credential info = playGame.login(playerName, password, ip);
            if (info == null) {
                System.out.println("Sala totalmente llena");
                return false;
            }
            inetAddressNum = info.getInetAddressNum();
            socketGroupNum = info.getSocketGroupNum();

            System.out.println("La clave es: " + socketGroupNum + " " + inetAddressNum);
            return true;
        } catch (Exception e) {
            System.err.println("exception");
            e.printStackTrace();
            return false;
        }
    }

    // UDP receiver
    public boolean receiveMonster() {
        MulticastSocket s = null;
        try {
            InetAddress group = InetAddress.getByName("228.5.60.7"); // destination multicast group
            s = new MulticastSocket(this.socketGroupNum);
            s.joinGroup(group);
            System.out.println("Waiting for messages");
            DatagramPacket messageIn = new DatagramPacket(this.buffer, this.buffer.length);
            s.receive(messageIn);
            String[] data = messageIn.getData().toString().split(",");
            int[] coordinates = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                coordinates[i] = (int) data[i];
            }
            System.out.println("Message: " + new String(messageIn.getData()) + " from: " + messageIn.getAddress());
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (this.socket != null) {
                s.close();
            }
        }
        return true;
    }

    // TCP Sender
    public boolean sendAnswer(int x, int y) {
        Socket s = null;
        try {
            int serverPort = 7896;

            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String messageOut = x + ", " + y;
            out.writeUTF(messageOut); // UTF is a string encoding
            String messageIn = in.readUTF();
            if (messageIn.equals(messageOut)) {
                System.out.println("Confirmation received");
                return true;
            }
            return false;
        } catch (UnknownHostException e) {
            System.out.println("Sock: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close: " + e.getMessage());
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("Hello, I'm a player");
        Player p = new Player("Paola", "hola");
        p.lookUpGame("Paola", "hola", inetAddress.getHostAddress());
    }

}
