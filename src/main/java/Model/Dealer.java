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

    /**
     * Logic for what should happen preflop
     */
    public void preflopTick(){
        if(currentParticipant.hasFolded){
            return;
        }
        Action action = currentParticipant.tick();
        System.out.println(currentParticipant.getName()+ " "+action.getTheAction());
        if(action.getTheAction().equals("FOLD")){
            currentParticipant.hasFolded=true;
        }

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
    private void setFirstBlinds(){
        Random r = new Random();
        int pos = r.nextInt(world.participants.size()-1)+1;
        smallBlind = world.participants.get(pos);
        smallBlind.addToChipsOnTable(smallBlindValue);
        bigBlind = smallBlind.getLeftParticipant();
        bigBlind.addToChipsOnTable(bigBlindValue);

    }
}
