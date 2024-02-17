import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * Class for reading the csv file.
 */
public class FileRead {
    /**
     * Reads the csv file.
     * If file doesn't already exist, it creates
     * a new one.
     * File name MUST NOT be changed.
     * @return return's the file's text as a string.
     */
    static String readFile() {
        String text = "";
        try {
            File textFile = new File("contactdb.csv");
            Scanner scanner = new Scanner(textFile);
            while (scanner.hasNextLine()) {
                text = text + scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return text;
    }
}
