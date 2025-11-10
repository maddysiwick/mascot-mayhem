import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Framework for player characters
 * 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int ultimateCharge;
    private int ultimateMax;
    //private int range; maybe not needed anymore
    protected boolean playerOne;
    private int barPosition;
    private int hitPoints;
    private String hitImage;
    private int damage;
    private String baseSprite;
    private boolean jumping;
    private int jumpTimer;
    private boolean blocking;
    public Player()
    {
        ultimateCharge=0;
        ultimateMax=100;
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int health; 
    protected boolean facingLeft;
    public void act()
    {
        
    }
    
    /**
     *  Method to be called on by the individual characters to act
     */
    public void actions()
    {
        getActions();
        chargeUltimate();
        displayCharge();
    }
    
    public void setCharacteristics(boolean playerOne, int barPosition, int hitPoints, String hitImage, int damage, String baseSprite)
    {
        this.playerOne=playerOne;
        this.barPosition=barPosition;
        this.hitPoints=hitPoints;
        this.hitImage=hitImage;
        this.damage=damage;
        this.baseSprite=baseSprite;
    }
    
    public void getActions()
    {
        //i just needed this to compile
        switch(getPlayerInput(1)){
            case "ultimate":
                triggerUltimate();
                break;
            case "block":
                block();
                break;
            case "attack":
                attack();
                break;
            case "up":
                jump();
                break;
            case "right":
                moveRight();
                break;
            case "left":
                moveLeft();
                break;
            case "crouch":
                crouch();
                break;
        }
    }
    
    private void chargeUltimate()
    {
        if(ultimateCharge<ultimateMax){
            if(Greenfoot.getRandomNumber(100)<5){
                ++ultimateCharge;
            }
        }
    }
    
    private void displayCharge()
    {
        getWorld().showText(ultimateCharge + "/100", barPosition, 50);
    }
    
    private String getPlayerInput(int player) //needed a player so the code would compile
    {
        if(playerOne){//dummy variable for a player select later on
            return InputManager.getPlayerOneInput();
        }
        else{
            return InputManager.getPlayerTwoInput();
        }
    }
    public void takeHit(int damage){
        health-=damage;
        if (health<1){
            getWorld().removeObject(this);
        }
    }  
    
    //ability methods:
    
    private void triggerUltimate()
    {
        unblock();
        if(ultimateCharge>=ultimateMax){
            //code to do ultimate
            ultimateCharge=0;
            turn(50);
        }
    }
    
    private void block()
    {
        blocking=true;
        setImage("block.png");
    }
    
    private void attack()
    {
       unblock();
       setImage(hitImage);
            Actor victim = getOneIntersectingObject(Player.class);
            Player jumpee = (Player) victim;
            if(victim!=null){
                jumpee.takeHit(damage);
            }
            Greenfoot.delay(10);
            setImage(baseSprite);
    }
    
    private void jump()
    {
        unblock();
        if(!jumping){
            jumping = true;
            jumpTimer = 0;
        }
    }
    
    private void moveRight()
    {
        unblock();
        move(-5);
    }
    
    private void moveLeft()
    {
        unblock();
        move(5);
    }
    
    private void crouch()
    {
        unblock();
    }
    
    protected void jumping(){
        if(jumping){
            if(jumpTimer<10){
                setLocation(getX(),getY()-16);
            }
            else if(jumpTimer<30){
                setLocation(getX(),getY()+8);
            }
            else{
                jumping=false;
            }
            jumpTimer++;
        }
    }
    private void unblock(){
        blocking=false;
        setImage(baseSprite);
    }
}