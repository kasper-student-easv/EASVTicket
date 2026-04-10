package dk.easv.easvticket;

import dk.easv.easvticket.be.User;
import dk.easv.easvticket.be.util.Encrypter;
import dk.easv.easvticket.bll.UserManager;
import dk.easv.easvticket.util.DisplayError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserForm implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> cmbType;
    private UserManager userManager = new UserManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbType.getItems().addAll("Admin", "Coordinator");
    }

    @FXML
    private void onSave(ActionEvent actionEvent) {
        if(txtName.getText() != null && txtUserName.getText() != null && txtPassword.getText() != null && txtEmail.getText() != null && cmbType.getValue() != null) {
            String hashedPassword = Encrypter.hashPassword(txtPassword.getText());
            int type = cmbType.getValue().equals("Admin") ? 1 : 2;
            User user = new User(
                    txtName.getText(),
                    txtUserName.getText(),
                    hashedPassword,
                    txtEmail.getText(),
                    type
            );
            User newUser = null;
            try {
                newUser = userManager.createUser(user);
            } catch (Exception e) {
                DisplayError.showError(e);
            }
            if(newUser != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("User created successfully!");
                alert.showAndWait();

                Stage stage = (Stage) txtName.getScene().getWindow();
                stage.close();
            }
        }
    }
}
