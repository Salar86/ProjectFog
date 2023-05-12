package dat.backend.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {

    @BeforeEach
    void setUp() {
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

    @Test
    void calculateFrontAndBackSternBoards(){

        double lengthInCm = 3120;
        final double WOOD_LENGTH = 540;

        double woodNeeded = lengthInCm/WOOD_LENGTH;
        double woodRatio = woodNeeded - (int)woodNeeded;
        double wholeWood = woodNeeded-woodRatio;
        double remainder = lengthInCm-(wholeWood*WOOD_LENGTH);

        int woodToShip = 0;

        if (woodNeeded == 4){
            System.out.println("A okay");
            woodToShip = (int)wholeWood;
        } else if ((wholeWood*WOOD_LENGTH) < lengthInCm) {
            System.out.println(remainder);
            if (remainder <= 360){
                System.out.println("Add 1 short");
                woodToShip += wholeWood+1;
            } else System.out.println("Add one long " + (woodToShip += wholeWood+1));
        }

        System.out.println("Wood to ship " + woodToShip + " " + woodToShip*WOOD_LENGTH);
    }
}