/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author daniel
 */
public class ReceiveAnswer extends Thread {
    private final int tcpPort;
    
    public ReceiveAnswer(int tcpPort) {
        this.tcpPort = tcpPort;
    }
    
    @Override
    public void run() {
        try {
            int cont = 1;
            ServerSocket listenSocket = new ServerSocket(this.tcpPort);
            while (true) {
                int newPort = this.tcpPort + cont;
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept();
                Connection connection = new Connection(clientSocket, newPort);
                connection.start();
                cont++;
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}
