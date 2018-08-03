package client1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import javax.swing.ImageIcon;


public class Clients extends Thread {

    public static Socket clientSocket;
    public static DataInputStream is = null;
    public static DataOutputStream os = null;
    private static String[] callCardTemp;
    public static Card[] cardTempCall, cardTempPlay;
    private static PlayerOne pOne;
    public static Vector<Card> ply1;
    private static Call callClient1;
    private static TrumpCard trumpTemp;
    public static Card card1st;
    private static Card opponentCard;

    static Card stringToCard(String str) {
        Card ret;
        String[] ar = str.split("\\s");
        ret = new Card(ar[0], ar[1], Integer.parseInt(ar[2]), Integer.parseInt(ar[3]));
        return ret;
    }

    static void stringToCardCall(String str) {

        str = str.substring(4);
        System.out.println("str " + str);
        callCardTemp = str.split("\\|");

        for (int i = 0; i < callCardTemp.length; i++) {
            System.out.println("callCardTemp " + callCardTemp[i]);
            String ar[] = callCardTemp[i].split("\\s");
            for (int j = 0; j < ar.length; j++) {
                System.out.println("ar " + ar[j]);
            }
            cardTempCall[i] = new Card(ar[0], ar[1], Integer.parseInt(ar[2]), Integer.parseInt(ar[3]));
        }
    }

    static void stringToCardPlay(String str) {
        str = str.substring(4);
        callCardTemp = str.split("\\|");
        for (int i = 0; i < callCardTemp.length; i++) {
            String ar[] = callCardTemp[i].split("\\s");
            cardTempPlay[i] = new Card(ar[0], ar[1], Integer.parseInt(ar[2]), Integer.parseInt(ar[3]));
        }
    }

    static String getCommand(String str) {
        if (str.startsWith("call")) {
            return "call";
        }
        return "defualt";
    }

    static String tempCardToString(Card card) {
        //String temp;
        return card.getSuit() + " " + card.getFaceName() + " " + Integer.toString(card.getFaceValue()) + " " + Integer.toString(card.getFaceNum());
    }

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {

        clientSocket = new Socket("localhost", 5555);
        is = new DataInputStream(clientSocket.getInputStream());
        os = new DataOutputStream(clientSocket.getOutputStream());
        while (true) {
            boolean round = true;
            callClient1 = new Call();
            pOne = new PlayerOne();
            trumpTemp = new TrumpCard();
            cardTempPlay = new Card[8];
            cardTempCall = new Card[4];
            ply1 = new Vector<>();
            while (round) {
                String str = is.readUTF();
                System.out.println(str);
                if (str.startsWith("call")) {
                    stringToCardCall(str);
                    pOne.callOne(cardTempCall); // four card frames
                } else if (str.startsWith("framecall")) {
                    callClient1.callCard(); // call buttons

                } else if (str.startsWith("TrumpFrame")) {
                    trumpTemp.selectTrump();

                } else if (str.startsWith("play")) {
                    stringToCardPlay(str);
                    pOne.panelCreate1(cardTempPlay);

                    for (int i = 0; i < 8; i++) {
                        ply1.add(cardTempPlay[i]);
                    }
                } else if (str.startsWith("first")) {
                    PlayerOne.cnt = 0;
                } else if (str.startsWith("not")) {
                    str = str.substring(3);
                    card1st = stringToCard(str);
                    PlayerOne.cnt = 1;

                } else if (str.startsWith("opp")) {
                    str = str.substring(3);
                    opponentCard = stringToCard(str);
                    System.out.println(str + " before Image");
                    String s = opponentCard.setCardImage(opponentCard);
                    PlayerOne.button2[PlayerOne.x].setIcon(new ImageIcon(s));
                    PlayerOne.button2[PlayerOne.x].setVisible(true);
                    PlayerOne.x++;
                    PlayerOne.x %= 4;
                    System.out.println(str + " after Image");
                } else if (str.startsWith("trumpTrue")) {
                    PlayerOne.trumpButton.setIcon(TrumpCard.trumpIcon);
                    PlayerOne.trumpButton.setVisible(true);
                } else if (str.startsWith("trumpCard")) {

                    System.out.println("str =" + str);
                    str = str.substring(9);

                    String s = stringToCard(str).setCardImage(stringToCard(str));
                    TrumpCard.trumpIcon = new ImageIcon(s);
                    pOne.frameDispose();
                } else if (str.startsWith("buttonFalse")) {
                    for (int i = 0; i < 4; i++) {
                        PlayerOne.button2[i].setVisible(false);
                    }
                    PlayerOne.playerShow.setVisible(false);
                } else if (str.startsWith("disposeFrame")) {
                    round = false;
                    pOne.mainFrameDispose();
                }
                
                else if(str.startsWith("babaEkhonLekh")) {
                    str = str.substring(13);
                    String[] tempS = str.split("\\s");
                    pOne.callTxt(Integer.parseInt(tempS[0]), Integer.parseInt(tempS[1]));
                }
                else if(str.startsWith("textfalse")) {
                    pOne.invisibleCallTxt();
                }
                else if(str.startsWith("teampoint")) {
                    str = str.substring(9);
                    String[] tempS = str.split("\\s");
                    pOne.pointShow(tempS[0], tempS[1]);
                }
                else if(str.startsWith("win")) {
                    str = str.substring(3);
                    pOne.playerShw(str);
                }
                else if(str.startsWith("point")) {
                    str = str.substring(5);
                    String[] tempS = str.split("\\s");
                    pOne.totalPoint(tempS[0], tempS[1]);
                }
//                else if(str.startsWith("double")) {
//                    pOne.dblFrame();
//                }

            }
        }

    }

}
