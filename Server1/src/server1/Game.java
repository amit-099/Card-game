
package server1;

import java.io.IOException;
import java.util.Vector;

class Game {
    private DeckOfCards deckCard;
    private Card temp;
    public static Card[] player1, player2, player3, player4;
    private int count, one, two, three, four;
    
    public static Card[] callCard1, callCard2, callCard3, callCard4;
    private int call1, call2, call3, call4;
    
    public static Vector <Card> ply1, ply2, ply3, ply4;

    public Game() throws IOException {
        
        count = 0; one = 0; two = 0; three = 0; four = 0; call1 = 0; call2 = 0; call3 = 0; call4 = 0;
        
        ply1 = new Vector<>();
        ply2 = new Vector<>();
        ply3 = new Vector<>();
        ply4 = new Vector<>();
        
        callCard2 = new Card[4];
        callCard3 = new Card[4];
        callCard4 = new Card[4];
        callCard1 = new Card[4];
        
        this.deckCard = new DeckOfCards();
        this.player1 = new Card[8];
        this.player2 = new Card[8];
        this.player3 = new Card[8];
        this.player4 = new Card[8];
        
        
    }
    
    public void cardShuffle() {
        deckCard.shuffle();
        count = 0; one = 0; two = 0; three = 0; four = 0; call1 = 0; call2 = 0; call3 = 0; call4 = 0;
        
        while(true)
        {
            temp = deckCard.dealCard();
            if(temp == null) {
                player1 = sort(player1);
                player2 = sort(player2);
                player3 = sort(player3);
                player4 = sort(player4);
                
                if(call1 == 4 && call2 == 4 && call3 == 4 && call4 == 4) {
                    callCard1 = sort2(callCard1);
                    callCard2 = sort2(callCard2);
                    callCard3 = sort2(callCard3);
                    callCard4 = sort2(callCard4);
                }
                
                for(int i = 0; i < 8; i++) {
                    ply1.add(player1[i]);
                    ply2.add(player2[i]);
                    ply3.add(player3[i]);
                    ply4.add(player4[i]);
                }
                
                break;
            }
            switch (count) {
                case 0:
                    player1[one++] = temp;
                    if(call1 < 4)
                        callCard1[call1++] = temp;
                    count++;
                    break;
                case 1:
                    player2[two++] = temp;
                    if(call2 < 4)
                        callCard2[call2++] = temp;
                    count++;
                    break;
                case 2:
                    player3[three++] = temp;
                    if(call3 < 4)
                        callCard3[call3++] = temp;
                    count++;
                    break;
                case 3:
                    player4[four++] = temp;
                    if(call4 < 4)
                        callCard4[call4++] = temp;
                    count++;
                    break;
                default:
                    break;
            }
            if(count == 4)
                count %= 4;
        }
    }
    
    public Card[] sort(Card[] arr) {
    
        int i,j,cnt = 0;
        Card[] temp1 = new Card[8];
        for(i = 0; i < 8; i++)
        {
            for(j = i + 1; j < 8; j++)
            {
                if(arr[i].getFaceValue() < arr[j].getFaceValue())
                {
                    Card temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    
                }
            }
        }
        
        for(i = 0; i < 8; i++)
        {
            if(arr[i].getSuit().equals("Diamonds") )
            {
                temp1[cnt++] = arr[i];
            }
            
        }
        for(i = 0; i < 8; i++)
        {
            if(arr[i].getSuit().equals("Hearts") )
            {
                temp1[cnt++] = arr[i];
            }
            
        }
        for(i = 0; i < 8; i++)
        {
            if(arr[i].getSuit().equals("Clubs") )
            {
                temp1[cnt++] = arr[i];
            }
            
        }
        for(i = 0; i < 8; i++)
        {
            if(arr[i].getSuit().equals("Spades") )
            {
                temp1[cnt++] = arr[i];
            }
            
        }
        return temp1;
    }
    
    public Card[] sort2(Card[] arr2) {
        
        int i,j,cnt = 0;
        Card[] temp1 = new Card[4];
        for(i = 0; i < 4; i++)
        {
            for(j = i + 1; j < 4; j++)
            {
                if(arr2[i].getFaceValue() < arr2[j].getFaceValue())
                {
                    Card temp = arr2[i];
                    arr2[i] = arr2[j];
                    arr2[j] = temp;
                    
                }
            }
        }
        
        for(i = 0; i < 4; i++)
        {
            if(arr2[i].getSuit().equals("Diamonds") )
            {
                temp1[cnt++] = arr2[i];
            }
            
        }
        for(i = 0; i < 4; i++)
        {
            if(arr2[i].getSuit().equals("Hearts") )
            {
                temp1[cnt++] = arr2[i];
            }
            
        }
        for(i = 0; i < 4; i++)
        {
            if(arr2[i].getSuit().equals("Clubs") )
            {
                temp1[cnt++] = arr2[i];
            }
            
        }
        for(i = 0; i < 4; i++)
        {
            if(arr2[i].getSuit().equals("Spades") )
            {
                temp1[cnt++] = arr2[i];
            }
            
        }
        return temp1;
        
        
    }
    
    public Card[] getPlayer1(){
        return player1;
    }
    
    public Card[] getPlayer2(){
        return player2;
    }
    
    public Card[] getPlayer3(){
        return player3;
    }
    
    public Card[] getPlayer4(){
        return player4;
    }
    
    public Card[] getCallCard1() {
        return callCard1;
    }
    
    public Card[] getCallCard2() {
        return callCard2;
    }
    
    public Card[] getCallCard3() {
        return callCard3;
    }
    
    public Card[] getCallCard4() {
        return callCard4;
    }
    
    public void displayGame()
    {
        System.out.println("Vector check");
        System.out.println("First Player Cards:");
        ply1.forEach((card) -> {
            System.out.println(card);
        });
        System.out.println("\nSecond Player Cards:");
        ply2.forEach((card) -> {
            System.out.println(card);
        });
        System.out.println("\nThird Player Cards:");
        ply3.forEach((card) -> {
            System.out.println(card);
        });
        System.out.println("\nFourth Player Cards:");
        ply4.forEach((card) -> {
            System.out.println(card);
        });
        
        System.out.println();
        
        System.out.println("Array Check");
        
        System.out.println("First Player Cards:");
        for(Card card : player1)
            System.out.println(card);
        System.out.println("\nSecond Player Cards:");
        for(Card card : player2)
            System.out.println(card);
        System.out.println("\nThird Player Cards:");
        for(Card card : player3)
            System.out.println(card);
        System.out.println("\nFourth Player Cards:");
        for(Card card : player4)
            System.out.println(card);
    }
    
    public void displayCall() {
        
        System.out.println("Player 1 call cards");
        for(Card card : callCard1)
            System.out.println(card);
        
        System.out.println();
        System.out.println("Player 2 call cards");
        for(Card card : callCard2)
            System.out.println(card);
        
        System.out.println();
        System.out.println("Player 3 call cards");
        for(Card card : callCard3)
            System.out.println(card);
        
        System.out.println();
        System.out.println("Player 4 call cards");
        for(Card card : callCard4)
            System.out.println(card);
        
    }
}
