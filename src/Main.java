import java.lang.classfile.FieldTransform;

public class Main {
    public static void main(String[] args) {
        FileManager books = new FileManager();

        DumbSearch k = new DumbSearch(books.getBooks(), "2022");
        k.serach();

        System.out.println("hello");


    }
}