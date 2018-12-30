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

    private boolean errorOccured = false;
    private boolean actionHappened = true;

    private String state="newgame";
    private String errorMessage="";
    private Action theAction = new Action();

    private int currentPot=0;
    private int callRequirement=1;

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
        errorOccured=false;

        executeGameState(state);

        if(actionHappened){
            world.sendMessageToLogBox(currentParticipant.getName()+" "+
                    currentParticipant.getTheAction().getTheActionString()+"\n");
            currentParticipant.setAction(new Action("NONE"));
            currentParticipant=currentParticipant.getLeftParticipant();
        }
        if(!actionHappened){
            currentParticipant.setAction(new Action("NONE"));
        }
        if(errorOccured){
            world.sendMessageToLogBox(errorMessage);
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
            errorOccured=true;
            return;
        }
        else{
            executeAction();
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
            //None validation logic
            actionHappened=false;
            return true;
        }
        else if(theAction.getTheActionString().equals("CHECK")){
            //Check validation logic
            if(callRequirement > currentParticipant.getChipsBetted()){
                errorMessage="Error: Can't check. You don't have enough money in the pot\n";
                return false;
            }
            return true;
        }else if(theAction.getTheActionString().equals("CALL")){
            //Call validation logic
            currentParticipant.increaseChipsBetted(callRequirement);
            return true;
        }else if(theAction.getTheActionString().equals("RAISE")){
            //Raise validation logic
            return true;
        }else if(theAction.getTheActionString().equals("ALLIN")){
            //All in validation logic
            return true;
        }else if(theAction.getTheActionString().equals("FOLD")){
            // Fold validation logic
            currentParticipant.hasFolded=true;
            actionHappened=true;
            return true;
        }
        return false;
    }
    private void executeAction(){
        if(theAction.getTheActionString().equals("NONE")){
            //NONE logic
            actionHappened=false;
        }
        else if(theAction.getTheActionString().equals("CHECK")){
            //Check logic
        }else if(theAction.getTheActionString().equals("CALL")){
            //Call logic
            currentParticipant.increaseChipsBetted(callRequirement);
        }else if(theAction.getTheActionString().equals("RAISE")){
            //Raise logic
        }else if(theAction.getTheActionString().equals("ALLIN")){
            //All in logic
        }else if(theAction.getTheActionString().equals("FOLD")){
            //Fold logic
        }
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

    /**
     * Determines the current game state
     */
    private void executeGameState(String state){
        if(state.equals("newgame")){
            newGametick();
        }
        else if(state.equals("preflop")) {
            preFlopTick();
        }
    }
    /*
    Getters and setters
     */
    public int getCallRequirement(){
        return callRequirement;
    }
}
