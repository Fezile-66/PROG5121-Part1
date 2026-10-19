/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package userregistration1;


import java.util.Scanner;

public class UserRegistration1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("                                 ");
        System.out.println("              ----- REGISTRATION PAGE ------");
        System.out.println("                                 ");
        System.out.println("                                 ");
        
        // Registration page input
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Username (must contain '_' and be less or eqaul to 5 characters): ");
        String username = scanner.nextLine();

        System.out.print("Enter Password (8+ characters, Capital, Number, Special): ");
        String password = scanner.nextLine();

        System.out.print("Enter SA Cell Phone (e.g., +27838968976): ");
        String cellNumber = scanner.nextLine();

        // Registration process
        String registrationMessage = loginSystem.registerUser(username, password, cellNumber, firstName, lastName);
        System.out.println("\nRegistration Status: " + registrationMessage);

        // Only proceed to login if registration was successful
        if (registrationMessage.equals("User successfully registered.")) {
            
            System.out.println("                                         ");
            System.out.println("             ------ LOGIN PAGE   -----            ");
            System.out.println("                                         ");
            System.out.println("                                         ");
            
            // Login page input
            System.out.print("Enter Username to Login: ");
            String loginUser = scanner.nextLine();

            System.out.print("Enter Password to Login: ");
            String loginPass = scanner.nextLine();

            // Process login
            boolean isAuthenticated = loginSystem.loginUser(loginUser, loginPass);
            String loginMessage = loginSystem.returnLoginStatus(isAuthenticated);
            
            System.out.println("\nLogin Status: " + loginMessage);
             if (isAuthenticated) {
                System.out.println("\nWelcome to QuickChat.");
                runQuickChat(scanner); }
        } else {
            System.out.println("\nPlease fix the errors above and try registering again.");
        }

        scanner.close();
    }
      // Method to handle the QuickChat menu loop
    private static void runQuickChat(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- QuickChat Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            String menuChoice = scanner.nextLine();

            switch (menuChoice) {
                case "1":
                    sendMessages(scanner);
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to handle sending messages
    private static void sendMessages(Scanner scanner) {
        System.out.print("How many messages do you wish to enter? ");
        int numMessages;
        try {
            numMessages = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Returning to menu.");
            return;
        }

        // Array to hold the messages for this session
        Message[] sessionMessages = new Message[numMessages];

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
            
            // 1. Get Recipient
            System.out.print("Enter recipient cell number (+27...): ");
            String recipient = scanner.nextLine();
            
            // 2. Get Message
            System.out.print("Enter message (max 250 chars): ");
            String text = scanner.nextLine();

            // 3. Create Message Object (Message number starts at 1, so i+1)
            Message msg = new Message(i + 1, recipient, text);

            // 4. Validate
            String recipientStatus = msg.checkRecipientCell();
            String lengthStatus = msg.checkMessageLength();
            boolean idValid = msg.checkMessageID();

            System.out.println("\nValidation Results:");
            System.out.println("- ID Check: " + (idValid ? "Valid" : "Invalid"));
            System.out.println("- Recipient: " + recipientStatus);
            System.out.println("- Length: " + lengthStatus);

            // Only allow sending if validations pass
            if (recipientStatus.contains("successfully") && lengthStatus.equals("Message ready to send.") && idValid) {
                System.out.println("\nMessage Generated Details:");
                System.out.println("Message ID: " + msg.getMessageID());
                System.out.println("Message Hash: " + msg.getMessageHash());
                System.out.println("Recipient: " + recipient);
                System.out.println("Message: " + text);

                System.out.println("\nOptions:");
                System.out.println("1 - Send Message");
                System.out.println("2 - Disregard Message");
                System.out.println("3 - Store Message to send later");
                System.out.print("Select an option: ");
                
                String actionChoice = scanner.nextLine();
                int actionInt = 0;
                
                // Breaks down the user's choice
                try {
                    actionInt = Integer.parseInt(actionChoice);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }

                // Call sentMessage method
                String result = msg.sentMessage(actionInt);
                System.out.println(result);
                
                // Store in array for local tracking if they chose to keep it
                if (actionInt == 1 || actionInt == 3) {
                    sessionMessages[i] = msg;
                }

            } else {
                System.out.println("Message could not be processed due to validation errors.");
            }
        }
        
        // Display total messages sent at the end of the batch
      // Check if we actually sent messages before calling the method
 Message tracker = new Message();
        System.out.println("\nTotal messages sent this session: " + tracker.returnTotalMessages());}
} 
    