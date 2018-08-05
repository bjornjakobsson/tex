package Model;

import java.awt.*;

public class Model {
    private Game game;

    public Model(){
        this.game = new Game();
    }

    /**
     * Starts a new game. Should load settings to game as well.
     * @param canvas
     */
    public void initGame(Canvas canvas, Settings settings){
            if(!game.getRunning()){
                game.setSettings(settings);
                game.start(canvas);
            }
    }
    public void exitGame(){
            game.stop();
    }
}
