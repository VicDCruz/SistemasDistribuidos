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

/**
 *
 * @author VicDCruz
 */
public class Player {
	InetAddress group
	MulticastSocket socket;
	byte[] buffer

	Player(String ipAdress, int socket) {
		this.group = InetAddress.getByName(ipAdress);
		this.socket = new MulticastSocket(socket);
		this.socket.joinGroup(group);
		this.buffer = new byte[1000];
	}

	public boolean receiveExampleMessage() {
		try {
			for(int i=0; i< 3; i++) {
				System.out.println("Waiting for messages");
				DatagramPacket messageIn = 
					new DatagramPacket(this.buffer, this.buffer.length);
				this.socket.receive(messageIn);
				System.out.println("Message: " + new String(messageIn.getData())+ " from: "+ messageIn.getAddress());
			}
		} catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		}
		catch (IOException e){
			System.out.println("IO: " + e.getMessage());
		}
		finally {
			if(this.socket != null) s.close();
        }
	}

    public static void main(String args[]){ 
			System.out.println("hola");
    }		     
                 // get messages from others in group
}
