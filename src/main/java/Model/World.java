package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private BufferedImage img;
    public World(Settings settings){
        this.settings = settings;
        ticks=0;
        try {
            loadSettings();
        } catch (IOException e){
            System.out.println("Cant find some file");
        }
    }
    private void loadSettings() throws IOException{
        deck=Card.getNewShuffeledDeck();
        setUpBots();
        setUpPlayer();
        setUpParticipants();
        dealer = new Dealer(settings.getWidth(),settings.getHeight(),player,bots,deck,this);
    }
    /**
     * Every tick updates the different objects currently active in the world.
     */
    public void tick(){
        ticks++;
        //All turn-based logic happends in dealer
        dealer.tick();
    }

    /**
     * Every render call renders every object currently active in the world.
     */
    public void render(Graphics g) {
        //   g.drawImage(img,0,0,null);
        //g.drawImage(deck.getFirst().getImage(),100,100,null);
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
        for (Participant p: participants) {
            //System.out.println("I am: "+p.getName()+" to my left is: "+p.getLeftParticipant().getName());
        }
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
    public void playerCheck(){
        player.setAction("Check");
    }
    public void playerCall(){
        player.setAction("Call");
    }
    public void playerFold(){
        player.setAction("Fold");
    }
    public void playerAllIn(){
        player.setAction("All in");
    }
    public void playerRaise(String ammount){
        player.setAction("r"+ammount);
    }
    public Dealer getDealer(){
        return dealer;
    }
    public void sendMessageToTextBox(String s) {
        settings.getController().printMessageToViewLoggBox(s);
    }
    public void clearTextBox(){
        settings.getController().clearGameLoggBox();
    }
    private void generateNames(){
        names.add("Bob");
        names.add("Steve");
        names.add("Alice");
        names.add("Cindy");

    }
    private void giveNameToBots(){
        System.out.println(participants.size());
        for (Participant participant: participants) {
            Random r = new Random();
            int pos = r.nextInt((names.size()-1)+1);
            participant.setName(names.get(pos));
            names.remove(pos);
        }
    }
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
}

