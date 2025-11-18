import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gimp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wilbur extends Player
{
    public Wilbur(boolean playerOne,boolean aiControlled)
    {
        super(playerOne,aiControlled);
        baseSprite="tux.png";
        hitImage="images/tuxKickTEMP.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=10;
        damage=5;
        bio="Wilbur \n An unstable gimp who loves painting. Not the strongest, but quite fast.";
    }    
    
    public void act()
    {
        super.act();    
    }
}
