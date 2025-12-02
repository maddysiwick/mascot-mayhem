import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gnu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gnu extends Player
{
    private boolean clone;
    public Gnu(boolean playerOne,boolean aiControlled,int aiDifficulty,boolean clone,boolean campaign,int currentLevel,String saveName)    
    {
        super(playerOne,aiControlled,aiDifficulty,"gnuTEMP.png","gnuHeadbuttTEMP.png",campaign,currentLevel,saveName);
        baseSprite="gnuTEMP.png";
        hitImage="images/gnuHeadbuttTEMP.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=120;
        damage=10;
        characterReference=5;
        this.clone=clone;
        if(clone){
            hitPoints=20;
            damage=3;
            baseSprite="gnuCloneTEMP.png";//make this a slightly altered version of the base one once we have the sprites
            ultTimer=500;
        }
        speedMultiplier=4;
        cooldownMaximum=20;
        bio="\n                            Gnu: \n      A strongheaded, mildly magical, \nwildebeest, with a burning rivalry with \n  Tux. Attacks slowly but with large \n                         strength."; 
    }
    
    public void act()
    {
        super.act();
        if(clone){
            if(ultTimer>0){
                actionsAI("");
                --ultTimer;
            }
            if(ultTimer==0){
                getWorld().removeObject(healthBar);
                getWorld().removeObject(ultimateBar);
                getWorld().removeObject(this);
            }
        }
    }

    public void triggerUltimate()
    {
        if(ultPossible()){
            super.triggerUltimate();
            usingUltimate=true;
            getWorld().addObject(new Gnu(playerOne,true,1,true,false,0,""),getX()+getOffset(),getY());
        }
    }

    public int getOffset()
    {
        if(playerOne){
            return 100;
        }
        else{
            return -100;
        }
    }
}
