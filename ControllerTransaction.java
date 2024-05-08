package ProjectPOO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

    class ControllerTransaction implements  Controller{
    static ArrayList <Transaction> allTransactions= new ArrayList <> ();
    static UUID uuid = UUID.randomUUID();
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
                System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", "CLIENTE", "LIBRO", "TIPO DE TRANSACCION", "FECHA");
                for (Transaction allTransaction : allTransactions) {
                    if (allTransaction.client.profile.equals(user.getProfile())) {
                        if (allTransaction.type.equals("PRESTAMO")) {
                            System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", allTransaction.client.profile.name, allTransaction.book.title, allTransaction.type, new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                            flag = true;
                        } else if (allTransaction.type.equals("DEVOLUCION")) {
                            System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", allTransaction.client.profile.name, allTransaction.book.title, allTransaction.type, new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
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
                        System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", "CLIENTE", "LIBRO", "TIPO DE TRANSACCION", "FECHA");
                        for (Transaction transaction : allTransactions) {
                            Date transactionDate = transaction.date;
                            if (transactionDate.compareTo(startDate1) >= 0 && transactionDate.compareTo(endDate1) <= 0) {
                                if (transaction.type.equals("PRESTAMO") && transaction.client.profile.equals(user.getProfile())) {
                                    System.out.printf("|%-20s | %-40s | %-20s | %-10s |%n", transaction.client.profile.name, transaction.book.title, transaction.type, new SimpleDateFormat("dd/MM/yyyy").format(transaction.date));
                                    banner = true;
                                } else if (transaction.type.equals("DEVOLUCION") && transaction.client.profile.equals(user.getProfile())) {
                                    System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", transaction.client.profile.name, transaction.book.title, transaction.type, new SimpleDateFormat("dd/MM/yyyy").format(transaction.date));
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
                ArrayList <Client> clients = new ArrayList<>();
                fillClient(clients);
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

        static void fillClient(ArrayList<Client> clients) {
            for (int i = 0; i < ControllerUser.users.size(); i++){
                if (ControllerUser.users.get(i) instanceof Client){
                    clients.add((Client) ControllerUser.users.get(i));
                }
            }
            System.out.printf("| %-20s |%n", "CLIENTE");
            for (Client client : clients) {
                System.out.printf("| %-20s |%n", client.profile.name);
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
            System.out.printf("| %-20s | %-40s | %-10s | %-20s |%n", "CLIENTE", "LIBRO", "TIPO DE TRANSACCION", "FECHA");
            for (Transaction allTransaction : allTransactions) {
                if (allTransaction.book.isbn.equals(book.isbn) && allTransaction.type.equals("PRESTAMO")) {
                    System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", allTransaction.client.profile.name, allTransaction.book.title, allTransaction.type, new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                    System.out.println();
                } else if (allTransaction.book.isbn.equals(book.isbn) && allTransaction.type.equals("DEVOLUCION")) {
                    System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", allTransaction.client.profile.name, allTransaction.book.title, allTransaction.type, new SimpleDateFormat("dd/MM/yyyy").format(allTransaction.date));
                    System.out.println();
                }
            }
        }
    }
    public static void reportMovementClient (int movementClient){
        boolean bannerClient = false;
        ArrayList <Client> reportClient = new ArrayList<>();
        fillClient(reportClient);
        for (Transaction transaction : allTransactions){
            if (transaction.client.profile.equals(reportClient.get(movementClient - 1).profile)) {
                bannerClient = true;
                break;
            }
        }
        if(!bannerClient){
            System.out.println(">EL CLIENTE NO TIENE TRANSACCIONES POR EL MOMENTO");
        }else{
            System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", "CLIENTE", "LIBRO", "TIPO DE TRANSACCION", "FECHA");
            for (Transaction allTransaction : allTransactions) {
                if (allTransaction.client.profile.equals(reportClient.get(movementClient - 1).profile)) {
                    printTransaction(allTransaction);
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
            System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", "CLIENTE", "LIBRO", "TIPO DE TRANSACCION", "FECHA");
            for (Transaction transaction : allTransactions) {
                Date transactionDate = transaction.date;
                if (transactionDate.compareTo(startDate) >= 0 && transactionDate.compareTo(endDate) <= 0) {
                    printTransaction(transaction);
                }
            }
        }
    }

        private static void printTransaction(Transaction transaction) {
            if (transaction.type.equals("PRESTAMO")) {
                System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", transaction.client.profile.name, transaction.book.title, transaction.type, new SimpleDateFormat("dd/MM/yyyy").format(transaction.date));
            } else if (transaction.type.equals("DEVOLUCION")) {
                System.out.printf("| %-20s | %-40s | %-20s | %-10s |%n", transaction.client.profile.name, transaction.book.title, transaction.type, new SimpleDateFormat("dd/MM/yyyy").format(transaction.date));
            }
        }

        public static void borrowedBook() throws ParseException {
        ArrayList <Client> availableClient = new ArrayList<>();
        System.out.println(">CLIENTES DISPONIBLES");
        ControllerClient.readerAvailableClients();
        System.out.println(">¿A QUÉ CLIENTE DESEA PRESTARLO?");
        Scanner opcpcc = new Scanner(System.in);
        int clientIndex = opcpcc.nextInt();
        System.out.printf(" | %-30s |%n ", "LIBROS DISPONIBLES");
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (int i = 0; i < ControllerBook.books.size(); i++) {
            Book book = ControllerBook.books.get(i);
            if (book.getAvailable()) {
                availableBooks.add(book);
                System.out.printf(" | %-30s |%n ", book.title);
            }
        }
        System.out.println(">¿QUÉ LIBRO DESEA PRESTAR?");
        Scanner opclpp = new Scanner(System.in);
        int bookIndex = opclpp.nextInt();
        if (bookIndex > 0 && bookIndex <= availableBooks.size()) {
            Book selectedBook = availableBooks.get(bookIndex - 1);
            selectedBook.setAvailable(false);
            for (int i = 0; i < ControllerUser.users.size(); i++) {
                if (ControllerUser.users.get(i) instanceof Client && ((Client) ControllerUser.users.get(i)).borrowedBooks.size() < 3){
                    availableClient.add((Client) ControllerUser.users.get(i));
                }
            }
            availableClient.get(clientIndex - 1).borrowedBooks.add(selectedBook);
            System.out.println(">LIBRO PRESTADO CON EXITO A " + availableClient.get(clientIndex - 1).profile.name + " " + availableClient.get(clientIndex - 1).profile.lastName);
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(currentDate);
            Transaction transaction = new Transaction(uuid.toString(), "PRESTAMO", availableClient.get(clientIndex - 1), selectedBook, formattedDate);
            allTransactions.add(transaction);
        } else if (availableClient.get(clientIndex - 1).borrowedBooks.size() > 3) {
            System.out.println(">NO SE PUEDE PRESTAR MAS DE 3 LIBROS A UNA PERSONA");
        } else {
            System.out.println(">EL INDICE DEL LIBRO SELECCIONADO NO ES VALIDO");
        }
    }
    public static void returnBook() throws ParseException {
        System.out.printf(" | %-20s |%n ", "CLIENTES CON LIBROS PRESTADOS");
        ArrayList <Client> clientsWithBooks = new ArrayList<>();
        for (int i = 0; i < ControllerUser.users.size(); i++) {
            if (ControllerUser.users.get(i) instanceof Client && ((Client) ControllerUser.users.get(i)).borrowedBooks.size() < 3){
                System.out.printf(" | %-20s |%n ", ControllerUser.users.get(i).getProfile().name);
                clientsWithBooks.add((Client) ControllerUser.users.get(i));
            }
        }
        System.out.println(">¿QUÉ CLIENTE VA A DEVOLVER EL LIBRO?");
        Scanner opccdd = new Scanner(System.in);
        int clientIndexToReturn = opccdd.nextInt();
        if (clientIndexToReturn > 0 && clientIndexToReturn <= clientsWithBooks.size()) {
            Client selectedClientToReturn = clientsWithBooks.get(clientIndexToReturn - 1);
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
                    allTransactions.add(transaction2);
                } else {
                    System.out.println(">EL INDICE DEL LIBRO SELECCIONADO NO ES VALIDO");
                }
            }
        } else {
            System.out.println(">EL INDICE DEL CIENTE SELECCIONADO NO ES VALIDO");
        }
    }

        @Override
        public void execute(User user) throws ParseException {
            ArrayList <String> optionsTransaction = new ArrayList<>();
            int opc;
            do {
                System.out.println();
                System.out.println("-MENU TRANSACCIONES-");
                System.out.println();
                System.out.println(">¿QUE DESEA HACER?");
                System.out.println();
                optionsTransaction.clear();
                if (((Administrator) user).isIsSuperAdmin() || ((Administrator) user).permissions.contains(Permissions.READ) && ((Administrator) user).permissions.contains(Permissions.DELETE) && ((Administrator) user).permissions.contains(Permissions.WRITE)) {
                    optionsTransaction.add("PRESTAR UN LIBRO");
                    optionsTransaction.add("DEVOLVER UN LIBRO");
                    optionsTransaction.add("VER TRANSACCIONES");
                    optionsTransaction.add("SALIR DEL MENU TRANSACCIONES");
                } else {
                    if (((Administrator) user).permissions.contains(Permissions.WRITE)) {
                        optionsTransaction.add("PRESTAR UN LIBRO");
                        optionsTransaction.add("DEVOLVER UN LIBRO");
                    }
                    if (((Administrator) user).permissions.contains(Permissions.READ)) {
                        optionsTransaction.add("VER TRANSACCIONES");
                    }
                    optionsTransaction.add("SALIR DEL MENU TRANSACCIONES");
                }
                Menu.printOptions(optionsTransaction);
                ConsoleReader reader = new ConsoleReader();
                IntValidator validatorOption = value -> value < optionsTransaction.size();
                opc = reader.readInteger(">SELECCIONA OPCION", validatorOption);
                if (opc >= 1 && opc <= optionsTransaction.size()) {
                    String selectedOption = optionsTransaction.get(opc - 1);
                    switch (selectedOption) {
                        case "PRESTAR UN LIBRO" -> {
                            System.out.println();
                            borrowedBook();
                        }
                        case "DEVOLVER UN LIBRO" -> {
                            System.out.println();
                            returnBook();
                        }
                        case "VER TRANSACCIONES" -> {
                            System.out.println();
                            reportMovement();
                        }
                    }
                } else {
                    System.out.println("OPCION NO VALIDA");
                }
            } while (!optionsTransaction.get(opc - 1).equals("SALIR DEL MENU TRANSACCIONES"));
        }
    }