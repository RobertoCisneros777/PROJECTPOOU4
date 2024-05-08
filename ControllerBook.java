package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class ControllerBook implements Controller {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList <Book> borrowedBooks = new ArrayList<>();
    public static void createBook() throws ParseException {
        Book book = new Book();
        ConsoleReader reader = new ConsoleReader();
        StringValidator isbnValidator = value -> value.matches("\\d+");
        String isbn = reader.readString(">INGRESE EL ISBN", isbnValidator);
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
        ControllerBook.books.add(book);
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
                ConsoleReader reader = new ConsoleReader();
                StringValidator isbnValidator = value -> value.matches("\\d+");
                String newIsbn = reader.readString(">INGRESE EL NUEVO ISBN", isbnValidator);
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
        books.get(editIndex - 1).isbn = isbn;
    }
    public static void updateBookTitle(int editIndex, String title){
        books.get(editIndex - 1).title = title;
    }
    public static void updateBookPublishDate(int editIndex, Date publishDate){
        books.get(editIndex - 1).publishDate = publishDate;
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

    @Override
    public void execute(User user) throws ParseException {
        ArrayList<String> optionsBook = new ArrayList<>();
        int opcBook;
        do {
            System.out.println();
            System.out.println("-MENU LIBROS- ");
            System.out.println();
            System.out.println(">¿QUE DESEA HACER?");
            optionsBook.clear();
            if (((Administrator) user).isIsSuperAdmin() || ((Administrator) user).permissions.contains(Permissions.READ) && ((Administrator) user).permissions.contains(Permissions.DELETE) && ((Administrator) user).permissions.contains(Permissions.WRITE)) {
                optionsBook.add("AÑADIR LIBRO");
                optionsBook.add("EDITAR LIBRO");
                optionsBook.add("MOSTRAR LIBROS");
                optionsBook.add("ELIMINAR LIBRO");
                optionsBook.add("SALIR DEL MENU LIBROS");
            } else {
                if (((Administrator) user).permissions.contains(Permissions.WRITE)) {
                    optionsBook.add("AÑADIR LIBRO");
                    optionsBook.add("EDITAR LIBRO");
                }
                if (((Administrator) user).permissions.contains(Permissions.READ)) {
                    optionsBook.add("MOSTRAR LIBROS");
                }
                if (((Administrator) user).permissions.contains(Permissions.DELETE)) {
                    optionsBook.add("ELIMINAR LIBRO");
                }
                optionsBook.add("SALIR DEL MENU LIBROS");
            }
            Menu.printOptions(optionsBook);
            ConsoleReader reader = new ConsoleReader();
            IntValidator validatorOption = value -> value < optionsBook.size() + 1;
            opcBook = reader.readInteger(">SELECCIONA OPCION", validatorOption);
            if (opcBook >= 1 && opcBook <= optionsBook.size()) {
                String selectedOption = optionsBook.get(opcBook - 1);
                switch (selectedOption) {
                    case "AÑADIR LIBRO" -> {
                        System.out.println();
                        createBook();
                    }
                    case "EDITAR LIBRO" -> {
                        System.out.println();
                        updateBook();
                    }
                    case "MOSTRAR LIBROS" -> {
                        System.out.println();
                        System.out.println("\n>1) MOSTRAR TODOS LOS LIBROS \n>2) MOSTRAR LIBROS PRESTADOS \n>3) MOSTRAR LIBROS DISPONIBLES PARA PRESTAMO");
                        IntValidator validatorPrintBooks = value -> value < 4;
                        int readerOption = reader.readInteger(">SELECCIONE OPCION", validatorPrintBooks);
                        if (readerOption == 1) {
                            readerAllBooks();
                        }else if (readerOption == 2){
                            readerBorrowedBooks();
                        }else if (readerOption == 3){
                            readerAvailableBooks();
                        }
                    }
                    case "ELIMINAR LIBRO" -> {
                        System.out.println();
                        deleteBook();
                    }
                }
            } else {
                System.out.println("OPCION NO VALIDA");
            }
        } while (!optionsBook.get(opcBook - 1).equals("SALIR DEL MENU LIBROS"));
    }
}