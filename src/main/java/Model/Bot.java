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
       // System.out.println("My name is: "+getName()+" My call req is: "+getCallRequirement()+" The highest bet is: " +
         //      " "+getDealer().getHighestRaise()+" I have betted: "+getChipsBetted());
        if(getDealer().getState().equals("preflop") || getDealer().getState().equals("flop")){
            if(getChipsBetted()>=getDealer().getHighestRaise()){
                setAction(new Action("CHECK"));
                //setAction(new Action("RAISE",10));
              /*  Random r = new Random();
                int prob = r.nextInt(2);
                System.out.println(prob);
                if(prob==0){
                    setAction(new Action("RAISE",10));
                }else{
                    setAction(new Action("CHECK"));
                }*/
            }
            else if(getChipsNotBetted()>=getCallRequirement()){
                setAction(new Action("CALL"));
                //increaseChipsBetted(getDealer().getCallRequirement());
            }else{
                setAction(new Action("FOLD"));
            }
        }
        //System.out.println("I choose to: "+getTheAction().getTheActionString());
        return getTheAction();
    }

    /**
     * Render method for bots
     * @param g
     */
    @Override
    public void render(Graphics g){
        // Borde kunna flytta allt detta till en metod i participant.
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        g.drawImage(getCardOne().getImage(),getCardOnex(),getCardOney(),null);
        g.drawImage(getCardTwo().getImage(),getCardTwox(),getCardTwoy(),null);

        g.drawString(Integer.toString(getChipsBetted()),getCardOnex()+80,getCardOney()+180);

        g.drawString(Integer.toString(getChipsNotBetted()),getCardTwox()+110,getCardTwoy()+70);

        g.drawString(getName(),getCardOnex()+20,getCardOney()-10);

       // g.drawRect(getDrawStartX(),getDrawStartY(),getDrawWidth(),getDrawHeight());

    }
}
