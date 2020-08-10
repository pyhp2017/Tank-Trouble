package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * A class to design and manage SettingMenu.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class SettingMenu extends JPanel {
    //frame
    private JFrame frame;
    //singleton instance
    private static SettingMenu INSTANCE = null;
    //tank side image address
    private static final String TANKsIDE_PATH = "res/kit/tankSide/";
    //logged user
    private User loggedUser;
    //username
    private JLabel username;
    //shape
    private JLabel shape;
    //time of playing
    private JLabel timeOfPlaying;
    //results of with PC
    private JLabel resultsOfWithPC;
    //results of with Lan
    private JLabel resultsOfWithLan;
    //default health of tank
    private JSlider defaultHealthOfTank;
    //default power of bullet
    private JSlider defaultPowerOfBullet;
    //default damage of wall
    private JSlider defaultDamageOfWall;
    //color panel
    private JPanel colorPanel;
    //number of color
    private int numberOfColor;
    //custom colors
    private ArrayList<JPanel> customColors;
    //ok
    private JButton ok;
    //back
    private JButton back;

    /**
     * Create a new SettingMenu.
     *
     * @param frame frame
     */
    private SettingMenu(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));
        this.frame = frame;
        username = new JLabel();
        shape = new JLabel();
        timeOfPlaying = new JLabel();
        resultsOfWithPC = new JLabel();
        resultsOfWithLan = new JLabel();
        defaultHealthOfTank = new JSlider(10,100,10);
        defaultPowerOfBullet = new JSlider(10,100,10);
        defaultDamageOfWall = new JSlider(10,100,10);
        colorPanel = new JPanel();
        numberOfColor = 0;
        customColors = new ArrayList<>();
        ok = new JButton("OK");
        back = new JButton("BACK");

        makeSetting();
    }

    /**
     * Create a new SettingMenu with singleton.
     *
     * @param frame frame
     * @return INSTANCE.
     */
    public static SettingMenu getInstance(JFrame frame) {
        if (INSTANCE == null)
            INSTANCE = new SettingMenu(frame);
        return INSTANCE;
    }

    /**
     * make setting menu.
     *
     * The method makes menu for SettingMenu.
     */
    private void makeSetting() {
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        settingPanel.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Setting"));

        //first panel
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BorderLayout());
        //shape
        shape.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Your tank"));
        Dimension size = new Dimension(100, 100);
        shape.setPreferredSize(size);
        shape.setMinimumSize(size);
        shape.setMaximumSize(size);
        shape.setSize(size);
        //username
        username.setPreferredSize(new Dimension(100, 25));
        username.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Your name"));
        username.setHorizontalAlignment(JLabel.CENTER);
        username.setFont(new Font("Chilanka", Font.BOLD, 25));
        firstPanel.setPreferredSize(new Dimension(200, 100));
        firstPanel.add(shape, BorderLayout.WEST);
        firstPanel.add(username);

        //time
        JPanel timePanel = new JPanel();
        timeOfPlaying.setHorizontalAlignment(JLabel.CENTER);
        timePanel.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Time of gaming"));
        timePanel.add(timeOfPlaying);
        timePanel.add(new JPanel());

        //result panel
        JPanel result = new JPanel();
        result.setLayout(new GridLayout(1,2));
        //PC
        resultsOfWithPC.setHorizontalAlignment(JLabel.CENTER);
        resultsOfWithPC.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Computer result"));
        //Lan
        resultsOfWithLan.setHorizontalAlignment(JLabel.CENTER);
        resultsOfWithLan.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Lan result"));
        result.add(resultsOfWithPC);
        result.add(resultsOfWithLan);

        //Sliders
        defaultHealthOfTank.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Health of tanks"));
        defaultHealthOfTank.setPaintTrack(true);
        defaultHealthOfTank.setPaintTicks(true);
        defaultHealthOfTank.setPaintLabels(true);
        defaultHealthOfTank.setMajorTickSpacing(10);
        defaultPowerOfBullet.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Power of bullets"));
        defaultPowerOfBullet.setPaintTrack(true);
        defaultPowerOfBullet.setPaintTicks(true);
        defaultPowerOfBullet.setPaintLabels(true);
        defaultPowerOfBullet.setMajorTickSpacing(10);
        defaultDamageOfWall.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Health of walls"));
        defaultDamageOfWall.setPaintTrack(true);
        defaultDamageOfWall.setPaintTicks(true);
        defaultDamageOfWall.setPaintLabels(true);
        defaultDamageOfWall.setMajorTickSpacing(10);


        //custom panel
        colorPanel.setBorder(new TitledBorder( new LineBorder(new Color(225,225,225)), "Color of your tank"));
        colorPanel.setPreferredSize(new Dimension(100, 40));
        colorPanel.setLayout(new GridLayout(1,9));
        makeColorPanel();

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        ok.setBackground(new Color(225,225,225));
        buttonPanel.add(ok, BorderLayout.WEST);
        buttonPanel.add(back, BorderLayout.EAST);
        buttonPanel.setPreferredSize(new Dimension(100, 30));
        ok.addActionListener(e -> ok());
        back.addActionListener(e -> back());

        settingPanel.add(firstPanel, BorderLayout.NORTH);
        settingPanel.add(timePanel);
        settingPanel.add(result);
        settingPanel.add(defaultHealthOfTank);
        settingPanel.add(defaultPowerOfBullet);
        settingPanel.add(defaultDamageOfWall);
        settingPanel.add(colorPanel);
        settingPanel.add(buttonPanel);

        add(settingPanel, BorderLayout.CENTER);
        //fake panels
        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(200,300));
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(200,300));
        add(jPanel1, BorderLayout.WEST);
        add(jPanel2, BorderLayout.EAST);
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
        jPanel9.setBackground(new Color(0,0,255));
        jPanel9.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel9.setPreferredSize(new Dimension(25, 25));
        customColors.add(jPanel9);

        for (JPanel temp : customColors) {
            temp.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        if (((LineBorder) temp.getBorder()).getLineColor().equals(Color.black)) {
                            temp.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.cyan));
                            customColors.get(numberOfColor).setBorder(BorderFactory.createLineBorder(Color.black));
                            numberOfColor = customColors.indexOf(temp);
                            ImageIcon icon = new ImageIcon(TANKsIDE_PATH + "tankSide" +  numberOfColor + ".png");
                            Image image = icon.getImage();
                            icon = new ImageIcon(image.getScaledInstance(90, 90,  Image.SCALE_SMOOTH));
                            shape.setIcon(icon);
                            updateUI();
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
     * back.
     *
     * The method backs to main menu.
     */
    private void back() {
        frame.remove(this);
        TankTroubleGUI.mainMenu(loggedUser);
    }

    /**
     * OK.
     *
     * The method saves setting.
     */
    private void ok() {
        loggedUser.setDefaultHealthOfTank(defaultHealthOfTank.getValue());
        loggedUser.setDefaultPowerOfBullet(defaultPowerOfBullet.getValue());
        loggedUser.setDefaultDamageOfWall(defaultDamageOfWall.getValue());
        loggedUser.setShapeCode(numberOfColor);
        TankTroubleGUI.saveUsers();
        frame.remove(this);
        TankTroubleGUI.mainMenu(loggedUser);
    }

    /**
     * set user to SettingMenu
     *
     * @param loggedUser loggedUser
     */
    public void setUser(User loggedUser) {
        this.loggedUser = loggedUser;
        username.setText(loggedUser.getUsername());
        customColors.get(numberOfColor).setBorder(BorderFactory.createLineBorder(Color.black));
        numberOfColor = loggedUser.getShapeCode();
        customColors.get(numberOfColor).setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.cyan));
        ImageIcon icon = new ImageIcon(TANKsIDE_PATH + "tankSide" + numberOfColor + ".png");
        Image image = icon.getImage();
        icon = new ImageIcon(image.getScaledInstance(90, 90,  Image.SCALE_SMOOTH));
        shape.setIcon(icon);
        long temp = loggedUser.getTimeOfPlaying();
        long day = temp / ( 3600 * 24 * 1000);
        long hour = temp / (3600 * 1000) % 24;
        long minute = temp / (60 * 1000) % 60;
        long second = temp / (1000) % 60;
        String s = day + " days , " + hour + ":" + minute + ":" + second;
        timeOfPlaying.setText(s);
        resultsOfWithPC.setText(loggedUser.getNumberOfLostWithPC() + " lost / " + loggedUser.getNumberOfWinWithPC() + " win");
        resultsOfWithLan.setText(loggedUser.getNumberOfLostWithLan() + " lost / " + loggedUser.getNumberOfWinWithLan() + " win");
        defaultHealthOfTank.setValue(loggedUser.getDefaultHealthOfTank());
        defaultPowerOfBullet.setValue(loggedUser.getDefaultPowerOfBullet());
        defaultDamageOfWall.setValue(loggedUser.getDefaultDamageOfWall());
        updateUI();
    }

}
