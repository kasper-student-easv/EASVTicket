package dk.easv.easvticket.dal;

import dk.easv.easvticket.be.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEvent {

    @Override
    public List<Event> getAllEvent() throws Exception {
        List<Event> events = new ArrayList<>();

        try (Connection conn = DBConnector.getStaticConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM Event";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                LocalDateTime time = rs.getTimestamp("Time").toLocalDateTime();
                int locationId = rs.getInt("LocationId");
                String notes = rs.getString("Notes");
                Timestamp endTs = rs.getTimestamp("EndTime");
                LocalDateTime end = endTs != null ? endTs.toLocalDateTime() : null;
                String arrivalMethod = rs.getString("ArrivalMethod");
                events.add(new Event(id, name, time, locationId, notes, end, arrivalMethod));
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong while getting Events", e);
        }
        return events;
    }

    @Override
    public Event createEvent(Event event) throws Exception {
        String sql = "INSERT INTO Event (Name, Time, LocationId, Notes, EndTime, ArrivalMethod) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnector.getStaticConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, event.getName());
            ps.setTimestamp(2, Timestamp.valueOf(event.getTime()));
            ps.setInt(3, event.getLocationId());
            ps.setString(4, event.getNote());
            ps.setTimestamp(5, event.getEnd() != null ? Timestamp.valueOf(event.getEnd()) : null);
            ps.setString(6, event.getArrivalMethod());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                return new Event(newId, event.getName(), event.getTime(), event.getLocationId(), event.getNote(), event.getEnd(), event.getArrivalMethod());
            } else {
                throw new Exception("Could not create Event in the database");
            }
        } catch (Exception e) {
            throw new Exception("Could not create Event in the database", e);
        }
    }

    @Override
    public void updateEvent(Event event) throws Exception {
        String sql = "UPDATE Event SET Name=?, Time=?, LocationId=?, Notes=?, EndTime=?, ArrivalMethod=? WHERE Id=?";
        try (Connection conn = DBConnector.getStaticConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, event.getName());
            ps.setTimestamp(2, Timestamp.valueOf(event.getTime()));
            ps.setInt(3, event.getLocationId());
            ps.setString(4, event.getNote());
            ps.setTimestamp(5, event.getEnd() != null ? Timestamp.valueOf(event.getEnd()) : null);
            ps.setString(6, event.getArrivalMethod());
            ps.setInt(7, event.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Could not update Event in the database", e);
        }
    }

    @Override
    public void deleteEvent(Event event) throws Exception {
        String sql = "DELETE FROM Event WHERE Id=?";
        try (Connection conn = DBConnector.getStaticConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, event.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Could not delete Event in the database", e);
        }
    }
}

