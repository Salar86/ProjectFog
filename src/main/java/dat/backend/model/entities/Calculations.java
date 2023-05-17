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


    public int calculateRafterWidth() {
        //TODO: Make method that calculates the number of boards needed for the different widths of the carport.
        return 0;
    }

    public int calculatePosts(double lengthInCm) {
        int post = 0;

        if (lengthInCm >= 481) {
            post = 6;
        } else post = 4;

        return post;
    }

    public Map<Integer, String> calculateFrontAndBackSternBoards(double lengthInCm) {
        final double WOOD_LENGTH = 540;
        //TODO: SHOUDL RETURN ARRAYLIST? WITH OBJECTS OF BOARDS - LONG OR SHORT

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

        return null;
    }

    public Map<Integer, String> calculateFrontAndBackSternBoardsPrototype(double carportWidthInCm, double carportLengthInCm) {
        double totalCarportSize = (carportWidthInCm*2) + (carportLengthInCm*2);
        for(Map.Entry<String, Double> set: woodList.entrySet()) { // Everything woodtype in Map is run through
            int longWood = 0;
            if (carportWidthInCm <= 540) {
                System.out.println("START - WIDTH " + closeToZeroWidth);
                if ((carportWidthInCm*2 / set.getValue()) % 1 == 0) {
                    System.out.println("SEND XX");
                    if(set.getValue() == carportWidthInCm){ // set.getValue is length of wood.
                        System.out.println("SHIP 2x SINGLE WOOD EQUAL TO get.value " + set.getValue());
                    }
                } else if((totalCarportSize/closeToZeroWidth) <= set.getValue()) { // picks the largest wood that will fit
                    System.out.println(set.getValue());
                    while(totalCarportSize >= set.getValue()){
                        totalCarportSize -= set.getValue(); // Subtracts one piece of wood from total length of carport.
                        longWood += (set.getValue()/set.getValue()); // Calculates how many pieces of wood is used.
                        System.out.println("TOTAL " + totalCarportSize + " " + longWood);
                    }
                } if ((totalCarportSize < set.getValue())){ // Divide remainder with wood-pieces again to find the next one that fits best.
                    System.out.println("REMAINDER " + totalCarportSize + " " + remainder);

                }
            }
        }
        return null;
    }

    public double sternHelperMethod(double carportSizeInCm)
    {
        loadWoodList();
        double closeToZeroWidth = 0;
        for(Map.Entry<String, Double> set: woodList.entrySet()) { // Gets the piece of wood thats is cloesest to coerving the length
            double temp = carportSizeInCm/set.getValue();
            System.out.println("HELPER " + " " + carportSizeInCm + " " + temp);
            System.out.println(closeToZeroWidth);
            if(carportSizeInCm/set.getValue() >= 1){
                if(carportSizeInCm/set.getValue() <= temp){
                    closeToZeroWidth = temp;
                }
            } else if (carportSizeInCm/set.getValue() < 1){
                if(carportSizeInCm/set.getValue() >= closeToZeroWidth){
                    System.out.println("VALUE " + carportSizeInCm/set.getValue());
                    closeToZeroWidth = temp;
                }
            }

        }
        return closeToZeroWidth;
    }
}
