package Controller;

import View.GameView;
import View.StartMenuView;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements Runnable {

    GameView gameView;
    StartMenuView startMenuView;
    View view;
    public Controller(){
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
    }

    /**
     * Adds actionListeners to the menu items.
     */
    private void addActionListenersToStartMenu(){
        startMenuView.addPlayButtonActionListener(new PlayButtonListener());
    }

    /**
     * Actionlistener for the play button. Starts the game.
     */
    private class PlayButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.changeView(gameView.getGameView());
        }
    }

}
