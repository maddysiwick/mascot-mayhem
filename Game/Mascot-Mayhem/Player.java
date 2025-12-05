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
    protected GreenfootImage leftHitImage;
    protected int damage;
    protected String baseSprite;
    protected GreenfootImage leftBaseSprite;
    protected String blockSprite;
    protected GreenfootImage leftBlockSprite;
    protected boolean jumping;
    private int jumpTimer;
    private boolean blocking;
    protected Bar ultimateBar = new Bar("","Ultimate%",0,100);
    protected Bar healthBar;
    protected boolean firstTime = true;
    protected boolean facingLeft;
    protected static String bio;
    protected boolean doNothing=false;
    private int p1UltimateBarPosition = 150;
    private int p2UltimateBarPosition = 1126; 
    private int p1HealthBarPosition = 175;
    private int p2HealthBarPosition = 901;
    protected int ultTimer;
    protected int speedMultiplier=5;
    protected boolean usingUltimate=false;
    protected String name;
    protected int attackCooldown;
    private PlayersManager playersManager;
    private boolean secondTime=false;
    //ai specific fields
    protected boolean aiControlled;
    protected Player player;
    private boolean beingAttacked;
    private boolean runningAway;
    private boolean moving=false;
    private int runTimer;
    private boolean willUltimateHit;
    protected int cooldownMaximum=15;
    //ai difficulty checks
    private int startMovingCheck;
    private int stopMovingCheck;
    private int defenseCheck;
    private int blockCheck;
    private int ultCheck;
    private int attackCheck;
    //player specific fields
    protected boolean playerOne;
    private String input;
    protected int damageMultiplier=1;
    protected int aiDifficulty;
    //campaign files
    protected boolean isEbodaHead;
    private boolean campaign;
    private int currentLevel;
    private String saveName;
    protected int characterReference;

    public Player(boolean playerOne,boolean aiControlled,int aiDifficulty,String baseSprite,String hitImage,boolean campaign,int currentLevel,String saveName)
    {
        this.playerOne=playerOne;
        this.aiControlled=aiControlled;
        this.aiDifficulty=aiDifficulty;
        this.campaign=campaign;
        this.currentLevel=currentLevel;
        this.characterReference=characterReference;
        this.saveName=saveName;
        isEbodaHead=false;
        blockSprite="block.png";
        if(playerOne){
            ultimateBarPosition=p1UltimateBarPosition;
            healthBarPosition=p1HealthBarPosition;
        }
        else{
            ultimateBarPosition=p2UltimateBarPosition;
            healthBarPosition=p2HealthBarPosition;
        }
        leftBaseSprite=createLeftSprite(baseSprite);
        leftBlockSprite=createLeftSprite(blockSprite);
        leftHitImage=createLeftSprite(hitImage);
        if(!playerOne){
            facingLeft=true;
            setSprite(leftBaseSprite,baseSprite);
        }
        else{
            facingLeft=false;
        }
    }
    
    public void act()
    {
        if(!aiControlled){
            getPlayerInput();
            jumping();
            useUltimate();
        }
        --attackCooldown;
        int debugPos;
        if(playerOne) debugPos=50;
        else debugPos=1100;
        //getWorld().showText("debug: \n ult time: " + ultTimer + "\n damage: " + damage + "\n speed: " + speedMultiplier + "\n using ultimate: " + usingUltimate + "\n attack cooldown: " + attackCooldown + "\n health: " + hitPoints, debugPos, 125);
    }
    
    public void actions()
    {
    
        if(!doNothing && input!=null){
            setUp();
            getActions();
            chargeUltimate();
            jumping();
        }
    }

    public void actionsAI(String playerAction)
    {
        setUp();
        chargeUltimate();
        int roll = Greenfoot.getRandomNumber(100);
        jumping();
        if(runningAway){
            runAway();
        }
        if(moving){
            moveAI();
        }
        if(roll<stopMovingCheck&&moving){
            moving=false;
        }
        else if(roll<startMovingCheck&&!moving&&getOneIntersectingObject(Actor.class)==null){
            moving=true;
        }
        if((playerAction=="attack"&&Greenfoot.getRandomNumber(100)<defenseCheck&&(getOneIntersectingObject(Player.class)!=null))||getOneIntersectingObject(AddedImage.class)!=null){
            if(roll<blockCheck){
                block();
            }
            else if(!runningAway){
                runTimer=30;
                runningAway=true;
                speedMultiplier=speedMultiplier*-1;
            }
        }
        else if(roll<ultCheck&&willUltimateHit&&ultPossible()){
            triggerUltimate();
        }
        else if(roll<attackCheck&&getOneIntersectingObject(Actor.class)!=null){
            attack();
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
            damage*=damageMultiplier;
            hitPoints-=damage;
            healthBar.subtract(damage);
        }
        if (hitPoints<1){
            die();
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
        healthBar = new Bar("","Health%",hitPoints,hitPoints);
        getWorld().addObject(healthBar,healthBarPosition+100,50);
        healthBar.setBarHeight(50);
        healthBar.setBarWidth(500);
        healthBar.setShowTextualUnits(false);
        
    }
    
    private void block()
    {
        blocking=true;
        setSprite(leftBlockSprite,blockSprite);
    }
    
    protected void attack()
    {
        unblock();
        if(attackCooldown<=0){
            attackCooldown=cooldownMaximum;
            setSprite(leftHitImage, hitImage);
            Actor victim = getOneIntersectingObject(Player.class);
            Player jumpee = (Player) victim;
            if(victim!=null){
                jumpee.takeHit(damage*damageMultiplier);
            }
            Greenfoot.delay(10);
            setSprite(leftBaseSprite,baseSprite);
        }
    }
    
    protected void jump()
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
        move(1*speedMultiplier);
        if(facingLeft){
            facingLeft=false;
            setSprite(leftBaseSprite,baseSprite);
        }
    }
    
    private void moveLeft()
    {
        unblock();
        move(-1*speedMultiplier);
        if(!facingLeft){
            facingLeft=true;
            setSprite(leftBaseSprite,baseSprite);
        }
    }
    
    private void crouch()
    {
        unblock();
    }
    
    protected void jumping(){
        if(jumping){
            if(jumpTimer<50){
                setLocation(getX(),getY()-6);
            }
            else if(jumpTimer<100){
                setLocation(getX(),getY()+6);
            }
            else{
                jumping=false;
            }
            jumpTimer++;
        }
    }
    protected void unblock(){
        if(blocking){
            blocking=false;
            setSprite(leftBaseSprite,baseSprite);
        }
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

    public void moveAI()
    {
        unblock();
        if(player.getX()>getX()){
            moveRight();
        }
        else if(player.getX()<getX()){
            moveLeft();
        }
        if((player.getJumping()&&Greenfoot.getRandomNumber(100)<2)||Greenfoot.getRandomNumber(100)<1){
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
        ((Arena)getWorld()).getQueue().flagDeadCharacter();
        if(!campaign){
            Greenfoot.setWorld(new WinScreen(this));
        }
        else{
            CampaignProgressManager manager=new CampaignProgressManager(saveName);
            if(playerOne){
                Greenfoot.setWorld(new WinScreen(player));
            }
            else{
                manager.nextLevel();
            }
        }
    }

    public void setUpAI()
    {
        switch(aiDifficulty){
            case 0:
                startMovingCheck=3;
                stopMovingCheck=4;
                defenseCheck=30;
                blockCheck=15;
                ultCheck=25;
                attackCheck=40;
                break;
            case 1:
                startMovingCheck=6;
                stopMovingCheck=2;
                defenseCheck=60;
                blockCheck=30;
                ultCheck=50;
                attackCheck=70;
                break;
            case 2:
                startMovingCheck=8;
                stopMovingCheck=1;
                defenseCheck=70;
                blockCheck=50;
                ultCheck=70;
                attackCheck=90;
                break;
        }
    }

    public void setUp()
    {
        if(firstTime){
            playersManager = new PlayersManager();
            getWorld().addObject(playersManager,0,0);
            player=playersManager.getOppositePlayer(playerOne);
            initializeUltimateBar();
            initializeHealthBar();
            if(aiControlled){
                setUpAI();
            }
            firstTime=false;
        }   
    }
    protected void setSprite(GreenfootImage leftSprite,String rightSprite){
        if(facingLeft){
            setImage(leftSprite);
        }
        else{
            setImage(rightSprite);
        }
    }
    protected GreenfootImage createLeftSprite(String filename){
        GreenfootImage sprite = new GreenfootImage(filename);
        sprite.mirrorHorizontally();
        return sprite;
    } 

    protected boolean getPlayerOneStatus()
    {
        return playerOne;
    }
    public boolean getIsEbodaHead(){
        return isEbodaHead;
    }

    public Player getPlayer()
    {
        return player;
    }
    
    public int getHitPoints()
    {
        return hitPoints;
    }
    
    public String getSprite()
    {
        return baseSprite;
    }
}