import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Tux here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tux extends Player
{    
    public Tux(boolean playerOne,boolean aiControlled)
    {
        super(playerOne,aiControlled);
        baseSprite="tux.png";
        hitImage="images/tuxKickTEMP.png";
        hitPoints=100;
        damage=5;
        name="Tux";
        bio="Tux: \n Though he isn't the brightest, he's a fairly balanced fighter. Mortal enemy of the one with horns.";
        firstTime=true;
    }
    
    public void act()
    {
        super.act();
        if(firstTime){
            List players = getWorld().getObjects(Player.class);
            player=(Player)players.get(1);
        }
    }

    public void triggerUltimate()
    {
        super.triggerUltimate();
        damage=35;
        usingUltimate=true;
        ultTimer=60;
    }

    public void useUltimate()
    {
        if(usingUltimate){
            System.out.println(ultTimer+"");
            if(ultTimer>30){
                setLocation(getX(),getY()-6);
                --ultTimer;
            }
            else if(ultTimer==30){
                if(player.getX()>getX()){
                    move(1*speedMultiplier);
                }
                else if(player.getX()<getX()){
                    move(-1*speedMultiplier);
                }
                if(player.getX()-5<=getX()&&player.getX()+5>=getX()){
                    --ultTimer;
                }
            }
            else if(ultTimer<30&&ultTimer>1){
                setLocation(getX(),getY()+6);
                --ultTimer;
            }
            else if(ultTimer==1){
                setLocation(getX(),getY()+30);
                attack();
                --ultTimer;
            }
            if(ultTimer<1){
                usingUltimate=false;
                damage=5;
            }
        }
    }
}