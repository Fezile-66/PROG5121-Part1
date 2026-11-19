
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import userregistration1.MessageStorage;

public class MessageStorageTest {

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        MessageStorage.loadTestData();
        assertTrue(MessageStorage.sentMessages.contains("Did you get the cake?"));
        assertTrue(MessageStorage.sentMessages.contains("It is dinner time !"));
    }

    @Test
    public void testDisplayLongestMessage() {
        MessageStorage.loadTestData();
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                MessageStorage.longestStoredMessage());
    }

    @Test
    public void testSearchByMessageID() {
        MessageStorage.loadTestData();
        String result = MessageStorage.searchByMessageID("0838884567");
        assertTrue(result.contains("It is dinner time !"));
    }

    @Test
    public void testSearchByRecipient() {
        MessageStorage.loadTestData();
        assertTrue(MessageStorage.searchByRecipient("+27838884567").size() >= 2);
    }

    @Test
    public void testDeleteByHash() {
        MessageStorage.loadTestData();
        String result = MessageStorage.deleteByHash("00:1:WHEREON");
        assertTrue(result.contains("successfully deleted"));
    }
}
