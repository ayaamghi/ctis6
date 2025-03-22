package edu.guilford.cardgame.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class ThirtyOneGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/guilford/cardgame/instantiate-game.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("printing");


        if(Screen.getScreens().size() == 2) {
            // Get the list of screens
            Screen secondScreen = Screen.getScreens().get(1); // Assuming the second screen is at index 1
            Rectangle2D bounds = secondScreen.getVisualBounds();
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMaxY() / 4);
        }
        else {
            stage.setX(0);
            stage.setY(0);

            stage.setWidth(600);
            stage.setHeight(400);

        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}