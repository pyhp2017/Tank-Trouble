package GUI;

import java.io.Serializable;

/**
 * A class to hold user information.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class User implements Serializable
{
    //username
    private String username;
    //password
    private String password;
    //shapeCode
    private int shapeCode;
    //time of playing
    private long timeOfPlaying;
    //number of win with PC
    private int numberOfWinWithPC;
    //number of lost with PC
    private int numberOfLostWithPC;
    //number of win with lan
    private int numberOfWinWithLan;
    //number of lost with lan
    private int numberOfLostWithLan;
    //default health of tank
    private int defaultHealthOfTank;
    //default power of bullet
    private int defaultPowerOfBullet;
    //default damage of wall
    private int defaultDamageOfWall;
    //condition of remember me
    private boolean rememberMe;


    /**
     * Create a new User.
     *
     * @param username username
     * @param password password
     * @param shapeCode shape code
     */
    public User(String username, String password, int shapeCode) {
        this.username = username;
        this.password = password;
        this.shapeCode = shapeCode;
        timeOfPlaying = 0;
        numberOfWinWithPC = 0;
        numberOfLostWithPC = 0;
        numberOfWinWithLan = 0;
        numberOfLostWithLan = 0;
        defaultHealthOfTank = 50;
        defaultPowerOfBullet = 20;
        defaultDamageOfWall = 20;
        rememberMe = false;
    }

    /**
     * get username.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * get password.
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * get shape code.
     *
     * @return shapeCode
     */
    public int getShapeCode() {
        return shapeCode;
    }

    /**
     * get time of playing.
     *
     * @return timeOfPlaying
     */
    public long getTimeOfPlaying() {
        return timeOfPlaying;
    }

    /**
     * get number of win with PC.
     *
     * @return numberOfWinWithPC
     */
    public int getNumberOfWinWithPC() {
        return numberOfWinWithPC;
    }

    /**
     * get number of lost with PC.
     *
     * @return numberOfLostWithPC
     */
    public int getNumberOfLostWithPC() {
        return numberOfLostWithPC;
    }

    /**
     * get number of win with Lan.
     *
     * @return numberOfWinWithLan
     */
    public int getNumberOfWinWithLan() {
        return numberOfWinWithLan;
    }

    /**
     * get number of lost with Lan.
     *
     * @return numberOfLostWithLan
     */
    public int getNumberOfLostWithLan() {
        return numberOfLostWithLan;
    }

    /**
     * get default health of tank.
     *
     * @return defaultHealthOfTank
     */
    public int getDefaultHealthOfTank() {
        return defaultHealthOfTank;
    }

    /**
     * get default power of bullet.
     *
     * @return defaultPowerOfBullet
     */
    public int getDefaultPowerOfBullet() {
        return defaultPowerOfBullet;
    }

    /**
     * get default damage of wall.
     *
     * @return defaultDamageOfWall
     */
    public int getDefaultDamageOfWall() {
        return defaultDamageOfWall;
    }

    /**
     * get condition of remember me.
     *
     * @return rememberMe
     */
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * set shape code
     *
     * @param shapeCode shapeCode
     */
    public void setShapeCode(int shapeCode) {
        this.shapeCode = shapeCode;
    }

    /**
     * add time of playing.
     *
     * The method adds time of playing to User field.
     * @param timeOfPlaying timeOfPlaying
     */
    public void addTimeOfPlaying(long timeOfPlaying) {
        this.timeOfPlaying += timeOfPlaying;
    }

    /**
     * add number of win with PC.
     *
     * The method adds one to User field.
     */

    public void addWinWithPC() {
        this.numberOfWinWithPC ++;
    }

    /**
     * add number of lost with PC.
     *
     * The method adds one to User field.
     */
    public void addLostWithPC() {
        this.numberOfLostWithPC ++;
    }

    /**
     * add number of win with Lan.
     *
     * The method adds one to User field.
     */
    public void addWinWithLan() {
        this.numberOfWinWithLan ++;
    }


    /**
     * add number of lost with Lan.
     *
     * The method adds one to User field.
     */
    public void addLostWithLan() {
        this.numberOfLostWithLan ++;
    }

    /**
     * set default health of tank
     *
     * @param defaultHealthOfTank defaultHealthOfTank
     */
    public void setDefaultHealthOfTank(int defaultHealthOfTank) {
        this.defaultHealthOfTank = defaultHealthOfTank;
    }

    /**
     * set default power of bullet
     *
     * @param defaultPowerOfBullet defaultPowerOfBullet
     */
    public void setDefaultPowerOfBullet(int defaultPowerOfBullet) {
        this.defaultPowerOfBullet = defaultPowerOfBullet;
    }

    /**
     * set default damage of wall
     *
     * @param defaultDamageOfWall defaultDamageOfWall
     */
    public void setDefaultDamageOfWall(int defaultDamageOfWall) {
        this.defaultDamageOfWall = defaultDamageOfWall;
    }

    /**
     * set remember me condition
     *
     * @param rememberMe condition of remember me.
     */
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
