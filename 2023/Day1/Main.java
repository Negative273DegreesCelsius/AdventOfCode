package Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<String> p1Targets = new ArrayList<>();
    public static ArrayList<String> p2Targets = new ArrayList<>();

    public static int convertStringToDigit(String str) {
        switch (str) {
            case "zero":
                return 0;
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
        }
        return Integer.parseInt(str);
    }
    public static String[] getDigits(ArrayList<String> subStringTargets, String str) {
        int firstIndex = str.length(), lastIndex = -1;
        String firstS = "", lastS = "";
        for (String s : subStringTargets) {
            int first = str.indexOf(s), last = str.lastIndexOf(s);
            if (first != -1 && first < firstIndex) {
                firstIndex = first;
                firstS = s;
            }
            if (last > lastIndex) {
                lastIndex = last;
                lastS = s;
            }
        }
        String[] arr = {firstS, lastS};
        return arr;
    }
    public static void main(String[] args) throws FileNotFoundException {
        for (int i = 0; i < 10; i++) {
            p1Targets.add(i + "");
            p2Targets.add(i + "");
        }
        p2Targets.add("one");
        p2Targets.add("two");
        p2Targets.add("three");
        p2Targets.add("four");
        p2Targets.add("five");
        p2Targets.add("six");
        p2Targets.add("seven");
        p2Targets.add("eight");
        p2Targets.add("nine");

        File file = new File("2023\\Day1\\Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int sum = 0, sum2 = 0;
        try {
            String str;
            while ((str = br.readLine()) != null) {
                int d1p1 = Integer.parseInt(getDigits(p1Targets, str)[0]);
                int d2p1 = Integer.parseInt(getDigits(p1Targets, str)[1]);
                int d1p2 = Integer.parseInt(convertStringToDigit(getDigits(p2Targets, str)[0]) + "");
                int d2p2 = Integer.parseInt(convertStringToDigit(getDigits(p2Targets, str)[1]) + "");
                sum += 10 * d1p1 + d2p1;
                sum2 += 10 * d1p2 + d2p2;
            }
        } catch (IOException e) { }
        System.out.println(sum + " " + sum2);
    }
}
