package frevib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> numbersListTest = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            numbersListTest.add(i);
        }

        // build list with numbers
        List<Integer> numbersList = new ArrayList<Integer>();
        for (int i = 0; i < 256; i++) {
            numbersList.add(i);
        }

        // first star TEST lengths
        List<Integer> lengthsTest = new ArrayList<Integer>();
        for (String length : testInput.split(",")) {
            lengthsTest.add(Integer.parseInt(length));
        }

        // second star TEST lengths
        List<Integer> lengthsSecondStarTest1 = new ArrayList<Integer>();
        for (int i = 0; i < inputSecondStarTest1.length(); i ++) {
            lengthsSecondStarTest1.add((int) inputSecondStarTest1.charAt(i));
        }
        lengthsSecondStarTest1.addAll(Arrays.asList(17, 31, 73, 47, 23));

        // second star TEST lengths
        List<Integer> lengthsSecondStarTest2 = new ArrayList<Integer>();
        for (int i = 0; i < inputSecondStarTest2.length(); i ++) {
            lengthsSecondStarTest2.add((int) inputSecondStarTest2.charAt(i));
        }
        lengthsSecondStarTest2.addAll(Arrays.asList(17, 31, 73, 47, 23));

        // second star TEST lengths
        List<Integer> lengthsSecondStarTest3 = new ArrayList<Integer>();
        for (int i = 0; i < inputSecondStarTest3.length(); i ++) {
            lengthsSecondStarTest3.add((int) inputSecondStarTest3.charAt(i));
        }
        lengthsSecondStarTest3.addAll(Arrays.asList(17, 31, 73, 47, 23));

        // second star TEST lengths
        List<Integer> lengthsSecondStarTest4 = new ArrayList<Integer>();
        for (int i = 0; i < inputSecondStarTest4.length(); i ++) {
            lengthsSecondStarTest4.add((int) inputSecondStarTest4.charAt(i));
        }
        lengthsSecondStarTest4.addAll(Arrays.asList(17, 31, 73, 47, 23));

        // second star lengths
        List<Integer> lengthsSecondStar = new ArrayList<Integer>();
        for (int i = 0; i < input.length(); i ++) {
            lengthsSecondStar.add((int) input.charAt(i));
        }
        lengthsSecondStar.addAll(Arrays.asList(17, 31, 73, 47, 23));

        List<Integer> lengths14 = new ArrayList<Integer>();
        for (int i = 0; i < input14.length(); i ++) {
            lengths14.add((int) input14.charAt(i));
        }
        lengths14.addAll(Arrays.asList(17, 31, 73, 47, 23));

//        numbersList = numbersListTest;

        int pos = 0;
        int skipSize = 0;
        for (int i = 0; i < 64; i++) {
            for (Integer lengthNumber : lengths14) {
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
            }
        }

        int result = numbersList.get(0) * numbersList.get(1);
        System.out.println("First star: " + result);

        // create sparse hash
        List<Integer> parseHashList = new ArrayList<Integer>();
        char number = 0;
        int counter = 0;
        for (int i = 0; i < numbersList.size(); i++) {
            counter++;
            number ^= numbersList.get(i);

            if (counter == 16) {
                parseHashList.add((int) number);
                number = 0;
                counter = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer item : parseHashList) {
            sb.append(String.format("%02X", item));
            System.out.print(String.format("%02x", item));
        }

        String resultSecondStarTest = "33efeb34ea91902bb2f59c9920caa6cd";
        System.out.println("\nTestInput second star: " + (resultSecondStarTest.equals(sb.toString()) ? "SUCCESS" : "FAILS"));
        System.out.println("hash length: " + sb.toString().length());

    }


    private static String input = "225,171,131,2,35,5,0,13,1,246,54,97,255,98,254,110";
    private static String testInput = "3,4,1,5";

    private static String inputSecondStarTest1 = "AoC 2017";
    private static String inputSecondStarTest2 = "1,2,3";
    private static String inputSecondStarTest3 = "";
    private static String inputSecondStarTest4 = "RIMBUOD";
    private static String input14 = "flqrgnkx-0";
//            e1a65bfb5a5ce396025fab5528c25a87
//            e1a65bfb5a5ce39625fab5528c25a87

}
