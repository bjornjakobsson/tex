package Model;

import java.awt.*;

public class Model {
    private Game game;

    public Model(){
        this.game = new Game();
    }

    public void initGame(Canvas canvas){
            game.start(canvas);
    }
}
