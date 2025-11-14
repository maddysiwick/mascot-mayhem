import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Keith here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Keith extends Player
{
    /**
     * Act - do whatever the Tux wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Keith(boolean playerOne)
    {
        super(playerOne);
        baseSprite="tux.png";
        hitImage="images/tuxKickTEMP.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=10;
        damage=5;
        bio="Keith %n A rat who’s obese, diseased, and missing a leg. Despite this he’s loved by everyone, but has an especially close relationship with Duke. Holds a hidden power which even he is unaware of. Extremely fast.";
    }
    
    public void act()
    {
        super.act();
    }
}
