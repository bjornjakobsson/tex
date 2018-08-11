package Model;

import java.awt.*;

public class Player {
    private int cardSeperator = 120;
    private int cardOnex;
    private int cardOney;
    private int cardTwox;
    private int cardTwoy;

    private Card cardOne;
    private Card cardTwo;

    private int width;
    private int height;

    private int chipsValue=1000;
    private boolean myTurn=true;

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
    public void tick(){
        if(!myTurn){
            return;
        }

    }
    public void render(Graphics g){
      g.drawImage(cardOne.getImage(),cardOnex,cardOney,null);
      g.drawImage(cardTwo.getImage(),cardTwox,cardTwoy,null);
    }
    public void startPlayerTurn(){
        myTurn=true;
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
}
