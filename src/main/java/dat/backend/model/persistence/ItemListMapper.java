package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemListMapper {

    static ItemList getItemList(int order, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ItemList itemList = null;
        String sql = "select from itemlist where order_id = ?";
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
                    itemList = new ItemList(itemListId, description, price, orderId, productVariantId, quantity);
                }
            }
        } catch (SQLException sqlException) {
            throw new DatabaseException("Could not find Itemlist");
        }
        return itemList;
    }

    static boolean deleteItemList(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isDeleted = false;
        String sql = "delete from itemlist where order_id = ?";
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
        String sql = "select * from itemlist";
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
}
