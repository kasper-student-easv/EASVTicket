package dk.easv.easvticket.bll;

import dk.easv.easvticket.be.Location;
import dk.easv.easvticket.dal.ILocation;
import dk.easv.easvticket.dal.LocationDAO;

import java.util.List;

public class LocationManager {
    private final ILocation locationDAO = new LocationDAO();

    public List<Location> getAllLocation() throws Exception {
        return locationDAO.getAllLocation();
    }

    public Location createLocation(Location location) throws Exception {
        return locationDAO.createLocation(location);
    }

}
