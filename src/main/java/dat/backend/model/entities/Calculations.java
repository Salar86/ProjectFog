package dat.backend.model.entities;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ProductFacade;
import dat.backend.model.persistence.ProductVariantFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *The Calculations class calculates the products required to build the carport being processed.
 * @author Michael Meyer
 * */
public class Calculations {
    ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    Map<String, Double> woodList = new HashMap<>();
    double closeToZeroWidth = 0;

    /**
     * The method parameters are received from the showAllOrdersForAdmin.jsp through the generateitemlist-servlet where the method is called.
     * The method makes four additional calls to other methods in-class to calculate the products needed for the carport being processed.
     * The return values from each method is added to an arraylist of itemlist-objects. Each object contains the specific product information
     * for the carport being processed. The method returns a complete itemlist as an arraylist.
     * @param orderId The orderId from the order being processed.
     * @param carportWidthInCm The width of the carport being processed.
     * @param carportLengthInCm The length of the carport being processed.
     * @return                  An ArrayList of itemlist-objects for the order being processed.
     * */
    public ArrayList<ItemList> calculateCarport(int orderId, double carportWidthInCm, double carportLengthInCm) throws DatabaseException {

        ArrayList<ItemList> itemListEntries = new ArrayList<>();

        itemListEntries.addAll(calculateRem(carportLengthInCm, orderId));
        itemListEntries.addAll(calculatePosts(carportLengthInCm, orderId));
        itemListEntries.addAll(calculateRafters(carportWidthInCm,carportLengthInCm, orderId));
        itemListEntries.addAll(calculateSternBoardsMainMethod(carportWidthInCm, carportLengthInCm, orderId));

        return itemListEntries;
    }

    /**
     * The method loads a HashMap with the types of wood used for the stern, that's in stock at Fog's. Due to the fact we are only
     * operation with two types of wood, this has been hardcoded.
     * The Key is meant to be the productId and the value the length of the wood.
     * The Map is mainly used in the SternHelperMethod to calculate which piece of wood fits the carport being processed.
     * */
    public void loadWoodList()
    {
        woodList.put("woodShort", 360.00);
        woodList.put("woodLong", 540.00);
    }

    /**
     * The method calculates how many pieces of wood is needed for the rems, for the carport being processed.
     * @param carportLengthInCm The length of the carport being processed.
     * @param orderId The orderId for the carport being processed.
     * @return An arraylist with itemList-objects of rems.
     * */
    public ArrayList<ItemList> calculateRem(double carportLengthInCm, int orderId) throws DatabaseException {
        ArrayList<ItemList> remList = new ArrayList<>();
        double priceRaftersAndRem = ProductFacade.getPrice(2, connectionPool);

        if (carportLengthInCm <= 240) {
            remList.add(new ItemList("480mm remme skal sadles ned i stolperne.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))), orderId, 3, 1));
        } else if (carportLengthInCm > 240 && carportLengthInCm <= 480){
            remList.add(new ItemList("480mm remme skal sadles ned i stolperne.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))*2), orderId, 3, 2));
        } else if (carportLengthInCm > 480 && carportLengthInCm <= 600) {
            remList.add(new ItemList("600mm remme skal sadles ned i stolperne.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(4, connectionPool))*2), orderId, 4, 2));
        } else if (carportLengthInCm > 600) {
            remList.add(new ItemList("480mm remme skal sadles ned i stolperne.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))), orderId, 3, 1));
            remList.add(new ItemList("600mm remme skal sadles ned i stolperne.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(4, connectionPool))*2), orderId, 4, 2));
        }

        return remList;
    }

    public ArrayList<ItemList> calculateRafters(double carportWidthInCm, double carportLengthInCm, int orderId) throws DatabaseException {
        final double RAFTER_SPACING_CM = 55;
        double priceRaftersAndRem = ProductFacade.getPrice(2, connectionPool);
        ArrayList<ItemList> rafterList= new ArrayList<>();

        double rafterCount = carportLengthInCm / RAFTER_SPACING_CM; // (Decimal) How many rafters will fit inside the roof.
        double rafterValue = rafterCount - (int) rafterCount; // The decimal-points from rafterCount to calculate the excess distance.
        int numberOfRafters = (int) rafterCount; // The whole number of rafters.

        if (rafterValue <= 0.36) { // If its below 0.36 the last rafter will be removed and the back-stern will act last rafter.
            numberOfRafters = numberOfRafters - 1;
        }

        if (carportWidthInCm <= 600 && carportWidthInCm >= 481) {
            rafterList.add(new ItemList("600mm spær. Monteres på rem.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(4, connectionPool))*numberOfRafters), orderId, 4, numberOfRafters));
        } else if (carportWidthInCm <= 480 && carportWidthInCm >= 301) {
            rafterList.add(new ItemList("480mm spær. Monteres på rem.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))*numberOfRafters), orderId, 3, numberOfRafters));
        } else if (carportWidthInCm == 300) {
            numberOfRafters = numberOfRafters/2;
            rafterList.add(new ItemList("600mm Spær. Monteres på rem.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(4, connectionPool))*numberOfRafters), orderId, 4, numberOfRafters));
        } else if (carportWidthInCm == 240) {
            numberOfRafters = numberOfRafters/2;
            rafterList.add(new ItemList("480mm spær. Monteres på rem.", (int)((priceRaftersAndRem*ProductVariantFacade.getProductVariantQuantity(3, connectionPool))*numberOfRafters), orderId, 3, numberOfRafters));
        }

        for (ItemList item: rafterList) {
            System.out.println(item.getDescription() + " " + item.getProductVariantId() + " " + item.getQuantity());
        }
        return rafterList;
    }

    public ArrayList<ItemList> calculatePosts(double lengthInCm, int orderId) throws DatabaseException {
        double pricePost = ProductFacade.getPrice(3, connectionPool);
        ArrayList<ItemList> postList = new ArrayList<>();

        if (lengthInCm >= 481) {
            postList.add(new ItemList("Stolper nedgraves 90 cm. i jord.", ((int)(pricePost*ProductVariantFacade.getProductVariantQuantity(5, connectionPool))*6), orderId, 5, 6));
        } else postList.add(new ItemList("Stolper nedgraves 90 cm. i jord.", ((int)(pricePost*ProductVariantFacade.getProductVariantQuantity(5, connectionPool))*4), orderId, 5, 4));

        for (ItemList item: postList) {
            System.out.println(item.getDescription() + " " + item.getProductVariantId() + " " + item.getQuantity());
        }

        return postList;
    }

    public ArrayList<ItemList> calculateSternBoardsMainMethod(double carportWidthInCm, double carportLengthInCm, int orderId) throws DatabaseException {
        loadWoodList();
        ArrayList<ItemList> itemList = new ArrayList<>();
        double priceStern = ProductFacade.getPrice(1, connectionPool);
        boolean lengthDone = false, widthDone = false;

        if (woodList.containsValue(carportWidthInCm)) {
            if(carportWidthInCm == 360) {
                itemList.add(new ItemList("360mm Sternbrædder til for- og bagende.",
                        (int)((priceStern*ProductVariantFacade.getProductVariantQuantity(1, connectionPool))*2), orderId, 1, 2));
            } if(carportWidthInCm == 540){
                itemList.add(new ItemList("540mm Sternbrædder til for- og bagende.",
                        (int)((priceStern*ProductVariantFacade.getProductVariantQuantity(2, connectionPool))*2), orderId, 2, 2));
            }
            widthDone = true;
        }

        if (woodList.containsValue(carportLengthInCm)) {
            if(carportLengthInCm == 360) {
                itemList.add(new ItemList("360mm Sternbrædder til siderne.",
                        (int)((priceStern*ProductVariantFacade.getProductVariantQuantity(1, connectionPool))*2), orderId, 1, 2));
            } if(carportLengthInCm == 540) {
                itemList.add(new ItemList("540mm Sternbrædder til siderne.",
                        (int)((priceStern*ProductVariantFacade.getProductVariantQuantity(2, connectionPool))*2), orderId, 2, 2));
            }
            lengthDone = true;
        }

        if(lengthDone && !widthDone){
            itemList.addAll(CalculateSternBoards(((carportWidthInCm)*2), orderId));
        } else if(!lengthDone && widthDone){
            itemList.addAll(CalculateSternBoards(((carportLengthInCm)*2), orderId));
        } else if(!lengthDone && !widthDone){
            itemList.addAll(CalculateSternBoards(((carportLengthInCm+carportWidthInCm)*2), orderId));
        }
        return itemList;
    }

    public ArrayList<ItemList> CalculateSternBoards(double totalCarportSize, int orderId) throws DatabaseException {
        ArrayList<ItemList> itemList = new ArrayList<>();
        double priceStern = ProductFacade.getPrice(1, connectionPool);
        int longWood = 0, shortWood = 0;
        closeToZeroWidth = sternHelperMethod(totalCarportSize);
        double woodLength = 0;
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

        if(longWood > 0){
            itemList.add(new ItemList("540mm Sternbrædder. Tilskæring kan forekomme.", (int)((priceStern*ProductVariantFacade.getProductVariantQuantity(2, connectionPool))*longWood), orderId, 2, longWood));
        }
        if(shortWood > 0){
            itemList.add(new ItemList("360mm Sternbrædder. Tilskæring kan forekomme.", (int)((priceStern*ProductVariantFacade.getProductVariantQuantity(1, connectionPool))*shortWood), orderId, 1, shortWood));
        }
        return itemList;
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

