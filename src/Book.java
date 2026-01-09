public class Book {
    private String title;
    private String author;
    private String pages;
    private String language;
    private String year;
    private String isbn;

    public Book(String title, String author, String pages, String language, String year, String isbn) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.language = language;
        this.year = year;
        this.isbn = isbn;
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
