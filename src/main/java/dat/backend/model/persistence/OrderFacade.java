package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class OrderFacade {

    public static Order createOrder(int orderId, double length, double width, double price, String material, String status, int userId, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.createOrder(orderId, length, width, price, material, status, userId, connectionPool);
    }

    public static boolean deleteOrder(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.deleteOrder(orderId, connectionPool);
    }

    public static ArrayList<Order> showOrders(ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.showOrders(connectionPool);
    }

    public static boolean modifyLength(int orderId, double length, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.modifyLength(orderId, length, connectionPool);
    }

    public static boolean modifyWidth(int orderId, double width, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.modifyWidth(orderId, width, connectionPool);
    }

    public static boolean modifyPrice(int orderId, double price, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.modifyPrice(orderId, price, connectionPool);
    }

    public static boolean modifyMaterial(int orderId, String material, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.modifyMaterial(orderId, material, connectionPool);
    }

    public static boolean modifyStatus(int orderId, String status, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.modifyStatus(orderId, status, connectionPool);
    }

}
