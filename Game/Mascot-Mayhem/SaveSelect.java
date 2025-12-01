import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SaveSelect extends World
{
    private int selectedSave;
    private int playerCharacter;

    public SaveSelect(int playerCharacter)
    {    
        super(1280, 720, 1); 
        this.playerCharacter=playerCharacter;
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
            if(selectedSave==2){
                selectedSave=0;
            }
            else{
                ++selectedSave;
            }
        }
        if(InputManager.getPlayerOneInput(false)=="left"){
            if(selectedSave==0){
                selectedSave=2;
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
            CampaignProgressManager manager = new CampaignProgressManager(getSaveName(selectedSave),playerCharacter);
            manager.start();
        }
    }

    public String getSaveName(int save)
    {
        switch(save){
            case 0:
                return "save1.txt";
            case 1:
                return "save2.txt";
            case 3:
                return "save3.txt";
        }
        return "";
    }
}
