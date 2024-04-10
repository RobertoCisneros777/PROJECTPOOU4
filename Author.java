package ProjectPOO;

import java.util.ArrayList;

class Author {
    Profile profile;
    ArrayList<Book> books;
    public Author(Profile profile) {
        this.profile = profile;
        this.books = new ArrayList<>();
    }
    public Author(Profile profile, Book book) {
        this.profile = profile;
        this.books = new ArrayList<>();
        books.add(book);
    }
}