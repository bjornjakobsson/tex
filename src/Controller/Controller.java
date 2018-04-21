package Controller;

import Model.Model;
import View.GameView;
import View.StartMenuView;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements Runnable {

    GameView gameView;
    StartMenuView startMenuView;
    View view;
    Model model;
    public Controller(){
        this.model = new Model();
        this.view = new View();
        this.startMenuView = new StartMenuView();
        this.gameView = new GameView();

    }
    @Override
    public void run() {
        startMenuView.createAndShowStartMenuFrame();
        gameView.createGameView();
        view.changeView(startMenuView.getStartMenuView());
        addActionListenersToStartMenu();
        addActionListenersToGameMenus();
    }

    /**
     * Adds actionListeners to the menu items.
     */
    private void addActionListenersToStartMenu(){
        startMenuView.addPlayButtonActionListener(new PlayButtonListener());
        startMenuView.addExitButtonActionListener(new ExitButtonListener());
    }
    private void addActionListenersToGameMenus(){
        gameView.addActionListernerToExitButton(new ExitGameViewButtonListener());
    }
    /**
     * ActionListener for the play button. Starts the game.
     */
    private class PlayButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.changeView(gameView.getGameView());
            model.initGame(gameView.getGameCanvas());
            // This means that the gui doesnt run on the same thread
            // as the game graphics.
            try {
                Thread.sleep(2000);
                System.out.println("asd");
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
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

}
