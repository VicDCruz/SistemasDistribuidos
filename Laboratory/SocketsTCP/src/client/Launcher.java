/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author VicDCruz
 */
public class Launcher {
    public static void main(String args[]) {
        int clients = 0;
        int request = 0;
        System.out.println("client, request, mean");
        for (int i = 1; i <= 3; i++) {
            request = i * 25;
            for (int j = 1; j <= 5; j++) {
                clients = j * 10;
                // ArrayList<Double> listG = new ArrayList<>();
                for (int k = 0; k < clients; k++) {
                    ClientThread clientThread = new ClientThread(request, clients);
                    clientThread.start();
                }
            }
        }
    }
}
