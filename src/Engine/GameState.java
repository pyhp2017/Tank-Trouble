package Engine;

import GUI.PCGamePanel;
import GUI.PCMenu;
import Game.Elements.*;
import Game.Map.Map;
import Game.Utils.Ability;
import Game.Utils.Draw;
import Game.Utils.Sound;
import Game.Utils.Utility;
import jdk.jshell.execution.Util;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is State of Game
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class GameState
{

//                  ---------------------------Fields---------------------------

	//Game OVER CHECK
	public boolean gameOver;
	//Number of Rounds
	private int numberOfRounds;
	//keys
	private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT, keySpace;
	//Soft Wall Health
	public static int wallHealth;
	//Delay for Show  winner's name (Winner of round)
	public long startEndTime;
	//Player Wins
	private int playerWins;
	//Computer Wins
	private int aiWins;
	//Check End of Round
	private boolean endGame;
	//Winner of round's name
	private String winner;
	//backup bullet's speed
	private final int backupSpeed;
	//backup bullet's power
	private final int backupPower;
	//List of abilities
	public ArrayList<Ability> abilities;
	//Player Tank
	public Tank tank;
	//PC Tank
	public AITank aiTank;
	//Keyboard Handler
	private KeyHandler keyHandler;
	//ArrayList of map Address
	private ArrayList<String> mapsAddresses;
	//Map
	public Map map;


	/**
	 * Create a new GameState
	 */
	public GameState()
	{
		//========================================================= INIT NUMBERS
		gameOver = false;
		endGame = false;
		numberOfRounds = PCMenu.pcGame.getNumberOfRounds();
		playerWins = 0;
		aiWins = 0;
		Random rand = new Random();
		backupSpeed = 8;
		backupPower = PCMenu.pcGame.getPowerOfBullet();
		//========================================================= KEYBOARD STUFFS
		keyUP = false;
		keyDOWN = false;
		keyRIGHT = false;
		keyLEFT = false;
		keySpace = false;
		//========================================================= INIT handler
		keyHandler = new KeyHandler();
		//========================================================= INIT MAP
		wallHealth = PCMenu.pcGame.getDamageOfWall();
		mapsAddresses = new ArrayList<>();
		mapsAddresses.add(Utility.mapAddress1);
		mapsAddresses.add(Utility.mapAddress2);
		mapsAddresses.add(Utility.mapAddress3);
		mapsAddresses.add(Utility.mapAddress4);
		mapsAddresses.add(Utility.mapAddress5);
		int bound = mapsAddresses.size();
		int indexOfRandMap = rand.nextInt(bound);
		//Change This if you want random map
		map = new Map(mapsAddresses.get(0));
		//========================================================= INIT Random
		int upperBound = map.getProperXLocations().size();
		int randomLocation = rand.nextInt(upperBound);
		//========================================================= INIT TANK PICTURE
		switch (PCMenu.pcGame.getTankCode())
		{
			case 0:
				Utility.chosenPictureForPlayer = Utility.tankUp0;
				break;

			case 1:
				Utility.chosenPictureForPlayer = Utility.tankUp1;
				break;

			case 2:
				Utility.chosenPictureForPlayer = Utility.tankUp2;
				break;

			case 3:
				Utility.chosenPictureForPlayer = Utility.tankUp3;
				break;

			case 4:
				Utility.chosenPictureForPlayer = Utility.tankUp4;
				break;
			case 5:
				Utility.chosenPictureForPlayer = Utility.tankUp5;
				break;

			case 6:
				Utility.chosenPictureForPlayer = Utility.tankUp6;
				break;

			case 7:
				Utility.chosenPictureForPlayer = Utility.tankUp7;
				break;

			case 8:
				Utility.chosenPictureForPlayer = Utility.tankUp8;
				break;

		}
		//========================================================= INIT TANK (PLAYER)
		//PLAYER TANK
		tank = new Tank(PCMenu.pcGame.getUserName(), Utility.chosenPictureForPlayer , map.getProperXLocations().get(randomLocation) , map.getProperYLocations().get(randomLocation) , this, backupPower , backupSpeed );
		//========================================================= INIT AI
		int randAI;
		while(true)
		{
			randAI = rand.nextInt(upperBound);
			if(randAI != randomLocation)
			{
				break;
			}
		}
		aiTank = new AITank("AiTank" , Utility.chosenPictureForAI , map.getProperXLocations().get(randAI) , map.getProperYLocations().get(randAI) , this , backupPower , backupSpeed);
		//========================================================= Set Settings
		tank.setHealth(PCMenu.pcGame.getHealthOfTank());
		aiTank.setHealth(PCMenu.pcGame.getAiTankHealth());
		//========================================================= INIT ABILITIES LIST
		abilities = new ArrayList<>();
		addAbility(); //First Ability
	}

	/**
	 * Update State
	 */
	public void update()
	{

		//Move TANK
		userKeyBoard();
		//Move Ai Tank (Random)
		aiTank.move();

		//Moving bullets
		moveBullets(tank);
		moveBullets(aiTank);

		//Remove
		doRemove(tank , aiTank);
		doRemove(aiTank , tank);

		//Check Time for ability
		backToNormalAbility(tank,false);
		backToNormalAbility(aiTank,false);

		//Remove For abilities
		removeAbilities(tank);
		removeAbilities(aiTank);

		//New Round
		if(endGame)
		{
			//Update
			updateHealth(tank);
			updateHealth(aiTank);
			if(numberOfRounds>1)
			{
				//Reset ability
				backToNormalAbility(tank,true);
				backToNormalAbility(aiTank,true);
				//Reset Map
				map.resetMap();
				//Remove all Abilities
				abilities.clear();
				addAbility();
				//Reset Locations
				resetLocation();
				//Reset Health
				resetHealth();
				//Reduce number of rounds
				numberOfRounds--;
				PCGamePanel.roundCounter += 1;
				PCGamePanel.roundCounterLabel.setText("  Round: " + PCGamePanel.roundCounter + "  ");
				endGame = false;
				Draw.message = winner;
			}
			else
			{
				if(playerWins>aiWins)
				{
					winner = tank.getTankName();
					PCGamePanel.labelEND.setText(tank.getTankName().toUpperCase() + " WON");
					PCGamePanel.loggedUser.addWinWithPC();
				}
				else if(playerWins < aiWins)
				{
					winner = aiTank.getTankName();
					PCGamePanel.labelEND.setText(aiTank.getTankName().toUpperCase() + " WON");
					PCGamePanel.loggedUser.addLostWithPC();
				}
				else
				{
					PCGamePanel.labelEND.setText("BOTH " + " WON");
					PCGamePanel.loggedUser.addWinWithPC();
				}
				Draw.message = "GAME OVER - " + winner + " WON";
				PCMenu.pcGame.setStopGame(true);
			}
		}


		//Create new Ability
		if(abilities.size()==0)
		{
			addAbility();
		}
		else
		{
			if(System.currentTimeMillis() - abilities.get(abilities.size()-1).getStartTime() > 30000)
			{
				addAbility();
			}
		}

	}

	/**
	 * @return KeyListener
	 */
	public KeyListener getKeyListener() {
		return keyHandler;
	}

	/**
	 * KeyHandler Class
	 */
	class KeyHandler extends KeyAdapter
	{

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = true;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = true;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = true;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = true;
					break;
				case KeyEvent.VK_SPACE:
					keySpace = true;
					break;

				case KeyEvent.VK_ESCAPE:
					gameOver = true;
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = false;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = false;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = false;
					break;
				case KeyEvent.VK_SPACE:
					keySpace = false;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = false;
					break;
			}
		}

	}

	/**
	 * Remove bullets and etc
	 * @param tank is you
	 * @param anOtherTank is your enemy
	 */
	public void doRemove(Tank tank , Tank anOtherTank)
	{
		int indexToRemoveBullet = -1;
		int indexToRemoveSoftWall = -1;


		for(int i=0 ; i<tank.getBullets().size(); i++)
		{
			//Remove Bullet if it was out of the range
			if(tank.getBullets().get(i).getxCoordination()<0 || tank.getBullets().get(i).getyCoordination()<0 || tank.getBullets().get(i).getxCoordination()>GameFrame.GAME_WIDTH || tank.getBullets().get(i).getyCoordination()>GameFrame.GAME_HEIGHT)
			{
				indexToRemoveBullet = i;
			}
			//Remove Bullet and SoftWall
			for(int j=0 ; j<map.getSoftWalls().size();j++)
			{
				if((tank.getBullets().get(i).getxCoordination()> map.getSoftWalls().get(j).getxCoordination()) && (tank.getBullets().get(i).getxCoordination()<map.getEachWallWidth()+map.getSoftWalls().get(j).getxCoordination()) && (tank.getBullets().get(i).getyCoordination() > map.getSoftWalls().get(j).getyCoordination()) && (tank.getBullets().get(i).getyCoordination()< map.getEachWallHeight()+map.getSoftWalls().get(j).getyCoordination()))
				{
					//Check WallHealth
					if(map.getSoftWalls().get(j).getHealth() - tank.getBullets().get(i).getPower()<=0)
					{
						indexToRemoveSoftWall = j;
						if(!tank.getTankName().equals("AiTank"))
						{
							Sound sound = new Sound(Utility.softWallSound);
							sound.playSound();
						}
					}
					else
					{
						map.getSoftWalls().get(j).setHealth(map.getSoftWalls().get(j).getHealth()-tank.getBullets().get(i).getPower());
						if(!tank.getTankName().equals("AiTank"))
						{
							Sound sound = new Sound(Utility.hardWallSound);
							sound.playSound();
						}
					}
					indexToRemoveBullet = i;
				}
			}

			//Remove Bullet if crashed to a HardWall
			for(int j=0; j<map.getHardWalls().size();j++)
			{
				if((tank.getBullets().get(i).getxCoordination()> map.getHardWalls().get(j).getxCoordination()) && (tank.getBullets().get(i).getxCoordination()<map.getEachWallWidth()+map.getHardWalls().get(j).getxCoordination()) && (tank.getBullets().get(i).getyCoordination() > map.getHardWalls().get(j).getyCoordination()) && (tank.getBullets().get(i).getyCoordination()< map.getEachWallHeight()+map.getHardWalls().get(j).getyCoordination()))
				{
					indexToRemoveBullet = i;

					if(!tank.getTankName().equals("AiTank"))
					{
						Sound sound = new Sound(Utility.hardWallSound);
						sound.playSound();
					}
				}
			}


//			Remove Bullet if crashed to a tank
			if((tank.getBullets().get(i).getxCoordination()> anOtherTank.getxCoordination()) && (tank.getBullets().get(i).getxCoordination()< anOtherTank.getxCoordination()+map.getEachWallWidth()) && (tank.getBullets().get(i).getyCoordination() > anOtherTank.getyCoordination()) && (tank.getBullets().get(i).getyCoordination() < anOtherTank.getyCoordination()+map.getEachWallHeight()))
			{
				indexToRemoveBullet = i;

				if(anOtherTank.getHealth()-tank.getBullets().get(i).getPower()>0)
				{
					if(!anOtherTank.hasShield())
					{
						anOtherTank.setHealth(anOtherTank.getHealth()-tank.getBullets().get(i).getPower());
						updateHealth(anOtherTank);
						if(anOtherTank.getTankName().equals("AiTank"))
						{
							Sound sound = new Sound(Utility.shootSound);
							sound.playSound();
						}
					}
				}
				else
				{
					//End Game
					Sound sound = new Sound(Utility.explosionSound);
					sound.playSound();
					startEndTime = System.nanoTime();
					winner = tank.getTankName();
					if(winner.equals(this.tank.getTankName()))
					{
						this.playerWins++;
						PCGamePanel.userLabel.setText(""  + this.playerWins);
						PCGamePanel.userWin +=1;
						PCGamePanel.pcLost +=1;
					}
					else if(winner.equals(this.aiTank.getTankName()))
					{
						this.aiWins++;
						PCGamePanel.pcLabel.setText("" + this.aiWins);
						PCGamePanel.pcWin +=1;
						PCGamePanel.userLost +=1;
					}
					endGame = true;

				}

			}

		}


		if(indexToRemoveBullet!=-1)
		{
			tank.getBullets().remove(indexToRemoveBullet);
			indexToRemoveBullet = -1;
		}
		if(indexToRemoveSoftWall !=-1)
		{
			map.getSoftWalls().remove(indexToRemoveSoftWall);
			indexToRemoveSoftWall = -1;
		}

	}

	/**
	 * Move Bullets
	 * @param tank is your tank
	 */
	public void moveBullets(Tank tank)
	{
		for(int i=0; i<tank.getBullets().size();i++)
		{
			tank.getBullets().get(i).moveUp();
		}
	}

	/**
	 * Reset Health
	 */
	public void resetHealth()
	{
		tank.setHealth(PCMenu.pcGame.tankHealth);
		aiTank.setHealth(PCMenu.pcGame.tankHealth);
		PCGamePanel.userHealth.setValue(tank.getHealth());
		PCGamePanel.pcHealth.setValue(aiTank.getHealth());
	}

	/**
	 * Reset Location for Both tank and ai tank
	 */
	public void resetLocation()
	{
		Random random = new Random();
		int upperBound = map.getProperXLocations().size();
		int randomLocation = random.nextInt(upperBound);

		tank.setxCoordination(map.getProperXLocations().get(randomLocation));
		tank.setyCoordination(map.getProperYLocations().get(randomLocation));

		int randAI;
		do {
			randAI = random.nextInt(upperBound);
		} while (randAI == randomLocation);
		aiTank.setxCoordination(map.getProperXLocations().get(randAI));
		aiTank.setyCoordination(map.getProperYLocations().get(randAI));
	}

	/**
	 * add a new Ability to the map
	 */
	private void addAbility()
	{
		Random random = new Random();
		int randNum = random.nextInt(4);
		int upperBound = map.getProperXLocations().size();
		int randIndex = random.nextInt(upperBound);

		switch (randNum)
		{
			case 0:
				//Add Extra Health
				abilities.add(new ExtraHealth(map.getProperXLocations().get(randIndex),map.getProperYLocations().get(randIndex)));
				break;

			case 1:
				//Add Extra Power
				abilities.add(new ExtraPower(map.getProperXLocations().get(randIndex),map.getProperYLocations().get(randIndex)));
				break;

			case 2:
				//Add Guard
				abilities.add(new Guard(map.getProperXLocations().get(randIndex),map.getProperYLocations().get(randIndex)));
				break;

			case 3:
				//Add Laser
				abilities.add(new Laser(map.getProperXLocations().get(randIndex),map.getProperYLocations().get(randIndex)));
				break;

		}

	}

	/**
	 * Remove ability
	 * @param tank is your tank
	 */
	private void removeAbilities(Tank tank)
	{
		int indexToRemoveAbility = -1;

		//Moved On ability
		for(int i=0 ; i<abilities.size();i++)
		{
			if((tank.getxCoordination()>=abilities.get(i).getxCoordination()-map.getEachWallWidth()) && (tank.getxCoordination()<=map.getEachWallWidth()+abilities.get(i).getxCoordination()) && (tank.getyCoordination()>=abilities.get(i).getyCoordination()-map.getEachWallHeight()) && (tank.getyCoordination()<=abilities.get(i).getyCoordination()+map.getEachWallHeight()) )
			{
				//Check Time
				if(System.currentTimeMillis() - tank.getAbilityTime() > 40000)
				{
					indexToRemoveAbility = i;
					Sound sound = new Sound(Utility.upgradeSound);
					sound.playSound();
					tank.setAbilityTime(System.currentTimeMillis());
					tank.currentAbility = abilities.get(i);
					break;
				}
			}
		}

		if(tank.currentAbility != null)
		{
			if(tank.currentAbility instanceof ExtraHealth)
			{
				if(tank.getHealth()<80)
				{
					tank.setHealth(tank.getHealth()+20);
					updateHealth(tank);
				}
			}
			else if(tank.currentAbility instanceof ExtraPower)
			{
				Random random = new Random();
				int powerValue = random.nextInt(2);
				if(powerValue==0)
				{
					tank.setBulletPower(tank.getBulletPower()*2);
				}
				else
				{
					tank.setBulletPower(tank.getBulletPower()*3);
				}
			}
			else if(tank.currentAbility instanceof Guard)
			{
				tank.setShield(true);
				//Change Picture
				if(tank.getTankName().equals("AiTank"))
				{
					tank.setImage(Utility.tankUpShield9);
					Utility.chosenPictureForAI = Utility.tankUpShield9;
				}
				else
				{
					switch (PCMenu.pcGame.getTankCode())
					{
						case 0:
							Utility.chosenPictureForPlayer = Utility.tankUpShield0;
							tank.setImage(Utility.tankUpShield0);
							break;

						case 1:
							Utility.chosenPictureForPlayer = Utility.tankUpShield1;
							tank.setImage(Utility.tankUpShield1);
							break;

						case 2:
							Utility.chosenPictureForPlayer = Utility.tankUpShield2;
							tank.setImage(Utility.tankUpShield2);
							break;

						case 3:
							Utility.chosenPictureForPlayer = Utility.tankUpShield3;
							tank.setImage(Utility.tankUpShield3);
							break;

						case 4:
							Utility.chosenPictureForPlayer = Utility.tankUpShield4;
							tank.setImage(Utility.tankUpShield4);
							break;
						case 5:
							Utility.chosenPictureForPlayer = Utility.tankUpShield5;
							tank.setImage(Utility.tankUpShield5);
							break;

						case 6:
							Utility.chosenPictureForPlayer = Utility.tankUpShield6;
							tank.setImage(Utility.tankUpShield6);
							break;

						case 7:
							Utility.chosenPictureForPlayer = Utility.tankUpShield7;
							tank.setImage(Utility.tankUpShield7);
							break;

						case 8:
							Utility.chosenPictureForPlayer = Utility.tankUpShield8;
							tank.setImage(Utility.tankUpShield8);
							break;

					}
				}
			}
			else if (tank.currentAbility instanceof Laser)
			{
				tank.setLaser(true);
				tank.setBulletPower(100);
				tank.setBulletSpeed(tank.getBulletSpeed()*2);
				//Change Picture
				if(tank.getTankName().equals("AiTank"))
				{
					tank.setImage(Utility.tankUpLaser9);
					Utility.chosenPictureForAI = Utility.tankUpLaser9;
				}
				else
				{
					switch (PCMenu.pcGame.getTankCode())
					{
						case 0:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser0;
							tank.setImage(Utility.tankUpLaser0);
							break;

						case 1:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser1;
							tank.setImage(Utility.tankUpLaser1);
							break;

						case 2:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser2;
							tank.setImage(Utility.tankUpLaser2);
							break;

						case 3:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser3;
							tank.setImage(Utility.tankUpLaser3);
							break;

						case 4:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser4;
							tank.setImage(Utility.tankUpLaser4);
							break;
						case 5:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser5;
							tank.setImage(Utility.tankUpLaser5);
							break;

						case 6:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser6;
							tank.setImage(Utility.tankUpLaser6);
							break;

						case 7:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser7;
							tank.setImage(Utility.tankUpLaser7);
							break;

						case 8:
							Utility.chosenPictureForPlayer = Utility.tankUpLaser8;
							tank.setImage(Utility.tankUpLaser8);
							break;

					}
				}

			}
			tank.currentAbility = null;
		}

		if(indexToRemoveAbility !=-1)
		{
			abilities.remove(indexToRemoveAbility);
			indexToRemoveAbility = -1;
		}

	}

	/**
	 * Update User Health
	 * @param anOtherTank is a Tank
	 */
	private void updateHealth(Tank anOtherTank)
	{
		if(anOtherTank.getTankName().equals("AiTank"))
		{
			PCMenu.pcGame.setAiTankHealth(anOtherTank.getHealth());
			PCGamePanel.pcHealth.setValue(anOtherTank.getHealth());
		}
		else
		{
			PCMenu.pcGame.setHealthOfTank(anOtherTank.getHealth());
			PCGamePanel.userHealth.setValue(anOtherTank.getHealth());
		}
	}

	/**
	 * Back to Normal after a period
	 * @param tank is a Tank
	 */
	private void backToNormalAbility(Tank tank , boolean newRound)
	{
		if(System.currentTimeMillis() - tank.abilityTime > 30000 || newRound)
		{
			//Reset Ability
			tank.setBulletPower(backupPower);
			tank.setBulletSpeed(backupSpeed);
			//Reset Picture to Normal

			//Change Picture
			if(tank.hasShield())
			{
				if(tank.getTankName().equals("AiTank"))
				{
					tank.setImage(Utility.tankUp9);
					Utility.chosenPictureForAI = Utility.tankUp9;
				}
				else
				{
					switch (PCMenu.pcGame.getTankCode())
					{
						case 0:
							Utility.chosenPictureForPlayer = Utility.tankUp0;
							tank.setImage(Utility.tankUp0);
							break;

						case 1:
							Utility.chosenPictureForPlayer = Utility.tankUp1;
							tank.setImage(Utility.tankUp1);
							break;

						case 2:
							Utility.chosenPictureForPlayer = Utility.tankUp2;
							tank.setImage(Utility.tankUp2);
							break;

						case 3:
							Utility.chosenPictureForPlayer = Utility.tankUp3;
							tank.setImage(Utility.tankUp3);
							break;

						case 4:
							Utility.chosenPictureForPlayer = Utility.tankUp4;
							tank.setImage(Utility.tankUp4);
							break;
						case 5:
							Utility.chosenPictureForPlayer = Utility.tankUp5;
							tank.setImage(Utility.tankUp5);
							break;

						case 6:
							Utility.chosenPictureForPlayer = Utility.tankUp6;
							tank.setImage(Utility.tankUp6);
							break;

						case 7:
							Utility.chosenPictureForPlayer = Utility.tankUp7;
							tank.setImage(Utility.tankUp7);
							break;

						case 8:
							Utility.chosenPictureForPlayer = Utility.tankUp8;
							tank.setImage(Utility.tankUp8);
							break;

					}
				}
				tank.setShield(false);
			}

			//Change Picture
			if(tank.hasLaser())
			{
				if(tank.getTankName().equals("AiTank"))
				{
					tank.setImage(Utility.tankUp9);
					Utility.chosenPictureForAI = Utility.tankUp9;
				}
				else
				{
					switch (PCMenu.pcGame.getTankCode())
					{
						case 0:
							Utility.chosenPictureForPlayer = Utility.tankUp0;
							tank.setImage(Utility.tankUp0);
							break;

						case 1:
							Utility.chosenPictureForPlayer = Utility.tankUp1;
							tank.setImage(Utility.tankUp1);
							break;

						case 2:
							Utility.chosenPictureForPlayer = Utility.tankUp2;
							tank.setImage(Utility.tankUp2);
							break;

						case 3:
							Utility.chosenPictureForPlayer = Utility.tankUp3;
							tank.setImage(Utility.tankUp3);
							break;

						case 4:
							Utility.chosenPictureForPlayer = Utility.tankUp4;
							tank.setImage(Utility.tankUp4);
							break;
						case 5:
							Utility.chosenPictureForPlayer = Utility.tankUp5;
							tank.setImage(Utility.tankUp5);
							break;

						case 6:
							Utility.chosenPictureForPlayer = Utility.tankUp6;
							tank.setImage(Utility.tankUp6);
							break;

						case 7:
							Utility.chosenPictureForPlayer = Utility.tankUp7;
							tank.setImage(Utility.tankUp7);
							break;

						case 8:
							Utility.chosenPictureForPlayer = Utility.tankUp8;
							tank.setImage(Utility.tankUp8);
							break;

					}
				}
				tank.setLaser(false);
			}


		}
	}

	/**
	 * Move User
	 */
	private void userKeyBoard()
	{
		if (keyUP)
		{
			//KeyUp Movement - Go in same angle path
			tank.moveUp();
		}
		if (keyDOWN)
		{
			//KeyDown Movement - Go in same angle path
			tank.moveDown();
		}
		if (keyLEFT)
		{
			//Rotate Left
			tank.rotateLeft();
		}
		if (keyRIGHT)
		{
			//Rotate Right
			tank.rotateRight();
		}
		if(keySpace)
		{
			//Shoot
			tank.shoot();
		}
	}
}