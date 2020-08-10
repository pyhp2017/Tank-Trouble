package Game.Elements;

import Game.Utils.Ability;
import Game.Utils.Utility;

import java.io.Serializable;


/**
 * this is an ability for extra health
 * @author ahmad foroughi
 * @version 1.0
 */
public class ExtraHealth extends Ability {
    /**
     * Create a new Object in the game
     *
     * @param xCoordination is x coordination
     * @param yCoordination is y coordination
     */
    public ExtraHealth(int xCoordination, int yCoordination) {
        super(xCoordination, yCoordination);
        image = Utility.health;
    }
}
