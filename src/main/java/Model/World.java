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
        dealer = new Dealer(settings.getWidth(),settings.getHeight(),deck,this);
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
        player.render(g);
        for (Bot bot : bots) {
           bot.render(g);
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
        setLeftParticipans();
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
        for(int i=0;i<settings.getNumberOfBots();i++){
            bots.add(new Bot(settings.getBotDiff(),i,settings.getWidth(),settings.getHeight()));
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

}

