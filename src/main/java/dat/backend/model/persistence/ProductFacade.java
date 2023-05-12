package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

public class ProductFacade {

    public static boolean modifyPricePerUnit(int productId, double pricePerUnit, ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.modifyPricePerUnit(productId, pricePerUnit, connectionPool);
    }

    public static boolean modifyDescription(String productDescription, int productId, ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.modifyDescription(productDescription, productId, connectionPool);
    }
}
