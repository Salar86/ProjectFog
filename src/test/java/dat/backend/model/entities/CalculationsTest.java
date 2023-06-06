package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ProductFacade;
import dat.backend.model.persistence.ProductVariantFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class CalculationsTest {
    private final static String USER = "dev";
    private final static String PASSWORD = "MeYer@5600";
    private final static String URL = "jdbc:mysql://64.226.118.227:3306/project_fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void beforeAllSetup() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

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
    void calculateRafters() throws DatabaseException {
        ArrayList<ItemList> actual= new ArrayList<>();
        ArrayList<ItemList> expected = new ArrayList<>();
        expected.add(new ItemList(0, "480mm spær. Monteres på rem.", 2880, 0, 3, 10));

        double carportWidthInCm = 450, carportLengthInCm = 600;
        int orderId = 0;
        final double RAFTER_SPACING_CM = 55;
        double priceRaftersAndRem = ProductFacade.getPrice(2, connectionPool);

        double rafterCount = carportLengthInCm / RAFTER_SPACING_CM; // (Decimal) How many rafters will fit inside the roof.
        double rafterValue = rafterCount - (int) rafterCount; // The decimal-points from rafterCount to calculate the excess distance.
        int numberOfRafters = (int) rafterCount; // The whole number of rafters.

        if (rafterValue <= 0.36) { // If its below 0.36 the last rafter will be removed and the back-stern will act last rafter.
            numberOfRafters = numberOfRafters - 1;
        }

        if (carportWidthInCm <= 600 && carportWidthInCm >= 481) {
            actual.add(new ItemList("600mm spær. Monteres på rem.", (int)((priceRaftersAndRem* ProductVariantFacade.getProductVariantQuantity(4, connectionPool))*numberOfRafters), orderId, 4, numberOfRafters));
        } else if (carportWidthInCm <= 480 && carportWidthInCm >= 301) {
            actual.add(new ItemList("480mm spær. Monteres på rem.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))*numberOfRafters), orderId, 3, numberOfRafters));
        } else if (carportWidthInCm == 300) {
            numberOfRafters = numberOfRafters/2;
            actual.add(new ItemList("600mm Spær. Monteres på rem.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(4, connectionPool))*numberOfRafters), orderId, 4, numberOfRafters));
        } else if (carportWidthInCm == 240) {
            numberOfRafters = numberOfRafters/2;
            actual.add(new ItemList("480mm spær. Monteres på rem.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))*numberOfRafters), orderId, 3, numberOfRafters));
        }

        assertEquals(actual, expected);
    }

    @Test
    void calculateCarportTest360X360() throws DatabaseException {
        Calculations cal = new Calculations();

        ItemList itemOne = new ItemList("480mm remme skal sadles ned i stolperne.", 576, 2, 3, 2);
        ItemList itemTwo = new ItemList("Stolper nedgraves 90 cm. i jord.", 660, 2, 5, 4);
        ItemList itemThree = new ItemList("480mm spær. Monteres på rem.", 1728, 2, 3, 6);
        ItemList itemFour = new ItemList("360mm Sternbrædder til for- og bagende.", 360, 2, 1, 2);
        ItemList itemFive = new ItemList("360mm Sternbrædder til siderne.", 360, 2, 1, 2);

        assertEquals(cal.calculateCarport(2, 360, 360).get(0), itemOne);
        assertEquals(cal.calculateCarport(2, 360, 360).get(1), itemTwo);
        assertEquals(cal.calculateCarport(2, 360, 360).get(2), itemThree);
        assertEquals(cal.calculateCarport(2, 360, 360).get(3), itemFour);
        assertEquals(cal.calculateCarport(2, 360, 360).get(4), itemFive);

    }
}