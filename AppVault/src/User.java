import java.util.Scanner;

/**
 * A class that holds all relevant information on a user of the application.
 * 
 * @author Team 6
 *
 */
public class User {

    // Instance Variables
    private String username;
    private String password;
    private boolean isAdmin;
    private boolean isMod;
    private boolean loggedIn;

    // Constructors
    
    /**
     * Constructor for a regular user.
     * 
     * @param username The user's username
     * @param password The user's password
     * @param isLoggedIn Whether or not the User is currently logged in.
     * @param isMod Whether or not the User is a moderator.
     * @param isAdmin Whether or not the User is an admin.
     */
    public User(String username, String password, boolean isLoggedIn,
            boolean isMod, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isMod = isMod;
        this.loggedIn = isLoggedIn;
        this.isAdmin = isAdmin;
    }

    /**
     * This is a constructor to set for Admins.
     * 
     * <p>Note: this method seems redundant now that I changed the constructor
     * to do it by default to better match text file format. I've marked it as
     * deprecated for now. - Danny
     * 
     * @param isAdmin - Is the user an Admin?
     */
    @Deprecated
    public User(boolean isAdmin) {
        this(null, null, true, true, isAdmin);
        this.isAdmin = isAdmin;
    }

    /**
     * This is a default constructor or a "Guest" account. Sets all the
     * variables to null or false.
     */
    public User() {
        this.isMod = false;
        this.isAdmin = false;
        this.username = null;
        this.password = null;
        this.loggedIn = false;
    }

    /**
     * Helper method specifically for saving to a file. Can be easily modified
     * for a general toString().
     * 
     * @return userString Logged out user information.
     */
    public String toStringFile() {
        String userString = "";
        userString += this.username + "\n";
        userString += this.password + "\n";
        userString += "false\n";
        userString += this.isMod + "\n";
        userString += this.isAdmin;

        return userString;
    }

    // Public Interface
    /**
     * This method changes the password of a user object if the password they
     * had before matches what they input.
     * 
     * @param newPass String to change the password to.
     * @param oldPass Your old password.
     */
    public void passwordChange(String newPass, String oldPass) {
        String oldPass1 = null;
        Scanner scan = null;
        while (!oldPass1.equals(oldPass)) {
            System.out.println("Please enter your old password: ");
            scan = new Scanner(System.in);
            oldPass1 = scan.next();
            if (oldPass1.equals(oldPass)) {
                this.password = newPass;
            } else {
                System.out.println("The passwords did not match, try again.");
            }
        }
        System.out.println("Your password has been changed successfully.");
        scan.close();
    }

    /**
     * This method submits a request to the requestList.
     * 
     * @param store       Store to submit the request to.
     * @param link        Link to the application or request.
     * @param description Description of the app.
     */
    public void submitRequest(Store store, String link, String description) {
        Request request = new Request(link, description);
        if (store.addRequest(request)) {
            System.out.println("Your request has been submitted.");
        } else {
            System.out.println("There was an error processing your request.");
        }
    }

    /**
     * This method adds a Request Application to the stores application list if
     * approved by an admin.
     * 
     * @param request Request object to be added to the list.
     * @param isAdmin Boolean if the user is an admin or not
     * @param store   Store to add the application to.
     */
    public void verifyRequest(Request request, boolean isAdmin, Store store) {
        if (!isAdmin) {
            System.out.println("You do not have the permissions to do this.");
        }
        App app = new App(request);
        store.addApp(app);
    }

    // Getters and Setters

    /**
     * Gets the username of the User.
     * 
     * @return The username String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method sets the username of a User object.
     * 
     * @param username to change to.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password for the user.
     * 
     * @return the password String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns if the user is an admin.
     * 
     * @return true if admin, else false.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * This method sets the isAdmin boolean of a User object.
     * 
     * @param isAdmin The status, true or false, to set admin status.
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Returns if the user is a moderator.
     * 
     * @return true if mod, else false.
     */
    public boolean isMod() {
        return isMod;
    }

    /**
     * This method sets the isMod boolean of a User object.
     * 
     * @param isMod Desired moderator status of this user.
     */
    public void setMod(boolean isMod) {
        this.isMod = isMod;
    }

    /**
     * Returns whether the user is logged in or not.
     * 
     * @return true if logged in, else false.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * This method sets the isLoggedIn boolean of a User object.
     * 
     * @param loggedIn Desired log in status for this user.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
