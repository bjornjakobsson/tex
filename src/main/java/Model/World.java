package Model;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class World {
    private Settings settings;
    private LinkedList<Card> deck=new LinkedList<>();
    private LinkedList<Bot> bots=new LinkedList<>();
    public static LinkedList<String> names=new LinkedList<>();
    private Player player;
    private Dealer dealer;
    public LinkedList<Participant> participants=new LinkedList<>();
    private int ticks;
    /**
     * Constructor for world.
     * @param settings
     */
    public World(Settings settings){
        this.settings = settings;
        ticks=0;
        try {
            loadSettings();
        } catch (IOException e){
            System.out.println("Cant find some file");
        }
    }
    /**
     * Loads all settings
     * @throws IOException
     */
    private void loadSettings() throws IOException{
        deck=Card.getNewShuffeledDeck();
        setUpBots();
        setUpPlayer();
        setUpParticipants();
        setPositionOnTable();

        dealer = new Dealer(settings.getWidth(),settings.getHeight(),deck,this);
        for (Participant p: participants) {
            p.setDealer(dealer);
        }
    }
    /**
     * Every tick updates the different objects currently active in the world.
     */
    public void tick(){
        ticks++;
        dealer.tick();
    }
    /**
     * Every render call renders every object currently active in the world.
     */
    public void render(Graphics g) {
        g.clearRect(0,0,settings.getWidth(),settings.getHeight());
        for (Participant p:participants) {
            p.render(g);
        }
        dealer.render(g);
    }
    /**
     * Adds all bots and the player to a list of all participants. Also generates names for everyone
     */
    private void setUpParticipants(){
        participants.addAll(bots);
        generateNames();
        giveNameToBots();
        participants.add(player);
        //setLeftParticipans();
    }
    /**
     * Creates a new player.
     */
    private void setUpPlayer(){
        player = new Player(settings.getWidth(),settings.getHeight());
        player.giveParticipantsCards(deck.get(1),deck.get(1));
        player.setPositionOnTable("player");
        player.setName("Player");
    }
    /**
     * Creates all bots
     */
    private void setUpBots(){
        settings.setNumberOfBots(5);
        for(int i=0;i<settings.getNumberOfBots();i++){
            bots.add(new Bot(i,settings.getWidth(),settings.getHeight()));
        }
    }
    /**
     * Methods for when the player checks/calls etc.
     */
    public void playerCheck(){
        player.setAction(new Action("CHECK"));
    }
    public void playerCall() {
        player.setAction(new Action("CALL"));
    }
    public void playerFold(){
        player.setAction(new Action("FOLD"));
    }
    public void playerAllIn(){
        player.setAction(new Action("ALLIN"));
    }
    /**
     * Generates names for bots
     */
    private void generateNames(){
        names.add("Bob");
        names.add("Steve");
        names.add("Alice");
        names.add("Cindy");
        names.add("Charles");
        names.add("Mandy");
        names.add("Joe");
        names.add("Andy");
    }
    /**
     * Assigns names to bots
     */
    private void giveNameToBots(){
        for (Participant participant: participants) {
            Random r = new Random();
            int pos = r.nextInt((names.size()-1)+1);
            participant.setName(names.get(pos));
            names.remove(pos);
        }
    }

    private void setPositionOnTable(){
        //w1440, h900
        int i = 0;
        for (Participant p : participants) {
            if(i==0){
                p.setDrawStartX(p.getDrawWidth()/4);
                p.setDrawStartY(settings.getHeight()/3);
            }else if(i == 1 || i == 2){
                p.setDrawStartX((i*p.getDrawWidth())+(p.getDrawWidth()/4));
                p.setDrawStartY(0);
            }else if(i == 3){
                p.setDrawStartX(i*p.getDrawWidth()+(p.getDrawWidth()/4));
                p.setDrawStartY(settings.getHeight()/3);
            }
            else if(i == 4){
                p.setDrawStartX(i*p.getDrawWidth()-p.getDrawWidth()*2+p.getDrawWidth()/4);
                p.setDrawStartY(settings.getHeight()/2 + p.getDrawHeight()/3);
            }else if(i == 5){
                p.setDrawStartX(i*p.getDrawWidth()-p.getDrawWidth()*4+p.getDrawWidth()/4);
                p.setDrawStartY(settings.getHeight()/2 + p.getDrawHeight()/3);
                p.setPosAtTable(i);
                p.setLeftParticipant(participants.get(0));
            }
            if(i!=5){
                p.setLeftParticipant(participants.get(i+1));
            }

            p.setCardOnex(p.getDrawStartX());
            p.setCardOney(p.getDrawStartY()+40);

            p.setCardTwox(p.getCardOnex()+88);
            p.setCardTwoy(p.getDrawStartY()+40);
            i++;
        }
    }
    /**
     * Assigns left participant to all participans
     */
    private void setLeftParticipans(){
        //Om jag är top -> sätt höger om höger finns annrs neråt annars spelare
        //Om jag är höger -> sätt neråt om neråt finns annars spelare
        //Om jag är bottom -> sätt vänster spelare
        for (Participant p: participants) {
            if(p.getPositionOnTable().equals("top")){
                for (Participant pitem: participants) {
                    if(pitem.getPositionOnTable().equals("right")){
                        p.setLeftParticipant(pitem);
                    }
                }
            }
            else if(p.getPositionOnTable().equals("right")){
                for (Participant pitem: participants) {
                    if(pitem.getPositionOnTable().equals("bottom")){
                        p.setLeftParticipant(pitem);
                    }
                }
            }
            else if(p.getPositionOnTable().equals("bottom")){
                for (Participant pitem: participants) {
                    if(pitem.getPositionOnTable().equals("player")){
                        p.setLeftParticipant(pitem);
                    }
                }
            }
            else if(p.getPositionOnTable().equals("player")){
                for (Participant pitem: participants) {
                    if(pitem.getPositionOnTable().equals("top")){
                        p.setLeftParticipant(pitem);
                    }
                }
            }

        }
    }
    /**
     * Returns the dealer
     * @return
     */
    public Dealer getDealer(){
        return dealer;
    }

    /**
     * Sends a message to the log box.
     * @param msg
     */
    public void sendMessageToLogBox(String msg){
        settings.getController().getGameView().getLoggTextArea().append(msg);
    }

}

