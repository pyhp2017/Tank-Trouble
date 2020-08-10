package Game.Map;

import Game.Elements.Object;
import Game.Utils.Utility;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This is a hard wall
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class HardWall extends Object implements Serializable {


    //----------------------------------Fields----------------------------------

    //Wall's Image
    private BufferedImage image;


    /**
     * Create a new Object in the game
     *
     * @param xCoordination is x coordination
     * @param yCoordination is y coordination
     */
    public HardWall(int xCoordination, int yCoordination) {
        super(xCoordination, yCoordination);
        this.image = Utility.hardWall;
    }


    /**
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }
}
