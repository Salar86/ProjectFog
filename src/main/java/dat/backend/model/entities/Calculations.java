package dat.backend.model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculations {
    Map<String, Double> woodList = new HashMap<>();
    double totalCarportSize = 0;
    double closeToZeroWidth = 0;
    double remainder = 0;

    public void loadWoodList()
    {
        woodList.put("woodShort", 360.00);
        woodList.put("woodLong", 540.00);
    }

    public int calculateRafters(double lengthInCm, double width) {
        final double RAFTER_SPACING_CM = 55;

        double rafterCount = lengthInCm / RAFTER_SPACING_CM; // (Decimal) How many rafters will fit inside the roof.
        double rafterValue = rafterCount - (int) rafterCount; // The decimal-points from rafterCount to calculate the excess distance.
        int numberOfRafters = (int) rafterCount; // The whole number of rafters.

        if (rafterValue <= 0.36) { // If its below 0.36 the last rafter will be removed and the back-stern will act last rafter.
            numberOfRafters = numberOfRafters - 1;
        }

        if (width <= 600 && width >= 481) {
            System.out.println("RAFTERS 6 meters"); // TODO: Return the corretct rafter
        } else if (width == 300) {
            numberOfRafters = numberOfRafters/2;
            System.out.println("RaFTERS 6 meters " + numberOfRafters); // TODO: Return the corretct rafter
        } else if (width == 240) {
            numberOfRafters = numberOfRafters/2;
            System.out.println("Rafters 4.8 " + numberOfRafters); // TODO: Return the corretct rafter
        } else System.out.println("Rafters 4.8");

        return numberOfRafters;
    }

    public int calculatePosts(double lengthInCm) {
        int post = 0;

        if (lengthInCm >= 481) {
            post = 6;
        } else post = 4;

        return post;
    }

    public Map<Integer, Integer> calculateFrontAndBackSternBoards(double carportWidthInCm, double carportLengthInCm){

        Map<String, Double> woodList1 = new HashMap<>();
        woodList1.put("woodShort", 540.00);
        woodList1.put("woodLong", 360.00);

        double totalCarportSize = 0, closeToZeroWidth = sternHelperMethod((carportLengthInCm+carportWidthInCm)*2), subtract = 0;
        boolean sidesDone = false, frontAndBackDone = false;


        if (woodList1.containsValue(carportWidthInCm)) {
            if (((carportWidthInCm*2) / carportWidthInCm) % 1 == 0) {
                if (carportWidthInCm == carportWidthInCm) { // set.getValue is length of wood.
                    System.out.println("SHIP 2x SINGLE WOOD FOR SIDES EQUAL TO get.value " + carportWidthInCm);
                    subtract = (carportWidthInCm*2);
                    sidesDone = true;
                }
            }
        }

        if (woodList1.containsValue(carportLengthInCm)) {
            if ((carportLengthInCm * 2 / carportLengthInCm) % 1 == 0) {
                if (carportLengthInCm == carportLengthInCm) { // set.getValue is length of wood.
                    System.out.println("SHIP 2x SINGLE WOOD FOR FRONT AND BACK EQUAL TO get.value " + carportLengthInCm);
                    subtract = (carportLengthInCm*2);
                    frontAndBackDone = true;
                }
            }
        }

        totalCarportSize = (carportWidthInCm*2) + (carportLengthInCm*2);

        int longWood = 0;
        int shortWood = 0;

        if (sidesDone && !frontAndBackDone) {

            totalCarportSize -= subtract;
            closeToZeroWidth = sternHelperMethod(totalCarportSize);
            // picks the largest wood that will fit
            while ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                if (totalCarportSize >= (totalCarportSize / closeToZeroWidth)) {
                    double woodLength = (totalCarportSize / closeToZeroWidth);
                    totalCarportSize -= woodLength;
                    //shortWood += (set.getValue() / set.getValue());
                    subtract = woodLength;
                }
                closeToZeroWidth = sternHelperMethod(totalCarportSize);
            }

            if ((closeToZeroWidth < 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                while (totalCarportSize >= 0) {
                    totalCarportSize -= (totalCarportSize / closeToZeroWidth);
                    //shortWood += (set.getValue() / set.getValue());
                    closeToZeroWidth = sternHelperMethod(totalCarportSize);
                }
            }
            frontAndBackDone = true;
        }

        if (frontAndBackDone && !sidesDone) {
            totalCarportSize -= subtract;
            closeToZeroWidth = sternHelperMethod(totalCarportSize); // picks the largest wood that will fit
            while ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                if (totalCarportSize >= (totalCarportSize / closeToZeroWidth)) {
                    double woodLength = (totalCarportSize / closeToZeroWidth);
                    totalCarportSize -= woodLength;
                    //shortWood += (set.getValue() / set.getValue());
                    subtract = woodLength;
                }
                closeToZeroWidth = sternHelperMethod(totalCarportSize); // Sets the new ratio of wood that fits.
            }


            if ((closeToZeroWidth < 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                while (totalCarportSize >= 0) {
                    totalCarportSize -= (totalCarportSize / closeToZeroWidth);
                    //shortWood += (set.getValue() / set.getValue());
                }
                closeToZeroWidth = sternHelperMethod(totalCarportSize);
            }
            sidesDone = true;
        }

        if (!sidesDone && !frontAndBackDone) {
            while ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                if (totalCarportSize >= 0) {
                    totalCarportSize = totalCarportSize - (totalCarportSize / closeToZeroWidth);
                }
                closeToZeroWidth = sternHelperMethod(totalCarportSize);
            }

            if ((closeToZeroWidth < 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                while ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
                    if (totalCarportSize >= (totalCarportSize / closeToZeroWidth)) {
                        double woodLength = (totalCarportSize / closeToZeroWidth);
                        totalCarportSize -= woodLength;
                        //shortWood += (set.getValue() / set.getValue());
                        subtract = woodLength;
                    }
                    closeToZeroWidth = sternHelperMethod(totalCarportSize);
                }
            }
        }


        if (frontAndBackDone && sidesDone) {
            System.out.println("DONE");
        }
        System.out.println( "EXCESS WOOOOOD " + totalCarportSize);
        return null;
    }

    public double sternHelperMethod(double carportSizeInCm)
    {
        loadWoodList();
        double closeToZeroWidth = 0, big = 0, small = 0;

        for (Map.Entry<String, Double> set : woodList.entrySet()) {
            double temp = carportSizeInCm / set.getValue(); // Calculates how many pieces of a given length of wood is needed to build the stern.

            if(big < temp){big = temp;}
            if(big > temp){small = temp;}

            System.out.println("BIG BIG BIG " + big + "SMALL SMALL SMALL" + small);

            System.out.println("HELPERMETHOD " + " " + carportSizeInCm + " TEMP: " + temp);
            if (carportSizeInCm / set.getValue() > 2) { // If more then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    closeToZeroWidth = temp;
                }
            }
            if (carportSizeInCm / set.getValue() <= 2 && (carportSizeInCm / set.getValue()) % 1 == 0) { // If more then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    closeToZeroWidth = small;
                }
            }
            else if (carportSizeInCm / set.getValue() <= 2 && carportSizeInCm / set.getValue() > 1 && carportSizeInCm / set.getValue() % 1 != 0) { // If more then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    closeToZeroWidth = big;
                    System.out.println("FUCK");
                }
            }
            else if (carportSizeInCm / set.getValue() < 1) { // If less then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() >= closeToZeroWidth) { // Picks the piece of wood that is closest in length.
                    System.out.println("VALUE " + carportSizeInCm / set.getValue());
                    closeToZeroWidth = temp;
                }
            }
            System.out.println("CLOSE TO ZERO VALUE AFTER METHOD: " + closeToZeroWidth);
            System.out.println("SIZE OF CARPORT - HELPERMETHOD " + carportSizeInCm + "\n\n");
        }
        return closeToZeroWidth;
    }

    public Map<Integer, Integer> CalculateSternBoardsMainTest(double carportWidthInCm, double carportLengthInCm){
        loadWoodList();
        System.out.println("BEGENNING");
        Map<Integer, Integer> woodToShip = new HashMap<>();
        double closeToZeroWidth = sternHelperMethod((carportLengthInCm+carportWidthInCm)*2);
        boolean lengthDone = false, widthDone = false;
        int longWood = 0, shortWood = 0;

        if (woodList.containsValue(carportWidthInCm)) {
            System.out.println("START FRONT AND BACK - WIDTH " + closeToZeroWidth);
            //System.out.println("SHIP 2x SINGLE WOOD FOR SIDES EQUAL TO get.value " + carportWidthInCm);
            if(carportWidthInCm == 360){
                shortWood = shortWood+2;;
            } if(carportWidthInCm == 540){longWood = longWood+2;}
            widthDone = true;
            //System.out.println(widthDone);
        }

        if (woodList.containsValue(carportLengthInCm)) {
            //System.out.println("START SIDE - WIDTH " + closeToZeroWidth);
            //System.out.println("SHIP 2x SINGLE WOOD FOR SIDES EQUAL TO get.value " + carportLengthInCm);
            if(carportLengthInCm == 360){
                shortWood = shortWood+2;;
            } if(carportLengthInCm == 540)
            {longWood = longWood+2;}
            lengthDone = true;
            //System.out.println(lengthDone);
        }

        if(longWood > 0){ woodToShip.put(540, longWood);}
        if(shortWood > 0){ woodToShip.put(360, shortWood);}


        for (Map.Entry<Integer, Integer> set : woodToShip.entrySet()) {
            //System.out.println(+ set.getValue() + " " + set.getKey() );
        }

        if(lengthDone && !widthDone){
            woodToShip.put(360, CalculateSternBoardsTest(((carportWidthInCm)*2)).get(360)+woodToShip.get(360));
            woodToShip.put(540, CalculateSternBoardsTest(((carportWidthInCm)*2)).get(540)+woodToShip.get(540));
            System.out.println("Updated MAP LENGTH" + woodToShip);
        } else if(!lengthDone && widthDone){
            Map<Integer, Integer> temp = CalculateSternBoardsTest(((carportLengthInCm)*2));
            System.out.println("TEMPTEMP +" + temp);
            woodToShip.put(360, temp.get(360)+woodToShip.get(360));
            woodToShip.put(540, temp.get(540)+woodToShip.get(540));
            System.out.println("Updated MAP WIDTH " + woodToShip);
        } else if(!lengthDone && !widthDone){
            //CalculateSternBoardsTest(((carportWidthInCm+carportLengthInCm)*2));
            Map<Integer, Integer> temp = CalculateSternBoardsTest(((carportLengthInCm+carportWidthInCm)*2));
            System.out.println("TEMPTEMP +" + temp);
            System.out.println("Okay Anette " + temp.get(360) + " " + temp.get(540));

            woodToShip.put(360, temp.get(360));
            woodToShip.put(540, temp.get(540));
            System.out.println("Updated MAP WIDTH " + woodToShip);
        }
        for (Map.Entry<Integer, Integer> set : woodToShip.entrySet()) {
            System.out.println(+ set.getValue() + " " + set.getKey() );
        }
        return woodToShip;
    }

    public Map<Integer, Integer> CalculateSternBoardsTest(double totalCarportSize)
    {
        Map<Integer, Integer> woodToShip = new HashMap<>();
        int longWood = 0, shortWood = 0;
        //System.out.println(set.getValue() + " VALUE OF WOOD");
        closeToZeroWidth = sternHelperMethod(totalCarportSize);
        double woodLength = 0;
        // picks the largest wood that will fit
        while ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
            if (totalCarportSize >= (totalCarportSize / closeToZeroWidth)) {
                woodLength = (totalCarportSize / closeToZeroWidth);
                //System.out.println("\n" + totalCarportSize + " - " + woodLength + " = "  + (totalCarportSize-woodLength));
                totalCarportSize -= woodLength;
                //shortWood += (set.getValue() / set.getValue());
                //System.out.println("\n" + woodLength + "\n");
                if(woodLength == 540){
                    longWood++;
                } else shortWood++;
            }
            closeToZeroWidth = sternHelperMethod(totalCarportSize);
            //System.out.println("SIZE CAPRPORT OVER 1 " + totalCarportSize);
            //System.out.println("SHIP shortwood = " + shortWood);
            //System.out.println("New Size " + cal.sternHelperMethod(totalCarportSize));
        }

        if ((closeToZeroWidth <= 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
            while (totalCarportSize > 0) { // RUNS UNTIL CARPORT IS 0
                //System.out.println("\n SIZE OF CARPORT BEFORE WHILE " + totalCarportSize );
                woodLength = (totalCarportSize / closeToZeroWidth);
                //System.out.println("\n" + totalCarportSize + " - " + woodLength + " = "  + (totalCarportSize-woodLength));
                totalCarportSize -= (woodLength);

                if(woodLength == 540){
                    longWood++;
                } else shortWood++;

                //shortWood += (set.getValue() / set.getValue());
                //System.out.println("\n \n INSIDE ONE");
                //System.out.println("\n" + woodLength + "\n");
            }
            //System.out.println("SIZE CAPRPORT UNDER 1 " + totalCarportSize);
            //System.out.println("SHIP shortwood = " + shortWood);

            //System.out.println("New Size " + sternHelperMethod(totalCarportSize));
        } else System.out.println("DONE");

        //System.out.println("FINAL SIZE" + totalCarportSize);
        if(longWood > 0){woodToShip.put(540, longWood);} else woodToShip.put(540, 0);

        if(shortWood > 0){woodToShip.put(360, shortWood);} else woodToShip.put(360, 0);

        //System.out.println("\nWOOD TO SHIP MAP ");
        for (Map.Entry<Integer, Integer> set : woodToShip.entrySet()) {
            System.out.println(+ set.getKey() + " " + set.getValue() );
        }
        return woodToShip;
    }

}

