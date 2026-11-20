import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class PassStore {
    private final Path file;

    public PassStore(Path file) {
        this.file = file;
    }

    public void add(String passId, String regNo, String name, int route, double amount)
            throws IOException {
        if (passId == null || passId.isBlank() || regNo == null || regNo.isBlank()
                || name == null || name.isBlank() || route < 0 || amount < 0) {
            throw new IllegalArgumentException("Invalid pass details.");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(file,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(String.join(",", passId, regNo, name,
                    String.valueOf(route), String.valueOf(amount)));
            writer.newLine();
        }
    }

    /** Returns five fields, or null when no valid matching pass exists. */
    public String[] find(String passId) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (fields.length != 5) {
                    System.err.println("Skipping damaged line: " + line);
                    continue;
                }
                try {
                    Integer.parseInt(fields[3]);
                    Double.parseDouble(fields[4]);
                } catch (NumberFormatException error) {
                    System.err.println("Skipping invalid numeric fields: " + line);
                    continue;
                }
                if (fields[0].equals(passId)) {
                    return fields;
                }
            }
        }
        return null;
    }

    /** Returns all valid passes assigned to the requested route. */
    public java.util.List<String[]> listByRoute(int wantedRoute) throws IOException {
        java.util.List<String[]> matches = new java.util.ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (fields.length != 5) {
                    System.err.println("Skipping damaged line: " + line);
                    continue;
                }
                try {
                    int route = Integer.parseInt(fields[3]);
                    Double.parseDouble(fields[4]);
                    if (route == wantedRoute) {
                        matches.add(fields);
                    }
                } catch (NumberFormatException error) {
                    System.err.println("Skipping invalid numeric fields: " + line);
                }
            }
        }
        return matches;
    }

    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("passes", ".csv");
        PassStore store = new PassStore(file);
        store.add("P1001", "192311001", "Arun", 12, 4500);
        Files.writeString(file, "P1002,DAMAGED LINE\n", StandardOpenOption.APPEND);
        store.add("P1003", "192311003", "Divya", 7, 3800);
        System.out.println(Arrays.toString(store.find("P1003")));
        System.out.println(Arrays.toString(store.find("P1002")));\n        System.out.println("Route 7 passes: " + store.listByRoute(7).size());
        Files.deleteIfExists(file);
    }
}
