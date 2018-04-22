package Model;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    //GFX
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;

    //Threading
    private Thread thread;
    private boolean running = false;

    //Game world
    private Settings settings;
    private World world;
    private int width=800;
    private int height=600;

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;

            }
            if (timer >= 1000000000) {
                ticks = 0;
                timer = 0;
            }
        }
        stop();

    }
    public synchronized void start(Canvas canvas){
        running=true;
        this.canvas = canvas;
        world = new World(settings);
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        running=false;
        if(!running){
            return;
        }

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public void tick(){
        world.tick();
    }
    public void render(){
        bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0,0,width,height);
        world.render(g);

        bs.show();
        g.dispose();


    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    public boolean getRunning(){
        return running;
    }
    /**
     * Sets the settings for the game.
     * @param settings
     */
    public void setSettings(Settings settings){
        this.settings = settings;
    }
}
