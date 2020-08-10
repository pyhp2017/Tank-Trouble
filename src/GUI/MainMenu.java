package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static GUI.TankTroubleGUI.changeFont;

/**
 * A class to design and manage MainMenu.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class MainMenu extends JPanel {
    //frame
    private JFrame frame;
    //logged user
    private User loggedUser;
    //singleton instance
    private static MainMenu INSTANCE = null;
    //main menu picture address
    private static final String MAINmENU_PATH  = "res/Tank-Trouble-menu.jpg";
    //background
    private ImageIcon background;
    //menu
    private JPanel menu;
    //with PC button
    private JButton withPCButton;
    //lan button
    private JButton lanButton;
    //setting button
    private JButton settingButton;
    //logout button
    private JButton logoutButton;

    /**
     * Create a new MainMenu.
     *
     * @param frame frame
     */
    private MainMenu(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));
        background = new ImageIcon(MAINmENU_PATH);
        Dimension size = new Dimension(background.getImage().getWidth(null), background.getImage().getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);

        this.frame = frame;
        menu = new JPanel();
        withPCButton = new JButton("Computer Player");
        lanButton = new JButton("MultiPlayer");
        settingButton = new JButton("Setting");
        logoutButton = new JButton("Log out");

        makeMenu();

        add(menu, BorderLayout.EAST);
    }


    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background.getImage(), 0, 250, null);
    }

    /**
     * Create a new MainMenu with singleton.
     *
     * @param frame frame
     * @return INSTANCE.
     */
    public static MainMenu getInstance(JFrame frame) {
        if (INSTANCE == null)
            INSTANCE = new MainMenu(frame);
        return INSTANCE;
    }

    /**
     * make menu.
     *
     * The method makes menu for MainMenu.
     */
    private void makeMenu() {
        menu.setLayout(new GridLayout(11, 1));
        menu.setPreferredSize(new Dimension(250,300));
        menu.setBackground(Color.white);

        withPCButton.setFont(new Font("Chilanka", Font.BOLD, 20));
        withPCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withPC();
            }
        });

        lanButton.setFont(new Font("Chilanka", Font.BOLD, 20));
        lanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lan();
            }
        });

        settingButton.setFont(new Font("Chilanka", Font.BOLD, 20));
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setting();
            }
        });

        logoutButton.setFont(new Font("Chilanka", Font.BOLD, 20));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        //fake panels
        JPanel fake1 = new JPanel();
        JPanel fake2 = new JPanel();
        JPanel fake3 = new JPanel();
        JPanel fake4 = new JPanel();
        JPanel fake5 = new JPanel();
        JPanel fake6 = new JPanel();
        JPanel fake7 = new JPanel();
        fake1.setBackground(Color.white);
        fake2.setBackground(Color.white);
        fake3.setBackground(Color.white);
        fake4.setBackground(Color.white);
        fake5.setBackground(Color.white);
        fake6.setBackground(Color.white);
        fake7.setBackground(Color.white);

        menu.add(fake1);
        menu.add(fake2);
        menu.add(withPCButton);
        menu.add(fake3);
        menu.add(lanButton);
        menu.add(fake4);
        menu.add(settingButton);
        menu.add(fake5);
        menu.add(logoutButton);
        menu.add(fake6);
        menu.add(fake7);
    }

    /**
     * withPC.
     *
     * The method goes to PCMenu.
     */
    private void withPC() {
        frame.remove(this);
        TankTroubleGUI.pcMenu(loggedUser);
    }

    /**
     * lan.
     *
     * The method goes to LanMenu.
     */
    private void lan() {
        frame.remove(this);
        TankTroubleGUI.serverOrClient(loggedUser);
    }

    /**
     * setting.
     *
     * The method goes to SettingMenu.
     */
    private void setting() {
        frame.remove(this);
        TankTroubleGUI.settingMenu(loggedUser);
    }

    /**
     * logout.
     *
     * The method goes to LoginMenu.
     */
    private void logout() {
        loggedUser.setRememberMe(false);
        TankTroubleGUI.saveUsers();
        frame.remove(this);
        TankTroubleGUI.login();
    }

    /**
     * set user to MainMenu
     *
     * @param loggedUser loggedUser
     */
    public void setUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
