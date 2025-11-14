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
    CharacterSelector temp;
    ActionOrderManager queue;
    public Arena()
    {    
        super(1280, 720, 1);
        temp = new CharacterSelector();
        prepare();
    }
    
    private void prepare()
    {
        addObject(temp,0,0);
        temp.act();
        queue = new ActionOrderManager(temp.getPlayer1(),temp.getPlayer2());
        addObject(queue,0,0);
    }
    public CharacterSelector getCharacterSelector(){
        return temp;
    }
}
