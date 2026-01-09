import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InitialBookAdd {
    private ArrayList<Book> bookList = new ArrayList<Book>();

    public ArrayList<Book> getBookList() {
        getBooks();
        return bookList;
    }

    void getBooks() {
        String path = "src/books.txt";
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //char numLines = 0;
        while (sc.hasNextLine()){
            String bookInformation = sc.nextLine();
            String[] splitData = bookInformation.split("\\|");
            System.out.println(" " + splitData[1]); // Add list idx  to book class

            // Add to book class
            Book book = new Book(splitData[0],splitData[1],splitData[2],splitData[3],splitData[4],splitData[5]);
            bookList.add(book);

        }

    }
}
