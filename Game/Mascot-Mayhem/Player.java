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
    private int ultimateBarPosition;
    private int healthBarPosition;
    protected int hitPoints;
    protected String hitImage;
    protected int damage;
    protected String baseSprite;
    private boolean jumping;
    private int jumpTimer;
    private boolean blocking;
    private Bar ultimateBar = new Bar("","Ultimate%",0,100);
    private Bar healthBar = new Bar("","Health%",10,10);
    private boolean firstTime = true;
    protected boolean facingLeft;
    protected String bio;
    private boolean doNothing=false;
    
    public Player(/*boolean playerOne*/)
    {
        //this.playerOne=playerOne;
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        
    }
    
    /**
     *  Method to be called on by the individual characters to act
     */
    public void actions()
    {
        if(!doNothing){
            getActions();
            chargeUltimate();
            if(firstTime){
                firstTime=false;
                initializeUltimateBar();
                initializeHealthBar();
            }
        }
    }
    
    public void setCharacteristics(boolean playerOne, int ultimateBarPosition, int healthBarPosition)
    {
        this.playerOne=playerOne;
        this.ultimateBarPosition=ultimateBarPosition;
        this.healthBarPosition=healthBarPosition;
        this.hitPoints=hitPoints;
        this.hitImage=hitImage;
        this.damage=damage;
        this.baseSprite=baseSprite;
    }
    
    public void getActions()
    {
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
                ultimateBar.add(1);
            }
        }
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
        if(blocking){
            hitPoints-=(damage/2);
            healthBar.subtract(damage/2);
        }
        else{
            hitPoints-=damage;
            healthBar.subtract(damage);
        }
        if (hitPoints<1){
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
            ultimateBar.setValue(0);
        }
    }
    
    private void initializeUltimateBar()
    {
        getWorld().addObject(ultimateBar,ultimateBarPosition,100);
        ultimateBar.setBarHeight(25);
        ultimateBar.setBarWidth(250);
        ultimateBar.setBreakValue(99);
        Color uncharged = new Color(84,241,232);
        ultimateBar.setDangerColor(uncharged);
        Color charged = new Color(253,208,45);
        ultimateBar.setSafeColor(charged);
        ultimateBar.setShowTextualUnits(false);
    }
    
    private void initializeHealthBar()
    {
        getWorld().addObject(healthBar,healthBarPosition+100,50);
        healthBar.setBarHeight(50);
        healthBar.setBarWidth(500);
        /*
        ultimateBar.setBreakValue(20);
        Color uncharged = new Color(84,241,232);
        ultimateBar.setDangerColor(uncharged);
        Color charged = new Color(253,208,45);
        ultimateBar.setSafeColor(charged);
        */
        healthBar.setShowTextualUnits(false);
        
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

    public void makeStatic()
    {
        doNothing=true;
    }

    public String getBio()
    {
        return bio;
    }
}