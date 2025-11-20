import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WinScreen extends World
{
    public WinScreen(Player loser)
    {    
        super(1280, 720, 1); 
        showText("Loser:",getWidth()/2,35);
        showText(loser.getBio(),getWidth()/2,70);
    }
}
