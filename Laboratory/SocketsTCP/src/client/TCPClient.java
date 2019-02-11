/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {
    Socket s;
    DataInputStream in;
    DataOutputStream out;

    public TCPClient(int serverPort) {
        try {
            this.s = new Socket("localhost", serverPort);
            // s = new Socket("127.0.0.1", serverPort);
            this.in = new DataInputStream(s.getInputStream());
            this.out = new DataOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long sendRequest(int idP) {
        if (idP != -1) {
            try {
                long startTime = System.currentTimeMillis();
                out.writeInt(idP); // UTF is a string encoding
                String data = in.readUTF();
                long spentTime = System.currentTimeMillis() - startTime;
                System.out.println("Received: " + data);
                return spentTime;
            } catch (IOException ex) {
                Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public void closeConnection() {
        if (this.s != null)
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
    }
}
