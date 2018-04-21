import Controller.Controller;

public class Main {
    public static void main(String[]args){
        System.out.println("Main thread: "+java.lang.Thread.currentThread().getId());
       Thread MainGameThread = new Thread(new Controller());
       MainGameThread.start();
    }
}
