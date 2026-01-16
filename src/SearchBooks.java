import java.util.ArrayList;

public class SearchBooks {
    private ArrayList<Book> bookList;
    private String searchMsg;

    public SearchBooks(ArrayList<Book> bookList, String searchMsg) {
        this.bookList = bookList;
        this.searchMsg = "The Chronos Directive";
    }

    void goThroughBooks(){
        String[] bookStuff = new String[6];

        System.out.println(this.bookList.get(0).getTitle().replace("Title: ", ""));
        System.out.println(this.searchMsg);


        String title;
        for (int idx = 0; idx < this.bookList.size(); idx++){
            //title = this.bookList.get(idx).getTitle().substring(this.bookList.get(idx).getTitle().length() - 2, this.bookList.get(idx).getTitle().length() - 1).replace(" ", "");
            //System.out.println(title);

            String sentence = this.bookList.get(0).getTitle();
            StringBuilder sb = new StringBuilder(sentence);
            sb.deleteCharAt(sb.length() - 1);
            sentence = sb.toString();
            sentence = sentence.replace("Title: ", "");


            //        str = str.substring(0, str.length() - 1);
            if (sentence.equals(searchMsg)) System.out.println("fuk yeea");
            if (this.bookList.get(idx).getTitle().replace("Title: ", "").equals(searchMsg)) System.out.println("working---------------------");
            /*if (this.bookList.get(idx).getAuthor().replace("Author: ", "") == searchMsg) System.out.println("working");
            if (this.bookList.get(idx).getPages().replace("Pages: ", "") == searchMsg) System.out.println("working");
            if (this.bookList.get(idx).getLanguage().replace("Language: ", "") == searchMsg) System.out.println("working");
            if (this.bookList.get(idx).getYear().replace("Year: ", "") == searchMsg) System.out.println("working");
            if (this.bookList.get(idx).getIsbn().replace("ISBN: ", "") == searchMsg) System.out.println("working");
        */}
    }
}
