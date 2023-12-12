import java.util.ArrayList;

/**
 * A class that handles all of the app objects, including all of their instance
 * variables and adding/removing comments for moderators and users. Implements
 * the comparable interface for sorting of apps.
 * 
 * @author evangora
 */
public class App implements Comparable<App> {

    private String name;
    private String description;
    private String organization;
    private String category;
    private String compatiblePlatforms;
    private String version;
    private String link;
    private double price;
    private ArrayList<String> comments;

    /**
     * A no argument constructor.
     */
    public App() {
    }

    /**
     * The constructor to create an app object with all variables initialized.
     * 
     * @param name The name of the app
     * @param description The description of the app
     * @param organization The organization that made the app
     * @param category The category that the app falls under
     * @param compatiblePlatforms platforms that can run the app
     * @param version The version of the app
     * @param price The price of the app
     * @param link The link to the app in its store
     * @param comment Starter comment for the app
     */
    public App(String name, String description, String organization,
            String category, String compatiblePlatforms, String version,
            double price, String link, String comment) {
        this.name = name;
        this.description = description;
        this.organization = organization;
        this.category = category;
        this.compatiblePlatforms = compatiblePlatforms;
        this.version = version;
        this.link = link;
        this.price = price;
        this.comments = new ArrayList<String>();
        // Any comments provided by the user need to be added to the list of
        // comments.
        comments.add(comment);
    }

    /**
     * The constructor to create an app object with a supplied comment array.
     * 
     * @param name The name of the app
     * @param description The description of the app
     * @param organization The organization that made the app
     * @param category The category that the app falls under
     * @param compatiblePlatforms The platforms that can run the app
     * @param version The version of the app
     * @param price The price of the app
     * @param link The link to the app in its store
     * @param comments Any comments that have been made on the app
     */
    public App(String name, String description, String organization,
            String category, String compatiblePlatforms, String version,
            double price, String link, ArrayList<String> comments) {
        this.name = name;
        this.description = description;
        this.organization = organization;
        this.category = category;
        this.compatiblePlatforms = compatiblePlatforms;
        this.version = version;
        this.link = link;
        this.price = price;
        this.comments = comments;
    }

    /**
     * This is a constructor that creates an App based on a request (if a Mod
     * approves it).
     * 
     * @param request Request to be approved and made an app.
     */
    public App(Request request) {
        this();
        this.description = request.getDescription();
        this.link = request.getLink();
    }

    /**
     * Helper method for creating a String representation of an app. Useful for
     * text files.
     */
    @Override
    public String toString() {
        String thisApp = "";
        thisApp += this.getName() + "\n";
        thisApp += this.getDescription() + "\n";
        thisApp += this.getOrganization() + "\n";
        thisApp += this.getCategory() + "\n";
        thisApp += this.getPlatforms() + "\n";
        thisApp += this.getVersion() + "\n";
        thisApp += this.getPrice() + "\n";
        thisApp += this.getLink() + "\n";
        thisApp += "{\n";
        // Getting the comments.
        for (String comment : comments) {
            thisApp += comment + "\n";
        }
        thisApp += "}";

        return thisApp;
    }

    @Override
    /**
     * A method overriding how apps are compared. This is used for sorting of
     * apps by their price.
     */
    public int compareTo(App other) {
        return Double.compare(this.price, other.price);
    }

    /**
     * A method to retrieve the name of an application.
     * 
     * @return the name of the application
     */
    public String getName() {
        return name;
    }

    /**
     * A method to return the description of an application.
     * 
     * @return the description of the application
     */
    public String getDescription() {
        return description;
    }

    /**
     * A method to return the organization that created the app.
     * 
     * @return the organization for the app
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * A method to return the category for the app.
     * 
     * @return the category of the app
     */
    public String getCategory() {
        return category;
    }

    /**
     * A method to return the platforms that the app can be run on.
     * 
     * @return The compatible platforms for the app
     */
    public String getPlatforms() {
        return compatiblePlatforms;
    }

    /**
     * A method to return the current version that the app is in.
     * 
     * @return The app's version
     */
    public String getVersion() {
        return version;
    }

    /**
     * A method to return the link the the app in its respective store.
     * 
     * @return The link to the app
     */
    public String getLink() {
        return link;
    }

    /**
     * A method to return the current price of the app.
     * 
     * @return The price of the app
     */
    public double getPrice() {
        return price;
    }

    /**
     * A method to return an array list of all comments on the app. There is
     * also a method to print all the comments below.
     * 
     * @return The ArrayList containing all comments on the app.
     */
    public ArrayList<String> getComments() {
        return comments;
    }

    /**
     * A method to change the version of the app if a new version has been
     * released.
     * 
     * @param newVersion - The new version of the app
     */
    public void setVersion(String newVersion) {
        this.version = newVersion;
    }

    /**
     * A method to change the price of the app if it has changed.
     * 
     * @param newPrice - The new price of the app
     */
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    /**
     * A method to add a comment to a certain app. Checks if the user is logged
     * in before adding the comment. If the user is not logged in, says that you
     * must be logged in to comment on a post.
     * 
     * 
     * <p>NOTE: Checking if the person is logged in could be implemented in the
     * main outside of this method. Same goes for other methods that need to
     * check boolean variables on users, moderators, or admins.
     * 
     * @param comment The comment that is being added to the app.
     * @param isLoggedIn Whether or not the user trying to comment is logged in
     * 
     * @return true if the comment is added successfully, else false.
     */
    public boolean addComment(String comment, boolean isLoggedIn) {
        if (isLoggedIn) {
            comments.add(comment);
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method for moderators/admins to delete comments from certain apps.
     * Moderator privileges are verified in the UI.
     * 
     * @param comment The comment that is being deleted.
     * 
     * @return true if the comment was deleted, false otherwise.
     */
    public boolean deleteComment(String comment) {

        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).equals(comment)) {
                comments.remove(i);
                return true;
            }
        }
        return false;

    }

}
