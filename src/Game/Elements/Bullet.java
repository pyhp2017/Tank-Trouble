package Game.Elements;

import Game.Utils.Utility;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This is a bullet in our game
 * @author  Ahmad Foroughi
 * @version 1.0
 */
public class Bullet extends Object implements Serializable
{
    // ---------------------------Fields---------------------------

    //Bullet's Image
    private BufferedImage image;
    //Bullet's Speed
    private int speed;
    //Bullet's Angle
    private double angle;
    //Bullet's Power
    private int power;
    //Start Time
    public long startTime;



    /**
     * Create a new Bullet
     * @param x is x coordination
     * @param y is y coordination
     */
    public Bullet(int x , int y , double angle , int bulletPower , int bulletSpeed)
    {
        super(x , y);
        this.image = Utility.blackBullet;
        this.speed = bulletSpeed;
        this.angle = angle;
        this.power = bulletPower;
        startTime = System.nanoTime();
    }


    /**
     * Moving Bullet
     */
    public void moveUp()
    {
        int x = this.getxCoordination();
        int y = this.getyCoordination();

        if(this.angle>=0 && this.angle<90)
        {
            x = (this.getxCoordination() + (int) Math.abs(speed * Math.cos(Math.toRadians(this.angle))));
            y = (this.getyCoordination() - (int) Math.abs(speed * Math.sin(Math.toRadians(this.angle))));
        }
        else if(this.angle>=90 && this.angle<180)
        {
            x = (this.getxCoordination() - (int) Math.abs(speed * Math.cos(Math.toRadians(this.angle))));
            y = (this.getyCoordination() - (int) Math.abs(speed * Math.sin(Math.toRadians(this.angle))));

        }
        else if(this.angle>=180 && this.angle<270)
        {
            x = (this.getxCoordination() - (int) Math.abs(speed * Math.cos(Math.toRadians(this.angle))));
            y = (this.getyCoordination() + (int) Math.abs(speed * Math.sin(Math.toRadians(this.angle))));

        }
        else if(this.angle>=270 && this.angle<=360)
        {
            x = (this.getxCoordination() + (int) Math.abs(speed * Math.cos(Math.toRadians(this.angle))));
            y = (this.getyCoordination() + (int) Math.abs(speed * Math.sin(Math.toRadians(this.angle))));
        }

            this.setxCoordination(x);
            this.setyCoordination(y);

    }

    /**
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image is a BufferedImage
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * @return angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @return power
     */
    public int getPower() {
        return power;
    }
}
