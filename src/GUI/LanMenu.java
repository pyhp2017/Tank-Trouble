package GUI;

import javax.swing.*;

/**
 * Lan Menu (JPanel)
 * @author MS
 * @version 1.0
 */
public class LanMenu extends JPanel
{
    //--------------------------------Fields-----------------------------------
    private JFrame frame;
    private User loggedUser;
    private static LanMenu INSTANCE = null;

    /**
     * Create a new LanMenu
     */
    private LanMenu(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Get Instance
     * @param frame is a JFrame
     * @return is LanMenu Instance
     */
    public static LanMenu getInstance(JFrame frame) {
        if (INSTANCE == null)
            INSTANCE = new LanMenu(frame);
        return INSTANCE;
    }

    /**
     * Set User
     * @param loggedUser is logged user
     */
    public void setUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
