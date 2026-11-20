import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Patient {

    final String name;
    final int severity;
    final long arrival;

    Patient(String name, int severity, long arrival) {
        this.name = name;
        this.severity = severity;
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return name + "(S" + severity + ")";
    }
}

public class Q6 {

    private long counter = 0;

    // Lower severity number is served first.
    // For equal severity, the lower arrival counter is served first.
    private final PriorityQueue<Patient> queue =
        new PriorityQueue<>(
            Comparator.comparingInt((Patient patient) -> patient.severity)
                .thenComparingLong(patient -> patient.arrival)
        );

    public void admit(String name, int severity) {

        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException(
                "Severity must be between 1 and 5"
            );
        }

        queue.add(new Patient(name, severity, counter++));
    }

    public Patient next() {
        return queue.poll();
    }

    public List<Patient> waitingInTreatmentOrder() {

        List<Patient> waiting = new ArrayList<>(queue);

        waiting.sort(
            Comparator.comparingInt((Patient patient) -> patient.severity)
                .thenComparingLong(patient -> patient.arrival)
        );

        return waiting;
    }

    public static void main(String[] args) {

        Q6 emergencyRoom = new Q6();

        emergencyRoom.admit("Ravi", 3);
        emergencyRoom.admit("Meena", 1);
        emergencyRoom.admit("John", 3);
        emergencyRoom.admit("Asha", 5);
        emergencyRoom.admit("Kumar", 1);
        emergencyRoom.admit("Lata", 2);

        System.out.println("Treatment Order:");

        StringBuilder treatmentOrder = new StringBuilder();

        Patient patient;

        while ((patient = emergencyRoom.next()) != null) {
            treatmentOrder.append(patient).append(' ');
        }

        System.out.println(treatmentOrder.toString().trim());
    }
}