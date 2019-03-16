/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class DispatcherPlayers {
   public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack" , "true");
        int max = 60;
        Player p;
        for (int i = 0; i < max; i++) {
            if (i % 10 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DispatcherPlayers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            p = new Player(Math.random() + "", "hola123");
            p.start();
        }
    }
}
