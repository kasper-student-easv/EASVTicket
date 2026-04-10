package dk.easv.easvticket.dal;

import dk.easv.easvticket.be.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUser {


    @Override
    public List<User> getAllUser() throws Exception {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnector.getStaticConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM EventUser";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String userName = rs.getString("UserName");
                String email = rs.getString("email");
                int type = rs.getInt("Type");
                users.add(new User(id, name, userName, email, type));
            }
        } catch (Exception e) {
            throw new Exception("something has gone wrong in getting User", e);
        }
        return users;
    }

    @Override
    public User createUser(User user) throws Exception {
        try(Connection conn = DBConnector.getStaticConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO EventUser (Name,UserName,Password,EMail,Type) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,user.getName());
            ps.setString(2,user.getUserName());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
            ps.setInt(5,user.getType());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                int newId = rs.getInt(1);
                return new User(newId, user.getName(), user.getUserName(), user.getEmail(), user.getType());
            }
            else {
                throw new Exception("Could not create User in the database");
            }
        } catch (Exception e) {
            throw new Exception("Could not create User in the database",e);
        }
    }

    @Override
    public void updateUser(User user) throws Exception {
        try(Connection conn = DBConnector.getStaticConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE EventUser SET Name=?,UserName=?,EMail=?,Type=? WHERE Id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getEmail());
            ps.setInt(4,user.getType());
            ps.setInt(5,user.getId());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new Exception("Could not update the User in the database",e);
        }
    }

    @Override
    public void deleteUser(User user) throws Exception {
        try(Connection conn = DBConnector.getStaticConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM EventUser WHERE Id =?",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,user.getId());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new Exception("Could not delete User in the database", e);
        }
    }
}
