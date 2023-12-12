import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * A class to generate the main homepage for the AppVault. Utilizes methods from
 * other Swing classes such as logWindow, appWindow, and requestWindow to
 * display different information about users, apps, and requests.
 * 
 * @author Evan Gora, John Kosh
 *
 */
public class HomePage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton requestButton;
    private JButton viewReqs;
    private JButton loginButton;
    private JLabel filterMenu;
    private JButton confirmSort;
    private JTextField search;
    private JButton confirmSearch;
    private ArrayList<App> currAppList;
    private User currentUser;

    /**
     * A constructor to create the main homepage for the app store. Takes a
     * store object and uses the lists for displaying different information.
     * 
     * @param store The store for which to generate a HomePage.
     */
    public HomePage(Store store) {
        setTitle("AppVault");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Initialize the appList
        currAppList = store.getApps();

        // Save the store to a file upon closing the home page.
        WindowListener listener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(
                        null, "Close the application?");
                if (result == JOptionPane.OK_OPTION) {
                    store.saveToFile("store.txt");
                    setVisible(false);
                    dispose();
                }
            }
        };
        addWindowListener(listener);

        // A panel to contain buttons to log in, add a request, and sort apps.
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.NORTH);

        // A panel so all apps can be added as buttons. Uses GridBagLayout to
        // arrange apps into rows and columns
        JPanel appPanel = new JPanel();
        appPanel.setLayout(new GridLayout(
                ((int) Math.ceil(((double) store.getApps().size()) / 3)), 3, 25,
                25));
        add(appPanel, BorderLayout.CENTER);

        // Load all the apps as buttons
        appWindow.loadAppButtons(currAppList, appPanel);

        // Create and add a panel; to the bottom of the layout for searching.
        JPanel reqButtons = new JPanel();
        add(reqButtons, BorderLayout.SOUTH);

        // Create a button that allows users to open a login window to log
        // themselves in. Refreshes the app buttons so the the user has the
        // ability to comment on apps or delete comments if they are a
        // moderator.
        loginButton = new JButton("Log In");
        buttonPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logWindow frame = new logWindow(store);
                frame.setVisible(true);
            }
        });

        // Create a button that allows a user to submit a request for an app to
        // be added to the store.
        requestButton = new JButton("Request App");
        reqButtons.add(requestButton);
        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestWindow.showRequestForm(store);
            }
        });

        // Button to view requests. Only works if the user is an Admin.
        viewReqs = new JButton("View Requests");
        reqButtons.add(viewReqs);
        viewReqs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = logWindow.logUser();
                if (currentUser == null) {
                    JOptionPane.showMessageDialog(viewReqs,
                            "You must be logged in as an administrator to view the requests.");
                } else if (currentUser.isAdmin()) {
                    viewRequests(store.getRequests(), store);
                } else {
                    JOptionPane.showMessageDialog(viewReqs,
                            "You must be logged in as an administrator to view the requests.");
                }
            }
        });

        // Menu for filtering apps by categories
        filterMenu = new JLabel("Filter Apps");
        buttonPanel.add(filterMenu);
        String[] filterChoices = { "None", "Health and Wellness", "Gaming",
                "Social Media",
                "Finance", "Productivity" };
        final JComboBox<String> filterMenu = new JComboBox<String>(
                filterChoices);
        filterMenu.setVisible(true);
        buttonPanel.add(filterMenu);
        // Menu for sorting apps
        String[] sortChoices = { "None", "Price: Low to High",
                "Price: High to Low" };
        final JComboBox<String> sortMenu = new JComboBox<String>(sortChoices);
        sortMenu.setVisible(true);
        buttonPanel.add(sortMenu);

        // A button to confirm filtering. Refreshes the page so that the new
        // list
        // of apps is visible.
        confirmSort = new JButton("Confirm Filter");
        buttonPanel.add(confirmSort);
        confirmSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = logWindow.logUser();
                appPanel.removeAll();
                currAppList = store
                        .filterApps(filterMenu.getSelectedItem().toString());
                currAppList = store.sortApps(
                        sortMenu.getSelectedItem().toString(), currAppList);
                appWindow.loadAppButtons(currAppList, appPanel);
                appPanel.revalidate();
                appPanel.repaint();
            }
        });

        // Create a search bar and add a confirm button which refreshes the page
        // showing all apps that match the search.
        search = new JTextField();
        search.setColumns(30);
        confirmSearch = new JButton("Search");
        buttonPanel.add(search);
        buttonPanel.add(confirmSearch);
        // Add the functionality for the confirm search button.
        confirmSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appPanel.removeAll();
                currAppList = store.searchApp(search.getText());
                appWindow.loadAppButtons(currAppList, appPanel);
                appPanel.revalidate();
                appPanel.repaint();
            }
        });

    }

    /**
     * A method to show an admin all of the requests in the app store.
     * 
     * @param reqList - The list of all requests
     */
    private void viewRequests(ArrayList<Request> reqList, Store store) {
        // Set up the frame
        JFrame requestList = new JFrame("Request List");
        requestList.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        requestList.setSize(700, 700);
        requestList.setLayout(new BorderLayout());
        // Set up the layout and panel
        // A panel so all requests can be added as buttons. Uses GridBagLayout
        // to arrange requests into rows and columns
        JPanel requestPanel = new JPanel();
        requestPanel.setLayout((new GridLayout(
                ((int) Math.ceil(((double) store.getRequests().size()) / 3)), 3, 15,
                15)));
        requestList.add(requestPanel, BorderLayout.CENTER);

        // Add the requests as buttons and set the components as visible.
        requestWindow.addRequests(reqList, requestPanel, store);
        requestList.setVisible(true);
        requestPanel.setVisible(true);
    }

    /**
     * Create a new appstore frame
     * 
     * @param store The store used to make the frame.
     */
    public static void createAppStoreFrame(Store store) {
        HomePage appStore = new HomePage(store);
        appStore.pack();
        appStore.setVisible(true);
    }
}