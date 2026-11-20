import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PassLoader {
    /** Loads passes in batches of 1,000 as one atomic transaction. */
    public static int loadAll(Connection con, List<String[]> passes)
            throws SQLException {
        String sql = "INSERT INTO bus_pass(pass_id, reg_no, name, route_no, amount) "
                   + "VALUES (?, ?, ?, ?, ?)";
        boolean oldAutoCommit = con.getAutoCommit();
        con.setAutoCommit(false);
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int count = 0;
            for (String[] pass : passes) {
                if (pass.length != 5) {
                    throw new IllegalArgumentException("Each pass must contain five fields.");
                }
                ps.setString(1, pass[0]);
                ps.setString(2, pass[1]);
                ps.setString(3, pass[2]);
                ps.setInt(4, Integer.parseInt(pass[3]));
                ps.setDouble(5, Double.parseDouble(pass[4]));
                ps.addBatch();
                count++;
                if (count % 1000 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            con.commit();
            return count;
        } catch (SQLException | RuntimeException error) {
            con.rollback();
            throw error;
        } finally {
            con.setAutoCommit(oldAutoCommit);
        }
    }
}
