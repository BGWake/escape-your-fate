package application;

import java.util.Scanner;

import audio.Audio;
import night1.Night1;
import text.Headers;
import view.Menu;

public class App {

	public static void main(String[] args) {

		Events e = new Events();
		Areas a = new Areas();
		startGame(e, a);
	}

	public static void startGame(Events e, Areas a) {
		
		Audio aud = new Audio();
		Menu menu = new Menu(System.in, System.out);
		Scanner enter = new Scanner(System.in);
		Headers h = new Headers();
		Night1 n1 = new Night1(menu);

		menu.clearConsole();
		System.out.println(h.mainHeader());
		h.stats(e);
		aud.startMusic();
		System.out.println(h.start());
		enter.nextLine();
		n1.runNight1(e, a);
		enter.close();
	}
}
