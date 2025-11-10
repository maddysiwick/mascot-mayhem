import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Suzanne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Suzanne extends Player
{
    /**
     * Act - do whatever the Tux wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Suzanne()
    {
        setCharacteristics(false, 1000, 10, "images/suzannePunchTemp.png", 5, "lemur.png");
    }
    
    public void act()
    {
        actions();
        jumping();
    }
}
