import java.util.*;
import java.io.*;
import greenfoot.*;

public class CampaignProgressManager  
{
    private File saveFile;
    private FileWriter saveFileWriter;
    private Scanner saveFileReader;
    private int playerCharacter;
    private int currentLevel;
    private String saveFileString;
    private String saveName;
    //debug fields
    private String playerCharacterString;

    public CampaignProgressManager(String saveName)
    {
        this.saveName=saveName;
        saveFile=new File(saveName);
        if(isNewSave()){
            makeNewSave();
        }
        else{
            openSave();
        }
    }

    public boolean isNewSave()
    {
        if(saveFile.exists()){
            System.out.println("save exists");
            return false;
        }
        else{
            System.out.println("save doesnt exist");
            return true;
        }
    }

    public void openSave()
    {
        setUpReader();
        while(saveFileReader.hasNext()){
            decodeToken(saveFileReader.next());
        }
        System.out.println("save opened ("+saveName+")");
    }

    public void makeNewSave()
    {
        try{
            saveFileWriter=new FileWriter(saveName);
            System.out.println("saveFileWriter created");
            saveFileWriter.close();
            System.out.println("saveFileWriter closed");
        }
        catch(IOException e){
            System.out.println("An exception occured.");
            e.printStackTrace();
        }
    }

    public void setUpReader()
    {
        try{
            saveFileReader=new Scanner(saveFile);
            saveFileReader.useDelimiter(";");
        }
        catch(FileNotFoundException e){
            System.out.println("exception occured: file not found");
        }
    }

    public void decodeToken(String token)
    {
        switch(token){
            //character tokens
            case "6":
                playerCharacterString="Duke";
                playerCharacter=6;
                break;
            case "5":
                playerCharacterString="Gnu";
                playerCharacter=5;
                break;
            case "2":
                playerCharacterString="Keith";
                playerCharacter=2;
                break;
            case "7":
                playerCharacterString="Suzanne";
                playerCharacter=7;
                break;
            case "3":
                playerCharacterString="Tux";
                playerCharacter=3;
                break;
            case "1":
                playerCharacterString="Wilbur";
                playerCharacter=1;
                break;
            //level tokens
            case "10":
                //agaisnt easy ai
                currentLevel=10;
                break;
            case "11":
                //agaisnt medium ai
                currentLevel=11;
                break;
            case "12":
                //agaisnt hard ai
                currentLevel=12;
                break;
            case "13":
                //agaisnt eboda
                currentLevel=13;
                break;
            case "14":
                //final win screen
                currentLevel=14;
                break;
        }
        System.out.println("player character: " + playerCharacterString + " current level: " + currentLevel);
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public int getPlayerCharacter()
    {
        return playerCharacter;
    }

    public void completeLevel()
    {
        if(currentLevel==4) return;
        try{
            ++currentLevel;
            System.out.println("completeLevel(); currentLevel (post-increment): "+currentLevel);
            saveFileWriter=new FileWriter(saveName,true);
            saveFileWriter.write(currentLevel+";");
            saveFileWriter.close();
            System.out.println("completeLevel(): new level written");
        }
        catch(IOException e){
            System.out.println("An exception occured.");
            e.printStackTrace();
        }
    }

    public void nextLevel()
    {
        completeLevel();
        Greenfoot.setWorld(getLevel());
        System.out.println("new world set, level: "+currentLevel);
    }

    public void start()
    {
        File f = new File(saveName);
        if(f.length()==0){
            Greenfoot.setWorld(new CharacterSelect(true,saveName,this));
        }
        else{
            Greenfoot.setWorld(getLevel());
        }
    }
    public void writePlayer(int player){
        try{
            playerCharacter=player;
            FileWriter saveFileWriter=new FileWriter(saveName);
            saveFileWriter.write(playerCharacter + ";");
            System.out.println("character written");
            saveFileWriter.write("10;");
            System.out.println("level written");
            saveFileWriter.close();
            currentLevel=10;
        }
        catch(IOException e){
            System.out.println("An exception occured. cant write the character");
            e.printStackTrace();
        }
    }
    
    public World getLevel()
    {
        switch(currentLevel){
            case 10:
                System.out.println("level 10 selected");
                return new Arena(playerCharacter,4,true,0,true,10,saveName);
            case 11:
                System.out.println("level 11 selected");
                return new Arena(playerCharacter,4,true,1,true,11,saveName);
            case 12:
                System.out.println("level 12 selected");
                return new Arena(playerCharacter,4,true,2,true,12,saveName);
            case 13:
                return new Arena(playerCharacter,saveName);
            case 14:
                return new WinScreen(playerCharacter,true);
        }
        System.out.println("level selection error: fell out of switch-case");
        System.out.println("    currentLevel value: "+currentLevel);
        return new Arena(playerCharacter,saveName);
    }
}
