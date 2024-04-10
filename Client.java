package ProjectPOO;

import java.util.ArrayList;

class Client extends User{
    Profile profile;
    ArrayList<Book> borrowedBooks;

    public Client(Profile profile, String userName, String password) {
        super(profile, userName, password);
        this.profile = profile;
        this.borrowedBooks = new ArrayList<>();
    }
    public Client(Profile profile) {
        this.profile = profile;
        this.borrowedBooks = new ArrayList<>();
    }
    public Client(Profile profile, Book borrowedBook){
        this.profile = profile;
        this.borrowedBooks = new ArrayList<>();
        borrowedBooks.add(borrowedBook);
    }
}