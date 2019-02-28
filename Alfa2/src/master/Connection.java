package master;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.xml.crypto.Data;

/**
 * Connection
 */
public class Connection extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;

    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
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
            Monster monster = (Monster) this.in.readObject();
            System.out.println("Monster received...");
            if (!GameMaster.hasWinner) {
                User player = GameMaster.findPlayer(monster.getIp());
                this.out.writeObject(player.getScore());
            }
            this.out.writeObject(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            this.clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}