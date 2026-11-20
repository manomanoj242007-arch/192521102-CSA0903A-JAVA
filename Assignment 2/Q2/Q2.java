import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class Q2 {
    private static volatile long result;

    public static long measureTime(Runnable operation) {
        long startTime = System.nanoTime();
        operation.run();
        return System.nanoTime() - startTime;
    }

    public static void compareCollections(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
            arrayDeque.addLast(i);
        }
        int operations = Math.min(size, 1000);
        long arrayAppend = measureTime(() -> {
            for (int i = 0; i < operations; i++) arrayList.add(i);
        });
        long linkedAppend = measureTime(() -> {
            for (int i = 0; i < operations; i++) linkedList.add(i);
        });
        long dequeAppend = measureTime(() -> {
            for (int i = 0; i < operations; i++) arrayDeque.addLast(i);
        });
        long arrayFront = measureTime(() -> {
            for (int i = 0; i < operations; i++) arrayList.add(0, -i);
        });
        long linkedFront = measureTime(() -> {
            for (int i = 0; i < operations; i++) linkedList.addFirst(-i);
        });
        long dequeFront = measureTime(() -> {
            for (int i = 0; i < operations; i++) arrayDeque.addFirst(-i);
        });
        long arrayAccess = measureTime(() -> {
            for (int i = 0; i < operations; i++)
                result += arrayList.get(arrayList.size() / 2);
        });
        long linkedAccess = measureTime(() -> {
            for (int i = 0; i < operations; i++)
                result += linkedList.get(linkedList.size() / 2);
        });
        System.out.println("\nPlaylist size: " + size);
        System.out.printf("Append times (ms): ArrayList=%.3f, LinkedList=%.3f, ArrayDeque=%.3f%n",
            arrayAppend / 1_000_000.0, linkedAppend / 1_000_000.0, dequeAppend / 1_000_000.0);
        System.out.printf("Front insertion times (ms): ArrayList=%.3f, LinkedList=%.3f, ArrayDeque=%.3f%n",
            arrayFront / 1_000_000.0, linkedFront / 1_000_000.0, dequeFront / 1_000_000.0);
        System.out.printf("Middle access times (ms): ArrayList=%.3f, LinkedList=%.3f%n",
            arrayAccess / 1_000_000.0, linkedAccess / 1_000_000.0);
    }

    public static void main(String[] args) {
        compareCollections(1000);
        compareCollections(5000);
        compareCollections(10000);
        ArrayDeque<String> upNext = new ArrayDeque<>();
        upNext.addLast("Song A");
        upNext.addLast("Song B");
        upNext.addFirst("Urgent Song");
        System.out.println("\nUp Next Queue:");
        System.out.println(upNext.removeFirst());
        System.out.println(upNext.removeFirst());
    }
}