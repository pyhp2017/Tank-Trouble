package Game.Elements;

import Game.Utils.Ability;
import Game.Utils.Utility;


/**
 * This is an Ability for extra Power
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class ExtraPower extends Ability
{

    /**
     * Create a new Object in the game
     *
     * @param xCoordination is x coordination
     * @param yCoordination is y coordination
     */
    public ExtraPower(int xCoordination, int yCoordination) {
        super(xCoordination, yCoordination);
        image = Utility.power;
    }
}
