import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SaveSelect extends World
{
    private int selectedSave=1;
    private boolean newSave;
    private AddedImage saveSelected=new AddedImage(0,true,false);
    private GreenfootSound music=new GreenfootSound("menuMusic.wav");

    public SaveSelect()
    {    
        super(1280, 720, 1);
        drawBackground();
        addObject(saveSelected,getWidth()/2,getHeight()/2);
        music.playLoop();
    }
    
    public void act()
    {
        manageSaveSelection();
        start();
    }

    public void manageSaveSelection()
    {
        Greenfoot.delay(5);
        if(InputManager.getPlayerOneInput(false)=="right"){
            if(selectedSave==3){
                selectedSave=1;
            }
            else{
                ++selectedSave;
            }
        }
        if(InputManager.getPlayerOneInput(false)=="left"){
            if(selectedSave==1){
                selectedSave=3;
            }
            else{
                --selectedSave;
            }
        }
        saveSelected.setImage(getSaveSelectedImage());
    }

    public void start()
    {
        if(InputManager.getPlayerOneInput(false)=="attack"){
            Greenfoot.playSound("nextScreen.wav");
            music.pause();
            CampaignProgressManager manager = new CampaignProgressManager(getSaveName(selectedSave));
            manager.start();
        }
    }

    public String getSaveName(int save)
    {
        switch(save){
            case 1:
                return "save1.txt";
            case 2:
                return "save2.txt";
            case 3:
                return "save3.txt";
        }
        return "";
    }
    
    public void drawBackground()
    {
        setBackground("matrixBinaryOogaBooga.png");
        GreenfootImage bg=getBackground();
        bg.setColor(Color.WHITE);
        bg.fillOval(200,25,900,150);
        bg.fillOval(422,297,450,110);
        
        bg.setFont(new Font(true,true,70));
        bg.setColor(Color.BLACK);
        bg.drawString("Select Your Save File!",260,120);
    }
    
    public GreenfootImage getSaveSelectedImage()
    {
        GreenfootImage save1=new GreenfootImage(1280,720);
        GreenfootImage save2=new GreenfootImage(1280,720);
        GreenfootImage save3=new GreenfootImage(1280,720);
        
        save1.setColor(Color.BLACK);
        save1.setFont(new Font(false,false,50));
        save1.drawString("Save 1 Selected!",450,370);
        save2.setColor(Color.BLACK);
        save2.setFont(new Font(false,false,50));
        save2.drawString("Save 2 Selected!",450,370);
        save3.setColor(Color.BLACK);
        save3.setFont(new Font(false,false,50));
        save3.drawString("Save 3 Selected!",450,370);
        
        switch(selectedSave){
            case 1:
                return save1;
            case 2:
                return save2;
            case 3:
                return save3;
        }
        return save1;
    }
}