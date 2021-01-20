package night2;

import java.util.Scanner;

import application.Areas;
import application.Events;
import application.Restart;
import audio.Audio;
import audio.AudioLoops;
import dream.Dream;
import text.Headers;
import text.Night2Txt;
import view.Menu;

public class Night2 {

	public Night2(Menu menu) {
		this.menu = menu;
	}

	Menu menu = new Menu(System.in, System.out);
	Scanner userInput = new Scanner(System.in);
	Audio aud = new Audio();
	AudioLoops al = new AudioLoops();

	Night2Txt txt = new Night2Txt();
	Headers h = new Headers();
	Dream dream = new Dream(menu);
	Restart r = new Restart();

	public void runNight2(Events e, Areas a) {

		menu.clearConsole();

		System.out.println(h.night2Header());
		System.out.println(txt.night2Start());
		new Thread(() -> aud.play(aud.clock())).start();

		userInput.nextLine();
		menu.clearConsole();

		while (true) {

			if (e.getChase() == 1) {
				al.setDread(false);
				System.out.println(txt.bedroomHide());
				aud.play(aud.doorShort());
				menu.clearConsole();
				System.out.println(h.face());
				aud.play(aud.yell());
				System.out.println(txt.died1());
				r.restartGame(e, a);
			}

			System.out.println(txt.inBedroom());

			String bedroomChoice = (String) menu.getChoiceFromOptions(Night2Constants.NIGHT2_BEDROOM_MENU_OPTIONS);

			if (bedroomChoice.equals(Night2Constants.NIGHT2_BEDROOM_MENU_KEEP_CODING)) {
				menu.clearConsole();

				System.out.println(txt.keepCoding());
				e.setCodeNight2(e.getCodeNight2() + 1);

				if (e.getCodeNight2() == 5) {
					System.out.println(txt.keepCodingNight2());
					e.setSecCodeNight2(true);
					userInput.nextLine();
					dream.runDream(e, a);
					break;
				}
			} else if (bedroomChoice.equals(Night2Constants.NIGHT2_BEDROOM_MENU_EXPLORE)) {
				menu.clearConsole();

				while (!a.isHallway()) {

					if (e.getChase() == 1) {
						System.out.println(txt.chaseHallway());
					}

					System.out.println(txt.enterHallway());

					String hallwayChoice = (String) menu
							.getChoiceFromOptions(Night2Constants.NIGHT2_HALLWAY_MENU_OPTIONS);

					if (hallwayChoice.equals(Night2Constants.NIGHT2_HALLWAY_MENU_BATHROOM)) {
						menu.clearConsole();
						
						if (e.getChase() == 1) {
							al.setDread(false);
							System.out.println(txt.bathroomHide());
							aud.play(aud.doorShort());
							menu.clearConsole();
							System.out.println(h.face());
							aud.play(aud.yell());
							System.out.println(txt.died1());
							r.restartGame(e, a);
						}
						
						al.setDrip(true);
						Thread dripThread = new Thread(() -> {
							while (al.isDrip()) {	
							aud.play(aud.drip());
							}
						});
						dripThread.start();

						System.out.println(txt.enterBathroom());

						while (!a.isBathroom()) {

							String bathroomChoice = (String) menu
									.getChoiceFromOptions(Night2Constants.NIGHT2_BATHROOM_MENU_OPTIONS);

							if (bathroomChoice.equals(Night2Constants.NIGHT2_BATHROOM_MENU_SHOWER)) {
								menu.clearConsole();

								if (e.getMirror() <= 1) {
									System.out.println(txt.noShower());
								}

								if (e.getMirror() == 2 && !e.isSecondNumber()) {
									System.out.println(txt.secondNumber());
									e.setSecondNumber(true);

								} else if (e.getMirror() == 2 && e.isSecondNumber()) {
									System.out.println(txt.noShower());
								}

							} else if (bathroomChoice.equals(Night2Constants.NIGHT2_BATHROOM_MENU_SINK)) {
								menu.clearConsole();

								if (e.getMirror() == 2) {
									System.out.println(txt.nothingUseful());

								} else if (e.getMirror() == 1) {
									System.out.println(txt.mirrorNumber());
									e.setMirror(2);

								} else if (e.getMirror() == 0) {
									e.setMirror(1);
									menu.clearConsole();
									System.out.println(h.face());
									aud.play(aud.dread());
									menu.clearConsole();
									System.out.println(txt.mirrorFace());
								}

							} else if (bathroomChoice.equals(Night2Constants.NIGHT2_BATHROOM_MENU_HALLWAY)) {
								menu.clearConsole();
								al.setDrip(false);
								break;
							}
						}
					} else if (hallwayChoice.equals(Night2Constants.NIGHT2_HALLWAY_MENU_GUEST_ROOM)) {
						menu.clearConsole();

						if (!e.isGuestRoomFirst()) {
							e.setGuestRoomFirst(true);
							System.out.println(txt.guestRoomFirst());
						}
						if (e.getChase() == 1) {
							System.out.println(txt.guestChase());
						}
						if (!e.isNight2FoundDog() && e.getChase() == 0) {
							System.out.println(txt.enterGuestRoom());
						}

						while (!a.isGuestRoom()) {

							String guestRoomChoice = (String) menu
									.getChoiceFromOptions(Night2Constants.NIGHT2_GUEST_ROOM_MENU_OPTIONS);

							if (guestRoomChoice.equals(Night2Constants.NIGHT2_GUEST_ROOM_MENU_WINDOW)) {
								menu.clearConsole();

								if (e.getChase() == 0) {
									System.out.println(txt.window());

									if (!e.isNight2FoundDog()) {
										System.out.println(txt.lola());
										e.setDogCount(e.getDogCount() + 1);
										e.setNight2FoundDog(true);
									}

								} else if (e.getChase() == 1) {
									al.setDread(false);
									menu.clearConsole();
									System.out.println(txt.windowFall());
									aud.play(aud.doorShort());
									menu.clearConsole();
									System.out.println(h.face());
									aud.play(aud.fall());
									System.out.println(txt.died2());
									r.restartGame(e, a);
								}
							} else if (guestRoomChoice.equals(Night2Constants.NIGHT2_GUEST_ROOM_MENU_CLOSET)) {
								menu.clearConsole();

								if (e.getChase() == 0) {
									System.out.println(txt.nothingCloset());

								} else if (e.getChase() == 1) {
									al.setDread(false);
									menu.clearConsole();
									System.out.println(txt.night2End());
									userInput.nextLine();
									e.setCamera(true);
									e.setChase(0);
									dream.runDream(e, a);
									break;
								}
							} else if (guestRoomChoice.equals(Night2Constants.NIGHT2_GUEST_ROOM_MENU_HALLWAY)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									al.setDread(false);
									menu.clearConsole();
									System.out.println(h.face());
									aud.play(aud.yell());
									System.out.println(txt.died1());
									r.restartGame(e, a);
								}
								break;
							}
						}
					} else if (hallwayChoice.equals(Night2Constants.NIGHT2_HALLWAY_MENU_BEDROOM)) {
						menu.clearConsole();
						break;

					} else if (hallwayChoice.equals(Night2Constants.NIGHT2_HALLWAY_MENU_FOYER)) {
						menu.clearConsole();

						if (e.getChase() == 1) {
							al.setDread(false);
							System.out.println(h.face());
							aud.play(aud.yell());
							System.out.println(txt.died1());
							r.restartGame(e, a);
						}

						System.out.println(txt.foyer());

						while (!a.isFoyer()) {

							String foyerChoice = (String) menu
									.getChoiceFromOptions(Night2Constants.NIGHT2_FOYER_MENU_OPTIONS);

							if (foyerChoice.equals(Night2Constants.NIGHT2_FOYER_MENU_DINING)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									al.setDread(false);
									System.out.println(h.face());
									aud.play(aud.yell());
									System.out.println(txt.died1());
									r.restartGame(e, a);
								}

								System.out.println(txt.clock());
								System.out.println(txt.dining());
								al.setClockTicking(true);
								al.getClockTickingThread().start();

								while (!a.isDining()) {

									String diningChoice = (String) menu
											.getChoiceFromOptions(Night2Constants.NIGHT2_DINING_MENU_OPTIONS);

									if (diningChoice.equals(Night2Constants.NIGHT2_DINING_MENU_GRANDFATHER_CLOCK)) {
										menu.clearConsole();

										if (e.getChase() == 0) {
											System.out.println(txt.fire());
											aud.play(aud.match());
											menu.clearConsole();
											System.out.println(h.face());
											aud.play(aud.laugh());
											al.setDread(true);
											al.getDreadThread().start();
											e.setChase(1);
											menu.clearConsole();
											System.out.println(txt.encounter());

										} else if (e.getChase() == 1) {
											al.setDread(false);
											System.out.println(h.face());
											aud.play(aud.yell());
											System.out.println(txt.died1());
											al.setClockTicking(false);
											r.restartGame(e, a);
										}

									} else if (diningChoice.equals(Night2Constants.NIGHT2_DINING_MENU_FIREPLACE)) {
										menu.clearConsole();

										if (e.getChase() == 0) {
											System.out.println(txt.fire());
											aud.play(aud.match());
											menu.clearConsole();
											System.out.println(h.face());
											aud.play(aud.laugh());
											al.setDread(true);
											al.getDreadThread().start();
											e.setChase(1);
											menu.clearConsole();
											System.out.println(txt.encounter());

										} else if (e.getChase() == 1) {
											al.setDread(false);
											System.out.println(h.face());
											aud.play(aud.yell());
											System.out.println(txt.died1());
											al.setClockTicking(false);
											r.restartGame(e, a);
										}
									} else {
										menu.clearConsole();

										if (e.getChase() == 0) {
											System.out.println(txt.investigate());

										} else if (e.getChase() == 1) {
											System.out.println(txt.foyerChase());
											System.out.println(txt.foyer());
											al.setClockTicking(false);
											break;
										}
									}
								}
							} else if (foyerChoice.equals(Night2Constants.NIGHT2_FOYER_MENU_BASEMENT)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									al.setDread(false);
									System.out.println(txt.basementHide());
									aud.play(aud.doorShort());
									menu.clearConsole();
									System.out.println(h.face());
									aud.play(aud.yell());
									System.out.println(txt.died1());
									r.restartGame(e, a);

								} else {
									System.out.println(txt.basement());
								}
							} else if (foyerChoice.equals(Night2Constants.NIGHT2_FOYER_MENU_HALLWAY)) {
								menu.clearConsole();
								break;
							}
						}
					}
				}
			}
		}
	}
}