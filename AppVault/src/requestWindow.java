import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A class containing most of the code for adding and displaying all requests.
 * Contains a method to generate a pop up window for users to submit requests
 * for apps to be added. Also contains methods for viewing and accessing all
 * requests. This function is only available to users that are logged in and
 * have admin privileges.
 * 
 * @author Evan Gora
 *
 */
public class requestWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * A method to display a form to submit a request when the submit request
     * button is pressed on the home page.
     * 
     * @param store The store being actively used for the GUI.
     * @author John Kosh
     */
    public static void showRequestForm(Store store) {
        JFrame requestFrame = new JFrame("Request App");
        requestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        requestFrame.setSize(300, 200);
        requestFrame.setLayout(new GridLayout(4, 2));

        JLabel linkLabel = new JLabel("Link:");
        JTextField linkField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();

        JButton submitButton = new JButton("Submit Request");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String link = linkField.getText();
                String description = descriptionField.getText();

                if (!description.isEmpty() && !link.isEmpty()) {
                    JOptionPane.showMessageDialog(requestFrame,
                            "Request Submitted!");
                    store.addRequest(new Request(link, description));
                    requestFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(requestFrame,
                            "Please fill in all fields.");
                }
            }
        });

        requestFrame.add(linkLabel);
        requestFrame.add(linkField);
        requestFrame.add(descriptionLabel);
        requestFrame.add(descriptionField);
        requestFrame.add(submitButton);

        requestFrame.setVisible(true);
    }

    /**
     * A method to add all of the requests as buttons to the review page.
     * 
     * @param reqList List of requests to add.
     * @param reqPanel The panel buttons will be added to.
     * @param store The Store object being used for the GUI.
     */
    public static void addRequests(ArrayList<Request> reqList, JPanel reqPanel,
            Store store) {
        // Add the apps to the button panel.
        for (int i = 0; i < reqList.size(); i++) {
            Request current = reqList.get(i);
            int numReq = i + 1;
            // Set the row value for the apps being added.
            JButton reqButton = new JButton(
                    "<html> <B> Request </B>" + numReq + "</html>");
            reqPanel.add(reqButton);
            Dimension size = new Dimension(150, 150);
            reqButton.setPreferredSize(size);
            reqButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayReqInfo(current, store, reqPanel);
                }
            });

        }

    }

    /**
     * A method to display the information of a request if it is selected. Also
     * allows for the admin to approve or deny requests, but if a request is to
     * be approved, the rest of the information needed to create an app needs to
     * be manually filled in by the admin.
     * 
     * @param request The request to draw info from.
     * @param store The store being used for the GUI.
     * @param reqPanel The panel where requests are being displayed.
     */
    public static void displayReqInfo(Request request, Store store,
            JPanel reqPanel) {
        // Set up the frame
        JFrame reqInfo = new JFrame("App Request");
        reqInfo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        reqInfo.setSize(700, 700);
        // Set the layout
        reqInfo.setLayout(new GridLayout(8, 2, 5, 0));

        // Showing the link and description for the request
        JLabel link = new JLabel(
                "<html> <B> Link: </B>" + request.getLink() + "</html>");
        JLabel description = new JLabel(
                "<html> <B> Description: </B>" + request.getDescription()
                        + "</html>");

        // Provide fields for the admin to fill in the rest of the information
        // for an App object.
        JLabel nameLabel = new JLabel("App Name: ");
        JTextField nameField = new JTextField();
        JLabel orgLabel = new JLabel("Developer: ");
        JTextField orgField = new JTextField();
        JLabel categoryLabel = new JLabel("App Category: ");
        JTextField categoryField = new JTextField();
        JLabel platformLabel = new JLabel("Compatible Platforms: ");
        JTextField platformField = new JTextField();
        JLabel versionLabel = new JLabel("App Version: ");
        JTextField versionField = new JTextField();
        JLabel priceLabel = new JLabel("App Price: ");
        JTextField priceField = new JTextField();

        // Create and add buttons to approve or deny the request
        JButton approveReq = new JButton("Approve");
        JButton denyReq = new JButton("Deny");
        reqInfo.add(approveReq);
        reqInfo.add(denyReq);

        // Approving a request creates a new App, but all fields must be filled
        // out.
        approveReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the values for the attributes of an app.
                String name = nameField.getText();
                String organization = orgField.getText();
                String category = categoryField.getText();
                String platforms = platformField.getText();
                String version = versionField.getText();
                // Cast as double when creating the app
                String price = priceField.getText();
                // Make sure all fields are filled in
                if (!name.isEmpty() && !organization.isEmpty()
                        && !category.isEmpty() && !platforms.isEmpty()
                        && !version.isEmpty() && !price.isEmpty()) {
                    JOptionPane.showMessageDialog(reqInfo,
                            "Request Approved!");
                    reqInfo.dispose();
                    store.addApp(new App(name, request.getDescription(),
                            organization, category, platforms, version,
                            Double.valueOf(price), request.getLink(), ""));
                    store.removeRequest(request);
                } else {
                    JOptionPane.showMessageDialog(reqInfo,
                            "All fields must be filled in to approve an app.");
                }
            }
        });

        // Denying an app Resuest
        denyReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(reqInfo, "Request Denied.");
                store.removeRequest(request);
                reqInfo.dispose();
            }
        });

        // Add all the components to the frame
        reqInfo.add(link);
        reqInfo.add(description);
        reqInfo.add(nameLabel);
        reqInfo.add(nameField);
        reqInfo.add(orgLabel);
        reqInfo.add(orgField);
        reqInfo.add(categoryLabel);
        reqInfo.add(categoryField);
        reqInfo.add(platformLabel);
        reqInfo.add(platformField);
        reqInfo.add(versionLabel);
        reqInfo.add(versionField);
        reqInfo.add(priceLabel);
        reqInfo.add(priceField);
        reqInfo.add(approveReq);
        reqInfo.add(denyReq);

        // reqInfo.pack();
        reqInfo.setVisible(true);
    }

}
