import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EventRegistrationApp extends JFrame {
    private final JTextField studentId = new JTextField(12);
    private final JTextField studentName = new JTextField(15);
    private final JComboBox<String> event = new JComboBox<>(
        new String[]{"Cultural Fest", "Tech Symposium", "Sports Day"});
    private final DefaultTableModel model =
        new DefaultTableModel(new String[]{"ID", "Student", "Event"}, 0);
    private final JTable table = new JTable(model);
    private final RegistrationService service;

    public EventRegistrationApp() {
        super("Campus Event Registration");
        service = new RegistrationService();
        setJMenuBar(createMenu());

        JPanel form = new JPanel(new GridLayout(3, 2, 6, 6));
        form.add(new JLabel("Student ID"));
        form.add(studentId);
        form.add(new JLabel("Student Name"));
        form.add(studentName);
        form.add(new JLabel("Event"));
        form.add(event);

        JButton register = new JButton("Register");
        register.addActionListener(e -> registerStudent());

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(register, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);
        loadRegistrations();
    }

    private JMenuBar createMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem refresh = new JMenuItem("Refresh");
        refresh.addActionListener(e -> loadRegistrations());
        file.add(refresh);
        bar.add(file);
        return bar;
    }

    private void registerStudent() {
        String id = studentId.getText().trim();
        String name = studentName.getText().trim();
        String selectedEvent = (String) event.getSelectedItem();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter student ID and name.");
            return;
        }

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                service.register(id, name, selectedEvent);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(EventRegistrationApp.this,
                        "Registration successful.");
                    loadRegistrations();
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(EventRegistrationApp.this,
                        error.getCause() == null ? error.getMessage()
                            : error.getCause().getMessage(),
                        "Registration Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void loadRegistrations() {
        new SwingWorker<java.util.List<String[]>, Void>() {
            @Override
            protected java.util.List<String[]> doInBackground() throws Exception {
                return service.list();
            }

            @Override
            protected void done() {
                try {
                    model.setRowCount(0);
                    for (String[] row : get()) {
                        model.addRow(row);
                    }
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(EventRegistrationApp.this,
                        "Unable to load registrations: " + error.getMessage());
                }
            }
        }.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new EventRegistrationApp().setVisible(true));
    }
}

class RegistrationService {
    private static final String URL = "jdbc:mysql://localhost:3306/campus_events";
    private static final String USER = "root";
    private static final String PASSWORD = "change_me";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    void register(String id, String name, String event) throws SQLException {
        String sql = "INSERT INTO registrations (student_id, student_name, event_name) VALUES (?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, event);
            statement.executeUpdate();
        }
    }

    java.util.List<String[]> list() throws SQLException {
        java.util.List<String[]> rows = new java.util.ArrayList<>();
        String sql = "SELECT student_id, student_name, event_name FROM registrations ORDER BY id DESC LIMIT 100 OFFSET 0";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                rows.add(new String[]{
                    result.getString("student_id"),
                    result.getString("student_name"),
                    result.getString("event_name")
                });
            }
        }
        return rows;
    }
}