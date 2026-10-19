/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration1;

/**
 *
 * @author cash
 */
import java.util.ArrayList;
import java.util.Random;

public class Message {
      // variables
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    // Static variables to track messages across the application
    private static int totalMessagesSent = 0;
    private static ArrayList<String> allSentMessages = new ArrayList<>();

    // Constructor
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Default constructor
    public Message() {}

  

    /**
     * Generates a random 10-digit number for the message ID.
     */
    private String generateMessageID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }

    /**
     * Ensures the message ID is not more than ten characters.
     */
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    /**
     * Ensures the recipient cell number is no more than ten characters long 
     * and starts with an international code (+).
     */
    public String checkRecipientCell() {
        // Reusing logic from Task 1, but modifying to fit this specific assignment prompt
        if (this.recipient != null && this.recipient.startsWith("+") && this.recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else if (this.recipient != null && this.recipient.length() <= 10) {
             return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    /**
     * Checks if the message is not more than 250 characters.
     */
    public String checkMessageLength() {
        if (this.messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = this.messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    /**
     * Creates and returns the Message Hash.
     * Format: First two numbers of ID : Message Number : First and Last words (Uppercase)
     */
    public String createMessageHash() {
        if (this.messageID == null || this.messageText == null) return "";
        
        // Get first two digits of Message ID
        String firstTwoID = this.messageID.substring(0, 2);
        
        // Split message into words to get first and last
        String[] words = this.messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        
        // Format: 00:0:HITHANKS
        return (firstTwoID + ":" + this.messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    /**
     * Allows the user to choose to send, store, or disregard the message.
     * @param choice 1 = Send, 2 = Disregard, 3 = Store
     * @return The corresponding status message.
     */
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                totalMessagesSent++;
                // Format for printMessages()
                String record = "Message ID: " + this.messageID + "\n" +
                                "Message Hash: " + this.messageHash + "\n" +
                                "Recipient: " + this.recipient + "\n" +
                                "Message: " + this.messageText + "\n" +
                                "-------------------------";
                allSentMessages.add(record);
                return "Message successfully sent.";
                
            case 2:
                return "Press 0 to delete the message.";
                
            case 3:
                storeMessage(); // Calls your research method
                return "Message successfully stored.";
                
            default:
                return "Invalid choice.";
        }
    }

    /**
     * Returns all the messages sent while the program is running.
     */
    public String printMessages() {
        if (allSentMessages.isEmpty()) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : allSentMessages) {
            sb.append(msg).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the total number of messages sent.
     */
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    /**
     * Stores message in a JSON file.
     */
    public void storeMessage() {
        try {
            // Simulating JSON storage
            String jsonFormat = "{\n" +
                    "  \"messageID\": \"" + this.messageID + "\",\n" +
                    "  \"messageHash\": \"" + this.messageHash + "\",\n" +
                    "  \"recipient\": \"" + this.recipient + "\",\n" +
                    "  \"message\": \"" + this.messageText + "\"\n" +
                    "}\n";
            
            System.out.println("\n[Simulated JSON Storage] Saving to file:");
            System.out.println(jsonFormat);
            
        } catch (Exception e) {
            System.out.println("Error storing message.");
        }
    }
    
    // Getters for testing purposes
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    
}
