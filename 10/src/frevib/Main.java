package frevib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> testNumberList = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            testNumberList.add(i);
        }

        // build list with numbers
        List<Integer> numbersList = new ArrayList<Integer>();
        for (int i = 0; i < 256; i++) {
            numbersList.add(i);
        }

//        numbersList = testNumberList;


        int pos = 0;
        int skipSize = 0;
        String[] lengths = input.split(",");

        for (String lengthString : lengths) {
            int lengthNumber = Integer.parseInt(lengthString);
            int numbersListSize = numbersList.size();

            if (pos + lengthNumber > numbersList.size() - 1) {
                numbersList.addAll(numbersList);
                List<Integer> sublistToReverse = new ArrayList<Integer>(numbersList.subList(pos, pos + lengthNumber));
                Collections.reverse(sublistToReverse);

                // cut numbers that needs to be replaced for the reversed list at the end
                numbersList = new ArrayList<Integer>(numbersList.subList(0, pos));
                // add numbers from reversed list to the end
                numbersList.addAll(new ArrayList<Integer>(sublistToReverse.subList(0, numbersListSize - pos)));

                // cut numbers that need to be replaced for the reversed list at the front
                numbersList = new ArrayList<Integer>(numbersList.subList(lengthNumber - (numbersListSize - pos), numbersListSize));
                // add numbers from reversed list to the front
                List<Integer> frontPartReversedSublist = new ArrayList<Integer>(sublistToReverse.subList(numbersListSize - pos, sublistToReverse.size()));

                frontPartReversedSublist.addAll(numbersList);

                numbersList = frontPartReversedSublist;
            } else if (lengthNumber == 0 || lengthNumber == 1) {

            } else {
                List<Integer> sublistToReverse = new ArrayList<Integer>(numbersList.subList(pos, pos + lengthNumber));
                Collections.reverse(sublistToReverse);

                // add after
                sublistToReverse.addAll(new ArrayList<Integer>(numbersList.subList(pos + lengthNumber, numbersListSize)));

                // add before
                sublistToReverse.addAll(0, new ArrayList<Integer>(numbersList.subList(0, pos)));

                numbersList = sublistToReverse;
            }
            pos = (pos + lengthNumber + skipSize) % (numbersListSize);
            skipSize++;

//            for (Integer number : numbersList) {
//                System.out.print(number + ",");
//            }
//            System.out.println("\n");
        }

        int result = numbersList.get(0) * numbersList.get(1);
        System.out.println("Result: " + result);

    }


    private static String input = "225,171,131,2,35,5,0,13,1,246,54,97,255,98,254,110";
    private static String testInput = "3,4,1,5";
}
