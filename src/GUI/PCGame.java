package GUI;


/**
 * This is a Shared Data Class
 * @author MS
 * @version 2.0
 */
public class PCGame
{
    //--------------------------- Fields ------------------------------
    private String userName;
    private int numberOfRounds;
    private int healthOfTank;
    private int powerOfBullet;
    private int damageOfWall;
    private boolean stopGame;
    private int tankCode;
    private int aiTankHealth;

    //NOT FOR CHANGING
    public int tankHealth;


    /**
     * Create a new PC game
     */
    public PCGame(int numberOfRounds, int healthOfTank, int powerOfBullet, int damageOfWall , int tankCode, String userName) {
        this.numberOfRounds = numberOfRounds;
        this.healthOfTank = healthOfTank;
        this.powerOfBullet = powerOfBullet;
        this.damageOfWall = damageOfWall;
        this.stopGame = false;
        this.tankCode = tankCode;
        this.aiTankHealth = healthOfTank;
        this.userName = userName;

        //Static Value
        tankHealth = healthOfTank;

    }

    /**
     * @return numberOfRounds
     */
    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    /**
     * @return healthOfTank
     */
    public int getHealthOfTank() {
        return healthOfTank;
    }

    /**
     * @return powerOfBullet
     */
    public int getPowerOfBullet() {
        return powerOfBullet;
    }

    /**
     * @return damageOfWall
     */
    public int getDamageOfWall() {
        return damageOfWall;
    }

    /**
     * @param numberOfRounds is Number of Rounds
     */
    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    /**
     * @param healthOfTank is healthOfTank
     */
    public void setHealthOfTank(int healthOfTank) {
        this.healthOfTank = healthOfTank;
    }

    /**
     * @param powerOfBullet is power Of Bullet
     */
    public void setPowerOfBullet(int powerOfBullet) {
        this.powerOfBullet = powerOfBullet;
    }

    /**
     * @return stopGame
     */
    public boolean isStopGame() {
        return stopGame;
    }

    /**
     * @param stopGame is a boolean
     */
    public void setStopGame(boolean stopGame) {
        this.stopGame = stopGame;
    }

    /**
     * @return tankCode
     */
    public int getTankCode() {
        return tankCode;
    }

    /**
     * @param tankCode is tankCode;
     */
    public void setTankCode(int tankCode) {
        this.tankCode = tankCode;
    }

    /**
     * @return aiTankHealth
     */
    public int getAiTankHealth() {
        return aiTankHealth;
    }

    /**
     * @param aiTankHealth set ai health
     */
    public void setAiTankHealth(int aiTankHealth) {
        this.aiTankHealth = aiTankHealth;
    }

    /**
     * @return get UserName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName is userName (String)
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "PCGame{" +
                "numberOfRounds=" + numberOfRounds +
                ", healthOfTank=" + healthOfTank +
                ", powerOfBullet=" + powerOfBullet +
                ", damageOfWall=" + damageOfWall +
                ", stopGame=" + stopGame +
                ", tankCode=" + tankCode +
                ", aiTankHealth=" + aiTankHealth +
                '}';
    }
}
