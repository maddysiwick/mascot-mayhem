import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Suzanne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Suzanne extends Player
{
    public Suzanne(boolean playerOne,boolean aiControlled)
    {
        super(playerOne,aiControlled);
        baseSprite="lemur.png";
        hitImage="images/suzannePunchTemp.png";
        hitPoints=100;
        damage=5;
        bio="Suzanne: \n A mischief-loving monkey who stabs people with her surprisingly sharp bananas.";
    }
    
    public void act()
    {
        super.act();
    }

    public void triggerUltimate()
    {
        if(ultPossible()){
            super.triggerUltimate();
            speedMultiplier=10;
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
                speedMultiplier=3;
                damage=5;
            }
        }
    }
}
