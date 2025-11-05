import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Suzanne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Suzanne extends Player
{
    /**
     * Act - do whatever the Suzanne wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Suzanne(){
        health=10;
    }
    public void act()
    {
        badHit("images/suzannePunchTemp.png","control",5,"lemur.png");
        badMove("a","d");
    }
}
