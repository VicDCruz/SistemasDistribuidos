package master;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Connection
 */
public class Connection extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    int newPort = -1;

    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.out = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.in = new ObjectInputStream(this.clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Connection(Socket clientSocket, int newPort) {
        this.clientSocket = clientSocket;
        this.newPort = newPort;
        try {
            this.out = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.in = new ObjectInputStream(this.clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        try {
            if (this.newPort > 0) {
                System.out.println("Sending new port...");
                this.out.writeObject(this.newPort);
                ServerSocket listenSocket = new ServerSocket(this.newPort);
                System.out.println("Waiting for messages...");
                Socket assistSocket = listenSocket.accept();
                Connection connection = new Connection(assistSocket);
                connection.start();
            } else {
                Monster monster = (Monster) this.in.readObject();
                System.out.println("Monster received...");
                System.out.println(monster.toString());
                User player = Statics.findPlayer(monster.getIp());
                if (player != null) {
                    System.out.println("Score: " + player.getScore() + 1);
                    this.out.writeObject(player.getScore() + 1);
                    Statics.updateScore(player, player.getScore() + 1);
                } else {
                    System.out.println("No existen registros");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnection();
        }
    }

    public void closeConnection() {
        try {
            this.clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}