import java.util.*;
import greenfoot.*;

public class PlayersManager extends Actor 
{
    private List players;

    public PlayersManager()
    {
        
    }

    public void getPlayers() 
    {
        players = getWorld().getObjects(Player.class);
    }

    public Player getOppositePlayer(boolean playerOne)
    {
        getPlayers();
        if(playerOne){
            return (Player)players.get(1);
        }
        else{
            return (Player)players.get(0);
        }
    }
}
