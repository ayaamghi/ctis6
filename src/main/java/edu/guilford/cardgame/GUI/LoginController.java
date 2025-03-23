package edu.guilford.cardgame.GUI;

import edu.guilford.cardgame.Backend.Accounts.AccountJSONManager;
import edu.guilford.cardgame.Backend.Accounts.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginController {


    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private String username;

    @FXML
    private Button loginButton;

    private String password;

    @FXML
    private Label incorrectPasswordText;


    private User user;


    @FXML
    public void initialize() {

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            username = newValue;
            System.out.println("Name changed to: " + username);
            checkForLoginDisable();
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            password = newValue;
            checkForLoginDisable();
        });


        loginButton.setOnMouseClicked(event -> {
            user = new User(username, password);
            if (AccountJSONManager.doesUserExist(user)) {
                try {
                    user = AccountJSONManager.loadUserFromJson("src/main/resources/Users/" + username + ".json");
                    if (user.getPassword().equals(password)) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/guilford/cardgame/simulation-page.fxml"));
                            Parent root = fxmlLoader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) loginButton.getScene().getWindow();
                            stage.setScene(scene);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        incorrectPasswordText.setVisible(true);
                    }
                } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            else {
                try {
                    AccountJSONManager.saveUserToJson("src/main/resources/Users/" + username + ".json", user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Saved user to json file");

            }

        });
    }

        private void checkForLoginDisable() {
            boolean shouldDisable = username == null || username.isEmpty() || password == null || password.isEmpty();
            loginButton.setDisable(shouldDisable);
        }
    }
