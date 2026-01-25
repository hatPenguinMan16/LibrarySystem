    import java.util.ArrayList;

    public class DumbSearch implements SearchMotor{
        private ArrayList<Book> bookList;
        private String searchMsg;
        private ArrayList<Book> retBooks = new ArrayList<Book>(); // Note hardcoded 5 books total when search

        public DumbSearch(ArrayList<Book> bookList, String searchMsg) {
            this.bookList = bookList;
            this.searchMsg = searchMsg;
        }

        @Override
        public ArrayList<Book> search() {

            //TODO: Change print title to display to GUI, Smarter search

            for (Book b : this.bookList) {
                boolean match = false;

                searchMsg = searchMsg.toLowerCase();

                if (b.getTitle().toLowerCase().contains(searchMsg)) match = true;
                else if (b.getAuthor().toLowerCase().contains(searchMsg)) match = true;
                else if (b.getLanguage().toLowerCase().contains(searchMsg)) match = true;
                else if (b.getYear().toLowerCase().contains(searchMsg)) match = true;
                else if (b.getIsbn().toLowerCase().contains(searchMsg)) match = true;
                else if (b.getBorrowed().toLowerCase().contains(searchMsg)) match = true;

                if (match) {
                    retBooks.add(b);
                }
            }
            return retBooks;
        }
    }


