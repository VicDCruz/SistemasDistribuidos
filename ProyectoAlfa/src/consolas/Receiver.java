/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolas;

/**
 *
 * @author daniel
 */
public class Receiver extends Thread {
    private final PlayerConsole pc;
    
    public Receiver(PlayerConsole pc) {
        this.pc = pc;
    }
    
    @Override
    public void run () {
        while (true) {
            pc.catchMonster();
        }
    }
}
