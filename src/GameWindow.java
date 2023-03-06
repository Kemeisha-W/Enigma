package src;

import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for Layout Managers
import java.awt.event.*;		// need this to respond to GUI events
import java.awt.geom.Rectangle2D;

public class GameWindow extends JFrame implements ActionListener,KeyListener,MouseListener
{
	private Player player;
	// declare labels
	private JLabel statusL;

	// declare text fields
	private JTextField statusTF;

	private static Boolean changed= false;

	// declare buttons
	private ButtonCustom startB;
	private ButtonCustom infoB;
	private ButtonCustom englishB;
	private ButtonCustom hardB;
	private ButtonCustom wordB;
	private ButtonCustom mathB;
	private ButtonCustom funnyB;
	private SoundManager soundManager;
	private Container c;

	// Panels
	private JPanel mainPanel;
	private StartPanel startPanel;
	private MenuPanel menuPanel;
	private GameBoard gameBoard;
	private BorderLayout borderLayout;

	protected enum STATE{
		START,
		MENU,
		GAME,
		INFO,
	};

	protected enum CATEGORY{
		ENGLISH,
		FUNNY,
		WORD,
		MATH,
		HARD
	}
	protected CATEGORY category;
	protected STATE state;
	private Toolkit tk;

	@SuppressWarnings({"unchecked"})
	public GameWindow() {
		state = STATE.START;
		setTitle ("COMP 3609: Assignment 2 Enigma Game");
		tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setSize (screenSize.width/2+300, screenSize.height);
		setLocationRelativeTo(null);

		// create JPanels
		mainPanel = new JPanel();
		mainPanel.setSize(screenSize.width, screenSize.height);
		setFrame(STATE.START);
		// set properties of window
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		soundManager = SoundManager.getInstance();
		soundManager.playClip("background",true);
	}

	protected void setFrame(STATE state){
		this.state = state;
//		GridBagLayout gb = new GridBagLayout();
//		GridBagConstraints gcon = new GridBagConstraints();
//		gcon.fill = GridBagConstraints.HORIZONTAL;
//		gcon.insets = new Insets(5,3,5,3);

		switch(state){
			case START:
				startPanel = new StartPanel(tk.getScreenSize().width);
				startPanel.setBackground(new Color(244, 190, 130));
				startPanel.startB.addActionListener(this);
				startPanel.infoB.addActionListener(this);
				this.startB = startPanel.startB;
				this.infoB = startPanel.infoB;

				c = getContentPane();
				c.add(startPanel);

				break;
			case MENU:
				menuPanel = new MenuPanel();
				menuPanel.englishB.addActionListener(this);
				menuPanel.funnyB.addActionListener(this);
				menuPanel.mathB.addActionListener(this);
				menuPanel.wordB.addActionListener(this);
				menuPanel.hardB.addActionListener(this);
				this.englishB = menuPanel.englishB;
				this.funnyB = menuPanel.funnyB;
				this.mathB = menuPanel.mathB;
				this.wordB = menuPanel.wordB;
				this.hardB = menuPanel.hardB;
				this.add(menuPanel,BorderLayout.CENTER);
				c = getContentPane();
				c.add(menuPanel);
				break;
			case GAME:

				//Add new player
				player = new Player(11);

				// create mainPanel

				borderLayout = new BorderLayout();
				mainPanel.setLayout(borderLayout);
				GridLayout gridLayout;


				statusL.setText("Enter which column you wish to play your disk ");
				JPanel infoPanel = new JPanel();
				gridLayout = new GridLayout(2, 2);
				infoPanel.setLayout(gridLayout);
				infoPanel.setBackground(Color.WHITE);

				// add user interface objects to infoPanel
				infoPanel.add(statusL);
				infoPanel.add(statusTF);
				infoPanel.add(startB);

				JLabel text = new JLabel();
				Font f1 = new Font("Serif", Font.PLAIN, 26);
				text.setFont(f1);
				text.setPreferredSize(new Dimension(getWidth() / 4, 30));

				JLabel rule = new JLabel();
				Font f2 = new Font("Serif", Font.PLAIN, 18);
				rule.setFont(f2);
				rule.setPreferredSize(new Dimension(getWidth() / 2 + 200, 90));
				rule.setText(player.questions.get(0) + "\nEnter the column number you wish play.");

				this.gameBoard = new GameBoard(player, text, rule);
				gameBoard.setLayout(new FlowLayout());

				gameBoard.add(text);
				gameBoard.add(rule);


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
				gameBoard.setCanvas(board);
				board.setPreferredSize(new Dimension(1000, 800));
				gameBoard.add(board);
				gameBoard.setBackground(Color.LIGHT_GRAY);


				//Display GameBoard and Player turn
				mainPanel.remove(borderLayout.getLayoutComponent(borderLayout.SOUTH));
				mainPanel.add(infoPanel, BorderLayout.SOUTH);
				mainPanel.add(gameBoard, BorderLayout.CENTER);

				// add mainPanel to window surface

				c = getContentPane();
				c.add(mainPanel);
				break;
		}
	}

	// implement single method in ActionListener interface
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		switch(state){
			case START:
				if (command.equals(startB.getText())) {
					state = STATE.MENU;
					startPanel.setVisible(false);
					setFrame(state);
				}if (command.equals(infoB.getText())) {
				state = STATE.INFO;
				startPanel.setVisible(false);
				setFrame(state);
				}
				break;

			case MENU:
				switch(command){
					case "English":
						category = CATEGORY.ENGLISH;
						break;
					case "Funny":
						category = CATEGORY.FUNNY;
						break;
					case "Words":
						category = CATEGORY.WORD;
						break;
					case "Math":
						category = CATEGORY.MATH;
						break;
					case "Hard":
						category = CATEGORY.HARD;
						break;
				}
				state = STATE.GAME;
				menuPanel.setVisible(false);
				setFrame(state);
				break;
			case INFO:
			case GAME:
		}
		mainPanel.requestFocus();
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		int a;
//		if (keyCode == VK_ENTER) {
//			if(gameBoard != null){
//				String col = statusTF.getText();
//				try{
//					a= Integer.parseInt(col);
//
//				}catch (NumberFormatException nfe){
//					return;
//				}
//				gameBoard.updateBoard(a);
//			}
//		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}