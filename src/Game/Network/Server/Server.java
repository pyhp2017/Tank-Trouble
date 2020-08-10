package Game.Network.Server;

import GUI.LanGame;

import java.util.List;
import java.util.Map;

/**
 * This is a Server
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Server
{
    //----------------FIELDS----------------\\
    private static final int CONN_TIMEOUT = 30000;// (30 seconds)
    private static final int SOCKET_TIMEOUT= 2000;
    private static final String ADMIN = "admin$"; // name of admin account.
    //Default Settings
    private LanGame lanGame;
    //Vars
    private static boolean shutdown = false;

//    static Map<String, Game> gameList;     //all games waiting for players
//    static List<ClientThread> clientList; //all connected clients
//    static List<String> userList;         //all current usernames
//    static Map<String, Game> activeGames;  //all currently active(started) games

}
