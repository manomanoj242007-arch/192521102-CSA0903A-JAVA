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
        add(searchPanel, BorderLayout.PAGE_START);

        JList<String> resultList = new JList<>(results);
        results.addElement("Java Programming");
        results.addElement("Data Structures");
        results.addElement("Computer Networks");
        add(new JScrollPane(resultList), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(new JButton("Prev"));
        bottom.add(new JButton("Next"));
        bottom.add(new JButton("Reserve"));
        add(bottom, BorderLayout.SOUTH);
        add(new JLabel("Ready"), BorderLayout.PAGE_END);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 420);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearch().setVisible(true));
    }
}