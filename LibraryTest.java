package ProjectPOO;

import java.text.ParseException;

public class LibraryTest {
    public static void main(String[] args) throws ParseException {
        Seeder seeder = new Seeder();
        seeder.initialize();
        ControllerUser.login();
    }
}