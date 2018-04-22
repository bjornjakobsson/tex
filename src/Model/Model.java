package Model;

import java.awt.*;

public class Model {
    private Game game;
    private Settings settings;

    public Model(){
        this.game = new Game();
    }

    /**
     * Starts a new game. Should load settings to game aswell.
     * @param canvas
     */
    public void initGame(Canvas canvas, Settings settings){
            if(!game.getRunning()){
                game.start(canvas);
                game.setSettings(settings);
            }
    }
    public void exitGame(){
            game.stop();
    }
}
