package edu.guilford.cardgame.Backend.Accounts;

import edu.guilford.cardgame.Backend.Params.ParameterRecord;

import java.io.IOException;

public class AccountTest {

    public static void main(String[] args) throws IOException {
//        // Create a new user
//        User user = new User("testUser", "password123");
//
//        // Set the user's parameter record
//        user.setParameterRecord(new ParameterRecord());
//
//        // Print the user's name and password
//        System.out.println("User Name: " + user.getName());
//        System.out.println("User Password: " + user.getPassword());
//
//        // Print the user's parameter record
//        System.out.println("User Parameter Record: " + user.getParameterRecord());
//
//        // Save the user to a JSON file
        User user = AccountJSONManager.loadUserFromJson("src/main/resources/Users/testUser.json");
         String filePath = "src/main/resources/Users/" + user.getName() + ".json";
        System.out.println("Loaded user from JSON");
//        try {
//            AccountJSONManager.saveUserToJson(filePath, user);
//            System.out.println("User saved to JSON file: " + filePath);
//        } catch (IOException e) {
//            System.err.println("Error saving user to JSON file: " + e.getMessage());
//        }

        // Update the user's parameter record

        ParameterRecord parameterRecord = AccountJSONManager.getUserParameterRecord(filePath);

        System.out.println("User parameter record loaded without mods " + parameterRecord);

        ParameterRecord newRecord = new ParameterRecord();
        newRecord.setLowerBoundNumPlantsToChew(100);
        System.out.println("New record after mod " +  newRecord);
        AccountJSONManager.updateUserParameterRecord(filePath, newRecord);

        System.out.println("User parameter record updated to " + AccountJSONManager.getUserParameterRecord(filePath));

//        // Load the user from the JSON file
//        try {
//            User loadedUser = AccountJSONManager.loadUserFromJson(filePath);
//            System.out.println("Loaded User Name: " + loadedUser.getName());
//            System.out.println("Loaded User Password: " + loadedUser.getPassword());
//            System.out.println("Loaded User Parameter Record: " + loadedUser.getParameterRecord());
//        } catch (IOException e) {
//            System.err.println("Error loading user from JSON file: " + e.getMessage());
//        }


    }
}
