import java.util.ArrayList;

public class DumbSearch implements SearchMotor{
    private ArrayList<Book> bookList;
    private String searchMsg;

    public DumbSearch(ArrayList<Book> bookList, String searchMsg) {
        this.bookList = bookList;
        this.searchMsg = searchMsg;
    }

    @Override
    public void serach() {
        //TODO: Change print title to display to GUI
        for (int idx = 0; idx < this.bookList.size(); idx++) {
            if (this.bookList.get(idx).getTitle().replace("Title: ", "").equals(searchMsg)) System.out.println(this.bookList.get(idx).getTitle());
            if (this.bookList.get(idx).getAuthor().replace("Author: ", "").equals(searchMsg)) System.out.println(this.bookList.get(idx).getTitle());
            if (this.bookList.get(idx).getLanguage().replace("Language: ", "").equals(searchMsg)) System.out.println(this.bookList.get(idx).getTitle());
            if (this.bookList.get(idx).getYear().replace("Year: ", "").equals(searchMsg)) System.out.println(this.bookList.get(idx).getTitle());
            if (this.bookList.get(idx).getIsbn().replace("ISBN: ", "").equals(searchMsg)) System.out.println(this.bookList.get(idx).getTitle());
            if (this.bookList.get(idx).getBorrowed().replace("Borrowed: ", "").equals(searchMsg)) System.out.println(this.bookList.get(idx).getTitle());

        }
    }
}

