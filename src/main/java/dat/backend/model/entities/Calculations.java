package dat.backend.model.entities;

public class Calculations
{

    private int calculateRafters(double lengthInCm) {
        final double RAFTER_SPACING_CM = 55;
        double rafterCount = lengthInCm/RAFTER_SPACING_CM; // How many rafters can fit along the length of the carport.
        double rafterValue = rafterCount - (int)rafterCount; // The decimal-numbers of the rafterCount. (1.818 x length (eg. 2,20m) = 3,99 rafters. )
        int numberOfRafters = (int)rafterCount;

        if(rafterValue >= 0.36) { // If the rafterValue is less than 0.36 (55cm x 0.364 = 20 cm), there will always be at least 20 cm to the last rafter.
            return numberOfRafters;

        }else numberOfRafters = numberOfRafters-1;

        return numberOfRafters;
    }

    private int calculatePosts(double lengthInCm) {
        int post = 0;

        if(lengthInCm >= 481){
            post = 6;
        } else post = 4;

        return post;
    }



}
