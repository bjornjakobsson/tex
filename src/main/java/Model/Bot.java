package Model;

import java.awt.*;


public class Bot extends  Participant{


    private String difficilty;

    private int positionOnTable;

    private boolean myTurn=false;

    public Bot(String difficilty, int positionOnTable, int width, int height){
        this.difficilty=difficilty;
        this.positionOnTable=positionOnTable;
        setWidth(width);
        setHeight(height);
        findBotPosition();

    }
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
        Action action = new Action("CHECK");
      //  System.out.println(getName()+" ticks");
        return action;
    }
    public void render(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        g.drawImage(getCardOne().getImage(),getCardOnex(),getCardOney(),null);
        g.drawImage(getCardTwo().getImage(),getCardTwox(),getCardTwoy(),null);

        g.drawString(Integer.toString(getChipsValue()),getCardOnex()+20,getCardOney()+180);

        g.drawString(Integer.toString(getChipsOnTable()),getCardTwox()+130,getCardTwoy()+70);

        g.drawString(getName(),getCardOnex()+20,getCardOney()-10);


    }
}
