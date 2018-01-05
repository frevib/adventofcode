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
    public static int numberOfGroups = 0;

    public static void main(String[] args) throws IOException {
        String input = readFile();
        String[] inputArray = input.split("\n");
        inputList = new ArrayList<String>(Arrays.asList(inputArray));

        killRecursion = 0;
//        int numberOfGroups = 0;
        Set<Integer> encounteredIds = new HashSet<Integer>();

            for (String program : inputList) {
                if (!encounteredIds.contains(Integer.parseInt(program.split("<->")[0].trim()))) {
                    killRecursion = 0;
                    encounterNumber(program, encounteredIds ,0);
                }
            }
//            numberOfGroups++;

        System.out.println("times encountered: " + timesEncountered);
        System.out.println("number of groups: " + numberOfGroups);
    }

    public static void encounterNumber(String program, Set<Integer> encounteredIds, int recursionDept) {
        if (killRecursion == 1) {
            return;
        }
        String[] split = program.split("<->");
        int fromId = Integer.parseInt(split[0].trim());
        encounteredIds.add(fromId);

        String[] splitToIds = split[1].split(",");
        for (String toIdString : splitToIds) {
            int toId = Integer.parseInt(toIdString.trim());

            if (!encounteredIds.contains(toId)) {
                encounterNumber(inputList.get(toId), encounteredIds, recursionDept + 1);
            }
        }
        if (recursionDept == 0) {
            numberOfGroups++;
            killRecursion = 1;
            return;
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
