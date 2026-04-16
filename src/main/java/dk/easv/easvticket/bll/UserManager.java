package dk.easv.easvticket.bll;

import dk.easv.easvticket.be.User;
import dk.easv.easvticket.dal.IUser;
import dk.easv.easvticket.dal.UserDAO;

import java.util.List;

public class UserManager {

    private final IUser userDAO = new UserDAO();

    public List<User> getAllUser() throws Exception {
        return userDAO.getAllUser();
    }

    public List<User> getCoordinator() throws Exception {
        return userDAO.getCoordinator();
    }

    public User createUser(User user) throws Exception {
        return userDAO.createUser(user);
    }
    public void deleteUser(User user) throws  Exception {
        userDAO.deleteUser(user);
    }

    public void updateUser(User user) throws Exception {
        userDAO.updateUser(user);
    }
}
