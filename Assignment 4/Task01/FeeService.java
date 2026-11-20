import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class FeeService {
    private final Connection connection;

    FeeService(Connection connection) {
        this.connection = connection;
    }

    public void pay(String regNo, String amountText) throws Exception {
        if (regNo == null || regNo.isBlank()) {
            throw new IllegalArgumentException("Registration number is required.");
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid amount.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        String sql = "INSERT INTO fees (reg_no, amount) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, regNo);
            statement.setDouble(2, amount);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Payment could not be saved.", e);
        }
    }
}