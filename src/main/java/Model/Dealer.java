package Model;

import java.util.LinkedList;

public class Dealer {
    private LinkedList<Card> deck=new LinkedList<>();
    private LinkedList<Bot> bots=new LinkedList<>();
    private Player player;
    private int currentPot=0;
    private String playerAction="";

    public Dealer(int width, int height, Player player, LinkedList<Bot> bots, LinkedList<Card> deck){
        this.player = new Player(width,height);
        this.player = player;
        this.bots = bots;
        this.deck = deck;
    }
    public void tick(){
        if(player.isPlayersTurn()){
            playerAction=player.tick();
            if(playerAction.equals("NONE")){
                playerAction=player.tick();
            }
            else{
                if(isLeagal(playerAction)){
                    doPlayerAction(playerAction);
                    player.endPlayerTurn();
                }
                else{
                    System.out.println("ERROR: That action is illegal");
                }
                System.out.println(playerAction);
            }
        }
        if(!player.isPlayersTurn()){
            for (Bot bot:bots) {
                bot.startBotTurn();
                bot.tick();
                bot.endBotTurn();
            }
            player.startPlayerTurn();
        }
    }
    private boolean isLeagal(String Action){
        if(Action.equals("Check")){
            return true;
        }
        else if(Action.equals("Call")){
            return true;
        }
        else if(Action.equals("All in")){
            return true;
        }
        else if(Action.equals("Fold")){
            player.setFolded(true);
            return true;
        }
        return false;
    }
    private void doPlayerAction(String Action){

    }
    public void setCurrentPot(int val){
        currentPot = val;
    }
    public int getCurrentPot(){
        return currentPot;
    }
    public boolean playerCheck(){
        return true;
    }
    public boolean botCheck(){
        return true;
    }
}
