public class Book {

    private int id;
    private String title;
    private String author;
    private int pages;
    private String genre;
    private String publisher;

    public Book() {                       
    }

    public Book(int id, String title, String author, int pages, String genre, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages=pages;
        this.genre=genre;
        this.publisher=publisher;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }



    @Override
    public String toString() {
        return getTitle()+" by "+getAuthor()+" published by "+getPublisher()+" which has "+getPages()+" pages "+" in the genre of " + getGenre();
    }
}



