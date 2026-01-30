import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main67 {
    private ArrayList<Book> bookList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    private FileManager bookAdd = new FileManager();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main67().createLoginView());
    }

    private void createLoginView() {
        // Load existing users from file
        userList = bookAdd.getUsers();

        JFrame frame = new JFrame("Library System - Login");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Log In");
        JButton registerButton = new JButton("Register");

        panel.add(Box.createVerticalGlue());
        panel.add(centerComponent(new JLabel("Username:")));
        panel.add(centerComponent(usernameField));
        panel.add(centerComponent(new JLabel("Password:")));
        panel.add(centerComponent(passwordField));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(centerComponent(loginButton));
        panel.add(centerComponent(registerButton)); // Added this!
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            String inputUser = usernameField.getText();
            String inputPass = new String(passwordField.getPassword());
            User loggedInUser = Authenticator.login(inputUser, inputPass, userList);

            if (loggedInUser != null) {
                switchView(frame, loggedInUser);
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong name or password!");
            }
        });

        registerButton.addActionListener(e -> {
            String inputUser = usernameField.getText();
            String inputPass = new String(passwordField.getPassword());

            User newUser = new User(inputUser, inputPass);
            userList.add(newUser);
            bookAdd.writeUsers(userList);

            if(newUser != null){
                frame.getContentPane().removeAll();
                createLoginView();
                frame.revalidate();
                frame.repaint();
            }
        });


    }

    // Helper to swap views easily
    private void switchView(JFrame frame, User user) {
        frame.getContentPane().removeAll();
        createSearchView(frame, user);
        frame.revalidate();
        frame.repaint();
    }

    private void createSearchView(JFrame frame, User currentUser) {
        JPanel panel = new JPanel(new BorderLayout());
        bookList = bookAdd.getBooks();

        JPanel topPanel = new JPanel();
        JLabel userLabel = new JLabel("User: " + currentUser.getName());
        userLabel.setForeground(Color.BLUE);

        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JButton myLoansButton = new JButton("My Loans");

        topPanel.add(userLabel);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(myLoansButton);

        JPanel boxesContainer = new JPanel();
        boxesContainer.setLayout(new BoxLayout(boxesContainer, BoxLayout.Y_AXIS));

        displayBooks(boxesContainer, bookList, currentUser, frame);

        searchButton.addActionListener(e -> {
            String searchMsg = searchField.getText();
            DumbSearch searchEngine = new DumbSearch(bookList, searchMsg);
            searchEngine.search();
            displayBooks(boxesContainer, searchEngine.getRetBooks(), currentUser, frame);
        });

        myLoansButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            createLoanView(frame, currentUser);
            frame.revalidate();
            frame.repaint();
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(boxesContainer), BorderLayout.CENTER);

        frame.add(panel);
        frame.revalidate();
    }

    private void displayBooks(JPanel container, ArrayList<Book> booksToDisplay, User currentUser, JFrame frame) {
        container.removeAll();
        for (Book b : booksToDisplay) {
            String cleanTitle = b.getTitle().replace("Title:", "").trim();
            container.add(createSampleBox(cleanTitle, b, currentUser, frame));
            container.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        container.revalidate();
        container.repaint();
    }

    private void createLoanView(JFrame frame, User currentUser) {
        JPanel loanPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        JButton homePageButton = new JButton("Back to Home");
        headerPanel.add(new JLabel("MY LOANS"));
        headerPanel.add(homePageButton);

        JPanel boxesContainer = new JPanel();
        boxesContainer.setLayout(new BoxLayout(boxesContainer, BoxLayout.Y_AXIS));

        ArrayList<Book> borrowedList = new ArrayList<>();
        for (Book b : bookList) {
            if (currentUser.getBorrowedBooks().contains(b.getTitle())) {
                borrowedList.add(b);
            }
        }
        displayBooks(boxesContainer, borrowedList, currentUser, frame);

        loanPanel.add(headerPanel, BorderLayout.NORTH);
        loanPanel.add(new JScrollPane(boxesContainer), BorderLayout.CENTER);

        frame.add(loanPanel);

        homePageButton.addActionListener(e -> switchView(frame, currentUser));
    }

    private JPanel createSampleBox(String title, Book currentBook, User currentUser, JFrame frame) {
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBorder(BorderFactory.createTitledBorder(title));

        boxPanel.add(new JLabel("Author: " + currentBook.getAuthor()));
        boxPanel.add(new JLabel("Pages: " + currentBook.getPages()));

        JButton actionButton = new JButton();
        String isBorrowed = currentBook.getBorrowed().trim();

        if (currentUser.getBorrowedBooks().contains(currentBook.getTitle())) {
            actionButton.setText("Return");
            actionButton.addActionListener(e -> {
                currentUser.getBorrowedBooks().remove(currentBook.getTitle());
                currentBook.setBorrowed("false");
                saveData();
                JOptionPane.showMessageDialog(frame, "Book Returned!");
                switchView(frame, currentUser); // Refresh view
            });
        } else if (isBorrowed.equalsIgnoreCase("true") || isBorrowed.equalsIgnoreCase("Yes")) {
            actionButton.setText("Unavailable");
            actionButton.setEnabled(false);
        } else {
            actionButton.setText("Borrow");
            actionButton.addActionListener(e -> {
                currentUser.requestBorrow(currentBook);
                currentBook.setBorrowed("true");
                saveData();
                JOptionPane.showMessageDialog(frame, "Borrowed!");
                switchView(frame, currentUser); // Refresh view
            });
        }

        boxPanel.add(actionButton);
        return boxPanel;
    }

    private void saveData() {
        bookAdd.writeUsers(userList);
        bookAdd.writeBooks(bookList);
    }

    private Component centerComponent(Component comp) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setOpaque(false);
        wrapper.add(comp);
        return wrapper;
    }
}