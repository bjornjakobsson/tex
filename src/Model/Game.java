package Model;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Canvas canvas;
    private Thread thread;
    private boolean running = true;
    private BufferStrategy bs;
    private Graphics g;

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
        this.canvas = canvas;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public void tick(){

    }
    public void render(){
        bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0,0,800,600);

        bs.show();
        g.dispose();


    }
}
