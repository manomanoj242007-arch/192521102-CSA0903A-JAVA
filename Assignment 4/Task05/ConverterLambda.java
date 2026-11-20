import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ConverterLambda extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target =
        new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");

    private static double rate(String currency) {
        return currency.equals("USD") ? 0.012 : 0.011;
    }

    public ConverterLambda() {
        super("Currency Converter");
        setLayout(new FlowLayout());
        JButton convertButton = new JButton("Convert");
        add(new JLabel("INR"));
        add(amount);
        add(target);
        add(convertButton);
        add(result);

        ActionListener convertAction = event -> convert();
        convertButton.addActionListener(convertAction);
        amount.addActionListener(convertAction);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent event) {
                dispose();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void convert() {
        try {
            double value = Double.parseDouble(amount.getText().trim());
            if (value < 0) {
                throw new NumberFormatException();
            }
            String currency = (String) target.getSelectedItem();
            double converted = value * rate(currency);
            String message = String.format("%.2f %s", converted, currency);
            result.setText(message);
            JOptionPane.showMessageDialog(this, message);
        } catch (NumberFormatException error) {
            result.setText("Invalid amount");
            JOptionPane.showMessageDialog(this, "Invalid amount",
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new ConverterLambda().setVisible(true));
    }
}