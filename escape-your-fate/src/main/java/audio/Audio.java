package audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import application.App;

public class Audio {

	private static final int BUFFER_SIZE = 4096;
	private static boolean isMusicPlaying = false;

	public void play(String audioFilePath) {

		try {
			InputStream audioSource = App.class.getResourceAsStream(audioFilePath);

			InputStream bufferedIn = new BufferedInputStream(audioSource);

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

			SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);

			audioLine.open(format);

			audioLine.start();

			byte[] bytesBuffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
				audioLine.write(bytesBuffer, 0, bytesRead);
			}

			audioLine.drain();
			audioLine.close();
			audioStream.close();

		} catch (UnsupportedAudioFileException ex) {
			System.out.println("The specified audio file is not supported.");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.out.println("Audio line for playing back is unavailable.");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error playing the audio file.");
			ex.printStackTrace();
		}
	}

	public void startMusic() {
		if (!isMusicPlaying) {
			Thread musicThread = new Thread(() -> {
				while (!Thread.currentThread().isInterrupted()) {
					play(inTheDark());
				}
			});
			musicThread.start();
			isMusicPlaying = true;
		}
	}

	public String inTheDark() {
		return "/aud/inthedark.au";
	}

	public String dread() {
		return "/aud/dread.au";
	}

	public String drip() {
		return "/aud/drip.au";
	}

	public String shower() {
		return "/aud/shower.au";
	}

	public String match() {
		return "/aud/match.au";
	}

	public String door() {
		return "/aud/door.au";
	}

	public String doorShort() {
		return "/aud/doorshort.au";
	}

	public String clockTicking() {
		return "/aud/clockticking.au";
	}

	public String clock() {
		return "/aud/clock.au";
	}

	public String yell() {
		return "/aud/yell.au";
	}

	public String fall() {
		return "/aud/fall.au";
	}

	public String laugh() {
		return "/aud/laugh.au";
	}

	public String camera() {
		return "/aud/camera.au";
	}

	public String afraid() {
		return "/aud/afraid.au";
	}

	public String busters() {
		return "/aud/busters.au";
	}
}
