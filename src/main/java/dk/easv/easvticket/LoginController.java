package dk.easv.easvticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController {


    @FXML
    private void onLogin(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("adminMain-view.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.sizeToScene();
        stage.setTitle("admin Main view");
    }
}
