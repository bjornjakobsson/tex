package Model;

import java.awt.*;
import java.awt.event.ActionListener;

public class Player extends Participant {

    private int cardSeperator = 120;

    private int width;
    private int height;

    private String Action="NONE";

    private boolean myTurn=true;
    private boolean folded=false;

    public Player(int width, int height){
        this.width= width;
        this.height=height;
        findPosition();
    }
    private void findPosition(){
        setCardOnex(width/2-width/3-cardSeperator);
        setCardOney(height/3+12);

        setCardTwox(width/2-width/3);
        setCardTwoy(height/3+12);
    }
    @Override
    public void tick(){
        System.out.println("Player turn");
    }
    public void render(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

      g.drawImage(getCardOne().getImage(),getCardOnex(),getCardOney(),null);
      g.drawImage(getCardTwo().getImage(),getCardTwox(),getCardTwoy(),null);
      g.drawString(Integer.toString(getChipsValue()),getCardOnex()+80,getCardOney()+200);

      g.drawString(Integer.toString(getChipsOnTable()),getCardTwox()+130,getCardTwoy()+70);

      g.drawString(getName(),getCardOnex()+20,getCardTwoy()-10);
    }
    public void setAction(String string){
        Action = string;
    }
    public void setFolded(boolean val){
        folded = val;
    }
}
