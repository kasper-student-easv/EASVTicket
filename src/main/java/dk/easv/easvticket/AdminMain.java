package dk.easv.easvticket;

import dk.easv.easvticket.be.User;
import dk.easv.easvticket.bll.UserManager;
import dk.easv.easvticket.util.DisplayError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
        try {
            userList = userManager.getAllUser();
        } catch (Exception e) {
            DisplayError.showError(e);
        }


        for(User user :userList) {
            VBox userBox = new VBox(5); userBox.getStyleClass().add("contentCart");
            Label nameLabel = new Label("Name: " + user.getName());
            Label usernameLabel = new Label("Username: " + user.getUserName());
            Label emailLabel = new Label("Email: " + user.getEmail());
            String typeText = switch (user.getType()) { case 1 -> "Admin"; case 2 -> "Coordinator"; default -> "Unknown"; };
            Label typeLabel = new Label("Type: " + typeText);
            Button detailsBtn = new Button("Details");
            detailsBtn.getStyleClass().add("contentMenuBtn");
            detailsBtn.setOnAction(e -> {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("userDetails-view.fxml"));
                Stage stage = new Stage();
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                stage.setTitle("user Details");
                stage.setScene(scene);
                stage.show(); });
            Button deleteBtn = new Button("Delete");
            deleteBtn.getStyleClass().add("contentDeleteBtn");
            HBox buttonRow = new HBox(10);
            buttonRow.getStyleClass().add("contentBackground");
            buttonRow.getChildren().addAll(detailsBtn, deleteBtn);
            userBox.getChildren().addAll( nameLabel, usernameLabel, emailLabel, typeLabel, buttonRow );
            TilePane.setMargin(userBox, new Insets(10));
            tilePane.getChildren().add(userBox);
        }
    }

    @FXML
    private void onCreateUser(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("userForm-view.fxml"));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        stage.setTitle("user Form");
        stage.setScene(scene);
        stage.show();
    }
}
