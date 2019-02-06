/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoalfa;

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

    public Player(String ipAdress, int socket) {
        try {
            this.group = InetAddress.getByName(ipAdress);
            this.socket = new MulticastSocket(socket);
            this.socket.joinGroup(group);
            this.buffer = new byte[1000];
        } catch (UnknownHostException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            out.writeUTF(""+x+","+y);        	// UTF is a string encoding 

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
    }
    // get messages from others in group
}
