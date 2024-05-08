package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

    class ControllerClient implements Controller {
    public static void createClient () throws ParseException {
        Profile profile;
        System.out.print(">INGRESE NOMBRE DEL CLIENTE: ");
        Scanner addClient = new Scanner(System.in);
        String name = addClient.nextLine();
        System.out.print(">INGRESE APELLIDO DEL CLIENTE: ");
        String lastName = addClient.nextLine();
        System.out.print(">INGRESE FECHA DE NACIMIENTO CON EL SIGUIENTE FORMATO DD/MM/YYYY: ");
        String birthDate = addClient.nextLine();
        profile = new Profile(name, lastName, birthDate);
        ControllerUser.createClientUser(profile);
    }
    public static void readerAvailableClients(){
        System.out.printf("| %-20s |%n", "CLIENTE");
        for (int i = 0; i < ControllerUser.users.size(); i++) {
            if (ControllerUser.users.get(i) instanceof Client){
                if (ControllerUser.users.get(i).getProfile() != null && ((Client) ControllerUser.users.get(i)).borrowedBooks.size() < 3){
                    System.out.printf("| %-20s |%n", ControllerUser.users.get(i).getProfile().name);
                }
            }
        }
    }
    public static void readerClientsBooksBorrowed(){
        System.out.printf("| %-20s | | %-17s |%n", "CLIENTE", "LIBROS PRESTADOS");
        for (int i = 0; i < ControllerUser.users.size(); i++) {
            if (ControllerUser.users.get(i) instanceof Client) {
                System.out.printf("| %-20s | | %-17s |%n", ControllerUser.users.get(i).getProfile().name, (((Client) ControllerUser.users.get(i)).borrowedBooks.size()));
            }
        }
    }
    public static void deleteClient() {
        int deletedIndex;
        ArrayList <Client> clients1 = new ArrayList<>();
        ControllerTransaction.fillClient(clients1);
        System.out.print(">¿QUE CLIENTE DESEA ELIMINAR?: ");
        Scanner deletedClient = new Scanner(System.in);
        deletedIndex = deletedClient.nextInt();
        if (clients1.get(deletedIndex - 1).borrowedBooks.isEmpty()) {
            for (int i = 0; i < ControllerUser.users.size(); i++) {
                if (ControllerUser.users.get(i).getProfile().equals(clients1.get(deletedIndex - 1).profile)){
                    ControllerUser.users.remove(i);
                    break;
                }
            }
            System.out.println(">CLIENTE ELIMINADO CON EXITO");
        }else{
            System.out.println(">NO PUEDES ELIMINAR UN CLIENTE CON LIBROS PRESTADOS");
        }
    }
    public static void updateClient() throws ParseException {
        ArrayList <Client> updateClients = new ArrayList<>();
        Scanner updateClient = new Scanner(System.in);
        int i = 0;
        while (i < ControllerUser.users.size()) {
            if (ControllerUser.users.get(i) instanceof Client){
                updateClients.add((Client) ControllerUser.users.get(i));
            }
            i++;
        }
        System.out.printf("| %-20s |%n", "CLIENTE");
        for (Client client : updateClients) {
            System.out.printf("| %-20s |%n", client.profile.name);
        }
        System.out.print(">¿QUE CLIENTE DESEAS EDITAR?: ");
        int updateIndex = updateClient.nextInt();
        System.out.println(">¿QUE DESEAS EDITAR DEL CLIENTE?");
        System.out.println("\n>1) NOMBRE\n>2) APELLIDO\n>3) FECHA DE NACIMIENTO\n>4) NOMBRE DE USUARIO\n>5) CONTRASEÑA");
        System.out.print(">SELECCIONA OPCION: ");
        int opcClient = updateClient.nextInt();
        updateClient.nextLine();
        switch (opcClient){
            case 1 -> {
                System.out.print(">INGRESE EL NUEVO NOMBRE: ");
                updateClients.get(updateIndex - 1).profile.name = updateClient.nextLine();
                System.out.println(">NOMBRE EDITADO CORRECTAMENTE");
            }
            case 2 -> {
                System.out.print(">INGRESE EL NUEVO APELLIDO: ");
                updateClients.get(updateIndex - 1).profile.lastName = updateClient.nextLine();
                System.out.println(">APELLIDO EDITADO CORRECTAMENTE");
            }
            case 3 -> {
                System.out.print(">INGRESE LA NUEVA FECHA DE NACIMIENTO CON EL SIGUIENTE FORMATO: DD/MM/YYYY: ");
                String newBirthdate = updateClient.nextLine();
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                updateClients.get(updateIndex - 1).profile.birthdate = format.parse(newBirthdate);
                System.out.println("FECHA DE NACIMIENTO MODIFICADA CORRECTAMENTE");
            }
            case 4 -> {
                updateClients.get(updateIndex - 1).setUserName(ControllerUser.validUserName());
                System.out.println(">NOMBRE DE USUARIO EDITADO CORRECTAMENTE");
            }
            case 5 -> {
                updateClients.get(updateIndex - 1).setPassword(ControllerUser.validPassword());
                System.out.println(">CONTRASEÑA EDITADA CORRECTAMENTE");
            }
        }
        for (int j = 0; j < ControllerUser.users.size(); j++){
            if (ControllerUser.users.get(j).getProfile().equals(updateClients.get(updateIndex - 1).profile)){
                ControllerUser.users.set(j, updateClients.get(updateIndex - 1));
            }
        }
        updateClients.clear();
    }

        @Override
        public void execute(User user) throws ParseException {
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
                Menu.printOptions(options);
                ConsoleReader reader = new ConsoleReader();
                IntValidator validatorOption = value -> value < options.size() + 1;
                opcClient = reader.readInteger(">SELECCIONA OPCION", validatorOption);
                if (opcClient >= 1 && opcClient <= options.size()) {
                    String selectedOption = options.get(opcClient - 1);
                    switch (selectedOption) {
                        case "AÑADIR CLIENTE" -> {
                            System.out.println();
                            createClient();
                        }
                        case "EDITAR CLIENTE" -> {
                            System.out.println();
                            updateClient();
                        }
                        case "MOSTRAR CLIENTES" -> {
                            System.out.println();
                            readerClientsBooksBorrowed();
                        }
                        case "ELIMINAR CLIENTE" -> {
                            System.out.println();
                            deleteClient();
                        }
                    }
                } else {
                    System.out.println("OPCION NO VALIDA");
                }
            } while (!options.get(opcClient - 1).equals("SALIR DEL MENU CLIENTES"));
        }
    }