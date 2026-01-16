import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private ArrayList<Book> bookList = new ArrayList<Book>();
    private ArrayList<User> userList = new ArrayList<User>();

    public ArrayList<Book> getBookList() {
        getBooks();
        return bookList;
    }

    public ArrayList<User> getUserList() {
        getUsers();
        return userList;
    }

    Scanner openFile(String name) {
        String path = "src/" + name + ".txt";
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sc;
    }

    void getBooks() {
        Scanner sc = openFile("book");
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
    void getUsers() {
        Scanner sc = openFile("user");
        //char numLines = 0;
        while (sc.hasNextLine()){
            String bookInformation = sc.nextLine();
            String[] splitData = bookInformation.split("\\|");
            System.out.println(" " + splitData[1]); // Add list idx to users class

            // Add to user class
            User user = new User(splitData[0],splitData[1]);
            userList.add(user);

        }
    }

    public void write(String name) {
        String path = "src/" + name + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            if (name.equalsIgnoreCase("book")) {
                for (Book b : bookList) {
                    writer.println(b.toString());
                }
            } else if (name.equalsIgnoreCase("user")) {
                for (User u : userList) {
                    writer.println(u.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
