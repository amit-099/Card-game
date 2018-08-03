package server1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server1 {

    public static ServerSocket server;
    public static Socket[] clients;
    public static DataInputStream[] is;
    private static DataOutputStream[] os;
    private static int[] callArray;
    public static Card[] temp;
    private static final int maxClientsCount = 4;
    public static boolean isTrumpShown;
    private static int totalPoint1, totalPoint2;
    //  private static final clientThread[] threads = new clientThread[maxClientsCount];
    private static String userName;
    private static Result result;
    private static boolean dblFlg;

    Server1() {
    }

    static String cardToString(Card[] newCard, String name) {
        String temp = name;
        for (Card newCard1 : newCard) {
            temp += newCard1.getSuit() + " " + newCard1.getFaceName() + " " + Integer.toString(newCard1.getFaceValue()) + " " + Integer.toString(newCard1.getFaceNum()) + "|";
        }
        System.out.println(temp);
        return temp;
    }

    static Card stringToCard(String str) {
        System.out.println(str);
        Card ret;
        String[] ar = str.split("\\s");
        for (int i = 0; i < ar.length; i++) {
            System.out.println(ar[i]);
        }
        ret = new Card(ar[0], ar[1], Integer.parseInt(ar[2]), Integer.parseInt(ar[3]));
        return ret;
    }

    static String tempCardToString(Card card) {
        return card.getSuit() + " " + card.getFaceName() + " " + Integer.toString(card.getFaceValue()) + " " + Integer.toString(card.getFaceNum());
    }

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(5555);
            clients = new Socket[4];
            is = new DataInputStream[4];
            os = new DataOutputStream[4];
            callArray = new int[5];
            temp = new Card[4];
            totalPoint1 = 0;
            totalPoint2 = 0;
            result = new Result();
            dblFlg = false;
        } catch (IOException ex) {
            Logger.getLogger(Server1.class.getName()).log(Level.SEVERE, null, ex);
        }
        clients[0] = server.accept();
        is[0] = new DataInputStream(clients[0].getInputStream());
        os[0] = new DataOutputStream(clients[0].getOutputStream());
        clients[1] = server.accept();
        is[1] = new DataInputStream(clients[1].getInputStream());
        os[1] = new DataOutputStream(clients[1].getOutputStream());
        clients[2] = server.accept();
        is[2] = new DataInputStream(clients[2].getInputStream());
        os[2] = new DataOutputStream(clients[2].getOutputStream());
        clients[3] = server.accept();
        is[3] = new DataInputStream(clients[3].getInputStream());
        os[3] = new DataOutputStream(clients[3].getOutputStream());
        int palash = 1;
        while (true) {
            Game gameStart = new Game();
            gameStart.cardShuffle();

            os[0].writeUTF(cardToString(Game.callCard1, "call"));
            os[0].flush();
            os[1].writeUTF(cardToString(Game.callCard2, "call"));
            os[1].flush();
            os[2].writeUTF(cardToString(Game.callCard3, "call"));
            os[2].flush();
            os[3].writeUTF(cardToString(Game.callCard4, "call"));
            os[3].flush();
            for (int i = 0; i < 4; i++) {
                os[i].writeUTF("framecall");//frame
                os[i].flush();
            }
            System.out.println("after framecall");

            int maxPlayer = 4, maxCall = 4, cnt = 0;
            for (int i = palash - 1;; i++) {
                i %= 4;
                System.out.println("loop" + i);
                String str = is[i].readUTF();
                System.out.println(str);
                callArray[i + 1] = Integer.parseInt(str);///////////callarray te client er call gula jacche
                if (callArray[i + 1] != 0) {
                    maxPlayer = i + 1;
                    maxCall = callArray[i + 1];
                }
                cnt++;
                if (cnt == 4) {
                    break;
                }
            }
            System.out.println("maxPlayer " + maxPlayer);
            os[maxPlayer - 1].writeUTF("TrumpFrame");

            System.out.println("After trump frame");
            String str = is[maxPlayer - 1].readUTF();
            System.out.println("Trump Card" + str);
            Card trumpCard = stringToCard(str);
            
            
            
            for (int k = 0; k < 4; k++) {
                System.out.println("server " + str);
                os[k].writeUTF("trumpCard" + str);
                os[k].flush();
            }
            
//            for(int i = 0; i < 4; i++) {
//                os[i].writeUTF("double");
//                os[i].flush();
//            }

//            if(maxPlayer == 1 || maxPlayer == 3) {
//                os[1].writeUTF("double");
//                os[1].flush();
//                os[3].writeUTF("double");
//                os[3].flush();
//            }
//            else if(maxPlayer == 2 || maxPlayer == 4) {
//                os[0].writeUTF("double");
//                os[0].flush();
//                os[2].writeUTF("double");
//                os[2].flush();
//            }
//            
//            if(maxPlayer == 1 || maxPlayer == 3) {
//                String stri = is[1].readUTF();
//                if(stri.startsWith("doubleok")) {
//                    dblFlg = true;
//                    
//                }
//            }
            
            os[0].writeUTF(cardToString(Game.player1, "play"));
            os[0].flush();
            os[1].writeUTF(cardToString(Game.player2, "play"));
            os[1].flush();
            os[2].writeUTF(cardToString(Game.player3, "play"));
            os[2].flush();
            os[3].writeUTF(cardToString(Game.player4, "play"));
            os[3].flush();
            System.out.println("After play card");
            int res = palash;
            isTrumpShown = false;
            
            for(int i = 0; i < 4; i++) {
                os[i].writeUTF("babaEkhonLekh"+maxCall+" "+maxPlayer);
                os[i].flush();
            }
            
            GamePlay playPlay = new GamePlay();
            
            for(int i = 0; i < 4; i++) {
                os[i].writeUTF("point"+totalPoint1+" "+totalPoint2);
                os[i].flush();
            }
            
            for (int j = 0; j < 8; j++) {
                
                for(int i = 0; i < 4; i++) {
                    os[i].writeUTF("teampoint"+GamePlay.team1+" "+GamePlay.team2);
                    os[i].flush();
                }
                
                int count = 0;
                for (int i = res - 1;; i++) {
                    i = i % 4;
                    if (res - 1 == i) {
                        os[i].writeUTF("first");
                        os[i].flush();
                    } else {
                        os[i].writeUTF("not" + tempCardToString(temp[res - 1]));
                        os[i].flush();
                    }
                    String s = is[i].readUTF();//////////isTrumpShown checking
                    if (s.startsWith("trumpTrue")) {
                        isTrumpShown = true;
                        for (int k = 0; k < 4; k++) {
                            if (i != k) {

                                os[k].writeUTF(s);
                                os[k].flush();

                            }
                        }
                        s = is[i].readUTF();
                        System.out.println("Amit " + s);
                    } 
                    temp[i] = stringToCard(s);
                    for (int k = 0; k < 4; k++) {
                        if (i != k) {
                            System.out.println(s + " opp");
                            os[k].writeUTF("opp" + s);
                            os[k].flush();
                        }
                    }
                    count++;

                    if (count == 4) {
                        break;
                    }

                }
                res = playPlay.startGame(res, trumpCard);
                for(int i = 0; i < 4; i++) {
                    os[i].writeUTF("win"+res);
                    os[i].flush();
                }
                
                System.out.println(res);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server1.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < 4; i++) {
                    os[i].writeUTF("buttonFalse");
                    os[i].flush();
                }

            }
            
            ///////////////////////////round winner////////////////////////////
            if(maxPlayer == 1 || maxPlayer == 3) {
                if(GamePlay.team2 >= maxCall) {
                    totalPoint2++;
                }
                else
                    totalPoint2--;
            }
            else if(maxPlayer == 4 || maxPlayer == 2) {
                if(GamePlay.team1 >= maxCall) {
                    totalPoint1++;
                }
                else
                    totalPoint1--;
            }
            
            for(int i = 0; i < 4; i++) {
                os[i].writeUTF("textfalse");
                os[i].flush();
            }
            
            isTrumpShown = false;
            for (int i = 0; i < 4; i++) {
                os[i].writeUTF("disposeFrame");
                os[i].flush();
            }
            palash++;
            palash %= 4;
            if (palash == 0) {
                palash = 4;
            }
            
            if(totalPoint1 >= 1 || totalPoint2 <= -1) {
                result.resultFrame(1);
                break;
            }
            else if(totalPoint2 >= 1 || totalPoint1 <= -1) {
                result.resultFrame(2);
                break;
            }
            
        }
    }
}
