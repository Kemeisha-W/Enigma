package src;

import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for Layout Managers
import java.awt.event.*;		// need this to respond to GUI events
import java.awt.geom.Rectangle2D;

public class GameWindow extends JFrame implements ActionListener,KeyListener,MouseListener
{

	// declare buttons
	private ButtonCustom startB;
	private ButtonCustom infoB;
	private ButtonCustom returnB;

	private SoundManager soundManager;
	private Container c;

	// Panels
	private JPanel mainPanel;
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
				this.add(menuPanel);
				c = getContentPane();
				c.add(menuPanel);
				break;
			case GAME:
				gamePanel = new GamePanel();
				gamePanel.hintB.addActionListener(this);
				gamePanel.restartB.addActionListener(this);
				gamePanel.submitB.addActionListener(this);
				this.add(gamePanel);
				c = getContentPane();
				c.add(gamePanel);
				break;
			case INFO:
				infoPanel = new InfoPanel();
				infoPanel.returnB.addActionListener(this);
				this.returnB = infoPanel.returnB;
				this.add(infoPanel);
				c = getContentPane();
				c.add(infoPanel);
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
				if(command.equals(returnB.getText())){
					state = STATE.START;
					infoPanel.setVisible(false);
					startPanel.setVisible(true);
				}
			case GAME:
		}
		mainPanel.requestFocus();
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