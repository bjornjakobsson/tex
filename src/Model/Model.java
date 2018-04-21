package Model;

import java.awt.*;

public class Model {
    private Game game;

    public Model(){
        this.game = new Game();
    }

    public void initGame(Canvas canvas){
            if(!game.getRunning()){
                game.start(canvas);
            }else {
                System.out.println("HASD");
            }

    }
    public void exitGame(){
            game.stop();
    }
}
