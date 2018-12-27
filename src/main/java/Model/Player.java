package Model;

import java.awt.*;
import java.awt.event.ActionListener;

public class Player extends Participant {

    private int cardSeperator = 120;

    private int width;
    private int height;

    private Action theAction=new Action("NONE");



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

    /**
     * Logic for what the player does on his/her turn.
     * When its the players turn and a button is pressed, the dealer should validate
     * the action and change the players action to NONE again.
     */
    @Override
    public Action tick(){
        while(theAction.getTheAction().equals("NONE")){
            System.out.println("Players turn");
        }
        return theAction;
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
        theAction.setTheAction(string);
    }
}
