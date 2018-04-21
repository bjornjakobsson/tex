package View;


import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView {
    private JPanel gameFrame;
    private Canvas canvas;
    public void createGameView(){
        initGameView();
    }
    private void initGameView(){
        gameFrame = new JPanel();
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setSize(800,600);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(800,600));

        gameFrame.add(canvas);
    }
    public JPanel getGameView(){
        return gameFrame;
    }
    public Canvas getGameCanvas(){
        return canvas;
    }
}
