import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EbodaHandManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EbodaManager extends Actor
{
    /**
     * Act - do whatever the EbodaHandManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    EbodaHand firstHand;
    EbodaHand secondHand;
    boolean turnOverFlag;
    boolean firstHandTurn;
    boolean firstTime=true;
    
    public EbodaManager(){
        firstHand=new EbodaHand(true,this);
        secondHand=new EbodaHand(false,this);
        firstHandTurn=true;
    }
    public void act()
    {
        if(firstTime){
            setUp();
        }
        if(turnOverFlag){
            firstHandTurn=!firstHandTurn;
            turnOverFlag=false;
            if(firstHandTurn){
                firstHand.setMyTurn(true);
            }
            else{
                secondHand.setMyTurn(true);
            }
        }
    }
    public void flagTurnOver(){
        turnOverFlag=true;
    }
    private void setUp(){
        getWorld().addObject(firstHand,getWorld().getWidth()-400,200);
        getWorld().addObject(secondHand,getWorld().getWidth()+400,200);
        ((Arena)getWorld()).getPlayer2().setLocation(0,0); //TEMP just so i can test without the campaign manager
    }
}
