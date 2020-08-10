package Engine;

import GUI.TankTroubleGUI;
import Game.Utils.Draw;
import Game.Utils.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


/**
 * This is Our Game Frame
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering,
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Ahmad Foroughi
 * @version 2.0
 */
public class GameFrame extends JFrame
{
	//                  ---------------------------Fields---------------------------

	//GAME HEIGHT
	public static final int GAME_HEIGHT = 540;
	//GAME WIDTH
	public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;
	//BufferStrategy
	private BufferStrategy bufferStrategy;

	/**
	 * Create a new Game Frame
	 */
	public GameFrame()
	{
		super("Tank Trouble");
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setUndecorated(true);
		ImageIcon icon = new ImageIcon(TankTroubleGUI.getIconPath());
		this.setIconImage(icon.getImage());
		this.setVisible(true);
	}

	/**
	 * This must be called once after the JFrame is shown:
	 *    frame.setVisible(true);
	 * and before any rendering is started.
	 */
	public void initBufferStrategy()
	{
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
	}

	/**
	 * Game rendering with triple-buffering using BufferStrategy.
	 */
	public void render(GameState state) {
		do {
			do {
				Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
				try {
					doRendering(graphics, state);
				} finally {
					graphics.dispose();
				}
			} while (bufferStrategy.contentsRestored());

			bufferStrategy.show();
			Toolkit.getDefaultToolkit().sync();

		} while (bufferStrategy.contentsLost());
	}

	/**
	 * Rendering all game elements based on the game state.
	 */
	private void doRendering(Graphics2D g2d, GameState state)
	{
		// Draw background
		//Note: You Can use tileGrass or tileSand
		g2d.drawImage(Utility.tileGrass,0,0,GAME_WIDTH, GAME_HEIGHT,null);
		Draw.DrawTank(g2d,state);
		Draw.DrawBullets(g2d,state);
		Draw.DrawWalls(g2d,state);
		Draw.DrawEnd(g2d,state);
		Draw.DrawAbilities(g2d, state);

	}


}
