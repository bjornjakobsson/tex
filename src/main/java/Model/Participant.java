package Model;


public abstract class Participant {

    private int chipsValue=1000;
    private int chipsOnTable=0;

    private String positionOnTable;
    private Participant leftParticipant;
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

    public void render(){

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
