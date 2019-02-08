/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import interfaces.Credential;
import interfaces.Play;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

import java.net.*;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente UDP (Multicast) Servidor TCP
 *
 * @author pmeji
 */
public class GameMaster implements Play {

    //Global variables
    private int xCoor;
    private int yCoor;
    private int round;
    private int[][] scoreBoard;
    private int n = 3; //Number of wins per round to win
    private int maxNumPlayers = 10;
    
    private String InetAddressNum  = "228.5.6.7";
    private int socketGroup = 6789;

    public GameMaster() throws RemoteException {
        super();
    }

    public GameMaster(int numPlayers, int numWins)  {
        round = 0;
        maxNumPlayers = numPlayers;
        n = numWins;
        scoreBoard = new int[maxNumPlayers][n];
    }

    // RMI 
    public static void bindGame() {
        //"file:/C:/Users/pmeji/Documents/OpWin/SistemasDistribuidos/ProyectoAlfa/src/master/master.policy"
        System.setProperty("java.security.policy", "file:/C:/Users/pmeji/Documents/OpWin/SistemasDistribuidos/ProyectoAlfa/src/master/master.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            LocateRegistry.createRegistry(1099);
            String name = "HitMonster";
            Play engine = new GameMaster();
            Play stub
                    = (Play) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("HitMonster bound");
        } catch (Exception e) {
            System.err.println("HitMonster exception:");
            e.printStackTrace();
        }
    }

    public void sendMonster(int x, int y, int round) {
        MulticastSocket s = null;
        try {
            xCoor = x;
            yCoor = y;

            InetAddress group = InetAddress.getByName(InetAddressNum); // destination multicast group 
            s = new MulticastSocket(socketGroup);
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
        //GameMaster gm = new GameMaster(10,3);
        bindGame();

    }

    @Override
    public Credential login(String name) throws RemoteException {
        Credential info = new Credential(InetAddressNum,socketGroup);
        return info; //To change body of generated methods, choose Tools | Templates.
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
