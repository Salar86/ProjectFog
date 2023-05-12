package dat.backend.model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculations
{

    public int calculateRafters(double lengthInCm) {
        final double RAFTER_SPACING_CM = 55;
        double rafterCount = lengthInCm/RAFTER_SPACING_CM; // How many rafters can fit along the length of the carport.
        double rafterValue = rafterCount - (int)rafterCount; // The decimal-numbers of the rafterCount. (1.818 x length (eg. 2,20m) = 3,99 rafters. )
        int numberOfRafters = (int)rafterCount;

        if(rafterValue >= 0.36) { // If the rafterValue is less than 0.36 (55cm x 0.364 = 20 cm), there will always be at least 20 cm to the last rafter.
            return numberOfRafters;

        }else numberOfRafters = numberOfRafters-1;

        return numberOfRafters;
    }

    public int calculateRafterWidth() {
        //TODO: Make method that calculates the number of boards needed for the different widths of the carport.
        return  0;
    }

    public int calculatePosts(double lengthInCm) {
        int post = 0;

        if(lengthInCm >= 481){
            post = 6;
        } else post = 4;

        return post;
    }

    public Map<Integer, String> calculateFrontAndBackSternBoards(double lengthInCm, int woodLength){
        Map<Integer, String> sternBoards = new HashMap<>();

        if((lengthInCm/woodLength) % 2 == 0) {

        }

        return null;
    }

    /*private int[][] readArrayFromFile()
    {
        double[][] array = new double[7][13];
        try
        {
            Scanner myFileReader = new Scanner(new File("src/main/resources/Testetete.csv"));

            int i = 0;
            while (myFileReader.hasNextLine())
            {

                String line = myFileReader.nextLine();
                String[] tokens = line.split(";");

                for (int j = 0; j < tokens.length; j++)
                {
                    array[i][j] = Double.parseDouble(tokens[j]);
                }
                i++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }*/

}
