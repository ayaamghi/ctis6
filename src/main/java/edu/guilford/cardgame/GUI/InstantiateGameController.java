package edu.guilford.cardgame.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InstantiateGameController {

    @FXML
    private AnchorPane gameStartPane;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numCPUField;

    @FXML
    private Button gameStartButton;

    private int numCPU;
    private String name;


    @FXML
    public void initialize() {

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            name = newValue;
            System.out.println("Name changed to: " + name);
            checkFields();
        });

        numCPUField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                numCPU = Integer.parseInt(newValue);
                System.out.println("NumCPU changed to: " + numCPU);
            } catch (NumberFormatException e) {
                numCPU = 0;
                System.out.println("Invalid number format, setting numCPU to 0");
            }
            checkFields();
        });

        gameStartButton.setOnAction(event -> {
            System.out.println("Button clicked!");
            System.out.println("Name: " + name + ", Number of CPU Players: " + numCPU);
        });

        // Initialize fields
        name = "";
        numCPU = 0;
        checkFields();

        gameStartButton.setOnMouseClicked (event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/guilford/cardgame/game-view.fxml"));

            Parent root;
            try {
                root = fxmlLoader.load();
                Stage stage = (Stage) gameStartButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setX(0);
                stage.setY(0);


            } catch (Exception e) {
                e.printStackTrace();
            }

        });


    }

    private void checkFields() {
        boolean shouldDisable = name == null || name.isEmpty() || numCPU <= 0;
        gameStartButton.setDisable(shouldDisable);
        System.out.println("Button disabled state updated to: " + shouldDisable);
    }
}