import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Framework for player characters
 * 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int ultimateCharge=0;
    private int ultimateMax=100;
    //private int range; maybe not needed anymore
    protected boolean playerOne;
    private int barPosition;
    private int hitPoints;
    private String hitImage;
    private int damage;
    private String baseSprite;
    private Bar ultimateBar;
    private Bar healthBar;
    
    public Player()
    {
        
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
        ultimateBar=new Bar("Ultimate","%",0,100);
        ultimateBar.setBackgroundColor(Color.BLACK);
        
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