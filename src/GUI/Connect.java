package GUI;

import Engine.RunGame;
import Engine.ThreadPool;

import java.awt.*;

public class Connect
{
    public static void startGame()
    {
        //Start GameFrame
        ThreadPool.init();
        EventQueue.invokeLater(new RunGame());
    }

}
