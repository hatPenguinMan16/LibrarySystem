import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main2 {
    private ArrayList<Book> bookList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    private FileManager bookAdd = new FileManager();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main2().createLoginView());
    }

    private void createLoginView() {
        userList = bookAdd.getUsers();

        JFrame frame = new JFrame("Login Window");
        frame.setSize(600, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Log In");

        panel.add(Box.createVerticalGlue());
        panel.add(centerComponent(new JLabel("Username:")));
        panel.add(centerComponent(usernameField));
        panel.add(centerComponent(new JLabel("Password:")));
        panel.add(centerComponent(passwordField));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(centerComponent(loginButton));
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            String inputUser = usernameField.getText();
            String inputPass = new String(passwordField.getPassword());

            User loggedInUser = Authenticator.login(inputUser, inputPass, userList);

            if (loggedInUser != null) {
                frame.getContentPane().removeAll();
                createSearchView(frame, loggedInUser);
                frame.revalidate();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong name or password!");
            }
        });
    }

    private void createSearchView(JFrame frame, User currentUser) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        bookList = bookAdd.getBooks();

        JPanel topPanel = new JPanel();

        JLabel userLabel = new JLabel("Logged in as: " + currentUser.getName());
        userLabel.setForeground(Color.BLUE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(userLabel);

        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        topPanel.add(searchField);
        topPanel.add(searchButton);

        JPanel boxesContainer = new JPanel();
        boxesContainer.setLayout(new BoxLayout(boxesContainer, BoxLayout.Y_AXIS));

        searchButton.addActionListener(e -> {
            String searchMsg = searchField.getText();

            DumbSearch searchEngine = new DumbSearch(bookList, searchMsg);
            ArrayList<Book> results = searchEngine.search();

            boxesContainer.removeAll();

            for (Book b : results) {
                int originalIndex = bookList.indexOf(b);

                String cleanTitle = b.getTitle().replace("Title:", "").trim();

                boxesContainer.add(createSampleBox(cleanTitle, originalIndex, currentUser));
                boxesContainer.add(Box.createRigidArea(new Dimension(0, 15)));
            }
            boxesContainer.revalidate();
            boxesContainer.repaint();
        });

        for (int bookIdx = 0; bookIdx < bookList.size(); bookIdx++){
            String rawTitle = bookList.get(bookIdx).getTitle();
            String cleanTitle = rawTitle.replace("Title:", "").trim();

            boxesContainer.add(createSampleBox(cleanTitle, bookIdx, currentUser));

            boxesContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JScrollPane scrollPane = new JScrollPane(boxesContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
    }

    private Component centerComponent(Component comp) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.add(comp);
        return wrapper;
    }

    private JPanel createSampleBox(String title, int idx, User currentUser) {
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBorder(BorderFactory.createTitledBorder(title));
        boxPanel.add(new JLabel("Author: " + bookList.get(idx).getAuthor()));
        boxPanel.add(new JLabel("Pages: " + bookList.get(idx).getPages()));
        boxPanel.add(new JLabel("Language: " + bookList.get(idx).getLanguage()));

        JButton actionButton = new JButton();
        Book currentBook = bookList.get(idx);
        String isBorrowed = currentBook.getBorrowed().trim();

        if (currentUser.getBorrowedBooks().contains(currentBook.getTitle())) {
            actionButton.setText("Return");
            actionButton.addActionListener(e -> {
                currentUser.getBorrowedBooks().remove(currentBook.getTitle());
                currentBook.setBorrowed("false");

                bookAdd.writeUsers(userList);
                bookAdd.writeBooks(bookList);

                JOptionPane.showMessageDialog(boxPanel, "Returned!");
                actionButton.setEnabled(false);
                actionButton.setText("Returned");
            });
        }

        else if (isBorrowed.equalsIgnoreCase("true") || isBorrowed.equalsIgnoreCase("Yes")) {
            actionButton.setText("Unavailable");
            actionButton.setForeground(Color.RED);
            actionButton.setEnabled(false);
        }

        else {
            actionButton.setText("Borrow");
            actionButton.addActionListener(e -> {
                currentUser.requestBorrow(currentBook);
                currentBook.setBorrowed("true");

                bookAdd.writeUsers(userList);
                bookAdd.writeBooks(bookList);

                JOptionPane.showMessageDialog(boxPanel, "Borrowed!");
                actionButton.setEnabled(false);
                actionButton.setText("Borrowed");
            });
        }

        boxPanel.add(Box.createVerticalStrut(10));
        boxPanel.add(actionButton);

        return boxPanel;
    }
}