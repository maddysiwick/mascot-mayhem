import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Wilbur extends Player
{
    private int ultXPosition;
    private int ultYPosition=614;

    public Wilbur(boolean playerOne,boolean aiControlled,int aiDifficulty,boolean campaign,int currentLevel,String saveName)
    {
        super(playerOne,aiControlled,aiDifficulty,"wilbur.png","wilburHit.png",campaign,currentLevel,saveName);
        baseSprite="wilbur.png";
        hitImage="images/wilburHit.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=80;
        damage=4;
        characterReference=1;
        cooldownMaximum=10;
        bio="\n                           Wilbur:  \nAn unstable gimp who loves painting. \n    Not the strongest, but quite fast.";
    }

    public void act()
    {
        super.act();
    }

    public void triggerUltimate()
    {
        if(ultPossible()){
            super.triggerUltimate();
            if(player.getX()<=getX()){
                ultXPosition=player.getX()-45;
            }
            else{
                ultXPosition=player.getX()+45;
            }
            AddedImage ultimateImage = new AddedImage(10,false,true);
            getWorld().addObject(ultimateImage,ultXPosition,ultYPosition);
            ultimateImage.setImage("wilburUltimate.png");
        }
    }
}

