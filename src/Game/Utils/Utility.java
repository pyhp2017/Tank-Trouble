package Game.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is for images (tool kits) and buffering images
 * all fields and methods are static
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Utility
{
    //Fields

    public static BufferedImage greenTank = loadBufferedImage("res/kit/tankUp/tankUp1.png");
    public static BufferedImage blackBullet = loadBufferedImage("res/bullet.png");
    public static BufferedImage hardWall = loadBufferedImage("res/PNG/Retina/crateMetal.png");
    public static BufferedImage softWall = loadBufferedImage("res/PNG/Retina/crateWood.png");
    public static BufferedImage tileGrass = loadBufferedImage("res/PNG/Default size/tileGrass1.png");
    public static BufferedImage tileSand = loadBufferedImage("res/PNG/Retina/tileSand1.png");
    public static BufferedImage shield = loadBufferedImage("res/kit/tankAbility/tankShield.png");
    public static BufferedImage health = loadBufferedImage("res/kit/tankAbility/health+.png");
    public static BufferedImage lazer = loadBufferedImage("res/kit/tankAbility/tankLazer.png");
    public static BufferedImage power = loadBufferedImage("res/PNG/Retina/sandbagBrown.png");


    //Tanks
    public static BufferedImage tankUp0 = loadBufferedImage("res/kit/tankUp/tankUp0.png");
    public static BufferedImage tankUp1 = loadBufferedImage("res/kit/tankUp/tankUp1.png");
    public static BufferedImage tankUp2 = loadBufferedImage("res/kit/tankUp/tankUp2.png");
    public static BufferedImage tankUp3 = loadBufferedImage("res/kit/tankUp/tankUp3.png");
    public static BufferedImage tankUp4 = loadBufferedImage("res/kit/tankUp/tankUp4.png");
    public static BufferedImage tankUp5 = loadBufferedImage("res/kit/tankUp/tankUp5.png");
    public static BufferedImage tankUp6 = loadBufferedImage("res/kit/tankUp/tankUp6.png");
    public static BufferedImage tankUp7 = loadBufferedImage("res/kit/tankUp/tankUp7.png");
    public static BufferedImage tankUp8 = loadBufferedImage("res/kit/tankUp/tankUp8.png");
    public static BufferedImage tankUp9 = loadBufferedImage("res/kit/tankUp/tankUp9.png");


    public static BufferedImage tankUpShield0 = loadBufferedImage("res/kit/tankUp/tankUp00.png");
    public static BufferedImage tankUpShield1 = loadBufferedImage("res/kit/tankUp/tankUp10.png");
    public static BufferedImage tankUpShield2 = loadBufferedImage("res/kit/tankUp/tankUp20.png");
    public static BufferedImage tankUpShield3 = loadBufferedImage("res/kit/tankUp/tankUp30.png");
    public static BufferedImage tankUpShield4 = loadBufferedImage("res/kit/tankUp/tankUp40.png");
    public static BufferedImage tankUpShield5 = loadBufferedImage("res/kit/tankUp/tankUp50.png");
    public static BufferedImage tankUpShield6 = loadBufferedImage("res/kit/tankUp/tankUp60.png");
    public static BufferedImage tankUpShield7 = loadBufferedImage("res/kit/tankUp/tankUp70.png");
    public static BufferedImage tankUpShield8 = loadBufferedImage("res/kit/tankUp/tankUp80.png");
    public static BufferedImage tankUpShield9 = loadBufferedImage("res/kit/tankUp/tankUp90.png");


    public static BufferedImage tankUpLaser0 = loadBufferedImage("res/kit/tankUp/tankUp01.png");
    public static BufferedImage tankUpLaser1 = loadBufferedImage("res/kit/tankUp/tankUp11.png");
    public static BufferedImage tankUpLaser2 = loadBufferedImage("res/kit/tankUp/tankUp21.png");
    public static BufferedImage tankUpLaser3 = loadBufferedImage("res/kit/tankUp/tankUp31.png");
    public static BufferedImage tankUpLaser4 = loadBufferedImage("res/kit/tankUp/tankUp41.png");
    public static BufferedImage tankUpLaser5 = loadBufferedImage("res/kit/tankUp/tankUp51.png");
    public static BufferedImage tankUpLaser6 = loadBufferedImage("res/kit/tankUp/tankUp61.png");
    public static BufferedImage tankUpLaser7 = loadBufferedImage("res/kit/tankUp/tankUp71.png");
    public static BufferedImage tankUpLaser8 = loadBufferedImage("res/kit/tankUp/tankUp81.png");
    public static BufferedImage tankUpLaser9 = loadBufferedImage("res/kit/tankUp/tankUp91.png");


    //=============== Player Tank ====================
    public static BufferedImage chosenPictureForPlayer = Utility.tankUp0;
    public static BufferedImage chosenPictureForAI = Utility.tankUp9;
    //================================================


    public static String mapAddress1 = "res/Maps/sample-map.txt";
    public static String mapAddress2 = "res/Maps/map1.txt";
    public static String mapAddress3 = "res/Maps/map2.txt";
    public static String mapAddress4 = "res/Maps/map3.txt";
    public static String mapAddress5 = "res/Maps/map4.txt";


    //================================================

    public static File shootSound = new File("res/sounds/LSS.wav");
    public static File upgradeSound = new File("res/sounds/upgrade.wav");
    public static File softWallSound = new File("res/sounds/softWall.wav");
    public static File hardWallSound = new File("res/sounds/bulletHitHardWall.wav");
    public static File explosionSound = new File("res/sounds/explosion.wav");


    /**
     * Load BufferedImage
     * @param path is file path
     * @return BufferedImage
     */
    public static BufferedImage loadBufferedImage(String path)
    {
        BufferedImage temp = null;
        try
        {
            temp = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return temp;
    }
}
