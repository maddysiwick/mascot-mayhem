import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SplashScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Splash extends World
{
    private double timeWelcomeScreenCreation=System.currentTimeMillis();
    
    /**
     * Constructor for objects of class SplashScreen.
     * 
     */
    public Splash()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        setBackground("splashScreen.png");
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new Title()); 
        }
        if (System.currentTimeMillis() >= (timeWelcomeScreenCreation + (1.75 * 1000)))
        {
            Greenfoot.setWorld(new Title()); 
        }
    }
}
