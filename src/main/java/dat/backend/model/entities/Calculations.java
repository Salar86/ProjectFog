package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ProductFacade;
import dat.backend.model.persistence.ProductVariantFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Calculations {
    Map<String, Double> woodList = new HashMap<>();
    double closeToZeroWidth = 0;

    public ArrayList<ItemList> calculateCarport(int orderId, double carportWidthInCm, double carportLengthInCm) throws DatabaseException {
        ConnectionPool connectionPool = new ConnectionPool();
        ArrayList<ItemList> itemLists = new ArrayList<>();
        double pricePost = ProductFacade.getPrice(3, connectionPool), priceRaftersAndRem = ProductFacade.getPrice(2, connectionPool), priceStern = ProductFacade.getPrice(1, connectionPool);

        ItemList posts = new ItemList("Stolper skal graves ned i jorden.", ((int)(pricePost*ProductVariantFacade.getProductVariantQuantity(5, connectionPool))*calculatePosts(carportLengthInCm).get(5)), orderId, 5, calculatePosts(carportLengthInCm).get(5));
        itemLists.add(posts);

        if(calculateRem(carportLengthInCm).get(3) > 0){
        ItemList remShort = new ItemList("Remme skal sadles ned i stolperne.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))*calculateRem(carportLengthInCm).get(3)), orderId, 3, calculateRem(carportLengthInCm).get(3));
            itemLists.add(remShort);
        }

        if(calculateRem(carportLengthInCm).get(4) > 0){
        ItemList remLong = new ItemList("Remme skal sadles ned i stolperne.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(4, connectionPool))*calculateRem(carportLengthInCm).get(4)), orderId, 4, calculateRem(carportLengthInCm).get(4));
            itemLists.add(remLong);
        }

        if(calculateRafters(carportWidthInCm, carportLengthInCm).get(3) > 0){
            ItemList raftersShort = new ItemList("Spær skal Korte...", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))*calculateRafters(carportWidthInCm,carportLengthInCm).get(3)), orderId, 3, calculateRafters(carportWidthInCm,carportLengthInCm).get(3));
            itemLists.add(raftersShort);
        }

        if(calculateRafters(carportWidthInCm, carportLengthInCm).get(4) > 0){
            ItemList raftersLong = new ItemList("Spær skal Lange...", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(4, connectionPool))*calculateRafters(carportWidthInCm,carportLengthInCm).get(4)), orderId, 4, calculateRafters(carportWidthInCm,carportLengthInCm).get(4));
            itemLists.add(raftersLong);
        }

        if(calculateSternBoardsMainMethod(carportWidthInCm, carportLengthInCm).get(1) > 0){
            ItemList sternShort = new ItemList("Stern skal Korte...", (int)((priceStern*ProductVariantFacade.getProductVariantQuantity(1, connectionPool))*calculateSternBoardsMainMethod(carportWidthInCm,carportLengthInCm).get(1)), orderId, 1, calculateSternBoardsMainMethod(carportWidthInCm,carportLengthInCm).get(1));
            itemLists.add(sternShort);
        }

        if(calculateSternBoardsMainMethod(carportWidthInCm, carportLengthInCm).get(2) > 0){
            ItemList sternLong = new ItemList("Stern skal Lange...", (int)((priceStern*ProductVariantFacade.getProductVariantQuantity(2, connectionPool))*calculateSternBoardsMainMethod(carportWidthInCm,carportLengthInCm).get(2)), orderId, 2, calculateSternBoardsMainMethod(carportWidthInCm,carportLengthInCm).get(2));
            itemLists.add(sternLong);
        }

        return itemLists;
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
            remList.put(4, 0);
        } else if (carportLengthInCm > 240 && carportLengthInCm <= 480){
            remList.put(3, 2);
            remList.put(4, 0);
        } else if (carportLengthInCm > 480 && carportLengthInCm <= 600) {
            remList.put(4, 2);
            remList.put(3, 0);
        } else if (carportLengthInCm > 600) {
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
            rafterList.put(3, 0);
        } else if (carportWidthInCm <= 480 && carportWidthInCm >= 301) {
            rafterList.put(3, numberOfRafters);
            rafterList.put(4, 0);
        } else if (carportWidthInCm == 300) {
            numberOfRafters = numberOfRafters/2;
            rafterList.put(4, numberOfRafters);
            rafterList.put(3, 0);
        } else if (carportWidthInCm == 240) {
            numberOfRafters = numberOfRafters/2;
            rafterList.put(3, numberOfRafters);
            rafterList.put(4, 0);
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

        Map<Integer, Integer> woodToShip = new HashMap<>();
        boolean lengthDone = false, widthDone = false;
        int longWood = 0, shortWood = 0;

        if (woodList.containsValue(carportWidthInCm)) {
            if(carportWidthInCm == 360) {
                shortWood = shortWood+2;;
            } if(carportWidthInCm == 540){ longWood = longWood+2; }
            widthDone = true;
        }

        if (woodList.containsValue(carportLengthInCm)) {
            if(carportLengthInCm == 360) {
                shortWood = shortWood+2;;
            } if(carportLengthInCm == 540) { longWood = longWood+2; }
            lengthDone = true;
        }

        if(longWood > 0){ woodToShip.put(2, longWood);} else woodToShip.put(2, 0);
        if(shortWood > 0){ woodToShip.put(1, shortWood);} else woodToShip.put(1, 0);


        if(lengthDone && !widthDone){
            woodToShip.put(1, CalculateSternBoards(((carportWidthInCm)*2)).get(1)+woodToShip.get(1));
            woodToShip.put(2, CalculateSternBoards(((carportWidthInCm)*2)).get(2)+woodToShip.get(2));
        } else if(!lengthDone && widthDone){
            woodToShip.put(1, CalculateSternBoards(((carportLengthInCm)*2)).get(1)+woodToShip.get(1));
            woodToShip.put(2, CalculateSternBoards(((carportLengthInCm)*2)).get(2)+woodToShip.get(2));
        } else if(!lengthDone && !widthDone){
            woodToShip.put(1, CalculateSternBoards(((carportLengthInCm+carportWidthInCm)*2)).get(1));
            woodToShip.put(2, CalculateSternBoards(((carportLengthInCm+carportWidthInCm)*2)).get(2));
        }
        return woodToShip;
    }

    public Map<Integer, Integer> CalculateSternBoards(double totalCarportSize)
    {
        Map<Integer, Integer> woodToShip = new HashMap<>();
        int longWood = 0, shortWood = 0;
        closeToZeroWidth = sternHelperMethod(totalCarportSize);
        double woodLength = 0;
        // picks the largest wood that will fit
        while ((closeToZeroWidth > 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
            if (totalCarportSize >= (totalCarportSize / closeToZeroWidth)) {
                woodLength = (totalCarportSize / closeToZeroWidth);
                totalCarportSize -= woodLength;
                if(woodLength == 540){
                    longWood++;
                } else shortWood++;
            }
            closeToZeroWidth = sternHelperMethod(totalCarportSize);
        }

        if ((closeToZeroWidth <= 1)) { // Divide remainder with wood-pieces again to find the next one that fits best.
            while (totalCarportSize > 0) { // RUNS UNTIL CARPORT IS 0
                woodLength = (totalCarportSize / closeToZeroWidth);
                totalCarportSize -= (woodLength);
                if(woodLength == 540){
                    longWood++;
                } else shortWood++;
            }
        }

        if(longWood > 0){woodToShip.put(2, longWood);} else woodToShip.put(2, 0);

        if(shortWood > 0){woodToShip.put(1, shortWood);} else woodToShip.put(1, 0);

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

            if (carportSizeInCm / set.getValue() > 2) { // If more then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    closeToZeroWidth = temp;
                }
            }
            if (carportSizeInCm / set.getValue() <= 2 && carportSizeInCm / set.getValue() > 1 && (carportSizeInCm / set.getValue()) % 1 == 0) { // If 2 pieces or under and they are whole pieces.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    closeToZeroWidth = small;
                }
            }
            else if (carportSizeInCm / set.getValue() <= 2 && carportSizeInCm / set.getValue() > 1 && carportSizeInCm / set.getValue() % 1 != 0) { // If more then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() <= temp) { // Picks the largest piece of wood that fits.
                    closeToZeroWidth = big;
                }
            }
            else if (carportSizeInCm / set.getValue() <= 1) { // If less then one piece of wood is needed.
                if (carportSizeInCm / set.getValue() >= closeToZeroWidth) { // Picks the piece of wood that is closest in length.
                    closeToZeroWidth = temp;
                }
            }
        }
        return closeToZeroWidth;
    }

}

