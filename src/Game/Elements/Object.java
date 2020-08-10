package Game.Elements;

import java.io.Serializable;

/**
 * This is an Object in Game
 * @author Ahmad
 * @version 1.0
 */
public class Object implements Serializable
{
    //Fields
    private int xCoordination;
    private int yCoordination;

    /**
     * Create a new Object in the game
     * @param xCoordination is x coordination
     * @param yCoordination is y coordination
     */
    public Object(int xCoordination,int yCoordination)
    {
        this.xCoordination = xCoordination;
        this.yCoordination = yCoordination;
    }


    /**
     * @param xCoordination is Object x Coordination
     */
    public void setxCoordination(int xCoordination) {
        this.xCoordination = xCoordination;
    }

    /**
     * @param yCoordination is Object y Coordination
     */
    public void setyCoordination(int yCoordination) {
        this.yCoordination = yCoordination;
    }

    /**
     * @return xCoordination
     */
    public int getxCoordination() {
        return xCoordination;
    }

    /**
     * @return yCoordination
     */
    public int getyCoordination() {
        return yCoordination;
    }
}
