import Controller.Controller;

public class Main {
    public static void main(String[]args){
       Thread MainGameThread = new Thread(new Controller(1024,768));
       MainGameThread.start();
    }
}
