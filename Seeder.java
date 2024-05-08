package ProjectPOO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

class Seeder {
    public void initialize() throws ParseException {
        UUID uuid = UUID.randomUUID();
        Profile profile1 = new Profile("Joanne", "Rowling", "31/07/1965");
        Book book1 = new Book();
        Author author1 = new Author (profile1, book1);
        book1 = new Book("6073193009", "Harry Potter y la Piedra Filosofal", author1, "26/06/1997", true);
        Book book2 = new Book("6073193017", "Harry Potter y la Camara de los Secretos", author1, "02/07/1998", true);
        ControllerAuthor.authors.add(author1);

        Profile profile2 = new Profile("Suzzane", "Collins", "10/08/1962");
        Book book3 = new Book();
        Author author2 = new Author(profile2, book3);
        book3 = new Book("6073807848", "Los Juegos del Hambre", author2, "14/09/2008", true);
        Book book4 = new Book("6073807856", "Los Juegos del Hambre: En llamas", author2, "01/09/2009", true);
        ControllerAuthor.authors.add(author2);

        Book book5 = new Book();
        Profile profile3 = new Profile("John Ronald", "Reuel", "03/01/1892");
        Author author3 = new Author(profile3, book5);
        book5 = new Book("8445073729", "El Señor de los Anillos: La Comunidad del Anillo", author3, "29/07/1954", true);
        ControllerAuthor.authors.add(author3);

        Profile profile4 = new Profile("Cristiano Ronaldo", "dos Santos", "05/02/1985");
        Client client = new Client(profile4, "Bicho", "Siu7");
        client.setPassword("Siu7");
        ControllerUser.users.add(client);
        Transaction transaction1 = new Transaction(uuid.toString(), "PRESTAMO", client, book1, "09/03/2024");
        client.borrowedBooks.add(book1);
        ControllerTransaction.allTransactions.add(transaction1);
        book1.isAvailable=false;

        Profile profile5 = new Profile("Lionel Messi", "Cuccitini", "24/06/1987");
        Client client2 = new Client(profile5, "LioMessi 10", "Pulga0610");
        ControllerUser.users.add(client2);
        Transaction transaction2 = new Transaction(uuid.toString(), "PRESTAMO", client2, book2, "10/03/2024");
        Transaction transaction3 = new Transaction(uuid.toString(), "PRESTAMO", client2, book3, "15/04/2024");
        client2.borrowedBooks.add(book2);
        client2.borrowedBooks.add(book3);
        ControllerTransaction.allTransactions.add(transaction2);
        ControllerTransaction.allTransactions.add(transaction3);

        Profile profileSuperAdmin = new Profile("Roberto", "Cisneros", "26/10/2005");
        ArrayList<Permissions> permissions = new ArrayList<>();
        permissions.add(Permissions.READ);
        permissions.add(Permissions.WRITE);
        permissions.add(Permissions.DELETE);
        Administrator superAdmin = new Administrator(profileSuperAdmin, "RobertoCisneros777", "Rcg#123.", true, permissions);
        ControllerUser.users.add(superAdmin);

        Profile profileAdmin = new Profile("Jesus", "Alcantar", "10/10/2010");
        ArrayList <Permissions> permissions1 = new ArrayList<>();
        permissions1.add(Permissions.READ);
        Administrator admin1 = new Administrator(profileAdmin, "JesusPro", "Jesuspro1", false, permissions1);
        ControllerUser.users.add(admin1);

        Profile profileAdmin2 = new Profile("Emilio", "Hernandez", "10/12/2005");
        ArrayList <Permissions> permissions2 = new ArrayList<>();
        permissions2.add(Permissions.WRITE);
        permissions2.add(Permissions.DELETE);
        Administrator admin2 = new Administrator(profileAdmin2, "EmilioH8", "EmilioBF18", false, permissions2);
        ControllerUser.users.add(admin2);

        ControllerBook.books.add(book1);
        ControllerBook.books.add(book2);
        ControllerBook.books.add(book3);
        ControllerBook.books.add(book4);
        ControllerBook.books.add(book5);
    }
}