package frevib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] lines = input.split("\n");
        List<ArrayList<Integer>> firewall = resetFirewall(lines);

        System.out.println("Firewall size: " + firewall.size());

        long start = System.currentTimeMillis();
        int severity;

        List<ArrayList<Integer>> firewallBase = resetFirewall(lines);
        for (int delay = 0; delay < 10000000; delay++) {
//            firewall = new ArrayList<ArrayList<Integer>>(firewallBase);
            severity = 0;
            boolean caught = false;
            firewall = deepCopyList(firewallBase);
            for (int i = 0; i < firewall.size(); i++) {
                // check if the packet is caught
                if (firewall.get(i).get(0) == 0) {
                    // caught
                    caught = true;
                    severity = severity + (firewall.get(i).get(2) * i);
                }

                // move the scanner
                moveScanner(lines, firewall);
            }

//            System.out.println("Severity: " + severity);
//            System.out.println("Delay: " + delay);



            moveScanner(lines, firewallBase);

            if (!caught) {
                System.out.println(" Not Caught at delay: " + delay);
                long end = System.currentTimeMillis();
                System.out.println("Time: " + (end - start));
                return;
            }

        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));

    }

    public static List<ArrayList<Integer>> deepCopyList(List<ArrayList<Integer>> src) {
        List<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
        for (List<Integer> item : src) {
            newList.add(new ArrayList<Integer>(Arrays.asList(item.get(0), item.get(1), item.get(2))));
        }

        return newList;
    }

    public static void moveScanner(String[] lines, List<ArrayList<Integer>> firewall) {
        // move the scanner
        for (String line : lines) {
            String[] split = line.split(":");
            int dept = Integer.parseInt(split[0].trim());

            List<Integer> data = firewall.get(dept);
            if ((data.get(0) + data.get(1)) == data.get(2) || data.get(0) + data.get(1) == -1) {
                data.set(1, data.get(1) * -1);
            }

            data.set(0, data.get(0) + data.get(1));
        }
    }

    public static List<ArrayList<Integer>> resetFirewall(String[] lines) {
        List<ArrayList<Integer>> firewall = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < Integer.parseInt(lines[lines.length - 1].split(":")[0].trim()) + 1; i++) {
            // position, direction, range
            firewall.add(0, new ArrayList<Integer>(Arrays.asList(9, 1, 0)));
        }

        for (String line : lines) {
            String[] split = line.split(":");
            int dept = Integer.parseInt(split[0].trim());
            int range = Integer.parseInt(split[1].trim());
            firewall.set(dept, new ArrayList<Integer>(Arrays.asList(0, 1, range)));
        }
        return firewall;
    }


    public static String testInput = "0: 3\n" +
            "1: 2\n" +
            "4: 4\n" +
            "6: 4";

    public static String input = "0: 3\n" +
            "1: 2\n" +
            "2: 4\n" +
            "4: 8\n" +
            "6: 5\n" +
            "8: 6\n" +
            "10: 6\n" +
            "12: 4\n" +
            "14: 6\n" +
            "16: 6\n" +
            "18: 17\n" +
            "20: 8\n" +
            "22: 8\n" +
            "24: 8\n" +
            "26: 9\n" +
            "28: 8\n" +
            "30: 12\n" +
            "32: 12\n" +
            "34: 10\n" +
            "36: 12\n" +
            "38: 12\n" +
            "40: 8\n" +
            "42: 12\n" +
            "44: 12\n" +
            "46: 10\n" +
            "48: 12\n" +
            "50: 12\n" +
            "52: 14\n" +
            "54: 14\n" +
            "56: 12\n" +
            "58: 14\n" +
            "60: 14\n" +
            "62: 14\n" +
            "64: 14\n" +
            "66: 14\n" +
            "68: 12\n" +
            "70: 14\n" +
            "72: 14\n" +
            "74: 14\n" +
            "76: 14\n" +
            "80: 18\n" +
            "82: 14\n" +
            "90: 18";
}
