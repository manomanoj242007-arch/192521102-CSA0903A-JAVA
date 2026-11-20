import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;

public class FeeWindow extends JFrame {
    private final JTextField regNo = new JTextField(15);
    private final JTextField amount = new JTextField(15);
    private final FeeService service;

    public FeeWindow(Connection connection) {
        super("Student Fee Payment");
        service = new FeeService(connection);

        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.add(new JLabel("Registration No:"));
        form.add(regNo);
        form.add(new JLabel("Amount:"));
        form.add(amount);

        JButton pay = new JButton("Pay");
        JButton clear = new JButton("Clear");
        JButton receipt = new JButton("Receipt");

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(pay);
        buttons.add(clear);
        buttons.add(receipt);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        pay.addActionListener(event -> processPayment());
        clear.addActionListener(event -> clearForm());
        receipt.addActionListener(event ->
            JOptionPane.showMessageDialog(this, "Receipt is available after payment.")
        );

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(420, 180);
        setLocationRelativeTo(null);
    }

    private void processPayment() {
        try {
            service.pay(regNo.getText(), amount.getText());
            JOptionPane.showMessageDialog(this, "Payment successful.");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, error.getMessage(),
                "Payment Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        regNo.setText("");
        amount.setText("");
    }
}