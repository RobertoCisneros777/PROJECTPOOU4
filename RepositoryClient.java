package ProjectPOO;

class RepositoryClient {
    public static void searchClientBorrowedBook(Book book){
        for (int i = 0; i < ControllerUser.users.size(); i++) {
            if(ControllerUser.users.get(i) instanceof Client && ((Client) ControllerUser.users.get(i)).borrowedBooks.contains(book)){
                System.out.println(">CLIENTE ENCONTRADO" + ControllerUser.users.get(i).getProfile().name);
            }
        }
    }
}