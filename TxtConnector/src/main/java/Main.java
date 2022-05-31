import Concatenator.Concatenator;
import java.io.File;
import static Service.Service.directoryNameInput;


public class Main {
    public static void main(String[] args) {

        String originalDirectory  = directoryNameInput();
        Concatenator concatenator = new Concatenator(originalDirectory);
        File file = new File(concatenator.getStartDirectory());
        concatenator.txtFilesSearch(file);
        concatenator.sortFilesList();
        concatenator.writeFinalTxt();

        System.out.printf("Found " + concatenator.getFileList().size() + " files. " +
                "the data is written to a file " + concatenator.getFinalFile() + ". Please, check your start directory.");
    }
}
