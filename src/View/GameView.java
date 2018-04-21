package View;


import javax.swing.*;
import java.awt.*;

public class GameView {
    private JFrame gameFrame;

    public GameView(){
        gameFrame = new JFrame();
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setTitle("Game");
        gameFrame.setSize(800,600);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
    }
}
