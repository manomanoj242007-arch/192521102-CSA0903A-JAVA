import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Q4 {
    public static void testMaps(int numberOfMedicines) {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        for (int i = numberOfMedicines - 1; i >= 0; i--) {
            String medicineCode = String.format("MED%05d", i);
            int stock = 100 + i;
            hashMap.put(medicineCode, stock);
            treeMap.put(medicineCode, stock);
            linkedHashMap.put(medicineCode, stock);
        }
        List<String> searchCodes = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            searchCodes.add(String.format("MED%05d", i % numberOfMedicines));
        }
        long startTime = System.nanoTime();
        for (String code : searchCodes) hashMap.get(code);
        long hashMapTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();
        for (String code : searchCodes) treeMap.get(code);
        long treeMapTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();
        for (String code : searchCodes) linkedHashMap.get(code);
        long linkedHashMapTime = System.nanoTime() - startTime;
        System.out.println("\nNumber of medicines: " + numberOfMedicines);
        System.out.printf("HashMap lookup time: %.3f ms%n", hashMapTime / 1_000_000.0);
        System.out.printf("TreeMap lookup time: %.3f ms%n", treeMapTime / 1_000_000.0);
        System.out.printf("LinkedHashMap lookup time: %.3f ms%n", linkedHashMapTime / 1_000_000.0);
        System.out.println("First code in sorted report: " + treeMap.keySet().iterator().next());
        System.out.println("First code in insertion report: " + linkedHashMap.keySet().iterator().next());
        System.out.println("Sorted report entries: " + treeMap.size());
        System.out.println("Insertion-order report entries: " + linkedHashMap.size());
    }

    public static void main(String[] args) {
        testMaps(1000);
        testMaps(5000);
        testMaps(10000);
    }
}