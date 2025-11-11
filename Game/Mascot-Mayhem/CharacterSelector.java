import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CharacterSelector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharacterSelector extends Actor
{
    private Player playerOneCharacter;
    private Player playerTwoCharacter;
    private Tux tux;
    private Duke duke;
    private Gnu gnu;
    private Keith keith;
    private Suzanne suzanne;
    private boolean firstTime = true;
    private int p1UltimateBarPosition = 150;
    private int p2UltimateBarPosition = 1126; 
    private int p1HealthBarPosition = 175;
    private int p2HealthBarPosition = 614;
    
    public CharacterSelector()
    {
        //playerOneCharacter=p1;
        //playerTwoCharacter=p2;
    }

    public void act()
    {
        if(firstTime){
            tempStart();
            firstTime=false;
        }
    }
    
    /**
     * right now literally all this does is replace the prepare() method in Arena
     */
    public void tempStart()
    {
        playerOneCharacter = new Tux(true,150,175);
        getWorld().addObject(playerOneCharacter,140,614);
        playerTwoCharacter = new Suzanne(false,1130,905);
        getWorld().addObject(playerTwoCharacter,1126,614);
    }
}
