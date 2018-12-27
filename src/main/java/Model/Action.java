package Model;

public class Action {
    private String theAction="";
    private int raiseValue=0;
    private int callValue=0;

    public Action(){

    }
    /**
     * Constructor for an action that does not req. add. info.
     * ex. fold
     * @param theAction
     */
    public Action(String theAction){
        this.theAction=theAction;
    }

    /**
     * Constructor for an action that does req. add. info.
     * ex raise.
     * @param theAction
     * @param value
     */
    public Action(String theAction, int value){
        this.theAction=theAction;
        this.raiseValue=value;
    }
    public void setTheAction(String s){
        theAction=s;
    }
    public String getTheActionString(){
        return theAction;
    }
    public int getRaiseValue(){
        return raiseValue;
    }
    public int getCallValue(){
        return callValue;
    }
}
