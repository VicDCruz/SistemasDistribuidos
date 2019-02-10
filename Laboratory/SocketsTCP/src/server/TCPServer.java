package server;

import java.net.*;
import java.io.*;

/**
 *
 * @author VicDCruz
 */
public class TCPServer {

    public static void main(String args[]) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept(); // Listens for a connection to be made to this socket and
                                                             // accepts it. The method blocks until a connection is
                                                             // made.
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    AddressBook ab;

    public Connection(Socket aClientSocket) {
        this.ab = new AddressBook();
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
        try { // an echo server

            int data = in.readInt();
            while (data != -1) {
                System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
                Person p = ab.getRecord(data);
                if (p != null)
                    out.writeUTF(p.getName());
                else
                    out.writeUTF("No existe persona");
                data = in.readInt();
            }
            System.out.println("FINISH");
            closeConnection();
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }

    public void closeConnection() {

        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
