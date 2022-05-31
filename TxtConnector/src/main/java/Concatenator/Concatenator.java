package Concatenator;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Concatenator {

    //directory for searching files that the user enters from the keyboard
    private String startDirectory;

    //String for store the name of the file for the final concatenation
    private String finalFile;

    //list that will store files for concatenation
    private ArrayList<File> FileList = new ArrayList<>();

    //constructor creates an empty file to write in start directory
    public Concatenator(String directory) {
        this.startDirectory = directory;
        this.finalFile = directory + "\\final.txt";
    }

    public String getStartDirectory() {
        return startDirectory;
    }

    public void setStartDirectory(String startDirectory) {
        this.startDirectory = startDirectory;
    }

    public ArrayList<File> getFileList() {
        return FileList;
    }

    public void setFileList(ArrayList<File> fileList) {
        FileList = fileList;
    }

    public String getFinalFile() {
        return finalFile;
    }

    public void setFinalFile(String finalFile) {
        this.finalFile = finalFile;
    }


    //function for sorting files by name
    public void sortFilesList(){

        Comparator<File> comparator = new Comparator<>() {
            @Override
            public int compare(File first, File second) {
                return first.getName().compareTo(second.getName());
            }
        };
            this.FileList.sort(comparator);
    }

    /*this function checks the endings of the names of all files in the entered directory.
    When a .txt file is found, function adds it to the list*/
    public void txtFilesSearch(File directory){
        if(directory.isDirectory()){
            for(File file:Objects.requireNonNull(directory.listFiles())){
                if((file.getName().endsWith(".txt")) & file.isFile()){
                    FileList.add(file);
                } else {
                    txtFilesSearch(file);  //recursion to explore all files
                }
            }
        }
        if(FileList.size() == 0) {
            System.out.println("Txt files not found");
            System.exit(0);
        }
    }

    //function for writing the final file
    public void writeFinalTxt() {
        try{
            BufferedWriter outputStream = new BufferedWriter(new FileWriter(this.finalFile));
            for (File file : this.FileList) {
                try(BufferedReader inputStream = new BufferedReader(new FileReader(file.toString())))
                {
                    while (inputStream.ready()){
                        outputStream.write(inputStream.readLine());
                        outputStream.newLine();
                    }
                }
            }
            outputStream.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

