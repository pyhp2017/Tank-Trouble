package Game.Elements;

import Game.Utils.Ability;
import Game.Utils.Utility;

import java.awt.image.BufferedImage;

/**
 * this is an ability for tank(Shield)
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Guard extends Ability
{

    /**
     * Create a new Object in the game
     *
     * @param xCoordination is x coordination
     * @param yCoordination is y coordination
     */
    public Guard(int xCoordination, int yCoordination) {
        super(xCoordination, yCoordination);
        image = Utility.shield;
    }

    public BufferedImage getImage() {
        return image;
    }

}
