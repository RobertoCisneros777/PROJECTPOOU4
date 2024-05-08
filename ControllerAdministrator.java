package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

class ControllerAdministrator implements Controller{

    public static void readAdmins() {
        System.out.printf("| %-25s | %-25s |", "USERNAME", "PERMISOS");
        System.out.println();
        for (int i = 0; i < ControllerUser.users.size(); i++) {
            if (ControllerUser.users.get(i) instanceof Administrator) {
                System.out.printf("| %-25s | %-25s |",
                        ControllerUser.users.get(i).getUserName(),
                        formatPermissions(((Administrator) ControllerUser.users.get(i)).permissions));
                System.out.println();
            }
        }
    }
    private static String formatPermissions(ArrayList<Permissions> permissions) {
        StringBuilder builder = new StringBuilder();
        for (Permissions permission : permissions) {
            builder.append(permission.toString()).append(", ");
        }
        if (!builder.isEmpty()) {
            builder.setLength(builder.length() - 2);
        }
        return builder.toString();
    }
    public static void createAdmin() throws ParseException {
        Scanner createAdmin = new Scanner(System.in);
        System.out.print(">INGRESE NOMBRE: ");
        String nameAdmin = createAdmin.nextLine();
        System.out.print(">INGRESE APELLIDO: ");
        String lastNameAdmin = createAdmin.nextLine();
        System.out.print(">INGRESE FECHA DE NACIMIENTO CON EL SIGUIENTE FORMATO: DD/MM/YYYY: ");
        String birthdateAdmin = createAdmin.nextLine();
        Profile profile = new Profile(nameAdmin, lastNameAdmin, birthdateAdmin);
        ControllerUser.createAdminUser(profile);
    }

    public static void deleteAdmin() {
        ControllerAdministrator.readAdmins();
        System.out.print(">¿QUE ADMINISTRADOR DESEA ELIMINAR?: ");
        ArrayList <Administrator> administrators = new ArrayList<>();
        for (int i = 0; i < ControllerUser.users.size(); i++) {
            if (ControllerUser.users.get(i) instanceof Administrator){
                administrators.add((Administrator) ControllerUser.users.get(i));
            }
        }
        Scanner delete = new Scanner(System.in);
        int deleteIndex = delete.nextInt();
        if (administrators.get(deleteIndex - 1).isIsSuperAdmin()){
            System.out.println(">NO PUEDES ELIMINAR AL SUPER ADMINISTRADOR");
        }else{
            administrators.remove(deleteIndex - 1);
            for (int i = 0; i < ControllerUser.users.size(); i++) {
                if (ControllerUser.users.get(i).getProfile().equals(administrators.get(deleteIndex - 1).getProfile())){
                    ControllerUser.users.remove(administrators.get(deleteIndex - 1));
                }
            }
            System.out.println(">ADMINISTRADOR ELIMINADO CON EXITO");
        }
    }

    public static void updateAdmin() throws ParseException {
        ConsoleReader reader = new ConsoleReader();
        ControllerAdministrator.readAdmins();
        ArrayList <Administrator> updateAdmin = new ArrayList<>();
        for (int i = 0; i < ControllerUser.users.size(); i++){
            if (ControllerUser.users.get(i) instanceof Administrator){
                updateAdmin.add((Administrator) ControllerUser.users.get(i));
            }
        }
        System.out.println(">¿QUE ADMINISTRADOR DESEAS EDITAR?");
        Scanner updateAdminIndex = new Scanner(System.in);
        int updateIndex = updateAdminIndex.nextInt();
        Scanner update = new Scanner(System.in);
        System.out.println(">¿QUE DESEAS EDITAR DEL ADMINISTRADOR?");
        System.out.println("\n>1) NOMBRE\n>2) APELLIDO\n>3) FECHA DE NACIMIENTO\n>4) PERMISOS\n>4) NOMBRE DE USUARIO\n>4) CONTRASEÑA");
        IntValidator validatorOption = value -> value < 5;
        int opcEdit = reader.readInteger(">SELECCIONA OPCION", validatorOption);
        update.nextLine();
        switch (opcEdit) {
            case 1 -> {
                System.out.print(">INGRESE EL NUEVO NOMBRE: ");
                updateAdmin.get(updateIndex - 1).getProfile().name = update.nextLine();
            }
            case 2 -> {
                System.out.print(">INGRESE EL NUEVO APELLIDO: ");
                updateAdmin.get(updateIndex - 1).getProfile().lastName = update.nextLine();
            }
            case 3 -> {
                System.out.print(">INGRESE LA NUEVA FECHA DE NACIMIENTO CON EL SIGUIENTE FORMATO DD/MM/YYYY: ");
                String newBirthdate = update.nextLine();
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                updateAdmin.get(updateIndex - 1).getProfile().birthdate = format.parse(newBirthdate);
            }
            case 4 -> {
                updateAdmin.get(updateIndex - 1).setUserName(ControllerUser.validUserName());
            }
            case 5 -> {
                updateAdmin.get(updateIndex - 1).setPassword(ControllerUser.validPassword());
            }
        }
        for (int i = 0; i < ControllerUser.users.size(); i++) {
            if (ControllerUser.users.get(i).getProfile().equals(updateAdmin.get(updateIndex - 1).getProfile())){
                ControllerUser.users.set(i, updateAdmin.get(updateIndex - 1));
            }
        }
        updateAdmin.clear();
    }

    @Override
    public void execute(User user) throws ParseException {
        int opcAdmin;
        do {
            System.out.println();
            System.out.println("-MENU ADMINISTRADORES- ");
            System.out.println();
            System.out.println(">¿QUE DESEA HACER?");
            System.out.println("\n>1) AÑADIR ADMINISTRADOR\n>2) EDITAR ADMINISTRADOR\n>3) MOSTRAR ADMINISTRADORES\n>4) ELIMINAR ADMINISTRADOR\n>5) SALIR DEL MENU ADMINISTRADORES");
            ConsoleReader reader = new ConsoleReader();
            IntValidator validatorOption = value -> value < 6;
            opcAdmin = reader.readInteger(">SELECCIONE OPCION", validatorOption);
            switch (opcAdmin){
                case 1 -> createAdmin();
                case 2 -> updateAdmin();
                case 3 -> readAdmins();
                case 4 -> deleteAdmin();
            }
        } while (opcAdmin < 5);
    }
}