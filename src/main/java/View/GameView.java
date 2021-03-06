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
    private JPanel actionPanel;
    private JPanel fillerPanel;

    //Game canvas
    private Canvas canvas;

    //Buttons
    private JButton exitButton;
    private JButton muteButton;
    private JButton callButton;
    private JButton checkButton;
    private JButton raiseButton;
    private JButton foldButton;
    private JButton allInButton;
    private JButton clearLogPanelButton;

    //Text fields
    private JTextField raiseTextField;

    //Text areas
    JScrollPane loggScrollPane;
    private JTextArea loggTextArea;

    //Fonts
    private Font normalButtonFont = new Font("Italic", BOLD, 20);
    private Font largerButtonFont = new Font("Italic", BOLD, 30);

    //Dimensions
    private int width;
    private int height;

    /**
     * Constructor for GameView
     * @param width
     * @param height
     */
    public GameView(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * Creates the Game View, all methods are called upon
     * from here.
     */
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
     * Initialises the main game view panel.
     */
    private void initGameView(){
        gameFrame = new JPanel();
        gameFrame.setLayout(new BorderLayout());

        gameFrame.setSize(width,height);
        gameFrame.setBackground(Color.BLUE);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height-(height/6)));
        canvas.setBackground(Color.WHITE);

    }

    /**
     * Initialises the bottom menu.
     */
    private void initBottomMenu(){
        bottomMenu = new JPanel();
        bottomMenu.setPreferredSize(new Dimension(width,height/8+5));
        bottomMenu.setLayout(new BorderLayout());
    }

    /**
     * Initiates the button panel. Exit, mute etc.
     */
    private void initButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,0,0));
        buttonPanel.setPreferredSize(new Dimension(width/10,height/10));

        buttonPanel.add(muteButton);
        buttonPanel.add(exitButton);
    }

    /**
     * Initiates the info panel. Current money, time etc.
     */
    private void initInfoPanel(){
        actionPanel = new JPanel(new GridBagLayout());
        fillerPanel = new JPanel();
        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(width- (width/8),height/6));
        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(actionPanel, BorderLayout.WEST);
        infoPanel.add(fillerPanel, BorderLayout.EAST);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        callButton = new JButton("Call");
        checkButton = new JButton("Check");
        foldButton = new JButton("Fold");
        allInButton = new JButton("All in");
        raiseButton = new JButton("Raise");
        raiseTextField = new JTextField("",width/120);

        clearLogPanelButton = new JButton("Clear");
        loggTextArea = new JTextArea(5,65);
        loggScrollPane = new JScrollPane(loggTextArea);
        fillerPanel.add(clearLogPanelButton);
        fillerPanel.add(loggScrollPane);

        actionPanel.add(checkButton);
        actionPanel.add(callButton);
        actionPanel.add(foldButton);
        actionPanel.add(allInButton);
        actionPanel.add(raiseButton);
        actionPanel.add(raiseTextField);
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

    /**
     * Returns the game canvas, all game components are being rendered
     * on the game canvas.
     * @return
     */
    public Canvas getGameCanvas(){
        return canvas;
    }
    /**
     * Adds an ActionListener to the exit button.
     * @param actionListener
     */
    public void addActionListernerToExitButton(ActionListener actionListener){
        exitButton.addActionListener(actionListener);
    }

    /**
     * Adds and ActionListener to the clear button.
     * @param actionListener
     */
    public void addActionListenerToClearButton(ActionListener actionListener){
        clearLogPanelButton.addActionListener(actionListener);
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
            button.setForeground(Color.GRAY);
            //button.setFont(largerButtonFont);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
          //  button.setFont(normalButtonFont);
        }
    }

    /*
    Getters and setters
     */
    public JButton getExitButton(){return exitButton;}

    public JButton getCallButton() {
        return callButton;
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public JButton getRaiseButton() {
        return raiseButton;
    }

    public JButton getFoldButton() {
        return foldButton;
    }

    public JButton getAllInButton() {
        return allInButton;
    }

    public JTextField getRaiseTextField() {
        return raiseTextField;
    }

    public JTextArea getLoggTextArea(){
        return loggTextArea;
    }

    public JPanel getGameView(){
        return gameFrame;
    }
}
