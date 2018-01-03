package frevib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static String testInput1 = "ne,ne,sw,sw"; // 0 steps
    public static String testInput2 = "se,sw,se,sw,sw"; // 3 steps
    public static String testInput3 = "ne,ne,s,s"; // 2 steps

    public static void main(String[] args) throws IOException {
        List<Integer[]> coordinatesXYZ = new ArrayList<Integer[]>();
        coordinatesXYZ.add(new Integer[]{0,0,0});

        String input = readFile();
        List<Integer> position = new ArrayList<Integer>(Arrays.asList(0,0,0));
        Integer maxStepsAway = 0;
        Integer currentStepsAway = 0;

        for (String direction : input.split(",")) {
            List<Integer> directionCoordinates = getDirectionCoordinates(direction);
            position = sumArrays(position, directionCoordinates);
            currentStepsAway = (Math.abs(position.get(0)) + Math.abs(position.get(1)) + Math.abs(position.get(2))) / 2;
            if (currentStepsAway > maxStepsAway) {
                maxStepsAway = currentStepsAway;
            }

        }

        for (Integer number : position) {
            System.out.print(number + ",");
        }

        Integer stepsAway = (Math.abs(position.get(0)) + Math.abs(position.get(1)) + Math.abs(position.get(2))) / 2;
        System.out.println("Steps away: " + stepsAway);
        System.out.println("Max steps away: " + maxStepsAway);
    }

    public static List<Integer> getDirectionCoordinates(String direction) {
        if (direction.charAt(0) == 'n') {
            if (direction.length() == 1) {
                return new ArrayList<Integer>(Arrays.asList(0,1,-1));
            }
            if (direction.charAt(1) == 'e') {
                return new ArrayList<Integer>(Arrays.asList(-1,1,0));
            }
            if (direction.charAt(1) == 'w') {
                return new ArrayList<Integer>(Arrays.asList(1,0,-1));
            }
        } else {
            if (direction.length() == 1) {
                return new ArrayList<Integer>(Arrays.asList(0,-1,1));
            }
            if (direction.charAt(1) == 'e') {
                return new ArrayList<Integer>(Arrays.asList(-1,0,1));
            }
            if (direction.charAt(1) == 'w') {
                return new ArrayList<Integer>(Arrays.asList(1,-1,0));
            }
        }
        throw new RuntimeException("Something went wrong!!");
    }

    public static List<Integer> sumArrays(List<Integer> array1, List<Integer> array2) {
        List<Integer> total = new ArrayList<Integer>();
        for (int i = 0; i < array1.size(); i++) {
            total.add(array1.get(i) + array2.get(i));
        }
        return total;
    }

    public static String readFile() throws IOException {
        File file = new File("input.txt");
        System.out.println(file.getAbsolutePath());

        BufferedReader reader = new BufferedReader(new FileReader("src/frevib/input.txt"));
        StringBuilder sb = new StringBuilder();
        try {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        } finally {
            reader.close();
        }
        return sb.toString();
    }
}
