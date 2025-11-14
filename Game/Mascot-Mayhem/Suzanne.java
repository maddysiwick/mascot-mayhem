import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Suzanne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Suzanne extends Player
{
    public Suzanne(boolean playerOne, int ultimateBarPosition, int healthBarPosition)
    {
        baseSprite="lemur.png";
        hitImage="images/suzannePunchTemp.png";
        hitPoints=10;
        damage=5;
        bio="Suzanne: \n A mischief-loving monkey who stabs people with her surprisingly sharp bananas.";
        setCharacteristics(playerOne,ultimateBarPosition,healthBarPosition);
    }
    
    public void act()
    {
        getPlayerInput();
        jumping();
    }
}
