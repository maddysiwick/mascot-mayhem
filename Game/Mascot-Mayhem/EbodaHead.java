import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EbodaHead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EbodaHead extends Player
{
    /**
     * Act - do whatever the EbodaHead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean firstTime=true;
    EbodaManager manager;
    public EbodaHead(){
        super(false,false,0,"ebodaHeadTEMP.png","ebodaHeadTEMP.png",true,13);
        manager=new EbodaManager();
        hitPoints=200;
        isEbodaHead=true;
        bio="The head of an extremely evil corporation";
    }
    public void act()
    {
        if(firstTime){
            getWorld().addObject(manager,0,0);//should do this in the world probably to avoid delay
            firstTime=false;
        }
    }
    public GreenfootImage createLeftSprite(String filename){
        return new GreenfootImage(filename);
    }
}
