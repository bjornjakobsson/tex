package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class World {
    private Settings settings;

    private LinkedList<Bot> bots=new LinkedList<>();
    private Player player;

    private int ticks;
    private BufferedImage img = null;
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

    }
    /**
     * Every tick updates the different objects currently active in the world.
     */
    public void tick(){
        ticks++;
        player.tick();
        for (Bot bot: bots) {
            bot.tick();
        }
    }

    /**
     * Every render call renders every object currently active in the world.
     */
    public void render(Graphics g){
     //   g.drawImage(img,0,0,null);
        player.render(g);
        for (Bot bot: bots) {
            bot.render(g);
        }
    }
}
