import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tux here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tux extends Player
{
    public Tux(boolean playerOne, int ultimateBarPosition, int healthBarPosition)
    {
        baseSprite="tux.png";
        hitImage="images/tuxKickTEMP.png";
        hitPoints=10;
        damage=5;
        bio="Tux: \n Though he isn't the brightest, he's a fairly balanced fighter. Mortal enemy of the one with horns.";
        setCharacteristics(playerOne,ultimateBarPosition,healthBarPosition);
    }
    
    public void act()
    {
        getPlayerInput();
        jumping();
    }
}