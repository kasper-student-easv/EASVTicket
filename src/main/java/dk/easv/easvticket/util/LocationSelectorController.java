package dk.easv.easvticket.util;

import dk.easv.easvticket.be.Location;
import dk.easv.easvticket.bll.LocationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LocationSelectorController implements Initializable {
    @FXML
    private TextField txtFilter;
    @FXML
    private TableView<Location> tbvLocation;
    @FXML
    private TableColumn tbvCollumPostcode;
    @FXML
    private TableColumn tbvCollumRoadName;
    @FXML
    private TableColumn tbvCollumRoadNumber;
    @FXML
    private Button btnSelectLocation;
    private LocationManager locationManager = new LocationManager();
    private Location selectLocation = null;
    private ObservableList<Location> locationObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbvCollumPostcode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        tbvCollumRoadName.setCellValueFactory(new PropertyValueFactory<>("roadName"));
        tbvCollumRoadNumber.setCellValueFactory(new PropertyValueFactory<>("roadNumber"));
        try {
            locationObservableList.addAll(locationManager.getAllLocation());
        } catch (Exception e) {
            DisplayError.showError(e);
        }
        FilteredList<Location> filteredData = new FilteredList<>(locationObservableList, p -> true);
        txtFilter.textProperty().addListener((obs, oldValue, newValue) -> {
            filteredData.setPredicate(locationObj -> {
                if (newValue == null || newValue.isEmpty())
                    return true;
                String filter = newValue.toLowerCase();
                return String.valueOf(locationObj.getPostCode()).contains(filter)
                        || locationObj.getRoadName().toLowerCase().contains(filter)
                        || String.valueOf(locationObj.getRoadNumber()).contains(filter);
            });
        });
        SortedList<Location> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbvLocation.comparatorProperty());
        tbvLocation.setItems(sortedData);
    }

    @FXML
    private void onCreateLocation(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LocationCreate.fxml"));
            Parent root = loader.load();
            LocationCreateController controller = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Create Location");
            stage.showAndWait();
            Location newLocation = controller.getCreateLocation();
            if (newLocation != null) {
                locationObservableList.add(newLocation);
            }
        } catch (Exception e) {
            DisplayError.showError(e);
        }
    }

    @FXML
    private void onSelectLocation(ActionEvent actionEvent) {
        if(!tbvLocation.getSelectionModel().isEmpty()) {
            selectLocation = tbvLocation.getSelectionModel().getSelectedItem();
            Stage stage = (Stage) txtFilter.getScene().getWindow();
            stage.close();
        }
    }

    public Location getSelectLocation() {
        return selectLocation;
    }
}
