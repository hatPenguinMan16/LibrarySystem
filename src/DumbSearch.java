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

        for (int idx = 0; idx < this.bookList.size(); idx++) {
            if (this.bookList.get(idx).getTitle().equalsIgnoreCase(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getAuthor().equalsIgnoreCase(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getLanguage().equalsIgnoreCase(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getYear().equals(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getIsbn().equals(searchMsg)) retBooks.add(this.bookList.get(idx));
            if (this.bookList.get(idx).getBorrowed().equalsIgnoreCase(searchMsg)) retBooks.add(this.bookList.get(idx));
        }
    }
}

