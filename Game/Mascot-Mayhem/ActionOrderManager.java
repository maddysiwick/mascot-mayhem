import greenfoot.*;
/**
 * Write a description of class ActionOrderManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ActionOrderManager extends Actor 
{
    Player player1;
    Player player2;
    boolean deadCharacterFlag;
    
    public ActionOrderManager(Player player1,Player player2)
    {
        this.player1=player1;
        this.player2=player2;
        deadCharacterFlag=false;
    }
    
    public void act(){
        if(player2.getAIControlled()){
            player2.actionsAI(player1.getInput());
        }
        executeInputs();
    }
    
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
            if(!deadCharacterFlag){
                player1.actions();
            }
            if(!deadCharacterFlag){
                player2.actions();
            }
        }
        else{
            if(!deadCharacterFlag){
                player2.actions();
            }
            if(!deadCharacterFlag){
                player1.actions();
            }
        }
    }
    
    public void flagDeadCharacter(){
        deadCharacterFlag=true;
    }
}