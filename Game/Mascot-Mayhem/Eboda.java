import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Eboda extends Actor
{
    private int healthBarPosition;
    private int hitPoints=300;
    private Bar healthBar;
    private boolean hitTaken=false;
    private boolean firstTime=true;
    private boolean blocking;

    public Eboda()
    {
        activateVulnerability();
    }

    public void act()
    {
        hitAnimation();
        if(firstTime){
            firstTime=false;
            initializeHealthBar();
        }
    }

    public void activateVulnerability()
    {
        blocking=true;
        setImage("ebodaVulnerable.png");
    }

    public void deActivateVulnerability()
    {
        blocking=false;
        setImage("ebodaStrong.png");
    }

    public void initializeHealthBar()
    {
        healthBar=new Bar("","",hitPoints,hitPoints);
        healthBar.setBarHeight(75);
        healthBar.setBarWidth(1000);
        healthBar.setShowTextualUnits(false);
        healthBar.setSafeColor(new Color(160,0,25));
        getWorld().addObject(healthBar,getWorld().getWidth()/2,650);
    }

    public void takeHit(int damage)
    {
        hitTaken=true;
        if(blocking){
            hitPoints-=(damage/4);
            healthBar.subtract(damage/4);
        }
        else{
            hitPoints-=damage;
            healthBar.subtract(damage);
        }
        if (hitPoints<1){
            die();
        }
    }

    public void die()
    {
        getWorld().showText("You win! :) CLOUD is saved!",getWidth()/2,getHeight()/2);
    }

    public void hitAnimation()
    {
        --animationTimer;
        if(hitTaken&&animationTimer!=0){
            animationTimer=60;
            if(blocking){
                setImage("ebodaWeakHit.png");
            }
            else{
                setImage("ebodaStrongHit.png");
            }
            hitTaken=false;
        }
        if(animationTimer<=0){
            if(blocking){
                setImage("ebodaStrong.png");
            }
            else{
                setImage("ebodaVulnerable.png");
            }
        }
    }
}
