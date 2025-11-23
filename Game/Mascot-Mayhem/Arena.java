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
    private boolean withAi;
    private int aiDifficulty;

    /**
     * Constructor for objects of class Arena.
     * 
     */
    public Arena(int p1Selection, int p2Selection, boolean withAi,int aiDifficulty)
    {    
        super(1280, 720, 1); 
        this.p1Selection=p1Selection;
        this.p2Selection=p2Selection;
        this.withAi=withAi;
        this.aiDifficulty=aiDifficulty;
        prepare();
    }
    
    private void prepare()
    {
        p1 = getCharacter(p1Selection,true,false);
        p2 = getCharacter(p2Selection,false,withAi);
        queue = new ActionOrderManager(p1,p2);
        addObject(queue,0,0);
        addObject(p1,140,614);
        addObject(p2,1126,614);
    }

    public Player getCharacter(int selection,boolean playerOne,boolean aiControlled)
    {
        switch(selection){
            case 1:
                return new Wilbur(playerOne,aiControlled,aiDifficulty);
            case 2:
                return new Keith(playerOne,aiControlled,aiDifficulty);
            case 3:
                return new Tux(playerOne,aiControlled,aiDifficulty);
            case 4:
                return getCharacter(Greenfoot.getRandomNumber(7)+1,playerOne,aiControlled);
            case 5:
                //this will be gnu but its suzanne atm
                return new Suzanne(playerOne,aiControlled,aiDifficulty);
            case 6:
                return new Duke(playerOne,aiControlled,aiDifficulty);
            case 7:
                return new Suzanne(playerOne,aiControlled,aiDifficulty);
        }
        return null;
    }
}
