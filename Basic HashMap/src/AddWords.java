import java.util.*;

/**
 * A class containing all methods for manipulating the HashMap containing the
 * words. Adds words to the map, sorts the map, and prints the ten most common
 * words in the map.
 * 
 * @author evangora
 *
 */
public class AddWords extends HashMap<String, Integer> {

    private static final long serialVersionUID = 1L;
    // Keeps track of how many entries are in the map.
    private static int size;

    // A no argument constructor.
    public AddWords() {
    }

    /**
     * A method to add all words from a file to the map. Takes advantage of the
     * helper method add().
     * 
     * @param The file contains words to be added to the map.
     * @return
     */
    public boolean addAll(Scanner file, HashMap<String, Integer> map) {
        file.useDelimiter("[^a-zA-Z]+");
        while (file.hasNext()) {
            // Take each word from the file and add it to the map.
            String word = file.next().toLowerCase();
            if (map.containsKey(word)) {
                // if the word is already in the map, increment its value.
                int val = map.get(word) + 1;
                map.put(word, val);
            } else {
                // otherwise, add the word to the map with a value of 1.
                map.put(word, 1);
                size++;
            }
        }
        return true;
    }

    public void printList(HashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println("Word: " + key + " Value: " + value);
        }
    }

    /**
     * A method to sort the map by its most common words.
     * 
     * Inspired by an implementation by GeeksForGeeks.com.
     * 
     * @param The map to be sorted
     * @return The sorted map
     */
    public HashMap<String, Integer> mapSort(
            HashMap<String, Integer> map) {
        // Create a list containing all the entries in the map.
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry);
        }
        // Create a comparator to use when sorting the list.
        Comparator<Map.Entry<String, Integer>> compareList = new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1,
                    Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        };
        // Sort the list
        Collections.sort(list, compareList);
        // Create a HashMap with the data from the sorted list.
        HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
            // System.out.println(entry.getKey() +" " + entry.getValue());
        }
        return sortedMap;
    }

    /**
     * A method to print the ten most common words in the map based on their
     * values. Expects the list to be sorted by the mapSort method beforehand.
     * 
     * @param The map containing the words
     */
    public void printTen(HashMap<String, Integer> map) {
        // Keeps track of the number of words that have been added to the map.
        int wordsAdded = 0;
        // Sort the map in case it has not been sorted yet.
        // HashMap<String, Integer> sortedMap = mapSort(map);
        System.out.println("The ten most common words are: ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String word = entry.getKey();
            Integer count = entry.getValue();
            System.out.println(word + ", Count: " + count);
            wordsAdded++;

            if (wordsAdded == 10) {
                break;
            }
        }
    }

}
