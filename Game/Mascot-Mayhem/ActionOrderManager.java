import greenfoot.*;
/**
 * Write a description of class ActionOrderManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ActionOrderManager extends Actor 
{
    // instance variables - replace the example below with your own
    Player player1;
    Player player2;
    /**
     * Constructor for objects of class ActionOrderManager
     */
    public ActionOrderManager(Player player1,Player player2)
    {
        this.player1=player1;
        this.player2=player2;
    }
    public void act(){
        executeInputs();
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public boolean playerOneGoesFirst()
    {
        boolean player1Status=checkValidReasons(player1);
        boolean player2Status=checkValidReasons(player2);
        if(player1Status && !player2Status){
            return true;
        }
        else if(player1Status && !player2Status){
            return false;
        }
        else if(Greenfoot.getRandomNumber(2)==1){
            return true;
        }
        return false;
    }
    private boolean checkValidReasons(Player player){
        if(player.getInput()=="block"||player.getJumping()||player.getBlocking()){
            return true;
        }
        return false;
    }
    private void executeInputs(){
        if(playerOneGoesFirst()){
            player1.actions();
            player2.actions();
        }
        else{
            player2.actions();
            player2.actions();
        }
    }
}
