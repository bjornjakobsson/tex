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
            world.sendMessageToLogBox(currentParticipant.getName()+" "+
                    currentParticipant.getTheAction().getTheActionString()+"\n");
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
        if(!isActionValid(theAction)){
            actionHappened=false;

        }
        if(theAction.getTheActionString().equals("NONE")){
            actionHappened=false;
            return;
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
     * Checks if the participants action is valid or not.
     * @param theAction
     * @return
     */
    private boolean isActionValid(Action theAction){
        if(theAction.getTheActionString().equals("NONE")){
            actionHappened=false;
            return true;
        }
        else if(theAction.getTheActionString().equals("CHECK")){
            //Check logic
            return true;
        }else if(theAction.getTheActionString().equals("CALL")){
            //Call logic
            return true;
        }else if(theAction.getTheActionString().equals("RAISE")){
            //Raise logic
            return true;
        }else if(theAction.getTheActionString().equals("ALLIN")){
            //All in logic
            return true;
        }else if(theAction.getTheActionString().equals("FOLD")){
            currentParticipant.hasFolded=true;
            return true;
        }
        return false;
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
        smallBlind.increaseChipsBetted(smallBlindValue);
        bigBlind = smallBlind.getLeftParticipant();
        bigBlind.increaseChipsBetted(bigBlindValue);

    }
}
