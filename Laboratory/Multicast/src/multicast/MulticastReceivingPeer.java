/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author VicDCruz
 */
public class MulticastReceivingPeer {
    public static void main(String args[]) {

        MulticastSocket s = null;
        try {
            InetAddress group = InetAddress.getByName("228.5.60.7"); // destination multicast group
            s = new MulticastSocket(6789);
            s.joinGroup(group);

            byte[] buffer = new byte[1000];
            DatagramPacket messageIn = null;
            System.out.println("Waiting for messages");
            for (int i = 0; i < 10; i++) {
                messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                System.out.println("Message: " + new String(messageIn.getData()) + " from: " + messageIn.getAddress());
            }
            s.leaveGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null)
                s.close();
        }
    }
    // get messages from others in group
}
