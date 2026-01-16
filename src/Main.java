import java.awt.print.Book;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        User user = new User("Password", "Name");
        InitialBookAdd books = new InitialBookAdd();


        SearchBooks k = new SearchBooks(books.getBookList(), "sldfölkjs");
        k.goThroughBooks();

        System.out.println("hello");


    }
}