/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
/**
 *
 * @author daur0
 */
public class FieldValidator {
    
    public static boolean onlyText(String textFieldTrim){
        if(textFieldTrim != null){
            textFieldTrim = textFieldTrim.trim();
        }
        if(!textFieldTrim.isBlank()){
            return textFieldTrim.replaceAll("[.,-]", "") 
                               .matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+");
        } else {            
            return false;
        }
    }
    
    
    public static boolean onlyNumber(String textFieldTrim){
        if(textFieldTrim != null){
            textFieldTrim = textFieldTrim.trim();
        }
        if(!textFieldTrim.isBlank()){
            return textFieldTrim.matches("\\d+");
        } else {
            return false;
        }
    }
    
    public static boolean isEmail(String email){
        if(email != null){
            email = email.trim();
        }
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            return true;
        } catch  (AddressException ex) {
            return false;
        }
    }

    public static boolean onlyTextAndNumbers(String textFieldTrim) {
        if(textFieldTrim != null){
            textFieldTrim = textFieldTrim.trim();
        }
        if (!textFieldTrim.isBlank()) {
            return textFieldTrim.replaceAll("[.,-]", "")
            .matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\d\\s]+");
        } else {
            return false;
        }
    }

    public static boolean isValidName(String textFieldTrim) {
        if(textFieldTrim != null){
            textFieldTrim = textFieldTrim.trim();
        } 
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

    
    public static boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Pattern digitPattern = Pattern.compile("[0-9]");
        Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher upperCaseMatcher = upperCasePattern.matcher(password);
        Matcher lowerCaseMatcher = lowerCasePattern.matcher(password);
        Matcher digitMatcher = digitPattern.matcher(password);
        Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);

        return upperCaseMatcher.find() && lowerCaseMatcher.find() && digitMatcher.find() && specialCharacterMatcher.find();
    }
    


}