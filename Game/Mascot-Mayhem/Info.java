import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Info here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Info extends World
{

    /**
     * Constructor for objects of class Info.
     * 
     */
    public Info()
    {    
        super(1280, 720, 1);
        
        showText("""
                    Player1 inputs:
                    A and D for left and right
                    W to jump
                    F to attack
                    G to use ultimate attack
                    H to block
                    """, 500, 75);
        showText("""
                    Player2 inputs:
                    left and right arrows for left and right
                    Up arrow to jump
                    / to attack
                    . to use ultimate attack
                    , to block
                    """, 500, 275);
        showText("These can also be used to navigate menus, with S and the down arrow going down and attack to select.",500,375);
        showText("blocking will let you return to the menu from this page",500,425);
    }
    
    public void act()
    {
        if(InputManager.getPlayerOneInput(false)=="block"||InputManager.getPlayerTwoInput(false)=="block"){
            Greenfoot.setWorld(new Title());
        }
    }
}
