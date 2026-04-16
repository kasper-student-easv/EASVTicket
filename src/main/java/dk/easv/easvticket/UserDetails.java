package dk.easv.easvticket;

import dk.easv.easvticket.be.User;
import dk.easv.easvticket.util.DisplayError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserDetails implements Initializable {
    @FXML
    private Label lblName;
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblType;
    @FXML
    private VBox vboxEvents;
    private User user;
    private boolean change;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setUser(User user) {
        this.user = user;
        lblName.setText(user.getName());
        lblUserName.setText(user.getUserName());
        lblEmail.setText(user.getEmail());
        String typeText = switch (user.getType()) {
            case 1 -> "Admin";
            case 2 -> "Coordinator";
            default -> "Unknown";
        };
        lblType.setText(typeText);
    }



    @FXML
    private void onDelete(ActionEvent actionEvent) {
        this.user = null;
        change = true;
        Stage stage = (Stage) lblName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onEdit(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserForm.fxml"));
            Parent root = loader.load();
            UserForm controller = loader.getController();
            controller.setEditingUser(user);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            if (controller.getCreatedUser() != null) {
                change = true;
                setUser(controller.getCreatedUser());
            }
        } catch (IOException e) {
            DisplayError.showError(e);
        }
    }

    @FXML
    private void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) lblName.getScene().getWindow();
        stage.close();
    }

    public boolean getChange() {
        return change;
    }
    public User getUser() {
        return user;
    }
}
