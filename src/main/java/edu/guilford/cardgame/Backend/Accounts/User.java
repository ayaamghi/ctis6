package edu.guilford.cardgame.Backend.Accounts;

import edu.guilford.cardgame.Backend.Params.ParameterRecord;

public class User {

    private String name;
    private String password;
    private ParameterRecord parameterRecord;

    // No-arg constructor for Jackson
    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, ParameterRecord parameterRecord) {
        this.name = name;
        this.password = password;
        this.parameterRecord = parameterRecord;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
     }

    public ParameterRecord getParameterRecord() {
        return parameterRecord;
    }

    public void setParameterRecord(ParameterRecord parameterRecord) {
        this.parameterRecord = parameterRecord;
    }

    public void setName(String name) {
        this.name = name;
    }

}
