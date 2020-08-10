package Game.Map;

import Engine.GameState;
import Game.Elements.Object;
import Game.Utils.Utility;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This is a Soft Wall
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class SoftWall extends Object implements Serializable {

    //Fields
    private int health;
    //Wall's Image
    private BufferedImage image;


    /**
     * Create a new Object in the game
     *
     * @param xCoordination is x coordination
     * @param yCoordination is y coordination
     */
    public SoftWall(int xCoordination, int yCoordination) {
        super(xCoordination, yCoordination);
        this.image = Utility.softWall;
        health = GameState.wallHealth;
    }


    /**
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health is Wall Health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }
}
