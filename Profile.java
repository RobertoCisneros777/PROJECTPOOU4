package ProjectPOO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Profile {
    String name;
    String lastName;
    Date birthdate;
    public Profile(){
    }
    public Profile (String name, String lastName, String birthDate) throws ParseException {
        this.name = name;
        this.lastName = lastName;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.birthdate = format.parse(birthDate);
    }
}