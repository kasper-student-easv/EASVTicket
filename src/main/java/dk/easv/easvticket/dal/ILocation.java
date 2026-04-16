package dk.easv.easvticket.dal;

import dk.easv.easvticket.be.Location;

import java.util.List;

public interface ILocation {

    List<Location> getAllLocation() throws Exception;

    Location createLocation(Location location) throws Exception;
}
