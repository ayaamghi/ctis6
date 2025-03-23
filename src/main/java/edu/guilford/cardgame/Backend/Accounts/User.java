package edu.guilford.cardgame.Backend.Accounts;

public class User {

    private String name;
    private String password;
    private String parameterRecordPath;

    // No-arg constructor for Jackson
    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
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

    public String getParameterRecordPath() {
        return parameterRecordPath;
    }

    public void setParameterRecordPath(String parameterRecordPath) {
        this.parameterRecordPath = parameterRecordPath;
    }

    public void setName(String name) {
        this.name = name;
    }

}
