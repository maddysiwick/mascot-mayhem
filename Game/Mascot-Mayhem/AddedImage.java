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
    
    public AddedImage(int time,boolean immortal)
    {
        time=lifespan;
        this.immortal=immortal;
    }
    
    public void act()
    {
        if(!immortal){
            if(lifespan>0){
                --lifespan;
            }
            if(lifespan==0){
                getWorld().removeObject(this);
            }
        }
        //getWorld().showText("lifespan: " + lifespan,getWorld().getWidth()/2,100);
    }
}
