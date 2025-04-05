package edu.guilford.cardgame.Backend.Accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.guilford.cardgame.Backend.Params.ParameterRecord;

import java.io.File;
import java.io.IOException;

public class AccountJSONManager {

    public static User loadUserFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), User.class);
    }

    public static void saveUserToJson(String filePath, User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), user);
    }

    public static boolean doesUserExist(User user) {
        File file = new File("src/main/resources/Users/" + user.getName() + ".json");
        return file.exists();
    }

    public static void updateUserParameterRecord(String filePath, ParameterRecord newRecord) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(new File(filePath), User.class);
        user.setParameterRecord(newRecord);
        mapper.writeValue(new File(filePath), user);
    }

    public static ParameterRecord getUserParameterRecord(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(new File(filePath), User.class);
            System.out.println("User parameter record: " + user.getParameterRecord());
            return user.getParameterRecord();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
