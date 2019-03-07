/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

/**
 *
 * @author daniel
 */
public class DispatcherPlayers {
   public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack" , "true");
        int max = 10;
        Player p;
        for (int i = 0; i < max; i++) {
            p = new Player(Math.random() + "", "hola123");
            p.start();
        }
    }
}
