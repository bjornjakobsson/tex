package Controller;

import View.GameView;
import View.StartMenuView;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements Runnable {

    GameView gameView;
    StartMenuView startMenuView;

    public Controller(){
        this.startMenuView = new StartMenuView();


    }
    @Override
    public void run() {
        System.out.println(java.lang.Thread.activeCount());
        startMenuView.createAndShowStartMenuFrame();
        addActionListener();
    }

    /**
     * Adds actionListeners to the menu items.
     */
    private void addActionListener(){
        startMenuView.addPlayButtonActionListener(new PlayButtonListener());
    }
    private class PlayButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gameView = new GameView();
            startMenuView.closeMenuFrame();

        }
    }
}
