/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoalfa;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente UDP (Multicast) Servidor TCP
 *
 * @author pmeji
 */
public class GameMaster {

    //Global variables
    private int xCoor;
    private int yCoor;
    private int round;
    private int[][] scoreBoard;
    private int n = 3; //Number of wins per round to win
    private int maxNumPlayers = 10;

    public GameMaster() {
        round = 0;
        scoreBoard = new int[maxNumPlayers][n];
    }

    public void sendMonster(int x, int y, int round) {
        MulticastSocket s = null;
        try {
            xCoor = x;
            yCoor = y;

            InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
            s = new MulticastSocket(6789);
            s.joinGroup(group);
            //s.setTimeToLive(10);
            System.out.println("Messages' TTL (Time-To-Live): " + s.getTimeToLive());

            String myMessage = "(" + x + ", " + y + ") round = " + round;
            byte[] m = myMessage.getBytes();
            DatagramPacket messageOut
                    = new DatagramPacket(m, m.length, group, 6789);
            s.send(messageOut);

            s.leaveGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) {
                s.close();
            }
        }

    }

    public void recieveAnswer() {
        try {
            int serverPort = 7897;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }

    public static void main(String args[]) {
        GameMaster gm = new GameMaster();
        gm.sendMonster(1, 2, 1);

    }

}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {			                 // an echo server
            String data = in.readUTF();
            System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
            
            out.writeUTF(data);
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
