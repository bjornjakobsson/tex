package Model;

import java.awt.*;

public class Bot {
    private String difficilty;
    private int positionOnTable;
    private String botName;
    private int x;
    private int y;
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
            x=width/2;
            y=height/3;
        }
        else if(positionOnTable==1){
           botName="Right";
           x=width-(width/3);
           y=height/2;
        }
        else{
            botName="Bottom";
            x=width/2;
            y=height-(height/3);
        }
    }
    public void tick(){

    }
    public void render(Graphics g){
        g.drawString(botName,x,y);
    }
}
