package dk.easv.easvticket;

import dk.easv.easvticket.be.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = new User("Bob", "bob123", "bob@example.com", 1);
        lblName.setText(user.getName());
        lblUserName.setText(user.getUserName());
        lblEmail.setText(user.getEmail());
        String typeText = switch (user.getType()) { case 1 -> "Admin"; case 2 -> "Coordinator"; default -> "Unknown"; };
        lblType.setText(typeText);
    }
}
