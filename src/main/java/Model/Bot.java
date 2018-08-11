package Model;

import java.awt.*;

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
            cardOney=height/6;
        }
        else if(positionOnTable==1){
           botName="Right";
            cardOnex=width-(width/3)+40;
            cardOney=height/2-50;
        }
        else{
            botName="Bottom";
            cardOnex=width/2-50;
            cardOney=height-(height/3);
        }
    }
    public void tick(){

    }
    public void render(Graphics g){
        g.drawString(botName,cardOnex,cardOney);
    }
}
