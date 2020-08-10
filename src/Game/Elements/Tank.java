package Game.Elements;

import Engine.GameFrame;
import Engine.GameState;
import Game.Map.HardWall;
import Game.Map.SoftWall;
import Game.Utils.Ability;
import Game.Utils.Sound;
import Game.Utils.Utility;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a Tank in our game (This is an Object)
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Tank extends Object implements Serializable
{
    //Fields

    //Tank's Name
    protected String tankName;
    //Tank's health value
    protected int health;
    //Tank's Bullets value
    protected int numberOfBullets;
    //Tank's Angle (Gun Angle)
    protected double angle;
    //Tank's Image
    protected BufferedImage image;
    //Movement Speed
    protected final int speed;
    // the speed at which each tank can turn (If We Change This rotationGun Must be change too)
    protected final double rotationSpeed;
    //Gun Angle
    protected double gunAngle;
    //Rotate Of GUN
    protected final double rotationGun;
    //Bullets List
    protected ArrayList<Bullet> bullets;
    //Shoot delay
    protected static final int delay = 3;
    //Game State
    protected GameState gameState;
    //Shield
    protected boolean hasShield;
    //Ability
    public long abilityTime;
    //Ability
    public Ability currentAbility;
    //Bullet's Power
    protected int bulletPower;
    //Bullet's Speed
    protected int bulletSpeed;
    //Laser
    protected boolean hasLaser;


    /**
     * Create a New Tank
     */
    public Tank(String tankName , BufferedImage image, int xCoordination, int yCoordination , GameState gameState , int bulletPower , int bulletSpeed)
    {
        super(xCoordination,yCoordination);
        this.rotationSpeed = 0.02;
        this.rotationGun = 90/((79*rotationSpeed)/0.02);
        this.image = image;
        this.tankName = tankName;
        this.health = 100;
        this.numberOfBullets = 1000;
        this.angle = 0;
        this.speed = 2;
        this.gunAngle = 90;
        this.bullets = new ArrayList<>();
        this.gameState = gameState;
        this.hasShield = false;
        this.currentAbility = null;
        this.bulletPower = bulletPower;
        this.bulletSpeed = bulletSpeed;
        this.hasLaser = false;

    }


    /**
     * Move Tank UP with current angle
     */
    public void moveUp()
    {

        double x =  this.getxCoordination();
        double y =  this.getyCoordination();


        if(this.gunAngle>=0 && this.gunAngle<=90)
        {
            x = this.getxCoordination() + Math.abs(speed * Math.cos(Math.toRadians(this.gunAngle)));
            y = this.getyCoordination() - Math.abs(speed * Math.sin(Math.toRadians(this.gunAngle)));

        }
        else if(this.gunAngle>90 && this.gunAngle<=180)
        {
            x = this.getxCoordination() - Math.abs(speed * Math.cos(Math.toRadians(this.gunAngle)));
            y = this.getyCoordination() - Math.abs(speed * Math.sin(Math.toRadians(this.gunAngle)));
        }
        else if(this.gunAngle>180 && this.gunAngle<=270)
        {
            x = this.getxCoordination() - Math.abs(speed * Math.cos(Math.toRadians(this.gunAngle)));
            y = this.getyCoordination() + Math.abs(speed * Math.sin(Math.toRadians(this.gunAngle)));
        }
        else if(this.gunAngle>270 && this.gunAngle<=360)
        {
            x = this.getxCoordination() + Math.abs(speed * Math.cos(Math.toRadians(this.gunAngle)));
            y = this.getyCoordination() + Math.abs(speed * Math.sin(Math.toRadians(this.gunAngle)));
        }


        boolean move = checkMove(x,y);


        if( x>10  && y>10 && x< GameFrame.GAME_WIDTH-10 && y< GameFrame.GAME_HEIGHT-10)
        {
            if(move)
            {
                this.setxCoordination((int)x);
                this.setyCoordination((int)y);
            }
        }

    }

    /**
     * Move Tank Down with current angle
     */
    public void moveDown()
    {

        double x = this.getxCoordination();
        double y = this.getyCoordination();

        if(this.gunAngle>=0 && this.gunAngle<=90)
        {
            x = this.getxCoordination() - Math.abs(speed * Math.cos(Math.toRadians(this.gunAngle)));
            y = this.getyCoordination() + Math.abs(speed * Math.sin(Math.toRadians(this.gunAngle)));
        }
        else if(this.gunAngle>90 && this.gunAngle<=180)
        {
            x = this.getxCoordination() + Math.abs(speed * Math.cos(Math.toRadians(this.gunAngle)));
            y = this.getyCoordination() + Math.abs(speed * Math.sin(Math.toRadians(this.gunAngle)));
        }
        else if(this.gunAngle>180 && this.gunAngle<=270)
        {
            x = this.getxCoordination() + Math.abs(speed * Math.cos(Math.toRadians(this.gunAngle)));
            y = this.getyCoordination() - Math.abs(speed * Math.sin(Math.toRadians(this.gunAngle)));
        }
        else if(this.gunAngle>270 && this.gunAngle<=360)
        {
            x = this.getxCoordination() - Math.abs(speed * Math.cos(Math.toRadians(this.gunAngle)));
            y = this.getyCoordination() - Math.abs(speed * Math.sin(Math.toRadians(this.gunAngle)));
        }


        boolean move = checkMove(x,y);

        if( x>10  && y>10 && x< GameFrame.GAME_WIDTH-10 && y< GameFrame.GAME_HEIGHT-10)
        {
            if(move)
            {
                this.setxCoordination((int)x);
                this.setyCoordination((int)y);
            }
        }


    }

    /**
     * Rotate left
     */
    public void rotateLeft()
    {
        this.angle -= rotationSpeed;

        this.gunAngle += rotationGun;
        if (this.gunAngle > 359)
        {
            this.gunAngle -= 360;
        }


        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        this.setImage(create(Utility.chosenPictureForPlayer,this.angle,gc));


    }

    /**
     * Rotate Right
     */
    public void rotateRight()
    {

        this.angle += rotationSpeed;

        this.gunAngle -= rotationGun;
        if(this.gunAngle<0)
        {
            this.gunAngle += 360;
        }




        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        this.setImage(create(Utility.chosenPictureForPlayer, this.angle,gc));

    }


    /**
     * Rotate BufferedImage
     * @param image is a BufferedImage
     * @param angle is an angle
     * @param gc is GraphicsConfiguration
     * @return BufferedImage
     */
    public static BufferedImage create(BufferedImage image, double angle,
                                       GraphicsConfiguration gc) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
                * cos + w * sin);
        int transparency = image.getColorModel().getTransparency();
        BufferedImage result = gc.createCompatibleImage(neww, newh, transparency);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        return result;
    }


    /**
     * Shooting Method
     */
    public void shoot()
    {
        if(this.getNumberOfBullets()>0)
        {
            //Check last shoot's time
            if(bullets.size()==0)
            {
                createBullet();
            }
            else
            {
                long endTime = System.nanoTime();
                if((endTime - bullets.get(bullets.size()-1).startTime) / 1000000000 > delay)
                {
                    createBullet();
                }
            }
        }
    }

    /**
     * Creating a new bullet
     */
    public void createBullet()
    {
        Bullet bullet = new Bullet((int)(this.getxCoordination()+24),(int)(this.getyCoordination()+24),this.gunAngle , bulletPower,bulletSpeed);
        this.bullets.add(bullet);
        this.numberOfBullets--;
    }


    /**
     * @param health is health value
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return health field
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return tank's name
     */
    public String getTankName() {
        return tankName;
    }

    /**
     * @return tank's angle ( 0 to 360 )
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @return numberOfBullets
     */
    public int getNumberOfBullets() {
        return numberOfBullets;
    }

    /**
     * @param tankName is tankName
     */
    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    /**
     * @return image(Tank's image)
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image is Tank's image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * @param numberOfBullets is number of bullets
     */
    public void setNumberOfBullets(int numberOfBullets) {
        this.numberOfBullets = numberOfBullets;
    }

    /**
     * @param angle is tank angle (0 to 360)
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * @return gunAngle
     */
    public double getGunAngle() {
        return gunAngle;
    }

    /**
     * @return bullets list
     */
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    /**
     * @param bullets is bullets list
     */
    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    /**
     * Check Move (Crash to a Wall)
     * @param x is where you want to go
     * @param y is where you want to go
     * @return true or false
     */
    public boolean checkMove(double x , double y)
    {
        boolean move = true;
        for(HardWall hardWall: gameState.map.getHardWalls())
        {
            int xInt = (int)x;
            int yInt = (int)y;
            if((xInt>=hardWall.getxCoordination()-gameState.map.getEachWallWidth()+12 && xInt<=hardWall.getxCoordination()+gameState.map.getEachWallWidth()-12) && (yInt>=hardWall.getyCoordination()-gameState.map.getEachWallHeight()+12 && yInt<=hardWall.getyCoordination()+gameState.map.getEachWallHeight()-12))
            {
                move = false;
            }
        }
        for(SoftWall softWall: gameState.map.getSoftWalls())
        {
            int xInt = (int)x;
            int yInt = (int)y;
            if((xInt>=softWall.getxCoordination()-gameState.map.getEachWallWidth()+12 && xInt<=softWall.getxCoordination()+gameState.map.getEachWallWidth()-12) && (yInt>=softWall.getyCoordination()-gameState.map.getEachWallHeight()+12 && yInt<=softWall.getyCoordination()+gameState.map.getEachWallHeight()-12))
            {
                move = false;
            }

        }


        return move;
    }


    public boolean hasShield() {
        return hasShield;
    }

    public void setShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    public long getAbilityTime() {
        return abilityTime;
    }

    public void setAbilityTime(long abilityTime) {
        this.abilityTime = abilityTime;
    }

    public int getBulletPower() {
        return bulletPower;
    }

    public void setBulletPower(int bulletPower) {
        this.bulletPower = bulletPower;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public boolean hasLaser() {
        return hasLaser;
    }

    public void setLaser(boolean hasLaser) {
        this.hasLaser = hasLaser;
    }
}
