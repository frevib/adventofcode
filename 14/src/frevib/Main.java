package frevib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));


        String input = "vbqugkhl";
        int squares = 0;

        for (int i = 0; i < 128; i++) {
            String knotHash = knotHash(input + "-" + i);

            StringBuilder sb = new StringBuilder();
            for (int chr = 0; chr < knotHash.length(); chr++) {
                sb.append(hexToBinary(String.valueOf(knotHash.charAt(chr))));
            }

            squares += countSquares(sb.toString());
            bw.write(sb.append("\n").toString());
        }

        System.out.println("total squares: " + squares);
        bw.close();

//        String binary = hexToBinary("0");
//        System.out.println("binary: " + binary);
    }

    public static int countSquares(String row) {
        int total = 0;
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == '1') {
                total++;
            }
        }
        return total;
    }

    public static String knotHash(String input) {
        List<Integer> lengths14 = new ArrayList<Integer>();
        for (int i = 0; i < input.length(); i ++) {
            lengths14.add((int) input.charAt(i));
        }
        lengths14.addAll(Arrays.asList(17, 31, 73, 47, 23));

        // build list with numbers
        List<Integer> numbersList = new ArrayList<Integer>();
        for (int i = 0; i < 256; i++) {
            numbersList.add(i);
        }

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

//        int result = numbersList.get(0) * numbersList.get(1);
//        System.out.println("First star: " + result);

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
//            System.out.print(String.format("%02x", item));
        }

        return sb.toString().toLowerCase();
    }

    public static String hexToBinary(String hex) {
        int i = Integer.parseInt(hex, 16);
        String bin = String.format("%4s", Integer.toBinaryString(i)).replace(' ', '0');
        return bin;
    }
}
