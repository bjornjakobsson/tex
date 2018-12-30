package Model;

import java.awt.*;


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
        findBotPosition();

    }

    /**
     * Calculates the rendering position for the bots
     */
    private void findBotPosition(){
        int cardOnex;
        int cardOney;
        int cardTwox;
        int cardTwoy;

        int width = getWidth();
        int height = getHeight();
        if(positionOnTable==0){
            setPositionOnTable("top");
            cardOnex=width/2-50;
            cardOney=height/30;

            cardTwox=width/2-64;
            cardTwoy=height/21;

            setCardOnex(cardOnex);
            setCardTwox(cardTwox);

            setCardOney(cardOney);
            setCardTwoy(cardTwoy);
        }
        else if(positionOnTable==1){
            setPositionOnTable("right");
            cardOnex=width-(width/5)+(width/100);
            cardOney=height/3;

            cardTwox=width-(width/5);
            cardTwoy=height/3+12;

            setCardOnex(cardOnex);
            setCardTwox(cardTwox);

            setCardOney(cardOney);
            setCardTwoy(cardTwoy);
        }
        else{
            setPositionOnTable("bottom");
            cardOnex=width/2-50;
            cardOney=height-(height/3)-10;

            cardTwox=width/2-64;
            cardTwoy=height-(height/3);

            setCardOnex(cardOnex);
            setCardTwox(cardTwox);

            setCardOney(cardOney);
            setCardTwoy(cardTwoy);
        }
    }

    /**
     * Logic for bot actions
     */
    @Override
    public Action tick(){
        //This setAction generates buggs, but is here for other debugging purposes.
        if(getChipsNotBetted()>=getDealer().getCallRequirement()){
            setAction(new Action("CALL"));
            //increaseChipsBetted(getDealer().getCallRequirement());
        }else{
            setAction(new Action("FOLD"));
        }
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
