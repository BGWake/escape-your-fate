package night1;

import java.util.Scanner;

import application.Areas;
import application.Events;
import audio.Audio;
import audio.AudioLoops;
import night2.Night2;
import text.Headers;
import text.Night1Txt;
import view.Menu;

public class Night1 {

	public Night1(Menu menu) {
		this.menu = menu;
	}

	Menu menu = new Menu(System.in, System.out);
	Scanner enter = new Scanner(System.in);
	Audio aud = new Audio();
	AudioLoops al = new AudioLoops();

	Night1Txt txt = new Night1Txt();
	Headers h = new Headers();
	Night2 n2 = new Night2(menu);

	public void runNight1(Events e, Areas a) {

		menu.clearConsole();
		System.out.println(h.night1Header());
		System.out.println(txt.night1Start());
		enter.nextLine();
		menu.clearConsole();

		while (true) {

			if (e.getFlashlight() == 1) {
				e.setFlashlight(2);
				System.out.println(txt.gotFlashlight());
			}

			if (!e.isBedroomFirst()) {
				e.setBedroomFirst(true);
				System.out.println(txt.bedroomFirst());
			}

			System.out.println(txt.inBedroom());

			String bedroomChoice = (String) menu.getChoiceFromOptions(Night1Constants.NIGHT1_ROOM_MENU_OPTIONS);

			if (bedroomChoice.equals(Night1Constants.NIGHT1_ROOM_MENU_KEEP_CODING)) {
				menu.clearConsole();

				if (e.getFlashlight() == 2) {
					System.out.println(txt.noPowerCode());
				}

				else if (e.getFlashlight() == 0) {
					System.out.println(txt.keepCoding());
					e.setCodeNight1(e.getCodeNight1() + 1);

					if (e.getCodeNight1() == 5) {
						System.out.println(txt.keepCodingNight1());
						enter.nextLine();
						menu.clearConsole();
						System.out.println(h.face());
						new Thread(() -> aud.play(aud.dread())).start();
						System.out.println(txt.night1End());
						enter.nextLine();

						e.setSecCodeNight1(true);
						n2.runNight2(e, a);
						break;
					}
				}

			} else if (bedroomChoice.equals(Night1Constants.NIGHT1_ROOM_MENU_EXPLORE)) {
				menu.clearConsole();

				while (!a.isHallway()) {

					System.out.println(txt.enterHallway());

					String hallwayChoice = (String) menu
							.getChoiceFromOptions(Night1Constants.NIGHT1_HALLWAY_MENU_OPTIONS);

					if (hallwayChoice.equals(Night1Constants.NIGHT1_HALLWAY_MENU_BATHROOM)) {

						al.setDrip(true);
						Thread dripThread = new Thread(() -> {
							while (al.isDrip()) {
								aud.play(aud.drip());
							}
						});
						dripThread.start();
						menu.clearConsole();

						if (!e.isBathroomFirst()) {
							e.setBathroomFirst(true);
							System.out.println(txt.bathroomFirst());
						}

						System.out.println(txt.enterBathroom());

						while (!a.isBathroom()) {

							String bathroomChoice = (String) menu
									.getChoiceFromOptions(Night1Constants.NIGHT1_BATHROOM_MENU_OPTIONS);

							if (bathroomChoice.equals(Night1Constants.NIGHT1_BATHROOM_MENU_SHOWER)) {
								menu.clearConsole();

								if (!e.isShower() && e.getFlashlight() == 0) {
									System.out.println(txt.noShower());

								} else if (e.isShower() && e.getFlashlight() == 0) {
									System.out.println(txt.powerOut());
									new Thread(() -> aud.play(aud.shower())).start();
									e.setFlashlight(1);

								} else if (e.isShower() && e.getFlashlight() >= 1) {
									System.out.println(txt.noPowerShower());
								}
							} else if (bathroomChoice.equals(Night1Constants.NIGHT1_BATHROOM_MENU_SINK)) {
								menu.clearConsole();

								if (e.getFlashlight() == 1) {
									System.out.println(txt.needFlashlight());

								} else {
									if (e.isFirstNumber()) {
										System.out.println(txt.nothingUseful());

									} else if (!e.isFirstNumber()) {
										e.setFirstNumber(true);
										System.out.println(txt.underSink());
									}
								}
							} else if (bathroomChoice.equals(Night1Constants.NIGHT1_BATHROOM_MENU_HALLWAY)) {
								menu.clearConsole();
								al.setDrip(false);
								break;
							}
						}
					} else if (hallwayChoice.equals(Night1Constants.NIGHT1_HALLWAY_MENU_GUEST_ROOM)) {
						menu.clearConsole();

						if (!e.isGuestRoomFirst()) {
							e.setGuestRoomFirst(true);
							System.out.println(txt.guestRoomFirst());
						}

						System.out.println(txt.enterGuestRoom());

						while (!a.isGuestRoom()) {

							if (e.getFlashlight() == 1) {
								System.out.println(txt.needFlashlight());
								break;
							}

							String guestRoomChoice = (String) menu
									.getChoiceFromOptions(Night1Constants.NIGHT1_GUEST_ROOM_MENU_OPTIONS);

							if (guestRoomChoice.equals(Night1Constants.NIGHT1_GUEST_ROOM_MENU_WINDOW)) {
								menu.clearConsole();
								System.out.println(txt.window());

							} else if (guestRoomChoice.equals(Night1Constants.NIGHT1_GUEST_ROOM_MENU_CLOSET)) {
								menu.clearConsole();
								System.out.println(txt.nothingCloset());

							} else if (guestRoomChoice.equals(Night1Constants.NIGHT1_GUEST_ROOM_MENU_HALLWAY)) {
								menu.clearConsole();
								break;
							}
						}
					} else if (hallwayChoice.equals(Night1Constants.NIGHT1_HALLWAY_MENU_BEDROOM)) {
						menu.clearConsole();
						break;

					} else if (hallwayChoice.equals(Night1Constants.NIGHT1_HALLWAY_MENU_FOYER)) {
						menu.clearConsole();

						if (!e.isFoyerFirst()) {
							e.setFoyerFirst(true);
							System.out.println(txt.foyerFirst());
						}

						System.out.println(txt.foyer());

						while (!a.isFoyer()) {

							if (e.getFlashlight() == 1) {
								System.out.println(txt.needFlashlight());
								break;
							}

							String foyerChoice = (String) menu
									.getChoiceFromOptions(Night1Constants.NIGHT1_FOYER_MENU_OPTIONS);

							if (foyerChoice.equals(Night1Constants.NIGHT1_FOYER_MENU_DINING)) {
								menu.clearConsole();

								if (!e.isDiningFirst()) {
									System.out.println(txt.diningFirst());
									e.setDiningFirst(true);
								}

								System.out.println(txt.dining());

								while (!a.isDining()) {

									String diningChoice = (String) menu
											.getChoiceFromOptions(Night1Constants.NIGHT1_DINING_MENU_OPTIONS);

									if (diningChoice.equals(Night1Constants.NIGHT1_DINING_MENU_GRANDFATHER_CLOCK)) {
										menu.clearConsole();
										System.out.println(txt.clock());

										if (!e.isNight1FoundDog()) {
											System.out.println(txt.lola());
											e.setDogCount(e.getDogCount() + 1);
											e.setNight1FoundDog(true);
										}
									} else if (diningChoice.equals(Night1Constants.NIGHT1_DINING_MENU_FIREPLACE)) {
										menu.clearConsole();
										System.out.println(txt.fire());
										new Thread(() -> aud.play(aud.match())).start();

										while (!a.isPainting()) {
											String paintingChoice = (String) menu
													.getChoiceFromOptions(Night1Constants.NIGHT1_PAINTING_MENU_OPTIONS);

											if (paintingChoice
													.equals(Night1Constants.NIGHT1_PAINTING_MENU_LOOK_CLOSER)) {
												menu.clearConsole();

												if (!e.isPaintingFirst()) {
													System.out.println(txt.paintingFirst());
													e.setPaintingFirst(true);
													System.out.println(txt.takeAShower());
													e.setShower(true);

												} else {
													System.out.println(txt.safe());
												}
											} else if (paintingChoice
													.equals(Night1Constants.NIGHT1_PAINTING_MENU_BACK_TO_DINING)) {
												menu.clearConsole();
												System.out.println(txt.extinguish());
												break;
											}
										}
									} else {
										menu.clearConsole();
										System.out.println(txt.foyer());
										break;
									}
								}
							} else if (foyerChoice.equals(Night1Constants.NIGHT1_FOYER_MENU_BASEMENT)) {
								menu.clearConsole();
								if (e.getFlashlight() == 2) {
									System.out.println(txt.breaker());
									new Thread(() -> aud.play(aud.door())).start();
									enter.nextLine();
									menu.clearConsole();
									System.out.println(h.face());
									new Thread(() -> aud.play(aud.dread())).start();
									System.out.println(txt.night1End());
									enter.nextLine();
									n2.runNight2(e, a);

								} else if (e.getFlashlight() < 2) {
									System.out.println(txt.basement());
								}
							} else if (foyerChoice.equals(Night1Constants.NIGHT1_FOYER_MENU_HALLWAY)) {
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