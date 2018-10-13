package Model;

import java.awt.*;
import java.awt.event.ActionListener;

public class Player extends Participant {

    //private String name = "Player";

    private int cardSeperator = 120;
    private int cardOnex;
    private int cardOney;
    private int cardTwox;
    private int cardTwoy;

    private int ChipsImage;

    private Card cardOne;
    private Card cardTwo;

    private int width;
    private int height;

    private String Action="NONE";

    private int chipsValue=1000;
    private int chipsOnTable=0;

    private boolean myTurn=true;
    private boolean folded=false;

    public Player(int width, int height){
        this.width= width;
        this.height=height;
        findPosition();
    }
    private void findPosition(){
        cardOnex=width/2-width/3-cardSeperator;
        cardOney=height/3+12;

        cardTwox=width/2-width/3;
        cardTwoy=height/3+12;
    }
    @Override
    public void tick(){
        //System.out.println("Player tick");
        /*if(!Action.equals("NONE")){
            String tmp = Action;
            Action = "NONE";
            return tmp;
        }
        return "NONE";*/
    }
    public void render(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

      g.drawImage(cardOne.getImage(),cardOnex,cardOney,null);
      g.drawImage(cardTwo.getImage(),cardTwox,cardTwoy,null);
      g.drawString(Integer.toString(chipsValue),cardOnex+80,cardOney+200);

      g.drawString(Integer.toString(getChipsOnTable()),cardTwox+130,cardTwoy+70);

      g.drawString(getName(),cardOnex+20,cardOney-10);
    }
    public void startPlayerTurn(){
        if(!folded){
            myTurn=true;
        }
    }
    public void endPlayerTurn(){
        myTurn=false;
    }
    public boolean isPlayersTurn() {
        return myTurn;
    }
    public void givePlayerCards(Card cardOne, Card cardTwo){
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
    }
    public void setAction(String string){
        Action = string;
    }
    public int getChipsValue(){
        return chipsValue;
    }
    public void setChipsValue(int value){
        chipsValue=value;
    }
    public void setChipsOnTable(int value){
        chipsOnTable=value;
    }
    public int getChipsOnTable(){
        return chipsOnTable;
    }
    public void setFolded(boolean val){
        folded = val;
    }
}
