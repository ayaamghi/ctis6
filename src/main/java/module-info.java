module edu.guilford.cardgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.guilford.cardgame to javafx.fxml;
    exports edu.guilford.cardgame.Backend;
    opens edu.guilford.cardgame.Backend to javafx.fxml;
    exports edu.guilford.cardgame.GUI;
    opens edu.guilford.cardgame.GUI to javafx.fxml;
    exports edu.guilford.cardgame.Backend.Organisms;
    opens edu.guilford.cardgame.Backend.Organisms to javafx.fxml;
    exports edu.guilford.cardgame.Backend.Data;
    opens edu.guilford.cardgame.Backend.Data to javafx.fxml;
    opens edu.guilford.cardgame.Backend.Params to javafx.fxml;
    exports edu.guilford.cardgame.Backend.Params;
}