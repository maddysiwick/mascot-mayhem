import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EbodaHand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EbodaHand extends Actor
{
    boolean firstHand;
    boolean myTurn;
    int[][] attackPatterns={{1,1,2,0,1},{0,1,0,1,2},{0,2,0,1,0}};
    int[] slamPostitionsX;
    int swipePositionX;
    int limitX;
    int limitY;
    int patternPlace;
    int patternRunning;
    int restTimer;
    boolean swiping;
    boolean resting;
    boolean slamming;
    int telegraphTimer;
    /**
     * Act - do whatever the EbodaHand wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public EbodaHand(boolean firstHand){
        this.firstHand=firstHand;
        patternPlace=0;
        patternRunning=Greenfoot.getRandomNumber(3);
        if(firstHand){
            GreenfootImage sprite=getImage();
            sprite.mirrorHorizontally();
            setImage(sprite);
            myTurn=true;
            slamPostitionsX=new int[]{100,200,300};
            limitX=300;
            limitY=620;
            swipePositionX=100;
        }
        else{
            myTurn=false;
            slamPostitionsX=new int[]{700,800,900};
            limitX = 600;
            swipePositionX=400;
        }
    }
    public void act()
    {
        if(myTurn){
            if(patternPlace<attackPatterns[patternRunning].length){
                if(attackPatterns[patternRunning][patternPlace]==0){
                    swipe();
                }
                else if(attackPatterns[patternRunning][patternPlace]==1){
                    slam();
                }
                else{
                    rest();
                }
            }
            else{
                patternPlace=0;
                patternRunning=Greenfoot.getRandomNumber(3);
            }
        }
    }
    private void swipe(){
        doDamage();
        if(!swiping){
            swiping=true;
            telegraphTimer=15;
            setLocation(swipePositionX,limitY);
        }
        else if(telegraphTimer!=0){
            telegraphTimer--;
        }
        else if(getX()!=limitX){
            if(firstHand){
                move(10);
            }
            else{
                move(-10);
            }
        }
        else{
            swiping=false;
            patternPlace++;
        }
        
    }
    private void slam(){
        doDamage();
        if(!slamming){
            slamming=true;
            telegraphTimer=15;
            setLocation(slamPostitionsX[Greenfoot.getRandomNumber(3)],300);
        }
        else if(telegraphTimer!=0){
            telegraphTimer--;
        }
        else if(getY()!=limitY){
            setLocation(getX(),getY()+10);
            }
        else{
            slamming=false;
            patternPlace++;
        }
    }
    private void rest(){
        if(!resting){
            resting=true;
            restTimer=40;
        }
        else if(restTimer!=0){
            restTimer--;
        }
        else{
            resting=false;
            patternPlace++;
        }
    }
    private void doDamage(){
        Actor victim = getOneIntersectingObject(Player.class);
        Player jumpee = (Player) victim;
        if(victim!=null){
            jumpee.takeHit(1);
        }
    }
}
