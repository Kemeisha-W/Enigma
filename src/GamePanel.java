package src;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private GameBoard gameBoard;
    private JPanel heartPanel;
    private Player player;
    private BufferedImage image;
    private ImageIcon imageIcon;
    private DisintegrateFX disintegrate;
    private GrayScaleFX grayScale;
    private final String heartImage = "Assets/images/heart_icon.png";
    //Buttons
    ButtonCustom restartB;
    ButtonCustom hintB;
    ButtonCustom submitB;

    public GamePanel(){

        //Add new player
        player = new Player(11);

        //Add Game Panel Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbCon = new GridBagConstraints();
        gbCon.insets = new Insets(5,3,5,3);

        gbCon.gridx=0;
        gbCon.gridy=0;

        heartPanel= new JPanel();

        Image heartIcon = ImageManager.loadBufferedImage(heartImage);
        JLabel heartL = new JLabel(new ImageIcon(heartIcon));
        heartPanel.setLayout(new GridBagLayout());
        heartPanel.add(heartL,gbCon);
        gbCon.gridx = 1;
        JLabel heartL1 = new JLabel(new ImageIcon(heartIcon));
        heartPanel.add(heartL1,gbCon);
        gbCon.gridx = 2;
        JLabel heartL2 = new JLabel(new ImageIcon(heartIcon));
        heartPanel.add(heartL2,gbCon);
        gbCon.gridx = 5;
        JLabel heartsLeft = new JLabel();
        heartsLeft.setText("You have three hearts left");
        heartsLeft.setBackground(new Color(244, 190, 130));
        gbCon.gridx=0;
        gbCon.gridy=0;
        heartPanel.setBackground(new Color(244, 190, 130));
        add(heartPanel, gbCon);


        //Create Game Board
        this.gameBoard = new GameBoard(player);
        gameBoard.setLayout(new FlowLayout());

        Canvas board = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                System.out.println("Updating canvas");

                // Display initial Board
                Graphics2D g2 = (Graphics2D) g;
                int count = 0;
                for (int col = 0; col < gameBoard.COLS; col++) {
                    int x = gameBoard.SPACING + gameBoard.SPACING * col + Disk.WIDTH * col + 5;
                    gameBoard.value[count] = new BoardSpace(col);
                    gameBoard.value[count].x = x;
                    gameBoard.value[count].colNumber = col;
                    g2.setColor(gameBoard.value[count].color);
                    Rectangle2D.Double initSpace = new Rectangle2D.Double(x, 0, Disk.WIDTH, 750);
                    g2.fill(initSpace);
                    g2.draw(initSpace);
                    count++;
                }
                g2.dispose();
            }
        };
//
//        gameBoard.setCanvas(board);
//        board.setPreferredSize(new Dimension(1000, 800));
//        gameBoard.add(board);
        gameBoard.setBackground(new Color(244, 190, 130));

        gbCon.gridx=0;
        gbCon.gridy=2;
        add(gameBoard, gbCon);
        setBackground(new Color(244, 190, 130));        //Display GameBoard and Player turn


        //Set Details Panel
        JPanel detailsPanel = new JPanel();

        //Initialize Buttons
        restartB = new ButtonCustom();
        imageIcon = new ImageIcon("Assets/images/restart.png");
        restartB.setIcon(imageIcon);
        restartB.setText("Restart Game");
        restartB.setRound(30);
        gbCon.gridx = 1;
        detailsPanel.add(restartB);

        hintB = new ButtonCustom();
        imageIcon = new ImageIcon("Assets/images/hint2.png");
        hintB.setIcon(imageIcon);
        hintB.setRound(30);
        hintB.setText("Hint");
        gbCon.gridx = 2;
        detailsPanel.add(hintB);

        submitB = new ButtonCustom();
        imageIcon = new ImageIcon("Assets/images/ok_icon.png");
        submitB.setIcon(imageIcon);
        submitB.setRound(30);
        submitB.setText("Submit");
        gbCon.gridx = 3;
        detailsPanel.add(submitB);

        detailsPanel.setBackground(new Color(244, 190, 130));
        gbCon.gridx=0;
        gbCon.gridy=3;
        gbCon.fill = GridBagConstraints.HORIZONTAL;
        add(detailsPanel, gbCon);
    }

    public void gameRender() {
        Graphics2D imageContext = (Graphics2D) image.getGraphics();
    }
    public void lostHeart(Graphics2D g2) {
        disintegrate = new DisintegrateFX(heartPanel,heartImage);
        grayScale = new GrayScaleFX(heartPanel);
        disintegrate.draw(g2);
        grayScale.draw(g2);
    }
}
