package Game.Utils;


import Game.Elements.Object;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Ability extends Object implements Serializable
{
    //-------------------------------Fields-------------------------------

    //Start Time in ms
    protected long startTime;
    //Image
    protected BufferedImage image;


    /**
     * Create a new Object in the game
     *
     * @param xCoordination is x coordination
     * @param yCoordination is y coordination
     */
    public Ability(int xCoordination, int yCoordination) {
        super(xCoordination, yCoordination);
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Get Start Time
     * @return startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }
}
