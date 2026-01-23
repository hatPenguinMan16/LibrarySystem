import java.util.ArrayList;

public class Main {
    // These belong to the OBJECT
    private ArrayList<Book> bookList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    private FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        Main app = new Main();

        System.out.println(app.fileManager.getUsers());
        System.out.println(app.fileManager.getBooks());

        //app.bookList = app.fileManager.getBooks();
        //System.out.println(app.fileManager.getBooks());

        // User newUser = new User("NewGuy", "password123");
        //System.out.println(app.bookList);
        //System.out.println(app.bookList);
        // app.fileManager.writeUsers(app.bookList);
    }
}