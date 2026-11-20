import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConverterFrameListener extends JFrame implements ActionListener {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target =
        new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");

    public ConverterFrameListener() {
        super("Converter - Frame Listener");
        setLayout(new FlowLayout());
        JButton convert = new JButton("Convert");
        add(amount);
        add(target);
        add(convert);
        add(result);
        convert.addActionListener(this);
        amount.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new ConverterFrameListener().setVisible(true));
    }
}