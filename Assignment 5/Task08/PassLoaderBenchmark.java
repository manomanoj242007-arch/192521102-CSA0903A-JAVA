import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassLoaderBenchmark {
    private static final String URL = "jdbc:mysql://localhost:3306/assignment5";
    private static final String USER = "root";
    private static final String PASSWORD = "change_me";

    private static List<String[]> generate(int size) {
        List<String[]> rows = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            rows.add(new String[]{"P" + i, "REG" + i, "Student" + i,
                    String.valueOf((i % 20) + 1), "4500.00"});
        }
        return rows;
    }

    private static long batchRun(Connection con, List<String[]> rows) throws Exception {
        try (Statement clear = con.createStatement()) {
            clear.executeUpdate("DELETE FROM bus_pass");
        }
        long start = System.nanoTime();
        PassLoader.loadAll(con, rows);
        return System.nanoTime() - start;
    }

    private static long preparedRun(Connection con, List<String[]> rows) throws SQLException {
        try (Statement clear = con.createStatement()) {
            clear.executeUpdate("DELETE FROM bus_pass");
        }
        String sql = "INSERT INTO bus_pass VALUES (?, ?, ?, ?, ?)";
        long start = System.nanoTime();
        con.setAutoCommit(false);
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (String[] row : rows) {
                ps.setString(1, row[0]);
                ps.setString(2, row[1]);
                ps.setString(3, row[2]);
                ps.setInt(4, Integer.parseInt(row[3]));
                ps.setDouble(5, Double.parseDouble(row[4]));
                ps.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
        return System.nanoTime() - start;
    }

    private static long statementRun(Connection con, List<String[]> rows) throws SQLException {
        try (Statement clear = con.createStatement()) {
            clear.executeUpdate("DELETE FROM bus_pass");
        }
        long start = System.nanoTime();
        con.setAutoCommit(false);
        try (Statement st = con.createStatement()) {
            for (String[] row : rows) {
                String sql = "INSERT INTO bus_pass VALUES ('" + row[0] + "','"
                        + row[1] + "','" + row[2] + "'," + row[3] + "," + row[4] + ")";
                st.executeUpdate(sql);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
        return System.nanoTime() - start;
    }

    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            for (int size : new int[]{10, 1000, 10000, 100000}) {
                List<String[]> rows = generate(size);
                System.out.println("Rows: " + size);
                System.out.println("Statement ns: " + statementRun(con, rows));
                System.out.println("PreparedStatement ns: " + preparedRun(con, rows));
                System.out.println("Prepared batch ns: " + batchRun(con, rows));
            }
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM bus_pass");
                 ) {
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.println(md.getColumnName(i) + " : " + md.getColumnTypeName(i));
                }
            }
        }
    }
}
