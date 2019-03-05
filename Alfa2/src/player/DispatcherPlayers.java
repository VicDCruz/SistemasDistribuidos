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
        Player p1 = new Player("Victor", "hola123");
        p1.start();
        Player p2 = new Player("Paola", "hola123");
        p2.start();
    }
}
