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
    public Keith()
    {
        setCharacteristics(true, 50, 50, 10, "images/tuxKickTEMP.png", 5, "tux.png");
    }
    
    public void act()
    {
        actions();
    }
}
