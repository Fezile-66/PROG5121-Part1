/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import userregistration1.Login;

public class LoginTest {

    Login login = new Login();

    // Registration Feature Tests (AssertEquals)

    @Test
    public void testUsernameCorrectlyFormatted_ReturnsTrue() {
        // Test Data: "kyl_1"
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted_ReturnsFalse() {
        // Test Data: "kyle!!!!!!!"
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity_ReturnsTrue() {
        // Test Data: "Ch&&sec@ke99!"
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity_ReturnsFalse() {
        // Test Data: "password"
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormatted_ReturnsTrue() {
        // Test Data: +27838968976
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted_ReturnsFalse() {
        // Test Data: 08966553
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // --- Login Feature Tests (AssertTrue/False) ---

    @Test
    public void testLoginSuccessful_ReturnsTrue() {
        // Register user first
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        // Verify login
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed_ReturnsFalse() {
        // Register user first
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        // Verify login with wrong password
        assertFalse(login.loginUser("kyl_1", "WrongPassword123!"));
    }
    
    // Method Testing for Registration Messages (AssertEquals)
    
    @Test
    public void testRegisterUserSuccessMessage() {
        String expected = "User successfully registered.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals(expected, actual);
    }

    @Test
    public void testRegisterUserUsernameErrorMessage() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals(expected, actual);
    }
}