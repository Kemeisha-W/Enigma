package src;

import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for Layout Managers
import java.awt.event.*;		// need this to respond to GUI events
import java.awt.geom.Rectangle2D;

public class GameWindow extends JFrame implements ActionListener,KeyListener,MouseListener
{
	//Background colour
	public static final Color background = new Color(244, 190, 130);


	public static SoundManager soundManager;
	private Container c;

	// Panels
	private StartPanel startPanel;
	private InfoPanel infoPanel;
	private MenuPanel menuPanel;
	private GamePanel gamePanel;

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
		setFrame(STATE.START);
		// set properties of window
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		soundManager = SoundManager.getInstance();
		//TODO: Add back sound
		soundManager.playClip("background",true);
	}

	protected void setFrame(STATE state){
		this.state = state;

		switch(state){
			case START:
				startPanel = new StartPanel(tk.getScreenSize().width);
				startPanel.setBackground(background);
				startPanel.startB.addActionListener(this);
				startPanel.infoB.addActionListener(this);


				c = getContentPane();
				c.add(startPanel);
				startPanel.requestFocusInWindow();
				break;
			case MENU:
				menuPanel = new MenuPanel();
				menuPanel.englishB.addActionListener(this);
				menuPanel.funnyB.addActionListener(this);
				menuPanel.mathB.addActionListener(this);
				menuPanel.wordB.addActionListener(this);
				menuPanel.hardB.addActionListener(this);
				this.add(menuPanel);
				c = getContentPane();
				c.add(menuPanel);
				menuPanel.requestFocusInWindow();
				break;
			case GAME:
				gamePanel = new GamePanel(this.category);
				gamePanel.hintB.addActionListener(this);
				gamePanel.restartB.addActionListener(this);
				gamePanel.exitB.addActionListener(this);
				this.add(gamePanel);
				c = getContentPane();
				c.add(gamePanel);
				gamePanel.requestFocusInWindow();
				gamePanel.startGame();
				break;
			case INFO:
				infoPanel = new InfoPanel();
				infoPanel.returnB.addActionListener(this);
				this.add(infoPanel);
				c = getContentPane();
				c.add(infoPanel);
				infoPanel.requestFocusInWindow();
				break;
		}
	}

	// implement single method in ActionListener interface
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		switch(state){
			case START:
				if (command.equals(startPanel.startB.getText())) {
					state = STATE.MENU;
					startPanel.setVisible(false);
					setFrame(state);
				}if (command.equals(startPanel.infoB.getText())) {
					state = STATE.INFO;
					startPanel.setVisible(false);
					setFrame(state);
				}
				break;

			case MENU:
				switch(command){
					case "English":
						this.category = CATEGORY.ENGLISH;
						break;
					case "Funny":
						this.category = CATEGORY.FUNNY;
						break;
					case "Words":
						this.category = CATEGORY.WORD;
						break;
					case "Math":
						this.category = CATEGORY.MATH;
						break;
					case "Hard":
						this.category = CATEGORY.HARD;
						break;
				}
				state = STATE.GAME;
				menuPanel.setVisible(false);
				setFrame(state);
				break;
			case INFO:
				if(command.equals(infoPanel.returnB.getText())){
					state = STATE.START;
					infoPanel.setVisible(false);
					startPanel.setVisible(true);
				}
			case GAME:
				switch (command){
					case "Restart Game":
						startPanel.setVisible(true);
						state = STATE.START;
						gamePanel.setVisible(false);
						gamePanel.restartGame();
						break;
					case "Hint":
						break;
					case "Exit":
						gamePanel.endGame();
//						setVisible(false);
//						dispose();
						break;
				}

		}
	}

	public void keyPressed(KeyEvent e) {

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