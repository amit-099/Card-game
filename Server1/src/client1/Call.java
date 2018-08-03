package client1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Call {

    JButton[][] button;
    private JFrame frame;
    String str[][] = {{"16", "17", "18", "19", "20"}, {"21", "22", "23", "24", "25"}, {"26", "27", "28", "", ""}};
    int k;
    public static JPanel contentPane;
    public static Card card;//trump Card//////////////////////////////
    public static int track, total;
    public static int[] player;
    public static int maxCall;

    private boolean isPlayer1, isPlayer2, isPlayer3, isPlayer4;

    JButton bid;
    JButton pass;
    JButton calling;

    private int tracePlayer;

    Call() {
        button = new JButton[3][5];
        frame = new JFrame("Player One");
        k = 250;
        contentPane = new JPanel();
        card = new Card("Spades", "7", 0, 7);
        track = 1;
        total = 0;
        bid = new JButton();
        pass = new JButton();
        calling = new JButton();
        isPlayer1 = isPlayer2 = isPlayer3 = isPlayer4 = false;
        player = new int[5];
        tracePlayer = 1;
    }

    public void invisibleCallFrame() {
        frame.setVisible(false);
        System.out.println("call frame");
    }

    public void callCard() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.setOpaque(true);
        contentPane.setBackground(new Color(186, 103, 82));

        //tryLabel.x();
        contentPane.setLayout(null);

        ButtonHandler xyz = new ButtonHandler();

        /////////////display call/////////////////
        /*calling.setSize(250, 50);
        //calling.setText("Player " + num + "Calling");
        Font font = new Font("Verdana", 1, 20);

        calling.setFont(font);

        calling.setLocation(350, 100);
        calling.setBackground(new Color(186, 103, 82));
        contentPane.add(calling);*/
        //////////////////////////////////////////
        pass.setSize(250, 50);
        pass.setText("PASS");
        pass.addActionListener(xyz);
        pass.setLocation(350, 400);
        bid.setSize(250, 50);
        bid.setText("BID");

        bid.setLocation(350, 200);
        bid.setBackground(Color.LIGHT_GRAY);
        contentPane.add(bid);
        pass.setBackground(Color.LIGHT_GRAY);
        contentPane.add(pass);

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 5; i++) {

                button[j][i] = new JButton();

                button[j][i].setSize(50, 50);
                button[j][i].setLocation(350 + (i * 50), k);
                button[j][i].setText(str[j][i]);
                button[j][i].addActionListener(xyz);
                String s1 = button[j][i].getText();
                //System.out.println(s1);
                button[j][i].setBackground(Color.LIGHT_GRAY);
                contentPane.add(button[j][i]);

            }
            k += 50;

        }

        frame.setContentPane(contentPane);
        frame.setSize(1000, 750);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    /*public int callWinner(int p1, int p2, int val) {
        
        
    }*/
    private class ButtonHandler implements ActionListener {

        private int p = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == pass) {
                frame.dispose();
                maxCall = 0;
                //player[track] = 0;
                //System.out.println("value " + player[track]);
                track++;
                total++;
                try {
                    Clients.os.writeUTF("" + Call.maxCall);
                    Clients.os.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Call.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (track == 5) {
                    track = 1;
                }

            } else {
                for (int j = 0; j < 3; j++) {
                    for (int i = 0; i < 5; i++) {
                        if (source == button[j][i]) {
                            frame.dispose();
                            String text = button[j][i].getText();
                            int valueOfText = Integer.parseInt(text);

                            //player[track] = valueOfText;
                            maxCall = valueOfText;
                            try {
                                Clients.os.writeUTF("" + Call.maxCall);
                                Clients.os.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(Call.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //System.out.println("value " + player[track]);
                            track++;
                            if (track == 5) {
                                track = 1;
                            }
                            total++;
                            break;

                        }
                    }
                }
            }

//            if (total == 1) {
//                //frame.setVisible(false);
//                frame.dispose();
//                //total = 0;
//                System.out.println("Total checking");
//            }

            //System.out.println("total: " + total );
        }
    }

}
