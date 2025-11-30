import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private AddedImage aiIndicator=new AddedImage(999999999,true);
    private AddedImage aiDifficultyIndicator=new AddedImage(999999999,true);
    private AddedImage p1bioDisplay=new AddedImage(999999999,true);
    private AddedImage p2bioDisplay=new AddedImage(999999999,true);
    private GreenfootImage aiControlled=new GreenfootImage(1280,720);
    private GreenfootImage playerControlled=new GreenfootImage(1280,720);
    private GreenfootImage easyAI=new GreenfootImage(1280,720);
    private GreenfootImage mediumAI=new GreenfootImage(1280,720);
    private GreenfootImage hardAI=new GreenfootImage(1280,720);
    private int p1PreviousSelection;
    private int p2PreviousSelection;
    private String dukeBio;
    private String gnuBio;
    private String keithBio;
    private String suzanneBio;
    private String tuxBio;
    private String wilburBio;
    
    
    public CharacterSelect()
    {    
        super(1280, 720, 1); 
        draw();
        drawOptions();
        initializeSelectors();
        setUpAiIndicator();
        setUpBios();
        manageBios(true);
    }

    public void act()
    {
        moveOn();
        manageSelections();
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
        tuxBio=tux.getBio();
        Suzanne suzanne = new Suzanne(true,false,0);
        addObject(suzanne,getWidth()/2+160,575);
        suzanne.makeStatic();
        suzanneBio=suzanne.getBio();
        Keith keith = new Keith(true,false,0);
        addObject(keith,getWidth()/2-290,550);
        keith.makeStatic();
        keithBio=keith.getBio();
        Duke duke = new Duke(true,false,0);
        addObject(duke,getWidth()/2+290,550);
        duke.makeStatic();
<<<<<<< Updated upstream
        Gnu gnu=new Gnu(true,false,0,false);
        addObject(gnu,getWidth()+410,525);
        gnu.makeStatic();
=======
        dukeBio=duke.getBio();
        wilburBio="";
        gnuBio="";
>>>>>>> Stashed changes
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
        p1PreviousSelection=p1Selection;
        p2PreviousSelection=p2Selection;
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
        moveSelectors();
        manageBios(false);
    }

    public void moveOn()
    {
        if(p1Confirmed&&p2Confirmed&&(InputManager.getPlayerOneInput(false)=="ultimate"||InputManager.getPlayerTwoInput(false)=="ultimate")){
            Greenfoot.setWorld(new Arena(p1Selection,p2Selection,withAi,aiDifficulty));
        }
    }

    public String getBio(int selection)
    {
        switch(selection){
            case 1:
                return wilburBio;
            case 2:
                return keithBio;
            case 3:
                return tuxBio;
            case 4:
                return "Selects a random character!";
            case 5:
                return gnuBio;
            case 6:
                return dukeBio;
            case 7:
                return suzanneBio;
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
            aiIndicator.setImage(aiControlled);
        }
        else{
            aiIndicator.setImage(playerControlled);
        }
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
            aiDifficultyIndicator.setImage(getDifficultyImage());
        }
        else{
            aiDifficultyIndicator.setImage(new GreenfootImage(5,5));
        }
    }
    public GreenfootImage getDifficultyImage()
    {
        switch(aiDifficulty){
            case 0:
                return easyAI;
            case 1:
                return mediumAI;
            case 2:
                return hardAI;
        }
        return aiControlled;
    }
    public void setUpAiIndicator()
    {
        addObject(aiIndicator,getWidth()/2,getHeight()/2);
        Color grey = new Color(68,68,68);
        aiControlled.setFont(new Font(true,true,25));
        aiControlled.setColor(grey);
        aiControlled.drawString("(AI controlled)",1087,75);
        playerControlled.setFont(new Font(true,true,25));
        playerControlled.setColor(grey);
        playerControlled.drawString("(Player controlled)",1038,75);
        addObject(aiDifficultyIndicator,getWidth()/2,getHeight()/2);
        Font difficultyFont = new Font(false,true,20);
        Color lightGrey = new Color(90,90,90);
        easyAI.setFont(difficultyFont);
        easyAI.setColor(lightGrey);
        easyAI.drawString("AI difficulty: \n      easy :)",1145,100);
        mediumAI.setFont(difficultyFont);
        mediumAI.setColor(lightGrey);
        mediumAI.drawString("AI difficulty: \n  medium :|",1145,100);
        hardAI.setFont(difficultyFont);
        hardAI.setColor(lightGrey);
        hardAI.drawString("AI difficulty: \n      hard :<",1145,100);
    }

    public void setUpBios()
    {
        addObject(p1bioDisplay,getWidth()/2,getHeight()/2);
        addObject(p2bioDisplay,getWidth()/2,getHeight()/2);
        Color rust = new Color(171,69,0);
        Font bioFont = new Font(false,false,12);
        bioP1.setFont(bioFont);
        bioP1.setColor(rust);
        bioP2.setFont(bioFont);
        bioP2.setColor(rust);
    }

    public void manageBios(boolean firstTime)
    {
        if(p1PreviousSelection!=p1Selection||firstTime){ 
            bioP1.clear();
            bioP1.drawString(getBio(p1Selection),50,350);
            p1bioDisplay.setImage(bioP1);
        }
        if(p2PreviousSelection!=p2Selection||firstTime){ 
            bioP2.clear();
            bioP2.drawString(getBio(p2Selection),1100,350);
            p2bioDisplay.setImage(bioP2);
        }
    }
}