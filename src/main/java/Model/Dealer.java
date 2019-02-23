package Model;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Dealer {
    /*
    Varje satsningsrunda fortsätter tills alla spelare antingen har satsat lika mycket eller foldat
    (om ingen betar är rundan över när alla spelare har checkat). När satsningsrundan är över börjar nästa
     kortrunda/satsningsrunda, eller så är handen avslutad.
     */

    private Participant bigBlind;
    private Participant smallBlind;
    private int bigBlindValue=2;
    private int smallBlindValue=1;
    private Participant currentParticipant;
    private Participant latestRaiser;

    private boolean errorOccured = false;
    private boolean actionHappened = true;

    private String state="newgame";
    private String errorMessage="";
    private Action theAction = new Action();
   // private boolean isBettingRoundOver = false;

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
    /*
    Varje satsningsrunda fortsätter tills alla spelare antingen har satsat lika mycket eller foldat
    (om ingen betar är rundan över när alla spelare har checkat). När satsningsrundan är över börjar nästa
     kortrunda/satsningsrunda, eller så är handen avslutad.
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
            currentParticipant.setHasActed(true);
           /* THIS CODE LETS THE BIGBLIND RERAISE
            if(!currentParticipant.getTheAction().getTheActionString().equals("RAISE")){
                currentParticipant.setHasActed(true);
            }*/
        }

        //Since the player cant respond as fast as the bots.
        if(theAction.getTheActionString().equals("NONE")){
            actionHappened=false;
            return;
        }
        if(isBettingRoundOver()){
            System.out.println("Preflop over, next state is flop");
            resetHasActed();
            state="flop";
        }
    }
    public void flopTick(){
      //  System.out.println("FLOP");
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
        if(isBettingRoundOver()){
            System.out.println("Flop over, next state is the turn");
            resetHasActed();
            state="turn";
        }
    }
    public void turnTick(){
        //System.out.println("TURN TICK");
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
        if(isBettingRoundOver()){
            System.out.println("Flop over, next state is the river");
            resetHasActed();
            state="river";
        }
    }
    public void riverTick(){
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
        if(isBettingRoundOver()){
            System.out.println("River betting round is over! Hand done");
            resetHasActed();
            state="none";
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
            if(highestRaise != currentParticipant.getChipsBetted()){
                errorMessage="Error: Can't check. Not enough money in the pot "+
                        currentParticipant.getCallRequirement() + " is required\n";
                return false;
            }
           // System.out.println("I am "+currentParticipant.getName()+ " my call req is: "+currentParticipant.getCallRequirement()+" " +
             //       " I have betted: "+ currentParticipant.getChipsBetted());
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
            // Kan behöva modifieras?
            // Kan big blind re-raisa ifall hen redan raisat?
            currentParticipant.increaseChipsBetted(theAction.getRaiseValue());
            currentParticipant.decreaseChipsNotBetted(theAction.getRaiseValue());
            currentPot+=theAction.getRaiseValue();
            highestRaise=currentParticipant.getChipsBetted();
            resetHasActed();
            currentParticipant.setHasActed(false);
            latestRaiser=currentParticipant;



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
        }else if(state.equals("flop")){
            flopTick();
        }else if(state.equals("turn")){
            turnTick();
        }else if(state.equals("river")){
            riverTick();
        }
    }

    /**
     * Determines if a betting round is over.
     * @return
     */
    private boolean isBettingRoundOver(){
        if(hasAllParticipantsBettedTheSame()){
            for (Participant p : world.participants) {
                if(!p.hasFolded){
                    if(!p.getHasActed()){
                        return false;
                    }
                }
            }
        }else{
            return false;
        }
        return true;
    }
    /**
     * Checks if all active playing particpants(the ones that have not folded)
     * have betted the same amount
     * @return
     */
    private boolean hasAllParticipantsBettedTheSame(){
        int first=world.participants.getFirst().getChipsBetted();
        for (Participant p: world.participants) {
            if(!p.hasFolded){
                if(p.getChipsBetted()!=first){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if all participants has folded.
     * @return
     */
    private boolean hasAllParticipantsFolded(){
        for (Participant p: world.participants) {
            if(!p.hasFolded){
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the hasActed boolean for each participants to false.
     * Used when a new betting round starts.
     */
    private void resetHasActed(){
        for (Participant p : world.participants) {
            p.setHasActed(false);
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
    public String getState(){
        return state;
    }

}
