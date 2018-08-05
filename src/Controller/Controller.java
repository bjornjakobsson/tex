package Controller;

import Model.Model;
import Model.Settings;
import View.GameView;
import View.StartMenuView;
import View.View;
import View.SettingsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements Runnable {
    private SettingsView settingsView;
    private GameView gameView;
    private StartMenuView startMenuView;
    private View view;
    private Model model;
    private Settings settings;
    private int width;
    private int height;


    public Controller(int width, int height){
        this.width = width;
        this.height = height;
        this.model = new Model();
        this.view = new View(width, height);
        this.startMenuView = new StartMenuView(width, height);
        this.gameView = new GameView(width, height);
        this.settingsView = new SettingsView(width,height);
        this.settings = new Settings(width,height);

    }
    @Override
    public void run() {
        startMenuView.createAndShowStartMenuFrame();
        gameView.createGameView();
        settingsView.createSettingsView();
        view.changeView(startMenuView.getStartMenuView());
        addActionListenersToStartMenu();
        addActionListenersToGameMenus();
        addActionListenersToSettingMenu();
    }

    /**
     * Adds actionListeners to the menu items.
     */
    private void addActionListenersToStartMenu(){
        startMenuView.addPlayButtonActionListener(new PlayButtonListener());
        startMenuView.addExitButtonActionListener(new ExitButtonListener());
        startMenuView.addSettingsButtonActionListener(new SettingsButtonListener());
    }
    private void addActionListenersToGameMenus(){
        gameView.addActionListernerToExitButton(new ExitGameViewButtonListener());
    }
    private void addActionListenersToSettingMenu(){
        settingsView.getApplyButton().addActionListener(new ApplySettingsButtonListener());
        settingsView.getExitButton().addActionListener(new ExitSettingsButtonListener());
    }
    /**
     * ActionListener for the play button. Starts the game.
     */
    private class PlayButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.changeView(gameView.getGameView());
            model.initGame(gameView.getGameCanvas(), settings);
            //Uppdatera settings här till model som sen
            // skickas till game klassen.
        }
    }
    /**
     * Actionlistener for the exit button. Exits the game.
     */
    private class ExitButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
    /**
     * ActionListener for exit button in game view, takes the user back to the main menu.
     */
    private class ExitGameViewButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            model.exitGame();
            System.out.println(java.lang.Thread.activeCount());
            view.changeView(startMenuView.getStartMenuView());
        }
    }
    private class SettingsButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            view.changeView(settingsView.getSettingsView());
        }
    }
    private class ExitSettingsButtonListener implements ActionListener{
        public ExitSettingsButtonListener(){}
        @Override
        public void actionPerformed(ActionEvent e) {
            view.changeView(startMenuView.getStartMenuView());

        }
    }
    private class ApplySettingsButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.changeView(startMenuView.getStartMenuView());
            settings.setBotDiff(settingsView.getBotDiff());
            settings.setNumberOfBots(settingsView.getNumberOfBots());
            settings.setGodMode(settingsView.getGodMode());
            settings.setFullScreen(settingsView.getFullScreen());
        }
    }

}
