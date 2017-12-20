import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Advent of code day 6 ===");
        String[] stringArray = "4\t1\t15\t12\t0\t9\t9\t5\t5\t8\t7\t3\t14\t5\t12\t3".split("\t");
        List<Integer> intList = new ArrayList<Integer>();

        for (String item : stringArray) {
            intList.add(Integer.parseInt(item));
        }

        List<Integer> originalList = new ArrayList<Integer>(intList);
        List<List<Integer>> encounteredLists = new ArrayList<List<Integer>>();

        encounteredLists.add(new ArrayList<Integer>(originalList));
        long count = 0;

        StringBuilder sb1 = new StringBuilder();
        for (Integer item : originalList) {
            sb1.append(item + ",");
        }

        while (true) {
            int maxIndex = maxIndex(intList);
            Integer maxNumber = intList.get(maxIndex);
            count += 1;
            intList.set(maxIndex, 0);
            for (int i = maxIndex + 1; i < maxIndex + 1 + maxNumber; i++) {
                intList.set(i % intList.size(), intList.get(i % intList.size()) + 1);
            }

            for (int i = 0; i < encounteredLists.size(); i++) {
                if (encounteredLists.get(i).equals(intList)) {
                    int itemIndex = i;
                    StringBuilder sb2 = new StringBuilder();
                    for (Integer listitem : intList) {
                        sb2.append(listitem + ",");
                    }
                    System.out.println("Duplicate: " + sb2.toString());
                    System.out.println("Count: " + count);
                    System.out.println("Interval: " + (count - itemIndex));
                    return;
                }
            }
            encounteredLists.add(new ArrayList<Integer>(intList));
        }
    }

    private static int maxIndex(List<Integer> intList) {
        Integer max = 0;
        int index = 0;

        for (int i = 0; i < intList.size(); i++) {
            if (intList.get(i) > max) {
                max = intList.get(i);
                index = i;
            }
        }

        return index;
    }
}
