import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class Q2 {

    private static volatile long result;

    // Measure the execution time of an operation.
    public static long measureTime(Runnable operation) {
        long startTime = System.nanoTime();

        operation.run();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1_000_000;
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
            for (int i = 0; i < operations; i++) {
                arrayList.add(i);
            }
        });

        long linkedAppend = measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                linkedList.add(i);
            }
        });

        long dequeAppend = measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                arrayDeque.addLast(i);
            }
        });

        long arrayFrontInsert = measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                arrayList.add(0, -i);
            }
        });

        long linkedFrontInsert = measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                linkedList.addFirst(-i);
            }
        });

        long dequeFrontInsert = measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                arrayDeque.addFirst(-i);
            }
        });

        long arrayAccess = measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                result += arrayList.get(arrayList.size() / 2);
            }
        });

        long linkedAccess = measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                result += linkedList.get(linkedList.size() / 2);
            }
        });

        System.out.println("\nPlaylist size: " + size);
        System.out.println("Append time (ms):");
        System.out.println("ArrayList: " + arrayAppend);
        System.out.println("LinkedList: " + linkedAppend);
        System.out.println("ArrayDeque: " + dequeAppend);

        System.out.println("Front insertion time (ms):");
        System.out.println("ArrayList: " + arrayFrontInsert);
        System.out.println("LinkedList: " + linkedFrontInsert);
        System.out.println("ArrayDeque: " + dequeFrontInsert);

        System.out.println("Middle access time (ms):");
        System.out.println("ArrayList: " + arrayAccess);
        System.out.println("LinkedList: " + linkedAccess);
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