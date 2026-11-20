import java.sql.*;

public class AdversarialLogin {
    private static final String URL = "jdbc:mysql://localhost:3306/assignment5";
    private static final String USER = "root";
    private static final String PASSWORD = "change_me";

    static boolean unsafeLogin(Connection con, String id, String pwd) throws SQLException {
        String sql = "SELECT * FROM users WHERE id='" + id + "' AND pwd='" + pwd + "'";
        try (Statement statement = con.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            return result.next();
        }
    }

    static boolean safeLogin(Connection con, String id, String pwd) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE id = ? AND pwd = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, pwd);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String hostileId = "' OR '1'='1' -- ";
            String hostilePassword = "not-a-password";
            System.out.println("Unsafe login accepted: "
                    + unsafeLogin(con, hostileId, hostilePassword));
            System.out.println("Prepared login accepted: "
                    + safeLogin(con, hostileId, hostilePassword));
        }
    }
}
