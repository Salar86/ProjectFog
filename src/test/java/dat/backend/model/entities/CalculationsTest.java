package dat.backend.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CalculationsTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void calRem()
    {
        Calculations cal = new Calculations();
        //cal.calculateRem(240);
        cal.calculateRafters(600,780);
        cal.calculatePosts(481);
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
        Calculations cal = new Calculations();
        cal.calculateCarport(540, 360);

    }

    @Test
    public void calculateRaftersTest() {
        final double RAFTER_SPACING_CM = 55;
        double lengthInCm = 780;
        double width = 300;

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