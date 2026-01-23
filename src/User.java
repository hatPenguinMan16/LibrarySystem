public class User {
    protected String name;
    private String password;
    private String[] borrowed;
    private String[] books;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        //this.borrowed = null;
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

    public void setBooks(String[] books) {
        this.books = books;
    }

    public String[] getBooks() {
        return books;
    }

    public String toString() {
        return String.join(" | ", name, password);
    }

    public void addUserToList(User user) {

    }

    public void requestBorrow(Book book){

        // Do Stuff
    }
}

