package dk.easv.easvticket.dal;

import dk.easv.easvticket.be.User;

import java.util.List;

public interface IUser {

    List<User> getAllUser() throws Exception;

    List<User> getCoordinator() throws Exception;

    User createUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;
}
