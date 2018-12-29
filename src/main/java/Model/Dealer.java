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
    private boolean actionHappened = true;

    private String state="newgame";
    private Action theAction = new Action();

    private int currentPot=0;
    private int callRequirement=100;

    private int width;
    private int height;

    private World world;

    /**
     * Constructor for Dealer
     * @param width
     * @param height
     * @param deck
     * @param world
     */
    public Dealer(int width, int height, LinkedList<Card> deck, World world){
        this.width=width;
        this.height=height;
        this.world=world;
        for (Participant p: world.participants) {
            p.giveParticipantsCards(deck.getFirst(),deck.getFirst());
        }
    }

    /**
     * Main game loop
     */
    public void tick(){
        actionHappened=true;
        if(state.equals("newgame")){
            newGametick();
        }
        else if(state.equals("preflop")) {
            preFlopTick();
        }
        if(actionHappened){
            currentParticipant.setAction(new Action("NONE"));
            currentParticipant=currentParticipant.getLeftParticipant();
        }
    }

    /**
     * Logic for what should happen pre flop
     */
    public void preFlopTick(){
        if(currentParticipant.hasFolded){
            return;
        }
        theAction = currentParticipant.tick();
        if(theAction.getTheActionString().equals("NONE")){
            actionHappened=false;
            return;
        }else{
          //  System.out.println(currentParticipant.getName()+ " "+currentParticipant.getTheAction().getTheActionString());
            currentPot++;
        }
        if(theAction.getTheActionString().equals("FOLD")){
            currentParticipant.hasFolded=true;
        }
    }

    /**
     * Logic for what happends when a new game starts.
     */
    public void newGametick(){
        setFirstBlinds();
        state="preflop";
        currentParticipant=bigBlind;
    }

    /**
     * Render method for the dealer
     * @param g
     */
    public void render(Graphics g){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(Integer.toString(currentPot),width/2,height/2);
    }

    /**
     * Sets blinds for a new game.
     */
    private void setFirstBlinds(){
        Random r = new Random();
        int pos = r.nextInt(world.participants.size()-1)+1;
        smallBlind = world.participants.get(pos);
        smallBlind.addToChipsOnTable(smallBlindValue);
        bigBlind = smallBlind.getLeftParticipant();
        bigBlind.addToChipsOnTable(bigBlindValue);

    }
}
