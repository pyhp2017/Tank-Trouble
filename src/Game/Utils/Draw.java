package Game.Utils;

import Engine.GameFrame;
import Engine.GameState;
import Game.Elements.Bullet;
import Game.Map.HardWall;
import Game.Map.SoftWall;
import java.awt.*;


/**
 * Draw Elements in the Game Frame
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Draw
{
    //Message to Draw
    public static String message;

    /**
     * Draw Tank (Both PC and Player)
     * @param graphics2D is g2d
     * @param gameState is gameState
     */
    public static void DrawTank(Graphics2D graphics2D , GameState gameState)
    {
        //Draw Tank
        graphics2D.drawImage(gameState.tank.getImage(),gameState.tank.getxCoordination(),gameState.tank.getyCoordination(), (int) (gameState.map.getEachWallWidth()/1.1),(int)(gameState.map.getEachWallHeight()/1.1),null);
        graphics2D.drawImage(gameState.aiTank.getImage(),gameState.aiTank.getxCoordination(),gameState.aiTank.getyCoordination(), (int) (gameState.map.getEachWallWidth()/1.1),(int)(gameState.map.getEachWallHeight()/1.1),null );
    }

    /**
     * Draw Bullets for both PC and Player
     * @param graphics2D is g2d
     * @param gameState is gameState
     */
    public static void DrawBullets(Graphics2D graphics2D , GameState gameState)
    {
        //Draw Bullets
        for(Bullet bullet: gameState.tank.getBullets())
        {
                graphics2D.drawImage(bullet.getImage(),bullet.getxCoordination(),bullet.getyCoordination(),(int) (gameState.map.getEachWallWidth() /5),(int)(gameState.map.getEachWallHeight() / 5) ,null);
        }

        for(Bullet bullet : gameState.aiTank.getBullets())
        {
            graphics2D.drawImage(bullet.getImage(),bullet.getxCoordination(),bullet.getyCoordination(),(int) (gameState.map.getEachWallWidth() /5),(int)(gameState.map.getEachWallHeight() / 5) ,null);
        }

    }

    /**
     * Draw Walls (HardWalls and SoftWalls)
     * @param graphics2D is g2d
     * @param gameState is gameState
     */
    public static void DrawWalls(Graphics2D graphics2D , GameState gameState)
    {
        //Draw SoftWalls
        for(SoftWall softWall: gameState.map.getSoftWalls())
        {
            graphics2D.drawImage(softWall.getImage(),softWall.getxCoordination(),softWall.getyCoordination(),gameState.map.getEachWallWidth(),gameState.map.getEachWallHeight(),null);
        }
        //Draw HardWalls
        for(HardWall hardWall: gameState.map.getHardWalls())
        {
            graphics2D.drawImage(hardWall.getImage(),hardWall.getxCoordination(),hardWall.getyCoordination(),gameState.map.getEachWallWidth(),gameState.map.getEachWallHeight(),null);

        }

    }

    /**
     * Draw Text
     * @param graphics2D is g2d
     * @param gameState is gameState
     */
    public static void DrawEnd(Graphics2D graphics2D , GameState gameState)
    {
            long delay = (long) (5*Math.pow(10,9));
            if(System.nanoTime() - gameState.startEndTime < delay)
            {
                String str =  message.toUpperCase();
                graphics2D.setColor(Color.WHITE);
                graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
                int strWidth = graphics2D.getFontMetrics().stringWidth(str);
                graphics2D.drawString(str, (GameFrame.GAME_WIDTH - strWidth) / 2, GameFrame.GAME_HEIGHT / 2);
            }
    }

    /**
     * Draw Abilities on the screen
     * @param graphics2D is g2d
     * @param gameState is gameState
     */
    public static void DrawAbilities(Graphics2D graphics2D , GameState gameState)
    {
        for(Ability ability: gameState.abilities)
        {
            graphics2D.drawImage(ability.getImage(),ability.getxCoordination(),ability.getyCoordination(), (int) (gameState.map.getEachWallWidth()/2),(int)(gameState.map.getEachWallHeight()/2),null);
        }
    }

}

