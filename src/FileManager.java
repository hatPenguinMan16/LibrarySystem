import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

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

    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        Scanner sc = openFile("user");
        while (sc.hasNextLine()){
            String userInformation = sc.nextLine();
            String[] splitData = userInformation.split("\\|");
            userList.add(new User(splitData[0], splitData[1]));
        }
        return userList;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        Scanner sc = openFile("books");
        while (sc.hasNextLine()){
            String bookInformation = sc.nextLine();
            // Your existing logic to split and add to bookList
            String[] splitData = bookInformation.split("\\|");

            for (int i = 0; i < 6; i++){
                String sentence = splitData[i];
                StringBuilder sb = new StringBuilder(sentence);
                sb.deleteCharAt(sb.length() - 1);
                sentence = sb.toString();
                splitData[i] = sentence;
            }

            bookList.add(new Book(splitData[0].replace("Title: ", ""), splitData[1].replace(" Author: ", ""), splitData[2].replace(" Pages: ", ""), splitData[3].replace(" Language: ", ""), splitData[4].replace(" Year: ", ""), splitData[5].replace(" ISBN: ", ""), splitData[6].replace(" Borrowed: ", "")));
        }
        return bookList;
    }

    public void writeUsers(ArrayList<User> list) {
        String path = "src/user.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (User u : list) {
                writer.println(u.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBooks(ArrayList<Book> list) {
        String path = "src/book.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (Book b : list) {
                writer.println(b.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}