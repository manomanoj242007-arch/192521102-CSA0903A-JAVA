import java.util.ArrayDeque;
import java.util.ArrayList;

public class Q7 {
    private static volatile int result;

    public static long testArrayList(int size, int operations) {
        ArrayList<Integer> playlist = new ArrayList<>();
        for (int i = 0; i < size; i++) playlist.add(i);
        long startTime = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            playlist.add(0, i);
            result += playlist.remove(0);
        }
        return System.nanoTime() - startTime;
    }

    public static long testArrayDeque(int size, int operations) {
        ArrayDeque<Integer> upNext = new ArrayDeque<>();
        for (int i = 0; i < size; i++) upNext.addLast(i);
        long startTime = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            upNext.addFirst(i);
            result += upNext.removeFirst();
        }
        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) {
        int operations = 5000;
        int[] playlistSizes = {1000, 10000, 50000};
        System.out.println("Q7: Original and Redesigned Comparison");
        System.out.println("Front add/remove operations: " + operations);
        for (int size : playlistSizes) {
            long arrayListTime = testArrayList(size, operations);
            long arrayDequeTime = testArrayDeque(size, operations);
            System.out.println("\nPlaylist size: " + size);
            System.out.printf("ArrayList time: %.3f ms%n", arrayListTime / 1_000_000.0);
            System.out.printf("ArrayDeque time: %.3f ms%n", arrayDequeTime / 1_000_000.0);
        }
        System.out.println("\nRedesign objective: reduce front operation cost.");
        System.out.println("ArrayList front insertion/removal: O(n).");
        System.out.println("ArrayDeque end operations: amortized O(1).");
    }
}