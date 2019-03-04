/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

/**
 *
 * @author daniel
 */
public class Statics {
    public static int totalPlayers = 0;
    public static int remainingSpaces;
    public static int totalRounds;
    public static User[] players;
    public static boolean hasWinner;
    
    public static User findPlayer(String ip) {
        int i = Statics.binarySearch(Statics.players, new User("", "", ip), 0,
                Statics.totalPlayers);
        if (i != -1) {
            return Statics.players[i];
        }
        return null;
    }
    
    public static <T extends Comparable<T>> int binarySearch(T[] array, T element, int low, int high) {
        if (low > high) {
            return -1;
        }
        int middle = (high + low) / 2;
        T middleElement = array[middle];
        int comparisson = element.compareTo(middleElement);
        if (comparisson < 0) {
            return Statics.binarySearch(array, element, low, middle - 1);
        } else if (comparisson > 0) {
            return Statics.binarySearch(array, element, middle + 1, high);
        } else {
            return middle;
        }
    }

    public static void updateScore(User player, int newScore) {
        int i = Statics.binarySearch(Statics.players, player, 0, Statics.totalPlayers);
        Statics.players[i].setScore(newScore);
    }
}
