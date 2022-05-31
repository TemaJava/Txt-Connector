package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

//a class for storing additional methods
public class Service {

    //getting the start directory from the user
    public static String directoryNameInput(){
        String originalDirectory = "";
        try {
            BufferedReader InputStream = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter a name of directory. If you want to close a program - print 'stop'");
            originalDirectory = InputStream.readLine().trim();
            if(originalDirectory.equals("stop"))  System.exit(0);
            File file = new File(originalDirectory);

            //adding a check for exceptions
            if(!file.exists()) throw new FileNotFoundException();
        } catch (Exception exception){
            if(exception.getClass().getSimpleName().equals("FileNotFoundException")) {
                System.out.println("Incorrect directory path. Please try again or print 'stop' to close program");
                return directoryNameInput();
            }
        }
        return originalDirectory;
    }
}
