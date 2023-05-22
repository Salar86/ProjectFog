package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.login(email, password, connectionPool);
    }

    public static User createUser(String fullname, String email, String password, String phonenumber, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.createUser(fullname, email, password, phonenumber, connectionPool);
    }
    public static ArrayList<User> showUsers(ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.showUsers(connectionPool);
    }
    public static ArrayList<User> showUserHistory(ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.showUserHistory(connectionPool);
    }
    public static boolean deleteUser(int userId, ConnectionPool connectionPool)throws DatabaseException {
        return UserMapper.deleteUser(userId, connectionPool);
    }


}
