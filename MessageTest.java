
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import userregistration1.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class MessageTest {
    
     @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Creates a string longer than 250 characters
        String longMessage = "A".repeat(260); 
        Message msg = new Message(1, "+27718693002", longMessage);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientFormatSuccess() {
        Message msg = new Message(1, "+27718693002", "Test");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFormatFailure() {
        Message msg = new Message(1, "0712345678", "Test"); // Missing international code
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        String generatedHash = msg.getMessageHash();
        
        // Verify it ends with :0:HITONIGHT
        
        assertTrue(generatedHash.endsWith(":0:HITONIGHT"));
        System.out.println("Generated Hash: " + generatedHash);
    }
    
    @Test
    public void testMessageIDCreated() {
        Message msg = new Message(1, "+27718693002", "Test");
        assertTrue(msg.checkMessageID());
        assertNotNull(msg.getMessageID());
    }

    @Test
    public void testSentMessageOptions() {
        Message msg = new Message(1, "+27718693002", "Test");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}