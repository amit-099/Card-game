package server1;


import java.io.IOException;

public class GamePlay {

    public static int track, team1, team2;

    Card tmp;
    int tracePlayer;
    private static int firstCard;
    public static boolean isCalling;

    GamePlay() throws IOException {
        track = 0; team1 = 0; team2 = 0; tracePlayer = 0; isCalling = true;
//        p1 = new PlayerOne();
//        p2 = new PlayerTwo();
//        p3 = new PlayerThree();
//        p4 = new PlayerFour();
        //trCard = new TrumpCard();
    }

    /////////////////////mCard = trump Card///////////////////////
    public boolean isMarraige1(Card mCard) {
        int i, j;
        boolean isQueen = false;
        boolean isKing = false;

        int siz = Game.ply1.size();

        for (i = 0; i < siz; i++) {

            if (!"2".equals(Game.ply1.elementAt(i).getFaceName())) {
                if (Game.ply1.elementAt(i).getSuit().equals(mCard.getSuit())) {
                    if (Game.ply1.elementAt(i).getFaceNum() == 12) {
                        isQueen = true;
                    } else if (Game.ply1.elementAt(i).getFaceNum() == 13) {
                        isKing = true;
                    }
                }
            }
        }
        return isKing && isQueen;
    }

    public boolean isMarraige2(Card mCard) {
        int i, j;
        boolean isQueen = false;
        boolean isKing = false;

        int siz = Game.ply2.size();

        for (i = 0; i < siz; i++) {

            if (!"2".equals(Game.ply2.elementAt(i).getFaceName())) {
                if (Game.ply2.elementAt(i).getSuit().equals(mCard.getSuit())) {
                    if (Game.ply2.elementAt(i).getFaceNum() == 12) {
                        isQueen = true;
                    } else if (Game.ply2.elementAt(i).getFaceNum() == 13) {
                        isKing = true;
                    }
                }
            }
        }
        return isKing && isQueen;
    }

    public boolean isMarraige3(Card mCard) {
        int i, j;
        boolean isQueen = false;
        boolean isKing = false;

        int siz = Game.ply3.size();

        for (i = 0; i < siz; i++) {

            if (!"2".equals(Game.ply3.elementAt(i).getFaceName())) {
                if (Game.ply3.elementAt(i).getSuit().equals(mCard.getSuit())) {
                    if (Game.ply3.elementAt(i).getFaceNum() == 12) {
                        isQueen = true;
                    } else if (Game.ply3.elementAt(i).getFaceNum() == 13) {
                        isKing = true;
                    }
                }
            }
        }
        return isKing && isQueen;
    }

    public boolean isMarraige4(Card mCard) {
        int i, j;
        boolean isQueen = false;
        boolean isKing = false;

        int siz = Game.ply4.size();

        for (i = 0; i < siz; i++) {

            if (!"2".equals(Game.ply4.elementAt(i).getFaceName())) {
                if (Game.ply4.elementAt(i).getSuit().equals(mCard.getSuit())) {
                    if (Game.ply4.elementAt(i).getFaceNum() == 12) {
                        isQueen = true;
                    } else if (Game.ply4.elementAt(i).getFaceNum() == 13) {
                        isKing = true;
                    }
                }
            }
        }
        return isKing && isQueen;
    }
 
    public int startGame(int num, Card c) {
        //PlayerOne.cnt = 0;
        int j = 0;
        int count = 0;
        int cc = 0; //for tracking the first color card

        for (int i = 0; i < 4; i++) {

            System.out.print("");
            count += Server1.temp[i].getFaceValue();

        }

        for (int i = 1; i < 4; i++) {

            if ((Server1.temp[j].getSuit() == null ? c.getSuit() == null : Server1.temp[j].getSuit().equals(c.getSuit())) && (cc == 0)) {

                if ((Server1.temp[j].getFaceValue() == Server1.temp[i].getFaceValue()) && (Server1.temp[j].getSuit() == null ? Server1.temp[i].getSuit() == null : Server1.temp[j].getSuit().equals(Server1.temp[i].getSuit()))) {
                    if (Server1.temp[i].getFaceNum() == 14) {
                        j = i;
                    } else {

                        if (Server1.temp[j].getFaceNum() < Server1.temp[i].getFaceNum()) {
                            j = i;
                        }
                    }

                } else if ((Server1.temp[j].getFaceValue() < Server1.temp[i].getFaceValue()) && (Server1.temp[j].getSuit() == null ? Server1.temp[i].getSuit() == null : Server1.temp[j].getSuit().equals(Server1.temp[i].getSuit()))) {

                    j = i;
                }
            } else if ((Server1.temp[i].getSuit() == null ? c.getSuit() == null : Server1.temp[i].getSuit().equals(c.getSuit())) && Server1.isTrumpShown) {

                if (cc == 0) {
                    tmp = Server1.temp[i];
                    j = i;
                    System.out.println("1st color " + j);
                    cc++;
                } else {

                    if (tmp.getFaceValue() < Server1.temp[i].getFaceValue()) {
                        j = i;
                        System.out.println("2nd color " + j);
                    } else if (tmp.getFaceValue() == Server1.temp[i].getFaceValue()) {

                        if (Server1.temp[i].getFaceNum() == 14) {
                            j = i;
                        } else {

                            if (tmp.getFaceNum() < Server1.temp[i].getFaceNum()) {
                                j = i;
                            }
                        }
                    }
                }

            } else {

                if (Server1.temp[j].getSuit() == null ? Server1.temp[i].getSuit() == null : Server1.temp[j].getSuit().equals(Server1.temp[i].getSuit())) {

                    if (Server1.temp[j].getFaceValue() < Server1.temp[i].getFaceValue()) {
                        System.out.println("checking " + j);
                        j = i;
                        System.out.println("After check " + j);
                    } else if (Server1.temp[j].getFaceValue() == Server1.temp[i].getFaceValue()) {

                        if (Server1.temp[i].getFaceNum() == 14) {
                            j = i;
                        } else {

                            if (Server1.temp[j].getFaceNum() < Server1.temp[i].getFaceNum()) {
                                j = i;
                            }
                        }
                    }
                }
            }
        }

//        int val;
//        val = (num + j) % 4;
//
//        if (val == 0) {
//            val = 4;
//        }

        if (j%2==1) {
            team1 += count;
        } else {
            team2 += count;
        }

        //PlayerOne.scoreprint1(team1);
        //PlayerOne.scoreprint2(team2);

        //System.out.println("Player " + val + " wins");

        System.out.println("team1 (Player 2 & 4) " + team1);
        System.out.println("team2 (player 1 & 3) " + team2);
        System.out.println();

        return (j+1);
    }

}
