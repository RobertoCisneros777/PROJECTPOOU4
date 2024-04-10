package ProjectPOO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

    class ControllerUser {
        private static final ArrayList<User> users = new ArrayList<>();

        public ControllerUser() {
        }

        public static void addUsers(User user) {
            users.add(user);
        }

        public static void createClientUser(Profile profile) {
            Scanner createUser = new Scanner(System.in);
            System.out.print(">INGRESE NOMBRE DE USUARIO: ");
            String userName = createUser.nextLine();
            System.out.print(">INGRESE CONTRASEÑA: ");
            String password = createUser.nextLine();
            Client client = new Client(profile, userName, password);
            client.setPassword(password);
            addUsers(client);
            System.out.println(">CLIENTE AÑADIDO CON EXITO");
        }
        public static void createAdminUser(Profile profile) {
            Scanner createUser = new Scanner(System.in);
            ArrayList<Permissions> permissions = new ArrayList<>();

            System.out.print(">INGRESE NOMBRE DE USUARIO: ");
            String userName = createUser.nextLine();

            System.out.print(">INGRESE CONTRASEÑA: ");
            String password = createUser.nextLine();

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
            Administrator administrator = new Administrator(profile, userName, password, false, permissions);
            administrator.setPassword(password);
            ControllerUser.addUsers(administrator);
            ControllerAdministrator.addAdmins(administrator);
            System.out.println(">ADMINISTRADOR AÑADIDO CON EXITO");
        }

        public static void login() throws ParseException {
            Scanner login = new Scanner(System.in);
            boolean correctPassword = false;
            boolean userFound = false;
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
                                        ControllerMenu.menuUserSuperAdmin(user);
                                    } else {
                                        System.out.println(">BIENVENIDO ADMIN " + user.getUserName());
                                        ControllerMenu.menuUserAdmin(user);
                                    }
                                } else if (user instanceof Client) {
                                    System.out.println(">BIENVENIDO CLIENTE " + user.getUserName());
                                    ControllerMenu.menuUserClient(user);
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
    }