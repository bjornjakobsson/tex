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
    private JPanel startMenuFrame;
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

    //Dimensions
    private int width;
    private int height;

    public StartMenuView(int width,int height){
        this.width = width;
        this.height = height;
    }
    /**
     * Creates and shows the start menu frame.
     */
    public void createAndShowStartMenuFrame(){
        initButtons();
        initBufferPanels();
        initButtonPanel();
        initStartMenuFrame();
    }

    /**
     * Initiates the start menu. Loads size, title etc and adds the components
     * The components consists off three panels. They are spaced out evenly and the
     * center panel contains the menu buttons.
     */
    private void initStartMenuFrame(){
        startMenuFrame = new JPanel();
        startMenuFrame.setLayout(new BorderLayout());
        startMenuFrame.setSize(width,height);
        startMenuFrame.add(bufferPanelLeft, BorderLayout.WEST);
        startMenuFrame.add(buttonPanel, BorderLayout.CENTER);
        startMenuFrame.add(bufferPanelRight, BorderLayout.EAST);

        //startMenuFrame.setVisible(true);
    }

    /**
     * Initiates the buffer panels. The purpose of the buffer panels is to get center the
     * button panel.
     */
    private void initBufferPanels(){
        bufferPanelRight = new JPanel();
        bufferPanelLeft = new JPanel();
        bufferPanelRight.setPreferredSize(new Dimension(width/3,height));
        bufferPanelLeft.setPreferredSize(new Dimension(width/3,height));
    }
    /**
     * Initiates the button panel. This panel holds all the buttons for the menu.
     */
    private void initButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1,0,0));
        buttonPanel.setPreferredSize(new Dimension(width/3,height));
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
    }
    /**
     * Adds an actionListener to the buttons
     * @param actionListener
     */
    public void addPlayButtonActionListener(ActionListener actionListener){
        playButton.addActionListener(actionListener);
    }
    public void addExitButtonActionListener(ActionListener actionListener){
        exitButton.addActionListener(actionListener);
    }
    public void addSettingsButtonActionListener(ActionListener actionListener){
        settingsButton.addActionListener(actionListener);
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
            button.setFont(normalButtonFont);
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
    public JPanel getStartMenuView(){
        return startMenuFrame;
    }
}
