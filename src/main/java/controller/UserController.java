package controller;

import model.User;
import service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService = new UserService();

    // ADD USER
    public boolean addUser(User user) {

        return userService.addUser(user);
    }

    // GET USER BY ID
    public User getUserById(int userId) {

        return userService.getUserById(userId);
    }

    // GET ALL USERS
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    // UPDATE USER
    public boolean updateUser(User user) {

        return userService.updateUser(user);
    }

    // DELETE USER
    public boolean deleteUser(int userId) {

        return userService.deleteUser(userId);
    }
}