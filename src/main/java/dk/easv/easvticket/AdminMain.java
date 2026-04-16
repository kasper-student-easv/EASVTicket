package dk.easv.easvticket;

import dk.easv.easvticket.be.User;
import dk.easv.easvticket.bll.UserManager;
import dk.easv.easvticket.util.DisplayError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminMain implements Initializable {

    @FXML
    private TilePane tilePane;
    private UserManager userManager;
    List<User> userList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userManager = new UserManager();
        setOpScrollPane();
    }

    public void setOpScrollPane() {
        tilePane.getChildren().clear();
        try {
            userList = userManager.getAllUser();
        } catch (Exception e) {
            DisplayError.showError(e);
        }
        for(User user :userList) {
            addUserToTilePane(user);
        }
    }

    @FXML
    private void onCreateUser(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("userForm-view.fxml"));
            Parent root = loader.load();
            UserForm controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("User Form");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            User result = controller.getCreatedUser();
            if (result != null) {
                addUserToTilePane(result);
            }

        } catch (IOException ex) {
            DisplayError.showError(new IOException("fail to get userForm",ex));
        }
    }
    private void addUserToTilePane(User user) {
        VBox userBox = new VBox(5);
        userBox.getStyleClass().add("contentCart");
        populateUserBox(userBox, user);
        TilePane.setMargin(userBox, new Insets(10));
        tilePane.getChildren().add(userBox);
    }
    private void populateUserBox(VBox userBox, User user) {
        userBox.getChildren().clear();
        Label nameLabel = new Label("Name: " + user.getName());
        Label usernameLabel = new Label("Username: " + user.getUserName());
        Label emailLabel = new Label("Email: " + user.getEmail());
        String typeText = switch (user.getType()) {
            case 1 -> "Admin";
            case 2 -> "Coordinator";
            default -> "Unknown";
        };
        Label typeLabel = new Label("Type: " + typeText);
        Button detailsBtn = new Button("Details");
        detailsBtn.getStyleClass().add("contentMenuBtn");
        Button deleteBtn = new Button("Delete");
        deleteBtn.getStyleClass().add("contentDeleteBtn");
        HBox buttonRow = new HBox(10);
        buttonRow.getStyleClass().add("contentBackground");
        buttonRow.getChildren().addAll(detailsBtn, deleteBtn);
        userBox.getChildren().addAll(nameLabel, usernameLabel, emailLabel, typeLabel, buttonRow);
        detailsBtn.setOnAction(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("userDetails-view.fxml"));
                Parent root = fxmlLoader.load();
                UserDetails controller = fxmlLoader.getController();
                controller.setUser(user);
                Stage stage = new Stage();
                stage.setTitle("User Details");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                if (controller.getChange()) {
                    User resultUser = controller.getUser();
                    if (resultUser == null) {
                        try {
                            userManager.deleteUser(user);
                            tilePane.getChildren().remove(userBox);
                        } catch (Exception ex) {
                            DisplayError.showError(ex);
                        }
                    } else {
                        populateUserBox(userBox, resultUser);
                    }
                }

            } catch (IOException ex) {
                DisplayError.showError(new Exception("fail to get details", ex));
            }
        });
        deleteBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete User");
            alert.setHeaderText("Are you sure you want to delete this user?");
            alert.setContentText("This action cannot be undone.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    userManager.deleteUser(user);
                    tilePane.getChildren().remove(userBox);
                } catch (Exception ex) {
                    DisplayError.showError(ex);
                }
            }
        });
    }


    @FXML
    private void onGetUsers(ActionEvent actionEvent) {
        setOpScrollPane();
    }

    @FXML
    private void onGetEvens(ActionEvent actionEvent) {
        setOpEvents();
    }
    private void setOpEvents() {
        tilePane.getChildren().clear();

    }
}
