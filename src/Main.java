import Controller.Controller;

public class Main {
    public static void main(String[]args){
       Thread MainGameThread = new Thread(new Controller());
       MainGameThread.start();
    }
}
