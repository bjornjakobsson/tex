package View;


import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import static java.awt.Font.BOLD;

public class GameView {
    // Panels
    private JPanel gameFrame;
    private JPanel bottomMenu;
    private JPanel buttonPanel;
    private JPanel infoPanel;

    //Game canvas
    private Canvas canvas;

    //Buttons
    private JButton exitButton;
    private JButton muteButton;

    //Fonts
    private Font normalButtonFont = new Font("Italic", BOLD, 20);
    private Font largerButtonFont = new Font("Italic", BOLD, 30);
    public void createGameView(){
        initButtons();
        initButtonPanel();
        initInfoPanel();
        initBottomMenu();
        initGameView();

        gameFrame.add(canvas, BorderLayout.CENTER);
        gameFrame.add(bottomMenu,BorderLayout.SOUTH);
        bottomMenu.add(infoPanel,BorderLayout.WEST);
        bottomMenu.add(buttonPanel,BorderLayout.EAST);
    }

    /**
     * Initialises the game view.
     */
    private void initGameView(){
        gameFrame = new JPanel();
        gameFrame.setLayout(new BorderLayout());

        gameFrame.setSize(800,600);
        gameFrame.setBackground(Color.BLUE);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(100,100));
        canvas.setBackground(Color.PINK);
    }

    /**
     * Initialises the bottom menu.
     */
    private void initBottomMenu(){
        bottomMenu = new JPanel();
        bottomMenu.setPreferredSize(new Dimension(800,100));
        bottomMenu.setLayout(new BorderLayout());
    }

    /**
     * Initiates the button panel. Exit, mute etc.
     */
    private void initButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1,0,0));
        buttonPanel.setPreferredSize(new Dimension(100,100));

        buttonPanel.add(muteButton);
        buttonPanel.add(exitButton);
    }

    /**
     * Initiates the info panel. Current money, time etc.
     */
    private void initInfoPanel(){
        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(700,100));
        infoPanel.setBackground(Color.RED);
    }
    /**
     * Initiates the side menu buttons.
     */
    private void initButtons(){
        LinkedList<JButton> buttonPanelButtons = new LinkedList<>();

        exitButton = new JButton("Exit");
        muteButton = new JButton("Mute");

        buttonPanelButtons.add(exitButton);
        buttonPanelButtons.add(muteButton);

        for (JButton button: buttonPanelButtons) {
            button.setFont(normalButtonFont);
            button.setOpaque(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBorder(null);
            button.addMouseListener(new ButtonGraphicEffects(button));
        }
    }

    public JPanel getGameView(){
        return gameFrame;
    }
    public Canvas getGameCanvas(){
        return canvas;
    }
    public void addActionListernerToExitButton(ActionListener actionListener){
        exitButton.addActionListener(actionListener);
    }
    /**
     * Graphical stuff for the buttons.
     */
    class ButtonGraphicEffects implements MouseListener {

        private JButton button;
        public ButtonGraphicEffects(JButton button){
            this.button = button;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            button.setFont(normalButtonFont);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setFont(largerButtonFont);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setFont(normalButtonFont);
        }
    }
}
