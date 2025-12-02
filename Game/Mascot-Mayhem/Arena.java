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
    private boolean campaign;
    private int currentLevel;

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
    public Arena(int p1Selection){
        super(1280, 720, 1); 
        this.p1Selection=p1Selection;
        this.p2Selection=8;
        prepare();
    }
    
    private void prepare()
    {
        p1 = getCharacter(p1Selection,true,false,campaign,currentLevel);
        p2 = getCharacter(p2Selection,false,withAi,campaign,currentLevel);
        queue = new ActionOrderManager(p1,p2);
        addObject(queue,0,0);
        addObject(p1,140,614);
        if(!p2.getIsEbodaHead()){
            addObject(p2,1126,614);
        }
        else{
            addObject(p2,getWidth()/2,400);
        }
    }

    public Player getCharacter(int selection,boolean playerOne,boolean aiControlled,boolean campaign,int currentLevel)
    {
        switch(selection){
            case 1:
                return new Wilbur(playerOne,aiControlled,aiDifficulty,campaign,currentLevel);
            case 2:
                return new Keith(playerOne,aiControlled,aiDifficulty,campaign,currentLevel);
            case 3:
                return new Tux(playerOne,aiControlled,aiDifficulty,campaign,currentLevel);
            case 4:
                return getCharacter(Greenfoot.getRandomNumber(7)+1,playerOne,aiControlled,campaign,currentLevel);
            case 5:
                //this will be gnu but its suzanne atm
                return new Suzanne(playerOne,aiControlled,aiDifficulty,campaign,currentLevel);
            case 6:
                return new Duke(playerOne,aiControlled,aiDifficulty,campaign,currentLevel);
            case 7:
                return new Gnu(playerOne,aiControlled,aiDifficulty,false,campaign,currentLevel);
            case 8:
                return new EbodaHead();
        }
        return null;
    }
    public Player getPlayer1(){
        return p1;
    }
    public Player getPlayer2(){
        return p2;
    }
}
