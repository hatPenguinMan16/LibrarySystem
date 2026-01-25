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
        Scanner sc = openFile("users");

        while (sc.hasNextLine()) {
            String userInformation = sc.nextLine();
            String[] splitData = userInformation.split("\\|");
            if (splitData.length >= 2) {
                String name = splitData[0].replace("Name:", "").trim();
                String pass = splitData[1].replace("Password:", "").trim();
                User user = new User(name, pass);

                if (splitData.length >= 3) {
                    String rawBooks = splitData[2].replace("Borrowed:", "").trim();

                    if (!rawBooks.equals("None") && !rawBooks.isEmpty()) {
                        String[] bookTitles = rawBooks.split(",");
                        for (String title : bookTitles) {
                            user.getBorrowedBooks().add(title.trim());
                        }
                    }
                }
                userList.add(user);
            }
        }
        return userList;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        Scanner sc = openFile("books");
        while (sc.hasNextLine()){
            String bookInformation = sc.nextLine();
            String[] splitData = bookInformation.split("\\|");

            for (int i = 0; i < 6; i++){
                String sentence = splitData[i];
                StringBuilder sb = new StringBuilder(sentence);
                sb.deleteCharAt(sb.length() - 1);
                sentence = sb.toString();
                splitData[i] = sentence;
            }

            bookList.add(new Book(
                    splitData[0].replace("Title: ", "").trim(),
                    splitData[1].replace(" Author: ", "").trim(),
                    splitData[2].replace(" Pages: ", "").trim(),
                    splitData[3].replace(" Language: ", "").trim(),
                    splitData[4].replace(" Year: ", "").trim(),
                    splitData[5].replace(" ISBN: ", "").trim(),
                    splitData[6].replace(" Borrowed: ", "").trim()
            ));
        }
        return bookList;
    }

    public void writeUsers(ArrayList<User> list) {
        String path = "src/users.txt";
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