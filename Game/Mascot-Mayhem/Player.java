import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Framework for player characters
 * 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        getActions();
    }
    
    public void getActions()
    {
        switch(getPlayerInput()){
            case "ultimate":
                break;
            case "attack":
                break;
            case "up":
                break;
            case "right":
                break;
            case "left":
                break;
            case "crouch":
                break;
        }
    }
    
    public String getPlayerInput()
    {
        if(player=1){//dummy varuiable for a player select later on
            return InputManager.getPlayerOneInput();
        }
        else{
            return InputManager.getPlayerTwoInput();
        }
    }
}
