package src;

import javax.sound.sampled.AudioInputStream;		// for playing sound clips
import javax.sound.sampled.*;
import java.io.*;
import java.util.HashMap;				// for storing sound clips

public class SoundManager {				// a Singleton class
	HashMap<String, Clip> clips;

	private static SoundManager instance = null;	// keeps track of Singleton instance
	private long clipTime;
	private float volume;

	private SoundManager () {
		clips = new HashMap<String, Clip>();

		Clip clip = loadClip("Assets/sound/Background.wav");	// played from start of the game
		clips.put("background", clip);

		clip = loadClip("Assets/sound/dramatic-synth.wav");	// played when the game ends
		clips.put("gameOver", clip);

		clip = loadClip("Assets/sound/Game level-completed.wav");	// after getting 5 riddles correct
		clips.put("levelUp", clip);

		clip = loadClip("Assets/sound/wrong-answer-fail.wav");	// after choosing the wrong answer
		clips.put("wrong", clip);

		clip = loadClip("Assets/sound/correct.wav");	// after choosing the correct answer
		clips.put("correct", clip);

		clip = loadClip("Assets/sound/gameComplete.wav");	// after user beats game
		clips.put("gameFinished", clip);

		volume = 0.06f;
	}


	public static SoundManager getInstance() {	// class method to retrieve instance of Singleton
		if (instance == null)
			instance = new SoundManager();
		
		return instance;
	}

	public void resumeClip(String fileName, boolean looping){
		Clip clip = getClip(fileName);
		clipTime = clip.getMicrosecondPosition();
		if(looping){
			clip.setMicrosecondPosition(clipTime);
			clip.start();
		}else{
			clip.setMicrosecondPosition(clipTime);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

	}

 	public Clip loadClip (String fileName) {	// gets clip from the specified file
 		AudioInputStream audioIn;
		Clip clip = null;
		try {
			File file = new File(fileName);
			audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		}
		catch (Exception e) {
 			System.out.println ("Error opening sound files: " + e);
		}
    		return clip;
	}

	public Clip getClip (String title) {

		return clips.get(title);
	}

	public void playClip(String title,boolean looping) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.setFramePosition(0);
			if (looping)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.start();
		}
	}

	public void stopClip(String title) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.stop();
		}
	}
}