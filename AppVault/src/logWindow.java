import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class containing the code for a log in window. Allows for the user to log
 * themselves into the store, which is required to access certain
 * functionalities. Also allows users to create an account, which is later
 * stored in a text file.
 * 
 * @author John Kosh
 *
 */
public class logWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public static User current = null;

    /**
     * Constructor to create the login window.
     * 
     * @param store Uses a store object to check the user credentials
     */
    public logWindow(Store store) {
        // Set up the main panel and layout
        setTitle("Appvault Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title the login panel
        JLabel titleLabel = new JLabel("Welcome to Appvault!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create another panel to get the user input.
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        add(inputPanel, BorderLayout.CENTER);

        // Create a field for the user to input their username.
        JPanel usernameInputPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        usernameInputPanel.add(usernameLabel);
        usernameInputPanel.add(usernameField);

        // Create a field for the user to enter their password.
        JPanel passwordInputPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        passwordInputPanel.add(passwordLabel);
        passwordInputPanel.add(passwordField);
        inputPanel.add(usernameInputPanel);
        inputPanel.add(passwordInputPanel);

        // Create a button panel and add the login and signup buttons.
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(buttonPanel, BorderLayout.SOUTH);
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        // Functionality for the login button. Checks the username and password
        // entered with the userList from the store that is passed to the login
        // window.
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (store.checkCredentials(username, password)
                        && store.findUser(username) != null) {
                    current = store.findUser(username);
                    current.setLoggedIn(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(loginButton,
                            "Username or password is incorrect.");
                }
            }
        });

        // Functionality for the signup button. Opens a new signup frame for a
        // user to create their account.
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpFrame(store);
            }
        });
    }

    /**
     * A constructor for a signup window if the user does not have an account
     * yet. Also automatically logs them in when the account is created.
     * 
     * @param store
     */
    private void signUpFrame(Store store) {
        // Creating the signup frame and setting the layout.
        JFrame signUp = new JFrame();
        signUp.setTitle("Sign Up for AppVault!");
        signUp.setSize(400, 200);
        signUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUp.setLayout(new BorderLayout());

        // Create a panel that will allow for users to input their information.
        JPanel input = new JPanel(new GridLayout(3, 1));
        signUp.add(input, BorderLayout.CENTER);

        // Add a text field for the user to enter their username.
        JPanel usernamePanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        // Add a text field for the user to enter their password.
        JPanel passwordPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Add a text field for the user to retype their password. Needs to
        // match the password they entered earlier.
        JPanel passwordCheckPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel passwordCheckLabel = new JLabel("Retype Password:");
        JPasswordField passwordCheckField = new JPasswordField(15);
        passwordCheckPanel.add(passwordCheckLabel);
        passwordCheckPanel.add(passwordCheckField);

        // Add all components to the input panel.
        input.add(usernamePanel);
        input.add(passwordPanel);
        input.add(passwordCheckPanel);

        // Create a panel with a button to sign up. Checks that all fields have
        // been filled in and then compares the passwords to make sure they
        // match. If the passwords match, the user is logged in and a new user
        // object is created and added to the store passed to the method.
        JPanel buttons = new JPanel();
        signUp.add(buttons, BorderLayout.SOUTH);
        JButton signUpButton = new JButton("Sign Up");
        buttons.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String retypePassword = new String(
                        passwordCheckField.getPassword());
                if (!username.isEmpty() && !password.isEmpty()
                        && !retypePassword.isEmpty()) {
                    if (password.equals(retypePassword)) {
                        JOptionPane.showMessageDialog(signUp,
                                "Registration successful!");
                        signUp.dispose();
                        store.addUser(new User(username, password, false, false,
                                false));
                        current = store.findUser(username);
                        current.setLoggedIn(true);
                    } else {
                        JOptionPane.showMessageDialog(signUp,
                                "Passwords do not match.");
                    }
                } else {
                    JOptionPane.showMessageDialog(signUp,
                            "Please fill in all fields.");
                }
            }
        });
        signUp.setVisible(true);
    }

    /**
     * A method that returns the user currently logged in to the application.
     * 
     * @return The logged in user.
     */
    public static User logUser() {
        return current;
    }
}