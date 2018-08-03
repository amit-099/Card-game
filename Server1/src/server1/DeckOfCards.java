
package server1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import javax.imageio.ImageIO;


class DeckOfCards {
    private Card[] deck;
    private int currentCard;
    private int[] faceValue = {0, 0, 2, 1, 3, 0, 0, 1};
    
    public DeckOfCards() throws IOException{
        String[] faces = {"7","8","9","10","Jack","Queen","King","Ace"};
        String[] suits = {"Diamonds","Clubs","Hearts","Spades"};
        int[] maxFace = {7, 8, 9, 10, 11, 12, 13, 14};///////////////facename in integer
        
        deck = new Card[32];
        currentCard = 0;
        
        final int width = 123;
        final int height = 172;
        final int rows = 4;
        final int cols = 8;
        
        BufferedImage bigImage = ImageIO.read(new File("allcards.jpg"));
        BufferedImage tempCardImage;
        
        for(int suit = 0; suit < rows; suit++)
        {
            for(int faceNum = 0; faceNum < cols; faceNum++)
            {
                //extract the image
                tempCardImage = bigImage.getSubimage(
                        faceNum*width + (faceNum*9),       //starting x coordinate
                        suit*height + (suit*14),           //starting y coordinate
                        width,                             //width of the image
                        height);                           //height of the image
                deck[(faceNum + (suit*8))] = new Card(suits[suit],faces[faceNum],faceValue[faceNum],maxFace[faceNum]);
            }
        }
        
    }
    
    public void displayDeck()
    {
        for(Card card : deck)
            System.out.println(card);
    }
    
    public void shuffle()
    {
        currentCard = 0;
        
        SecureRandom randomNum = new SecureRandom();
        
        for(int first = 0; first < deck.length; first++)
        {
            int second = randomNum.nextInt(32);
            
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
        
    }
    
    public Card dealCard()
    {
        if(currentCard < deck.length)
            return deck[currentCard++];
        else
            return null;
    }
}
