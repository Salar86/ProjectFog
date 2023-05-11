package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemListMapper {

    static ItemList getItemList(int listId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ItemList itemList = null;
        String sql = "select from itemlist where itemlist_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
}
