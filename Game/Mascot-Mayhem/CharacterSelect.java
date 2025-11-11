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
    private Selector selector1 = new Selector();
    private Selector selector2 = new Selector();

    
    public CharacterSelect()
    {    
        super(1280, 720, 1); 
        draw();
        drawOptions();
        initializeSelectors();
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
        //random selector box
        int[] xPoints1={(getWidth()/2-75),(getWidth()/2+75),(getWidth()/2+120),(getWidth()/2-120)};
        int[] yPoints1={650,650,550,550};
        bg.fillPolygon(xPoints1,yPoints1,4);
        //left 1 box (option 3)
        int[] xPoints2={(getWidth()/2-95),(getWidth()/2-200),(getWidth()/2-245),(getWidth()/2-140)};
        int[] yPoints2={625,625,525,525};
        bg.fillPolygon(xPoints2,yPoints2,4);
        //right 1 box (option 4)
        int[] xPoints3={(getWidth()/2+95),(getWidth()/2+200),(getWidth()/2+245),(getWidth()/2+140)};
        int[] yPoints3={625,625,525,525};
        bg.fillPolygon(xPoints3,yPoints3,4);
        //left 2 box (option 2)
        int[] xPoints4={(getWidth()/2-220),(getWidth()/2-325),(getWidth()/2-370),(getWidth()/2-265)};
        int[] yPoints4={600,600,500,500};
        bg.fillPolygon(xPoints4,yPoints4,4);
        //right 2 box (option 5)
        int[] xPoints5={(getWidth()/2+220),(getWidth()/2+325),(getWidth()/2+370),(getWidth()/2+265)};
        int[] yPoints5={600,600,500,500};
        bg.fillPolygon(xPoints5,yPoints5,4);
        //left 3 box (option 1)
        int[] xPoints6={(getWidth()/2-345),(getWidth()/2-450),(getWidth()/2-495),(getWidth()/2-390)};
        int[] yPoints6={575,575,475,475};
        bg.fillPolygon(xPoints6,yPoints6,4);
        //right 3 box (option 6)
        int[] xPoints7={(getWidth()/2+345),(getWidth()/2+450),(getWidth()/2+495),(getWidth()/2+390)};
        int[] yPoints7={575,575,475,475};
        bg.fillPolygon(xPoints7,yPoints7,4);
    }

    public void drawOptions()
    {
        bg.setColor(Color.RED);
        bg.setFont(new Font(true,false,120));
        bg.drawString("?",getWidth()/2-31,642);
        Tux tux = new Tux(true,0,0);
        addObject(tux,getWidth()/2-160,575);
        tux.makeStatic();
        Suzanne suzanne = new Suzanne(true,0,0);
        addObject(suzanne,getWidth()/2+160,575);
        suzanne.makeStatic();
    }

    public void moveOnTEMP()
    {
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new Arena());
        }
    }

    public void moveSelectorP1()
    {
        switch(p1Selection){
            case 1:
                selector1.setLocation()
                break;
            case 2:
                break;
            case 3:
                selector1.setLocation((getWidth()/2-240),530);
                break;
            case 4:
                selector1.setLocation((getWidth()/2+240),530);
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    public void moveSelectorP2()
    {
        switch(p2Selection){
            case 1:

                break;
            case 2:
                break;
            case 3:
                
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    public void initializeSelectors()
    {
        addObject(selector1,(getWidth()/2-240),530);
        addObject(selector2,(getWidth()/2+240),530);
    }
}
