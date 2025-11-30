import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AddedImage extends Actor
{
    private int lifespan;
    private boolean immortal;
    private boolean damaging;
    
    public AddedImage(int lifespan,boolean immortal,boolean damaging)
    {
        this.lifespan=lifespan;
        this.immortal=immortal;
        this.damaging=damaging;
        //if(damaging) life
    }
    
    public void act()
    {
        getWorld().showText("lifespan: " + lifespan,getWorld().getWidth()/2,100);
        if(damaging){
            Actor victim = getOneIntersectingObject(Player.class);
            Player jumpee = (Player) victim;
            if(victim!=null){
                jumpee.takeHit(1);
            }
            Greenfoot.delay(10);
        }
        if(!immortal){
            if(lifespan>0){
                --lifespan;
            }
            if(lifespan==0){
                getWorld().removeObject(this);
            }
        }
    }
}