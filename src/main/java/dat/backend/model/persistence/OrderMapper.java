package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper {

    static Order createOrder(double length, double width, double price, String material, String status, int userId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Order order;
        String sql = "insert into project_fog_test.order (length, width, price, material, status, user_id) values (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, length);
                preparedStatement.setDouble(2, width);
                preparedStatement.setDouble(3, price);
                preparedStatement.setString(4, material);
                preparedStatement.setString(5, status);
                preparedStatement.setInt(6, userId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    order = new Order(length, width, price, material, status, userId);
                } else {
                    throw new DatabaseException("Order can not be inserted into the database");
                }
            }
        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Order could not be inserted into the database");
        }
        return order;
    }

    static boolean modifyLength(int orderId, double length, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;
        String sql = "update project_fog_test.order set length = ? where order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, length);
                preparedStatement.setInt(2, orderId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    isModified = true;
                } else {
                    throw new DatabaseException("Length could not be modified in the database");
                }
            }

        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Length could not be modified in the database");
        }
        return isModified;
    }

    static boolean modifyWidth(int orderId, double width, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;
        String sql = "update project_fog_test.order set width = ? where order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, width);
                preparedStatement.setInt(2, orderId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    isModified = true;
                } else {
                    throw new DatabaseException("Width could not be modified in the database");
                }
            }

        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Width could not be modified in the database");
        }
        return isModified;
    }
    static boolean modifyPrice(int orderId, double price, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;
        String sql = "update project_fog_test.order set price = ? where order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, price);
                preparedStatement.setInt(2, orderId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    isModified = true;
                } else {
                    throw new DatabaseException("Price could not be modified in the database");
                }
            }

        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Price could not be modified in the database");
        }
        return isModified;
    }

    static boolean modifyMaterial(int orderId, String material, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;
        String sql = "update project_fog_test.order set material = ? where order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, material);
                preparedStatement.setInt(2, orderId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    isModified = true;
                } else {
                    throw new DatabaseException("Material could not be modified in the database");
                }
            }

        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Material could not be modified in the database");
        }
        return isModified;
    }

    static boolean modifyStatus(int orderId, String status, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;
        String sql = "update project_fog_test.order set status = ? where order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, orderId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    isModified = true;
                } else {
                    throw new DatabaseException("Status could not be modified in the database");
                }
            }

        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Status could not be modified in the database");
        }
        return isModified;
    }

    static boolean deleteOrder(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        boolean isDeleted = false;
        String sql = ("delete from project_fog_test.order where order_id = ?");
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);
                int rowsAffected = preparedStatement.executeUpdate(); //makes sure only one row is affected
                if (rowsAffected == 1)
                {
                    isDeleted = true;
                } else {
                    throw new DatabaseException("Order could not be deleted from database");
                }

            }
        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Order could not be deleted from database");
        }
        return isDeleted;
    }

    static ArrayList<Order> showOrders(ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Order order;
        ArrayList<Order> allOrders = new ArrayList<>();
        String sql = "select * from project_fog_test.order";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    double length = resultSet.getDouble("length");
                    double width = resultSet.getDouble("width");
                    double price = resultSet.getDouble("price");
                    String material = resultSet.getString("material");
                    String status = resultSet.getString("status");
                    int userId = resultSet.getInt("user_id");
                    order = new Order(orderId, length, width, price, material, status, userId);
                    allOrders.add(order);
                }
            }
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Could not connect to database");
        }
        return allOrders;
    }
    static ArrayList<Order> showOrdersForUser(int userId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Order order;
        ArrayList<Order> allOrders = new ArrayList<>();
        String sql = "select * from project_fog_test.order where user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    double length = resultSet.getDouble("length");
                    double width = resultSet.getDouble("width");
                    double price = resultSet.getDouble("price");
                    String material = resultSet.getString("material");
                    String status = resultSet.getString("status");
                    userId = resultSet.getInt("user_id");
                    order = new Order(orderId, length, width, price, material, status, userId);
                    allOrders.add(order);
                }
            }
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Could not connect to database "+ sqlException.getMessage());
        }
        return allOrders;
    }

}
