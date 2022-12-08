package Day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// this one does not look fun...

public class Main {
    private static Folder outer = new Folder();

    public static String getFullDir(Folder folder, String fileName) {
        String fullDir = fileName;
        Folder tempFolder = folder;
        while (!tempFolder.equals(outer)) {
            fullDir = tempFolder.getName() + "/" + fullDir;
            tempFolder = tempFolder.getOuterFolder();
        }
        return fullDir;
    }

    public static Folder searchFolder(Folder outerFolder, String folderName, String fullDir) {
        for (Folder folder : outerFolder.getInnerFolders()) {
            if (folder.getName().equals(folderName)) return folder;
            else if (folder.getInnerFolders().size() != 0) {
                Folder retFolder = searchFolder(folder, folderName, fullDir);
                if (!retFolder.equals(outer) && (getFullDir(retFolder, folderName).equals(fullDir))) return retFolder;
            }
        }
        return outer;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2022\\Day7\\Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int sumSize = 0;
        Folder currFolder = outer;
        try {
            String str;
            while ((str = br.readLine()) != null) {
                String[] words = str.split(" ");
                String prefix = words[0];
                if (prefix.equals("$")) {
                    String command = words[1];
                    if (command.equals("cd")) {
                        String targetDir = words[2];
                        if (targetDir.equals("..")) {
                            if (currFolder.getOuterFolder() != null) 
                                currFolder = currFolder.getOuterFolder();
                        } else if (targetDir.equals("/")) {
                            currFolder = outer;
                        } else {
                            String fullDir = getFullDir(currFolder, words[2]);
                            currFolder = searchFolder(currFolder, words[2], fullDir);
                        }
                    }
                } else if (prefix.equals("dir")) {
                    currFolder.addFolder(words[1]);
                } else {
                    currFolder.addSize(Integer.parseInt(prefix));
                }
            }
        } catch (IOException e) { }
        int maxSize = 100000;
        sumSize = outer.sumOfTotalSizes(maxSize);
        System.out.println("Part 1 Size: " + sumSize);

        outer.trueSizeDir();
        outer.setDifference();
        int minAccepted = outer.getSmallestAccepted();
        System.out.println("Part 2 Size: " + minAccepted);
    }
}
