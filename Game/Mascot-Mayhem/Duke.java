import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Duke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Duke extends Player
{
    
    public Duke(boolean playerOne,boolean aiControlled)
    {
        super(playerOne,aiControlled);  
        baseSprite="tux.png";
        hitImage="images/tuxKickTEMP.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=10;
        damage=5;
        bio="Duke \n The sweetest robot, and Keith’s best friend. Loves slapping those who annoy him, but can shoot a laser in dire situations.";
    }
    
    public void act()
    {
        actions();
    }
}
