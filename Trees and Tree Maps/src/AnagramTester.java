
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to take a text file containing words and add them to a tree with each
 * node containing all words that are anagrams of each other. Also has the
 * ability to take a word and print all words that are anagrams of the word that
 * is used as input.
 */

public class AnagramTester {

    /**
     * The main method for the class. Processes all user input and performs the
     * desired functions.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name of dictionary file: ");
        String fileName = input.next();
        System.out.print("Max word length: ");
        int maxLength = input.nextInt();

        AnagramTree anagrams = new AnagramTree();
        anagrams.loadWords(fileName, maxLength);

        System.out.println("Words in tree: " + anagrams.numWords());
        System.out.println("Nodes in tree: " + anagrams.size());
        System.out.println();
        System.out.println("Find Anagrams");
        if (!anagrams.isEmpty()) {
            System.out.print("\nEnter string to search [#] to stop: ");
            String searchKey = input.nextLine();
            while (!searchKey.equals("#")) {
                if (searchKey.length() <= maxLength) {
                    ArrayList<String> list = anagrams.findMatches(searchKey);
                    if (list != null) {
                        System.out.println("  Words that match: " + list);
                    } else {
                        System.out.println("  NO Words match!");
                    }
                } else {
                    System.out.println("  That word is too long; max length = "
                            + maxLength);
                }

                System.out.print("\nstring to search [#] to stop: ");
                searchKey = input.nextLine();
            }
        }

        input.close();
    }
}