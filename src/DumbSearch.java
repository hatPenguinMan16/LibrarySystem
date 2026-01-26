import java.util.ArrayList;

public class DumbSearch implements SearchMotor{
    private ArrayList<Book> bookList;
    private String searchMsg;
    private ArrayList<Book> retBooks = new ArrayList<Book>(); // Note hardcoded 5 books total when search

    public DumbSearch(ArrayList<Book> bookList, String searchMsg) {
        this.bookList = bookList;
        this.searchMsg = searchMsg;
    }

    public ArrayList<Book> getRetBooks() {
        return retBooks;
    }

    @Override
    public void search() {
        if (searchMsg.equalsIgnoreCase("")){
            retBooks = bookList;
            return;
        }

            //TODO: Change print title to display to GUI
        for (int idx = 0; idx < this.bookList.size(); idx++) {
            if (this.bookList.get(idx).getTitle().replace("Title: ", "").equalsIgnoreCase(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getAuthor().replace("Author: ", "").equalsIgnoreCase(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getLanguage().replace("Language: ", "").equalsIgnoreCase(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getYear().replace("Year: ", "").equals(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getIsbn().replace("ISBN: ", "").equals(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getBorrowed().replace("Borrowed: ", "").equalsIgnoreCase(searchMsg)) retBooks.add(this.bookList.get(idx));
        }
    }
}

