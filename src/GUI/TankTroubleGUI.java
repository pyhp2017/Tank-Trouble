package GUI;

import com.jtattoo.plaf.JTattooUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * A class to design and manage Tank Trouble GUI.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class TankTroubleGUI {
    //users address
    private static final String USER_PATH = "res/UserSettings/user.bin";
    //icon address
    private static final String ICON_PATH = "res/icon.png";
    //singleton instance
    private static TankTroubleGUI INSTANCE = null;
    //frame
    private static JFrame frame;
    //Welcome Panel
    private WelcomePanel welcomePanel;
    //login
    private static LoginMenu loginMenu;
    //Register
    private static RegisterMenu registerMenu;
    //Main Menu
    private static MainMenu mainMenu;
    //PC Menu
    private static PCMenu pcMenu;
    //server or client Menu
    private static ServerOrClientMenu serverOrClientMenu;
    //Lan Menu
    private static ServerMenu serverGame;
    //joinGame
    private static ClientMenu joinGame;
    //Setting Menu
    private static SettingMenu settingMenu;
    //users
    private static ArrayList<User> users;
    //logged user
    private User loggedUser;
    //condition of welcome
    private boolean isWelcome = false;

    /**
     * Create a new TankTroubleGUI.
     *
     */
    private TankTroubleGUI(){
        frame = new JFrame();
        users = new ArrayList<>();
        loadUsers();
        welcomePanel = WelcomePanel.getInstance();
        loginMenu = LoginMenu.getInstance(frame, users);
        registerMenu = RegisterMenu.getInstance(frame, users);
        mainMenu = MainMenu.getInstance(frame);
        pcMenu = PCMenu.getInstance(frame);
        serverGame = ServerMenu.getInstance(frame);
        serverOrClientMenu = ServerOrClientMenu.getInstance(frame);
        joinGame = ClientMenu.getInstance(frame);
        settingMenu = SettingMenu.getInstance(frame);

        //look and feel
        try {
            javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.texture.TextureLookAndFeel.class.getName());
        } catch (Exception ignored) {}

        makeFrame();
        welcome();
        frame.setVisible(true);
    }


    /**
     * Create a new TankTroubleGUI with singleton.
     *
     * @return INSTANCE.
     */
    public static TankTroubleGUI getInstance() {
        if (INSTANCE == null)
            INSTANCE = new TankTroubleGUI();
        return INSTANCE;
    }

    /**
     * make frame
     *
     * The method makes frame for TankTroubleGUI.
     */
    private void makeFrame() {
        frame.setTitle("Tank Trouble");
        frame.setSize(740, 530);
        frame.setLocation(300, 200);
        frame.setBackground(Color.white);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                saveUsers();
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Icon
        ImageIcon icon = new ImageIcon(ICON_PATH);
        frame.setIconImage(icon.getImage());

    }

    /**
     * load users.
     *
     * The method loads all of users.
     */
    private static void loadUsers() {
        try (FileInputStream fs = new FileInputStream(USER_PATH)){
            ObjectInputStream os = new ObjectInputStream(fs);
            users = (ArrayList<User>)os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("cannot read");
            e.printStackTrace();
        }
    }

    /**
     * save users.
     *
     * The method saves users.
     */
    public static void saveUsers() {
        try (FileOutputStream fs = new FileOutputStream(USER_PATH )){
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * welcome
     *
     * The method welcomes game to users.
     */
    private void welcome() {
        frame.add(welcomePanel);

        welcomePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isWelcome = true;
                frame.remove(welcomePanel);
                if (existRememberMeUser()) {
                    mainMenu(loggedUser);
                }
                else {
                    login();
                }
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

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if (!isWelcome) {
                            frame.remove(welcomePanel);
                            if (existRememberMeUser()) {
                                mainMenu(loggedUser);
                            }
                            else {
                                login();
                            }
                        }
                    }
                },
                4000
        );
    }

    /**
     * exist remember me User
     *
     * The method determines condition of last remember me user.
     */
    private boolean existRememberMeUser() {
        for (User temp : users) {
            if (temp.isRememberMe()) {
                loggedUser = temp;
                return true;
            }
        }
        return false;
    }


    /**
     * login
     *
     * The method add login panel.
     */
    public static void login() {
        loginMenu.reset();
        frame.add(loginMenu);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * Sign up
     *
     * The method add register panel.
     */
    public static void signUp() {
        registerMenu.reset();
        frame.add(registerMenu);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * Sign up
     *
     * The method add register panel.
     * @param loggedUser loggedUser
     */
    public static void mainMenu(User loggedUser) {
        mainMenu.setUser(loggedUser);
        frame.add(mainMenu);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * setting Menu
     *
     * The method add settingMenu panel.
     * @param loggedUser loggedUser
     */
    public static void settingMenu(User loggedUser) {
        settingMenu.setUser(loggedUser);
        frame.add(settingMenu);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * pc Menu
     *
     * The method add pcMenu panel.
     * @param loggedUser loggedUser
     */
    public static void pcMenu(User loggedUser) {
        pcMenu.setUser(loggedUser);
        frame.add(pcMenu);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * lan Menu
     *
     * The method adds lanMenu panel.
     * @param loggedUser loggedUser
     */
    public static void serverMenu(User loggedUser) {
        serverGame.setUser(loggedUser);
        frame.add(serverGame);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * joinGame Menu
     *
     * The method adds joinGame panel.
     * @param loggedUser loggedUser
     */
    public static void joinGame(User loggedUser) {
        joinGame.setUser(loggedUser);
        frame.add(joinGame);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * ServerOrClient Menu
     *
     * The method adds ServerOrClient panel.
     * @param loggedUser loggedUser
     */
    public static void serverOrClient(User loggedUser) {
        serverOrClientMenu.setUser(loggedUser);
        frame.add(serverOrClientMenu);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * fullScreen
     *
     * The method makes frame full screen.
     */
    public static void fullScreen() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * normalScreen
     *
     * The method makes frame normal screen.
     */
    public static void normalScreen() {
        frame.setExtendedState(JFrame.NORMAL);
    }

    /**
     * Change JFrame Font
     * @param component is a Component
     * @param font is a Font
     */
    public static void changeFont ( Component component, Font font )
    {
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }


    public static String getIconPath() {
        return ICON_PATH;
    }
}
