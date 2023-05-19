package dat.backend.model.entities;

import java.util.HashMap;
import java.util.Map;

public class Calculations {
    Map<String, Double> woodList = new HashMap<>();
    double closeToZeroWidth = 0;

    public Map<Integer, Integer> calculateCarport(double carportWidthInCm, double carportLengthInCm){
        Map<Integer, Integer> itemList = new HashMap<>();

        itemList.putAll(calculatePosts(carportLengthInCm));
        itemList.putAll(calculateRafters(carportWidthInCm, carportLengthInCm));
        itemList.putAll(calculateRem(carportWidthInCm));
        itemList.putAll(calculateSternBoardsMainMethod(carportWidthInCm, carportLengthInCm));

        System.out.println(itemList);

        return itemList;
    }

    public void loadWoodList()
    {
        woodList.put("woodShort", 360.00);
        woodList.put("woodLong", 540.00);
    }

    public Map<Integer, Integer> calculateRem(double carportLengthInCm) {
        int remCount = 0;
        Map<Integer, Integer> remList= new HashMap<>();

        if (carportLengthInCm <= 240) {
            remList.put(3, 1);
        } else if (carportLengthInCm > 240 && carportLengthInCm <= 480){
            remList.put(3, 2);
        } else if (carportLengthInCm > 480) {
            remList.put(4, 2);
            remList.put(3, 1);
        }

        return remList;
    }

    public Map<Integer, Integer> calculateRafters(double carportWidthInCm, double carportLengthInCm) {
        final double RAFTER_SPACING_CM = 55;

        Map<Integer, Integer> rafterList= new HashMap<>();

        double rafterCount = carportLengthInCm / RAFTER_SPACING_CM; // (Decimal) How many rafters will fit inside the roof.
        double rafterValue = rafterCount - (int) rafterCount; // The decimal-points from rafterCount to calculate the excess distance.
        int numberOfRafters = (int) rafterCount; // The whole number of rafters.

        if (rafterValue <= 0.36) { // If its below 0.36 the last rafter will be removed and the back-stern will act last rafter.
            numberOfRafters = numberOfRafters - 1;
        }

        if (carportWidthInCm <= 600 && carportWidthInCm >= 481) {
            rafterList.put(4, numberOfRafters);
        } else if (carportWidthInCm <= 480 && carportWidthInCm >= 300) {
            rafterList.put(3, numberOfRafters);
        } else if (carportWidthInCm == 300) {
            numberOfRafters = numberOfRafters/2;
            rafterList.put(4, numberOfRafters);
        } else if (carportWidthInCm == 240) {
            numberOfRafters = numberOfRafters/2;
            rafterList.put(3, numberOfRafters);
        }

        return rafterList;
    }

    public Map<Integer, Integer> calculatePosts(double lengthInCm) {
        Map<Integer, Integer> postList= new HashMap<>();

        if (lengthInCm >= 481) {
            postList.put(5, 6);
        } else postList.put(5, 4);
        return postList;
    }

    public Map<Integer, Integer> calculateSternBoardsMainMethod(double carportWidthInCm, double carportLengthInCm){
        loadWoodList();

        Map<Integer, Integer> woodToShip = new HashMap<>();
        double closeToZeroWidth = sternHelperMethod((carportLengthInCm+carportWidthInCm)*2);
        boolean lengthDone = false, widthDone = false;
        int longWood = 0, shortWood = 0;

        if (woodList.containsValue(carportWidthInCm)) {
            //System.out.println("START FRONT AND BACK - WIDTH " + closeToZeroWidth);
            //System.out.println("SHIP 2x SINGLE WOOD FOR SIDES EQUAL TO get.value " + carportWidthInCm);
            if(carportWidthInCm == 360) {
                shortWood = shortWood+2;;
            } if(carportWidthInCm == 540){ longWood = longWood+2; }
            widthDone = true;
            //System.out.println(widthDone);
        }

        if (woodList.containsValue(carportLengthInCm)) {
            //System.out.println("START SIDE - WIDTH " + closeToZeroWidth);
            //System.out.println("SHIP 2x SINGLE WOOD FOR SIDES EQUAL TO get.value " + carportLengthInCm);
            if(carportLengthInCm == 360) {
                shortWood = shortWood+2;;
            } if(carportLengthInCm == 540) { longWood = longWood+2; }
            lengthDone = true;
            //System.out.println(lengthDone);
        }

        if(longWood > 0){ woodToShip.put(2, longWood);} else woodToShip.put(2, 0);
        if(shortWood > 0){ woodToShip.put(1, shortWood);} else woodToShip.put(1, 0);


        for (Map.Entry<Integer, Integer> set : woodToShip.entrySet()) {
            //System.out.println(+ set.getValue() + " " + set.getKey() );
        }

        if(lengthDone && !widthDone){
            woodToShip.put(1, CalculateSternBoards(((carportWidthInCm)*2)).get(1)+woodToShip.get(1));
            woodToShip.put(2, CalculateSternBoards(((carportWidthInCm)*2)).get(2)+woodToShip.get(2));
            //System.out.println("Updated MAP LENGTH" + woodToShip);
        } else if(!lengthDone && widthDone){
            //Map<Integer, Integer> temp = CalculateSternBoards(((carportLengthInCm)*2));
            //System.out.println("TEMPTEMP +" + temp);
            woodToShip.put(1, CalculateSternBoards(((carportLengthInCm)*2)).get(1)+woodToShip.get(1));
            woodToShip.put(2, CalculateSternBoards(((carportLengthInCm)*2)).get(2)+woodToShip.get(2));
            //System.out.println("Updated MAP WIDTH " + woodToShip);
        } else if(!lengthDone && !widthDone){
            //CalculateSternBoardsTest(((carportWidthInCm+carportLengthInCm)*2));
            //Map<Integer, Integer> temp = CalculateSternBoards(((carportLengthInCm+carportWidthInCm)*2));
            //System.out.println("TEMPTEMP +" + temp);
            //System.out.println("Okay Anette " + temp.get(360) + " " + temp.get(540));

            woodToShip.put(1, CalculateSternBoards(((carportLengthInCm+carportWidthInCm)*2)).get(1));
            woodToShip.put(2, CalculateSternBoards(((carportLengthInCm+carportWidthInCm)*2)).get(2));
            //System.out.println("Updated MAP WIDTH " + woodToShip);
        }
        /*for (Map.Entry<Integer, Integer> set : woodToShip.entrySet()) {
            System.out.println(+ set.getValue() + " " + set.getKey() );
        }*/
        return woodToShip;
    }

    public Map<Integer, Integer> CalculateSternBoards(double totalCarportSize)
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
        if(longWood > 0){woodToShip.put(2, longWood);} else woodToShip.put(2, 0);

        if(shortWood > 0){woodToShip.put(1, shortWood);} else woodToShip.put(1, 0);

        //System.out.println("\nWOOD TO SHIP MAP ");
        /*for (Map.Entry<Integer, Integer> set : woodToShip.entrySet()) {
            System.out.println(+ set.getKey() + " " + set.getValue() );
        }*/
        return woodToShip;
    }

    public double sternHelperMethod(double carportSizeInCm)
    {
        loadWoodList();
        double closeToZeroWidth = 0, big = 0, small = 0;

        for (Map.Entry<String, Double> set : woodList.entrySet()) {
            double temp = carportSizeInCm / set.getValue(); // Calculates how many pieces of a given length of wood is needed to build the stern.

            if(big < temp){big = temp;}
            if(big > temp){small = temp;}

            //System.out.println("BIG BIG BIG " + big + "SMALL SMALL SMALL" + small);

            //System.out.println("HELPERMETHOD " + " " + carportSizeInCm + " TEMP: " + temp);
            if (carportSizeInCm / set.getValue() > 2) { // If more then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    //System.out.println("METHOD ONE \n");
                    closeToZeroWidth = temp;
                }
            }
            if (carportSizeInCm / set.getValue() <= 2 && carportSizeInCm / set.getValue() > 1 && (carportSizeInCm / set.getValue()) % 1 == 0) { // If 2 pieces or under and they are whole pieces.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    //System.out.println("METHOD TWO \n");
                    closeToZeroWidth = small;
                }
            }
            else if (carportSizeInCm / set.getValue() <= 2 && carportSizeInCm / set.getValue() > 1 && carportSizeInCm / set.getValue() % 1 != 0) { // If more then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    //System.out.println("METHOD THREE \n");
                    closeToZeroWidth = big;
                }
            }
            else if (carportSizeInCm / set.getValue() <= 1) { // If less then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() >= closeToZeroWidth) { // Picks the piece of wood that is closest in length.
                    //System.out.println("METHOD FOUR \n");
                    //System.out.println(closeToZeroWidth + " CLOSE");
                    //System.out.println("VALUE " + carportSizeInCm / set.getValue());
                    closeToZeroWidth = temp;
                }
            }
            //System.out.println("CLOSE TO ZERO VALUE AFTER METHOD: " + closeToZeroWidth);
            //System.out.println("SIZE OF CARPORT - HELPERMETHOD " + carportSizeInCm + " " + carportSizeInCm/closeToZeroWidth + "\n\n");
        }
        return closeToZeroWidth;
    }

}

