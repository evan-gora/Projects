/**
 * The main class for the program. Contains the UI code so that the user can
 * interact with the program.
 * 
 * @author evangora
 */
public class Main {

    public static void main(String[] args) {
        // Create our store and load in the information from the file.
        Store store = new Store();
        store.loadFromFile("store.txt");
        
        HomePage.createAppStoreFrame(store);
       
    }
        
}
