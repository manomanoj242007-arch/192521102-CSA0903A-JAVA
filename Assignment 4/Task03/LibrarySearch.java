import java.awt.*;
import javax.swing.*;

public class LibrarySearch extends JFrame {
    private final JTextField searchText = new JTextField(22);
    private final JComboBox<String> category =
        new JComboBox<>(new String[]{"All", "Books", "Journals", "Thesis"});
    private final DefaultListModel<String> results = new DefaultListModel<>();

    public LibrarySearch() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8, 8));

        JLabel title = new JLabel("Campus Library Search", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(20f));
        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchText);
        searchPanel.add(category);
        searchPanel.add(new JButton("Search"));

        JPanel top = new JPanel(new BorderLayout());
        top.add(title, BorderLayout.NORTH);
        top.add(searchPanel, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);

        JList<String> resultList = new JList<>(results);
        results.addElement("Java Programming");
        results.addElement("Data Structures");
        results.addElement("Computer Networks");
        add(new JScrollPane(resultList), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel actions = new JPanel(new FlowLayout());
        actions.add(new JButton("Prev"));
        actions.add(new JButton("Next"));
        actions.add(new JButton("Reserve"));
        bottom.add(actions, BorderLayout.CENTER);
        bottom.add(new JLabel("Ready"), BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 420);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearch().setVisible(true));
    }
}