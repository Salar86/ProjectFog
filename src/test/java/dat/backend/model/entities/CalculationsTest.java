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
        Calculations cal = new Calculations();

        Map<String, Double> woodList1 = new HashMap<>();
        woodList1.put("woodShort", 540.00);
        woodList1.put("woodLong", 360.00);

        double carportWidthInCm = 570.00;
        double carportLengthInCm = 780.00;

        double totalCarportSize = 0;
        double closeToZeroWidth = cal.sternHelperMethod((carportLengthInCm+carportWidthInCm)*2);
        double remainder = 0;
        boolean sidesDone = false;
        boolean frontAndBackDone = false;
        double subtract = 0;

        if (woodList1.containsValue(carportWidthInCm)) {
            System.out.println("START - WIDTH " + closeToZeroWidth);
            if ((carportWidthInCm * 2 / carportWidthInCm) % 1 == 0) {
                System.out.println("SEND XX");
                if (carportWidthInCm == carportWidthInCm) { // set.getValue is length of wood.
                    System.out.println("SHIP 2x SINGLE WOOD FOR SIDES EQUAL TO get.value " + carportWidthInCm);
                    subtract = (carportWidthInCm*2);
                    sidesDone = true;
                    System.out.println(sidesDone);
                }
            }
        }

        if (woodList1.containsValue(carportLengthInCm)) {
            System.out.println("START - LENGTH " + closeToZeroWidth);
            if ((carportLengthInCm * 2 / carportLengthInCm) % 1 == 0) {
                System.out.println("SEND XX");
                if (carportLengthInCm == carportLengthInCm) { // set.getValue is length of wood.
                    System.out.println("SHIP 2x SINGLE WOOD FOR FRONT AND BACK EQUAL TO get.value " + carportLengthInCm);
                    subtract = (carportLengthInCm*2);
                    frontAndBackDone = true;
                    System.out.println(frontAndBackDone);

                }
            }
        }


        totalCarportSize = (carportWidthInCm*2) + (carportLengthInCm*2);

        for(Map.Entry<String, Double> set: woodList1.entrySet()) { // Every woodtype in Map is run through
            int longWood = 0;
            int shortWood = 0;


            if(sidesDone && !frontAndBackDone) {
                System.out.println(totalCarportSize + "SIDES DONE - CALCULATING FRONT AND BACK");
                totalCarportSize -= subtract;
                System.out.println(totalCarportSize + "æaww231æ3213o1239210321");
                System.out.println(set.getValue() + " sdjæakjd39438723911''¨¨s1");
                closeToZeroWidth = cal.sternHelperMethod(totalCarportSize);
                // picks the largest wood that will fit
                if ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                    if (totalCarportSize >= (totalCarportSize/closeToZeroWidth)) {
                        double woodLength = (totalCarportSize/closeToZeroWidth);
                        totalCarportSize -= woodLength;
                        shortWood += (set.getValue() / set.getValue());
                        subtract = woodLength;
                    }
                    System.out.println("SIZE CAPRPORT " + totalCarportSize);
                    System.out.println("SHIP shortwood = " + shortWood);

                    System.out.println("New Size " + cal.sternHelperMethod(totalCarportSize));
                }
                System.out.println(subtract + " SUBSUBSUSBUSBUSBUSBUSBUSBSUBUS");

                System.out.println(totalCarportSize + "TETSTSTETSTSTETSTTETETSTSTE");
                closeToZeroWidth = cal.sternHelperMethod(totalCarportSize);
                System.out.println(closeToZeroWidth + "CLOSE TO ZERO");
                if ((closeToZeroWidth < 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                    while (totalCarportSize >= 0) {
                        totalCarportSize -= (totalCarportSize/closeToZeroWidth);
                        shortWood += (set.getValue() / set.getValue());
                    }
                    System.out.println("SIZE CAPRPORT TWOWOWOWOW " + totalCarportSize);
                    System.out.println("SHIP shortwood = " + shortWood);

                    System.out.println("New Size " + cal.sternHelperMethod(totalCarportSize));
                }

            }

            if(frontAndBackDone && !sidesDone) {
                System.out.println(totalCarportSize + "FRONT AND BACK DONE - CALCULATING LENGTH");
                totalCarportSize -= subtract;
                System.out.println(totalCarportSize + "REMANING LENGTH");
                System.out.println(set.getValue() + " VALUE OF WOOD");
                closeToZeroWidth = cal.sternHelperMethod(totalCarportSize);
                // picks the largest wood that will fit
                if ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                    if (totalCarportSize >= (totalCarportSize/closeToZeroWidth)) {
                        double woodLength = (totalCarportSize/closeToZeroWidth);
                        totalCarportSize -= woodLength;
                        shortWood += (set.getValue() / set.getValue());
                        subtract = woodLength;
                    }
                    System.out.println("SIZE CAPRPORT OVER 1 " + totalCarportSize);
                    System.out.println("SHIP shortwood = " + shortWood);

                    System.out.println("New Size " + cal.sternHelperMethod(totalCarportSize));
                }
                System.out.println(subtract + " SUBSUBSUSBUSBUSBUSBUSBUSBSUBUS");

                System.out.println(totalCarportSize + "TETSTSTETSTSTETSTTETETSTSTE");
                closeToZeroWidth = cal.sternHelperMethod(totalCarportSize);
                System.out.println(closeToZeroWidth + "aædkajw NAPWOEÅ");
                if ((closeToZeroWidth < 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                    while (totalCarportSize >= 0) {
                        totalCarportSize -= (totalCarportSize/closeToZeroWidth);
                        shortWood += (set.getValue() / set.getValue());
                    }
                    System.out.println("SIZE CAPRPORT UNDER 1 " + totalCarportSize);
                    System.out.println("SHIP shortwood = " + shortWood);

                    System.out.println("New Size " + cal.sternHelperMethod(totalCarportSize));
                }

                System.out.println("FINAL SIZE" + totalCarportSize);
            }

            if(!sidesDone && !frontAndBackDone) {
                System.out.println(totalCarportSize + "CALCULATING WOOOOOOD" + closeToZeroWidth);
                System.out.println(totalCarportSize + "Size of carport");
                System.out.println(set.getValue() + " value used");
                //closeToZeroWidth = cal.sternHelperMethod(totalCarportSize);
                // picks the largest wood that will fit
                if ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                    System.out.println(" BEFORE WHILE " + totalCarportSize);
                    System.out.println(closeToZeroWidth);
                    System.out.println(totalCarportSize);
                    if (totalCarportSize >= (totalCarportSize/closeToZeroWidth)) {
                        double woodLength = (totalCarportSize/closeToZeroWidth);
                        totalCarportSize -= woodLength;
                        shortWood += (set.getValue() / set.getValue());
                        subtract = woodLength;
                    }
                    System.out.println("SIZE CAPRPORT " + totalCarportSize);
                    System.out.println("SHIP shortwood = " + shortWood);

                    System.out.println("New Size " + cal.sternHelperMethod(totalCarportSize));
                }
                System.out.println(subtract + " SUBSUBSUSBUSBUSBUSBUSBUSBSUBUS");

                System.out.println(totalCarportSize + "TETSTSTETSTSTETSTTETETSTSTE");

                System.out.println(closeToZeroWidth + "aædkajw NAPWOEÅ");

                if ((closeToZeroWidth < 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                    while (totalCarportSize >= 0) {
                        totalCarportSize -= (totalCarportSize/closeToZeroWidth);
                        shortWood += (set.getValue() / set.getValue());
                    }
                    System.out.println("SIZE CAPRPORT TWOWOWOWOW " + totalCarportSize);
                    System.out.println("SHIP shortwood = " + shortWood);

                    System.out.println("New Size " + cal.sternHelperMethod(totalCarportSize));
                }
                closeToZeroWidth = cal.sternHelperMethod(totalCarportSize);
            }

            if(frontAndBackDone) {
                System.out.println("DONE");
            }

        }

        System.out.println( "EXCESS WOOOOOD " + totalCarportSize);
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