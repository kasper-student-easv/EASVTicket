package dk.easv.easvticket.dal;

import dk.easv.easvticket.be.Location;
import dk.easv.easvticket.be.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements ILocation{
    @Override
    public List<Location> getAllLocation() throws Exception {
        List<Location> locations = new ArrayList<>();
        try (Connection conn = DBConnector.getStaticConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Locations";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                int postcode = rs.getInt("Postcode");
                String roadName = rs.getString("RoadName");
                int roadNumber = rs.getInt("RoadNumber");
                locations.add(new Location(id, postcode, roadName, roadNumber));
            }
        } catch (Exception e) {
            throw new Exception("something has gone wrong in getting locations", e);
        }
        return locations;
    }

    @Override
    public Location createLocation(Location location) throws Exception {
        try(Connection conn = DBConnector.getStaticConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Locations (Postcode,RoadName,RoadNumber) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,location.getPostCode());
            ps.setString(2,location.getRoadName());
            ps.setInt(3,location.getRoadNumber());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                int newId = rs.getInt(1);
                return new Location(newId, location.getPostCode(),location.getRoadName(),location.getRoadNumber());
            }
            else {
                throw new Exception("Could not create location in the database");
            }
        } catch (Exception e) {
            throw new Exception("Could not create location in the database",e);
        }
    }
}
