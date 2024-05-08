package ProjectPOO;

class RepositoryBook {
    public static void searchBookIsbn(String isbn){
        for (int i = 0; i < ControllerBook.books.size(); i++) {
            if (ControllerBook.books.get(i).isbn.equals(isbn)){
                System.out.println(">LIBRO ENCONTRADO" + ControllerBook.books.get(i).title);
            }
        }
    }
}