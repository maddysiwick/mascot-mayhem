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
        hitPoints=100;
        damage=5;
        characterReference=5;
        this.clone=clone;
        if(clone){
            hitPoints=20;
            damage=3;
            baseSprite="gnuCloneTEMP.png";//make this a slightly altered version of the base one once we have the sprites
            ultTimer=500;
        }
        bio=""; 
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
