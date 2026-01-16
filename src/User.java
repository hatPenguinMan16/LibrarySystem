public class User {
    protected String name;
    private String password;
    private String[] books;

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
        return String.join("|", name, password);
    }

    public void addUserToList(User user) {

    }

    public User(String name, String password) {
        setName(name);
        setPassword(password);
    }

    public static User[] HardcodedUsers(){
        User User1 = new User("Eskil", "123");
        User User2 = new User("Gustav", "321");
        User User3 = new User("hej", "");
        User User4 = new User("User", "Password");
        return new User[] { User1, User2, User3, User4 };
    }

    public void requestBorrow(){
        // Do Stuff
    }
}

