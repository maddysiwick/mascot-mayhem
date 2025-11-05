import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arena extends World
{

    /**
     * Constructor for objects of class Arena.
     * 
     */
    public Arena()
    {    
        super(1280, 720, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Keith keith = new Keith();
        addObject(keith,1051,544);
        Duke duke = new Duke();
        addObject(duke,327,541);
        duke.setLocation(351,550);
    }
}
