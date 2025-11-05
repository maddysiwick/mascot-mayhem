import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Framework for player characters
 * 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int health; 
    protected boolean facingLeft;
    public void act()
    {
        getActions();
    }
    
    public void getActions()
    {
        //i just needed this to compile
        switch(getPlayerInput(1)){
            case "ultimate":
                break;
            case "attack":
                break;
            case "up":
                break;
            case "right":
                break;
            case "left":
                break;
            case "crouch":
                break;
        }
    }
    
    public String getPlayerInput(int player) // this prob isnt right but it wasnt compiling
    {
        if(player==1){//dummy varuiable for a player select later on
            return InputManager.getPlayerOneInput();
        }
        else{
            return InputManager.getPlayerTwoInput();
        }
    }
    public void badMove(String left, String right){
        if(Greenfoot.isKeyDown(left)){
            move(-4);
            
        }
        if(Greenfoot.isKeyDown(right)){
            move(4);
        }
    }
    public void takeHit(int damage){
        health-=damage;
        if (health<1){
            getWorld().removeObject(this);
        }
    }
    public void badHit(String hitImage,String hit,int damage,String baseSprite){
        if(Greenfoot.isKeyDown(hit)){
            setImage(hitImage);
            Actor victim = getOneIntersectingObject(Player.class);
            Player jumpee = (Player) victim;
            if(victim!=null){
                jumpee.takeHit(damage);
            }
            Greenfoot.delay(10);
            setImage(baseSprite);
        }   
    }
}
