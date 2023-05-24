package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductVariantMapper {

    static double getProductVariantQuantity(int productVariantId, ConnectionPool connectionPool) throws DatabaseException {
        double quantity = 0;
        String sql = "SELECT * FROM project_fog_test.product_variant where product_variant_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, productVariantId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    quantity = rs.getDouble("quantity");
                }
            }
        }
        catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not get products " + ex.getMessage());
        }
        return quantity;
    }

    static boolean modifyQuantity(int productId, double quantity, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean isModified;
        String sql = "update product set quantity = ? where product_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, quantity);
                preparedStatement.setInt(2, productId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    isModified = true;
                } else {
                    throw new DatabaseException("Quantity could not be modified in the database");
                }
            }

        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException, "Quantity could not be modified in the database");
        }
        return isModified;
    }
}