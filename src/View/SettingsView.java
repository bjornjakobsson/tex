package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import static java.awt.Font.BOLD;

public class SettingsView {
    //Enum for bot diff
    private enum Difficulty{
        Easy, Medium, Hard, Expert{
        };
        public Difficulty next() {
            return values()[ordinal() + 1];
        }
        public Difficulty prev(){
            return values()[ordinal() > 0 ? ordinal()  - 1 : 0];
        }

    }
    Difficulty difficulty = Difficulty.Easy;
    //Panels
    private JPanel SettingsFrame;
    private JPanel bufferPanelLeft;
    private JPanel bufferPanelRight;
    private JPanel navigationPanel;
    private JPanel buttonNavigationPanel;
    private JPanel buttonPanel;
    private JPanel botCountPanel;
    private JPanel botDifficultyPanel;
    private JPanel godModePanel;
    private JPanel fullScreenPanel;
    private JPanel navigationFillerPanel;

    //Font  for the buttons
    private Font normalButtonFont = new Font("Italic", BOLD, 15);
    private Font largerButtonFont = new Font("Italic", BOLD, 25);

    private LinkedList<JButton>buttons;
    private LinkedList<JButton> currValueButtons = new LinkedList<>();

    //Settings
    private int numberOfBots =0;
    private int botDiff = 0;
    private boolean godMode = false;
    private boolean fullScreen = false;

    //Dimensions
    private int width;
    private int height;

    //Main Buttons (really just labels)
    private JButton botCountButton;
    private JButton botDifficulty;
    private JButton godModeButton;
    private JButton fullScreenButton;
    private JButton defaultButton;
    private JButton exitButton;

    //Actual value (also just really labels but w/e)
    private JButton currentValueBotCountButton;
    private JButton currentValueBotDiffButton;
    private JButton currentValueGodModeButton;
    private JButton currentValueFullscreenButton;

    //Inc/dec/on/off buttons
    private JButton botCountIncButton;
    private JButton botCountDecButton;
    private JButton botDifficultyIncButton;
    private JButton botDifficultyDecButton;
    private JButton godModeOffButton;
    private JButton godModeOnButton;
    private JButton fullScreenOffButton;
    private JButton fullScreenOnButton;


    public SettingsView(int width, int height){
        this.width = width;
        this.height = height;
    }
    /**
     * Creates the settings view.
     */
    public void createSettingsView(){
        initLabelButtons();
        initChoicePanels();
        initButtonPanel();
        initBufferPanels();
        initSettingsFrame();
    }
    /**
     * Initiates the settings frame.
     */
    private void initSettingsFrame(){
        SettingsFrame = new JPanel();
        SettingsFrame.setLayout(new BorderLayout());
        SettingsFrame.setSize(width,height);
        SettingsFrame.add(bufferPanelLeft, BorderLayout.WEST);
        SettingsFrame.add(buttonPanel, BorderLayout.CENTER);
        SettingsFrame.add(bufferPanelRight, BorderLayout.EAST);
    }
    /**
     * Initiates the buffer panels.
     */
    private void initBufferPanels(){
        bufferPanelRight = new JPanel();
        bufferPanelLeft = new JPanel();
        bufferPanelRight.setPreferredSize(new Dimension(width/3,height));
        bufferPanelLeft.setPreferredSize(new Dimension(width/3,height));

        initNavigationPanel();
        bufferPanelRight.setLayout(new BorderLayout());
        bufferPanelRight.add(navigationPanel,BorderLayout.SOUTH);
    }
    private void initNavigationPanel(){
        navigationPanel = new JPanel(new BorderLayout());
        navigationPanel.setPreferredSize(new Dimension(width/3,height/3));
        buttonNavigationPanel = new JPanel(new GridLayout(2,2,0,0));
        buttonNavigationPanel.setPreferredSize(new Dimension(width/3,height/6));

        navigationFillerPanel = new JPanel();

        defaultButton = new JButton("Apply");
        defaultButton.setFont(largerButtonFont);
        defaultButton.setOpaque(false);
        defaultButton.setFocusPainted(false);
        defaultButton.setContentAreaFilled(false);
       // applyButton.setBorderPainted(false);
       // applyButton.setBorder(null);
        exitButton = new JButton("Exit");
        exitButton.setFont(largerButtonFont);
        exitButton.setOpaque(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);
        //exitButton.setBorderPainted(false);
       // exitButton.setBorder(null);

        buttonNavigationPanel.add(defaultButton);
        buttonNavigationPanel.add(exitButton);
        buttonNavigationPanel.add(navigationFillerPanel);
        navigationPanel.add(buttonNavigationPanel,BorderLayout.SOUTH);

    }
    /**
     * Initiates the button panel.
     */
    private void initButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8,1,0,0));
        buttonPanel.setPreferredSize(new Dimension(width/3,height));

        buttonPanel.add(botCountButton);
        buttonPanel.add(botCountPanel);
        buttonPanel.add(botDifficulty);
        buttonPanel.add(botDifficultyPanel);
        buttonPanel.add(godModeButton);
        buttonPanel.add(godModePanel);
        buttonPanel.add(fullScreenButton);
        buttonPanel.add(fullScreenPanel);

    }
    private void initLabelButtons(){
        LinkedList<JButton> labelButtons = new LinkedList<>();
        botCountButton = new JButton("Number of bots");
        botDifficulty = new JButton("Bot difficulty");
        godModeButton = new JButton("God mode");
        fullScreenButton = new JButton("Fullscreen");


        labelButtons.add(botCountButton);
        labelButtons.add(botDifficulty);
        labelButtons.add(godModeButton);
        labelButtons.add(fullScreenButton);
        for (JButton button: labelButtons) {
            button.setFont(largerButtonFont);
            button.setOpaque(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBorder(null);
        }
    }
    /**
     * Initiates the choice panels.
     */
    private void initChoicePanels(){
        initChoiceButtons();
        initBotCountPanel();
        initBotDiffPanel();
        initGodModePanel();
        initFullscreenPanel();
        styleCurrentSettingsButtons();
    }

    /**
     * Initiates the dec and inc / off and on buttons with mouselisteners
     */
    private void initChoiceButtons(){
        buttons= new LinkedList<>();

        botCountDecButton = new JButton("<");
        botCountIncButton = new JButton(">");
        currentValueBotCountButton = new JButton("1");
        botCountIncButton.addMouseListener(new NumberOfBotsIncrease(botCountIncButton));
        botCountDecButton.addMouseListener(new NumberOfBotsDecrease(botCountDecButton));


        botDifficultyDecButton = new JButton("<");
        botDifficultyIncButton = new JButton(">");
        currentValueBotDiffButton = new JButton(difficulty.toString());
        botDifficultyIncButton.addMouseListener(new BotDifficultyInc(botDifficultyIncButton));
        botDifficultyDecButton.addMouseListener(new BotDifficultyDec(botDifficultyDecButton));

        godModeOffButton = new JButton("<");
        godModeOnButton = new JButton(">");
        godModeOffButton.addMouseListener(new GodModeOff(godModeOffButton));
        godModeOnButton.addMouseListener(new GodModeOn(godModeOnButton));
        currentValueGodModeButton = new JButton("Off");

        fullScreenOffButton = new JButton("<");
        fullScreenOnButton = new JButton(">");
        fullScreenOffButton.addMouseListener(new FullScreenOff(fullScreenOffButton));
        fullScreenOnButton.addMouseListener(new FullScreenOn(fullScreenOnButton));
        currentValueFullscreenButton = new JButton("Off");

        addButtonsToList();

        for (JButton button : buttons) {
            button.setFont(normalButtonFont);
            button.setOpaque(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBorder(null);
        }
    }
    /**
     * Adds all the buttons to the list, makes it easier/faster to style them.
     */
    private void addButtonsToList(){
        buttons.add(botCountIncButton);
        buttons.add(botCountDecButton);
        buttons.add(botDifficultyIncButton);
        buttons.add(botDifficultyDecButton);
        buttons.add(godModeOnButton);
        buttons.add(godModeOffButton);
        buttons.add(fullScreenOffButton);
        buttons.add(fullScreenOnButton);
    }
    private void initBotCountPanel(){
        botCountPanel = new JPanel(new GridLayout(0,3));
        botCountPanel.add(botCountDecButton,BorderLayout.WEST);
        botCountPanel.add(currentValueBotCountButton,BorderLayout.CENTER);
        botCountPanel.add(botCountIncButton, BorderLayout.EAST);
        currValueButtons.add(currentValueBotCountButton);
    }
    private void initBotDiffPanel(){
        botDifficultyPanel = new JPanel(new GridLayout(0,3));
        botDifficultyPanel.add(botDifficultyDecButton,BorderLayout.WEST);
        botDifficultyPanel.add(currentValueBotDiffButton,BorderLayout.CENTER);
        botDifficultyPanel.add(botDifficultyIncButton,BorderLayout.EAST);
        currValueButtons.add(currentValueBotDiffButton);
    }
    private void initGodModePanel(){
        godModePanel = new JPanel(new GridLayout(0,3));
        godModePanel.add(godModeOffButton,BorderLayout.WEST);
        godModePanel.add(currentValueGodModeButton,BorderLayout.CENTER);
        godModePanel.add(godModeOnButton,BorderLayout.EAST);
        currValueButtons.add(currentValueGodModeButton);
    }
    private void initFullscreenPanel(){
        fullScreenPanel = new JPanel(new GridLayout(0,3));
        fullScreenPanel.add(fullScreenOffButton,BorderLayout.WEST);
        fullScreenPanel.add(currentValueFullscreenButton,BorderLayout.CENTER);
        fullScreenPanel.add(fullScreenOnButton,BorderLayout.EAST);
        currValueButtons.add(currentValueFullscreenButton);
    }
    private void styleCurrentSettingsButtons(){
        for (JButton button: currValueButtons) {
            button.setFont(normalButtonFont);
            button.setOpaque(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBorder(null);
        }
    }
    public JPanel getSettingsView(){
        return SettingsFrame;
    }
    private class NumberOfBotsIncrease implements MouseListener{

        private JButton button;
        public NumberOfBotsIncrease(JButton button){
            this.button = button;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(numberOfBots<=3){
                numberOfBots++;
            }
           currentValueBotCountButton.setText(Integer.toString(numberOfBots));
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setForeground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
        }
    }
    private class NumberOfBotsDecrease implements MouseListener{

        private JButton button;
        public NumberOfBotsDecrease(JButton button){
            this.button = button;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(numberOfBots>0){
                numberOfBots--;
            }
            currentValueBotCountButton.setText(Integer.toString(numberOfBots));
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setForeground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
        }
    }
    private class BotDifficultyInc implements MouseListener{

        private JButton button;
        public BotDifficultyInc(JButton button){
            this.button = button;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(!currentValueBotDiffButton.getText().equals("Expert")){
                difficulty = difficulty.next();
                currentValueBotDiffButton.setText(difficulty.toString());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setForeground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
        }
    }
    private class BotDifficultyDec implements MouseListener{

        private JButton button;
        public BotDifficultyDec(JButton button){
            this.button = button;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(!currentValueBotDiffButton.getText().equals("Easy")){
                difficulty = difficulty.prev();
                currentValueBotDiffButton.setText(difficulty.toString());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setForeground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
        }
    }
    private class GodModeOn implements MouseListener{
        private JButton button;
        public GodModeOn(JButton button){this.button = button;}
        @Override
        public void mouseClicked(MouseEvent e) {
            if(currentValueGodModeButton.getText().equals("Off")){
                currentValueGodModeButton.setText("On");
            }else{
                currentValueGodModeButton.setText("Off");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setForeground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
        }
    }
    private class GodModeOff implements MouseListener{

        private JButton button;
        public GodModeOff(JButton button){this.button = button;}
        @Override
        public void mouseClicked(MouseEvent e) {
            if(currentValueGodModeButton.getText().equals("Off")){
                currentValueGodModeButton.setText("On");
            }else{
                currentValueGodModeButton.setText("Off");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setForeground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
        }
    }
    private class FullScreenOn implements MouseListener{
        private JButton button;
        public FullScreenOn(JButton button){this.button = button;}
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
            button.setForeground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
        }
    }
    private class FullScreenOff implements MouseListener{
        private JButton button;
        public FullScreenOff(JButton button){this.button = button;}
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
            button.setForeground(Color.RED);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(Color.BLACK);
        }
    }
    public JButton getExitButton(){
        return exitButton;
    }
    public JButton getApplyButton(){
        return defaultButton;
    }
    public int getNumberOfBots(){
        return numberOfBots;
    }
    public String getBotDiff(){
        return currentValueBotDiffButton.getText();
    }
    public boolean getGodMode(){
        if(currentValueGodModeButton.getText().equals("On")){
            return true;
        }
        return false;
    }
    public boolean getFullScreen(){
        if(currentValueFullscreenButton.getText().equals("On")){
            return true;
        }
        return false;
    }
}

