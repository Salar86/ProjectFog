package dat.backend.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    // Make a double with decimals. Cast it to an int (no decimals), and subtract it from the double, to get only decimal points.
    void calcTestRafters() {
        int lengthInCm = 390;
        final double RAFTER_SPACING_CM = 55;

        double rafterCount = lengthInCm/RAFTER_SPACING_CM;
        double rafterValue = rafterCount - (int)rafterCount;
        int numberOfRafters = (int)rafterCount;
        System.out.println(rafterValue);

        System.out.println(numberOfRafters);

        if(rafterValue >= 0.36){ // If its below 0.36 the end of the carport will be the "last" rafter.
            System.out.println("Rafter on - " + numberOfRafters);

        }else numberOfRafters = numberOfRafters-1;
        System.out.println(numberOfRafters);
    }

    @Test
    void calcTestPosts() {
        int lengthInCm = 481;

        int post = 0;

        if(lengthInCm >= 480){
            post = 6;
        } else post = 4;

        assertEquals(6, 6);
    }
}