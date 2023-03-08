package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static src.GameWindow.soundManager;

public class GamePanel extends JPanel {
    private GameBoard gameBoard;
    private JPanel heartPanel;
    private Player player;
    private BufferedImage image;
    private ImageIcon imageIcon;
    private DisintegrateFX disintegrate;
    private GrayScaleFX grayScale;
    private JTextField answerT;
    private final String heartImage = "Assets/images/heart_icon.png";
    //Buttons
    ButtonCustom restartB;
    ButtonCustom hintB;
//    ButtonCustom submitB;
    ButtonCustom exitB;

    public GamePanel(GameWindow.CATEGORY c){
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
        heartsLeft.setBackground(GameWindow.background);
        gbCon.gridx=0;
        gbCon.gridy=0;
        heartPanel.setBackground(GameWindow.background);
        add(heartPanel, gbCon);


        //Create Game Board
        this.gameBoard = new GameBoard(player, c);

//        Canvas board = new Canvas() {
//            @Override
//            public void paint(Graphics g) {
//                super.paint(g);
//                System.out.println("Updating canvas");
//
//                // Display initial Board
//                Graphics2D g2 = (Graphics2D) g;
//                int count = 0;
//                for (int col = 0; col < gameBoard.COLS; col++) {
//                    int x = gameBoard.SPACING + gameBoard.SPACING * col + Disk.WIDTH * col + 5;
//                    gameBoard.value[count] = new BoardSpace(col);
//                    gameBoard.value[count].x = x;
//                    gameBoard.value[count].colNumber = col;
//                    g2.setColor(gameBoard.value[count].color);
//                    Rectangle2D.Double initSpace = new Rectangle2D.Double(x, 0, Disk.WIDTH, 750);
//                    g2.fill(initSpace);
//                    g2.draw(initSpace);
//                    count++;
//                }
//                g2.dispose();
//            }
//        };

//        gameBoard.setCanvas(board);
        gameBoard.setPreferredSize(new Dimension(1000, 719));
//        gameBoard.add(board);

        gbCon.gridx=0;
        gbCon.gridy=2;
        add(gameBoard, gbCon);
        setBackground(GameWindow.background);        //Display GameBoard and Player turn


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

        answerT = new JTextField();
        gbCon.gridx = 3;


        exitB = new ButtonCustom();
        imageIcon = new ImageIcon("Assets/images/exit.png");
        exitB.setIcon(imageIcon);
        exitB.setText("Exit");
        exitB.setRound(30);
        gbCon.gridx = 3;
        detailsPanel.add(exitB);
        detailsPanel.setBackground(GameWindow.background);
        gbCon.gridx=0;
        gbCon.gridy=4;
        gbCon.fill = GridBagConstraints.HORIZONTAL;
        add(detailsPanel, gbCon);

    }


    public void lostHeart(Graphics2D g2) {
        disintegrate = new DisintegrateFX(heartPanel,heartImage);
        grayScale = new GrayScaleFX(heartPanel);
        disintegrate.draw(g2);
        grayScale.draw(g2);
    }
    public void endGame(){
        gameBoard.endGame();

    }
    public void startGame() {
        gameBoard.startGame();
    }
    public void restartGame(){
        gameBoard.restartGame();
    }
}
