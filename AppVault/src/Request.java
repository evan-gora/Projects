/**
 * A class representing a user-made request for a new app to be added to the
 * repository.
 * 
 * @author Danny Carroll
 *
 */
public class Request {
    private String link;
    private String description;

    /**
     * Constructor to make a new request.
     * 
     * @param link The link to the source of the App being requested.
     * @param description A description of the requested App.
     */
    public Request(String link, String description) {
        this.link = link;
        this.description = description;
    }

    /** Checks if two Requests are equal.
     * 
     * @param request The request to compare.
     * @return true if the two requests have the same link and description.
     *         else, false.
     */
    public boolean equals(Request request) {
        if (this.link.equals(request.getLink())
                && this.description.equals(request.getDescription())) {
            return true;
        }
        return false;
    }

    /**
     * Returns the link to a requested link.
     * 
     * @return the link to the requested app.
     */
    public String getLink() {
        return link;
    }

    // This method may not be needed, as existing requests should not be 
    // modified by users.
    /* public void setLink(String link) {
        this.link = link;
    } */

    /**
     * Returns the description of the request.
     * 
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the request.
     * 
     * @param description The new description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Returns this request as a String. Needed for text file saving.
     */
    @Override
    public String toString() {
        return this.link + "\n" + this.description;
    }

}
