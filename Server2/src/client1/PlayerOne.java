package client1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class PlayerOne {

    /////////////////////////////variables and arrays////////////////////
    private JButton[] button;
    public static JButton[] button2;

    public static Card[] temp;
    private String s;
    public static int x, t, cnt, traceCount;
    public static boolean flag1, trumpFlag1;
    public static Card tcard;
    public static JButton trumpButton;
    public static Card tempPlayCard;
    private int rmv, greaterMove;

    //////////////////////ends///////////////////////////
    public JFrame frame;
    public static JTextField tf;
    public static JTextField tf1;
    public static JPanel contentPane;
    private JTextField callText;
    private JTextField pointTeam1;
    private JTextField pointTeam2;
    public static JTextField playerShow;
    private JTextField score1;
    private JTextField score2;
    public static boolean doubleFlag1, okFlag1;
    private JFrame doubleFrame1;
    //private JButton pointTeam1;
    //private JButton pointTeam2;

    JFrame callFrame1;

    PlayerOne() throws IOException {
        temp = new Card[4];
        flag1 = false;
        trumpButton = new JButton();
        trumpFlag1 = false;
        button = new JButton[8];
        button2 = new JButton[4];
        x = 0; t = 0; cnt = 0; traceCount = 0; rmv = 0; greaterMove = 0;
        frame = new JFrame("Player two");
        tf = new JTextField();
        tf1 = new JTextField();
        contentPane = new JPanel();
        callFrame1 = new JFrame("Player two Call");
        callText  = new JTextField();
        pointTeam1 = new JTextField();
        pointTeam2 = new JTextField();
        playerShow = new JTextField();
        score1 = new JTextField();
        score2 = new JTextField();
        doubleFlag1 = false;
        okFlag1 = false;
        doubleFrame1 = new JFrame("Double Frame");
    }
    
    public void frameDispose() {
        callFrame1.dispose();
    }
    
    public void mainFrameDispose() {
        frame.dispose();
    }

    

    public void trumpButtonFalse1() {
        trumpButton.setVisible(false);
    }

    

    public void buttonFalse() {
        for (int i = 0; i < 4; i++) {
            button2[i].setVisible(false);
        }
    }

    public void panelCreate1(Card[] receivedFromClientPlay) throws IOException {

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.setOpaque(true);
        contentPane.setBackground(new Color(186, 103, 82));
        contentPane.setLayout(null);
        ButtonHandler xyz = new ButtonHandler();
        trumpButton.addActionListener(xyz);
        trumpButton.setSize(100, 150);
        trumpButton.setLocation(50, 250);
        trumpButton.setIcon(new ImageIcon("trump.jpg"));
        contentPane.add(trumpButton);

        for (int i = 0; i < 8; i++) {
            //////////////////////////////player1 properties///////////////////////////

            button[i] = new JButton();
            button[i].addActionListener(xyz);
            button[i].setSize(100, 150);
            button[i].setLocation(50 + (i * 100) + 10 * i, 530);

            ////////////////////////////////////////////////////////////////////////////
            ///////////////////player 1/////////////////
            s = receivedFromClientPlay[i].setCardImage(receivedFromClientPlay[i]);
            button[i].setIcon(new ImageIcon(s));
            contentPane.add(button[i]);

        }
        for (int i = 0; i < 4; i++) {
            button2[i] = new JButton();
            button2[i].setSize(100, 150);
            button2[i].setVisible(false);
            switch (i) {
                case 0:
                    button2[i].setLocation(400 + (i * 100) + 10 * i, 200);
                    break;
                case 1:
                    button2[i].setLocation(350 + (i * 100) + 10 * i, 275);
                    break;
                case 2:
                    button2[i].setLocation(180 + (i * 100) + 10 * i, 350);
                    break;
                case 3:
                    button2[i].setLocation(0 + (i * 100) + 10 * i, 275);
                    break;
                default:
                    break;
            }
            contentPane.add(button2[i]);

        }

        for (int i = 0; i < 4; i++) {
            button2[i].setVisible(false);
            System.out.println("Button false hoitese");
        }

        ////////////////////////////////////////////JFrame Properties///////////////////////////////////////////
        frame.setContentPane(contentPane);
        frame.setSize(1000, 750);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    
    public void totalPoint(String scr1, String scr2) {
        contentPane.add(score1);
        contentPane.add(score2);
        score1.setVisible(true);
        score2.setVisible(true);
        score1.setSize(200, 50);
        score2.setSize(200, 50);
        score1.setLocation(20,110);
        score2.setLocation(790,110);
        
        score1.setEditable(false);
        score2.setEditable(false);
        score1.setFont(new Font("Consolas", Font.BOLD, 20));
        score2.setFont(new Font("Consolas", Font.BOLD, 20));
        score1.setBackground(new Color(186, 103, 82));
        score2.setBackground(new Color(186, 103, 82));
        score1.setForeground(Color.BLACK);
        score2.setForeground(Color.BLACK);
        score1.setText("Team 1 Score: " + scr1);
        score2.setText("Team 2 Score: " + scr2);
    } 
    
    public void pointShow(String team1, String team2) {
        contentPane.add(pointTeam1);
        contentPane.add(pointTeam2);
        pointTeam1.setVisible(true);
        pointTeam2.setVisible(true);
        pointTeam1.setSize(200, 50);
        pointTeam2.setSize(200, 50);
        pointTeam1.setLocation(20,50);
        pointTeam2.setLocation(790,50);
        
        pointTeam1.setEditable(false);
        pointTeam2.setEditable(false);
        pointTeam1.setFont(new Font("Consolas", Font.PLAIN, 20));
        pointTeam2.setFont(new Font("Consolas", Font.PLAIN, 20));
        pointTeam1.setBackground(new Color(186, 103, 82));
        pointTeam2.setBackground(new Color(186, 103, 82));
        pointTeam1.setForeground(Color.BLACK);
        pointTeam2.setForeground(Color.BLACK);
        pointTeam1.setText("Team 1 (2 & 4): " + team1);
        pointTeam2.setText("Team 2 (1 & 3): " + team2);
        
    }
    
    public void playerShw(String s) {
        contentPane.add(playerShow);
        playerShow.setVisible(true);
        playerShow.setSize(220, 80);
        playerShow.setLocation(380,100);
        playerShow.setEditable(false);
        playerShow.setFont(new Font("Consolas", Font.PLAIN, 25));
        playerShow.setBackground(new Color(186, 103, 82));
        playerShow.setForeground(Color.BLACK);
        playerShow.setText("Player " + s + " wins");
    }
    
    public void invisibleCallTxt() {
        callText.setVisible(false);
    }
    
    public void callTxt(int topCall, int topPlayer) {
        contentPane.add(callText);
        callText.setVisible(true);
        callText.setSize(370, 50);
        callText.setLocation(300,30);
        callText.setEditable(false);
        callText.setFont(new Font("Consolas", Font.PLAIN, 20));
        callText.setBackground(new Color(186, 103, 82));
        callText.setForeground(Color.BLACK);
        callText.setText("Top Bidder: Player " + String.valueOf(topPlayer) + " -> Call: " + String.valueOf(topCall));
    }
    
    public void dblFrame() {
        
        doubleFlag1 = false;
        okFlag1 = false;
        doubleFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel doublePanel = new JPanel();
        doublePanel.setOpaque(true);
        doublePanel.setBackground(new Color(186, 103, 82));
        doublePanel.setLayout(null);
        JButton doubleButton = new JButton();
        JButton okButton = new JButton();
        doublePanel.add(doubleButton);
        doublePanel.add(okButton);
        doubleButton.setSize(300, 100);
        doubleButton.setLocation(350, 250);
        doubleButton.setFont(new Font("Consolas", Font.PLAIN, 30));
        doubleButton.setText("Set Double");
        doubleButton.setBackground(Color.BLACK);
        doubleButton.setForeground(Color.white);
        
        doubleButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            doubleFlag1 = true;
            doubleFrame1.dispose();
            try {
                Clients.os.writeUTF("doubleok");
                Clients.os.flush();
            } catch (IOException ex) {
                Logger.getLogger(PlayerOne.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        okButton.setSize(300, 100);
        okButton.setLocation(350, 350);
        okButton.setFont(new Font("Consolas", Font.PLAIN, 30));
        okButton.setText("Ok");
        okButton.setBackground(Color.BLACK);
        okButton.setForeground(Color.white);
        
        okButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            okFlag1 = true;
            doubleFrame1.dispose();
        });
        
        doubleFrame1.setContentPane(doublePanel);
        doubleFrame1.setSize(1000, 750);
        doubleFrame1.setLocationByPlatform(true);
        doubleFrame1.setVisible(true);
        doubleFrame1.setResizable(false);
    }

    public void invisibleMainFrame1() {
        frame.setVisible(false);

    }

    public void invisibleFrame1() {
        callFrame1.setVisible(false);
    }

    public void callOne(Card[] receivedFromClientCall) throws IOException {

        //cCard1 = Game.callCard1;
        JPanel callPanel1 = new JPanel();

        callFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        callPanel1.setOpaque(true);
        callPanel1.setBackground(new Color(186, 103, 82));
        callPanel1.setLayout(null);

        JButton[] callButton1 = new JButton[4];

        for (int i = 0; i < 4; i++) {
            //////////////////////////////player1 properties///////////////////////////
            callButton1[i] = new JButton();
            callButton1[i].setSize(100, 150);
            callButton1[i].setLocation(280 + (i * 100) + 10 * i, 330);
            ////////////////////////////////////////////////////////////////////////////

            ///////////////////player 1/////////////////
            s = receivedFromClientCall[i].setCardImage(receivedFromClientCall[i]);
            callButton1[i].setIcon(new ImageIcon(s));

            callPanel1.add(callButton1[i]);

        }

        callFrame1.setContentPane(callPanel1);
        callFrame1.setSize(1000, 750);
        callFrame1.setLocationByPlatform(true);
        callFrame1.setVisible(true);
        callFrame1.setResizable(false);

    }

    private class ButtonHandler implements ActionListener {

        private int p = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            tcard = new Card("Spades", "2", 0, 2);
            for (int i = 0; i < 8; i++) {

                if (source == trumpButton) {
                    //trumpFlag1 = true;
                    trumpButton.setIcon(TrumpCard.trumpIcon);
                    trumpButton.setVisible(true);
                    try {
                        Clients.os.writeUTF("trumpTrue");
                        Clients.os.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(PlayerOne.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                if (source == button[i]) {
                    if (x == 4) {
                        x = 0;
                    }

                    if (t == 4) {
                        t = 0;
                    }

                    if (rmv == 8) {

                        rmv = 0;
                        greaterMove = 0;
                    }

                    p %= 4;

                    if (cnt != 0) {

                        Card first = Clients.card1st;
                        if (Clients.cardTempPlay[i].getSuit() == null ? first.getSuit() == null : Clients.cardTempPlay[i].getSuit().equals(first.getSuit())) {

                            //cnt++;
                            button2[x].setVisible(true);
                            button2[x].setIcon(button[i].getIcon());
                            tempPlayCard = Clients.cardTempPlay[i];
                            // PlayerOne.traceCount = 0;
                            //System.out.println(PlayerOne.tempPlayCard.getSuit());
                            try {
                                Clients.os.writeUTF("" + PlayerOne.tempPlayCard.getSuit() + " " + PlayerOne.tempPlayCard.getFaceName() + " " + PlayerOne.tempPlayCard.getFaceValue() + " " + PlayerOne.tempPlayCard.getFaceNum());
                                Clients.os.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(PlayerOne.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //temp[t] = Clients.cardTempPlay[i];
                            //temp[t] = Clients.cardTempPlay[i];
                            

                            Clients.ply1.set(i, tcard);

                            t++;
                            x++;
                            x %= 4;
                            button[i].setVisible(false);

                        } else {

                            int s = Clients.ply1.size();
                            int k;
                            for (k = 0; k < s; k++) {

                                if (!"2".equals(Clients.ply1.elementAt(k).getFaceName())) {

                                    if (Clients.ply1.elementAt(k).getSuit() == null ? first.getSuit() == null : Clients.ply1.elementAt(k).getSuit().equals(first.getSuit())) {
                                        break;
                                    }

                                }
                            }
                            if (k == s) {

                                //cnt++;
                                button2[x].setVisible(true);
                                button2[x].setIcon(button[i].getIcon());
                                //temp[t] = Clients.cardTempPlay[i];                                
                                tempPlayCard = Clients.cardTempPlay[i];
                                try {
                                    Clients.os.writeUTF("" + PlayerOne.tempPlayCard.getSuit() + " " + PlayerOne.tempPlayCard.getFaceName() + " " + PlayerOne.tempPlayCard.getFaceValue() + " " + PlayerOne.tempPlayCard.getFaceNum());
                                    Clients.os.flush();
                                } catch (IOException ex) {
                                    Logger.getLogger(PlayerOne.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Clients.ply1.set(i, tcard);

                                t++;
                                x++;
                                x %= 4;
                                button[i].setVisible(false);
                            }
                        }
                    } else {

                        //cnt++;
                        button2[x].setVisible(true);
                        button2[x].setIcon(button[i].getIcon());
                        //temp[t] = Clients.cardTempPlay[i];
                        System.out.println("before " + Clients.cardTempPlay[i].getSuit());
                        tempPlayCard = Clients.cardTempPlay[i];
                        System.out.println("After " + tempPlayCard);
                        try {
                            Clients.os.writeUTF("" + PlayerOne.tempPlayCard.getSuit() + " " + PlayerOne.tempPlayCard.getFaceName() + " " + PlayerOne.tempPlayCard.getFaceValue() + " " + PlayerOne.tempPlayCard.getFaceNum());
                            Clients.os.flush();
                        } catch (IOException ex) {
                            Logger.getLogger(PlayerOne.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Clients.ply1.set(i, tcard);

                        t++;
                        x++;
                        x %= 4;
                        button[i].setVisible(false);

                    }

                    traceCount++;

                    if (cnt == 1) {
                        flag1 = true;
                    }

                }
            }
        }
    }

}
