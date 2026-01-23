import java.lang.classfile.FieldTransform;

public class Main {
    public static void main(String[] args) {
        //User user = new User("Password", "Name");
        //InitialBookAddDelete books = new InitialBookAddDelete();
        FileManager books = new FileManager();

        DumbSearch k = new DumbSearch(books.getBooks(), "sldfölkjs");
        k.serach();

        System.out.println("hello");


    }
}