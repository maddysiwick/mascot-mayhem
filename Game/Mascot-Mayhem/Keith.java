import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Keith here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Keith extends Player
{    
    public Keith(boolean playerOne,boolean aiControlled,int aiDifficulty,boolean campaign,int currentLevel,String saveName)
    {
        super(playerOne,aiControlled,aiDifficulty,"keith.png","KeithBite.png",campaign,currentLevel,saveName);
        baseSprite="keith.png";
        hitImage="images/KeithBite.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=75;
        damage=3;
        cooldownMaximum=5;
        speedMultiplier=10;
        bio="                            Keith:\n    A rat who’s obese, diseased, and \n missing a leg. Despite this he’s loved \nby everyone, but has an especially close \nrelationship with Duke. Holds a hidden \n power which even he is unaware of. \n                      Extremely fast.";
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
            speedMultiplier=20;
            baseSprite="KeithUlt.png";
            hitImage="images/KeithUltBite.png";
        }
    }
    
    public void useUltimate(){
        ultTimer--;
        if(ultTimer==0){
            damageMultiplier=1;
            usingUltimate=false;
            baseSprite="Keith.png";
             hitImage="images/KeithBite.png";
            speedMultiplier=10;
        }
    }
}
