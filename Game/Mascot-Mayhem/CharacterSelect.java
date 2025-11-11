import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CharacterSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharacterSelect extends World
{
    private GreenfootImage bg = getBackground();
    private int p1Selection = 1;
    private int p2Selection = 6;
    
    public CharacterSelect()
    {    
        super(1280, 720, 1); 
        draw();
    }

    public void act()
    {
        moveOnTEMP();
    }

    public void draw()
    {
        bg.setColor(Color.BLACK);
        bg.setFont(new Font(true, true, 36));
        bg.drawString("PLAYER ONE", 15, 50);
        bg.drawString("PLAYER TWO", 1015, 50);
        int[] xPoints1={(getWidth()/2-75),(getWidth()/2+75),(getWidth()/2+120),(getWidth()/2-120)};
        int[] yPoints1={650,650,550,550};
        bg.fillPolygon(xPoints1,yPoints1,4);
        int[] xPoints2={(getWidth()/2-85),(getWidth()/2-160),(getWidth()/2-205),(getWidth()/2-130)};
        int[] yPoints2={625,625,525,525};
        bg.fillPolygon(xPoints2,yPoints2,4);
    }

    public void moveOnTEMP()
    {
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new Arena());
        }
    }
}
