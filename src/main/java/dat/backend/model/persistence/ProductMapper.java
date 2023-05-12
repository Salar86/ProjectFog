package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductMapper {

    static boolean modifyPricePerUnit(int productId, double pricePerUnit, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;
        String sql = "update product set price_per_unit = ? where product_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, pricePerUnit);
                preparedStatement.setInt(2, productId);
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

    static boolean modifyDescription(String productDescription, int productId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;
        String sql = "update product set product_description = ? where product_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, productDescription);
                preparedStatement.setInt(2, productId);
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
}
