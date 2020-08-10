package GUI;

import Game.Network.Server.StaticData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Objects;


/**
 * A class to design and manage PCMenu.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class PCMenu extends JPanel
{
    //frame
    private JFrame frame;
    //logged user
    private User loggedUser;
    //gameMenu
    private PCGamePanel gameMenu;
    //singleton instance
    private static PCMenu INSTANCE = null;
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
    private JButton start;
    //back
    private JButton back;
    //pc Game
    public static PCGame pcGame;

    /**
     * Create a new PCMenu.
     *
     * @param frame frame
     */
    private PCMenu(JFrame frame)
    {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));
        this.frame = frame;
        gameMode = new JComboBox(new String[]{"League", "Deathmatch"});
        numberOfPlayers = new JComboBox(new String[]{"2 Players", "3 Players", "4 Players"});
        numberOfRounds = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
        healthOfTank = new JSlider(10,100,10);
        powerOfBullet = new JSlider(10,100,10);
        damageOfWall = new JSlider(10,100,10);
        start = new JButton("START");
        back = new JButton("BACK");
        makeMenu();
    }

    /**
     * Create a new PCMenu with singleton.
     *
     * @param frame frame
     * @return INSTANCE.
     */
    public static PCMenu getInstance(JFrame frame)
    {
        if (INSTANCE == null)
            INSTANCE = new PCMenu(frame);
        return INSTANCE;
    }

    /**
     * make menu.
     *
     * The method makes menu for PCMenu.
     */
    private void makeMenu()
    {
        JPanel PCPanel = new JPanel();
        PCPanel.setLayout(new BoxLayout(PCPanel, BoxLayout.Y_AXIS));
        PCPanel.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "PC Game"));

        //game mode panel
        JPanel gameModePanel = new JPanel();
        gameModePanel.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Game mode"));
        gameModePanel.setLayout(new GridLayout(1,3));
        gameModePanel.setPreferredSize(new Dimension(200, 50));
        //game mode
        gameMode.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Mode"));
        gameMode.addActionListener(e -> {
            if (Objects.equals(gameMode.getSelectedItem(), "League")) {
                numberOfRounds.setValue(2);
                numberOfRounds.setEnabled(true);
            }
            else {
                numberOfRounds.setValue(1);
                numberOfRounds.setEnabled(false);
            }
        });
        //number of rounds
        numberOfRounds.setFont(new Font("Chilanka", Font.BOLD, 20));
        numberOfRounds.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Rounds"));
        //number of players
        numberOfPlayers.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Players"));
        numberOfPlayers.setEnabled(false);
        gameModePanel.add(numberOfPlayers);
        gameModePanel.add(numberOfRounds);
        gameModePanel.add(gameMode);


        //Sliders
        healthOfTank.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Health of tanks"));
        healthOfTank.setPaintTrack(true);
        healthOfTank.setPaintTicks(true);
        healthOfTank.setPaintLabels(true);
        healthOfTank.setMajorTickSpacing(10);
        powerOfBullet.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Power of bullets"));
        powerOfBullet.setPaintTrack(true);
        powerOfBullet.setPaintTicks(true);
        powerOfBullet.setPaintLabels(true);
        powerOfBullet.setMajorTickSpacing(10);
        damageOfWall.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Health of walls"));
        damageOfWall.setPaintTrack(true);
        damageOfWall.setPaintTicks(true);
        damageOfWall.setPaintLabels(true);
        damageOfWall.setMajorTickSpacing(10);

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        start.setBackground(new Color(225,225,225));
        buttonPanel.add(start, BorderLayout.WEST);
        buttonPanel.add(back, BorderLayout.EAST);
        buttonPanel.setPreferredSize(new Dimension(100, 30));
        start.addActionListener(e -> start());
        back.addActionListener(actionEvent -> back());

        PCPanel.add(gameModePanel, BorderLayout.NORTH);
        PCPanel.add(healthOfTank);
        PCPanel.add(powerOfBullet);
        PCPanel.add(damageOfWall);
        PCPanel.add(buttonPanel);

        add(PCPanel, BorderLayout.CENTER);
        //fake panels
        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(200,300));
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(200,300));
        JPanel jPanel3 = new JPanel();
        jPanel3.setPreferredSize(new Dimension(200,90));
        JPanel jPanel4 = new JPanel();
        jPanel4.setPreferredSize(new Dimension(200,90));
        add(jPanel1, BorderLayout.WEST);
        add(jPanel2, BorderLayout.EAST);
        add(jPanel3, BorderLayout.NORTH);
        add(jPanel4, BorderLayout.SOUTH);
    }


    /**
     * start.
     *
     * The method makes PCGame and PCGamePanel.
     */
    private void start()
    {
        pcGame = new PCGame((int)numberOfRounds.getValue(), healthOfTank.getValue(),
                powerOfBullet.getValue(), damageOfWall.getValue(), loggedUser.getShapeCode() , loggedUser.getUsername());

        gameMenu = new PCGamePanel(frame, loggedUser, pcGame);

        frame.remove(this);
        frame.add(gameMenu);
        TankTroubleGUI.fullScreen();

        StaticData.netWorkGame = false;
        Connect.startGame();

        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * back.
     *
     * The method backs to main menu.
     */
    private void back()
    {
        frame.remove(this);
        TankTroubleGUI.mainMenu(loggedUser);
    }

    /**
     * set user to PCMenu
     *
     * @param loggedUser loggedUser
     */
    public void setUser(User loggedUser)
    {
        this.loggedUser = loggedUser;
        healthOfTank.setValue(loggedUser.getDefaultHealthOfTank());
        powerOfBullet.setValue(loggedUser.getDefaultPowerOfBullet());
        damageOfWall.setValue(loggedUser.getDefaultDamageOfWall());
        updateUI();
    }
}
