
package client1;


import java.awt.image.BufferedImage;




class Card {
    private String faceName, suit;
    private int faceValue, maxFaceNum;
    private BufferedImage cardImage;
    
    public Card(String suit, String face, int value, int maxFaceNum)
    {
        this.suit = suit;
        this.maxFaceNum = maxFaceNum;//face name in integer
        this.faceName = face;
        this.faceValue = value;
        //this.cardImage = card;
    }
    
    //////////////////////////////////Adding Images with every cards///////////////////////////////////////
    
    public String setCardImage(Card card) {
        if("Diamonds".equals(card.suit) && card.maxFaceNum == 7){
            return "Diamond7.png";
        }
        else if("Diamonds".equals(card.suit) && card.maxFaceNum == 8){
            return "Diamond8.png";
        }
        else if("Diamonds".equals(card.suit) && card.maxFaceNum == 9){
            return "Diamond9.png";
        }
        else if("Diamonds".equals(card.suit) && card.maxFaceNum == 10){
            return "Diamond10.png";
        }
        else if("Diamonds".equals(card.suit) && card.maxFaceNum == 11){
            return "DiamondJ.png";
        }
        else if("Diamonds".equals(card.suit) && card.maxFaceNum == 12){
            return "DiamondQueen.png";
        }
        else if("Diamonds".equals(card.suit) && card.maxFaceNum == 13){
            return "DiamondKing.png";
        }
        else if("Diamonds".equals(card.suit) && card.maxFaceNum == 14){
            return "DiamondTekka.png";
        }
        else if("Hearts".equals(card.suit) && card.maxFaceNum == 7){
            return "Heart7.png";
        }
        else if("Hearts".equals(card.suit) && card.maxFaceNum == 8){
            return "Heart8.png";
        }
        else if("Hearts".equals(card.suit) && card.maxFaceNum == 9){
            return "Heart9.png";
        }
        else if("Hearts".equals(card.suit) && card.maxFaceNum == 10){
            return "Heart10.png";
        }
        else if("Hearts".equals(card.suit) && card.maxFaceNum == 11){
            return "HeartJack.png";
        }
        else if("Hearts".equals(card.suit) && card.maxFaceNum == 12){
            return "HeartQueen.png";
        }
        else if("Hearts".equals(card.suit) && card.maxFaceNum == 13){
            return "HeartKing.png";
        }
        else if("Hearts".equals(card.suit) && card.maxFaceNum == 14){
            return "HeartTekka.png";
        }
        else if("Clubs".equals(card.suit) && card.maxFaceNum == 7){
            return "Chira7.png";
        }
        else if("Clubs".equals(card.suit) && card.maxFaceNum == 8){
            return "Chira8.png";
        }
        else if("Clubs".equals(card.suit) && card.maxFaceNum == 9){
            return "Chira9.png";
        }
        else if("Clubs".equals(card.suit) && card.maxFaceNum == 10){
            return "Chira10.png";
        }
        else if("Clubs".equals(card.suit) && card.maxFaceNum == 11){
            return "ChiraJack.png";
        }
        else if("Clubs".equals(card.suit) && card.maxFaceNum == 12){
            return "ChiraQueen.png";
        }
        else if("Clubs".equals(card.suit) && card.maxFaceNum == 13){
            return "ChiraKing.png";
        }
        else if("Clubs".equals(card.suit) && card.maxFaceNum == 14){
            return "ace.png";
        }
        else if("Spades".equals(card.suit) && card.maxFaceNum == 7){
            return "Spade7.png";
        }
        else if("Spades".equals(card.suit) && card.maxFaceNum == 8){
            return "Spade8.png";
        }
        else if("Spades".equals(card.suit) && card.maxFaceNum == 9){
            return "Spade9.png";
        }
        else if("Spades".equals(card.suit) && card.maxFaceNum == 10){
            return "Spade10.png";
        }
        else if("Spades".equals(card.suit) && card.maxFaceNum == 11){
            return "SpadeJack.png";
        }
        else if("Spades".equals(card.suit) && card.maxFaceNum == 12){
            return "SpadeQueen.png";
        }
        else if("Spades".equals(card.suit) && card.maxFaceNum == 13){
            return "SpadeKing.png";
        }
        else if("Spades".equals(card.suit) && card.maxFaceNum == 14){
            return "SpadeTekka.png";
        }
        return card.suit;
    }
    
    /////////////////////////////////////ends///////////////////////////////////
    
    
    
    ///////////////////////////////////////////////////////////////////////////////
    
    public int getFaceNum()
    {
        return maxFaceNum;
    }
    
    public BufferedImage getCardImage()
    {
        return cardImage;
    }
    
    @Override
    public String toString()
    {
        return faceName + " of " + suit + " Value " + faceValue + " face number " + maxFaceNum;
    }
    
    public int getFaceValue()
    {
        return faceValue;
    }
    
    public String getSuit()
    {
        return suit;
    }
    public String getFaceName()
    {
        return faceName;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
