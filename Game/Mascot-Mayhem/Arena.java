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
    private String saveName;
    private GreenfootSound music=new GreenfootSound("fightMusic.wav");    

    /**
     * Constructor for objects of class Arena.
     * 
     */
    public Arena(int p1Selection, int p2Selection, boolean withAi,int aiDifficulty,boolean campaignBattle,int currentLevel,String saveName)
    {
        super(1280, 720, 1); 
        this.p1Selection=p1Selection;
        this.p2Selection=p2Selection;
        this.withAi=withAi;
        this.aiDifficulty=aiDifficulty;
        this.campaign=campaignBattle;
        this.currentLevel=currentLevel;
        this.saveName=saveName;
        music.playLoop();
        prepare();
    }
    public Arena(int p1Selection,String saveName){
        super(1280, 720, 1); 
        this.p1Selection=p1Selection;
        campaign=true;
        currentLevel=13;
        this.p2Selection=8;
        campaign=true;
        this.saveName=saveName;
        prepare();
    }
    
    private void prepare()
    {
        p1 = getCharacter(p1Selection,true,false,campaign,currentLevel);
        p2 = getCharacter(p2Selection,false,withAi,campaign,currentLevel);
        addObject(p1,140,614);
        if(!p2.getIsEbodaHead()){
            addObject(p2,1126,614);
        }
        else{
            addObject(p2,getWidth()/2,400);
            GreenfootImage bg = getBackground();
            bg.drawImage(new GreenfootImage("ebodaSuit.png"),getWidth()/2-225,410);
        }
        queue = new ActionOrderManager(p1,p2);
        addObject(queue,0,0);
    }

    public Player getCharacter(int selection,boolean playerOne,boolean aiControlled,boolean campaign,int currentLevel)
    {
        switch(selection){
            case 1:
                return new Wilbur(playerOne,aiControlled,aiDifficulty,campaign,currentLevel,saveName);
            case 2:
                return new Keith(playerOne,aiControlled,aiDifficulty,campaign,currentLevel,saveName);
            case 3:
                return new Tux(playerOne,aiControlled,aiDifficulty,campaign,currentLevel,saveName);
            case 4:
                return getCharacter(Greenfoot.getRandomNumber(7)+1,playerOne,aiControlled,campaign,currentLevel);
            case 5:
                return new Gnu(playerOne,aiControlled,aiDifficulty,false,campaign,currentLevel,saveName);
            case 6:
                return new Duke(playerOne,aiControlled,aiDifficulty,campaign,currentLevel,saveName);
            case 7:
                return new Suzanne(playerOne,aiControlled,aiDifficulty,campaign,currentLevel,saveName);
            case 8:
                return new EbodaHead(saveName);
        }
        return null;
    }
    public Player getPlayer1(){
        return p1;
    }
    public Player getPlayer2(){
        return p2;
    }
    public ActionOrderManager getQueue(){
        return queue;
    }
    public boolean getCampaign(){
        return campaign;
    }
    public void pauseMusic(){
        music.pause();
    }
}
