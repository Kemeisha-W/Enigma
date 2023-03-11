package src;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {
    public ButtonCustom englishB;
    public ButtonCustom hardB;
    public ButtonCustom wordB;
    public ButtonCustom mathB;
    public ButtonCustom funnyB;
    private Font font= new Font("Arial", Font.PLAIN, 20 );
    public MenuPanel() {
        setBackground(GameWindow.background);

        Font buttonF;
        //Create Custom Font
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font f1 = Font.createFont(Font.TRUETYPE_FONT,new File("Assets/fonts/button_shield/Button Shield Personal Use Only.otf"));
            f1.deriveFont(13f);
            ge.registerFont(f1);
            buttonF = new Font("Button Shield Personal Use Only",Font.PLAIN,30);
        }catch(IOException | FontFormatException e){
            //Handle exception
            System.out.println("Message: " + e.getMessage());
            buttonF = new Font("Arial", Font.PLAIN, 20 );
        }

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
        JLabel label = new JLabel("<html>Choose your category</html>", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(500, 100));
        label.setFont(new Font("Button Shield Personal Use Only", Font.BOLD, 50 ));


        englishB.setPreferredSize(new Dimension(200, 50));
        englishB.setFont(buttonF);
        englishB.setRound(30);
        englishB.setHorizontalAlignment(SwingConstants.CENTER);

        funnyB.setPreferredSize(new Dimension(200, 50));
        funnyB.setFont(buttonF);
        funnyB.setHorizontalAlignment(SwingConstants.CENTER);
        funnyB.setRound(30);

        wordB.setPreferredSize(new Dimension(200, 50));
        wordB.setFont(buttonF);
        wordB.setHorizontalAlignment(SwingConstants.CENTER);
        wordB.setRound(30);

        mathB.setPreferredSize(new Dimension(200, 50));
        mathB.setFont(buttonF);
        mathB.setHorizontalAlignment(SwingConstants.CENTER);
        mathB.setRound(30);

        hardB.setPreferredSize(new Dimension(200, 50));
        hardB.setFont(buttonF);
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



