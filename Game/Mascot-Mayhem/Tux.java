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
    public Tux(boolean playerOne,boolean aiControlled,int aiDifficulty)
    {
        super(playerOne,aiControlled,aiDifficulty);
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
        if(firstTime){//this is the same code as for the ai, but tux always needs to do it for his ult
            List players = getWorld().getObjects(Player.class);
            player=(Player)players.get(1);
        }
        getWorld().showText("height: " + getY(),50, 250);
    }

    public void triggerUltimate()
    {
        if(ultPossible()){
            super.triggerUltimate();
            damage=35;
            usingUltimate=true;
            ultTimer=60;
            doNothing=true;
        }
    }

    public void useUltimate()
    {
        if(usingUltimate){
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
            else if(ultTimer<30&&ultTimer>=13){
                setLocation(getX(),getY()+10);
                --ultTimer;
            }
            else if(ultTimer==12){
                setLocation(getX(),getY()+10);
                attack();
                usingUltimate=false;
                damage=5;
                doNothing=false;
            }
        }
    }
}