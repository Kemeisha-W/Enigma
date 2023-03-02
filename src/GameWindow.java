package src;

import javax.swing.*;			// need this for GUI objects
import javax.swing.event.*;
import java.awt.*;			// need this for Layout Managers
import java.awt.event.*;		// need this to respond to GUI events
import java.awt.geom.Rectangle2D;
import static java.awt.event.KeyEvent.VK_ENTER;

public class GameWindow extends JFrame implements ActionListener,KeyListener
{
	private Player player;
	// declare labels
	private JLabel statusL;

	// declare text fields
	private JTextField statusTF;

	private static Boolean changed= false;

	// declare buttons
	private JButton startB;
	private JButton exitB;

	private Container c;

	// Panels
	private JPanel mainPanel;
	private StartPanel startPanel;
	private GameBoard gameBoard;
	private BorderLayout borderLayout;
	private enum STATE{
		START,
		MENU,
		GAME,
	};
	private STATE State = STATE.START;

	@SuppressWarnings({"unchecked"})
	public GameWindow() {
 
		setTitle ("COMP 3609: Assignment 2 Enigma Game");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setSize (screenSize.width/2+300, screenSize.height);
		setLocationRelativeTo(null);

		// create labels
		setFrame(STATE.START);
//		if(State == State.GAME){
//
//			statusL = new JLabel("Enter your name:");
//			statusTF = new JTextField (25);
//			statusTF.setEditable(true);
//
//			// create buttons
//			startB = new JButton ("Start Game");
//			exitB = new JButton ("Quit ");
//
//			// add listener to each button (same as the current object)
//			startB.addActionListener(this);
//			exitB.addActionListener(this);
//			statusTF.addKeyListener(this);
//
//			//Add new player
//			player = new Player(11);
//
//			// create mainPanel
//
//			mainPanel = new JPanel();
//			borderLayout = new BorderLayout();
//			mainPanel.setLayout(borderLayout);
//			GridLayout gridLayout;
//
//
//			Title title = new Title();
//			title.setPreferredSize(new Dimension(getWidth(),70));
//			title.setBackground(new Color(232, 142, 29));
//
//			// create the gamePanel for game entities
//			startPanel = new StartPanel();
//			startPanel.setPreferredSize(new Dimension(850, 800));
//			// create infoPanel
//			JPanel infoPanel = new JPanel();
//			gridLayout = new GridLayout(2, 2);
//			infoPanel.setLayout(gridLayout);
//			infoPanel.setBackground(Color.WHITE);
//
//			// add user interface objects to infoPanel
//			infoPanel.add(statusL);
//			infoPanel.add(statusTF);
//			infoPanel.add(startB);
//			infoPanel.add(exitB);
//
//
//			// add sub-panels with GUI objects to mainPanel and set its colour
//
//			mainPanel.add(title,BorderLayout.NORTH);
//			mainPanel.add(startPanel);
//			mainPanel.add(infoPanel,BorderLayout.SOUTH);
//			mainPanel.setBackground(Color.WHITE);
//
//			// set up mainPanel to respond to mouse
//			startPanel.addKeyListener(this);
//
//			// add mainPanel to window surface
//
//			c = getContentPane();
//			c.add(mainPanel);
//
//		}
		// set properties of window
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void setFrame(STATE State){
		if(State ==STATE.START){
			startPanel = new StartPanel();
			startPanel.setPreferredSize(new Dimension(850, 800));
			mainPanel.add(startPanel);
			mainPanel.setBackground(Color.WHITE);
			// set up mainPanel to respond to mouse
			startPanel.addKeyListener(this);
			// add mainPanel to window surface
			c = getContentPane();
			c.add(mainPanel);
		}else if(State ==STATE.MENU){

		}else if(State ==STATE.GAME){

			statusL = new JLabel("Enter your name:");
			statusTF = new JTextField (25);
			statusTF.setEditable(true);

			// create buttons
			startB = new JButton ("Start Game");
			exitB = new JButton ("Quit ");

			// add listener to each button (same as the current object)
			startB.addActionListener(this);
			exitB.addActionListener(this);
			statusTF.addKeyListener(this);

			//Add new player
			player = new Player(11);

			// create mainPanel

			mainPanel = new JPanel();
			borderLayout = new BorderLayout();
			mainPanel.setLayout(borderLayout);
			GridLayout gridLayout;


			Title title = new Title();
			title.setPreferredSize(new Dimension(getWidth(),70));
			title.setBackground(new Color(232, 142, 29));

			// create the gamePanel for game entities
			startPanel = new StartPanel();
			startPanel.setPreferredSize(new Dimension(850, 800));
			// create infoPanel
			JPanel infoPanel = new JPanel();
			gridLayout = new GridLayout(2, 2);
			infoPanel.setLayout(gridLayout);
			infoPanel.setBackground(Color.WHITE);

			// add user interface objects to infoPanel
			infoPanel.add(statusL);
			infoPanel.add(statusTF);
			infoPanel.add(startB);
			infoPanel.add(exitB);


			// add sub-panels with GUI objects to mainPanel and set its colour

			mainPanel.add(title,BorderLayout.NORTH);
			mainPanel.add(startPanel,BorderLayout.CENTER);
			mainPanel.add(infoPanel,BorderLayout.SOUTH);
			mainPanel.setBackground(Color.WHITE);

			// set up mainPanel to respond to mouse
			startPanel.addKeyListener(this);

			// add mainPanel to window surface

			c = getContentPane();
			c.add(mainPanel);
		}
	}
	// implement single method in ActionListener interface
	public boolean changed(){
		if (!statusTF.getText().equals("")){
			changed = true;
		}
		return changed;
	}

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		if(State == State.GAME){
			if (command.equals(startB.getText())){
				statusTF.getDocument().addDocumentListener(new DocumentListener(){
					@Override
					public void insertUpdate(DocumentEvent e) {
						changed();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						changed();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						changed();
					}
				});
				if(changed()) {
					player.setName(statusTF.getText());
					startPanel.setVisible(false);
					//Create new JPanel
					statusL.setText("Enter which column you wish to play your disk ");
					JPanel infoPanel = new JPanel();
					GridLayout gridLayout = new GridLayout(2, 2);
					infoPanel.setLayout(gridLayout);
					infoPanel.setBackground(Color.WHITE);

					// add user interface objects to infoPanel
					infoPanel.add(statusL);
					infoPanel.add(statusTF);
					infoPanel.add(startB);
					infoPanel.add(exitB);

					JLabel text = new JLabel();
					Font f1 = new Font("Serif",Font.PLAIN,26);
					text.setFont(f1);
					text.setPreferredSize(new Dimension(getWidth()/4,30));
					text.setText(player.name+"'s Turn");

					JLabel rule = new JLabel();
					Font f2 = new Font("Serif",Font.PLAIN,18);
					rule.setFont(f2);
					rule.setPreferredSize(new Dimension(getWidth()/2+200,90));
					rule.setText(player.questions.get(0)+"\nEnter the column number you wish play.");

					this.gameBoard = new GameBoard(player,text,rule);
					gameBoard.setLayout(new FlowLayout());

					gameBoard.add(text);
					gameBoard.add(rule);


					Canvas board = new Canvas(){
						@Override
						public void paint(Graphics g) {
							super.paint(g);
							System.out.println("Updating canvas");

							// Display initial Board
							Graphics2D g2 = (Graphics2D) g;
							int count = 0;
								for (int col = 0; col < gameBoard.COLS; col++) {
									int x = gameBoard.SPACING + gameBoard.SPACING * col + Disk.WIDTH * col+5;
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
					gameBoard.setCanvas(board);
					board.setPreferredSize(new Dimension(1000,800));
					gameBoard.add(board);
					gameBoard.setBackground(Color.LIGHT_GRAY);


					//Display GameBoard and Player turn
					mainPanel.remove(borderLayout.getLayoutComponent(borderLayout.SOUTH));
					mainPanel.add(infoPanel,BorderLayout.SOUTH);
					mainPanel.add(gameBoard,BorderLayout.CENTER);

					c= getContentPane();
					c.add(mainPanel);
					statusTF.setText("");
				}
				else{
					startPanel.display = "Enter your name below, then click start. ";
					startPanel.update(startPanel.getGraphics());
				}
			}
			if (command.equals(exitB.getText()))
				System.exit(0);

			mainPanel.requestFocus();
		}
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		int a;
		if (keyCode == VK_ENTER) {
			if(gameBoard != null){
				String col = statusTF.getText();
				try{
					a= Integer.parseInt(col);

				}catch (NumberFormatException nfe){
					return;
				}
				gameBoard.updateBoard(a);
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}