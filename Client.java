package ProjectPOO;

import java.util.ArrayList;

public class Client extends User{
    Profile profile;
    ArrayList<Book> borrowedBooks;

    public Client(Profile profile, String userName, String password) {
        super(profile, userName, password);
        this.profile = profile;
        this.borrowedBooks = new ArrayList<>();
    }
}