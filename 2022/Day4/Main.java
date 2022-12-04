package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2022\\Day4\\Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int countContain = 0;
        int countOverlap = 0;
        try {
            String str;
            while ((str = br.readLine()) != null) {
                String[] sections = str.split(",");
                String[] range1 = sections[0].split("-");
                String[] range2 = sections[1].split("-");
                int r1Start = Integer.parseInt(range1[0]);
                int r1End = Integer.parseInt(range1[1]);
                int r2Start = Integer.parseInt(range2[0]);
                int r2End = Integer.parseInt(range2[1]);
                if (((r1Start <= r2Start) && (r1End >= r2End)) || ((r2Start <= r1Start) && (r2End >= r1End)))
                    countContain++;
                if (((r1End >= r2Start) && (r1Start <= r2Start)) || ((r2End >= r1Start) && (r2Start <= r1Start)))
                    countOverlap++;
            }
        } catch (IOException e) { }

        System.out.println("Contains: " + countContain);
        System.out.println("Overlaps: " + countOverlap);
    }
}
