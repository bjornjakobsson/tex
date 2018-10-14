package Model;

import java.util.LinkedList;

public abstract class Participant {

    private int chipsValue=1000;
    private int chipsOnTable=0;
    private Participant leftParticipant;
    private String name;

    private int cardOnex;
    private int cardOney;
    private int cardTwox;
    private int cardTwoy;

    private int width;
    private int height;

    public void tick() {
    }
    public void render(){

    }
    public void addToChipsOnTable(int val){
        chipsOnTable =chipsOnTable+val;
    }
    public int getChipsOnTable(){
        return chipsOnTable;
    }

    public int getChipsValue() {
        return chipsValue;
    }

    public void setChipsValue(int chipsValue) {
        this.chipsValue = chipsValue;
    }

    public String getName(){
        return name;

    }
    public void setName(String s){
        name=s;
    }
    public Participant getLeftParticipant(){
        return leftParticipant;
    }

    public void setLeftParticipant(Participant participant) {
        this.leftParticipant = participant;
    }
    public void addToChipsValue(int newVal){
        chipsValue+=newVal;
    }

    public void setChipsOnTable(int chipsOnTable) {
        this.chipsOnTable = chipsOnTable;
    }

    public int getCardOnex() {
        return cardOnex;
    }

    public void setCardOnex(int cardOnex) {
        this.cardOnex = cardOnex;
    }

    public int getCardOney() {
        return cardOney;
    }

    public void setCardOney(int cardOney) {
        this.cardOney = cardOney;
    }

    public int getCardTwox() {
        return cardTwox;
    }

    public void setCardTwox(int cardTwox) {
        this.cardTwox = cardTwox;
    }

    public int getCardTwoy() {
        return cardTwoy;
    }

    public void setCardTwoy(int cardTwoy) {
        this.cardTwoy = cardTwoy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
