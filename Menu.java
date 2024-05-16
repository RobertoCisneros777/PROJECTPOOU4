package ProjectPOO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

class Menu {
    static HashMap <Integer, MenuOption> menuItems = new HashMap<>();
    public static void menu (User user) throws ParseException {
        ConsoleReader reader = new ConsoleReader();
        if (user instanceof Client){
            menuItems.clear();
            int opc;
            Controller option1 = new ControllerBook();
            Controller option2 = new ControllerTransaction();
            Controller option3 = new ControllerUser();
            addMenuItem(1, new MenuOption("\n>1) VER LIBROS", option1));
            addMenuItem(2, new MenuOption("\n>2) VER TRANSACCIONES", option2));
            addMenuItem(3, new MenuOption("\n>3) CERRAR SESION", option3));
            addMenuItem(4, new MenuOption("\n>4) SALIR", null));
            do {
                System.out.println();
                System.out.println("-MENU DE USUARIO CLIENTE-");
                for (Integer key : menuItems.keySet()) {
                    MenuOption menuOption= menuItems.get(key);
                    System.out.print(menuOption.getText());
                }
                System.out.println();
                IntValidator validator = value -> value > 0 && value < 5;
                opc = reader.readInteger(">SELECCIONA OPCION", validator);
                MenuOption menuOption = menuItems.get(opc);
                Controller controller = menuOption.getController();
                if (controller instanceof ControllerBook){
                    ControllerBook.readerAllBooks();
                }else if (controller instanceof ControllerTransaction) {
                    ControllerTransaction.reportMovementUserClient(user);
                }
            } while (opc != 3 && opc != 4);
            if (opc==3){
                ControllerUser.login();
            }
        } else if (user instanceof Administrator) {
            if (((Administrator) user).isIsSuperAdmin()){
                menuItems.clear();
                int opcSuperAdmin;
                do {
                    System.out.println();
                    System.out.println("-MENU DE USUARIO SUPER ADMINISTRADOR-");
                    Controller option1 = new ControllerClient();
                    Controller option2 = new ControllerAuthor();
                    Controller option3 = new ControllerBook();
                    Controller option4 = new ControllerAdministrator();
                    Controller option5 = new ControllerTransaction();
                    Controller option6 = new ControllerUser();
                    addMenuItem(1, new MenuOption("\n>1) MENU CLIENTES", option1));
                    addMenuItem(2, new MenuOption("\n>2) MENU AUTORES", option2));
                    addMenuItem(3, new MenuOption("\n>3) MENU LIBROS", option3));
                    addMenuItem(4, new MenuOption("\n>4) MENU ADMINISTRADORES", option4));
                    addMenuItem(5, new MenuOption("\n>5) MENU TRANSACCIONES", option5));
                    addMenuItem(6, new MenuOption("\n>6) CERRAR SESION", option6));
                    addMenuItem(7, new MenuOption("\n>7) SALIR", null));
                    printOpcAdmin();
                    IntValidator validator = value -> value > 0 && value < 8;
                    opcSuperAdmin = reader.readInteger(">SELECCIONA OPCION", validator);
                    if (opcSuperAdmin == 6) {
                        ControllerUser.login();
                    }if(opcSuperAdmin != 7) {
                        MenuOption menuOption = menuItems.get(opcSuperAdmin);
                        menuOption.getController().execute(user);
                    }
                }while (opcSuperAdmin != 6 && opcSuperAdmin != 7);
            }else{
                menuItems.clear();
                int opcAdmin;
                do {
                    System.out.println();
                    System.out.println("-MENU DE USUARIO ADMINISTRADOR- ");
                    Controller option1 = new ControllerClient();
                    Controller option2 = new ControllerAuthor();
                    Controller option3 = new ControllerBook();
                    Controller option4 = new ControllerTransaction();
                    Controller option5 = new ControllerUser();
                    addMenuItem(1, new MenuOption("\n>1) MENU CLIENTES", option1));
                    addMenuItem(2, new MenuOption("\n>2) MENU AUTORES", option2));
                    addMenuItem(3, new MenuOption("\n>3) MENU LIBROS", option3));
                    addMenuItem(4, new MenuOption("\n>4) MENU TRANSACCIONES", option4));
                    addMenuItem(5, new MenuOption("\n>5) CERRAR SESION", option5));
                    addMenuItem(6, new MenuOption("\n>6) SALIR", null));
                    printOpcAdmin();
                    IntValidator validator = value -> value > 0 && value < 7;
                    opcAdmin = reader.readInteger(">SELECCIONA OPCION", validator);
                    if (opcAdmin == 5) {
                        ControllerUser.login();
                    }
                    if (opcAdmin != 6){
                        MenuOption menuOption = menuItems.get(opcAdmin);
                        menuOption.getController().execute(user);
                    }
                } while (opcAdmin != 5 && opcAdmin != 6);
            }
        }
    }
    private static void printOpcAdmin() {
        for (Integer key : menuItems.keySet()) {
            MenuOption menuOption= menuItems.get(key);
            System.out.print(menuOption.getText());
        }
        System.out.println();
    }
    static void printOptions(ArrayList<String> options) {
        int optionNumber = 1;
        for (String option : options) {
            System.out.println(">" + optionNumber + ") " + option);
            optionNumber++;
        }
    }
    static void addMenuItem (int key, MenuOption menuOption){menuItems.put(key, menuOption);
    }
}