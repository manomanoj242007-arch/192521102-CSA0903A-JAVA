import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SalesReport {
    public static void main(String[] args) {
        List<Path> stores = List.of(
            Path.of("store1.txt"),
            Path.of("store2.txt"),
            Path.of("store3.txt")
        );
        Path report = Path.of("daily_report.txt");
        int total = 0;

        try (BufferedWriter writer = Files.newBufferedWriter(report)) {
            writer.write("DAILY SALES REPORT");
            writer.newLine();
            for (Path file : stores) {
                try (BufferedReader reader = Files.newBufferedReader(file)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        total += Integer.parseInt(line.trim());
                    }
                    writer.write(file.getFileName() + " processed");
                    writer.newLine();
                } catch (IOException | NumberFormatException e) {
                    System.out.println("Failed while reading " + file + ": " + e.getMessage());
                }
            }
            writer.write("Total sales: " + total);
            writer.newLine();
            System.out.println("Report created: " + report);
            System.out.println("Total sales: " + total);
        } catch (IOException e) {
            System.out.println("Unable to write report: " + e.getMessage());
        }
    }
}