import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Keith here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Keith extends Player
{    
    public Keith(boolean playerOne,boolean aiControlled,int aiDifficulty)
    {
        super(playerOne,aiControlled,aiDifficulty,"mouse.png");
        baseSprite="mouse.png";
        hitImage="images/keithHitTEMP.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=100;
        damage=5;
        bio="Keith \n A rat who’s obese, diseased, and missing a leg. Despite this he’s loved by everyone, but has an especially close relationship with Duke. Holds a hidden power which even he is unaware of. Extremely fast.";
    }
    
    public void act()
    {
        super.act();
    }
    
    public void triggerUltimate(){
        if(ultPossible()){
            super.triggerUltimate();
            damageMultiplier=3;
            usingUltimate=true;
            ultTimer=200;
            speedMultiplier=10;
            baseSprite="keith.png";
        }
    }
    
    public void useUltimate(){
        ultTimer--;
        if(ultTimer==0){
            damageMultiplier=1;
            usingUltimate=false;
            baseSprite="mouse.png";
            speedMultiplier=5;
        }
    }
}
