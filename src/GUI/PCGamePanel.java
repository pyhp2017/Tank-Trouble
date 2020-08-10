package GUI;

import javax.swing.*;
import java.awt.*;


/**
 * A class to design and manage PCGamePanel.
 *
 * @author Mohammadreza Shahrestani
 * @version 2.0
 */
public class PCGamePanel extends JPanel
{
    //-------------------------------------- FIELDS --------------------------------------------------
    private JFrame frame;
    private static final String TANKsIDE_PATH = "res/kit/tankSide/";
    private long startTime;
    private PCGame pcGame;
    private JPanel graphic;
    public static JLabel labelEND;
    private JPanel downPanel;
    public static JLabel userLabel;
    public static JLabel pcLabel;
    public static JProgressBar userHealth;
    public static JProgressBar pcHealth;
    public static String userResult;
    public static String pcResult;
    public static int userWin;
    public static int userLost;
    public static int pcWin;
    public static int pcLost;
    public static int roundCounter;
    public static JLabel roundCounterLabel;
    private final String crown = "👑";
    private final String skeletonSkull = "💀";
    private JButton exitGame;
    public static User loggedUser;


    /**
     * Create a new Game Panel
     * @param frame is a JFrame
     * @param loggedUser is a User
     * @param game is a game(Shared Class)
     */
    public PCGamePanel(JFrame frame, User loggedUser, PCGame game) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));

        startTime = System.currentTimeMillis();
        this.frame = frame;
        this.loggedUser = loggedUser;
        this.pcGame = game;
        graphic = new JPanel();
        downPanel = new JPanel();
        labelEND = new JLabel("GAME");
        labelEND.setFont(new Font("Chilanka", Font.PLAIN, 30));
        graphic.add(labelEND,BorderLayout.CENTER);
        userHealth = new JProgressBar();
        pcHealth = new JProgressBar();
        userWin = 0;
        userLost = 0;
        pcWin = 0;
        pcLost = 0;
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
        roundCounterLabel = new JLabel();
        roundCounterLabel.setText("  Round: " + roundCounter + "  ");
        roundCounterLabel.setFont(new Font("Chilanka", Font.PLAIN, 25));

        //health panels
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.X_AXIS));
        healthPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            //user panel
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
            userLabel = new JLabel();
            ImageIcon userIcon = new ImageIcon(TANKsIDE_PATH + "tankSide"  + loggedUser.getShapeCode() + ".png");
            Image userImage = userIcon.getImage();
            userIcon = new ImageIcon(userImage.getScaledInstance(90, 90,  Image.SCALE_SMOOTH));
            userLabel.setIcon(userIcon);
            userHealth.setValue(PCMenu.pcGame.getHealthOfTank());

            //pc panel
            JPanel pcPanel = new JPanel();
            pcPanel.setLayout(new BoxLayout(pcPanel, BoxLayout.Y_AXIS));
            pcLabel = new JLabel();
            ImageIcon pcIcon = new ImageIcon(TANKsIDE_PATH + "tankSide9.png");//Laika//tankSide" + 9 +
            Image pcImage = pcIcon.getImage();
            pcIcon = new ImageIcon(pcImage.getScaledInstance(90, 90,  Image.SCALE_SMOOTH));
            pcLabel.setIcon(pcIcon);
            pcHealth.setValue(PCMenu.pcGame.getAiTankHealth());

        userPanel.add(userLabel);
        pcPanel.add(pcLabel);
        if (pcGame.getNumberOfRounds() != 1) {
            userLabel.setText(userResult);
            pcLabel.setText(pcResult);
            userLabel.setFont(new Font("Chilanka", Font.PLAIN, 25));
            pcLabel.setFont(new Font("Chilanka", Font.PLAIN, 25));
        }
        userPanel.add(userHealth);
        pcPanel.add(pcHealth);

        healthPanel.add(userPanel);
        healthPanel.add(pcPanel);

        //exit
        exitGame.addActionListener(e -> backToMainMenu());
        exitGame.setBackground(new Color(225,225,225));

        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.X_AXIS));
        downPanel.add(exitGame);
        if (pcGame.getNumberOfRounds() != 1) {
            downPanel.add(roundCounterLabel);
        }
        downPanel.add(healthPanel);


        add(graphic, BorderLayout.CENTER);
        add(downPanel, BorderLayout.SOUTH);
    }


    /**
     * Back To main Menu
     */
    private void backToMainMenu()
    {
        PCMenu.pcGame.setStopGame(true);
        loggedUser.addTimeOfPlaying(System.currentTimeMillis() - startTime);
        TankTroubleGUI.saveUsers();
        frame.remove(this);
        TankTroubleGUI.mainMenu(loggedUser);
        TankTroubleGUI.normalScreen();
    }

    /**
     * Calculate User Result (didn't use)
     */
    private void calculateResult() {
        userResult = "" +  userWin;
        pcResult =   ""+ pcWin;
        if (userWin > pcWin) {
            userResult = userResult + crown;
        }
        if (userWin < pcWin) {
            pcResult = pcResult + crown;
        }
        if (userLost > pcLost) {
            userResult = skeletonSkull + userResult;
        }
        if (userLost < pcLost) {
            pcResult = skeletonSkull + pcResult;
        }
    }
}
