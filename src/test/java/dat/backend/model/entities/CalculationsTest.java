package dat.backend.model.entities;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;
import dat.backend.model.persistence.ProductVariantFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class CalculationsTest {
    private final static String USER = "root";
    private final static String PASSWORD = "Salar0108";
    private final static String URL = "jdbc:mysql://localhost:3306/project_fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void beforeAllSetup() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

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
    void testConnection() throws SQLException {
        Connection testConnection = connectionPool.getConnection();
        assertNotNull(testConnection);
        if (testConnection != null) {
            testConnection.close();
        }
    }

    @Test
    void calRem() throws DatabaseException {
        Calculations cal = new Calculations();
        cal.calculateRem(600, 0);
        //System.out.println(cal.calculateRem(780));
        cal.calculateRafters(500,600, 0);
        //System.out.println(cal.calculateRafters(360, 360));
        cal.calculatePosts(481, 0);

        cal.calculateSternBoardsMainMethod(450,600,0);
    }

    @Test
    // Make a double with decimals. Cast it to an int (no decimals), and subtract it from the double, to get only decimal points.
    void calcTestRafters() {
        int lengthInCm = 780;
        int width = 240;
        final double RAFTER_SPACING_CM = 55;

        double rafterCount = lengthInCm/RAFTER_SPACING_CM;
        double rafterValue = rafterCount - (int)rafterCount;
        int numberOfRafters = (int)rafterCount;
        System.out.println(rafterValue);

        System.out.println(numberOfRafters);

        if (rafterValue >= 0.36){ // If its below 0.36 the end of the carport will be the "last" rafter.
            System.out.println("Rafter on - " + numberOfRafters);

        }else numberOfRafters = numberOfRafters-1;


        if (width <= 600 && width >= 481){
            System.out.println("RAFTERS 6 meters");
        } else if (width == 300){
            System.out.println("RaFTERS 6 meters " + numberOfRafters/2);
        } else if (width == 240){
            System.out.println("Rafters 4.8 " + numberOfRafters/2);
        }else System.out.println("Rafters 4.8");
        System.out.println(numberOfRafters);
    }

    @Test
    void calcTestPosts() {
        int lengthInCm = 481;

        int post = 0;

        if (lengthInCm >= 480){
            post = 6;
        } else post = 4;

        assertEquals(6, 6);
    }

    //______________________________***________________________________________
    @Test
    void TestProductMapper() throws DatabaseException {

        Calculations cal = new Calculations();

        for (ItemList itemList: cal.calculateCarport(2, 360,360)) {
            ItemListFacade.createItemList(itemList, connectionPool);
        }


    }
   //________________________________***_____________________________________________

    @Test
    void calculateFrontAndBackSternBoards() throws DatabaseException {
        Calculations cal = new Calculations();
        cal.calculateCarport(1, 450, 600);

    }

    @Test
    public void calculateRaftersTest() {
        final double RAFTER_SPACING_CM = 55;
        double lengthInCm = 600;
        double width = 540;

        double rafterCount = lengthInCm / RAFTER_SPACING_CM;
        double rafterValue = rafterCount - (int) rafterCount;
        int numberOfRafters = (int) rafterCount;
        System.out.println(numberOfRafters);
        if (rafterValue <= 0.36) { // If its below 0.36 the end of the carport will be the "last" rafter.
            numberOfRafters = numberOfRafters - 1;
        }

        if (width <= 600 && width >= 481) {
            System.out.println("RAFTERS 6 meters");
        } else if (width == 300) {
            numberOfRafters = numberOfRafters/2;
            System.out.println("RaFTERS 6 meters " + numberOfRafters);
        } else if (width == 240) {
            numberOfRafters = numberOfRafters/2;
            System.out.println("Rafters 4.8 " + numberOfRafters);
        } else System.out.println("Rafters 4.8");

        assertEquals(numberOfRafters, 1);
    }

}