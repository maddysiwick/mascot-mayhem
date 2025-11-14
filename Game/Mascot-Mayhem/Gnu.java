import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gnu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gnu extends Player
{
    public Gnu(boolean playerOne)
    {
    super(playerOne);  
    baseSprite="tux.png";
    hitImage="images/tuxKickTEMP.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
    hitPoints=10;
    damage=5;
    bio=": Duke \n The sweetest robot, and Keith’s best friend. Loves slapping those who annoy him, but can shoot a laser in dire situations.";    
    }
    
    /**
     * Act - do whatever the Gnu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
}
