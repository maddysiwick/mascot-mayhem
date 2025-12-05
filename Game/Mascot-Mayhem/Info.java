import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Info here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Info extends World
{
    private GreenfootSound music=new GreenfootSound("menuMusic.wav");

    /**
     * Constructor for objects of class Info.
     * 
     */
    public Info()
    {    
        super(1280, 720, 1);
        setBackground("controls.png");
        music.playLoop();
    }
    
    public void act()
    {
        if(InputManager.getPlayerOneInput(false)=="block"||InputManager.getPlayerTwoInput(false)=="block"){
            music.pause();
            Greenfoot.setWorld(new Title());
        }
    }
}
