package net.guard.passer;

import java.io.File;

import static net.guard.passer.FileFinder.getDir;

public class Start {
    public static void main(String[] args) {
        String dir  = getDir();

        FileFinder fileFinder = new FileFinder(dir);

        File file = new File(fileFinder.dir);
        fileFinder.searchFile(file);
        fileFinder.sort();
        fileFinder.writeToFile();

        System.out.println("txt files found: " + fileFinder.listFiles.size());
        System.out.println("Path to result file: " + fileFinder.pathFileResult);

    }
}
