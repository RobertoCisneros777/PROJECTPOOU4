package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Transaction {
    String id;
    String type;
    Client client;
    Book book;
    Date date;
    public Transaction (String id, String type, Client client, Book book, String date) throws ParseException {
        this.id = id;
        this.type = type;
        this.client = client;
        this.book = book;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.date = format.parse(date);
    }
}