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
    EbodaManager manager;
    boolean hitForAttack;
    /**
     * Act - do whatever the EbodaHand wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public EbodaHand(boolean firstHand,EbodaManager manager){
        this.firstHand=firstHand;
        patternPlace=0;
        patternRunning=Greenfoot.getRandomNumber(3);
        this.manager=manager;
        limitY=620;
        if(firstHand){
            GreenfootImage sprite=getImage();
            sprite.mirrorHorizontally();
            setImage(sprite);
            myTurn=true;
            slamPostitionsX=new int[]{310,410,510};
            limitX=640-130;
            swipePositionX=640-430;
        }
        else{
            myTurn=false;
            slamPostitionsX=new int[]{770,870,970};
            limitX = 640+130;
            swipePositionX=640+430;
        }
    }
    public void act()
    {
        doDamage();
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
                myTurn=false;
                manager.flagTurnOver();
                patternPlace=0;
                patternRunning=Greenfoot.getRandomNumber(3);
                if(firstHand){
                    setLocation(640-400,300);
                }
                else{
                    setLocation(640+400,300);
                }
            }
        }
    }
    private void swipe(){
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
            hitForAttack=false;
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
            hitForAttack=false;
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
        if(jumpee!=null&&!jumpee.getIsEbodaHead()&&!hitForAttack){
            hitForAttack=true;
            jumpee.takeHit(4);
        }
    }
    public void setMyTurn(boolean myTurn){
        this.myTurn=myTurn;
    }
}
