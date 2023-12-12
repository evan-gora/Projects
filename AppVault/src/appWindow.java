import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A class containing many of the code necessary to add the apps and app
 * information onto the main homepage. Helps shorten the length of the HomePage
 * class.
 * 
 * @author Evan Gora
 *
 */
public class appWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * A method to add all of the apps in the appList as buttons to the
     * appPanel for users to access every app.
     * 
     * @param appList The list of all apps taken from the Store class.
     * @param appPanel The panel to which the apps are being added.
     */
    public static void loadAppButtons(ArrayList<App> appList, JPanel appPanel) {
        // Add the apps to the button panel.
        for (int i = 0; i < appList.size(); i++) {
            App current = appList.get(i);
            // Set the row value for the apps being added.
            JButton appButton = new JButton("<html> <B>" +
                    current.getName() + "</B> <br>"
                    + current.getCategory() + "<br>"
                    + String.valueOf(current.getPrice() + "</html>"));
            appPanel.add(appButton);
            Dimension size = new Dimension(150, 150);
            appButton.setPreferredSize(size);
            appButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayAppInfo(current);
                }
            });

        }

    }

    /**
     * A method to display all of the info for an app. Also allows users to
     * comment on the app. For use in adding action to the apps on the home
     * page.
     * 
     * @param app - The app that is having its info displayed.
     */
    private static void displayAppInfo(App app) {
        // Set up the frame
        JFrame appFrame = new JFrame(app.getName());
        appFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        appFrame.setSize(700, 800);
        appFrame.setLayout(new BorderLayout());

        // Set up a panel to contain the request info
        JPanel appInfo = new JPanel();
        GridLayout layout = new GridLayout(6, 2);
        layout.setHgap(100);
        appInfo.setLayout(layout);
        appFrame.add(appInfo, BorderLayout.CENTER);

        // Set up a panel to contain all of the comments
        JPanel commentPanel = new JPanel();
        appFrame.add(commentPanel, BorderLayout.SOUTH);
        appFrame.setLayout(new GridLayout(2, 1));

        // Create labels for each attribute of the app. Utilizes html formatting
        // so that text resizes to fit the panel and for bolding.
        JLabel name = new JLabel(
                "<html><B> Name: </B>" + app.getName() + "</html>");
        JLabel description = new JLabel("<html><B> Description: </B>"
                + app.getDescription() + "</html>");
        JLabel organization = new JLabel(
                "<html><B> Organization: </B>" + app.getOrganization()
                        + "</html>");
        JLabel category = new JLabel(
                "<html><B> Category: </B>" + app.getCategory() + "</html>");
        JLabel compatiblePlatforms = new JLabel(
                "<html><B> Compatible Platforms: </B>" + app.getPlatforms()
                        + "</html>");
        JLabel version = new JLabel(
                "<html><B> Version:  </B>" + app.getVersion() + "</html>");
        JLabel link = new JLabel(
                "<html><B> Link: </B>" + app.getLink() + "</html>");
        JLabel price = new JLabel("<html><B> Price: </B>"
                + String.valueOf(app.getPrice()) + "</html>");

        // Button and a text field to add a comment
        JButton addCommentButton = new JButton("<html>Add Comment</html>");
        JTextField commentField = new JTextField();

        addCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = logWindow.logUser();
                if (!(user == null)) {
                    app.addComment(commentField.getText(), user.isLoggedIn());
                    JOptionPane.showMessageDialog(appFrame, "Comment Added!");
                } else {
                    JOptionPane.showMessageDialog(appFrame,
                            "You must be logged in to comment on Apps.");
                }
            }
        });

        // Create a JList and JButton so that a moderator can select and delete
        // specific comments.
        String[] comments = app.getComments()
                .toArray(new String[app.getComments().size()]);
        JList<String> commentList = new JList<String>(comments);
        commentList.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        commentPanel.add(commentList);
        // Create the button and set its functionality.
        JButton deleteComment = new JButton("Delete Selected Comment");
        deleteComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = logWindow.logUser();
                if (user == null) {
                    JOptionPane.showMessageDialog(commentPanel,
                            "You must be logged in as a moderator to delete comments.");
                } else if (user.isMod()) {
                    // Remove the comment from the list and refresh the page so
                    // that the comment is no longer visible.
                    app.deleteComment((String) commentList.getSelectedValue());
                    commentList.revalidate();
                    commentList.repaint();
                    JOptionPane.showMessageDialog(commentPanel,
                            "Comment deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(commentPanel,
                            "You must be logged in as a moderator to delete comments.");
                }
            }
        });
        commentPanel.add(deleteComment);

        // Add all the labels to the panel
        appInfo.add(name);
        appInfo.add(description);
        appInfo.add(organization);
        appInfo.add(category);
        appInfo.add(compatiblePlatforms);
        appInfo.add(version);
        appInfo.add(link);
        appInfo.add(price);
        appInfo.add(addCommentButton);
        appInfo.add(commentField);

        appInfo.setVisible(true);
        appFrame.pack();
        appFrame.setVisible(true);
    }
}
