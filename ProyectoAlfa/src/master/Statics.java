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
    
    public static User findPlayer(String id) {
        int i = Statics.binarySearch(Statics.players, new User("", id), 0,
                Statics.totalPlayers - 1);
        if (i != -1) {
            return Statics.players[i];
        }
        return null;
    }
    
    public static User getPlayer(int i) {
        if (i != -1) {
            return Statics.players[i];
        }
        return null;
    }
    
    public static void addUser(User newUser) {
        Statics.players[Statics.totalPlayers - 1] = newUser;
        Statics.players = Statics.quickSort(Statics.players, 0, Statics.totalPlayers - 1);
    }
    
    public static <T extends Comparable<T>> T[] quickSort(T[] array, int low, int high) {
        if (low >= high) {
            return array;
        }
        int mean = (low + high) / 2;
        T pivot = array[mean];
        int leftIndex = low, rightIndex = high;
        while (leftIndex < rightIndex)
        {
            int comparisonLeft = pivot.compareTo(array[leftIndex]);
            int comparisonRight = pivot.compareTo(array[rightIndex]);
            if ((comparisonLeft > 0 && comparisonRight < 0) || 
                ((comparisonLeft == 0 && comparisonRight < 0) || (comparisonLeft > 0 && comparisonRight == 0))) {
                T tmp = array[leftIndex];
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = tmp;
                leftIndex++;
                rightIndex--;
            } else if(comparisonLeft < 0 && comparisonRight < 0) {
                leftIndex++;
            } else if(comparisonLeft > 0 && comparisonRight > 0) {
                rightIndex--;
            } else {
                leftIndex++;
                rightIndex--;
            }
        }
        quickSort(array, low, mean);
        quickSort(array, mean + 1, high);
        return array;
    }
    
    public static <T extends Comparable<T>> int binarySearch(T[] array, T element, int low, int high) {
        if (low > high) {
            return -1;
        }
        int middle = (high + low) / 2;
        T middleElement = array[middle];
        if (middleElement == null) {
            return -1;
        }
        int comparisson = middleElement.compareTo(element);
        if (comparisson < 0) {
            return Statics.binarySearch(array, element, low, middle - 1);
        } else if (comparisson > 0) {
            return Statics.binarySearch(array, element, middle + 1, high);
        } else {
            return middle;
        }
    }

    public static void updateScore(User player, int newScore) {
        int i = Statics.binarySearch(Statics.players, player, 0, Statics.totalPlayers - 1);
        Statics.players[i].setScore(newScore);
    }

	public static void resetScore() {
        for (int i = 0; i < Statics.totalPlayers; i++) {
            Statics.players[i].setScore(0);
        }
	}
}
