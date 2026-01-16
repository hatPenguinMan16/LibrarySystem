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
            //System.out.println(" " + splitData[1]); // Add list idx  to book class

            for (int i = 0; i < 5; i++) removeFirstandLast(splitData[i]);

            // Add to book class
            Book book = new Book(splitData[0],splitData[1],splitData[2],splitData[3],splitData[4],splitData[5]);
            bookList.add(book);

        }

    }

    // ctl + c, clt + v --- internet stuff
    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));

    }

    String removeFirstandLast(String str) {

        // Removing the last character
        // of a string using substring() method
        str = str.substring(0, str.length() - 1);

        // Return the modified string
        return str;
    }
}
