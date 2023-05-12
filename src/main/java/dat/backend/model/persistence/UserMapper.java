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

    static User createUser(String role, String fullname, String email, String password, String phonenumber, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (role, fullname, email, password, phonenumber) values (?,?,?,?,?)";
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
            throw new DatabaseException(ex, "Could not insert username into database");
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
                    String phonenumber = rs.getString("phonenumber");
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 1) {
                        user = new User(userId, role, fullname, email, "", phonenumber);
                        allUsers.add(user);

                    } else {
                        throw new DatabaseException("Could not show users");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 1) {
                        user = new User(userId, "", fullname, email, "", "", orderId);
                        allUsers.add(user);
                    } else {
                        throw new DatabaseException("Could not show users");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allUsers;
    }
}
