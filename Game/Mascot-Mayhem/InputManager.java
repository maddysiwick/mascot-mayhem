import greenfoot.*;
/**
 * Allows other clases to get player inputs through static methods
 * 
 * @version 4/11/2025
 */
public class InputManager  
{
    
    public InputManager()
    {
    }

    /** 
     * @return String with p1 input
     */
    public static String getPlayerOneInput()
    {
        String input = "do nothing";
        if(Greenfoot.isKeyDown("C")){
            input = "ultimate";
        }
        else if(Greenfoot.isKeyDown("z")){
            input = "attack";
        }
        else if(Greenfoot.isKeyDown("w")){
            input = "up";
        }
        else if(Greenfoot.isKeyDown("d")){
            input = "left";
        }
        else if(Greenfoot.isKeyDown("a")){
            input = "right";
        }
        else if(Greenfoot.isKeyDown("s")){
            input = "crouch";
        }
        return input;
    }
    
    /** 
     * @return String with p2 input
     */
    public static String getPlayerTwoInput()
    {
        String input = "do nothing";
        if(Greenfoot.isKeyDown(".")){
            input = "ultimate";
        }
        else if(Greenfoot.isKeyDown("/")){
            input = "attack";
        }
        else if(Greenfoot.isKeyDown("up")){
            input = "up";
        }
        else if(Greenfoot.isKeyDown("right")){
            input = "left";
        }
        else if(Greenfoot.isKeyDown("left")){
            input = "right";
        }
        else if(Greenfoot.isKeyDown("down")){
            input = "crouch";
        }
        return input;
    }
}
