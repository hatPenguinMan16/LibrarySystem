import java.util.ArrayList;


public class User {
    protected String name;
    private String password;
    private ArrayList<String> borrowedBooks = new ArrayList<String>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    private int daysLeft;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;

    }

    public String toString() {
        return String.join(" | ", name, password);
    }

    public void addUserToList(User user) {

    }

    public void requestBorrow(Book book) {
        String bookTitle = book.getTitle();
        this.borrowedBooks.add(bookTitle);
        book.borrowed = "Yes";
    }
}

