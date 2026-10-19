/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Login class handles user registration and authentication logic.
 * It validates usernames, passwords, and South African cell phone numbers.
 */
public class Login {

    // Instance variables to store registered user details
    private String storedUsername;
    private String storedPassword;
    private String userFirstName;
    private String userLastName;

    /**
     * Checks if the username contains an underscore and is no more than five characters long.
     * @param username The username to check
     * @return true if valid, false otherwise
     */
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    /**
     * Checks if the password meets complexity rules
     * @param password The password to check
     * @return true if valid, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        
        boolean hasLength = password.length() >= 8;
        boolean hasCapital = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return hasLength && hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Checks if the cell phone number contains the international country code
     * and is no more than ten characters long (excluding the '+').
     * @param phoneNumber The phone number to check
     * DeepSeek (2026) Regex pattern for South African cell phone validation. Available at: https://www.deepseek.com (Accessed: 18 September 2026)
     * @return true if valid, false otherwise
     */
    public boolean checkCellPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        
        // Regex: Starts with +, followed by 1 to 3 digits for country code, 
        // then 9 to 10 digits for the number. Total length check <= 13 chars.
        // For South Africa specifically, +27 followed by 9 digits.
        String regex = "^\\+[0-9]{1,3}[0-9]{9,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        
        return matcher.matches() && phoneNumber.length() <= 13;
    }

    /**
     * Registers the user if all checks pass.
     * @param username     
     * @param password     
     * @param phoneNumber     
     * @param firstName     
     * @param lastName     
     * @return      
     */
    public String registerUser(String username, String password, String phoneNumber, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "(Password is not correctly formatted, "
                    + "please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.)";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly fromatted or does not contain international code.";
        }

        // If all validations pass, store the details
        this.storedUsername = username;
        this.storedPassword = password;
        this.userFirstName = firstName;
        this.userLastName = lastName;

        return "User successfully registered.";
    }

    /**
     * Verifies login credentials from the stored details.
     * @param username the entered username
     * @param password the entered password
     * @return true if credentials match, false otherwise
     */
    public boolean loginUser(String username, String password) {
        if (username == null || password == null) return false;
        return username.equals(this.storedUsername) && password.equals(this.storedPassword);
    }

    /**
     * Returns the appropriate login status message.
     * @param loginStatus Result of the loginUser method
     * @return Welcome message or error message
     */
    public String returnLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            return "Welcome " + userFirstName + ", " + userLastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
