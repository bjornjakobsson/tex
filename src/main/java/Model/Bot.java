package Model;

import java.awt.*;
import java.util.Random;


public class Bot extends  Participant {
    private int positionOnTable;

    /**
     * Constructor for Bot
     * @param positionOnTable
     * @param width
     * @param height
     */
    public Bot(int positionOnTable, int width, int height){
        this.positionOnTable=positionOnTable;
        setWidth(width);
        setHeight(height);
    }

    /**
     * Logic for bot actions
     */
    @Override
    public Action tick(){

        setCallRequirement(getDealer().getHighestRaise()-getChipsBetted());
        System.out.println("My name is: "+getName()+" My call req is: "+getCallRequirement()+" The highest bet is: " +
                " "+getDealer().getHighestRaise()+" I have betted: "+getChipsBetted());
        if(getDealer().getState().equals("preflop") || getDealer().getState().equals("flop")){
            if(getChipsBetted()>=getDealer().getHighestRaise()){
                //setAction(new Action("RAISE",10));
                Random r = new Random();
                int prob = r.nextInt(2);
                System.out.println(prob);
                if(prob==0){
                    setAction(new Action("RAISE",10));
                }else{
                    setAction(new Action("CHECK"));
                }
            }
            else if(getChipsNotBetted()>=getCallRequirement()){
                setAction(new Action("CALL"));
                //increaseChipsBetted(getDealer().getCallRequirement());
            }else{
                setAction(new Action("FOLD"));
            }
        }
        System.out.println("I choose to: "+getTheAction().getTheActionString());
        return getTheAction();
    }

    /**
     * Render method for bots
     * @param g
     */
    @Override
    public void render(Graphics g){
        // Borde kunna flytta allt detta till en metod i participant.
        Card c1=getCardOne();
        Card c2=getCardTwo();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
       // System.out.println(c1.getImage().getWidth());

        g.drawImage(c1.getImage(),getCardOnex(),getCardOney(),Card.imgWidth,Card.imgHeight, null);
        g.drawImage(c2.getImage(),getCardTwox(),getCardTwoy(),Card.imgWidth,Card.imgHeight, null);

        g.drawString(Integer.toString(getChipsBetted()),getChipsBettedStringOffsetX(),getChipsBettedStringOffsetY());

        g.drawString(Integer.toString(getChipsNotBetted()),getChipsNotBettedStringOffsetX(),getChipsNotBettedStringOffsetY());
        g.drawImage(chipsImage,getChipsNotBettedStringOffsetX(),getChipsNotBettedStringOffsetY(),Card.imgWidth,Card.imgHeight,null);


        g.drawString(getName(),getNameOffsetX(),getNameOffsetY());

       // g.drawRect(getDrawStartX(),getDrawStartY(),getDrawWidth(),getDrawHeight());

    }
}
