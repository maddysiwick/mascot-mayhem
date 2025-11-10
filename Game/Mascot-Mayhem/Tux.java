import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tux here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tux extends Player
{
    private String baseSprite="tux.png";
    private String hitImage="images/tuxKickTEMP.png";
    private int hitPoints=10;
    private int damage=5;
    /**
     * Act - do whatever the Tux wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Tux(boolean playerOne, int ultimateBarPosition, int healthBarPosition)
    {
        setCharacteristics(playerOne,ultimateBarPosition,healthBarPosition, hitPoints,hitImage,damage,baseSprite);
    }
    
    public void act()
    {
        actions();
        jumping();
    }
}