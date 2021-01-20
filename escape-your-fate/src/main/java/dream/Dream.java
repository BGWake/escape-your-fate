package dream;

import java.util.Scanner;

import application.Areas;
import application.Events;
import audio.Audio;
import night3.Night3;
import text.DreamTxt;
import text.Headers;
import view.Menu;

public class Dream {

	public Dream(Menu menu) {
		this.menu = menu;
	}

	Menu menu = new Menu(System.in, System.out);
	Scanner userInput = new Scanner(System.in);
	Audio aud = new Audio();

	DreamTxt txt = new DreamTxt();
	Headers h = new Headers();
	Night3 n3 = new Night3(menu);

	public void runDream(Events e, Areas a) {

		menu.clearConsole();
		System.out.println(h.face());
		new Thread(() -> aud.play(aud.laugh())).start();
		System.out.println(txt.dreamStart());
		String userAnswer = userInput.nextLine();

		if (userAnswer.equalsIgnoreCase("Ghost") || userAnswer.equalsIgnoreCase("Spirit")
				|| userAnswer.equalsIgnoreCase("Demon") || userAnswer.equalsIgnoreCase("Poltergeist")
				|| userAnswer.equalsIgnoreCase("Phantom") || userAnswer.equalsIgnoreCase("Entity") 
				|| userAnswer.equalsIgnoreCase("Evil") || userAnswer.equalsIgnoreCase("Malevolent")) {
			menu.clearConsole();
			System.out.println(txt.closeGuess());
			e.setGoodGuess(true);
			
		} else if (userAnswer.equalsIgnoreCase("Grandpa") || userAnswer.equalsIgnoreCase("Grandfather")) {
			menu.clearConsole();
			System.out.println(txt.goodGuess());
			e.setThirdNumber(true);
			e.setGoodGuess(true);

		} else {
			menu.clearConsole();
			System.out.println(txt.badGuess());
		}
		System.out.println(txt.enter());
		userInput.nextLine();
		n3.runNight3(e, a);
	}
}