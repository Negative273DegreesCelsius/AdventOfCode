package Day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static int spritePosMin = 0;
    private static int spritePosMax = 2;

    private static String line = "";

    public static boolean isSprite(int x) {
        return (x >= spritePosMin) && (x <= spritePosMax);
    }

    public static void updateLine(int cycle, int x) {
        while (cycle > 40) cycle -= 40;
        line += (isSprite(cycle - 1)) ? "#" : ".";
        if (cycle % 40 == 0) line += "\n";
    }

    public static void updatePos(int x) {
        spritePosMax = x + 1;
        spritePosMin = x - 1;
    }

    public static int getSignalStrength(int cycle, int x) {
        switch (cycle) {
            case 20:
            case 60:
            case 100:
            case 140:
            case 180:
            case 220:
                return cycle * x;
            default:
                return 0;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2022\\Day10\\Input.txt");
        
        int cycle = 0;
        int sum = 0;
        int x = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String str = null; (str = br.readLine()) != null;) {
                String[] tokens = str.split(" ");
                if (tokens[0].equals("noop")) {
                    cycle++;
                    sum += getSignalStrength(cycle, x);
                    updateLine(cycle, x);
                } else {
                    cycle++;
                    sum += getSignalStrength(cycle, x);
                    updateLine(cycle, x);
                    cycle++;
                    sum += getSignalStrength(cycle, x);
                    updateLine(cycle, x);
                    x += Integer.parseInt(tokens[1]);
                    updatePos(x);
                }
            }
        } catch (IOException e) { }

        System.out.println("Total Signal Strength: " + sum);
        System.out.println(line);
    }
}
