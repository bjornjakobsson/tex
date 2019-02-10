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
   // private int callRequirement=0;
    private int highestRaise=0;

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
        //current/senaste höjningen? - chipsBetted
       // currentParticipant.setCallRequirement();
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

        //Since the player cant respond as fast as the bots.
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
        highestRaise=bigBlindValue;
        System.out.println(bigBlindValue);
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
            if(currentParticipant.getCallRequirement() > currentParticipant.getChipsBetted()){
                errorMessage="Error: Can't check. Not enough money in the pot "+
                        currentParticipant.getCallRequirement() + " is required\n";
                return false;
            }
            return true;

        }else if(theAction.getTheActionString().equals("CALL")){
            //Call validation logic
            if(currentParticipant.getCallRequirement() > currentParticipant.getChipsNotBetted()){
                errorMessage="Error: Can't call. Not enough money\n";
                return false;
            }
            if(currentParticipant.getChipsBetted()>=highestRaise){
                errorMessage="Error: Can't call. You already meet the call requirement\n";
                return false;
            }
            return true;

        }else if(theAction.getTheActionString().equals("RAISE")){
            //Raise validation logic
            if(theAction.getRaiseValue() > currentParticipant.getChipsNotBetted()){
                errorMessage="Error: Can't raise. Not enough money\n";
                return false;
            }
            return true;

        }else if(theAction.getTheActionString().equals("ALLIN")){
            //All in validation logic
            return true;

        }else if(theAction.getTheActionString().equals("FOLD")){
            // Fold validation logic
            return true;
        }
        return false;
    }

    /**
     * Executes the actions. Should only be used after isActionValid has been called
     */
    private void executeAction(){
        if(theAction.getTheActionString().equals("NONE")){
            //NONE logic
            actionHappened=false;
        }
        else if(theAction.getTheActionString().equals("CHECK")){
            //Check logic
        }else if(theAction.getTheActionString().equals("CALL")){
            //Call logic
            int valueToBet=(currentParticipant.getCallRequirement());
            currentParticipant.increaseChipsBetted(valueToBet);
            currentParticipant.decreaseChipsNotBetted(valueToBet);
            currentPot+=valueToBet;

        }else if(theAction.getTheActionString().equals("RAISE")){
            //Raise logic
            currentParticipant.increaseChipsBetted(theAction.getRaiseValue());
            currentParticipant.decreaseChipsNotBetted(theAction.getRaiseValue());
            currentPot+=theAction.getRaiseValue();



        }else if(theAction.getTheActionString().equals("ALLIN")){
            //All in logic

        }else if(theAction.getTheActionString().equals("FOLD")){
            //Fold logic
            currentParticipant.hasFolded=true;
            actionHappened=true;
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
        smallBlind.decreaseChipsNotBetted(smallBlindValue);

        bigBlind = smallBlind.getLeftParticipant();
        bigBlind.increaseChipsBetted(bigBlindValue);
        bigBlind.decreaseChipsNotBetted(bigBlindValue);

        smallBlind.setCallRequirement(bigBlindValue-smallBlindValue);
        bigBlind.setCallRequirement(0);

        currentPot+=(bigBlindValue+smallBlindValue);
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
    public int getHighestRaise(){
        return highestRaise;
    }
    public void setHighestRaise(int val){
        highestRaise=val;
    }
    //public int getCallRequirement(){
     //   return callRequirement;
    //}
}
