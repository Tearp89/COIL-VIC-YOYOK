package logic;

import java.security.SecureRandom;
import java.util.Random;

public class Access {

    public String userGenerator(String fullName){
        String[] partes = fullName.split("\\s+");
        String name = partes[0];
        String lastName = partes[partes.length - 1];
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        return name.toLowerCase() + "_" + lastName.toLowerCase() + randomNumber;
    }


    public final  String charLower = "abcdefghijklmnopqrstuvwxyz";
    public final  String charUpper = charLower.toUpperCase();
    public final  String digit = "0123456789";
    public final  String specialChar = "!@#$%&*";
    public final  String allChar = charLower + charUpper + digit + specialChar;
    public final  SecureRandom random = new SecureRandom();
    
    public String passwordGenerator(int length){

    
        StringBuilder password = new StringBuilder(length);
        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;

        for (int i = 0; i < length; i++) {
            char randomChar = allChar.charAt(random.nextInt(allChar.length()));
            password.append(randomChar);

            if (charLower.indexOf(randomChar) != -1) hasLower = true;
            else if (charUpper.indexOf(randomChar) != -1) hasUpper = true;
            else if (digit.indexOf(randomChar) != -1) hasDigit = true;
            else if (specialChar.indexOf(randomChar) != -1) hasSpecial = true;
        }
        
        if (!hasLower || !hasUpper || !hasDigit || !hasSpecial) {
            return passwordGenerator(length);

        }
        return password.toString();
    }
}
