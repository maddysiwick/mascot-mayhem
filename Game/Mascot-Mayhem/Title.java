import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Title extends World
{
    private int optionSelected=1;
    private final Color ORANGE = new Color(255, 126, 10);
    private GreenfootImage bg;
    private Selector selector = new Selector();
    private String input1;
    private String input2;
    private String control;
    private AddedImage title;
    
    public Title()
    {    
        super(1280, 720, 1);
        setBackground("startMenu.png");
        bg = getBackground();
        //drawScreen();
        addObject(selector, 465, 265);
        selector.turn(90);
        moveSelector();
    }

    public void act()
    {
        getInputs();
        respond();
    }

    public void drawScreen()
    {
        drawTitle();
        drawBoxes();
        drawOptionNames();
    }

    public void drawTitle()
    {
        title=new AddedImage(3, true, false);
        addObject(title,getWidth()/2-85,getHeight()/2-250);
        GreenfootImage titleCard=new GreenfootImage("title card.PNG");
        titleCard.rotate(-90);
        titleCard.scale(1000,970);
        title.setImage(titleCard);
        bg.setColor(Color.WHITE);
        bg.fillOval(245,80,750,160);
        bg.fillOval(373,19,500,85);
    }

    public void drawBoxes()
    {
        bg.setColor(Color.WHITE);
        bg.fillOval(425,245,375,85);
        bg.fillOval(425,345,375,85);
        bg.fillOval(425,445,375,85);
        bg.fillOval(425,545,375,85);

    }

    public void drawOptionNames()
    {
        bg.setColor(Color.BLACK);
        bg.setFont(new Font(60));
        bg.drawString("CAMPAIGN",453,310);
        bg.drawString("DUEL",540,410);
        bg.drawString("INFO",545,510);
        bg.drawString("QUIT",540,610);
    }

    public void getInputs()
    {
        input1=InputManager.getPlayerOneInput(false);
        input2=InputManager.getPlayerTwoInput(false);
        Greenfoot.delay(5);
        if(input1=="crouch"||input2=="crouch"){
            if(optionSelected==4){
                optionSelected=1;
            }
            else{
                ++optionSelected;
            }
        }
        else if(input1=="up"||input2=="up"){
            if(optionSelected==1){
                optionSelected=4;
            }
            else{
                --optionSelected;
            }
        }
        moveSelector();
    }

    public void respond()
    {
        input1=InputManager.getPlayerOneInput(false);
        input2=InputManager.getPlayerTwoInput(false);
        if(input1=="attack"||input2=="attack"){
            switch(optionSelected){
                case 1:
                    Greenfoot.setWorld(new SaveSelect()); 
                    break;
                case 2:
                    Greenfoot.setWorld(new CharacterSelect(false)); 
                    break;
                case 3:
                    Greenfoot.setWorld(new Info());
                    break;
                case 4:
                    Greenfoot.stop();
                    break;
            }
        }
    }

    public void moveSelector()
    {
        switch(optionSelected){
            case 1:
                selector.setLocation(465,265);
                break;
            case 2:
                selector.setLocation(465,378);
                break;
            case 3:
                selector.setLocation(465,504);
                break;
            case 4:
                selector.setLocation(465,626);
                break;
        }
    }
}
