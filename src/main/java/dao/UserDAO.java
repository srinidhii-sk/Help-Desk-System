package dao;

import model.User;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // CREATE
    public boolean addUser(User user) {

        String sql = """
                INSERT INTO users
                (name, email, department)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getDepartment());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding user: " + e.getMessage()
            );

            return false;
        }
    }


    // READ - Get User By ID
    public User getUserById(int userId) {

        String sql =
                "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    User user = new User();

                    user.setUserId(
                            resultSet.getInt("user_id")
                    );

                    user.setName(
                            resultSet.getString("name")
                    );

                    user.setEmail(
                            resultSet.getString("email")
                    );

                    user.setDepartment(
                            resultSet.getString("department")
                    );

                    if (resultSet.getTimestamp("created_at") != null) {

                        user.setCreatedAt(
                                resultSet
                                        .getTimestamp("created_at")
                                        .toLocalDateTime()
                        );
                    }

                    return user;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching user: " + e.getMessage()
            );
        }

        return null;
    }


    // READ - Get All Users
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User();

                user.setUserId(
                        resultSet.getInt("user_id")
                );

                user.setName(
                        resultSet.getString("name")
                );

                user.setEmail(
                        resultSet.getString("email")
                );

                user.setDepartment(
                        resultSet.getString("department")
                );

                if (resultSet.getTimestamp("created_at") != null) {

                    user.setCreatedAt(
                            resultSet
                                    .getTimestamp("created_at")
                                    .toLocalDateTime()
                    );
                }

                users.add(user);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching users: "
                            + e.getMessage()
            );
        }

        return users;
    }


    // UPDATE
    public boolean updateUser(User user) {

        String sql = """
                UPDATE users
                SET name = ?,
                    email = ?,
                    department = ?
                WHERE user_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getDepartment());
            statement.setInt(4, user.getUserId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating user: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // DELETE
    public boolean deleteUser(int userId) {

        String sql =
                "DELETE FROM users WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting user: "
                            + e.getMessage()
            );

            return false;
        }
    }
}