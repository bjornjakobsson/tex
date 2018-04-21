package View;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JPanel currentPanel;

    public View(){
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
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        currentPanel = new JPanel();

        frame.add(currentPanel);
        frame.setVisible(true);
    }
  
}
