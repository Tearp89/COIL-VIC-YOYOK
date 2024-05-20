/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
/**
 *
 * @author daur0
 */
public class FieldValidator {
    
    public static boolean onlyText(String textFieldTrim){
        if(!textFieldTrim.isBlank()){
            return textFieldTrim.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+");
        } else {
            
            return false;
        }
    }
    
    public static boolean onlyNumber(String textFieldTrim){
        if(!textFieldTrim.isBlank()){
            return textFieldTrim.matches("\\d+");
        } else {
            return false;
        }
    }
    
    public static boolean isEmail(String email){
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            return true;
        } catch  (AddressException ex) {
            return false;
        }
    }

    public static boolean onlyTextAndNumbers(String textFieldTrim) {
        if (!textFieldTrim.isBlank()) {
            return textFieldTrim.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\d\\s]+");
        } else {
            return false;
        }
    }
}