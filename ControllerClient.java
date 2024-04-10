package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

    class ControllerClient {
    static ArrayList<Client> clients = new ArrayList<>();
    public static void createClient () throws ParseException {
        Profile profile;
        Client client;
        System.out.print(">INGRESE NOMBRE DEL CLIENTE: ");
        Scanner addClient = new Scanner(System.in);
        String name = addClient.nextLine();
        System.out.print(">INGRESE APELLIDO DEL CLIENTE: ");
        String lastName = addClient.nextLine();
        System.out.print(">INGRESE FECHA DE NACIMIENTO CON EL SIGUIENTE FORMATO DD/MM/YYYY: ");
        String birthDate = addClient.nextLine();
        profile = new Profile(name, lastName, birthDate);
        client = new Client (profile);
        ControllerUser.createClientUser(profile);
        ControllerClient.addClients(client);
    }
    public static void addClients(Client client) {
        clients.add(client);
    }
    public static void readerAvailableClients(){
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).profile != null && clients.get(i).borrowedBooks.size() < 3){
                System.out.println(">CLIENTE " + (i + 1) + " " + clients.get(i).profile.name + " " + clients.get(i).profile.lastName);
            }
        }
    }
    public static void readerClientsBooksBorrowed(){
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(">CLIENTE " + (i + 1) + " " + clients.get(i).profile.name + " " + clients.get(i).profile.lastName);
            if (clients.get(i).borrowedBooks.isEmpty()) {
                System.out.println(">LIBROS PRESTADOS: 0");
            } else {
                System.out.println(">LIBROS PRESTADOS:");
                for (int k = 0; k < clients.get(i).borrowedBooks.size(); k++) {
                    System.out.println(clients.get(i).borrowedBooks.get(k).title);
                }
            }
        }
    }
    public static void readerClients() {
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(">CLIENTE " + (i + 1) + " " + clients.get(i).profile.name + " " + clients.get(i).profile.lastName);
        }
    }
    public static void deleteClient() {
        int deletedIndex;
        ControllerClient.readerClients();
        System.out.print(">¿QUE CLIENTE DESEA ELIMINAR?: ");
        Scanner deletedClient = new Scanner(System.in);
        deletedIndex = deletedClient.nextInt();
        if (clients.get(deletedIndex - 1).borrowedBooks.isEmpty()) {
            clients.remove(deletedIndex);
        }else{
            System.out.println(">NO PUEDES ELIMINAR UN CLIENTE CON LIBROS PRESTADOS");
        }
    }
    public static void updateClient() throws ParseException {
        Scanner updateClient = new Scanner(System.in);
        ControllerClient.readerClients();
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
                clients.get(updateIndex - 1).profile.name = updateClient.nextLine();
                System.out.println(">NOMBRE EDITADO CORRECTAMENTE");
            }
            case 2 -> {
                System.out.print(">INGRESE EL NUEVO APELLIDO: ");
                clients.get(updateIndex - 1).profile.lastName = updateClient.nextLine();
                System.out.println(">APELLIDO EDITADO CORRECTAMENTE");
            }
            case 3 -> {
                System.out.print(">INGRESE LA NUEVA FECHA DE NACIMIENTO CON EL SIGUIENTE FORMATO: DD/MM/YYYY: ");
                String newBirthdate = updateClient.nextLine();
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                clients.get(updateIndex - 1).profile.birthdate = format.parse(newBirthdate);
                System.out.println("FECHA DE NACIMIENTO MODIFICADA CORRECTAMENTE");
            }
            case 4 -> {
                System.out.print(">INGRESE EL NUEVO NOMBRE DE USUARIO: ");
                String newUserName = updateClient.nextLine();
                clients.get(updateIndex - 1).setUserName(newUserName);
                System.out.println(">NOMBRE DE USUARIO EDITADO CORRECTAMENTE");
            }
            case 5 -> {
                System.out.print(">INGRESE LA NUEVA CONTRASEÑA: ");
                String newPassword = updateClient.nextLine();
                clients.get(updateIndex - 1).setPassword(newPassword);
                System.out.println(">CONTRASEÑA EDITADA CORRECTAMENTE");
            }
        }
    }
}