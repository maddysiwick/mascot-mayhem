import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Framework for player characters
 * 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    public int ultimateCharge;
    public int ultimateMax;
    private int range;
    public int player;
    public int barPosition;
    public int hitPoints;
    
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
        if(player==1){//dummy variable for a player select later on
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
    
    //ability methods:
    
    private void triggerUltimate()
    {
        if(ultimateCharge>=ultimateMax){
            //code to do ultimate
            ultimateCharge=0;
            turn(50);
        }
    }
    
    private void block()
    {
        
    }
    
    private void attack()
    {
        
    }
    
    private void jump()
    {
        
    }
    
    private void moveRight()
    {
        move(-5);
    }
    
    private void moveLeft()
    {
        move(5);
    }
    
    private void crouch()
    {
        
    }

}