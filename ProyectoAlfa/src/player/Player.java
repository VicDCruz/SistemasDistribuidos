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

/**
 *
 * @author VicDCruz
 */
public class Player {

    private InetAddress group;
    private MulticastSocket socket;
    private byte[] buffer;

    private String InetAddressNum;
    private int socketGroupNum;

    public Player() {
    }

    //Sin RMI
    public Player(String myInetAddressNum, int myGroup) {
        try {
            this.group = InetAddress.getByName(InetAddressNum); // destination multicast group 
            this.socket = new MulticastSocket(6789);
            this.socket.joinGroup(group);
            this.buffer = new byte[1000];

        } catch (UnknownHostException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Con RMI
    public Player(String name) {
        if (lookUpGame(name)) {
            try {
                this.group = InetAddress.getByName(InetAddressNum); // destination multicast group 
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
    public boolean lookUpGame(String playerName) {
        boolean res = false;
        System.setProperty("java.security.policy", "file:./src/player/player.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String name = "HitMonster";
            Registry registry = LocateRegistry.getRegistry("localhost"); // server's ip address args[0]
            Play playGame = (Play) registry.lookup(name);

            Credential info = playGame.login(playerName);
            InetAddressNum = info.getInetAddressNum();
            socketGroupNum = info.getSocketGroupNum();
            

            System.out.println("La clave es: " +socketGroupNum + " "+ InetAddressNum);
            res = true;

        } catch (Exception e) {
            System.err.println("exception");
            e.printStackTrace();
            res = false;
        }
        return res;
    }

    //UDP receiver
    public boolean receiveMonster() {
        MulticastSocket s = null;
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("Waiting for messages");
                DatagramPacket messageIn
                        = new DatagramPacket(this.buffer, this.buffer.length);
                this.socket.receive(messageIn);
                System.out.println("Message: " + new String(messageIn.getData()) + " from: " + messageIn.getAddress());
            }
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

    //TCP Sender
    public void sendAnswer(int x, int y) {
        Socket s = null;
        try {
            int serverPort = 7896;

            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort);    
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out
                    = new DataOutputStream(s.getOutputStream());
            out.writeUTF("" + x + "," + y);        	// UTF is a string encoding 

            String data = in.readUTF();
            System.out.println("Received: " + data);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
    }

    public static void main(String args[]) {
        System.out.println("Hello, I'm a player");
        Player p = new Player("Paola");
        p.lookUpGame("Paola");

    }
    // get messages from others in group

}
