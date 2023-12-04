package Day7;

import java.util.ArrayList;

public class Folder_2022 {
    private String name;
    private ArrayList<Integer> fileSizes = new ArrayList<>();
    private ArrayList<Folder_2022> innerFolders = new ArrayList<>();
    private Folder_2022 outerFolder;

    private static int outerSize;
    private static int totalSpace = 70000000;
    private static int reqSpace = 30000000;
    private static int difference;
    private static int minAccepted;

    public Folder_2022() { // the outermost folder "/ "
        this.name = "/";
    }

    public Folder_2022(String name, Folder_2022 outerFolder) {
        this.name = name;
        this.outerFolder = outerFolder;
    }

    public String getName() {
        return this.name;
    }

    public int getTotalSize() {
        int total = 0;
        for (int size : fileSizes) total += size;
        return total;
    }

    public ArrayList<Folder_2022> getInnerFolders() {
        return this.innerFolders;
    }

    public Folder_2022 getOuterFolder() {
        return this.outerFolder;
    }

    public int getSizeOfDir() {
        int total = getTotalSize();
        for (Folder_2022 folder : innerFolders) {
            total += folder.getSizeOfDir();
        }
        return total;
    }

    public void addSize(int size) {
        fileSizes.add(size);
    }

    public void addFolder(String name) {
        innerFolders.add(new Folder_2022(name, this));
    }

    public int sumOfTotalSizes(int maxSize) { // gets part 1 solution
        int total = 0;
        for (Folder_2022 folder : innerFolders) {
            int size = folder.getSizeOfDir();
            total += (size <= maxSize) ? size : 0;
            total += folder.sumOfTotalSizes(maxSize);
        }
        return total;
    }

    public int trueSizeDir() { // used for part 2, finds size without counting files more than once
        int total = getTotalSize() + getSizeOfDir();
        outerSize = total;
        return total;
    }

    public void setDifference() {
        difference = reqSpace - (totalSpace - outerSize);
        minAccepted = outerSize;
    }

    public int getSmallestAccepted() {
        for (Folder_2022 folder : getInnerFolders()) {
            int dirSize = folder.getSizeOfDir();
            if ((dirSize >= difference) && (dirSize < minAccepted)) minAccepted = dirSize;
            folder.getSmallestAccepted();
        }
        return minAccepted;
    }
}