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
public class ClientThread extends Thread {
    TCPClient tc;
    int request;
    double sd;
    double mean;
    int client;

    public double getSd() {
        return sd;
    }

    public ClientThread(int request, int client) {
        this.tc = new TCPClient(7896);
        this.request = request;
        this.client = client;
    }

    @Override
    public void run() {
        long[] list = new long[this.request];
        for (int i = 0; i < this.request; i++) {
            int random = (int) Math.round(Math.random() * 4);
            list[i] = tc.sendRequest(random);
        }
        tc.sendRequest(-1);
        tc.closeConnection();
        this.sd = this.stdDev(list);
        this.mean = this.mean(list);
        // System.out.println(this.client + "," + this.request + "," + this.sd);
        System.out.println(this.client + "," + this.request + "," + this.mean);

    }

    private double stdDev(long[] list) {
        double sum = 0.0;
        double num = 0.0;

        for (int i = 0; i < list.length; i++)
            sum += list[i];

        double mean = sum / list.length;

        for (int i = 0; i < list.length; i++)
            num += Math.pow((list[i] - mean), 2);
        return Math.sqrt(num / list.length);
    }

    public double mean(long[] list) {
        double sum = 0.0;

        for (int i = 0; i < list.length; i++)
            sum += list[i];

        double mean = sum / list.length;
        return mean;
    }
}
