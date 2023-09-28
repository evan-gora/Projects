import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The class containing the main class to process a user input, sort the list,
 * and then print the 10 most common entries.
 * 
 * @author evangora
 *
 */
public class ProcessInput {

    public static void main(String[] args) {

        // Create a HashMap to add the words to and a default AddWords object to
        // call the methods.
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
        AddWords input = new AddWords();

        try {
            System.out.println("Please enter a file name to process: ");
            Scanner in = new Scanner(System.in);
            String filename = in.next();
            Scanner file = new Scanner(new File(filename));
            // Add all words from the file to the map and print the ten most
            // common.
            input.addAll(file, wordMap);
            HashMap<String, Integer> sortedMap = input.mapSort(wordMap);
            input.printTen(sortedMap);
            in.close();

        } catch (FileNotFoundException e) {
            System.out
                    .println("File not found. Please enter a valid file name.");
        }
    }

}
