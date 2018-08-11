package Model;

public class Settings {

    private int numberOfBots =3;
    private String botDiff = "Easy";
    private boolean godMode = false;
    private boolean fullScreen = false;
    private int width;
    private int height;
    public Settings(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setNumberOfBots(int numberOfBots) {
        this.numberOfBots = numberOfBots;
    }

    public void setBotDiff(String botDiff) {
        this.botDiff = botDiff;
    }
    public void setGodMode(boolean godMode){
        this.godMode = godMode;
    }
    public void setFullScreen(boolean fullScreen){
        this.fullScreen = fullScreen;
    }

    public int getNumberOfBots(){
        return numberOfBots;
    }
    public String getBotDiff(){
        return botDiff;
    }
    public boolean isGodMode(){
        return godMode;
    }
    public boolean isFullScreen() {
        return fullScreen;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
