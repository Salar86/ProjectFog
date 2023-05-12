package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class ItemListFacade {

    public static ItemList getItemList(int order, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.getItemList(order, connectionPool);
    }

    public static boolean deleteItemList(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.deleteItemList(orderId, connectionPool);
    }

    public static ArrayList<ItemList> showItemLists(ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.showItemLists(connectionPool);
    }

    public static boolean modifyDescription(String description, int itemListId, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.modifyDescription(description, itemListId, connectionPool);
    }

    public static boolean modifyPrice(int price, int itemListId, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.modifyPrice(price, itemListId, connectionPool);
    }

    public static boolean modifyQuantity(int quantity, int itemListId, ConnectionPool connectionPool) throws DatabaseException {
        return ItemListMapper.modifyQuantity(quantity, itemListId, connectionPool);
    }
}
