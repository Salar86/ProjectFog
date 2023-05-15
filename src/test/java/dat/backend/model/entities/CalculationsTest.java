package dat.backend.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Map;

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

        double carportWidthInCm = 390; // Parameters
        double carportLengthInCm = 390; // Parameters

        //double woodShort = 360;
        //double woodLong = 540;

        Map<String, Double> woodList = new HashMap<>();
        woodList.put("woodShort", 360.00);
        woodList.put("woodLong", 540.00);

        /*if(lengthInCm == 1440){
            woodLength = 360;
        }*/

        double woodNeeded = 0;
        double woodRatio = 0;
        double wholeWood = 0;
        double remainder;
        double closeToOneWidth = 0;
        double closeToOneLength = 0;
        boolean yes = false;

        for(Map.Entry<String, Double> set: woodList.entrySet()) { // Gets the piece of wood thats is cloesest to coerving the length
            if(carportWidthInCm / set.getValue() >= closeToOneWidth){
                System.out.println(set.getValue());
                System.out.println("closetoWidth " + (carportWidthInCm) / set.getValue());
                System.out.println(set.getValue());
                closeToOneWidth = carportWidthInCm / set.getValue();
                System.out.println(carportWidthInCm);
            }
        }

        for(Map.Entry<String, Double> set: woodList.entrySet()) { // Gets the piece of wood thats is cloesest to coerving the length
            if(carportLengthInCm / set.getValue() >= closeToOneLength){
                System.out.println(set.getValue());
                System.out.println("closetoLength " + (carportLengthInCm) / set.getValue());
                System.out.println(set.getValue());
                closeToOneLength = carportLengthInCm / set.getValue();
                System.out.println(carportLengthInCm);
            }
        }

        // All calculations are based on a single side.

        for(Map.Entry<String, Double> set: woodList.entrySet()) { // Everything woodtype in Map is run through

            if (carportWidthInCm <= 540) {
                System.out.println("START SHORT" + closeToOneWidth);
                if ((carportWidthInCm / set.getValue()) % 1 == 0) {
                    System.out.println("SEND XX");
                    if(set.getValue() == carportWidthInCm){ // set.getValue is length of wood.
                        System.out.println("SHIP 2x WOOD EQUAL TO get.value " + set.getValue());
                    }
                } else if (set.getValue() == carportWidthInCm*2){
                    System.out.println("SHIP 1x WOOD EQUAL TO GET VALUE " +  set.getValue()); // ONE WHOLE PIECE OF WOOD
                } else if ((carportWidthInCm*2 / set.getValue()) >= closeToOneWidth && (carportWidthInCm / set.getValue()) < 1) { // Calculates the
                    System.out.println("SENDT " + set.getKey()); // Mangler at få en lille med
                    System.out.println(carportWidthInCm / set.getValue() + set.getKey());
                }
            } else if (carportWidthInCm >= 541 && (carportWidthInCm*2 / set.getValue()) < 2.5) { // last part makes sure only set.getValue is used.
                System.out.println("2 x long " + "1 x short"); // Combined width is 1200 - so 2 x long 1 short
                /*woodNeeded = (carportWidthInCm / set.getValue());
                woodRatio = woodNeeded - (int) woodNeeded;
                wholeWood = woodNeeded - woodRatio;
                remainder = carportWidthInCm - (wholeWood * set.getValue());
                System.out.println(woodNeeded);
                System.out.println("re " + remainder);
                if (remainder > 0 && remainder <= 180) {
                    System.out.println("2 x Long and 1 Short");
                }*/
                // Can Add More Functionality for more wood or wider carport.
            }
        }

        for(Map.Entry<String, Double> set: woodList.entrySet()) {
            if (carportLengthInCm <= 540) {
                System.out.println("START - Length " + closeToOneLength);
                if ((carportLengthInCm / set.getValue()) % 1 == 0) {
                    System.out.println("LENGTH SEND XX - Length");
                    if (set.getValue() == carportLengthInCm) { // set.getValue is length of wood.
                        System.out.println("LENGTH SHIP 2x WOOD EQUAL TO get.value " + set.getValue());
                    }
                } else if (set.getValue() == carportLengthInCm * 2) {
                    System.out.println("LENGTH SHIP 1x WOOD EQUAL TO GET VALUE " + set.getValue()); // ONE WHOLE PIECE OF WOOD
                } else if ((carportLengthInCm * 2 / set.getValue()) >= closeToOneLength && (carportLengthInCm / set.getValue()) < 1) { // Calculates the
                    System.out.println("LENGTH SENDT " + set.getKey()); // Mangler at få en lille med
                    System.out.println(carportLengthInCm / set.getValue() + set.getKey());
                }
            } else if (carportLengthInCm >= 541) {
                System.out.println("LENGTH - 2 x long " + "1 x short");
                /*woodNeeded = (carportWidthInCm / set.getValue());
                woodRatio = woodNeeded - (int) woodNeeded;
                wholeWood = woodNeeded - woodRatio;
                remainder = carportWidthInCm - (wholeWood * set.getValue());
                System.out.println(woodNeeded);
                System.out.println("re " + remainder);
                if (remainder > 0 && remainder <= 180) {
                    System.out.println("2 x Long and 1 Short");
                }*/
                // Can Add More Functionality for more wood or wider carport.
            }
        }

        /*if(carportLengthInCm <= 541){
            if((carportLengthInCm/woodShort) % 1 == 0){
                System.out.println("less 540 - 2 x Short");
            }
            else if((carportLengthInCm/woodLong) % 1 == 0){
                System.out.println("540 - 2 x Long");
            } else if((carportLengthInCm <= 720)){
                System.out.println("less 720 - 4 x Short");
            }
        }*/
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