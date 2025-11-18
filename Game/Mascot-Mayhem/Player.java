import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Framework for player characters
 * 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    //general fields
    private int ultimateCharge=0;
    private int ultimateMax=100;
    private int ultimateBarPosition;
    private int healthBarPosition;
    protected int hitPoints;
    protected String hitImage;
    protected int damage;
    protected String baseSprite;
    protected boolean jumping;
    private int jumpTimer;
    private boolean blocking;
    private Bar ultimateBar = new Bar("","Ultimate%",0,100);
    private Bar healthBar = new Bar("","Health%",100,100);
    private boolean firstTime = true;
    protected boolean facingLeft;
    protected static String bio;
    private boolean doNothing=false;
    private int p1UltimateBarPosition = 150;
    private int p2UltimateBarPosition = 1126; 
    private int p1HealthBarPosition = 175;
    private int p2HealthBarPosition = 901;
    protected int ultTimer;
    protected int speedMultiplier=5;
    protected boolean usingUltimate=false;
    protected String name;
    //ai specific fields
    protected boolean aiControlled;
    protected Player player;
    private boolean beingAttacked;
    private boolean willUltimateHit;
    private boolean runningAway;
    private int runTimer;
    //player specific fields
    protected boolean playerOne;
    private String input;
    
    public Player(boolean playerOne,boolean aiControlled)
    {
        this.playerOne=playerOne;
        this.aiControlled=aiControlled;
        if(playerOne){
            ultimateBarPosition=p1UltimateBarPosition;
            healthBarPosition=p1HealthBarPosition;
        }
        else{
            ultimateBarPosition=p2UltimateBarPosition;
            healthBarPosition=p2HealthBarPosition;
        }
    }
    
    public void act()
    {
        if(!aiControlled){
            getPlayerInput();
            jumping();
            useUltimate();
        }
    }
    
    public void actions()
    {
        if(!doNothing && input!=null){
            getActions();
            chargeUltimate();
            jumping();
            if(firstTime){
                firstTime=false;
                initializeUltimateBar();
                initializeHealthBar();
            }
        }
    }

    public void actionsAI(String playerAction)
    {
        if(firstTime){
            List players = getWorld().getObjects(Player.class);
            player=(Player)players.get(0);
            initializeUltimateBar();
            initializeHealthBar();
            firstTime=false;
        }
        updateVariablesAI();
        chargeUltimate();
        int roll = Greenfoot.getRandomNumber(100);
        if(runningAway){
            runAway();
        }
        else if(roll<60){
            if(getOneIntersectingObject(Actor.class)==null){
            moveAI();
            jumping();
        }
        else if(playerAction=="attack"){
            if(roll<30){
                block();
            }
            else{
                runningAway=true;
                speedMultiplier=speedMultiplier*-1;
            }
        }
        else if(roll<90&&willUltimateHit){
            triggerUltimate();
        }
        else if(roll<30){
            attack();
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
        if(input!="block"&&blocking){
            unblock();
        }
        switch(input){
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
    
    protected void getPlayerInput() //needed a player so the code would compile
    {
        if(playerOne){//dummy variable for a player select later on
            input = InputManager.getPlayerOneInput(this.getJumping());
        }
        else{
            input = InputManager.getPlayerTwoInput(this.getJumping());
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
    
    protected void triggerUltimate()
    {
        unblock();
        if(ultPossible()){
            ultimateCharge=0;
            ultimateBar.setValue(0);
        }
    }

    protected boolean ultPossible()
    {
        if(ultimateCharge>=ultimateMax){
            return true;
        }
        else{
            return false;
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
        healthBar.setShowTextualUnits(false);
        
    }
    
    private void block()
    {
        blocking=true;
        setImage("block.png");
    }
    
    protected void attack()
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
        move(-1*speedMultiplier);
    }
    
    private void moveLeft()
    {
        unblock();
        move(1*speedMultiplier);
    }
    
    private void crouch()
    {
        unblock();
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
    protected void unblock(){
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

    public String getInput(){
        return input;
    }

    public boolean getJumping(){
        return jumping;
    }

    public boolean getBlocking(){
        return blocking;
    }

    public void useUltimate()
    {
        //this method is blank so that the other methods know what to call, its defined properly in each character
    }

    public void updateVariablesAI()
    {
        if(player==null){
            Greenfoot.stop();
        }
    }

    public void moveAI()
    {
        unblock();
        if(player.getX()>getX()){
            move(1*speedMultiplier);
        }
        else if(player.getX()<getX()){
            move(-1*speedMultiplier);
        }
        if((player.getJumping()&&Greenfoot.getRandomNumber(100)<8)&&Greenfoot.getRandomNumber(100)<4){
            {
                if(!jumping){
                    jumping = true;
                    jumpTimer = 0;
                }
            }
        }
    }
    public boolean getAIControlled(){
        return aiControlled;
    }
    public void runAway(){
        moveAI();
        if(runTimer>0){
            runTimer--;
        }
        else{
            speedMultiplier=speedMultiplier*-1;
            runningAway=false;
        }
    }

    public void die()
    {
        Greenfoot.setWorld(new WinScreen(this));
    }
}