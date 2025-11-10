import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Suzanne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Suzanne extends Player
{
    private String baseSprite="lemur.png";
    private String hitImage="images/suzannePunchTemp.png";
    private int hitPoints=10;
    private int damage=5;
    /**
     * Act - do whatever the Suzanne wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Suzanne(boolean playerOne, int ultimateBarPosition, int healthBarPosition)
    {
        setCharacteristics(playerOne,ultimateBarPosition,healthBarPosition, hitPoints,hitImage,damage,baseSprite);
    }
    
    public void act()
    {
        actions();
        jumping();
    }
}
