package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemListMapper {

    static boolean createItemList(ItemList itemList, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean itemListAdded = false;

        String sql = "INSERT INTO project_fog.itemlist (description, price, order_id, product_variant_id, quantity) values (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, itemList.getDescription());
                ps.setInt(2, itemList.getPrice());
                ps.setInt(3, itemList.getOrderId());
                ps.setInt(4, itemList.getProductVariantId());
                ps.setInt(5, itemList.getQuantity());
                ps.executeUpdate();
                itemListAdded = true;
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not add to ITEMLIST " + ex.getMessage());
        }
        return itemListAdded;
    }

    static ArrayList<ItemList> getItemList(int order, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ItemList itemListEntry = null;
        ArrayList<ItemList> entireItemList = new ArrayList<>();

        String sql = "select * from project_fog.itemlist where order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, order);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int itemListId = resultSet.getInt("itemlist_id");
                    String description = resultSet.getString("description");
                    int price = resultSet.getInt("price");
                    int orderId = resultSet.getInt("order_id");
                    int productVariantId = resultSet.getInt("product_variant_id");
                    int quantity = resultSet.getInt("quantity");
                    itemListEntry = new ItemList(itemListId, description, price, orderId, productVariantId, quantity);
                    entireItemList.add(itemListEntry);
                }
            }
        } catch (SQLException sqlException) {
            throw new DatabaseException("Could not find Itemlist "+ sqlException.getMessage());
        }
        return entireItemList;
    }

    static boolean deleteItemList(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isDeleted = false;

        String sql = "delete from project_fog.itemlist where order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);
                int rowsAffected = preparedStatement.executeUpdate(); //makes sure only one row is affected
                if (rowsAffected == 1)
                {
                    isDeleted = true;
                } else {
                    throw new DatabaseException("Itemlist could not be deleted from database");
                }

            }
        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Itemlist could not be deleted from database");
        }
        return isDeleted;
    }

    static ArrayList<ItemList> showItemLists(ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ItemList itemList = null;
        ArrayList<ItemList> itemLists = new ArrayList<>();

        String sql = "select * from project_fog.itemlist";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int itemListID = resultSet.getInt("itemlist_id");
                    String description = resultSet.getString("description");
                    int price = resultSet.getInt("price");
                    int orderId = resultSet.getInt("order_id");
                    int productVariantId = resultSet.getInt("product_variant_id");
                    int quantity = resultSet.getInt("quantity");
                    itemList = new ItemList(itemListID, description, price, orderId, productVariantId, quantity);
                    itemLists.add(itemList);
                }
            }
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Could not connect to database");
        }
        return itemLists;
    }

    static boolean modifyDescription(String description, int itemListId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;

        String sql = "update project_fog.itemlist set description = ? where itemlist_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, description);
                preparedStatement.setInt(2, itemListId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    isModified = true;
                } else {
                    throw new DatabaseException("Description could not be modified in the database");
                }
            }

        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Description could not be modified in the database");
        }
        return isModified;
    }

    static boolean modifyPrice(int price, int itemListId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;

        String sql = "update project_fog.itemlist set price = ? where itemlist_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, price);
                preparedStatement.setInt(2, itemListId);
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

    static boolean modifyQuantity(int quantity, int itemListId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;

        String sql = "update project_fog.itemlist set quantity = ? where itemlist_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, quantity);
                preparedStatement.setInt(2, itemListId);
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

    static double getPrice(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        double totalPrice = 0;

        String sql = "SELECT SUM(price) AS sumprice FROM project_fog.itemlist WHERE order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, orderId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    totalPrice = resultSet.getDouble("sumprice");
                }
            }

        }
        catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Price could not be modified in the database");
        }
        return totalPrice;
    }



}
