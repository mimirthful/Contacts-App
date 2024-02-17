import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to read a csv file.
 * File name MUST NOT be changed.
 */
public class FileWrite {
    /**
     * Writes a String on csv file and creates a new line.
     * Must not be accessed outside of the writeToFile() method.
     * @param str  string to be written on the file.
     * @throws IOException  throws exeption if the file can't be accessed.
     */
    private static void write(final String str) throws IOException {
        FileWriter filewriter = new FileWriter("contactdb.csv", true);
        filewriter.write(str + String.format("%n"));
        filewriter.close();
    }
    /**
     * Tries to access writeToFile.
     * If writeToFile throws exeption, it catches it.
     * @param str string to be written on the file.
     */

    public static void writeToFile(final String str) {
        try {
            write(str);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    /**
     * Deletes the content of the file.
     * Must not be accessed outside of the deleteFileContent method.
     * @throws IOException  throws exeption if the file can't be accessed.
     */
    private static void delete() throws IOException {
        new FileWriter("contactdb.csv", false).close();
    }
    /**
     * Tries to access delete().
     * If delete() throws exeption, it catches it.
     */
    public static void deleteFileContent() {
        try {
            delete();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
