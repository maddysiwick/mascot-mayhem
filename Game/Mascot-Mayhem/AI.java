import greenfoot.*;  
import java.util.*;

/**
 * Bones for differnet AI levels
 * 
 * @version (4/11/2025)
 */
public class AI extends Actor
{
    private int distanceToPlayer;
    private int chanceToBlock;
    private boolean willAttackHit;
    private boolean willUltimateHit;
    private int range;
    private boolean beingAttacked;
    private boolean firstTime=true;
    private Player player;
    private boolean jumping;
    private int jumpTimer;
    private String baseSprite="wombat.png";
    private int damage=1;
    private String hitImage=
    
    //https://gamedev.stackexchange.com/questions/203272/how-should-i-design-my-ai-in-2d-fighting-game
    
    public AI()
    {
        
    }
    
    /**
     * Act - do whatever the AI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(firstTime){
            List players = getWorld().getObjects(Player.class);
            player=(Player)players.get(0);
            firstTime=false;
        }
        updateVariables();
        move();
        jumping();
        if(beingAttacked){
            block();
        }
        else if(willUltimateHit){
            triggerUltimate();
        }
        else{
            attack();
        }
    }
    
    public void move()
    {
        if(player.getX()>getX()){
            move(5);
        }
        else if(player.getX()<getX()){
            move(-5);
        }
        if(player.getJumping()){
            {
                if(!jumping){
                    jumping = true;
                    jumpTimer = 0;
                }
            }
        }
    }
    
    public void updateVariables()
    {
        
    }
    
    public void block()
    {
        
    }
    
    public void triggerUltimate()
    {
        
    }
    
    public void attack()
    {
        setImage(hitImage);
        Actor victim = getOneIntersectingObject(Player.class);
        Player jumpee = (Player) victim;
        if(victim!=null){
            jumpee.takeHit(damage);
        }
        Greenfoot.delay(10);
        setImage(baseSprite);
    }
    
    protected void jumping(){
        if(jumping){
            if(jumpTimer<30){
                setLocation(getX(),getY()-6);
            }
            else if(jumpTimer<60){
                setLocation(getX(),getY()+6);
            }
            else{
                jumping=false;
            }
            jumpTimer++;
        }
    }
}
