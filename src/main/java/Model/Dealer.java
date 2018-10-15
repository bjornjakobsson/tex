package Model;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Dealer {


    private Participant bigBlind;
    private Participant smallBlind;
    private int bigBlindValue=2;
    private int smallBlindValue=1;
    private Participant currentParticipant;

    private LinkedList<Card> deck;
    private LinkedList<Bot> bots;
    private Player player;

    private String state="newgame";

    private int currentPot=0;
    private int callRequirement=100;

    private String playerAction="";
    private String errorMessage="";

    private int width;
    private int height;

    private World world;

    public Dealer(int width, int height, Player player, LinkedList<Bot> bots, LinkedList<Card> deck, World world){
        this.player = new Player(width,height);
        this.player = player;
        this.bots = bots;
        this.deck = deck;
        this.width=width;
        this.height=height;
        this.world=world;

        for (Participant p: world.participants) {
            p.giveParticipantsCards(deck.getFirst(),deck.getFirst());
        }


    }
    public void tick(){
        if(state.equals("newgame")){
            newgametick();
        }
        else if(state.equals("preflop")) {
            preflopTick();
        }
        currentParticipant=currentParticipant.getLeftParticipant();
    }
    public void preflopTick(){
        currentParticipant.tick();
    }
    public void newgametick(){
        setFirstBlinds();
        state="preflop";
        currentParticipant=bigBlind;
    }
    public void render(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(Integer.toString(currentPot),width/2,height/2);
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
                errorMessage="You can't call: insufficient funds\n";
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
            currentPot+=callRequirement;

        }
        else if(Action.equals("All in")){
            currentPot+=player.getChipsValue();
            player.setChipsOnTable(player.getChipsValue()+player.getChipsOnTable());
            player.setChipsValue(0);

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
    private void setFirstBlinds(){
        Random r = new Random();
        int pos = r.nextInt(world.participants.size()-1)+1;
        smallBlind = world.participants.get(pos);
        smallBlind.addToChipsOnTable(smallBlindValue);
        bigBlind = smallBlind.getLeftParticipant();
        bigBlind.addToChipsOnTable(bigBlindValue);

    }
}
