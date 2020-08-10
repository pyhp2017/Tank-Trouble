package Game.Elements;

import Engine.GameState;
import Game.Utils.Utility;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;


/**
 * This is an AI
 * For now it just user Random Movement
 * But if you wish to have a real ai, just change Move Method
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class AITank extends Tank implements Serializable
{

    /**
     * Create a New Tank
     */
    public AITank(String tankName, BufferedImage image, int xCoordination, int yCoordination, GameState gameState, int bulletPower , int bulletSpeed) {
        super(tankName, image, xCoordination, yCoordination, gameState, bulletPower , bulletSpeed);
    }

    /**
     * Move TANK (RANDOM)
     */
    public void move()
    {
        Random random = new Random();
        int option = random.nextInt(5);
        switch (option)
        {
            case 0:
                moveUp();
                break;

            case 1:
                moveDown();
                break;

            case 2:
                rotateLeft();
                break;

            case 3:
                rotateRight();
                break;

            case 4:
                shoot();
                break;

        }
    }

    /**
     * Rotate left
     */
    @Override
    public void rotateLeft() {
        this.angle -= rotationSpeed;

        this.gunAngle += rotationGun;
        if (this.gunAngle > 359)
        {
            this.gunAngle -= 360;
        }


        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        this.setImage(create(Utility.chosenPictureForAI,this.angle,gc));

    }

    /**
     * Rotate Right
     */
    @Override
    public void rotateRight() {
        this.angle += rotationSpeed;

        this.gunAngle -= rotationGun;
        if(this.gunAngle<0)
        {
            this.gunAngle += 360;
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        this.setImage(create(Utility.chosenPictureForAI, this.angle,gc));

    }
}
