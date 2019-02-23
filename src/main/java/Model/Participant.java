package Model;


import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Participant {

    private int chipsNotBetted=1000;
    private int chipsBetted=0;
    private int callRequirement=0;
    private boolean hasActed = false;

    private int posAtTable;
    private String positionOnTable;
    private Participant leftParticipant;
    private Dealer dealer;

    private String name;
    public boolean hasFolded=false;
    private Action theAction = new Action("NONE");

    //The max draw area of each participant.
    private int drawHeight=300;
    private int drawWidth=350;

    //The starting points of the participant draw area rectangles
    private int drawStartX=0;
    private int drawStartY=0;

    private int cardOnex;
    private int cardOney;
    private int cardTwox;
    private int cardTwoy;

    private int chipsNotBettedStringOffsetX;
    private int chipsNotBettedStringOffsetY;

    private int chipsBettedStringOffsetX;
    private int chipsBettedStringOffsetY;

    private int nameOffsetX;
    private int nameOffsetY;


    private Card cardOne;
    private Card cardTwo;
    public BufferedImage chipsImage;


    private int width;
    private int height;

    public Action tick() {
        Action action = new Action();
        return action;
    }

    public void render(Graphics g){

    }

    public void giveParticipantsCards(Card cardOne, Card cardTwo){
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
        //this.chipsBettedStringOffset=cardOne.getImage().getWidth()

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

    public void decreaseChipsBetted(int val){ chipsBetted-=val;}

    public void decreaseChipsNotBetted(int val){chipsNotBetted-=val;}

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

    public int getDrawHeight(){
        return drawHeight;
    }

    public int getDrawWidth(){return drawWidth;}

    public void setDrawHeight(int val){ drawHeight=val;}

    public void setDrawWidth(int val){drawHeight=val;}

    public int getDrawStartX(){
        return drawStartX;
    }

    public int getDrawStartY(){return drawStartY;}

    public void setDrawStartX(int val){ drawStartX=val;}

    public void setDrawStartY(int val){drawStartY=val;}

    public int getPosAtTable(){return posAtTable;}

    public void setPosAtTable(int val){posAtTable=val;}

    public int getCallRequirement(){return callRequirement;}

    public void setCallRequirement(int val){ callRequirement=val;}

    public void setHasActed(boolean val){
        hasActed=val;
    }

    public boolean getHasActed(){
        return hasActed;
    }

    public int getChipsNotBettedStringOffsetX() {
        return chipsNotBettedStringOffsetX;
    }

    public void setChipsNotBettedStringOffsetX(int chipsNotBettedStringOffset) {
        this.chipsNotBettedStringOffsetX = chipsNotBettedStringOffset;
    }

    public int getChipsBettedStringOffsetX() {
        return chipsBettedStringOffsetX;
    }

    public void setChipsBettedStringOffsetX(int chipsBettedStringOffset) {
        this.chipsBettedStringOffsetX= chipsBettedStringOffset;
    }

    public int getChipsNotBettedStringOffsetY() {
        return chipsNotBettedStringOffsetY;
    }

    public void setChipsNotBettedStringOffsetY(int chipsNotBettedStringOffsetY) {
        this.chipsNotBettedStringOffsetY = chipsNotBettedStringOffsetY;
    }

    public int getChipsBettedStringOffsetY() {
        return chipsBettedStringOffsetY;
    }

    public void setChipsBettedStringOffsetY(int chipsBettedStringOffsetY) {
        this.chipsBettedStringOffsetY = chipsBettedStringOffsetY;
    }

    public int getNameOffsetX() {
        return nameOffsetX;
    }

    public void setNameOffsetX(int nameOffset) {
        this.nameOffsetX = nameOffset;
    }

    public int getNameOffsetY() {
        return nameOffsetY;
    }

    public void setNameOffsetY(int nameOffset) {
        this.nameOffsetY = nameOffset;
    }
}
