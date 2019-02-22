package Model;

import java.awt.*;
import java.awt.event.ActionListener;

public class Player extends Participant {

    private int cardSeperator = 120;

    private int width;
    private int height;

    /**
     * Constructor for Player
     * @param width
     * @param height
     */
    public Player(int width, int height){
        this.width= width;
        this.height=height;
    }

    /**
     * Logic for what the player does on his/her turn.
     * When its the players turn and a button is pressed, the dealer should validate
     * the action and change the players action to NONE again.
     */
    @Override
    public Action tick(){
        setCallRequirement(getDealer().getHighestRaise()-getChipsBetted());
        return getTheAction();
    }
    @Override
    public void render(Graphics g){
        // Borde kunna flytta allt detta till en metod i participant.
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        g.drawImage(getCardOne().getImage(),getCardOnex(),getCardOney(),Card.imgWidth,Card.imgHeight, null);
        g.drawImage(getCardTwo().getImage(),getCardTwox(),getCardTwoy(),Card.imgWidth,Card.imgHeight, null);

        g.drawString(Integer.toString(getChipsBetted()),getCardOnex()+80,getCardOney()+180);
        g.drawString(Integer.toString(getChipsNotBetted()),getCardTwox()+110,getCardTwoy()+70);

        g.drawString(getName(),getCardOnex()+20,getCardTwoy()-10);

      //  g.drawRect(getDrawStartX(),getDrawStartY(),getDrawWidth(),getDrawHeight());
    }
}
