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
            saveFileWriter=new FileWriter(saveName,true);
            System.out.println("saveFileWriter created");
            saveFileWriter.write(playerCharacter + ";");
            System.out.println("character written");
            saveFileWriter.write("0;");
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
        }
        catch(FileNotFoundException e){
            System.out.println("exception occured: file not found");
        }
        saveFileReader.useDelimiter(";");
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
            case "0":
                currentLevel=0;
                break;
            case "1":
                currentLevel=1;
                break;
            case "2":
                currentLevel=2;
                break;
            case "3":
                currentLevel=3;
                break;
            case "4":
                currentLevel=4;
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
            saveFileWriter=new FileWriter(saveName,true);
            saveFileWriter.write(level+";");
            saveFileWriter.close();
            ++currentLevel;
        }
        catch(IOException e){
            System.out.println("An exception occured.");
            e.printStackTrace();
        }
    }

    public void nextLevel()
    {
        completeLevel(currentLevel);
        switch(currentLevel){
            case 1:
                Greenfoot.setWorld(new Arena(playerCharacter,4,true,1));
            case 2:
                Greenfoot.setWorld(new Arena(playerCharacter,4,true,2));
            case 3:
        }
    }

    public void start()
    {
        Greenfoot.setWorld(new Arena(playerCharacter,4,true,0));
    }
}
