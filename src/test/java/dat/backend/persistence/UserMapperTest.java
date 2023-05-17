package dat.backend.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest
{
    // TODO: Change mysql login credentials if needed below

    private final static String USER = "root";
    private final static String PASSWORD = "Ostefar";
    private final static String URL = "jdbc:mysql://localhost:3306/project_fog_test";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass()
    {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS project_fog_test;");

                // Creates test databases. Works
                /* stmt.execute("CREATE TABLE IF NOT EXISTS project_fog_test.user LIKE project_fog.user;");
                stmt.execute("CREATE TABLE IF NOT EXISTS project_fog_test.itemlist LIKE project_fog.itemlist;");
                stmt.execute("CREATE TABLE IF NOT EXISTS project_fog_test.order LIKE project_fog.order;");
                stmt.execute("CREATE TABLE IF NOT EXISTS project_fog_test.product LIKE project_fog.product;");
                stmt.execute("CREATE TABLE IF NOT EXISTS project_fog_test.product_variant LIKE project_fog.product_variant;"); */
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // TODO: Remove all rows from all tables - add your own tables here
               // stmt.execute("DELETE FROM user where user_id = 3");

                // TODO: Insert a few users - insert rows into your own tables here
                //stmt.execute("INSERT INTO user (role, fullname, email, password, phonenumber)" + "values ('user', 'Rasmus','Rasmus@me.dk','1234','21575525')");

            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

   @Test
     void login() throws DatabaseException
    {
        User expectedUser = new User("Rasmus@me.dk", "1234", "user");
        User actualUser = UserFacade.login("Rasmus@me.dk", "1234", connectionPool);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> UserFacade.login("Rasmus@me.dk", "123", connectionPool));
    }


    @Test
    void invalidUserNameLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> UserFacade.login("Rasmus@me.d", "1234", connectionPool));
    }

   @Test
    void createUser() throws DatabaseException
    {
        User newUser = UserFacade.createUser("user", "Lars", "Rasmus@me.dk", "1234", "21575525", connectionPool);
        User logInUser = UserFacade.login("Rasmus@me.dk", "1234", connectionPool);
        User expectedUser = new User("Rasmus@me.dk", "1234", "user");
        assertEquals(expectedUser, newUser);
        assertEquals(expectedUser, logInUser);

    }
   @Test
   void showUsers() throws DatabaseException
   {
        UserFacade.showUsers(connectionPool);
   }

   @Test
   void showUserHistory() throws DatabaseException
   {
       UserFacade.showUserHistory(connectionPool);
   }
}