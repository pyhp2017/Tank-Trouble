package GUI;

import Game.Network.Server.StaticData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;
import java.util.Objects;

/**
 * A class to design and manage LanMenu.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class ServerMenu extends JPanel {
    //frame
    private JFrame frame;
    //logged user
    private User loggedUser;
    //singleton instance
    private static ServerMenu INSTANCE = null;
    //nameOfGame
    private JTextField nameOfGame;
    //game mode
    private JComboBox gameMode;
    //number of rounds
    private JSpinner numberOfRounds;
    //number of players
    private JComboBox numberOfPlayers;
    //health of tank
    private JSlider healthOfTank;
    //power of bullet
    private JSlider powerOfBullet;
    //damage of wall
    private JSlider damageOfWall;
    //start
    private JButton create;
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
    private ServerMenu(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));

        this.frame = frame;
        nameOfGame = new JTextField();
        gameMode = new JComboBox(new String[]{"League", "Deathmatch"});
        numberOfPlayers = new JComboBox(new String[]{"2 Players", "3 Players", "4 Players"});
        numberOfRounds = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
        healthOfTank = new JSlider(10, 100, 10);
        powerOfBullet = new JSlider(10, 100, 10);
        damageOfWall = new JSlider(10, 100, 10);
        create = new JButton("CREATE");
        back = new JButton("BACK");

        makeMenu();
    }

    /**
     * Create a new LanMenu with singleton.
     *
     * @param frame frame
     * @return INSTANCE.
     */
    public static ServerMenu getInstance(JFrame frame) {
        if (INSTANCE == null)
            INSTANCE = new ServerMenu(frame);
        return INSTANCE;
    }

    /**
     * make menu.
     *
     * The method makes menu for LanMenu.
     */
    private void makeMenu() {
        JPanel lanPanel = new JPanel();
        lanPanel.setLayout(new BoxLayout(lanPanel, BoxLayout.Y_AXIS));
        lanPanel.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Lan Game"));

        //name of game
        nameOfGame.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Name of game"));
        JPanel nameAndModePanel = new JPanel();
        nameAndModePanel.setLayout(new GridLayout(2, 1));
        nameAndModePanel.add(nameOfGame);

        //game mode panel
        JPanel gameModePanel = new JPanel();
        gameModePanel.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Game mode"));
        gameModePanel.setLayout(new GridLayout(1, 3));
        gameModePanel.setPreferredSize(new Dimension(200, 50));
        //game mode
        gameMode.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Mode"));
        gameMode.addActionListener(e -> {
            if (Objects.equals(gameMode.getSelectedItem(), "League")) {
                numberOfRounds.setValue(2);
                numberOfRounds.setEnabled(true);
            } else {
                numberOfRounds.setValue(1);
                numberOfRounds.setEnabled(false);
            }
        });
        //number of rounds
        numberOfRounds.setFont(new Font("Chilanka", Font.BOLD, 20));
        numberOfRounds.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Rounds"));
        //number of players
        numberOfPlayers.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Players"));
        numberOfPlayers.setEnabled(false);
        gameModePanel.add(numberOfPlayers);
        gameModePanel.add(numberOfRounds);
        gameModePanel.add(gameMode);

        //Sliders
        healthOfTank.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Health of tanks"));
        healthOfTank.setPaintTrack(true);
        healthOfTank.setPaintTicks(true);
        healthOfTank.setPaintLabels(true);
        healthOfTank.setMajorTickSpacing(10);
        powerOfBullet.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Power of bullets"));
        powerOfBullet.setPaintTrack(true);
        powerOfBullet.setPaintTicks(true);
        powerOfBullet.setPaintLabels(true);
        powerOfBullet.setMajorTickSpacing(10);
        damageOfWall.setBorder(new TitledBorder(new LineBorder(new Color(225, 225, 225)), "Health of walls"));
        damageOfWall.setPaintTrack(true);
        damageOfWall.setPaintTicks(true);
        damageOfWall.setPaintLabels(true);
        damageOfWall.setMajorTickSpacing(10);

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        create.setBackground(new Color(225, 225, 225));
        buttonPanel.add(create, BorderLayout.WEST);
        buttonPanel.add(back, BorderLayout.EAST);
        buttonPanel.setPreferredSize(new Dimension(100, 30));
        create.addActionListener(e -> create());
        back.addActionListener(actionEvent -> back());

        nameAndModePanel.add(gameModePanel);
        lanPanel.add(nameAndModePanel);
        lanPanel.add(healthOfTank);
        lanPanel.add(powerOfBullet);
        lanPanel.add(damageOfWall);
        lanPanel.add(buttonPanel);

        add(lanPanel, BorderLayout.CENTER);
        //fake panels
        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(200, 300));
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(200, 300));
        JPanel jPanel3 = new JPanel();
        jPanel3.setPreferredSize(new Dimension(200, 60));
        JPanel jPanel4 = new JPanel();
        jPanel4.setPreferredSize(new Dimension(200, 60));
        add(jPanel1, BorderLayout.WEST);
        add(jPanel2, BorderLayout.EAST);
        add(jPanel3, BorderLayout.NORTH);
        add(jPanel4, BorderLayout.SOUTH);
    }

    /**
     * create.
     *
     * The method makes LanGame and LanGamePanel.
     */
    private void create() {

        //todo for lan game

        lanGame = new LanGame((int) numberOfRounds.getValue(), healthOfTank.getValue(),
                powerOfBullet.getValue(), damageOfWall.getValue(), loggedUser.getShapeCode(),loggedUser.getUsername(),nameOfGame.getText(),"127.0.0.1:8585");

        gamePanel = new LanGamePanel(frame, loggedUser, lanGame);

        frame.remove(this);

        frame.add(gamePanel);
        TankTroubleGUI.fullScreen();

        StaticData.startAsServer = true;
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
     * set user to LanMenu
     *
     * @param loggedUser loggedUser
     */
    public void setUser(User loggedUser) {
        this.loggedUser = loggedUser;
        healthOfTank.setValue(loggedUser.getDefaultHealthOfTank());
        powerOfBullet.setValue(loggedUser.getDefaultPowerOfBullet());
        damageOfWall.setValue(loggedUser.getDefaultDamageOfWall());
        updateUI();
    }
}