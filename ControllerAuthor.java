package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class ControllerAuthor implements Controller {
    static ArrayList<Author> authors = new ArrayList<>();
    public static void createAuthor() throws ParseException {
        Profile profile = new Profile();
        Author author;
        System.out.print(">INGRESE NOMBRE DEL AUTOR: ");
        Scanner nautor = new Scanner(System.in);
        profile.name = nautor.nextLine();
        System.out.print(">INGRESE APELLIDO DEL AUTOR: ");
        Scanner lastnautor = new Scanner(System.in);
        profile.lastName = lastnautor.nextLine();
        System.out.print(">INGRESE FECHA DE NACIMIENTO DEL AUTOR CON EL SIGUIENTE FORMATO DD/MM/YYYY: ");
        Scanner birthed = new Scanner(System.in);
        String birthdatee = birthed.nextLine();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        profile.birthdate = format.parse(birthdatee);
        author = new Author(profile);
        ControllerAuthor.authors.add(author);
        System.out.println(">PERFIL DE AUTOR CREADO CON EXITO");
    }
    public static void readerAuthors(){
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(">AUTOR " + (i+1) +" "+ authors.get(i).profile.name +" "+ authors.get(i).profile.lastName);
            System.out.println(">LIBROS DEL AUTOR ");
            ControllerAuthor.readerBooksAuthor(authors.get(i).profile);
        }
    }
    public static void deleteBookAuthor(Book deleteBook, Author author){
        ArrayList <Book> authorBook = new ArrayList<>();
        for (int i = 0; i < ControllerBook.books.size(); i++) {
            if(ControllerBook.books.get(i).author == author){
                authorBook.add(ControllerBook.books.get(i));
            }
        }
        for (int i = 0; i < authorBook.size(); i++) {
            if(deleteBook == authorBook.get(i)){
                authorBook.remove(i);
                break;
            }
        }
    }
    public static void readerBooksAuthor(Profile profile){
        for (int i = 0; i < ControllerBook.books.size(); i++) {
            if(ControllerBook.books.get(i).author.profile == profile){
                System.out.println(ControllerBook.books.get(i).title);
            }
        }
    }
    public static void deleteAuthor(){
        ControllerAuthor.readerAuthors();
        System.out.print(">¿QUE AUTOR DESEA ELIMINAR?: ");
        Scanner deleteAuthor = new Scanner(System.in);
        int indexDeleteAuthor = deleteAuthor.nextInt();
        if (authors.get(indexDeleteAuthor - 1).books.isEmpty()) {
            authors.remove(indexDeleteAuthor);
        }
        else{
            System.out.println(">NO PUEDES ELIMINAR A UN AUTOR CON LIBROS ACTIVOS");
        }
    }
    public static void editAuthor () throws ParseException {
        System.out.print(">¿QUE AUTOR DESEA EDITAR?: ");
        int editIndex, opc;
        Scanner editEE = new Scanner(System.in);
        editIndex = editEE.nextInt();
        System.out.println(">¿QUE DESEAS EDITAR DEL LIBRO?");
        System.out.println("\n>1) NOMBRE\n>2) APELLIDO\n>3) FECHA DE NACIMIENTO");
        Scanner editE = new Scanner(System.in);
        opc = editE.nextInt();
        if (opc == 1) {
            System.out.print(">INGRESE NUEVO NOMBRE: ");
            Scanner nmaa = new Scanner(System.in);
            String nma = nmaa.nextLine();
            ControllerAuthor.editAuthorName(editIndex, nma);
        } else if (opc == 2) {
            System.out.print(">INGRESE NUEVO APELLIDO: ");
            Scanner naa = new Scanner(System.in);
            String na = naa.nextLine();
            ControllerAuthor.editAuthorLastName(editIndex, na);
        } else if (opc == 3) {
            System.out.print(">INGRESE NUEVA FECHA DE NACIMIENTO CON EL SIGUIENTE FORMATO DD/MM/YYYY: ");
            Scanner nB = new Scanner(System.in);
            String nb = nB.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date nBirthdate = format.parse(nb);
            ControllerAuthor.editAuthorBirthdate(editIndex, nBirthdate);
        }
    }
    public static void editAuthorName(int editIndex, String name){
        authors.get(editIndex).profile.name=name;
        System.out.println(">NOMBRE EDITADO CON EXITO");
    }
    public static void editAuthorLastName(int editIndex, String lastName){
        authors.get(editIndex).profile.lastName=lastName;
        System.out.println(">APELLIDO EDITADO CON EXITO");
    }
    public static void editAuthorBirthdate(int editIndex, Date birthdate){
        authors.get(editIndex).profile.birthdate=birthdate;
        System.out.println("FECHA DE NACIMIENTO EDITADA CON EXITO");
    }

    @Override
    public void execute(User user) throws ParseException {
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
            Menu.printOptions(optionsAuthors);
            ConsoleReader reader = new ConsoleReader();
            IntValidator validatorOption = value -> value < optionsAuthors.size() + 1;
            opcAuthor = reader.readInteger(">SELECCIONA OPCION", validatorOption);
            if (opcAuthor >= 1 && opcAuthor <= optionsAuthors.size()) {
                String selectedOptionAuthor = optionsAuthors.get(opcAuthor - 1);
                switch (selectedOptionAuthor) {
                    case "AÑADIR AUTOR" -> {
                        System.out.println();
                        createAuthor();
                    }
                    case "EDITAR AUTOR" -> {
                        System.out.println();
                        editAuthor();
                    }
                    case "MOSTRAR AUTORES" -> {
                        System.out.println();
                        readerAuthors();
                    }
                    case "ELIMINAR AUTOR" -> {
                        System.out.println();
                        deleteAuthor();
                    }
                }
            } else {
                System.out.println("OPCION NO VALIDA");
            }
        } while (!optionsAuthors.get(opcAuthor - 1).equals("SALIR DEL MENU AUTORES"));
    }
}