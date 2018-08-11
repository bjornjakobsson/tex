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
    }
    /**
     * Every tick updates the different objects currently active in the world.
     */
    public void tick(){
        ticks++;
        player.tick();
        //If the player has finished his/hers turn, the bots start their turn in the list order (top,right, bot)
        //When the bots are done, its the players turn again.
        if(!player.isPlayersTurn()){
            for (Bot bot : bots) {
                bot.startBotTurn();
                bot.tick();
                bot.endBotTurn();
            }
        }
        player.startPlayerTurn();
    }

    /**
     * Every render call renders every object currently active in the world.
     */
    public void render(Graphics g) {
        //   g.drawImage(img,0,0,null);
      //  g.drawImage(deck.getFirst().getImage(),100,100,null);
        player.render(g);
        for (Bot bot : bots) {
            bot.render(g);
        }
    }
    public void playerCall(){
        player.endPlayerTurn();
    }
}
