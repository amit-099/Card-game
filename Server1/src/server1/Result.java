
package server1;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Result {
    private JFrame resFrame = new JFrame("Result");
    private JPanel resPanel = new JPanel();
    JTextField resText = new JTextField();
    
    public void resultFrame(int num) {
        resFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resPanel.setOpaque(true);
        resPanel.setBackground(new Color(186, 103, 82));
        resPanel.setLayout(null);
        
        resPanel.add(resText);
        resText.setVisible(true);
        resText.setSize(540, 100);
        resText.setLocation(220,250);
        resText.setEditable(false);
        resText.setFont(new Font("Consolas", Font.PLAIN, 40));
        resText.setBackground(Color.BLACK);
        resText.setForeground(Color.WHITE);
        
        resText.setText("Team " + String.valueOf(num) + " has won the match");
        
        resFrame.setContentPane(resPanel);
        resFrame.setSize(1000, 750);
        resFrame.setLocationByPlatform(true);
        resFrame.setVisible(true);
        resFrame.setResizable(false);
    }
}
