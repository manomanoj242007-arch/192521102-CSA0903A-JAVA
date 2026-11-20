import java.util.Arrays;

public class Q1 {
    private static volatile int result;

    public static String buildUsingString(String[] fields) {
        String ticket = "";
        for (String field : fields) {
            ticket = ticket + field;
        }
        return ticket;
    }

    public static String buildUsingStringBuilder(String[] fields) {
        StringBuilder ticket = new StringBuilder();
        for (String field : fields) {
            ticket.append(field);
        }
        return ticket.toString();
    }

    public static String buildUsingStringBuffer(String[] fields) {
        StringBuffer ticket = new StringBuffer();
        for (String field : fields) {
            ticket.append(field);
        }
        return ticket.toString();
    }

    public static long measure(int ticketCount, int fieldCount, int approach) {
        String[] fields = new String[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            fields[i] = "Field" + i + " | ";
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < ticketCount; i++) {
            String ticket;
            if (approach == 1) {
                ticket = buildUsingString(fields);
            } else if (approach == 2) {
                ticket = buildUsingStringBuilder(fields);
            } else {
                ticket = buildUsingStringBuffer(fields);
            }
            result += ticket.length();
        }
        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) {
        int[][] workloads = {{1, 6}, {1000, 50}, {5000, 20}};
        String[] names = {"String", "StringBuilder", "StringBuffer"};
        String[] workloadNames = {"Short ticket", "Many fields", "Many tickets"};
        for (int i = 0; i < workloads.length; i++) {
            System.out.println("\n" + workloadNames[i]);
            for (int approach = 1; approach <= 3; approach++) {
                long totalTime = 0;
                for (int repeat = 0; repeat < 5; repeat++) {
                    totalTime += measure(workloads[i][0], workloads[i][1], approach);
                }
                System.out.printf("%s average time: %.3f ms%n",
                    names[approach - 1], totalTime / 5.0 / 1_000_000.0);
            }
        }
        String[] sample = {"PNR: 12345 | ", "Passenger: Arun | ",
            "Coach: S1 | ", "Berth: 12 | ", "Fare: 450"};
        System.out.println("\nSample Ticket:");
        System.out.println(buildUsingStringBuilder(sample));
    }
}