package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import static java.awt.Font.BOLD;
import static javax.swing.text.StyleConstants.Italic;

public class StartMenuView {
    //Panels
    private JFrame startMenuFrame;
    private JPanel bufferPanelLeft;
    private JPanel bufferPanelRight;
    private JPanel buttonPanel;

    //Buttons
    private JButton playButton;
    private JButton settingsButton;
    private JButton exitButton;
    private JButton howToPlayButton;

    //Font  for the buttons
    private Font normalButtonFont = new Font("Italic", BOLD, 30);
    private Font largerButtonFont = new Font("Italic", BOLD, 40);
    /**
     * Creates and shows the start menu frame.
     */
    public void createAndShowStartMenuFrame(){

        initButtons();
        initBufferPanels();
        initButtonPanel();
        initStartMenuFrame();

        System.out.println("View thread: "+java.lang.Thread.currentThread().getId());
    }

    /**
     * Initiates the start menu. Loads size, title etc and adds the components
     * The components consists off three panels. They are spaced out evenly and the
     * center panel contains the menu buttons.
     */
    private void initStartMenuFrame(){
        startMenuFrame = new JFrame();
        startMenuFrame.setLayout(new BorderLayout());
        startMenuFrame.setTitle("Texas hold'em");
        startMenuFrame.setSize(800,600);
        startMenuFrame.setLocationRelativeTo(null);
        startMenuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startMenuFrame.setResizable(false);
        startMenuFrame.add(bufferPanelLeft, BorderLayout.WEST);
        startMenuFrame.add(buttonPanel, BorderLayout.CENTER);
        startMenuFrame.add(bufferPanelRight, BorderLayout.EAST);

        startMenuFrame.setVisible(true);
    }

    /**
     * Initiates the buffer panels. The purpose of the buffer panels is to get center the
     * button panel.
     */
    private void initBufferPanels(){
        bufferPanelRight = new JPanel();
        bufferPanelLeft = new JPanel();
        bufferPanelRight.setPreferredSize(new Dimension(265,600));
        bufferPanelLeft.setPreferredSize(new Dimension(265,600));
    }

    /**
     * Initiates the button panel. This panel holds all the buttons for the menu.
     */
    private void initButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1,0,0));
        buttonPanel.setPreferredSize(new Dimension(266,600));
        buttonPanel.setVisible(true);

        buttonPanel.add(playButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(exitButton);
    }

    /**
     * Initiates the buttons, sets fonts, size etc.
     */
    private void initButtons(){
        LinkedList<JButton> menuButtons = new LinkedList<>();

        playButton = new JButton("Play");
        settingsButton = new JButton("Settings");
        exitButton = new JButton("Exit");
        howToPlayButton = new JButton("How to play");

        menuButtons.add(playButton);
        menuButtons.add(settingsButton);
        menuButtons.add(exitButton);
        menuButtons.add(howToPlayButton);


        // Makes the buttons look like text
        for (JButton button: menuButtons) {
            button.setFont(normalButtonFont);
            button.setOpaque(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBorder(null);
            button.addMouseListener(new ButtonGraphicEffects(button));
        }
    }

    public void closeMenuFrame(){
        startMenuFrame.setVisible(false);
        startMenuFrame.dispose();
    }

    /**
     * Adds an actionListener to the play button
     * @param actionListener
     */
    public void addPlayButtonActionListener(ActionListener actionListener){
        playButton.addActionListener(actionListener);
    }


    /**
     * Graphical stuff for the buttons.
     */
    class ButtonGraphicEffects implements MouseListener{

        private JButton button;
        public ButtonGraphicEffects(JButton button){
            this.button = button;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

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
