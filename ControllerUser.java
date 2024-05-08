package ProjectPOO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

    class ControllerUser implements Controller {
        static ArrayList<User> users = new ArrayList<>();

        public ControllerUser() {
        }
        public static String validUserName(){
            ConsoleReader reader = new ConsoleReader();
            StringValidator validatorUserName = value -> {
                for (User user : users) {
                    if (value.equals(user.getUserName())){
                        return false;
                    }
                }
                return true;
            };
            return reader.readString(">INGRESE NOMBRE DE USUARIO", validatorUserName);
        }
        public static String validPassword(){
            ConsoleReader reader = new ConsoleReader();
            StringValidator validatorPassword = value ->  {
                boolean hasUppercase = false;
                boolean hasLowercase = false;
                boolean hasDigit = false;
                if (value.length() < 8) {
                    return false;
                }
                for (char c : value.toCharArray()) {
                    if (Character.isUpperCase(c)){
                        hasUppercase = true;
                    } else if (Character.isLowerCase(c)){
                        hasLowercase = true;
                    } else if (Character.isDigit(c)){
                        hasDigit = true;
                    }
                }
                return hasUppercase && hasLowercase && hasDigit;
            };
            return reader.readString(">INGRESE CONTRASEÑA", validatorPassword);
        }
        public static void createClientUser(Profile profile) {
            String userName = validUserName();
            String password = validPassword();
            Client client = new Client(profile, userName, password);
            client.setPassword(password);
            users.add(client);
            System.out.println(">CLIENTE AÑADIDO CON EXITO");
        }
        public static void createAdminUser(Profile profile) {
            Scanner createUser = new Scanner(System.in);
            ArrayList<Permissions> permissions = new ArrayList<>();
            System.out.print(">¿DESEA QUE TENGA PERMISO DE LEER DATOS?: ");
            String permissionRead = createUser.nextLine();
            if (permissionRead.equalsIgnoreCase("SI")) {
                permissions.add(Permissions.READ);
            }
            System.out.print(">¿DESEA QUE TENGA PERMISO DE EDITAR O AGREGAR DATOS?: ");
            String permissionWrite = createUser.nextLine();
            if (permissionWrite.equalsIgnoreCase("SI")) {
                permissions.add(Permissions.WRITE);
            }
            System.out.print(">¿DESEA QUE TENGA PERMISO DE ELIMINAR DATOS?: ");
            String permissionDelete = createUser.nextLine();
            if (permissionDelete.equalsIgnoreCase("SI")) {
                permissions.add(Permissions.DELETE);
            }
            String userName = validUserName();
            String password = validPassword();
            Administrator administrator = new Administrator(profile, userName, password, false, permissions);
            administrator.setPassword(password);
            users.add(administrator);
            System.out.println(">ADMINISTRADOR AÑADIDO CON EXITO");
        }

        public static void login() throws ParseException {
            Scanner login = new Scanner(System.in);
            boolean correctPassword = false;
            boolean userFound = false;
            System.out.println();
            System.out.println("- INICIAR SESION -");
            while (!userFound) {
                System.out.print(">INGRESE SU NOMBRE DE USUARIO: ");
                String inputUserName = login.nextLine();
                for (User user : users) {
                    if (inputUserName.equals(user.getUserName())) {
                        userFound = true;
                        while (!correctPassword) {
                            System.out.print(">INGRESE SU CONTRASEÑA: ");
                            String inputPassword = login.nextLine();
                            if (user.checkPassword(inputPassword)) {
                                correctPassword = true;
                                if (user instanceof Administrator administrator) {
                                    if (administrator.isIsSuperAdmin()) {
                                        System.out.println(">BIENVENIDO SUPER ADMIN " + user.getUserName());
                                        Menu.menu(user);
                                    } else {
                                        System.out.println(">BIENVENIDO ADMIN " + user.getUserName());
                                        Menu.menu(user);
                                    }
                                } else if (user instanceof Client) {
                                    System.out.println(">BIENVENIDO CLIENTE " + user.getUserName());
                                    Menu.menu(user);
                                }
                            } else {
                                System.out.println(">CONTRASEÑA INCORRECTA");
                            }
                        }
                        break;
                    }
                }
                if (!userFound) {
                    System.out.println(">NO SE ENCONTRO EL NOMBRE DE USUARIO, INTENTALO DE NUEVO");
                }
            }
        }

        @Override
        public void execute(User user) throws ParseException {
        }
    }