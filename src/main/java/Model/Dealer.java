package Model;

import java.util.LinkedList;

public class Dealer {
    private LinkedList<Card> deck=new LinkedList<>();
    private LinkedList<Bot> bots=new LinkedList<>();
    private Player player;
    private int currentPot=0;

    public Dealer(int width, int height, Player player, LinkedList<Bot> bots, LinkedList<Card> deck){
        this.player = new Player(width,height);
        this.player = player;
        this.bots = bots;
        this.deck = deck;
    }
    public void tick(){
        player.tick();
        if(!player.isPlayersTurn()){
            for (Bot bot : bots) {
                bot.startBotTurn();
                bot.tick();
                bot.endBotTurn();
            }
        }
        player.startPlayerTurn();
    }
    public void setCurrentPot(int val){
        currentPot = val;
    }
    public int getCurrentPot(){
        return currentPot;
    }
}
