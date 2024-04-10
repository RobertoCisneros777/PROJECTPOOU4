package ProjectPOO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

class User {
    private Profile profile;
    private String userName;
    private String password;

    public User(Profile profile, String userName, String password) {
        this.profile = profile;
        this.userName = userName;
        this.password = hashString(password);
    }

    public User() {
    }

    public void setPassword(String password) {
        this.password = hashString(password);
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public static String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found.");
            return null;
        }
    }

    public boolean checkPassword(String inputPassword) {
        return Objects.equals(hashString(inputPassword), getPassword());
    }
}