package service;

import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    // ADD USER
    public boolean addUser(User user) {

        if (user == null) {
            return false;
        }

        if (user.getName() == null || user.getName().isBlank()) {
            return false;
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return false;
        }

        if (user.getDepartment() == null || user.getDepartment().isBlank()) {
            return false;
        }

        return userDAO.addUser(user);
    }

    // GET USER BY ID
    public User getUserById(int userId) {

        if (userId <= 0) {
            return null;
        }

        return userDAO.getUserById(userId);
    }

    // GET ALL USERS
    public List<User> getAllUsers() {

        return userDAO.getAllUsers();
    }

    // UPDATE USER
    public boolean updateUser(User user) {

        if (user == null || user.getUserId() <= 0) {
            return false;
        }

        if (user.getName() == null || user.getName().isBlank()) {
            return false;
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return false;
        }

        if (user.getDepartment() == null || user.getDepartment().isBlank()) {
            return false;
        }

        return userDAO.updateUser(user);
    }

    // DELETE USER
    public boolean deleteUser(int userId) {

        if (userId <= 0) {
            return false;
        }

        return userDAO.deleteUser(userId);
    }
}