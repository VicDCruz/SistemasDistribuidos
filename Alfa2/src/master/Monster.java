package master;

import java.io.Serializable;

/**
 * Monster
 */
public class Monster implements Serializable {
    private int x;
    private int y;
    private int round;
    private String ip;

    public Monster(int x, int y, int round) {
        this.x = x;
        this.y = y;
        this.round = round;
        this.ip = null;
    }

    public Monster(int x, int y, int round, String ip) {
        this.x = x;
        this.y = y;
        this.round = round;
        this.ip = ip;
    }

    public Monster(byte[] array) {
        this.x = (int) array[0];
        this.y = (int) array[1];
        this.round = (int) array[2];
        if (array.length >= 4) {
            this.ip = new String(array, 3, array.length - 3);
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the round
     */
    public int getRound() {
        return this.round;
    }

    /**
     * @return the y
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return this.ip;
    }

    public byte[] getBytes() {
        byte x = (byte) this.x;
        byte y = (byte) this.y;
        byte round = (byte) this.round;
        byte[] output = {x, y, round};
        if (this.ip != null) {
            byte[] ipBytes = this.ip.getBytes();
            output = this.appendArrays(output, ipBytes);
        }
        return output;
    }

    private byte[] appendArrays(byte[] a1, byte[] a2) {
        byte[] output = new byte[a1.length + a2.length];
        for (int i = 0; i < a1.length; i++) {
            output[i] = a1[i];
        }
        for (int i = 0; i < a2.length; i++) {
            output[a1.length + i] = a2[i];
        }
        return output;
    }

    public String toString() {
        return "X: " + this.x + ", Y: " + this.y + ", ROUND: " + this.round;
    }

    public void deString(String s) {
        this.x = s.charAt(3) - 48;
        this.y = s.charAt(9)  - 48;
        this.round = s.charAt(19)  - 48;
    }

}
