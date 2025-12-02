import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Wilbur extends Player
{
    private int ultXPosition;
    private int ultYPosition=614;

    public Wilbur(boolean playerOne,boolean aiControlled,int aiDifficulty,boolean campaign,int currentLevel)
    {
        super(playerOne,aiControlled,aiDifficulty,"tux.png","tuxKickTEMP.png",campaign,currentLevel);
        baseSprite="tux.png";
        hitImage="images/tuxKickTEMP.png";//obviously not this CHANGE WHEN WE HAVE THE SPRITES 
        hitPoints=100;
        damage=5;
        characterReference=1;
        bio="Wilbur \n An unstable gimp who loves painting. Not the strongest, but quite fast.";
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

