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
    protected GreenfootImage laserLine;
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
            laserLine = new GreenfootImage(1280,720);
            laserLine.setColor(Color.RED);
            laserLine.setFont(new Font(50));
            Player target;
            AddedImage laser = new AddedImage(750,false);
            if(playerOne){
                target = (Player) players.get(1);
            }
            else{
                target = (Player) players.get(0);
            }
            laserLine.drawLine(getX(),getY(),target.getX(),target.getY());
            laser.setImage(laserLine);
            getWorld().addObject(laser,getWorld().getWidth()/2,getWorld().getHeight()/2);
            target.takeHit(50);
        }
    }
    
}
