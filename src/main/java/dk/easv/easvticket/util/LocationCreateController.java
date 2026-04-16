package dk.easv.easvticket.util;

import dk.easv.easvticket.be.Location;
import dk.easv.easvticket.bll.LocationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LocationCreateController {
    @FXML
    private TextField txtPostcode;
    @FXML
    private TextField txtRoadName;
    @FXML
    private TextField txtRoadNumber;
    private LocationManager locationManager = new LocationManager();
    private Location location = null;

    @FXML
    private void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) txtPostcode.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSave(ActionEvent actionEvent) {
        if (txtPostcode.getText() != null && txtRoadName.getText() != null && txtRoadNumber.getText() != null) {
            try {
                int postCode = Integer.parseInt(txtPostcode.getText());
                String roadName = txtRoadName.getText();
                int roadNumber = Integer.parseInt(txtRoadNumber.getText());
                Location newLocation = new Location(postCode, roadName, roadNumber);
                Location savedLocation = locationManager.createLocation(newLocation);
                if (savedLocation != null) {
                    location = savedLocation;
                    Stage stage = (Stage) txtPostcode.getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException e) {
                DisplayError.showError(new Exception("Postcode and Road Number must be numbers."));
            } catch (Exception e) {
                DisplayError.showError(e);
            }
        }
    }

    public Location getCreateLocation() {
        return location;
    }

}
