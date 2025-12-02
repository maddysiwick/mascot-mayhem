import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CharacterSelect extends World
{
    private GreenfootImage bg;
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
    private AddedImage aiIndicator=new AddedImage(999999999,true,false);
    private AddedImage aiDifficultyIndicator=new AddedImage(999999999,true,false);
    private AddedImage p1bioDisplay=new AddedImage(999999999,true,false);
    private AddedImage p2bioDisplay=new AddedImage(999999999,true,false);
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
    private boolean campaign;
    private Color grey = new Color(68,68,68);
    private Color lightGrey = new Color(90,90,90);

    
    public CharacterSelect(boolean campaign)
    {    
        super(1280, 720, 1); 
        setBackground("matrixBinaryOogaBooga.png");
        bg = getBackground();
        this.campaign=campaign;
        draw();
        drawOptions();
        initializeSelectors();
        if(!campaign)setUpAiIndicator();
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
        if(!campaign)bg.drawString("PLAYER TWO", 1015, 50);
        bg.setColor(grey);
        bg.fillOval(getWidth()/2-65,530,140,140);
        bg.fillOval(getWidth()/2-220,500,140,140);
        bg.fillOval(getWidth()/2-375,470,140,140);
        bg.fillOval(getWidth()/2-530,440,140,140);
        bg.fillOval(getWidth()/2+90,500,140,140);
        bg.fillOval(getWidth()/2+245,470,140,140);
        bg.fillOval(getWidth()/2+400,440,140,140);
    }

    public void drawOptions()
    {
        bg.setColor(Color.RED);
        bg.setFont(new Font(true,false,120));
        bg.drawString("?",getWidth()/2-31,642);
        
        Tux tux = new Tux(true,false,0,false,10,"");
        addObject(tux,getWidth()/2-160,575);
        tux.makeStatic();
        tuxBio=tux.getBio();
        Suzanne suzanne = new Suzanne(true,false,0,false,10,"");
        addObject(suzanne,getWidth()/2+160,575);
        suzanne.makeStatic();
        suzanneBio=suzanne.getBio();
        Keith keith = new Keith(true,false,0,false,10,"");
        addObject(keith,getWidth()/2-290,550);
        keith.makeStatic();
        keithBio=keith.getBio();
        keithBio=keith.getBio();
        Duke duke = new Duke(true,false,0,false,10,"");
        addObject(duke,getWidth()/2+290,550);
        duke.makeStatic();
        Gnu gnu=new Gnu(true,false,0,false,false,10,"");
        addObject(gnu,getWidth()+410,525);
        gnu.makeStatic();
        dukeBio=duke.getBio();
        wilburBio="";
        gnuBio="";
        dukeBio=duke.getBio();
        wilburBio="";
        gnuBio="";
        dukeBio=duke.getBio();
        wilburBio="";
        gnuBio="";
    }

    public void moveSelectors()
    {
        switch(p1Selection){
            case 1:
                selector1.setLocation(getWidth()/2-530,510);
                break;
            case 2:
                selector1.setLocation(getWidth()/2-375,540);
                break;
            case 3:
                selector1.setLocation(getWidth()/2-220,570);
                break;
            case 4:
                selector1.setLocation(getWidth()/2-65,600);
                break;
            case 5:
                selector1.setLocation(getWidth()/2+90,570);
                break;
            case 6:
                selector1.setLocation(getWidth()/2+245,540);
                break;
            case 7:
                selector1.setLocation(getWidth()/2+400,510);
                break;
        }
        if(!campaign){
            switch(p2Selection){
                case 1:
                    selector2.setLocation(getWidth()/2-390,510);
                    break;
                case 2:
                    selector2.setLocation(getWidth()/2-235,540);
                    break;
                case 3:
                    selector2.setLocation(getWidth()/2-80,570);
                    break;
                case 4:
                    selector2.setLocation(getWidth()/2+75,600);
                    break;
                case 5:
                    selector2.setLocation(getWidth()/2+230,570);
                    break;
                case 6:
                    selector2.setLocation(getWidth()/2+385,540);
                    break;
                case 7:
                    selector2.setLocation(getWidth()/2+540,510);
                    break;
            }
        }
    }

    public void initializeSelectors()
    {
        addObject(selector1,(getWidth()/2-220),570);
        if(!campaign)addObject(selector2,(getWidth()/2+240),530);
    }

    public void getSelections()
    {
        Greenfoot.delay(5);
        p1PreviousSelection=p1Selection;
        p2PreviousSelection=p2Selection;
        if(!p1Confirmed){
            switch(InputManager.getPlayerOneInput(false)){
                case "left":
                    if(p1Selection==1){
                        p1Selection=7;
                    }
                    else{
                        --p1Selection;
                    }
                    break;
                case "right":
                    if(p1Selection==7){
                        p1Selection=1;
                    }
                    else{
                        ++p1Selection;
                    }
                    break;
            }
        }
        if(!campaign){
            if(!p2Confirmed){
                switch(InputManager.getPlayerTwoInput(false)){
                    case "left":
                        if(p2Selection==1){
                            p2Selection=7;
                        }
                        else{
                            --p2Selection;
                        }
                        break;
                    case "right":
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
    }

    public void confirmSelections()
    {
        if(InputManager.getPlayerOneInput(false)=="attack"){
            selector1.setImage("beeperGreen.png");
            p1Confirmed=true;
        }
        if(!campaign){
            if(InputManager.getPlayerTwoInput(false)=="attack"){
                p2Confirmed=true;
                selector2.setImage("beeperGreen.png");
            }
        }
        else{
            p2Confirmed=true;
        }
    }

    public void unConfirmSelections()
    {
        if(InputManager.getPlayerOneInput(false)=="block"){
            selector1.setImage("beeper.png");
            p1Confirmed=false;
        }
        if(!campaign){
            if(InputManager.getPlayerTwoInput(false)=="block"){
                selector2.setImage("beeper.png");
                p2Confirmed=false;
            }
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
        if(p1Confirmed&&p2Confirmed&&(InputManager.getPlayerOneInput(false)=="ultimate"||InputManager.getPlayerTwoInput(false)=="ultimate")&&!campaign){
            Greenfoot.setWorld(new Arena(p1Selection,p2Selection,withAi,aiDifficulty,false,0,""));
        }
        else if(p1Confirmed&&campaign&&InputManager.getPlayerOneInput(false)=="ultimate"){
            Greenfoot.setWorld(new SaveSelect(p1Selection));
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
        aiControlled.setFont(new Font(true,true,25));
        aiControlled.setColor(grey);
        aiControlled.drawString("(AI controlled)",1087,75);
        playerControlled.setFont(new Font(true,true,25));
        playerControlled.setColor(grey);
        playerControlled.drawString("(Player controlled)",1038,75);
        addObject(aiDifficultyIndicator,getWidth()/2,getHeight()/2);
        Font difficultyFont = new Font(false,true,20);
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
        Color rust = new Color(171,69,0);
        Font bioFont = new Font(false,false,12);
        addObject(p1bioDisplay,getWidth()/2,getHeight()/2);
        bioP1.setFont(bioFont);
        bioP1.setColor(rust);
        if(!campaign){
            addObject(p2bioDisplay,getWidth()/2,getHeight()/2);
            bioP2.setFont(bioFont);
            bioP2.setColor(rust);
        }
    }

    public void manageBios(boolean firstTime)
    {
        if(p1PreviousSelection!=p1Selection||firstTime){ 
            bioP1.clear();
            bioP1.drawString(getBio(p1Selection),50,350);
            p1bioDisplay.setImage(bioP1);
        }
        if((p2PreviousSelection!=p2Selection||firstTime)&&!campaign){ 
            bioP2.clear();
            bioP2.drawString(getBio(p2Selection),1100,350);
            p2bioDisplay.setImage(bioP2);
        }
    }
}