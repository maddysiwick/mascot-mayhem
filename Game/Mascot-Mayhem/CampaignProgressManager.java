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

    public CampaignProgressManager(String saveName,int playerCharacter)
    {
        this.playerCharacter=playerCharacter;
        this.saveName=saveName;
        saveFile=new File(saveName);
        if(isNewSave()){
            makeNewSave();
        }
        openSave();
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
    }

    public void makeNewSave()
    {
        try{
            saveFileWriter=new FileWriter(saveName);
            System.out.println("saveFileWriter created");
            saveFileWriter.write(playerCharacter + ";");
            System.out.println("character written");
            saveFileWriter.write("10;");
            System.out.println("level written");
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

    public void completeLevel(int level)
    {
        if(currentLevel==4) return;
        try{
            ++currentLevel;
            saveFileWriter=new FileWriter(saveName,true);
            saveFileWriter.write(level+";");
            saveFileWriter.close();
        }
        catch(IOException e){
            System.out.println("An exception occured.");
            e.printStackTrace();
        }
    }

    public void nextLevel()
    {
        completeLevel(currentLevel);
        Greenfoot.setWorld(getLevel());
    }

    public void start()
    {
        Greenfoot.setWorld(getLevel());
    }
    
    public Arena getLevel()
    {
        switch(currentLevel){
            case 10:
                return new Arena(playerCharacter,4,true,0,true,10,saveName);
            case 11:
                return new Arena(playerCharacter,4,true,1,true,11,saveName);
            case 12:
                return new Arena(playerCharacter,4,true,2,true,12,saveName);
            case 13:
                return new Arena(playerCharacter,saveName);
            case 14:
                
        }
        return new Arena(playerCharacter,saveName);
    }
}
