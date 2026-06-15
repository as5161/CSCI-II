package classes;
public class Book {
    public static final int MAX_PAGES = 4000;
    private String title;
    private int pages;
    private boolean hardcover;
    private int currentPage;

    public Book(String title, int pages, boolean hardcover){
        this.title = title;
        this.pages = pages;
        if (pages>MAX_PAGES){
            this.pages = MAX_PAGES;
        }
        this.hardcover=hardcover;
        currentPage = 0;
    }

    public Book(String title, int pages, boolean hardcover, int currentPage){
        this.title = title;
        this.pages = pages;
        if (pages>MAX_PAGES){
            this.pages = MAX_PAGES;
        }
        this.hardcover=hardcover;
        this.currentPage = pages;
    }
}

