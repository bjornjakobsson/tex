package Model;


import java.awt.*;

public abstract class Participant {

    private int chipsNotBetted=1000;
    private int chipsBetted=0;

    private String positionOnTable;
    private Participant leftParticipant;
    private Dealer dealer;

    private String name;
    public boolean hasFolded=false;
    private Action theAction = new Action("NONE");

    private int cardOnex;
    private int cardOney;
    private int cardTwox;
    private int cardTwoy;

    private Card cardOne;
    private Card cardTwo;

    private int width;
    private int height;

    public Action tick() {
        Action action = new Action();
        return action;
    }

    public void render(Graphics g){

    }
    public void setAction(Action theAction){
        this.theAction = theAction;
    }
    public Action getTheAction(){
        return theAction;
    }
    public void setPositionOnTable(String s){
        positionOnTable=s;
    }
    public String getPositionOnTable(){
        return positionOnTable;
    }
    public Card getCardOne(){
        return cardOne;
    }

    public Card getCardTwo(){
        return cardTwo;
    }

    public void setCardOne(Card card){
        cardOne = card;
    }

    public void setCardTwo(Card card){
        cardTwo = card;
    }
    public void giveParticipantsCards(Card cardOne, Card cardTwo){
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
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

    public int getChipsBetted() {
        return chipsBetted;
    }

    public int getChipsNotBetted(){
        return chipsNotBetted;
    }

    public void increaseChipsNotBetted(int val){
        chipsNotBetted+=val;
    }

    public void increaseChipsBetted(int val){
        chipsBetted+=val;
    }

    public void setChipsBetted(int val){
        chipsBetted=val;
    }

    public void setChipsNotBetted(int val){
        chipsNotBetted=val;
    }

    public void setLeftParticipant(Participant participant) {
        this.leftParticipant = participant;
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

    public void setDealer(Dealer dealer){
        this.dealer=dealer;
    }
    public Dealer getDealer(){
        return dealer;
    }
}
