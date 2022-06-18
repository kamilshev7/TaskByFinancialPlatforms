package net.guard.passer;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class FileFinder {
    public String dir;
    public String pathFileResult;
    public ArrayList<File> listFiles = new ArrayList<>();

    public FileFinder(String dir) {
        this.dir = dir;
        this.pathFileResult = dir + "\\file.txt";
    }

    public static String getDir(){
        String dir = null;
        try {
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter your directory");
            System.out.println("to finish press \"exit\"");
            dir = bufferedInputStream.readLine().trim();
            if(dir.equals("exit")) {
                System.exit(0);
            }
            File file = new File(dir);
            if(!file.exists()){
                throw new FileNotFoundException();
            }
            bufferedInputStream.close();
        }catch (Exception e){
            if(e.getClass().getSimpleName().equals("FileNotFoundException")) {
                System.out.println("You specified incorrect directory");
                return getDir();
            }
            else e.printStackTrace();
        }

        return dir;
    }

    public void searchFile(File directory){
        if(directory.isDirectory()){
            for(File file : Objects.requireNonNull(directory.listFiles())){
                if(file.isFile() && (file.getName().endsWith(".txt"))){
                    listFiles.add(file);
                } else {
                    searchFile(file);
                }
            }
        }
        if(listFiles.size() == 0){
            System.out.println("Format \".txt\" not found ");
            System.exit(0);
        }
    }

    public void sort(){
        Comparator<File> comparator = Comparator.comparing(File::getName);
        this.listFiles.sort(comparator);
    }

    public void writeToFile() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.pathFileResult));){
            for ( File file : this.listFiles ) {
                try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file.toString())))
                {
                    while (bufferedReader.ready()){
                        bufferedWriter.write(bufferedReader.readLine());
                        bufferedWriter.newLine();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
