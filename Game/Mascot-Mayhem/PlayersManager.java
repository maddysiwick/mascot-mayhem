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
            return ((Arena)getWorld()).getPlayer2();
        }
        else{
            return ((Arena)getWorld()).getPlayer1();
        }
    }
}
