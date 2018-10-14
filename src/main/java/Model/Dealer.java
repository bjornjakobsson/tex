package Model;

import java.util.LinkedList;

public class Dealer {
    private LinkedList<Card> deck;
    private LinkedList<Bot> bots;
    private Player player;
    private int currentPot=0;
    private int callRequirement=100;
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
            if(player.getChipsValue()>=callRequirement){
                return true;
            }else {
                return false;
            }
        }
        else if(Action.equals("Call")){
            if(player.getChipsValue()>=callRequirement){
                return true;
            }else{
                return false;
            }
        }
        else if(Action.equals("All in")){
            return true;
        }
        else if(Action.equals("Fold")){
            return true;
        }
        else if(Action.substring(0,1).equals("r")){
            String s = Action.substring(1,Action.length());
            int ammount = Integer.parseInt(s);
            //Implementera raise logik
            return true;
        }
        return false;
    }
    private void doPlayerAction(String Action){
        if(Action.equals("Check")){
            //Nothing i guess
        }
        else if(Action.equals("Call")){
            player.setChipsValue(player.getChipsValue()-callRequirement);
            player.setChipsOnTable(player.getChipsOnTable()+callRequirement);
            System.out.println("Player has "+ player.getChipsValue()+" left\n" +
                    "Player has "+ player.getChipsOnTable()+" on the table");
        }
        else if(Action.equals("All in")){
            player.setChipsOnTable(player.getChipsValue()+player.getChipsOnTable());
            player.setChipsValue(0);
            System.out.println("Player has "+ player.getChipsValue()+" left\n" +
                    "Player has "+ player.getChipsOnTable()+" on the table");
        }
        else if(Action.equals("Fold")){
            player.setFolded(true);
        }
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
    public void setCallRequirement(int val){
        callRequirement=val;
    }
}
