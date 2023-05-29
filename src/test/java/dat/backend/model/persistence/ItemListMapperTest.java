package dat.backend.model.persistence;

import dat.backend.model.entities.Calculations;
import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ItemListMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "meyer";
    private final static String URL = "jdbc:mysql://localhost:3306/project_fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void beforeAllSetup() throws SQLException {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        Connection testConnection = connectionPool.getConnection();
        assertNotNull(testConnection);
        if (testConnection != null) {
            testConnection.close();
        }

    }

    @BeforeEach
    void beforeEachSetup(){
        try(Connection testConnection = connectionPool.getConnection()){
            try(Statement stmt = testConnection.createStatement() ){
                stmt.execute("DELETE from project_fog_test.itemlist");
                stmt.execute("DELETE from project_fog_test.order");
                stmt.execute("ALTER TABLE `order` DISABLE KEYS");
                stmt.execute("ALTER TABLE `order` AUTO_INCREMENT = 1");
                stmt.execute("INSERT INTO project_fog_test.order (length, width, material, price, status, user_id)" +
                        "VALUES (600.00, 450.00, 'A', 0, 'PENDING', 1), (360.00, 360.00, 'A', 0, 'PENDING', 1), (540.00, 540.00, 'A', 0, 'PENDING', 1), " +
                        "(780.00, 540.00, 'A', 0, 'PENDING', 1), (630.00, 750.00, 'A', 0, 'PENDING', 1) ");
                stmt.execute("ALTER TABLE `itemlist` DISABLE KEYS");
                stmt.execute("ALTER TABLE `itemlist` AUTO_INCREMENT = 1");
                stmt.execute("ALTER TABLE `order` ENABLE KEYS");
                stmt.execute("ALTER TABLE `itemlist` ENABLE KEYS");
            }
        }catch(SQLException throwables){
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void TestProductMapper() throws DatabaseException {


        Calculations cal = new Calculations();

        for (ItemList itemList: cal.calculateCarport(1, 450,600)) {
            assertTrue( ItemListFacade.createItemList(itemList, connectionPool));
        }

    }
}