package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Book {
    String isbn;
    String title;
    Author author;
    Date publishDate;
    boolean isAvailable;
    public Book() {
    }
    public Book(String isbn, String title, Author author, String publishDat, boolean isAvailable) throws ParseException {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.publishDate = format.parse(publishDat);
        this.isAvailable = isAvailable;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setIsbn(String isbn){
        this.isbn=isbn;
    }
    public void setPublishDate(Date publishDate){
        this.publishDate=publishDate;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public boolean getAvailable() {
        return isAvailable;
    }
}