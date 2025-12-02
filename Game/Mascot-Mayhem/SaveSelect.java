import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SaveSelect extends World
{
    private int selectedSave=1;
    private boolean newSave;

    public SaveSelect()
    {    
        super(1280, 720, 1);
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
        showText("Save Selected: "+selectedSave,getWidth()/2,getHeight()/2);
    }

    public void start()
    {
        if(InputManager.getPlayerOneInput(false)=="attack"){
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
}
