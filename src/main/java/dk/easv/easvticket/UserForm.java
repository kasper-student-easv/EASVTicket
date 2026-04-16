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
    private User createUser = null;
    private User editingUser = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbType.getItems().addAll("Admin", "Coordinator");
    }

    @FXML
    private void onSave(ActionEvent actionEvent) {
        if(editingUser == null) {
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
                    createUser = newUser;
                    Stage stage = (Stage) txtName.getScene().getWindow();
                    stage.close();
                }
            }
            else {
                editingUser.setName(txtName.getText());
                editingUser.setUserName(txtUserName.getText());
                editingUser.setEmail(txtEmail.getText());
                editingUser.setType(cmbType.getValue().equals("Admin") ? 1 : 2);
                try {
                    userManager.updateUser(editingUser);
                    createUser = editingUser;
                } catch (Exception e) {
                    DisplayError.showError(e);
                }
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();
            }
        }

    }

    public User getCreatedUser() {
        return createUser;
    }

    @FXML
    private void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }

    public void setEditingUser(User user) {
        this.editingUser = user;
        txtName.setText(user.getName());
        txtUserName.setText(user.getUserName());
        txtEmail.setText(user.getEmail());
        cmbType.setValue(user.getType() == 1 ? "Admin" : "Coordinator");
        txtPassword.setVisible(false);
        txtPassword.setManaged(false);
    }
}
