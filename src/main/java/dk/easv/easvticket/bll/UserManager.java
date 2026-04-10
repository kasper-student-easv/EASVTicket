package dk.easv.easvticket.bll;

import dk.easv.easvticket.be.User;
import dk.easv.easvticket.dal.UserDAO;

import java.util.List;

public class UserManager {

    private final UserDAO userDAO = new UserDAO();

    public List<User> getAllUser() throws Exception {
        return userDAO.getAllUser();
    }

    public User createUser(User user) throws Exception {
        return userDAO.createUser(user);
    }
    public void deleteUser(User user) throws  Exception {
        userDAO.deleteUser(user);
    }

}
