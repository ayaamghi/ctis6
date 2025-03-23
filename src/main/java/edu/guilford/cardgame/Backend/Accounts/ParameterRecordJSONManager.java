package edu.guilford.cardgame.Backend.Accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.guilford.cardgame.Backend.Params.ParameterRecord;

import java.io.File;
import java.io.IOException;

public class ParameterRecordJSONManager {

    public static ParameterRecord loadFromJson(String filePath ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), ParameterRecord.class);
    }

    public static void saveToJson(String filePath, ParameterRecord record) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), record);
    }

}