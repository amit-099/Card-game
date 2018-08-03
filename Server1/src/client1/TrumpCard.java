package client1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrumpCard {
    
    public JFrame trumpFrame;
    JPanel trumpPanel;
    JButton[] trumpButton;
    public static ImageIcon trumpIcon;
    
    public static int trumpTotal;
    
    public static Card finalTrump;
    
    Card[] trumpCard;
    
    TrumpCard() {
        trumpFrame = new JFrame("Trump Card");
        trumpPanel = new JPanel();
        trumpButton = new JButton[4];
        trumpCard = new Card[4];
        trumpTotal = 0;
        trumpCard[0] = new Card("Spades", "Ace", 1, 14);
        trumpCard[1] = new Card("Clubs", "Ace", 1, 14);
        trumpCard[2] = new Card("Hearts", "Ace", 1, 14);
        trumpCard[3] = new Card("Diamonds", "Ace", 1, 14);
    }
    
    public void invisibleTrumpFrame() {
        trumpFrame.setVisible(false);
    }
    
    public void selectTrump() {
        
        trumpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        trumpPanel.setOpaque(true);
        trumpPanel.setBackground(new Color(186, 103, 82));
        trumpPanel.setLayout(null);
        
        ButtonHandlerTrump bTrump = new ButtonHandlerTrump();
        
        for (int i = 0; i < 4; i++) {
            //////////////////////////////player1 properties///////////////////////////
            trumpButton[i] = new JButton();
            trumpButton[i].setSize(100, 150);
            trumpButton[i].setLocation(280 + (i * 100) + 10 * i, 330);
            trumpButton[i].addActionListener(bTrump);
            ////////////////////////////////////////////////////////////////////////////

            ///////////////////player 1/////////////////
            String trumpStr = trumpCard[i].setCardImage(trumpCard[i]);
            trumpButton[i].setIcon(new ImageIcon(trumpStr));
            
            trumpPanel.add(trumpButton[i]);
            
        }
        
        trumpFrame.setContentPane(trumpPanel);
        trumpFrame.setSize(1000, 750);
        trumpFrame.setLocationByPlatform(true);
        trumpFrame.setVisible(true);
        trumpFrame.setResizable(false);
        
    }
    
    private class ButtonHandlerTrump implements ActionListener {
        
        private int p = 0;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            
            for (int i = 0; i < 4; i++) {
                
                if(trumpTotal == 1)
                    trumpTotal = 0;
                
                if (source == trumpButton[i]) {
                    trumpFrame.dispose();
                    finalTrump = trumpCard[i];
                    try {
                        Clients.os.writeUTF("" + TrumpCard.finalTrump.getSuit() + " " + TrumpCard.finalTrump.getFaceName() + " " + TrumpCard.finalTrump.getFaceValue() + " " + TrumpCard.finalTrump.getFaceNum());
                        Clients.os.flush();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(TrumpCard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(finalTrump);                    
                    //trumpTotal++;
                    //System.out.println("TrumpTotal = " + trumpTotal);
                    trumpIcon = (ImageIcon) trumpButton[i].getIcon();
                    //System.out.println(trumpIcon);
                    
                    break;
                }
            }
            
//            if (trumpTotal == 1) {
//                //trumpFrame.setVisible(false);
//                //trumpTotal = 0;
//                trumpFrame.dispose();
//                System.out.println("Trump Frame visited");
//                
//            }
        }
    }
    
    
}
