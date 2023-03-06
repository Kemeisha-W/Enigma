package src;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;

public class MenuPanel extends JPanel {
    public ButtonCustom englishB;
    public ButtonCustom hardB;
    public ButtonCustom wordB;
    public ButtonCustom mathB;
    public ButtonCustom funnyB;
    public MenuPanel() {
        setBackground(new Color(244, 190, 130));

        //Create Buttons for menu Panel
        englishB = new ButtonCustom();
        englishB.setText("English");

        funnyB = new ButtonCustom();
        funnyB.setText("Funny");

        wordB = new ButtonCustom();
        wordB.setText("Words");

        mathB = new ButtonCustom();
        mathB.setText("Math");

        hardB = new ButtonCustom();
        hardB.setText("Hard");

        //Add Layout constrictions
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gcon = new GridBagConstraints();
        gcon.fill = GridBagConstraints.HORIZONTAL;
        gcon.insets = new Insets(5,3,5,3);

        //Create Label
        JLabel label = new JLabel("Choose your category");
        label.setPreferredSize(new Dimension(500, 50));
        label.setFont(new Font("Arial", Font.BOLD, 45 ));

        englishB.setPreferredSize(new Dimension(200, 50));
        englishB.setFont(new Font("Arial", Font.PLAIN, 20 ));
        englishB.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 2));
        englishB.setRound(30);
        englishB.setHorizontalAlignment(SwingConstants.CENTER);

        funnyB.setPreferredSize(new Dimension(200, 50));
        funnyB.setFont(new Font("Arial", Font.PLAIN, 20 ));
        funnyB.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 2));
        funnyB.setHorizontalAlignment(SwingConstants.CENTER);
        funnyB.setRound(30);

        wordB.setPreferredSize(new Dimension(200, 50));
        wordB.setFont(new Font("Arial", Font.PLAIN, 20 ));
        wordB.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 2));
        wordB.setHorizontalAlignment(SwingConstants.CENTER);
        wordB.setRound(30);

        mathB.setPreferredSize(new Dimension(200, 50));
        mathB.setFont(new Font("Arial", Font.PLAIN, 20 ));
        mathB.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 2));
        mathB.setHorizontalAlignment(SwingConstants.CENTER);
        mathB.setRound(30);

        hardB.setPreferredSize(new Dimension(200, 50));
        hardB.setFont(new Font("Arial", Font.PLAIN, 20 ));
        hardB.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 2));
        hardB.setHorizontalAlignment(SwingConstants.CENTER);
        hardB.setRound(30);

        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        setLayout(gb);
        //label
        gcon.gridx = 0;
        gcon.gridy = 0;
        add(label, gcon);


        //button
        gcon.gridx = 0;
        gcon.gridy = 1;
        add(englishB, gcon);
        gcon.gridy = 2;
        add(funnyB, gcon);
        gcon.gridy = 3;
        add(wordB, gcon);
        gcon.gridy = 4;
        add(mathB, gcon);
        gcon.gridy = 5;
        add(hardB, gcon);

    }

//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        Font f1 = new Font("Serif",Font.PLAIN,30);
//        g2.setFont(f1);
//        g2.setColor(Color.BLACK);
//        TextLayout textLayout = new TextLayout("To start the game press the Start Game button", f1,g2.getFontRenderContext());
//        textLayout.draw(g2,getWidth()/5+60,100);
//
//        Font f2 = new Font("Serif",Font.PLAIN,25);
//        g2.setFont(f2);
//        g2.setColor(Color.BLACK);
//        g2.drawString("Enigma is a puzzle game.",465,300);
//        g2.drawString("There will be a riddle displayed at the top  ",400,350);
//        g2.drawString("of a board. The answer to each riddle is a number. To win ",360,400);
//        g2.drawString(" you must answer most riddles correctly without losing all your hearts ",303,450);
//        g2.drawString("There are eight riddles and 3 Hearts. Please enter a name before you click start ",260,500);
//        g2.dispose();
//
//    }
}



