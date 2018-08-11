package View;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JPanel currentPanel;

    private int width;
    private int height;

    public View(int width, int height){
        this.width = width;
        this.height = height;
        initViewFrame();
        frame.setVisible(true);
    }
    public void changeView(JPanel newPanel){
            frame.setContentPane(newPanel);
    }
    private void initViewFrame(){
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Texas hold'em");
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);

        currentPanel = new JPanel();

        frame.add(currentPanel);
        frame.setVisible(true);
    }
  
}
