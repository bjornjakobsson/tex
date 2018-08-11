package Model;

import java.awt.*;

public class Player {
    private int cardOnex;
    private int cardOney;
    private int cardTwox;
    private int cardTwoy;
    private int width;
    private int height;

    public Player(int width, int height){
        this.width= width;
        this.height=height;
        findPosition();
    }
    private void findPosition(){
        cardOnex=width-((width/2)+(width/3));
        cardOney=height/2-50;
    }
    public void tick(){

    }
    public void render(Graphics g){
        g.drawString("Player",cardOnex,cardOney);
    }
}
