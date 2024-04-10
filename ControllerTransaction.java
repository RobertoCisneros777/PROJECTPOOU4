package ProjectPOO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

    class ControllerTransaction {
    static ArrayList <Transaction> allTransactions= new ArrayList <> ();
    static UUID uuid = UUID.randomUUID();
    public static void addTransaction(Transaction transaction){
        allTransactions.add(transaction);
    }
    public static void reportMovementUserClient(User user) throws ParseException {
        int opc;
        Scanner opctransac = new Scanner(System.in);
        System.out.println(">FILTRAR POR:");
        System.out.println("\n>1) MIS MOVIMIENTOS \n>2) RANGO DE FECHA");
        System.out.print(">SELECCIONE OPCION: ");
        opc = opctransac.nextInt();
        switch (opc){
            case 1 -> {
                boolean flag = false;
                for (Transaction allTransaction : allTransactions) {
                    if (allTransaction.client.profile.equals(user.getProfile())) {
                        if (allTransaction.type.equals("PRESTAMO")) {
                            System.out.println("EL LIBRO " + allTransaction.book.title + " FUE PRESTADO AL CLIENTE " + allTransaction.client.profile.name + " EN LA FECHA " + new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                            flag = true;
                        } else if (allTransaction.type.equals("DEVOLUCION")) {
                            System.out.println("EL LIBRO " + allTransaction.book.title + " FUE DEVUELTO POR EL CLIENTE " + allTransaction.client.profile.name + " EN LA FECHA " + new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                            flag = true;
                        }
                    }
                }
                if (!flag){
                    System.out.println(">NO HAZ HECHO NINGUN MOVIMIENTO AUN");
                }
            }
            case 2 -> {
                Scanner date = new Scanner(System.in);
                boolean banner = false;
                boolean clientContain = false;
                for (Transaction transaction : allTransactions) {
                    if (transaction.client.profile.equals(user.getProfile())) {
                        clientContain = true;
                        break;
                    }
                }
                if (!clientContain) {
                    System.out.println(">NO HAZ HECHO NINGUN MOVIMIENTO AUN");
                } else {
                    System.out.print(">INGRESE INICIO DE FECHA CON EL FORMATO DD/MM/AAAA: ");
                    String startDate = date.nextLine();
                    System.out.print(">INGRESE FINAL DE FECHA CON EL MISMO FORMATO: ");
                    String endDate = date.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date startDate1 = dateFormat.parse(startDate);
                    Date endDate1 = dateFormat.parse(endDate);
                    if (allTransactions.isEmpty()) {
                        System.out.println(">NO HAY TRANSACCIONES DISPONIBLES");
                    } else {
                        for (Transaction transaction : allTransactions) {
                            Date transactionDate = transaction.date;
                            if (transactionDate.compareTo(startDate1) >= 0 && transactionDate.compareTo(endDate1) <= 0) {
                                if (transaction.type.equals("PRESTAMO") && transaction.client.profile.equals(user.getProfile())) {
                                    System.out.println("EL LIBRO " + transaction.book.title + " FUE PRESTADO AL CLIENTE " + transaction.client.profile.name + " EN LA FECHA " + dateFormat.format(transactionDate));
                                    banner = true;
                                } else if (transaction.type.equals("DEVOLUCION") && transaction.client.profile.equals(user.getProfile())) {
                                    System.out.println("EL LIBRO " + transaction.book.title + " FUE DEVUELTO POR EL CLIENTE " + transaction.client.profile.name + " EN LA FECHA " + dateFormat.format(transactionDate));
                                    banner = true;
                                }
                            }
                        }
                        if (!banner) {
                            System.out.println(">NO HAY TRANSACCIONES EN ESTE PERIODO DE FECHAS");
                        }
                    }
                }
            }
        }
    }
    public static void reportMovement() throws ParseException {
        int opc;
        Scanner opctransac = new Scanner(System.in);
        System.out.println(">FILTRAR POR:");
        System.out.println("\n>1) MOVIMIENTOS DE UN LIBRO \n>2) MOVIMIENTOS DE UN CLIENTE \n>3) RANGO DE FECHA");
        System.out.print(">SELECCIONE OPCION: ");
        opc = opctransac.nextInt();
        switch (opc){
            case 1 -> {
                ControllerBook.readerAllBooks();
                System.out.print(">¿DE QUE LIBRO DESEA VER LOS MOVIMIENTOS REALIZADOS?: ");
                int movementBook = opctransac.nextInt();
                reportMovementBook(movementBook);
            }
            case 2 -> {
                ControllerClient.readerClients();
                System.out.print(">¿DE QUE CLIENTE DESEA VER LOS MOVIMIENTOS REALIZADOS?: ");
                int movementClient = opctransac.nextInt();
                reportMovementClient(movementClient);
            }
            case 3 -> {
                System.out.print(">INGRESE EL INICIO DEL RANGO DE FECHA CON EL FORMATO DD/MM/AAAA: ");
                Scanner rangeDate = new Scanner(System.in);
                String rangeStart = rangeDate.nextLine();
                System.out.print(">INGRESE EL FINAL DEL RANGO DE FECHA CON EL MISMO FORMATO: ");
                String rangeEnd = rangeDate.nextLine();
                ControllerTransaction.reportMovementDate(rangeStart, rangeEnd);
            }
        }
    }
    public static void reportMovementBook(int movementBook) {
        boolean banner = false;
        Book book = ControllerBook.books.get(movementBook - 1);
        for (Transaction transaction : allTransactions){
            if (book.isbn.equals(transaction.book.isbn)) {
                banner = true;
                break;
            }
        }
        if (!banner) {
            System.out.println(">EL LIBRO NO TIENE TRANSACCIONES POR EL MOMENTO");
        } else{
            for (Transaction allTransaction : allTransactions) {
                if (allTransaction.book.isbn.equals(book.isbn) && allTransaction.type.equals("PRESTAMO")) {
                    System.out.println("EL LIBRO " + book.title + " FUE PRESTADO AL CLIENTE " + allTransaction.client.profile.name + " EN LA FECHA " + new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                } else if (allTransaction.book.isbn.equals(book.isbn) && allTransaction.type.equals("DEVOLUCION")) {
                    System.out.println("EL LIBRO " + book.title + " FUE DEVUELTO POR EL CLIENTE " + allTransaction.client.profile.name + " EN LA FECHA " + new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                }
            }
        }
    }
    public static void reportMovementClient (int movementClient){
        boolean bannerClient = false;
        Client client = ControllerClient.clients.get(movementClient -1);
        for (Transaction transaction : allTransactions){
            if (transaction.client.profile.equals(client.profile)) {
                bannerClient = true;
                break;
            }
        }
        if(!bannerClient){
            System.out.println(">EL CLIENTE NO TIENE TRANSACCIONES POR EL MOMENTO");
        }else{
            for (Transaction allTransaction : allTransactions) {
                if (allTransaction.client.profile.equals(client.profile)) {
                    if (allTransaction.type.equals("PRESTAMO")) {
                        System.out.println("EL LIBRO " + allTransaction.book.title + " FUE PRESTADO AL CLIENTE " + allTransaction.client.profile.name + " EN LA FECHA " + new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                    } else if (allTransaction.type.equals("DEVOLUCION")) {
                        System.out.println("EL LIBRO " + allTransaction.book.title + " FUE DEVUELTO POR EL CLIENTE " + allTransaction.client.profile.name + " EN LA FECHA " + new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                    }
                }
            }
        }
    }
    public static void reportMovementDate(String startDateStr, String endDateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);
        if (allTransactions.isEmpty()) {
            System.out.println(">NO HAY TRANSACCIONES DISPONIBLES");
        }else {
            for (Transaction transaction : allTransactions) {
                Date transactionDate = transaction.date;
                if (transactionDate.compareTo(startDate) >= 0 && transactionDate.compareTo(endDate) <= 0) {
                    if (transaction.type.equals("PRESTAMO")) {
                        System.out.println("EL LIBRO " + transaction.book.title + " FUE PRESTADO AL CLIENTE " + transaction.client.profile.name + " EN LA FECHA " + dateFormat.format(transactionDate));
                    } else if (transaction.type.equals("DEVOLUCION")) {
                        System.out.println("EL LIBRO " + transaction.book.title + " FUE DEVUELTO POR EL CLIENTE " + transaction.client.profile.name + " EN LA FECHA " + dateFormat.format(transactionDate));
                    }
                }
            }
        }
    }
    public static void borrowedBook() throws ParseException {
        System.out.println(">CLIENTES DISPONIBLES");
        ControllerClient.readerAvailableClients();
        System.out.println(">¿A QUÉ CLIENTE DESEA PRESTARLO?");
        Scanner opcpcc = new Scanner(System.in);
        int clientIndex = opcpcc.nextInt();
        Client selectedClient = ControllerClient.clients.get(clientIndex - 1);
        System.out.println(">LIBROS DISPONIBLES");
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (int i = 0; i < ControllerBook.books.size(); i++) {
            Book book = ControllerBook.books.get(i);
            if (book.getAvailable()) {
                availableBooks.add(book);
                System.out.printf("%d %s BY %s\n", availableBooks.size(), book.title, book.author.profile.name + " " + book.author.profile.lastName);
            }
        }
        System.out.println(">¿QUÉ LIBRO DESEA PRESTAR?");
        Scanner opclpp = new Scanner(System.in);
        int bookIndex = opclpp.nextInt();
        if (bookIndex > 0 && bookIndex <= availableBooks.size()) {
            Book selectedBook = availableBooks.get(bookIndex - 1);
            selectedBook.setAvailable(false);
            selectedClient.borrowedBooks.add(selectedBook);
            System.out.println(">LIBRO PRESTADO CON EXITO A " + selectedClient.profile.name + " " + selectedClient.profile.lastName);
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(currentDate);
            Transaction transaction = new Transaction(uuid.toString(), "PRESTAMO", selectedClient, selectedBook, formattedDate);
            addTransaction(transaction);
        } else if (ControllerClient.clients.get(clientIndex - 1).borrowedBooks.size() > 3) {
            System.out.println(">NO SE PUEDE PRESTAR MAS DE 3 LIBROS A UNA PERSONA");
        } else {
            System.out.println(">EL INDICE DEL LIBRO SELECCIONADO NO ES VALIDO");
        }
    }
    public static void returnBook() throws ParseException {
        System.out.println(">CLIENTES CON LIBROS PRESTADOS");
        for (int i = 0; i < ControllerClient.clients.size(); i++) {
            Client client = ControllerClient.clients.get(i);
            if (!client.borrowedBooks.isEmpty()) {
                System.out.println((i + 1) + " " + client.profile.name + " " + client.profile.lastName);
            }
        }
        System.out.println(">¿QUÉ CLIENTE VA A DEVOLVER EL LIBRO?");
        Scanner opccdd = new Scanner(System.in);
        int clientIndexToReturn = opccdd.nextInt();

        if (clientIndexToReturn > 0 && clientIndexToReturn <= ControllerClient.clients.size()) {
            Client selectedClientToReturn = ControllerClient.clients.get(clientIndexToReturn - 1);
            ArrayList<Book> borrowedBooks = selectedClientToReturn.borrowedBooks;
            if (!borrowedBooks.isEmpty()) {
                System.out.println(">LIBROS PRESTADOS DE " + selectedClientToReturn.profile.name + " " + selectedClientToReturn.profile.lastName);
                for (int i = 0; i < borrowedBooks.size(); i++) {
                    Book book = borrowedBooks.get(i);
                    System.out.printf("%d %s DE %s\n", (i + 1), book.title, book.author.profile.name + " " + book.author.profile.lastName);
                }
                System.out.println(">¿QUÉ LIBRO VA A DEVOLVER?");
                Scanner opbb = new Scanner(System.in);
                int bookIndexToReturn = opbb.nextInt();
                if (bookIndexToReturn > 0 && bookIndexToReturn <= borrowedBooks.size()) {
                    Book selectedBookToReturn = borrowedBooks.get(bookIndexToReturn - 1);
                    selectedBookToReturn.setAvailable(true);
                    selectedClientToReturn.borrowedBooks.remove(selectedBookToReturn);
                    System.out.println(">LIBRO DEVUELTO CON EXITO POR " + selectedClientToReturn.profile.name + " " + selectedClientToReturn.profile.lastName);
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = dateFormat.format(currentDate);
                    Transaction transaction2 = new Transaction (uuid.toString(), "DEVOLUCION", selectedClientToReturn, selectedBookToReturn, formattedDate);
                    addTransaction(transaction2);
                } else {
                    System.out.println(">EL INDICE DEL LIBRO SELECCIONADO NO ES VALIDO");
                }
            }
        } else {
            System.out.println(">EL INDICE DEL CIENTE SELECCIONADO NO ES VALIDO");
        }
    }
}