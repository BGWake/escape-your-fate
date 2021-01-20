package application;

import java.util.Scanner;

public class Restart {
	
	Scanner userInput = new Scanner(System.in);
	
	public void restartNewGame() {
		System.out.println(restartAfterEnding());
		String restart = userInput.nextLine();
		if (restart.contains("Y") || restart.contains("y")) {
			App.main(null);
		} else {
			System.exit(0);
		}
	}

	public void restartGame(Events e, Areas a) {
		e.setChase(0);
		e.setFlashlight(0);
		e.setCodeNight1(0);
		e.setCodeNight2(0);
		e.setCodeNight3(0);
		e.setShower(false);
		e.setCamera(false);
		e.setBedroomFound(false);
		e.setBathroomFound(false);
		e.setGuestRoomFound(false);
		e.setDiningRoomFound(false);
		e.setBasementFound(false);
		e.setSecCodeNight1(false);
		e.setSecCodeNight2(false);
		System.out.println(restartTxt());
		String restart = userInput.nextLine();
		if (restart.contains("Y") || restart.contains("y")) {
			App.startGame(e, a);
		} else {
			System.exit(0);
		}
	}
	
	public String restartTxt() {
		return "\nRestarting will rollover your current stats to the new game." 
			 + "\nHint: Keep coding to skip to the next night."
		     + "\nRestart game? (Enter Y/N)";
	}
	
	public String restartAfterEnding() {
		return "\nStart a new game? (Enter Y/N)";
	}
}