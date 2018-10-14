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

    public Card(String name, String color, int value, BufferedImage image){
        this.name = name;
        this.color = color;
        this.value = value;

        this.image = image;
    }


    public static LinkedList<Card> getNewShuffeledDeck() throws IOException {
        LinkedList<Card> deck = new LinkedList<>();
       // BufferedImage test = ImageIO.read(new File("/home/bjorn/IdeaProjects/Texas_holdem/src/main/resources/2.png"));
        BufferedImage img1 = ImageIO.read(Card.class.getResource("/test1.png"));
        BufferedImage cardback = ImageIO.read(Card.class.getResource("/cardback.png"));
        Card cardOne = new Card("One", "void", 1, img1);
        Card cardBack = new Card("Cardback", "void", 1, cardback);
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
}
