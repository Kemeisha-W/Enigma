package src;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;

public class InfoPanel extends JPanel {
    ButtonCustom returnB = new ButtonCustom();
    public InfoPanel() {
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbCon = new GridBagConstraints();
        gbCon.insets = new Insets(5,3,5,3);
        gbCon.gridx = 0;
        gbCon.gridy = 1;
        setLayout(gb);
        ImageIcon imageIcon = new ImageIcon("Assets/images/return-30.png");
        returnB.setIcon(imageIcon);
        returnB.setRound(30);
        add(returnB,gbCon);
        String info = "Enigma is a puzzle game. There will be a riddle displayed at the top of a board. " +
                "The answer to each riddle is a number. To win you must answer most riddles correctly without " +
                "losing all your hearts. There are eight riddles and 3 Hearts. Please enter a name before" +
                " you click start ";
        gbCon.gridy = 2;
        JLabel infoL = new JLabel();
        infoL.setMaximumSize(new Dimension(800,900));
        infoL.setFont(new Font("Serif",Font.PLAIN,25));
        infoL.setText("<html>"+ info +"</html>");
        add(infoL,gbCon);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Font f1 = new Font("Serif",Font.PLAIN,30);
        g2.setFont(f1);
        g2.setColor(Color.BLACK);
        TextLayout textLayout = new TextLayout("To start the game press the Start Game button", f1,g2.getFontRenderContext());
        textLayout.draw(g2,getWidth()/5+60,100);

    }
}
