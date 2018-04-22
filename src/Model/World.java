package Model;

import java.awt.*;

public class World {
    private Settings settings;

    public World(Settings settings){
        this.settings = settings;
    }
    /**
     * Every tick updates the different objects currently active in the world.
     */
    public void tick(){

    }

    /**
     * Every render call renders every object currently active in the world.
     */
    public void render(Graphics g){
        g.drawRect(0,0,100,100);
    }
}
