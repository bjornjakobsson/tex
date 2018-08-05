package Model;

import java.awt.*;

public class Player {
    private int x;
    private int y;
    private int width;
    private int height;

    public Player(int width, int height){
        this.width= width;
        this.height=height;
        findPosition();
    }
    private void findPosition(){
        x=width-((width/2)+(width/4));
        y=height/2;
    }
    public void tick(){

    }
    public void render(Graphics g){
        g.drawString("Player",x,y);
    }
}
