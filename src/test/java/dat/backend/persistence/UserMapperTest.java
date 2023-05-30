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

class UserMapperTest {
    // TODO: Change mysql login credentials if needed below

    private final static String USER = "root";
    private final static String PASSWORD = "Ostefar";
    private final static String URL = "jdbc:mysql://localhost:3306/project_fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS project_fog_test;");

                // TODO: Create user table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS project_fog_test.user LIKE project_fog.user;");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                stmt.execute("DELETE from project_fog_test.order");
                stmt.execute("delete from user");
                stmt.execute("ALTER TABLE `user` DISABLE KEYS");
                stmt.execute("ALTER TABLE `user` AUTO_INCREMENT = 1");

                stmt.execute("insert into user (role, fullname, email, password, phonenumber) " +
                        "values ('user','test','user@user.dk', '1234', '1234'),('admin','test','admin@admin.dk', '12345', '12345'), ('user','test','user@test.dk', '123456', '123456')");
                stmt.execute("ALTER TABLE `user` ENABLE KEYS");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void login() throws DatabaseException {
        User expectedUser = UserFacade.createUser("Rasmus", "user@test.com", "12345678", "123456", connectionPool);
        User actualUser = UserFacade.login("user@test.com", "12345678", connectionPool);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> UserFacade.login("user@user.dk", "123", connectionPool));
    }

    @Test
    void invalidUserNameLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> UserFacade.login("user@user.d", "1234", connectionPool));
    }

    @Test
    void createUser() throws DatabaseException {
        User newUser = UserFacade.createUser("Farmand", "peter@test.dk", "hijklmn", "123", connectionPool);
        User logInUser = UserFacade.login("peter@test.dk", "hijklmn", connectionPool);
        User expectedUser = new User(6, "user", "Farmand", "peter@test.dk", "hijklmn", "123");
        System.out.println(expectedUser.getUserId());
        System.out.println(logInUser.getUserId());
        assertEquals(expectedUser, newUser);
        assertEquals(expectedUser, logInUser);

    }
}