import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultProcessor {
    private static class ResultTask implements Callable<String> {
        private final Path file;

        ResultTask(Path file) {
            this.file = file;
        }

        @Override
        public String call() throws Exception {
            if (!Files.exists(file)) {
                throw new IOException("File is missing");
            }
            int score = Integer.parseInt(Files.readString(file).trim());
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException("Invalid score");
            }
            return file.getFileName() + ": " + score;
        }
    }

    public static void main(String[] args) throws Exception {
        List<Path> files = List.of(
            Path.of("student1.txt"),
            Path.of("student2.txt"),
            Path.of("student3.txt"),
            Path.of("missing.txt")
        );

        ExecutorService pool = Executors.newFixedThreadPool(4);
        ConcurrentLinkedQueue<String> successes = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<String> failures = new ConcurrentLinkedQueue<>();

        try {
            List<Future<String>> futures = new ArrayList<>();
            for (Path file : files) {
                futures.add(pool.submit(new ResultTask(file)));
            }

            for (int i = 0; i < futures.size(); i++) {
                try {
                    successes.add(futures.get(i).get());
                } catch (Exception e) {
                    Throwable cause = e.getCause();
                    failures.add(files.get(i).getFileName() + ": "
                        + (cause == null ? e.getMessage() : cause.getMessage()));
                }
            }
        } finally {
            pool.shutdown();
        }

        System.out.println("Successful files: " + successes.size());
        for (String result : successes) {
            System.out.println("  " + result);
        }
        System.out.println("Failed files: " + failures.size());
        for (String failure : failures) {
            System.out.println("  " + failure);
        }
        System.out.println("Processed total: " + (successes.size() + failures.size()));
    }
}