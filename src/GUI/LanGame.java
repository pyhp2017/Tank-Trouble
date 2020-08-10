package GUI;

/**
 * This is LanGame ShareData
 * @version 1.0
 * @author MS
 */
public class LanGame
{
    //------------------------------FIELDS------------------------------
    private int numberOfRounds;
    private int healthOfTank;
    private int powerOfBullet;
    private int damageOfWall;
    private boolean stopGame;
    private int tankCode;
    private String userName;
    private String gameName;
    private String serverAddress;

    public LanGame(int numberOfRounds, int healthOfTank, int powerOfBullet, int damageOfWall, int tankCode, String userName , String gameName,  String serverAddress) {
        this.numberOfRounds = numberOfRounds;
        this.healthOfTank = healthOfTank;
        this.powerOfBullet = powerOfBullet;
        this.damageOfWall = damageOfWall;
        this.tankCode = tankCode;
        this.serverAddress = serverAddress;
        this.userName = userName;
        this.gameName = gameName;
        this.stopGame = false;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public int getHealthOfTank() {
        return healthOfTank;
    }

    public void setHealthOfTank(int healthOfTank) {
        this.healthOfTank = healthOfTank;
    }

    public int getPowerOfBullet() {
        return powerOfBullet;
    }

    public void setPowerOfBullet(int powerOfBullet) {
        this.powerOfBullet = powerOfBullet;
    }

    public int getDamageOfWall() {
        return damageOfWall;
    }

    public void setDamageOfWall(int damageOfWall) {
        this.damageOfWall = damageOfWall;
    }

    public boolean isStopGame() {
        return stopGame;
    }

    public void setStopGame(boolean stopGame) {
        this.stopGame = stopGame;
    }

    public int getTankCode() {
        return tankCode;
    }

    public void setTankCode(int tankCode) {
        this.tankCode = tankCode;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getUserName() {
        return userName;
    }
}
