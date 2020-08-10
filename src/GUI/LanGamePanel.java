package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A class to design and manage PCGamePanel.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class LanGamePanel extends JPanel
{
    //----------------------------------- FIELDS -------------------------------------------
    private JFrame frame;
    private static final String TANKsIDE_PATH = "res/kit/tankSide/";
    private long startTime;
    private LanGame lanGame;
    private JPanel graphic;
    private JPanel downPanel;
    private JProgressBar userHealth;
    private JProgressBar playerHealth;
    private String userResult;
    private String playerResult;
    private int userWin;
    private int userLost;
    private int playerShapeCode;
    private int playerWin;
    private int playerLost;
    private int roundCounter;
    private final String crown = "👑";
    private final String skeletonSkull = "💀";
    private JButton exitGame;
    private User loggedUser;

    /**
     * Create a new lan game
     */
    public LanGamePanel(JFrame frame, User loggedUser, LanGame game) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));

        startTime = System.currentTimeMillis();
        this.frame = frame;
        this.loggedUser = loggedUser;
        this.lanGame = game;
        graphic = new JPanel();
        downPanel = new JPanel();
        userHealth = new JProgressBar();
        playerHealth = new JProgressBar();
        userWin = 0;
        userLost = 0;
        playerWin = 0;
        playerLost = 0;
        roundCounter = 1;
        exitGame = new JButton("Exit game");

        calculateResult();
        makeMenu();
    }


    /**
     * Make Menu
     */
    private void makeMenu() {
        graphic.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel healthPanel = new JPanel();

        //Round Counter
        JLabel roundCounterLabel = new JLabel();
        roundCounterLabel.setText("  Round: " + roundCounter + "  ");
        roundCounterLabel.setFont(new Font("Chilanka", Font.PLAIN, 25));

        //health panels
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.X_AXIS));
        healthPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //user panel
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        JLabel userLabel = new JLabel();
        ImageIcon userIcon = new ImageIcon(TANKsIDE_PATH + "tankSide"  + loggedUser.getShapeCode() + ".png");
        Image userImage = userIcon.getImage();
        userIcon = new ImageIcon(userImage.getScaledInstance(90, 90,  Image.SCALE_SMOOTH));
        userLabel.setIcon(userIcon);
        userHealth.setValue(50);

        //palyer panel
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        JLabel playerLabel = new JLabel();
        ImageIcon playerIcon = new ImageIcon(TANKsIDE_PATH + "tankSide" +  playerShapeCode + ".png");//Laika//tankSide" + 9 +
        Image player = playerIcon.getImage();
        playerIcon = new ImageIcon(player.getScaledInstance(90, 90,  Image.SCALE_SMOOTH));
        playerLabel.setIcon(playerIcon);
        playerHealth.setValue(50);

        userPanel.add(userLabel);
        playerPanel.add(playerLabel);
        if (lanGame.getNumberOfRounds() != 1) {
            userLabel.setText(userResult);
            playerLabel.setText(playerResult);
            userLabel.setFont(new Font("Chilanka", Font.PLAIN, 25));
            playerLabel.setFont(new Font("Chilanka", Font.PLAIN, 25));
        }
        userPanel.add(userHealth);
        playerPanel.add(playerHealth);

        healthPanel.add(userPanel);
        healthPanel.add(playerPanel);

        //exit
        exitGame.addActionListener(e -> backToMainMenu());
        exitGame.setBackground(new Color(225,225,225));

        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.X_AXIS));
        downPanel.add(exitGame);
        if (lanGame.getNumberOfRounds() != 1) {
            downPanel.add(roundCounterLabel);
        }
        downPanel.add(healthPanel);


        add(graphic, BorderLayout.CENTER);
        add(downPanel, BorderLayout.SOUTH);
    }

    /**
     * Back to Main
     */
    private void backToMainMenu() {
        PCMenu.pcGame.setStopGame(true);
        loggedUser.addTimeOfPlaying(System.currentTimeMillis() - startTime);
        TankTroubleGUI.saveUsers();
        frame.remove(this);
        TankTroubleGUI.mainMenu(loggedUser);
        TankTroubleGUI.normalScreen();
    }

    /**
     * Calculate Winner
     */
    private void calculateResult() {
        userResult = userLost + " - " +  userWin;
        playerResult =   playerLost + " - " + playerWin;
        if (userWin > playerWin) {
            userResult = userResult + crown;
        }
        if (userWin < playerWin) {
            playerResult = playerResult + crown;
        }
        if (userLost > playerLost) {
            userResult = skeletonSkull + userResult;
        }
        if (userLost < playerLost) {
            playerResult = skeletonSkull + playerResult;
        }
    }
}
