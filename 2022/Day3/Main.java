package Day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static int itemToInt(char item) {
        int val = (Character.isUpperCase(item)) ? (item - 38) : (item - 96);
        return val;
    }

    public static ArrayList<Character> commonChar(String str1, String str2) {
        ArrayList<Character> common = new ArrayList<>();
        for (char item : str1.toCharArray()) {
            for (char item2 : str2.toCharArray()) {
                if (item == item2) {
                    common.add(item);
                    break;
                }
            }
        }
        return common;
    }

    public static void main(String[] args) {
        File file = new File("2022\\Day3\\Input.txt");

        int sum = 0;
        int groupSum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int itemNum = 0;
            String[] group = new String[3];
            for (String str = null; (str = br.readLine()) != null;) {
                String first = str.substring(0, str.length() / 2);
                String second = str.substring(str.length() / 2);
                sum += itemToInt(commonChar(first, second).get(0));
                group[itemNum] = str;
                if ((itemNum + 1) % 3 == 0) {
                    ArrayList<Character> commonChars = commonChar(group[0], group[1]);
                    boolean found = false;
                    for (char item : commonChars) {
                        for (char item2 : group[2].toCharArray()) {
                            if (item == item2) {
                                groupSum += itemToInt(item);
                                found = true;
                                break;
                            }
                            if (found) break;
                        }
                        if (found) break;
                    }
                }
                itemNum = (itemNum == 2) ? 0 : itemNum + 1;
            }
        } catch (IOException e) { }

        System.out.println("Part 1 Sum: " + sum);
        System.out.println("Part 2 Sum: " + groupSum);
    }
}
