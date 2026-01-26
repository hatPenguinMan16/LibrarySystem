public class Book {
    private String title;
    private String author;
    private String pages;
    private String language;
    private String year;
    private String isbn;
    private String borrowed;

    public Book(String title, String author, String pages, String language, String year, String isbn, String borrowed) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.language = language;
        this.year = year;
        this.isbn = isbn;
        this.borrowed = borrowed;
    }

    public String getBorrowed() {return borrowed;}

    public String toString() {
        return String.join(" | ", title, author, pages, language, year, isbn, borrowed);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPages() {
        return pages;
    }

    public String getLanguage() {
        return language;
    }

    public String getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }
}
