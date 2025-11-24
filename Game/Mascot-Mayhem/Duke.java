import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Duke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Duke extends Player
{
    protected GreenfootImage laser;
    private List players;
    public Duke(boolean playerOne,boolean aiControlled,int aiDifficulty)
    {
        super(playerOne,aiControlled,aiDifficulty);  
        baseSprite="dukeTEMP.png";
        hitImage="images/dukeHitTEMP.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=100;
        damage=5;
        bio="Duke \n The sweetest robot, and Keith's best friend. Loves slapping those who annoy him, but can shoot a laser in dire situations.";
    }
    
    public void act()
    {
        super.act();
        players=getWorld().getObjects(Player.class);
    }
    
    public void triggerUltimate(){
        if(ultPossible()){
            super.triggerUltimate();
            usingUltimate=true;
            ultTimer=40;
            laser = new GreenfootImage(5,5);
            laser.setColor(Color.BLUE);
            if(playerOne){
                laser.drawLine(getX(),getY(),players.get(1).getX(),players.get(1).getY());
            }
            else{
                laser.drawLine(getX(),getY(),((Arena)getWorld()).getPlayer1().getX(),((Arena)getWorld()).getPlayer1().getY());
            }
        }
    }
    
    public void useUltimate(){
        ultTimer--;
        if(ultTimer==0){
            usingUltimate=false;
        }
    }
}
