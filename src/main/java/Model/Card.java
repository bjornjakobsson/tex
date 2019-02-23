package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Card {
    private String name;
    private String color;
    private int value;
    private BufferedImage image;
    private int scaleWidth;
    private int scaleHeight;
    public static int imgScale=2;
    public static int imgWidth;
    public static int imgHeight;

    public Card(String name, String color, int value, BufferedImage image, int scaleWidth, int scaleHeight){
        this.name = name;
        this.color = color;
        this.value = value;
        this.image = image;
        this.scaleWidth=scaleWidth;
        this.scaleHeight=scaleHeight;

        imgWidth=image.getWidth()/2;
        imgHeight=image.getHeight()/2;

    }


    public static LinkedList<Card> getNewShuffeledDeck() throws IOException {
        LinkedList<Card> deck = new LinkedList<>();



        BufferedImage img1 = ImageIO.read(Card.class.getResource("/test1.png"));
        BufferedImage cardback = ImageIO.read(Card.class.getResource("/cardback.png"));
        Card cardOne = new Card("One", "void", 1, img1,cardback.getWidth()/imgScale,
                cardback.getHeight()/imgScale);
        Card cardBack = new Card("Cardback", "void", 1, cardback,cardback.getWidth()/imgScale,
                cardback.getHeight()/imgScale);
        deck.add(cardBack);
        deck.add(cardOne);

        return deck;
    }





    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {

        return name;
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getScaleWidth() { return scaleWidth;}

    public void setScaleWidth(int val) {scaleWidth=val;}

    public int getScaleHeight() {return scaleHeight;}

    public void setScaleHeight(int val) {scaleHeight = val;}
}
