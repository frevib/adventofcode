package frevib;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String input = readFile();
        System.out.println(input);

        int groupDepth = 1;
        boolean garbageState = false;
        int total = 1;
        int garbageCount = 0;
        for (int i = 1; i < input.length() - 1; i++) {
            char ch = input.charAt(i);

            if (garbageState) {
                if (ch == '>') {
                    garbageState = false;
                } else if (ch == '!') {
                    i++;
                } else {
                    garbageCount++;
                }
            } else {
                if (ch == '{') {
                    groupDepth++;
                } else if (ch == '}') {
                    total += groupDepth;
                    groupDepth--;
                } else if (ch == '!') {
                    i++;
                } else if (ch == '<') {
                    garbageState = true;
                }
            }
        }

        System.out.println("Score: " + total);
        System.out.println("Garbage count: " + garbageCount);
    }

    public static String testInput1 = "{{<ab>},{<ab>},{<ab>},{<ab>}}";
    public static String testInput2 = "{{<!!>},{<!!>},{<!!>},{<!!>}}";
    public static String testInput3 = "{{<a!>},{<a!>},{<a!>},{<ab>}}";

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
