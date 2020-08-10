package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * A class to design and manage LoginMenu.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class LoginMenu extends JPanel {
    //frame
    private JFrame frame;
    //users
    private ArrayList<User> users;
    //singleton instance
    private static LoginMenu INSTANCE = null;
    //login panel
    private JPanel loginPanel;
    //username
    private JLabel username;
    //user field
    private JTextField userField;
    //password
    private JLabel password;
    //pass field
    private JPasswordField passField;
    //remember me
    private JLabel rememberMe;
    //remember me button
    private JRadioButton rememberMeButton;
    //login
    private JButton login;
    //sign up
    private JButton signUp;

    /**
     * Create a new LoginMenu.
     *
     * @param frame frame
     * @param users users
     */
    private LoginMenu(JFrame frame, ArrayList<User> users) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));

        this.frame = frame;
        this.users = users;
        loginPanel = new JPanel();
        username = new JLabel(" Username", SwingConstants.LEFT);
        userField = new JTextField(16);
        password = new JLabel(" Password", SwingConstants.LEFT);
        passField = new JPasswordField(16);
        rememberMe = new JLabel("Remember me", SwingConstants.LEFT);
        rememberMeButton = new JRadioButton();
        login = new JButton("Log in");
        signUp = new JButton("Sign up");

        makeLogin();

        add(loginPanel, BorderLayout.CENTER);
    }

    /**
     * Create a new LoginMenu with singleton.
     *
     * @param frame frame
     * @param users users
     * @return INSTANCE.
     */
    public static LoginMenu getInstance(JFrame frame, ArrayList<User> users) {
        if (INSTANCE == null)
            INSTANCE = new LoginMenu(frame, users);
        return INSTANCE;
    }

    /**
     * make login panel.
     *
     * The method makes panel for LoginMenu.
     */
    private void makeLogin() {
        //user panel
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(2,1));
        userPanel.add(username, BorderLayout.NORTH);
        userPanel.add(userField);
        userField.setBackground(Color.white);
        userField.setBorder(BorderFactory.createLineBorder(Color.red));
        username.setPreferredSize(new Dimension(100, 50));
        userField.setPreferredSize(new Dimension(100, 40));
        userField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userField.setBorder(BorderFactory.createLineBorder(new Color(122,122,122)));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (userField.getText().equals("")) {
                    userField.setBorder(BorderFactory.createLineBorder(Color.red));
                }
                else {
                    userField.setBorder(BorderFactory.createLineBorder(new Color(122,122,122)));
                }
            }
        });


        //pass panel
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new GridLayout(2,1));
        passPanel.add(password, BorderLayout.NORTH);
        passPanel.add(passField);
        password.setPreferredSize(new Dimension(100, 50));
        passField.setPreferredSize(new Dimension(100, 40));
        passField.setBackground(Color.white);
        passField.setBorder(BorderFactory.createLineBorder(Color.red));
        passField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passField.setBorder(BorderFactory.createLineBorder(new Color(122,122,122)));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (passField.getText().equals("")) {
                    passField.setBorder(BorderFactory.createLineBorder(Color.red));
                }
                else {
                    passField.setBorder(BorderFactory.createLineBorder(new Color(122,122,122)));
                }
            }
        });

        //remember panel
        JPanel rememberPanel = new JPanel();
        rememberPanel.setLayout(new BoxLayout(rememberPanel, BoxLayout.X_AXIS));
        rememberPanel.add(rememberMeButton, BorderLayout.WEST);
        rememberPanel.add(rememberMe);
        rememberPanel.setPreferredSize(new Dimension(100, 60));

        //button panel
        login.setBackground(new Color(225,225,225));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        signUp.setBackground(Color.white);
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpMenu();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(login, BorderLayout.WEST);
        buttonPanel.add(signUp, BorderLayout.EAST);
        buttonPanel.setPreferredSize(new Dimension(100, 100));

        //add panels
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Login System"));
        loginPanel.add(userPanel);
        loginPanel.add(passPanel);
        loginPanel.add(rememberPanel);
        loginPanel.add(buttonPanel);

        //add fake panels
        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(250,300));
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(250,300));
        JPanel jPanel3 = new JPanel();
        jPanel3.setPreferredSize(new Dimension(200,150));
        JPanel jPanel4 = new JPanel();
        jPanel4.setPreferredSize(new Dimension(200,150));
        add(jPanel1, BorderLayout.WEST);
        add(jPanel2, BorderLayout.EAST);
        add(jPanel3, BorderLayout.NORTH);
        add(jPanel4, BorderLayout.SOUTH);
    }

    /**
     * login.
     *
     */
    private void login() {
        String userFieldText = userField.getText();
        String passFieldText = passField.getText();

        if (userFieldText.equals("") && passFieldText.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password");
        }
        else {
            boolean flag = true;
            for (User temp : users) {
                if (userFieldText.equals(temp.getUsername()) && passFieldText.equals(temp.getPassword())) {
                    frame.remove(this);
                    temp.setRememberMe(rememberMeButton.isSelected());
                    TankTroubleGUI.saveUsers();
                    TankTroubleGUI.mainMenu(temp);
                    flag = false;
                }
            }

            if (flag) {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                userField.setText("");
                passField.setText("");
                userField.requestFocus();
            }
        }
    }

    /**
     * sign up menu.
     *
     * The method goes to RegisterMenu.
     */
    private void signUpMenu() {
        frame.remove(this);
        TankTroubleGUI.signUp();
    }

    /**
     * reset.
     *
     * The method resets fields.
     */
    public void reset() {
        userField.setText("");
        passField.setText("");
        rememberMeButton.setSelected(false);
    }
}
