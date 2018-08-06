package Main;

import Controller.Controller;

public class Main {
    public static void main(String[]args){
        Thread MainGameThread = new Thread(new Controller(800,600));
        MainGameThread.start();
    }
}
