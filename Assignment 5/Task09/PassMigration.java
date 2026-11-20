import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassMigration {
    private static final String URL = "jdbc:mysql://localhost:3306/assignment5";
    private static final String USER = "root";
    private static final String PASSWORD = "change_me";

    public static void migrate(Path file, Connection con) throws IOException, SQLException {
        List<String[]> valid = new ArrayList<>();
        List<String> failures = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            int lineNo = 0;
            while ((line = reader.readLine()) != null) {
                lineNo++;
                String[] fields = line.split(",", -1);
                try {
                    if (fields.length != 5) {
                        throw new IllegalArgumentException("expected five fields");
                    }
                    Integer.parseInt(fields[3]);
                    double amount = Double.parseDouble(fields[4]);
                    if (amount < 0 || fields[0].isBlank() || fields[1].isBlank()
                            || fields[2].isBlank()) {
                        throw new IllegalArgumentException("invalid field value");
                    }
                    valid.add(fields);
                } catch (RuntimeException error) {
                    failures.add("Line " + lineNo + ": " + error.getMessage() + " [" + line + "]");
                }
            }
        }
        String sql = "INSERT INTO bus_pass(pass_id, reg_no, name, route_no, amount) VALUES (?, ?, ?, ?, ?)";
        boolean oldAuto = con.getAutoCommit();
        con.setAutoCommit(false);
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int count = 0;
            for (String[] row : valid) {
                ps.setString(1, row[0]);
                ps.setString(2, row[1]);
                ps.setString(3, row[2]);
                ps.setInt(4, Integer.parseInt(row[3]));
                ps.setDouble(5, Double.parseDouble(row[4]));
                ps.addBatch();
                if (++count % 1000 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            con.commit();
        } catch (SQLException error) {
            con.rollback();
            throw error;
        } finally {
            con.setAutoCommit(oldAuto);
        }
        System.out.println("Valid records migrated: " + valid.size());
        System.out.println("Rejected records: " + failures.size());
        for (String failure : failures) {
            System.out.println(failure);
        }
    }

    private static void showMetadata(Connection con) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        System.out.println("Database: " + meta.getDatabaseProductName());
        System.out.println("Database version: " + meta.getDatabaseProductVersion());
        System.out.println("Driver: " + meta.getDriverName());
        System.out.println("Driver version: " + meta.getDriverVersion());
        try (ResultSet tables = meta.getTables(con.getCatalog(), null, "%", new String[]{"TABLE"})) {
            System.out.println("Tables:");
            while (tables.next()) {
                System.out.println(" - " + tables.getString("TABLE_NAME"));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            showMetadata(con);
            migrate(Path.of("passes.csv"), con);
        }
    }
}
