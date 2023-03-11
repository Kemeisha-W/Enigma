package src;

import javax.swing.*;
import java.awt.*;

import static src.GameWindow.soundManager;

public class GamePanel extends JPanel {
    private GridBagConstraints gbCon = new GridBagConstraints();
    private GameBoard gameBoard;
    private JPanel heartPanel;
    private ImageIcon imageIcon;
    private final String heartImage = "Assets/images/heart_icon.png";
    public JLabel heartL;
    public JLabel heartL1;
    public JLabel heartL2;
    public Player player;
    //Buttons
    ButtonCustom restartB;
    ButtonCustom exitB;


    public GamePanel(GameWindow.CATEGORY c){
        //Add new player
        player= new Player();
        //Add Game Panel Layout
        setLayout(new GridBagLayout());

        gbCon.insets = new Insets(5,3,5,3);

        gbCon.gridx=0;
        gbCon.gridy=0;

        heartPanel= new javax.swing.JPanel();

        Image heartIcon = ImageManager.loadBufferedImage(heartImage);
         heartL = new JLabel(new ImageIcon(heartIcon));
        heartPanel.setLayout(new GridBagLayout());
        heartPanel.add(heartL,gbCon);
        gbCon.gridx = 1;
         heartL1 = new JLabel(new ImageIcon(heartIcon));
        heartPanel.add(heartL1,gbCon);
        gbCon.gridx = 2;
         heartL2 = new JLabel(new ImageIcon(heartIcon));
        heartPanel.add(heartL2,gbCon);

        gbCon.gridx=0;
        gbCon.gridy=0;
        heartPanel.setBackground(GameWindow.background);
        add(heartPanel, gbCon);


        //Create Game Board
        this.gameBoard = new GameBoard(c, heartPanel);
        gameBoard.setPreferredSize(new Dimension(1000, 719));

        gbCon.gridx=0;
        gbCon.gridy=2;
        add(gameBoard, gbCon);
        setBackground(GameWindow.background);        //Display GameBoard and Player turn


        //Set Details Panel
        javax.swing.JPanel detailsPanel = new javax.swing.JPanel();

        //Initialize Buttons
        restartB = new ButtonCustom();
        imageIcon = new ImageIcon("Assets/images/restart.png");
        restartB.setIcon(imageIcon);
        restartB.setText("Restart Game");
        restartB.setRound(30);
        gbCon.gridx = 1;
        detailsPanel.add(restartB);

        gbCon.gridx = 2;
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


    public void endGame(Window win){
        GameOverPanel goPanel = new GameOverPanel();
        gameBoard.endGame();
        gameBoard.setVisible(false);
        goPanel.setPreferredSize(new Dimension(1000,719));
        goPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        goPanel.setAnimation(win);
        soundManager.playClip("gameOver",false);
        gbCon.gridx=0;
        gbCon.gridy=2;
        goPanel.setVisible(true);
        add(goPanel,gbCon);

    }
    public void startGame() {
        gameBoard.startGame();
    }
    public void restartGame(){
        gameBoard.restartGame();
        player.hearts = 3;
    }
}
