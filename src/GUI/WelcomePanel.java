package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A class to design and manage WelcomePanel.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class WelcomePanel extends JPanel
{
    //welcome image address
    private static final String WELCOME_PATH = "res/Tank-Trouble.jpg";
    //jLabel
    private JLabel jLabel;
    //singleton instance
    private static WelcomePanel INSTANCE = null;

    /**
     * Create a new WelcomePanel.
     */
    private WelcomePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white));

        jLabel = new JLabel();
        showGamePicture();
    }

    /**
     * Create a new WelcomePanel with singleton.
     *
     * @return INSTANCE.
     */
    public static WelcomePanel getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new WelcomePanel();
        return INSTANCE;
    }

    /**
     * show game picture
     * The method shows picture for WelcomePanel.
     */
    private void showGamePicture()
    {
        ImageIcon welcome = new ImageIcon(WELCOME_PATH);
        jLabel = new JLabel(welcome);
        add(jLabel);
    }
}
