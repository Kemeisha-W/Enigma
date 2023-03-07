package src;

import javax.swing.*;
import java.awt.*;

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
        GridBagConstraints gbCon = new GridBagConstraints();
        gbCon.fill = GridBagConstraints.HORIZONTAL;
        gbCon.insets = new Insets(5,3,5,3);

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
        gbCon.gridx = 0;
        gbCon.gridy = 0;
        add(label, gbCon);


        //button
        gbCon.gridx = 0;
        gbCon.gridy = 1;
        add(englishB, gbCon);
        gbCon.gridy = 2;
        add(funnyB, gbCon);
        gbCon.gridy = 3;
        add(wordB, gbCon);
        gbCon.gridy = 4;
        add(mathB, gbCon);
        gbCon.gridy = 5;
        add(hardB, gbCon);

    }

}



