package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

import java.util.Map;

public class ProductVariantFacade {

    public static boolean modifyQuantity(int productId, double quantity, ConnectionPool connectionPool) throws DatabaseException {
        return ProductVariantMapper.modifyQuantity(productId, quantity, connectionPool);
    }

    public static double getProductVariantQuantity(int productVariantId, ConnectionPool connectionPool) throws DatabaseException {
        return ProductVariantMapper.getProductVariantQuantity(productVariantId, connectionPool);
    }
}
