package ProjectPOO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

class ControllerMenu {
    public static void rlvTransaction(User user) throws ParseException {
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
            printOptions(optionsTransaction);
            System.out.print(">SELECCIONA OPCION: ");
            Scanner rlvTransaction = new Scanner(System.in);
            opc = rlvTransaction.nextInt();
            if (opc >= 1 && opc <= optionsTransaction.size()) {
                String selectedOption = optionsTransaction.get(opc - 1);
                switch (selectedOption) {
                    case "PRESTAR UN LIBRO" -> {
                        System.out.println();
                        ControllerTransaction.borrowedBook();
                    }
                    case "DEVOLVER UN LIBRO" -> {
                        System.out.println();
                        ControllerTransaction.returnBook();
                    }
                    case "VER TRANSACCIONES" -> {
                        System.out.println();
                        ControllerTransaction.reportMovement();
                    }
                }
            } else {
                System.out.println("OPCION NO VALIDA");
            }
        } while (!optionsTransaction.get(opc - 1).equals("SALIR DEL MENU TRANSACCIONES"));
    }
    public static void crudClient(User user) throws ParseException {
        ArrayList<String> options = new ArrayList<>();
        int opcClient;
        do {
            System.out.println();
            System.out.println("-MENU CLIENTES-");
            System.out.println();
            System.out.println(">¿QUE DESEA HACER?");
            options.clear();
            if (((Administrator) user).isIsSuperAdmin() || ((Administrator) user).permissions.contains(Permissions.READ) && ((Administrator) user).permissions.contains(Permissions.DELETE) && ((Administrator) user).permissions.contains(Permissions.WRITE)) {
                options.add("AÑADIR CLIENTE");
                options.add("EDITAR CLIENTE");
                options.add("MOSTRAR CLIENTES");
                options.add("ELIMINAR CLIENTE");
                options.add("SALIR DEL MENU CLIENTES");
            } else {
                if (((Administrator) user).permissions.contains(Permissions.WRITE)) {
                    options.add("AÑADIR CLIENTE");
                    options.add("EDITAR CLIENTE");
                }
                if (((Administrator) user).permissions.contains(Permissions.READ)) {
                    options.add("MOSTRAR CLIENTES");
                }
                if (((Administrator) user).permissions.contains(Permissions.DELETE)) {
                    options.add("ELIMINAR CLIENTE");
                }
                options.add("SALIR DEL MENU CLIENTES");
            }
            printOptions(options);
            System.out.print(">SELECCIONA OPCION: ");
            Scanner crudClient = new Scanner(System.in);
            opcClient = crudClient.nextInt();
            if (opcClient >= 1 && opcClient <= options.size()) {
                String selectedOption = options.get(opcClient - 1);
                switch (selectedOption) {
                    case "AÑADIR CLIENTE" -> {
                        System.out.println();
                        ControllerClient.createClient();
                    }
                    case "EDITAR CLIENTE" -> {
                        System.out.println();
                        ControllerClient.updateClient();
                    }
                    case "MOSTRAR CLIENTES" -> {
                        System.out.println();
                        ControllerClient.readerClientsBooksBorrowed();
                    }
                    case "ELIMINAR CLIENTE" -> {
                        System.out.println();
                        ControllerClient.deleteClient();
                    }
                }
            } else {
                System.out.println("OPCION NO VALIDA");
            }
        } while (!options.get(opcClient - 1).equals("SALIR DEL MENU CLIENTES"));
    }
    public static void crudAuthor(User user) throws ParseException {
        ArrayList<String> optionsAuthors = new ArrayList<>();
        int opcAuthor;
        do {
            System.out.println();
            System.out.println("-MENU AUTORES- ");
            System.out.println();
            System.out.println(">¿QUE DESEA HACER?");
            optionsAuthors.clear();
            if (((Administrator) user).isIsSuperAdmin() || ((Administrator) user).permissions.contains(Permissions.READ) && ((Administrator) user).permissions.contains(Permissions.DELETE) && ((Administrator) user).permissions.contains(Permissions.WRITE)) {
                optionsAuthors.add("AÑADIR AUTOR");
                optionsAuthors.add("EDITAR AUTOR");
                optionsAuthors.add("MOSTRAR AUTORES");
                optionsAuthors.add("ELIMINAR AUTOR");
                optionsAuthors.add("SALIR DEL MENU AUTORES");
            } else {
                if (((Administrator) user).permissions.contains(Permissions.WRITE)) {
                    optionsAuthors.add("AÑADIR AUTOR");
                    optionsAuthors.add("EDITAR AUTOR");
                }
                if (((Administrator) user).permissions.contains(Permissions.READ)) {
                    optionsAuthors.add("MOSTRAR AUTORES");
                }
                if (((Administrator) user).permissions.contains(Permissions.DELETE)) {
                    optionsAuthors.add("ELIMINAR AUTOR");
                }
                optionsAuthors.add("SALIR DEL MENU AUTORES");
            }
            printOptions(optionsAuthors);
            System.out.print(">SELECCIONA OPCION: ");
            Scanner crudAuthor = new Scanner(System.in);
            opcAuthor = crudAuthor.nextInt();
            if (opcAuthor >= 1 && opcAuthor <= optionsAuthors.size()) {
                String selectedOptionAuthor = optionsAuthors.get(opcAuthor - 1);
                switch (selectedOptionAuthor) {
                    case "AÑADIR AUTOR" -> {
                        System.out.println();
                        ControllerAuthor.createAuthor();
                    }
                    case "EDITAR AUTOR" -> {
                        System.out.println();
                        ControllerAuthor.editAuthor();
                    }
                    case "MOSTRAR AUTORES" -> {
                        System.out.println();
                        ControllerAuthor.readerAuthors();
                    }
                    case "ELIMINAR AUTOR" -> {
                        System.out.println();
                        ControllerAuthor.deleteAuthor();
                    }
                }
            } else {
                System.out.println("OPCION NO VALIDA");
            }
        } while (!optionsAuthors.get(opcAuthor - 1).equals("SALIR DEL MENU AUTORES"));
    }
    public static void crudBook(User user) throws ParseException {
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
            printOptions(optionsBook);
            System.out.print(">SELECCIONA OPCION: ");
                Scanner crudBook = new Scanner(System.in);
            opcBook = crudBook.nextInt();
            if (opcBook >= 1 && opcBook <= optionsBook.size()) {
                String selectedOption = optionsBook.get(opcBook - 1);
                switch (selectedOption) {
                    case "AÑADIR LIBRO" -> {
                        System.out.println();
                        ControllerBook.createBook();
                    }
                    case "EDITAR LIBRO" -> {
                        System.out.println();
                        ControllerBook.updateBook();
                    }
                    case "MOSTRAR LIBROS" -> {
                        System.out.println();
                        System.out.println("\n>1) MOSTRAR TODOS LOS LIBROS \n>2) MOSTRAR LIBROS PRESTADOS \n>3) MOSTRAR LIBROS DISPONIBLES PARA PRESTAMO");
                        crudBook.nextLine();
                        System.out.print(">SELECCIONE OPCION: ");
                        int readerOption = crudBook.nextInt();
                        if (readerOption == 1) {
                            ControllerBook.readerAllBooks();
                        }else if (readerOption == 2){
                            ControllerBook.readerBorrowedBooks();
                        }else if (readerOption == 3){
                            ControllerBook.readerAvailableBooks();
                        }
                    }
                    case "ELIMINAR LIBRO" -> {
                        System.out.println();
                        ControllerBook.deleteBook();
                    }
                }
            } else {
                System.out.println("OPCION NO VALIDA");
            }
        } while (!optionsBook.get(opcBook - 1).equals("SALIR DEL MENU LIBROS"));
    }
    public static void crudAdministrator() throws ParseException {
        int opcAdmin;
        do {
            System.out.println();
            System.out.println("-MENU ADMINISTRADORES- ");
            System.out.println();
            System.out.println(">¿QUE DESEA HACER?");
            System.out.println("\n>1) AÑADIR ADMINISTRADOR\n>2) EDITAR ADMINISTRADOR\n>3) MOSTRAR ADMINISTRADORES\n>4) ELIMINAR ADMINISTRADOR\n>5) SALIR DEL MENU ADMINISTRADORES");
            Scanner crudAdmin = new Scanner(System.in);
            System.out.print(">SELECCIONE OPCION: ");
            opcAdmin = crudAdmin.nextInt();
            switch (opcAdmin){
                case 1 -> ControllerAdministrator.createAdmin();
                case 2 -> ControllerAdministrator.updateAdmin();
                case 3 -> ControllerAdministrator.readAdmins();
                case 4 -> ControllerAdministrator.deleteAdmin();
            }
        } while (opcAdmin < 5);
    }
    private static void printOptions(ArrayList<String> options) {
        int optionNumber = 1;
        for (String option : options) {
            System.out.println(">" + optionNumber + ") " + option);
            optionNumber++;
        }
    }
    public static void menuUserSuperAdmin(User user) throws ParseException {
        int opc;
        do {
            Scanner leer;
            System.out.println();
            System.out.println("-MENU DE USUARIO SUPER ADMINISTRADOR- ");
            System.out.println();
            System.out.println(">¿A QUE MENU DESEA INGRESAR?");
            System.out.println();
            System.out.println("\n>1) MENU CLIENTES\n>2) MENU AUTORES\n>3) MENU LIBROS\n>4) MENU ADMINISTRADORES\n>5) MENU TRANSACCIONES \n>6) CERRAR SESION\n>7) SALIR");
            System.out.print(">SELECCIONA OPCION: ");
            leer = new Scanner(System.in);
            opc = leer.nextInt();
            if (opc != 7) {
                switch (opc) {
                    case 1 -> ControllerMenu.crudClient(user);
                    case 2 -> ControllerMenu.crudAuthor(user);
                    case 3 -> ControllerMenu.crudBook(user);
                    case 4 -> ControllerMenu.crudAdministrator();
                    case 5 -> ControllerMenu.rlvTransaction(user);
                }
            }
        } while (opc != 6 && opc != 7);
        if (opc == 6) {
            ControllerUser.login();
        }
    }
    public static void menuUserAdmin(User user) throws ParseException {
        int opcAdmin;
            do {
                System.out.println();
                System.out.println("-MENU DE USUARIO ADMINISTRADOR- ");
                System.out.println();
                System.out.println(">¿A QUE MENU DESEA INGRESAR?");
                System.out.println("\n>1) MENU CLIENTES \n>2) MENU AUTORES\n>3) MENU LIBROS\n>4) MENU TRANSACCIONES \n>5) CERRAR SESION \n>6) SALIR");
                System.out.print(">SELECCIONA OPCION: ");
                Scanner leer = new Scanner(System.in);
                opcAdmin = leer.nextInt();
                if (opcAdmin != 6) {
                    switch (opcAdmin) {
                        case 1 -> ControllerMenu.crudClient(user);
                        case 2 -> ControllerMenu.crudAuthor(user);
                        case 3 -> ControllerMenu.crudBook(user);
                        case 4 -> ControllerMenu.rlvTransaction(user);
                    }
                }
            } while (opcAdmin != 5 && opcAdmin != 6);
            if (opcAdmin == 5) {
                ControllerUser.login();
            }
    }
    public static void menuUserClient (User user) throws ParseException {
        Scanner menuUser;
        int opc;
        do {
            System.out.println();
            System.out.println("-MENU DE USUARIO CLIENTE- ");
            System.out.println("\n>1) VER LIBROS \n>2) VER TRANSACCIONES\n>3) CERRAR SESION\n>4) SALIR");
            System.out.print(">SELECCIONA OPCION: ");
            menuUser = new Scanner(System.in);
            opc = menuUser.nextInt();
            switch (opc) {
                case 1 -> ControllerBook.readerAllBooks();
                case 2 -> ControllerTransaction.reportMovementUserClient(user);
            }
        } while (opc != 3 && opc != 4);
        if (opc==3){
            ControllerUser.login();
        }
    }
}