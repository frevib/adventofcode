package frevib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static List<String> inputList;
    public static int timesEncountered = 0;
    public static int killRecursion;

    public static void main(String[] args) throws IOException {
        String input = readFile();
        String[] inputArray = input.split("\n");
        inputList = new ArrayList<String>(Arrays.asList(inputArray));

        killRecursion = 0;
        int numberOfGroups = 0;
        Set<Integer> encounteredPrograms = new HashSet<Integer>();

            for (String program : inputList) {
                killRecursion = 0;
                encounterNumber(program, new HashSet<Integer>(), 0);
            }
            numberOfGroups++;

        System.out.println("times encountered: " + timesEncountered);
        System.out.println("number of groups: " + numberOfGroups);
    }

    public static void encounterNumber(String program, Set<Integer> encounteredNumbers, int numberToFind) {
        if (killRecursion == 1) {
            return;
        }
        String[] split = program.split("<->");
        int fromId = Integer.parseInt(split[0].trim());
        encounteredNumbers.add(fromId);

        for (String toIdString : split[1].split(",")) {
            int toId = Integer.parseInt(toIdString.trim());

            if (toId == numberToFind) {
                timesEncountered++;
                killRecursion = 1;
                return;
            }

            if (!encounteredNumbers.contains(toId)) {
                encounterNumber(inputList.get(toId), encounteredNumbers, numberToFind);
            }
        }
    }

    public static String testInput = "0 <-> 2\n" +
            "1 <-> 1\n" +
            "2 <-> 0, 3, 4\n" +
            "3 <-> 2, 4\n" +
            "4 <-> 2, 3, 6\n" +
            "5 <-> 6\n" +
            "6 <-> 4, 5";

    public static String readFile() throws IOException {
        File file = new File("input.txt");
        System.out.println(file.getAbsolutePath());

        BufferedReader reader = new BufferedReader(new FileReader("src/frevib/input.txt"));
        StringBuilder sb = new StringBuilder();
        try {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = reader.readLine();
            }
        } finally {
            reader.close();
        }
        return sb.toString();
    }
}
