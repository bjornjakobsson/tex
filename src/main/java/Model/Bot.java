package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bot {
    private String difficilty;
    private int positionOnTable;
    private String botName;
    private int cardOnex;
    private int cardOney;
    private int cardTwox;
    private int cardTwoy;
    private int width;
    private int height;

    private int chipsValue=1000;
    private boolean myTurn=false;
    private Card botCardOne;
    private Card botCardTwo;

    public Bot(String difficilty, int positionOnTable, int width, int height){
        this.difficilty=difficilty;
        this.positionOnTable=positionOnTable;
        this.width= width;
        this.height=height;
        findBotPosition();
    }
    private void findBotPosition(){
        if(positionOnTable==0){
            botName="Top";
            cardOnex=width/2-50;
            cardOney=height/30;

            cardTwox=width/2-64;
            cardTwoy=height/21;
        }
        else if(positionOnTable==1){
           botName="Right";
            cardOnex=width-(width/5)+(width/100);
            cardOney=height/3;

            cardTwox=width-(width/5);
            cardTwoy=height/3+12;
        }
        else{
            botName="Bottom";
            cardOnex=width/2-50;
            cardOney=height-(height/3)-10;

            cardTwox=width/2-64;
            cardTwoy=height-(height/3);
        }
    }
    public void tick(){
        if(!myTurn){
            return;
        }
        else{
            System.out.println(botName+"s turn");
        }
    }
    public void render(Graphics g){
        g.drawImage(botCardOne.getImage(),cardOnex,cardOney,null);
        g.drawImage(botCardTwo.getImage(),cardTwox,cardTwoy,null);


    }
    public void startBotTurn(){
        myTurn=true;
    }
    public void endBotTurn(){
        myTurn=false;
    }
    public void giveBotCards(Card cardOne, Card cardTwo){
        this.botCardOne = cardOne;
        this.botCardTwo = cardTwo;
    }

}
