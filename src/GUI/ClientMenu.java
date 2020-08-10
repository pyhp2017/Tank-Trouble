package GUI;

import Game.Network.Server.StaticData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * A class to design and manage JoinGame.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class ClientMenu extends JPanel {
    //frame
    private JFrame frame;
    //logged user
    private User loggedUser;
    //singleton instance
    private static ClientMenu INSTANCE = null;
    //server address
    private JTextField serverAddress;
    //joinGame
    private JButton joinGame;
    //back
    private JButton back;
    //game panel
    private LanGamePanel gamePanel;
    //lan Game
    public static LanGame lanGame;

    /**
     * Create a new LanMenu.
     *
     * @param frame frame
     */
    private ClientMenu(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));

        this.frame = frame;
        serverAddress = new JTextField();
        serverAddress.setText("127.0.0.1:8585");
        serverAddress.setEnabled(false);
        back = new JButton("BACK");
        joinGame = new JButton("JOIN GAME");

        makeMenu();
    }

    /**
     * Create a new JoinGame with singleton.
     *
     * @param frame frame
     * @return INSTANCE.
     */
    public static ClientMenu getInstance(JFrame frame) {
        if (INSTANCE == null)
            INSTANCE = new ClientMenu(frame);
        return INSTANCE;
    }

    /**
     * make menu.
     *
     * The method makes menu for ServerOrClientMenu.
     */
    private void makeMenu() {
        JPanel joinPanel = new JPanel();
        joinPanel.setLayout(new BoxLayout(joinPanel, BoxLayout.Y_AXIS));
        joinPanel.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Join Game"));

        //join address
        serverAddress.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Server Address"));


        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        joinGame.setBackground(new Color(225, 225, 225));
        buttonPanel.add(joinGame, BorderLayout.WEST);
        buttonPanel.add(back, BorderLayout.EAST);
        buttonPanel.setPreferredSize(new Dimension(100, 30));
        back.addActionListener(e -> back());
        joinGame.addActionListener(actionEvent -> joinGame());

        joinPanel.add(serverAddress);
        joinPanel.add(buttonPanel);

        add(joinPanel, BorderLayout.CENTER);
        //fake panels
        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(200, 300));
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(200, 300));
        JPanel jPanel3 = new JPanel();
        jPanel3.setPreferredSize(new Dimension(200, 200));
        JPanel jPanel4 = new JPanel();
        jPanel4.setPreferredSize(new Dimension(200, 200));
        add(jPanel1, BorderLayout.WEST);
        add(jPanel2, BorderLayout.EAST);
        add(jPanel3, BorderLayout.NORTH);
        add(jPanel4, BorderLayout.SOUTH);
    }

    /**
     * join game.
     *
     * The method goes to joinGame menu.
     */
    private void joinGame() {

        //todo for lanGame





//        lanGame = new LanGame(loggedUser.getShapeCode(), serverAddress.getText());
        gamePanel = new LanGamePanel(frame, loggedUser, lanGame);

        frame.remove(this);
        frame.add(gamePanel);
        TankTroubleGUI.fullScreen();

        StaticData.startAsClient = true;
        StaticData.netWorkGame = true;

        //        Connect.startGame();


        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * back.
     *
     * The method backs to main menu.
     */
    private void back() {
        frame.remove(this);
        TankTroubleGUI.mainMenu(loggedUser);
    }

    /**
     * set user to JoinGame.
     *
     * @param loggedUser loggedUser
     */
    public void setUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


}
