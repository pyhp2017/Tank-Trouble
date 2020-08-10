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
 * A class to design and manage RegisterMenu.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class RegisterMenu extends JPanel {
    //frame
    private JFrame frame;
    //users
    private ArrayList<User> users;
    //singleton instance
    private static RegisterMenu INSTANCE = null;
    //sign panel
    private JPanel signPanel;
    //username
    private JLabel username;
    //user field
    private JTextField userField;
    //password
    private JLabel password;
    //pass field
    private JPasswordField passField;
    //custom
    private JLabel custom;
    //color panel
    private JPanel colorPanel;
    //number of color
    private int numberOfColor;
    //custom colors
    private ArrayList<JPanel> customColors;
    //sign up
    private JButton signUp;
    //back
    private JButton back;

    /**
     * Create a new RegisterMenu.
     *
     * @param frame frame
     * @param users users
     */
    private RegisterMenu(JFrame frame, ArrayList<User> users) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));

        this.frame = frame;
        this.users = users;
        signPanel = new JPanel();
        username = new JLabel(" Username", SwingConstants.LEFT);
        userField = new JTextField(16);
        password = new JLabel(" Password", SwingConstants.LEFT);
        passField = new JPasswordField(16);
        custom = new JLabel(" Customize color of your tank", SwingConstants.LEFT);
        colorPanel = new JPanel();
        numberOfColor = 0;
        customColors = new ArrayList<>();
        signUp = new JButton("Sign up");
        back = new JButton("Back");

        makeSignUp();

        add(signPanel, BorderLayout.CENTER);
    }

    /**
     * Create a new RegisterMenu with singleton.
     *
     * @param frame frame
     * @param users users
     * @return INSTANCE.
     */
    public static RegisterMenu getInstance(JFrame frame, ArrayList<User> users) {
        if (INSTANCE == null)
            INSTANCE = new RegisterMenu(frame, users);
        return INSTANCE;
    }

    /**
     * make sign up panel.
     *
     * The method makes panel for RegisterMenu.
     */
    private void makeSignUp() {
        //user panel
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(2,1));
        userPanel.add(username, BorderLayout.NORTH);
        userPanel.add(userField);

        username.setPreferredSize(new Dimension(100, 50));
        userField.setBackground(Color.white);
        userField.setBorder(BorderFactory.createLineBorder(Color.red));
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

        //custom panel
        JPanel customPanel = new JPanel();
        customPanel.setLayout(new GridLayout(2,1));
        customPanel.add(custom, BorderLayout.NORTH);
        customPanel.add(colorPanel);
        custom.setPreferredSize(new Dimension(100, 50));
        colorPanel.setPreferredSize(new Dimension(100, 40));
        colorPanel.setLayout(new GridLayout(1,9));
        makeColorPanel();

        //button panel
        signUp.setBackground(new Color(225,225,225));
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });
        back.setBackground(Color.white);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginMenu();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(signUp, BorderLayout.WEST);
        buttonPanel.add(back, BorderLayout.EAST);
        buttonPanel.setPreferredSize(new Dimension(100, 100));

        //add panels
        signPanel.setLayout(new BoxLayout(signPanel, BoxLayout.Y_AXIS));
        signPanel.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Sign Up System"));
        signPanel.add(userPanel);
        signPanel.add(passPanel);
        signPanel.add(customPanel);
        signPanel.add(buttonPanel);

        //add fake panels
        JPanel fake1 = new JPanel();
        fake1.setPreferredSize(new Dimension(250,300));
        JPanel fake2 = new JPanel();
        fake2.setPreferredSize(new Dimension(250,300));
        JPanel fake3 = new JPanel();
        fake3.setPreferredSize(new Dimension(200,140));
        JPanel fake4 = new JPanel();
        fake4.setPreferredSize(new Dimension(200,140));
        add(fake1, BorderLayout.WEST);
        add(fake2, BorderLayout.EAST);
        add(fake3, BorderLayout.NORTH);
        add(fake4, BorderLayout.SOUTH);
    }

    /**
     * make color panel.
     *
     * The method makes color panel for SettingMenu.
     */
    private void makeColorPanel() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0,255,0));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel1.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel1);
        JPanel jPanel2 = new JPanel();
        jPanel2.setBackground(new Color(165,255,3));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel2.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel2);
        JPanel jPanel3 = new JPanel();
        jPanel3.setBackground(new Color(255,255,0));
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel3.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel3);
        JPanel jPanel4 = new JPanel();
        jPanel4.setBackground(new Color(255,128,0));
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel4.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel4);
        JPanel jPanel5 = new JPanel();
        jPanel5.setBackground(new Color(128,64,0));
        jPanel5.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel5.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel5);
        JPanel jPanel6 = new JPanel();
        jPanel6.setBackground(new Color(255,16,0));
        jPanel6.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel6.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel6);
        JPanel jPanel7 = new JPanel();
        jPanel7.setBackground(new Color(255,85,238));
        jPanel7.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel7.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel7);
        JPanel jPanel8 = new JPanel();
        jPanel8.setBackground(new Color(128,0,255));
        jPanel8.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel8.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel8);
        JPanel jPanel9 = new JPanel();
        jPanel9.setBackground(Color.BLUE);
        jPanel9.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel9.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel9);

        customColors.get(numberOfColor).setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.cyan));
        for (JPanel temp : customColors) {
            temp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        if (((LineBorder) temp.getBorder()).getLineColor().equals(Color.black)) {
                            temp.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.cyan));
                            customColors.get(numberOfColor).setBorder(BorderFactory.createLineBorder(Color.black));
                            numberOfColor = customColors.indexOf(temp);
                        }
                    }catch (Exception ignored){}
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

                }
            });
            colorPanel.add(temp, BorderLayout.CENTER);
        }
    }

    /**
     * sign up.
     *
     */
    private void signUp() {
        String userFieldText = userField.getText();
        String passFieldText = passField.getText();

        if(userFieldText.equals("") && passFieldText.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password");
        }
        else {
            boolean flag = true;
            for (User temp : users) {
                if (userFieldText.equals(temp.getUsername()) && passFieldText.equals(temp.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Username is already in use");
                    userField.setText("");
                    passField.setText("");
                    userField.requestFocus();
                    flag = false;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "Account has been created.");
                users.add(new User(userFieldText, passFieldText, numberOfColor));
                TankTroubleGUI.saveUsers();
                frame.remove(this);
                TankTroubleGUI.login();
            }
        }
    }

    /**
     * login menu.
     *
     * The method backs to login menu.
     */
    private void loginMenu() {
        frame.remove(this);
        TankTroubleGUI.login();
    }

    /**
     * reset.
     *
     * The method resets fields.
     */
    public void reset() {
        userField.setText("");
        passField.setText("");
    }
}