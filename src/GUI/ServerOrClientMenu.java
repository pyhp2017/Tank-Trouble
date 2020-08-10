package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * A class to design and manage ServerOrClientMenu.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class ServerOrClientMenu extends JPanel {
    //frame
    private JFrame frame;
    //logged user
    private User loggedUser;
    //singleton instance
    private static ServerOrClientMenu INSTANCE = null;
    //makeGame
    private JButton createGame;
    //joinGame
    private JButton joinGame;

    /**
     * Create a new LanMenu.
     *
     * @param frame frame
     */
    private ServerOrClientMenu(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));

        this.frame = frame;
        createGame = new JButton("CREATE GAME");
        joinGame = new JButton("JOIN GAME");

        makeMenu();
    }

    /**
     * Create a new ServerOrClientMenu with singleton.
     *
     * @param frame frame
     * @return INSTANCE.
     */
    public static ServerOrClientMenu getInstance(JFrame frame) {
        if (INSTANCE == null)
            INSTANCE = new ServerOrClientMenu(frame);
        return INSTANCE;
    }

    /**
     * make menu.
     *
     * The method makes menu for ServerOrClientMenu.
     */
    private void makeMenu() {
        JPanel serverOrClientPanel = new JPanel();
        serverOrClientPanel.setLayout(new BoxLayout(serverOrClientPanel, BoxLayout.Y_AXIS));
        serverOrClientPanel.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Create or Join?"));

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        createGame.setBackground(new Color(225, 225, 225));
        joinGame.setBackground(new Color(225, 225, 225));
        buttonPanel.add(createGame, BorderLayout.WEST);
        buttonPanel.add(joinGame, BorderLayout.EAST);
        buttonPanel.setPreferredSize(new Dimension(100, 30));
        createGame.addActionListener(e -> createGame());
        joinGame.addActionListener(actionEvent -> joinGame());

        serverOrClientPanel.add(buttonPanel);

        add(serverOrClientPanel, BorderLayout.CENTER);
        //fake panels
        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(200, 300));
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(200, 300));
        JPanel jPanel3 = new JPanel();
        jPanel3.setPreferredSize(new Dimension(200, 220));
        JPanel jPanel4 = new JPanel();
        jPanel4.setPreferredSize(new Dimension(200, 200));
        add(jPanel1, BorderLayout.WEST);
        add(jPanel2, BorderLayout.EAST);
        add(jPanel3, BorderLayout.NORTH);
        add(jPanel4, BorderLayout.SOUTH);
    }

    /**
     * create game.
     *
     * The method makes goes Server menu.
     */
    private void createGame() {
        frame.remove(this);
        TankTroubleGUI.serverMenu(loggedUser);
    }

    /**
     * join game.
     *
     * The method goes to joinGame menu.
     */
    private void joinGame() {
        frame.remove(this);
        TankTroubleGUI.joinGame(loggedUser);
    }

    /**
     * set user to ServerOrClientMenu.
     *
     * @param loggedUser loggedUser
     */
    public void setUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


}
