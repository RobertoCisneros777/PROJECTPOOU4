package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class ControllerBook {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList <Book> borrowedBooks = new ArrayList<>();
    public static void addBook(Book book){
        books.add(book);
    }
    public static void createBook() throws ParseException {
        Book book = new Book();
        System.out.print(">INGRESE EL ISBN: ");
        Scanner isb = new Scanner(System.in);
        String isbn = isb.nextLine();
        book.setIsbn(isbn);

        System.out.print(">INGRESE EL TITULO: ");
        Scanner titl = new Scanner(System.in);
        String title = titl.nextLine();
        book.setTitle(title);

        for (int i = 0; i < ControllerAuthor.authors.size(); i++) {
            System.out.println((i + 1) + ") " + ControllerAuthor.authors.get(i).profile.name);
        }
        System.out.print(">SELECCIONE EL AUTOR: ");
        Scanner na = new Scanner(System.in);
        int authorIndex = na.nextInt();
        if (authorIndex >= 1 && authorIndex <= ControllerAuthor.authors.size()) {
            Author selectedAuthor = ControllerAuthor.authors.get(authorIndex - 1);
            book.setAuthor(selectedAuthor);
            selectedAuthor.books.add(book);
            System.out.println(">AUTOR ENCONTRADO Y LIBRO ASIGNADO CORRECTAMENTE");
        } else {
            System.out.println(">SELECCIÓN DE AUTOR NO VÁLIDA. POR FAVOR, INTENTE DE NUEVO");
        }
        System.out.print(">INGRESE FECHA DE PUBLICACION CON EL FORMATO SIGUIENTE DD/MM/AAAA: ");
        Scanner pd = new Scanner(System.in);
        String publishdat = pd.nextLine();
        Date publishDate = new SimpleDateFormat("dd/MM/yyyy").parse(publishdat);
        book.setPublishDate(publishDate);
        book.setAvailable(true);
        ControllerBook.addBook(book);
        System.out.println(">LIBRO CREADO CON ÉXITO");
    }
    public static void deleteBook() {
        ControllerBook.readerAllBooks();
        System.out.print(">QUE LIBRO DESEA ELIMINAR: ");
        int deleteIndex;
        Scanner del = new Scanner(System.in);
        deleteIndex = del.nextInt();
        if (deleteIndex >= 1 && deleteIndex <= books.size()) {
            Book deleteBook = books.get(deleteIndex);
            if (!deleteBook.getAvailable()) {
                System.out.println(">NO SE PUEDE ELIMINAR UN LIBRO PRESTADO");
            } else {
                ControllerAuthor.deleteBookAuthor(deleteBook, deleteBook.author);
                books.remove(deleteIndex);
                System.out.println(">LIBRO ELIMINADO CON EXITO");
                ControllerBook.readerAllBooks();
            }
        } else {
            System.out.println(">INDICE DE LIBRO NO VALIDO");
        }
    }
    public static void updateBook() throws ParseException {
        ControllerBook.readerAllBooks();
        int opc;
        Scanner leer = new Scanner(System.in);
        System.out.print(">¿QUE LIBRO DESEA EDITAR?: ");
        int editIndex;
        Scanner edit = new Scanner(System.in);
        editIndex = edit.nextInt();
        System.out.println(">¿QUE DESEAS EDITAR DEL LIBRO?");
        System.out.println("\n>1) ISBN\n>2) TITULO\n>3) FECHA DE PUBLICACION");
        System.out.print(">SELECCIONA OPCION: ");
        opc = leer.nextInt();
        switch (opc) {
            case 1 -> {
                System.out.print(">INGRESE EL NUEVO ISBN: ");
                String newIsbn;
                Scanner ni = new Scanner(System.in);
                newIsbn = ni.nextLine();
                ControllerBook.updateBookIsbn(editIndex, newIsbn);
            }
            case 2 -> {
                System.out.print(">INGRESE EL NUEVO TITULO: ");
                String newTitle;
                Scanner nt = new Scanner(System.in);
                newTitle = nt.nextLine();
                ControllerBook.updateBookTitle(editIndex, newTitle);
            }
            case 3 -> {
                System.out.print(">INGRESE LA NUEVA FECHA DE PUBLICACION CON EL FORMATO SIGUIENTE DD/MM/AAAA: ");
                String newPublishdate;
                Scanner np = new Scanner(System.in);
                newPublishdate = np.nextLine();
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                ControllerBook.updateBookPublishDate(editIndex, format.parse(newPublishdate));
            }
        }
    }
    public static void updateBookIsbn(int editIndex, String isbn) {
        books.get(editIndex-1).isbn=isbn;
    }
    public static void updateBookTitle(int editIndex, String title){
        books.get(editIndex-1).title=title;
    }
    public static void updateBookPublishDate(int editIndex, Date publishDate){
        books.get(editIndex-1).publishDate=publishDate;
    }
    public static void readerAllBooks(){
        System.out.printf("| %-50s | %-20s | %-10s | %-20s |%n", "TITULO", "AUTOR", "ISBN", "FECHA DE PUBLICACION");
        for (Book book : books) {
            System.out.printf("| %-50s | %-20s | %-10s | %-20s |%n",
                    book.title,
                    book.author.profile.name + " " + book.author.profile.lastName,
                    book.isbn,
                    new SimpleDateFormat("dd/MM/yyyy").format(book.publishDate));
        }
    }
    public static void readerBorrowedBooks() {
        System.out.printf("| %-50s | %-20s | %-10s | %-20s |%n", "TITULO", "AUTOR", "ISBN", "FECHA DE PUBLICACION");
        for (Book book : books) {
            if (!book.isAvailable) {
                borrowedBooks.add(book);
                System.out.printf("| %-50s | %-20s | %-10s | %-20s |%n",
                        book.title,
                        book.author.profile.name + " " + book.author.profile.lastName,
                        book.isbn,
                        new SimpleDateFormat("dd/MM/yyyy").format(book.publishDate));
            }
        }
    }
    public static void readerAvailableBooks() {
        System.out.printf("| %-50s | %-20s | %-10s | %-20s |%n", "TITULO", "AUTOR", "ISBN", "FECHA DE PUBLICACION");
        for (Book book : books) {
            if (book.isAvailable) {
                System.out.printf("| %-50s | %-20s | %-10s | %-20s |%n",
                        book.title,
                        book.author.profile.name + " " + book.author.profile.lastName,
                        book.isbn,
                        new SimpleDateFormat("dd/MM/yyyy").format(book.publishDate));
            }
        }
    }
}