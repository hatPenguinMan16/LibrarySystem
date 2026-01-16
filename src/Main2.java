import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main2 {
    private ArrayList<Book> bookList = new ArrayList<Book>();
    private InitialBookAdd bookAdd = new InitialBookAdd();


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main2().createLoginView());
    }


    private void createLoginView() {
        JFrame frame = new JFrame("Login Window");
        frame.setSize(600, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Log In");

        usernameField.setMaximumSize(new Dimension(200, 30));
        passwordField.setMaximumSize(new Dimension(200, 30));

        panel.add(centerComponent(new JLabel("Username:")));
        panel.add(centerComponent(usernameField));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(centerComponent(new JLabel("Password:")));
        panel.add(centerComponent(passwordField));
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        panel.add(centerComponent(loginButton));

        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {

            frame.getContentPane().removeAll();
            createSearchView(frame);
            frame.revalidate();
            frame.repaint();
        });
    }

    private void createSearchView(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        bookList = bookAdd.getBookList();

        // Top search bar
        JPanel topPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        topPanel.add(searchField);
        topPanel.add(searchButton);

        // Center scrollable area for the 3 boxes
        JPanel boxesContainer = new JPanel();
        boxesContainer.setLayout(new BoxLayout(boxesContainer, BoxLayout.Y_AXIS));

        // Add titled boxes
        for (int bookIdx = 0; bookIdx < bookList.size(); bookIdx++){
            boxesContainer.add(createSampleBox(bookList.get(bookIdx).getTitle().replace("Title: ", ""), bookIdx));
            boxesContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        }


        JScrollPane scrollPane = new JScrollPane(boxesContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
    }

    private JPanel createSampleBox(String title, int idx) {
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBorder(BorderFactory.createTitledBorder(title));

        boxPanel.add(new JLabel(bookList.get(idx).getTitle()));
        boxPanel.add(new JLabel(bookList.get(idx).getAuthor()));
        boxPanel.add(new JLabel(bookList.get(idx).getPages()));
        boxPanel.add(new JLabel(bookList.get(idx).getLanguage()));
        boxPanel.add(new JLabel(bookList.get(idx).getYear()));
        boxPanel.add(new JLabel(bookList.get(idx).getIsbn()));

        return boxPanel;
    }

    // Utility to horizontally center components
    private Component centerComponent(Component comp) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.add(comp);
        return wrapper;
    }
}
