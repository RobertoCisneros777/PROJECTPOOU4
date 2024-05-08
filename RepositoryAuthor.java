package ProjectPOO;

class RepositoryAuthor {
    public static void searchAuthorName(String name){
        for (int i = 0; i < ControllerAuthor.authors.size(); i++) {
            if(ControllerAuthor.authors.get(i).profile.name.equals(name)){
                System.out.print(">AUTOR ENCONTRADO" + ControllerAuthor.authors.get(i));
            }
        }
    }
}