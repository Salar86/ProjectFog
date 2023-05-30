package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "meyer";
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
                stmt.execute("CREATE TABLE IF NOT EXISTS project_fog_test.order LIKE project_fog.order;");
            }
        }
        catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }

    }


    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {

                stmt.execute("DELETE from project_fog_test.order");
                stmt.execute("DELETE from user");
                stmt.execute("ALTER TABLE `order` DISABLE KEYS");
                stmt.execute("ALTER TABLE `order` AUTO_INCREMENT = 1");
                stmt.execute("ALTER TABLE `user` DISABLE KEYS");
                stmt.execute("ALTER TABLE `user` AUTO_INCREMENT = 1");
                // Inserts a few orders into test
                stmt.execute("insert into user (role, fullname, email, password, phonenumber) " +
                        "values ('user','test','user@user.dk', '1234', '1234'),('admin','test','admin@admin.dk', '1234', '1234'), ('user','test','user@test.dk', '1234', '1234')");
                stmt.execute("insert into project_fog_test.order (length, width, material, price, status, user_id) " +
                        "values (480, 480, 'test', 20000, 'test', 1),(600, 300, 'test', 30000, 'test', 2), (300, 300, 'test', 17500, 'test', 3)");
                stmt.execute("ALTER TABLE `order` ENABLE KEYS");
                stmt.execute("ALTER TABLE `user` ENABLE KEYS");

            }
        }
        catch (SQLException throwables)
        {
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
    void modifyPricePerUnit() throws DatabaseException {
        boolean isModified = ProductFacade.modifyPricePerUnit(1,35, connectionPool);
        assertTrue(isModified);
    }

    @Test
    void modifyDescription() throws DatabaseException {
        boolean isModified = ProductFacade.modifyDescription("TEST", 2, connectionPool);
        assertTrue(isModified);
    }
}