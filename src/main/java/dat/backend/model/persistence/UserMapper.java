package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


class UserMapper {
    static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    user = new User(email, password, role);
                } else {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    static User createUser(String fullname, String email, String password, String phonenumber, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user = null;
        String role = "User";
        String sql = "INSERT INTO user (role, fullname, email, password, phonenumber)" + "values (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, role);
                ps.setString(2, fullname);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, phonenumber);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {

                        user = new User(0, role, fullname, email, password, phonenumber);

                } else {
                    throw new DatabaseException("The user with username = " + email + " could not be inserted into the database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not create user");
        }
        return user;
    }

    static ArrayList<User> showUsers(ConnectionPool connectionPool) throws DatabaseException {
        User user = null;
        ArrayList<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String role = rs.getString("role");
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String phonenumber = rs.getString("phonenumber");
                    user = new User(userId, role, fullname, email, "", phonenumber);
                    allUsers.add(user);
                    System.out.println(allUsers);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not show users");
        }
        return allUsers;
    }

    static ArrayList<User> showUserHistory(ConnectionPool connectionPool) throws DatabaseException {
        User user = null;
        ArrayList<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM order_history";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String email = rs.getString("email");
                    String fullname = rs.getString("fullname");
                    int orderId = rs.getInt("order_id");
                    user = new User(userId, "", fullname, email, "", "", orderId);
                    allUsers.add(user);
                    System.out.println(allUsers);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not show users");
        }
        return allUsers;
    }
    static boolean deleteUser(int userId, ConnectionPool connectionPool) throws DatabaseException {
        boolean isDeleted = false;
        String sql = ("delete from user where user_id = ?");
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                int rowsAffected = ps.executeUpdate(); //makes sure only one row is affected
                if (rowsAffected == 1)
                {
                    isDeleted = true;
                } else {
                    throw new DatabaseException("User could not be deleted from database");
                }
            }
        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "User could not be deleted from database");
        }
        return isDeleted;
    }
}
