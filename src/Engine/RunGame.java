package Engine;

import GUI.PCGame;

public class RunGame implements Runnable
{
    //Field
    public GameFrame gameFrame;
    public GameLoop gameLoop;


    @Override
    public void run()
    {
        gameFrame = new GameFrame();
        gameFrame.initBufferStrategy();
        gameLoop = new GameLoop(gameFrame);
        gameLoop.init();
        ThreadPool.execute(gameLoop);
    }
}