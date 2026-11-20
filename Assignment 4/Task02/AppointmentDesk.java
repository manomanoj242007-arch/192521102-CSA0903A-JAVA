import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class AppointmentDesk extends JFrame {
    private final JTextField patientId = new JTextField(12);
    private final JComboBox<String> department =
        new JComboBox<>(new String[]{"Cardiology", "Neurology", "Orthopedics"});
    private final JComboBox<String> doctor = new JComboBox<>();
    private final JComboBox<String> slot =
        new JComboBox<>(new String[]{"09:00 AM", "10:00 AM", "11:00 AM"});
    private boolean bookingInProgress = false;

    private final Map<String, java.util.List<String>> doctors = Map.of(
        "Cardiology", java.util.List.of("Dr. Arun", "Dr. Meena"),
        "Neurology", java.util.List.of("Dr. Ravi", "Dr. Priya"),
        "Orthopedics", java.util.List.of("Dr. Kumar", "Dr. Nila")
    );

    public AppointmentDesk() {
        super("Hospital Appointment Desk");
        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.add(new JLabel("Patient ID:"));
        form.add(patientId);
        form.add(new JLabel("Department:"));
        form.add(department);
        form.add(new JLabel("Doctor:"));
        form.add(doctor);
        form.add(new JLabel("Time slot:"));
        form.add(slot);

        JButton book = new JButton("Book Appointment");
        add(form, BorderLayout.CENTER);
        add(book, BorderLayout.SOUTH);

        department.addActionListener(e -> refreshDoctors());
        book.addActionListener(e -> bookAppointment());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (bookingInProgress) {
                    int answer = JOptionPane.showConfirmDialog(
                        AppointmentDesk.this, "Booking is in progress. Close?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION);
                    if (answer != JOptionPane.YES_OPTION) {
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        return;
                    }
                }
                dispose();
            }
        });

        refreshDoctors();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(440, 240);
        setLocationRelativeTo(null);
    }

    private void refreshDoctors() {
        doctor.removeAllItems();
        String selected = (String) department.getSelectedItem();
        if (selected != null) {
            for (String name : doctors.get(selected)) {
                doctor.addItem(name);
            }
        }
    }

    private void bookAppointment() {
        if (patientId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter patient ID.");
            return;
        }
        bookingInProgress = true;
        JOptionPane.showMessageDialog(this,
            "Appointment booked for " + patientId.getText()
            + " with " + doctor.getSelectedItem()
            + " at " + slot.getSelectedItem());
        bookingInProgress = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppointmentDesk().setVisible(true));
    }
}