package ProjectPOO;

import java.util.Scanner;

class ConsoleReader {
    static Scanner scanner = new Scanner(System.in);
    public int readInteger(String prompt, IntValidator validator){
        boolean valid = false;
        int value = 0;
        while (!valid){
            System.out.print(prompt + ": ");
            value = scanner.nextInt();
            scanner.nextLine();
            valid = validator.validate(value);
        }
        return value;
    }
    public String readString(String prompt, StringValidator validator) {
        boolean valid = false;
        String value = "";
        while (!valid) {
            System.out.print(prompt + ": ");
            value = scanner.nextLine();
            valid = validator.validate(value);
        }
        return value;
    }
}