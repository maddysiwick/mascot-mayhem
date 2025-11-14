import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arena extends World
{
    private int p1Selection;
    private int p2Selection;
    private Player p1;
    private Player p2;
    private ActionOrderManager queue;

    /**
     * Constructor for objects of class Arena.
     * 
     */
    public Arena(int p1Selection, int p2Selection)
    {    
        super(1280, 720, 1); 
        this.p1Selection=p1Selection;
        this.p2Selection=p2Selection;
        prepare();
    }
    
    private void prepare()
    {
        p1 = getCharacter(p1Selection,true);
        p2 = getCharacter(p2Selection,false);
        queue = new ActionOrderManager(p1,p2);
        addObject(p1,140,614);
        addObject(p2,1126,614);
    }

    public Player getCharacter(int selection,boolean playerOne)
    {
        switch(selection){
            case 1:
                return new Wilbur(playerOne);
            case 2:
                return new Keith(playerOne);
            case 3:
                return new Tux(playerOne);
            case 4:
                return getCharacter(Greenfoot.getRandomNumber(7)+1,playerOne);
            case 5:
                return new Gnu(playerOne);
            case 6:
                return new Duke(playerOne);
            case 7:
                return new Suzanne(playerOne);
        }
        return null;
    }
}
