/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
/**
 *
 * @author daur0
 */
public class FieldValidator {
    
    public static boolean onlyText(String textFieldTrim){
        textFieldTrim = textFieldTrim.trim();
        if(!textFieldTrim.isBlank()){
            return textFieldTrim.replaceAll("[.,-]", "") 
                               .matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+");
        } else {            
            return false;
        }
    }
    
    
    public static boolean onlyNumber(String textFieldTrim){
        textFieldTrim = textFieldTrim.trim();
        if(!textFieldTrim.isBlank()){
            return textFieldTrim.matches("\\d+");
        } else {
            return false;
        }
    }
    
    public static boolean isEmail(String email){
        email = email.trim();
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            return true;
        } catch  (AddressException ex) {
            return false;
        }
    }

    public static boolean onlyTextAndNumbers(String textFieldTrim) {
        textFieldTrim = textFieldTrim.trim();
        if (!textFieldTrim.isBlank()) {
            return textFieldTrim.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\d\\s]+");
        } else {
            return false;
        }
    }

    public static boolean isValidName(String textFieldTrim) {
        textFieldTrim = textFieldTrim.trim(); 
        if (!textFieldTrim.isBlank()) {
            
            return textFieldTrim.matches("([A-ZÁÉÍÓÚÑ][a-záéíóúñ]*[\\s\\-']?)+");
        } else {
            return false;
        }
    }

    public static boolean isValidDateRange(LocalDate startDate, LocalDate finishDate) {

        if (startDate == null || finishDate == null) {
            return false;
        }
        

        return !startDate.isAfter(finishDate);
    }

    public static String convertDateFormat(String dateStr, String fromFormat, String toFormat) {
        DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern(fromFormat);
        DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern(toFormat);
        
        try {
            LocalDate date = LocalDate.parse(dateStr, fromFormatter);
            return date.format(toFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + dateStr);
            return null;
        }
    }
    


}