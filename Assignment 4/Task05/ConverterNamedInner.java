import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConverterNamedInner extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target =
        new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");

    private class ConvertHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            convert();
        }
    }

    private void convert() {
        try {
            double value = Double.parseDouble(amount.getText());
            double rate = target.getSelectedItem().equals("USD") ? 0.012 : 0.011;
            String answer = String.format("%.2f", value * rate);
            result.setText(answer);
            JOptionPane.showMessageDialog(this, answer);
        } catch (NumberFormatException e) {
            result.setText("Invalid amount");
        }
    }

    public ConverterNamedInner() {
        super("Converter - Named Inner");
        setLayout(new FlowLayout());
        JButton button = new JButton("Convert");
        add(amount);
        add(target);
        add(button);
        add(result);
        ConvertHandler handler = new ConvertHandler();
        button.addActionListener(handler);
        amount.addActionListener(handler);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new ConverterNamedInner().setVisible(true));
    }
}