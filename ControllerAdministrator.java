package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

class ControllerAdministrator {
    static ArrayList<Administrator> admins = new ArrayList<>();

    public static void addAdmins(Administrator admin) {
        admins.add(admin);
    }

    public static void readAdmins() {
        System.out.printf("| %-25s | %-25s |", "USERNAME", "PERMISOS");
        System.out.println();
        for (Administrator admin : admins) {
            System.out.printf("| %-25s | %-25s |",
                    admin.getUserName(),
                    formatPermissions(admin.permissions));
            System.out.println();
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
        Scanner delete = new Scanner(System.in);
        int deleteIndex = delete.nextInt();
        if (admins.get(deleteIndex - 1).isIsSuperAdmin()){
            System.out.println(">NO PUEDES ELIMINAR AL SUPER ADMINISTRADOR");
        }else{
            admins.remove(deleteIndex - 1);
            System.out.println(">ADMINISTRADOR ELIMINADO CON EXITO");
        }
    }

    public static void updateAdmin() throws ParseException {
        ControllerAdministrator.readAdmins();
        System.out.println(">¿QUE ADMINISTRADOR DESEAS EDITAR?");
        Scanner updateAdminIndex = new Scanner(System.in);
        int updateIndex = updateAdminIndex.nextInt();
        Scanner update = new Scanner(System.in);
        System.out.println(">¿QUE DESEAS EDITAR DEL ADMINISTRADOR?");
        System.out.println("\n>1) NOMBRE\n>2) APELLIDO\n>3) FECHA DE NACIMIENTO\n>4) PERMISOS\n>4) NOMBRE DE USUARIO\n>4) CONTRASEÑA");
        System.out.print(">SELECCIONA OPCION: ");
        int opcEdit = update.nextInt();
        update.nextLine();
        switch (opcEdit) {
            case 1 -> {
                System.out.print(">INGRESE EL NUEVO NOMBRE: ");
                admins.get(updateIndex - 1).getProfile().name = update.nextLine();
            }
            case 2 -> {
                System.out.print(">INGRESE EL NUEVO APELLIDO: ");
                admins.get(updateIndex - 1).getProfile().lastName = update.nextLine();
            }
            case 3 -> {
                System.out.print(">INGRESE LA NUEVA FECHA DE NACIMIENTO CON EL SIGUIENTE FORMATO DD/MM/YYYY: ");
                String newBirthdate = update.nextLine();
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                admins.get(updateIndex - 1).getProfile().birthdate = format.parse(newBirthdate);
            }
            case 4 -> {
                System.out.print(">INGRESE EL NUEVO NOMBRE DE USUARIO: ");
                admins.get(updateIndex - 1).setUserName(update.nextLine());
            }
            case 5 -> {
                System.out.print(">INGRESE LA NUEVA CONTRASEÑA: ");
                admins.get(updateIndex - 1).setPassword(update.nextLine());
            }
        }
    }
}