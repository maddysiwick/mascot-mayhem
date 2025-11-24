import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CharacterSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharacterSelect extends World
{
    private GreenfootImage bg = getBackground();
    private int p1Selection = 3;
    private int p2Selection = 5;
    private Selector selector1 = new Selector();
    private Selector selector2 = new Selector();
    private boolean p1Confirmed=false;
    private boolean p2Confirmed=false;
    private boolean withAi=true;
    private int aiDifficulty=0;
    private GreenfootImage bioP1=new GreenfootImage(1280,720);
    private GreenfootImage bioP2=new GreenfootImage(1280,720);
    
    public CharacterSelect()
    {    
        super(1280, 720, 1); 
        draw();
        drawOptions();
        initializeSelectors();
    }

    public void act()
    {
        moveOn();
        manageSelections();
        showBios();
        manageAI();
    }

    public void draw()
    {
        bg.setColor(Color.BLACK);
        bg.setFont(new Font(true, true, 36));
        bg.drawString("PLAYER ONE", 15, 50);
        bg.drawString("PLAYER TWO", 1015, 50);
        //random selector box
        int[] xPoints1={(getWidth()/2-75),(getWidth()/2+75),(getWidth()/2+120),(getWidth()/2-120)};
        int[] yPoints1={650,650,550,550};
        bg.fillPolygon(xPoints1,yPoints1,4);
        //left 1 box (option 3)
        int[] xPoints2={(getWidth()/2-95),(getWidth()/2-200),(getWidth()/2-245),(getWidth()/2-140)};
        int[] yPoints2={625,625,525,525};
        bg.fillPolygon(xPoints2,yPoints2,4);
        //right 1 box (option 4)
        int[] xPoints3={(getWidth()/2+95),(getWidth()/2+200),(getWidth()/2+245),(getWidth()/2+140)};
        int[] yPoints3={625,625,525,525};
        bg.fillPolygon(xPoints3,yPoints3,4);
        //left 2 box (option 2)
        int[] xPoints4={(getWidth()/2-220),(getWidth()/2-325),(getWidth()/2-370),(getWidth()/2-265)};
        int[] yPoints4={600,600,500,500};
        bg.fillPolygon(xPoints4,yPoints4,4);
        //right 2 box (option 5)
        int[] xPoints5={(getWidth()/2+220),(getWidth()/2+325),(getWidth()/2+370),(getWidth()/2+265)};
        int[] yPoints5={600,600,500,500};
        bg.fillPolygon(xPoints5,yPoints5,4);
        //left 3 box (option 1)
        int[] xPoints6={(getWidth()/2-345),(getWidth()/2-450),(getWidth()/2-495),(getWidth()/2-390)};
        int[] yPoints6={575,575,475,475};
        bg.fillPolygon(xPoints6,yPoints6,4);
        //right 3 box (option 6)
        int[] xPoints7={(getWidth()/2+345),(getWidth()/2+450),(getWidth()/2+495),(getWidth()/2+390)};
        int[] yPoints7={575,575,475,475};
        bg.fillPolygon(xPoints7,yPoints7,4);
    }

    public void drawOptions()
    {
        bg.setColor(Color.RED);
        bg.setFont(new Font(true,false,120));
        bg.drawString("?",getWidth()/2-31,642);
        Tux tux = new Tux(true,false,0);
        addObject(tux,getWidth()/2-160,575);
        tux.makeStatic();
        Suzanne suzanne = new Suzanne(true,false,0);
        addObject(suzanne,getWidth()/2+160,575);
        suzanne.makeStatic();
        Keith keith = new Keith(true,false,0);
        addObject(keith,getWidth()/2-290,550);
        keith.makeStatic();
        Duke duke = new Duke(true,false,0);
        addObject(duke,getWidth()/2+290,550);
        duke.makeStatic();
    }

    public void moveSelectors()
    {
        switch(p1Selection){
            case 1:
                selector1.setLocation(getWidth()/2-490,480);
                break;
            case 2:
                selector1.setLocation(getWidth()/2-365,505);
                break;
            case 3:
                selector1.setLocation(getWidth()/2-240,530);
                break;
            case 4:
                selector1.setLocation(getWidth()/2-115,555);
                break;
            case 5:
                selector1.setLocation(getWidth()/2+145,530);
                break;
            case 6:
                selector1.setLocation(getWidth()/2+270,505);
                break;
            case 7:
                selector1.setLocation(getWidth()/2+395,480);
                break;
        }
        switch(p2Selection){
            case 1:
                selector2.setLocation(getWidth()/2-390,480);
                break;
            case 2:
                selector2.setLocation(getWidth()/2-265,505);
                break;
            case 3:
                selector2.setLocation(getWidth()/2-140,530);
                break;
            case 4:
                selector2.setLocation(getWidth()/2+115,555);
                break;
            case 5:
                selector2.setLocation(getWidth()/2+240,530);
                break;
            case 6:
                selector2.setLocation(getWidth()/2+365,505);
                break;
            case 7:
                selector2.setLocation(getWidth()/2+490,480);
                break;
        }
    }

    public void initializeSelectors()
    {
        addObject(selector1,(getWidth()/2-240),530);
        addObject(selector2,(getWidth()/2+240),530);
    }

    public void getSelections()
    {
        Greenfoot.delay(5);
        if(!p1Confirmed){
            switch(InputManager.getPlayerOneInput(false)){
                case "right":
                    if(p1Selection==1){
                        p1Selection=7;
                    }
                    else{
                        --p1Selection;
                    }
                    break;
                case "left":
                    if(p1Selection==7){
                        p1Selection=1;
                    }
                    else{
                        ++p1Selection;
                    }
                    break;
            }
        }
        if(!p2Confirmed){
            switch(InputManager.getPlayerTwoInput(false)){
                case "right":
                    if(p2Selection==1){
                        p2Selection=7;
                    }
                    else{
                        --p2Selection;
                    }
                    break;
                case "left":
                    if(p2Selection==7){
                        p2Selection=1;
                    }
                    else{
                        ++p2Selection;
                    }
                    break;
            }
        }
        moveSelectors();
    }

    public void confirmSelections()
    {
        if(InputManager.getPlayerOneInput(false)=="attack"){
            selector1.setImage("beeperGreen.png");
            p1Confirmed=true;
        }
        if(InputManager.getPlayerTwoInput(false)=="attack"){
            p2Confirmed=true;
            selector2.setImage("beeperGreen.png");
        }
    }

    public void unConfirmSelections()
    {
        if(InputManager.getPlayerOneInput(false)=="block"){
            selector1.setImage("beeper.png");
            p1Confirmed=false;
        }
        if(InputManager.getPlayerTwoInput(false)=="block"){
            selector2.setImage("beeper.png");
            p2Confirmed=false;
        }
    }

    public void manageSelections()
    {
        getSelections();
        confirmSelections();
        unConfirmSelections();
    }

    public void moveOn()
    {
        if(p1Confirmed&&p2Confirmed&&(InputManager.getPlayerOneInput(false)=="ultimate"||InputManager.getPlayerTwoInput(false)=="ultimate")){
            Greenfoot.setWorld(new Arena(p1Selection,p2Selection,withAi,aiDifficulty));
        }
    }

    public void showBios()
    {
        
    }

    public String getBio(int selection)
    {
        switch(selection){
            case 1:
                return Wilbur.bio;
            case 2:
                return Keith.bio;
            case 3:
                return Tux.bio;
            case 4:
                return "Selects a random character!";
            case 5:
                return Gnu.bio;
            case 6:
                return Duke.bio;
            case 7:
                return Suzanne.bio;
        }
        return null;
    }

    public void manageAI()
    {
        Greenfoot.delay(5);
        if(Greenfoot.isKeyDown("space")){
            withAi=!withAi;
        }
        if(withAi){
            showText("Player 2 will be the computer. Press space to play against a friend",getWidth()/2,70);
        }
        else{
            showText("Player 2 will be a friend. Press space to play against the computer",getWidth()/2,70);
        }
        showText("Use each players keys (WASD or arrows) to navigate, attack (F or /) to choose a character and Ult (G or .) to confirm",getWidth()/2,125);
        if(Greenfoot.isKeyDown("=")){
            if(aiDifficulty==2){
                aiDifficulty=0;
            }
            else{
                ++aiDifficulty;
            }
        }
        else if(Greenfoot.isKeyDown("-")){
            if(aiDifficulty==0){
                aiDifficulty=2;
            }
            else{
                --aiDifficulty;
            }
        }
        if(withAi){
            showText("Current computer difficulty: " + getDifficultyWord(),getWidth()/2,150);
        }
    }

    public String getDifficultyWord()
    {
        switch(aiDifficulty){
            case 0:
                return "Easy :)";
            case 1:
                return "Medium :|";
            case 2:
                return "Hard :<";
        }
        return "";
    }
}
