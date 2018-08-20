package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class World {
    private Settings settings;
    private LinkedList<Card> deck=new LinkedList<>();
    private LinkedList<Bot> bots=new LinkedList<>();
    private Player player;
    private Dealer dealer;


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

        for(int i=0;i<settings.getNumberOfBots();i++){
            bots.add(new Bot(settings.getBotDiff(),i,settings.getWidth(),settings.getHeight()));
        }
        player = new Player(settings.getWidth(),settings.getHeight());
        deck=Card.getNewShuffeledDeck();
        player.givePlayerCards(deck.get(1),deck.get(1));
        for (Bot bot: bots) {
            bot.giveBotCards(deck.getFirst(),deck.getFirst());
        }
        dealer = new Dealer(settings.getWidth(),settings.getHeight(),player,bots,deck);
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
        //   g.drawImage(img,0,0,null);
        //g.drawImage(deck.getFirst().getImage(),100,100,null);
        player.render(g);
        for (Bot bot : bots) {
           bot.render(g);
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
}
