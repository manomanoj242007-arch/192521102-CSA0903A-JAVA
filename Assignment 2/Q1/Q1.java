import java.util.Arrays;

public class Q1 {

    private static volatile int result;

    // Approach 1: Build ticket text using String concatenation.
    public static String buildUsingString(String[] fields) {
        String ticket = "";

        for (String field : fields) {
            ticket = ticket + field;
        }

        return ticket;
    }

    // Approach 2: Build ticket text using StringBuilder.
    public static String buildUsingStringBuilder(String[] fields) {
        StringBuilder ticket = new StringBuilder();

        for (String field : fields) {
            ticket.append(field);
        }

        return ticket.toString();
    }

    // Approach 3: Build ticket text using StringBuffer.
    public static String buildUsingStringBuffer(String[] fields) {
        StringBuffer ticket = new StringBuffer();

        for (String field : fields) {
            ticket.append(field);
        }

        return ticket.toString();
    }

    // Measure one approach using System.nanoTime().
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

        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    public static void main(String[] args) {

        int[][] workloads = {
            {1, 6},
            {1000, 50},
            {5000, 20}
        };

        String[] workloadNames = {
            "Short ticket",
            "Many fields",
            "Many tickets"
        };

        String[] approachNames = {
            "String",
            "StringBuilder",
            "StringBuffer"
        };

        for (int i = 0; i < workloads.length; i++) {

            int ticketCount = workloads[i][0];
            int fieldCount = workloads[i][1];

            System.out.println("\n" + workloadNames[i]);
            System.out.println("Tickets: " + ticketCount);
            System.out.println("Fields per ticket: " + fieldCount);

            for (int approach = 1; approach <= 3; approach++) {

                long totalTime = 0;

                for (int repeat = 0; repeat < 5; repeat++) {
                    totalTime += measure(ticketCount, fieldCount, approach);
                }

                double averageTime = totalTime / 5.0 / 1_000_000.0;

                System.out.printf(
                    "%s average time: %.3f ms%n",
                    approachNames[approach - 1],
                    averageTime
                );
            }
        }

        String[] sampleFields = {
            "PNR: 12345 | ",
            "Passenger: Arun | ",
            "Coach: S1 | ",
            "Berth: 12 | ",
            "Fare: 450"
        };

        System.out.println("\nSample Ticket:");
        System.out.println(buildUsingStringBuilder(sampleFields));
    }
}