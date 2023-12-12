import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A class that will be used to handle most UI elements and store the list of
 * apps and pending requests.
 * 
 * @author Danny Carroll
 *
 */
public class Store {

    private ArrayList<App> appList;
    private ArrayList<Request> requestList;
    private ArrayList<User> userList;

    /**
     * Creates a new Store with all lists empty.
     */
    public Store() {
        appList = new ArrayList<App>();
        requestList = new ArrayList<Request>();
        userList = new ArrayList<User>();

    }

    /**
     * Not yet tested, but should load from files.
     * 
     * @param fileName The name of the file to load from.
     */
    public void loadFromFile(String fileName) {
        try {
            Scanner fileRead = new Scanner(new File(fileName));
            while (fileRead.hasNextLine()) {
                String current = fileRead.nextLine();
                if (current.equals("App")) {
                    String name = fileRead.nextLine();
                    String description = fileRead.nextLine();
                    String organization = fileRead.nextLine();
                    String category = fileRead.nextLine();
                    String compatiblePlatforms = fileRead.nextLine();
                    String version = fileRead.nextLine();
                    double price = fileRead.nextDouble();
                    fileRead.nextLine();
                    String link = fileRead.nextLine();
                    ArrayList<String> comments = new ArrayList<String>();
                    fileRead.nextLine(); // Flush out the { character.
                    String comment = fileRead.nextLine();
                    // It kept giving an empty string first and I don't know
                    // why. This is a brute force solution.
                    while (!comment.equals("}")) {
                        if (!comment.equals("")) {
                            comments.add(comment);
                        }
                        comment = fileRead.nextLine();
                    }
                    App newApp = new App(name, description, organization,
                            category, compatiblePlatforms, version, price, link,
                            comments);
                    this.addApp(newApp);
                } else if (current.equals("Request")) {
                    Request newRequest = new Request(fileRead.next(),
                            fileRead.next());
                    this.addRequest(newRequest);
                } else if (current.equals("User")) {
                    User newUser = new User(fileRead.next(), fileRead.next(),
                            fileRead.nextBoolean(), fileRead.nextBoolean(),
                            fileRead.nextBoolean());
                    this.addUser(newUser);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    /**
     * Method that converts the given store to a text file for saving purposes.
     * 
     * @param fileName The name for the output file.
     */
    public void saveToFile(String fileName) {
        try {
            PrintWriter output = new PrintWriter(new File(fileName));
            // Apps
            for (App app : appList) {
                output.println("App");
                output.println(app.toString());
            }
            // Requests
            for (Request request : requestList) {
                output.println("Request");
                output.println(request.toString());
            }
            // Users
            for (User user : userList) {
                output.println("User");
                output.println(user.toStringFile());
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

    }

    /**
     * Attempts to add a request to the request list. Returns true if
     * successful. Does not allow duplicates.
     * 
     * @param request The request to be added to the requestList.
     * @return True if the request was added successfully.
     */
    public boolean addRequest(Request request) {
        if (!requestList.contains(request)) {
            requestList.add(request);
            return true;
        }
        return false;

    }

    /**
     * Adds a user to the userList.
     * 
     * @param user The user to add.
     * @return true if it works, false if not.
     */
    public boolean addUser(User user) {
        if (!userList.contains(user)) {
            userList.add(user);
            return true;
        }
        return false;
    }

    /**
     * Removes a request if it is found in the request list.
     * 
     * @param request The request to be removed.
     * 
     * @return true if a request was successfully removed, else false.
     */
    public boolean removeRequest(Request request) {
        // Remove request.
        if (requestList.contains(request)) {
            requestList.remove(request);
            return true;
        }
        System.out.println("Request was not found.");
        return false;
    }

    /**
     * Adds an app to the appList as long as it is not a duplicate.
     * 
     * @param app The app to attempt to add.
     * @return True if the app is successfully added, else false.
     */
    public boolean addApp(App app) {
        if (!appList.contains(app)) {
            appList.add(app);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Searches the appList for a given name as a String.
     * 
     * @param search The name to search for as a String.
     * @return Returns the app if there is a matching result, else null.
     */
    public ArrayList<App> searchApp(String search) {
        if (search.equals("")) {
            return appList;
        }
        ArrayList<App> sorted = new ArrayList<App>();
        for (int i = 0; i < appList.size(); i++) {
            App curr = appList.get(i);
            if (curr.getName().toLowerCase().contains(search.toLowerCase())) {
                sorted.add(curr);
            }
        }
        return sorted;
    }

    /**
     * A method to sort the apps based on certain criteria.
     * 
     * @param mode - The category of the app you are searching for
     * @return - The sorted list
     */
    public ArrayList<App> filterApps(String mode) {
        ArrayList<App> filtered = new ArrayList<App>();
        if (mode.equals("None")) {
            return appList;
        }
        for (int i = 0; i < appList.size(); i++) {
            App current = appList.get(i);
            // If the app category is what the user is sorting by, add it to the
            // sorted list.
            if (current.getCategory().equals(mode)) {
                filtered.add(current);
            }
        }
        // Return the sorted list
        return filtered;
    }

    /**
     * A beginning for a method to sort the apps. Currently our only two options
     * are price: high to low and price: low to high. Returns an arrayList that
     * has been sorted the proper way.
     * 
     * @param mode Whether to sort in descending or ascending order.
     * 
     * @return The sorted AppList.
     */
    public ArrayList<App> sortApps(String mode, ArrayList<App> list) {
        if (mode.equals("None")) {
            // List does not need to be sorted.
            return list;
        } else if (mode.equals("Price: Low to High")) {
            Collections.sort(list);
        } else if (mode.equals("Price: High to Low")) {
            // Sort the list and then reverse it.
            Collections.sort(list);
            ArrayList<App> revList = new ArrayList<App>();
            for (int i = list.size() - 1; i >= 0; i--) {
                revList.add(list.get(i));
            }
            list = revList;
        }
        return list;
    }

    /**
     * A method to return all the apps in the current app list.
     * 
     * @return The list of all apps
     */
    public ArrayList<App> getApps() {
        return appList;
    }

    /**
     * A method to get the list of requests.
     * 
     * @return The list of requests
     */
    public ArrayList<Request> getRequests() {
        return requestList;
    }

    /**
     * A method to return the list of users. Needed for JUnit.
     * 
     * @return The list of users
     */
    public ArrayList<User> getUsers() {
        return userList;
    }

    /**
     * A method for checking if a username and password match.
     * 
     * @param username The given username
     * @param password The given password
     * @return True if the username and password match, false if they do not
     *         match or the user is not found.
     */
    public boolean checkCredentials(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            User current = userList.get(i);
            if (current.getUsername().equals(username)) {
                if (current.getPassword().equals(password)) {
                    // Log in successful
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Searches the userList for a given username as a String.
     * 
     * @param username The username to search for.
     * @return The user if a matching one is found, else null.
     */
    public User findUser(String username) {
        for (int i = 0; i < userList.size(); i++) {
            User current = userList.get(i);
            if (current.getUsername().equals(username)) {
                return current;
            }
        }
        return null;
    }
}
