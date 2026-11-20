import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedLineCounter {
    public static long countUnbuffered(Path path) throws IOException {
        long lines = 0;
        try (FileInputStream in = new FileInputStream(path.toFile())) {
            int value;
            while ((value = in.read()) != -1) {
                if (value == '\\n') lines++;
            }
        }
        return lines;
    }

    public static long countBuffered(Path path) throws IOException {
        long lines = 0;
        try (BufferedInputStream in =
                     new BufferedInputStream(new FileInputStream(path.toFile()))) {
            int value;
            while ((value = in.read()) != -1) {
                if (value == '\\n') lines++;
            }
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        Path sample = Files.createTempFile("attendance", ".log");
        Files.writeString(sample, "Student A\nStudent B\nStudent C\n");
        long start = System.nanoTime();
        long plain = countUnbuffered(sample);
        long plainTime = System.nanoTime() - start;
        start = System.nanoTime();
        long buffered = countBuffered(sample);
        long bufferedTime = System.nanoTime() - start;
        System.out.println("Unbuffered lines: " + plain);
        System.out.println("Buffered lines: " + buffered);
        System.out.println("Unbuffered time (ns): " + plainTime);
        System.out.println("Buffered time (ns): " + bufferedTime);
        Files.deleteIfExists(sample);
    }
}
