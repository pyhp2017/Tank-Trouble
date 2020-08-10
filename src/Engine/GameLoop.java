/*** In The Name of Allah ***/
package Engine;


import Engine.GameFrame;
import Engine.GameState;
import GUI.PCGame;
import GUI.PCMenu;
import GUI.ServerMenu;
import Game.Network.Server.StaticData;

import java.util.Date;

public class GameLoop implements Runnable {
	
	public static final int FPS = 30;
	
	private GameFrame canvas;
	private GameState state;



	public GameLoop(GameFrame frame) {
		canvas = frame;
	}
	
	public void init()
	{
		state = new GameState();
		canvas.addKeyListener(state.getKeyListener());
	}

	@Override
	public void run() {
		boolean gameOver = false;


		while (!gameOver) {

			if(!StaticData.netWorkGame)
			{
				if(PCMenu.pcGame.isStopGame())
				{
					canvas.dispose();
					break;
				}
			}

			try {
				long start = System.currentTimeMillis();
				//
				state.update();
				canvas.render(state);
				gameOver = state.gameOver;
				//
				long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
				if (delay > 0)
					Thread.sleep(delay);

			} catch (InterruptedException ex) {
			}

		}
		canvas.render(state);

	}

}
