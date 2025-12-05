import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WinScreen extends World
{
    private Player loser;
    private Player winner;
    private GreenfootSound music=new GreenfootSound("winScreenMusic.wav");
    
    public WinScreen(Player loser)
    {    
        super(1280, 720, 1); 
        setBackground("matrixBinaryOogaBooga.png");
        this.loser=loser;
        winner=loser.getPlayer();
        drawBackground();
        music.playLoop();
    }
    
    public void drawBackground()
    {
        GreenfootImage bg=getBackground();
        
        bg.setColor(Color.WHITE);
        bg.fillOval(200,25,900,80);
        bg.fillOval(90,165,225,225);
        bg.fillOval(980,430,225,225);
        bg.fillOval(330,225,700,100);
        bg.fillOval(250,495,700,100);
        
        bg.setColor(Color.BLACK);
        bg.setFont(new Font(true,true,45));
        bg.drawString(getWinnerString()+" Wins!",getWidth()/2-121,78);
        bg.drawString("Remaining HitPoints: "+winner.getHitPoints(),385,287);
        bg.drawString("Remaining HitPoints: "+loser.getHitPoints(),295,557);
        
        GreenfootImage winnerSprite=new GreenfootImage(winner.getSprite());
        GreenfootImage loserSprite=new GreenfootImage(loser.getSprite());
        winnerSprite.scale(winnerSprite.getWidth()*3,winnerSprite.getHeight()*3);
        loserSprite.scale(loserSprite.getWidth()*3,loserSprite.getHeight()*3);
        bg.drawImage(winnerSprite,110,185);
        bg.drawImage(loserSprite,1000,450);
    }
    
    public String getWinnerString()
    {
        if(winner.getPlayerOneStatus()==true){
            return "Player 1";
        }
        else{
            return "Player 2";
        }
    }
}
