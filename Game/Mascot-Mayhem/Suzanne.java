import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Suzanne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Suzanne extends Player
{
    public Suzanne(boolean playerOne,boolean aiControlled,int aiDifficulty,boolean campaign,int currentLevel,String saveName)
    {
        super(playerOne,aiControlled,aiDifficulty,"lemur.png","suzannePunchTemp.png",campaign,currentLevel,saveName);
        baseSprite="lemur.png";
        hitImage="images/suzannePunchTemp.png"; 
        hitPoints=100;
        damage=7;
        cooldownMaximum=12;
        characterReference=7;
        name="Suzanne";
        bio="\n                          Suzanne: \nA mischief-loving monkey who stabs \n  people with her surprisingly sharp \n                          bananas.";
    }
    
    public void act()
    {
        super.act();
    }

    public void triggerUltimate()
    {
        if(ultPossible()){
            super.triggerUltimate();
            speedMultiplier=3;
            ultTimer=30;
            usingUltimate=true;
            damage=2;
            setImage("images/suzanne_ult_TEMP.png");
            unblock();
        }
    }

    public void useUltimate()
    {
        if(usingUltimate){
            Actor victim = getOneIntersectingObject(Player.class);
            Player jumpee = (Player) victim;
            if(victim!=null){
                jumpee.takeHit(damage);
            }
            Greenfoot.delay(10);
            --ultTimer;
            if(ultTimer==0){
                usingUltimate=false;
                hitImage="images/suzannePunchTemp.png";
                speedMultiplier=5;
                damage=5;
            }
        }
    }
}
