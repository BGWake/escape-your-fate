package night3;

import java.util.Scanner;

import application.Areas;
import application.Events;
import application.Restart;
import audio.Audio;
import audio.AudioLoops;
import text.Headers;
import text.Night3Txt;
import view.Menu;

public class Night3 {

	public Night3(Menu menu) {
		this.menu = menu;
	}

	Menu menu = new Menu(System.in, System.out);
	Scanner userInput = new Scanner(System.in);
	Audio aud = new Audio();
	AudioLoops al = new AudioLoops();

	Night3Txt txt = new Night3Txt();
	Headers h = new Headers();
	Restart r = new Restart();

	public void runNight3(Events e, Areas a) {

		menu.clearConsole();

		System.out.println(h.night3Header());
		if (e.isCamera()) {
			System.out.println(txt.night3Start());
		} else {
			System.out.println(txt.noCamera());
		}
		userInput.nextLine();
		menu.clearConsole();

		while (true) {

			if (e.getChase() == 2) {
				quickDeath(e, a);
			}

			System.out.println(txt.inBedroom());

			String bedroomChoice = (String) menu.getChoiceFromOptions(Night3Constants.NIGHT3_BEDROOM_MENU_OPTIONS);

			if (bedroomChoice.equals(Night3Constants.NIGHT3_BEDROOM_MENU_KEEP_CODING)) {
				menu.clearConsole();

				if (e.getChase() == 1) {
					quickDeath(e, a);
				}

				System.out.println(txt.keepCoding());
				e.setCodeNight3(e.getCodeNight3() + 1);

				if (e.getCodeNight3() == 5 && e.isSecCodeNight1() && e.isSecCodeNight2()) {
					System.out.println(txt.keepCodingNight3());
					userInput.nextLine();
					menu.clearConsole();
					System.out.println(txt.codeEnding());
					userInput.nextLine();
					menu.clearConsole();
					System.out.println(txt.credits());
					r.restartNewGame();

				} else if (e.getCodeNight3() == 5) {
					menu.clearConsole();
					System.out.println(h.face());
					aud.play(aud.yell());
					System.out.println(txt.codeDeath());
					r.restartGame(e, a);
				}
			} else if (bedroomChoice.equals(Night3Constants.NIGHT3_BEDROOM_MENU_CAMERA)) {
				menu.clearConsole();

				if (e.getChase() == 1) {
					quickDeath(e, a);
				}
				if (!e.isBedroomFound()) {
					camera(e, 1);
				} else {
					System.out.println(txt.alreadyFound());
				}

			} else if (bedroomChoice.equals(Night3Constants.NIGHT3_BEDROOM_MENU_EXPLORE)) {
				menu.clearConsole();
				
				if (e.getChase() == 1) {
					e.setChase(2);
					e.setBedroomFound(true);
					System.out.println(txt.run());
				}

				while (!a.isHallway()) {

					System.out.println(txt.enterHallway());

					String hallwayChoice = (String) menu
							.getChoiceFromOptions(Night3Constants.NIGHT3_HALLWAY_MENU_OPTIONS);

					if (hallwayChoice.equals(Night3Constants.NIGHT3_HALLWAY_MENU_BATHROOM)) {
						menu.clearConsole();
						al.setDrip(true);
						Thread dripThread = new Thread(() -> {
							while (al.isDrip()) {
								aud.play(aud.drip());
							}
						});
						dripThread.start();

						if (e.getChase() == 2) {
							al.setDrip(false);
							quickDeath(e, a);
						}

						System.out.println(txt.enterBathroom());

						while (!a.isBathroom()) {

							String bathroomChoice = (String) menu
									.getChoiceFromOptions(Night3Constants.NIGHT3_BATHROOM_MENU_OPTIONS);

							if (bathroomChoice.equals(Night3Constants.NIGHT3_BATHROOM_MENU_SHOWER)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									al.setDrip(false);
									quickDeath(e, a);
								}

								System.out.println(txt.shower());

							} else if (bathroomChoice.equals(Night3Constants.NIGHT3_BATHROOM_MENU_SINK)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									al.setDrip(false);
									quickDeath(e, a);
								}

								System.out.println(txt.mirror());

							} else if (bathroomChoice.equals(Night3Constants.NIGHT3_BATHROOM_MENU_CAMERA)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									al.setDrip(false);
									quickDeath(e, a);
								}
								if (!e.isBathroomFound()) {
									camera(e, 2);
								} else {
									System.out.println(txt.alreadyFound());
								}

							} else if (bathroomChoice.equals(Night3Constants.NIGHT3_BATHROOM_MENU_HALLWAY)) {
								menu.clearConsole();
								al.setDrip(false);
								if (e.getChase() == 1) {
									e.setChase(2);
									e.setBathroomFound(true);
									System.out.println(txt.run());
								}
								break;
							}
						}
					} else if (hallwayChoice.equals(Night3Constants.NIGHT3_HALLWAY_MENU_GUEST_ROOM)) {
						menu.clearConsole();

						if (e.getChase() == 2) {
							quickDeath(e, a);
						}

						while (!a.isGuestRoom()) {

							String guestRoomChoice = (String) menu
									.getChoiceFromOptions(Night3Constants.NIGHT3_GUEST_ROOM_MENU_OPTIONS);

							if (guestRoomChoice.equals(Night3Constants.NIGHT3_GUEST_ROOM_MENU_WINDOW)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									quickDeath(e, a);
								}

								System.out.println(txt.window());

							} else if (guestRoomChoice.equals(Night3Constants.NIGHT3_GUEST_ROOM_MENU_CLOSET)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									quickDeath(e, a);
								}

								System.out.println(txt.nothingCloset());

							} else if (guestRoomChoice.equals(Night3Constants.NIGHT3_GUEST_ROOM_MENU_CAMERA)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									quickDeath(e, a);
								}
								if (!e.isGuestRoomFound()) {
									camera(e, 3);
								} else {
									System.out.println(txt.alreadyFound());
								}

							} else if (guestRoomChoice.equals(Night3Constants.NIGHT3_GUEST_ROOM_MENU_HALLWAY)) {
								menu.clearConsole();

								if (e.getChase() == 1) {
									e.setChase(2);
									e.setGuestRoomFound(true);
									System.out.println(txt.run());
								}
								break;
							}
						}
					} else if (hallwayChoice.equals(Night3Constants.NIGHT3_HALLWAY_MENU_BEDROOM)) {
						menu.clearConsole();
						break;

					} else if (hallwayChoice.equals(Night3Constants.NIGHT3_HALLWAY_MENU_FOYER)) {
						menu.clearConsole();

						cameraEnd(e);

						if (e.getChase() == 2) {
							e.setChase(0);
							System.out.println(txt.escaped());
						}

						System.out.println(txt.foyer());

						while (!a.isFoyer()) {

							String foyerChoice = (String) menu
									.getChoiceFromOptions(Night3Constants.NIGHT3_FOYER_MENU_OPTIONS);

							if (foyerChoice.equals(Night3Constants.NIGHT3_FOYER_MENU_DINING)) {
								menu.clearConsole();

								if (e.getChase() == 3) {
									quickDeath(e, a);
								}
								if (e.getChase() == 4) {
									e.setChase(0);
									System.out.println(txt.escaped());
								}

								System.out.println(txt.dining());

								while (!a.isDining()) {

									String diningChoice = (String) menu
											.getChoiceFromOptions(Night3Constants.NIGHT3_DINING_MENU_OPTIONS);

									if (diningChoice.equals(Night3Constants.NIGHT3_DINING_MENU_GRANDFATHER_CLOCK)) {
										menu.clearConsole();

										if (e.getChase() == 0) {
											System.out.println(txt.clock());

										} else if (e.getChase() == 1) {
											quickDeath(e, a);
										}

									} else if (diningChoice.equals(Night3Constants.NIGHT3_DINING_MENU_FIREPLACE)) {
										menu.clearConsole();

										if (e.getChase() == 0) {
											menu.clearConsole();
											System.out.println(txt.fire());
											new Thread(() -> aud.play(aud.match())).start();

											while (!a.isPainting()) {
												String paintingChoice = (String) menu.getChoiceFromOptions(
														Night3Constants.NIGHT3_PAINTING_MENU_OPTIONS);

												if (paintingChoice
														.equals(Night3Constants.NIGHT3_PAINTING_MENU_LOOK_CLOSER)) {
													menu.clearConsole();

													if (e.isFirstNumber() && e.isSecondNumber()
															&& e.isThirdNumber()) {

														System.out.println(txt.safeOne());
														String first = userInput.nextLine();
														
														if (first.equals("13")) {
															System.out.println(txt.safeTwo());
															String second = userInput.nextLine();
															
															if (second.equals("4")) {
																System.out.println(txt.safeThree());
																String third = userInput.nextLine();
																
																if (third.equals("42")) {
																	menu.clearConsole();
																	System.out.println(txt.phone());
																	userInput.nextLine();
																	menu.clearConsole();
																	System.out.println(h.busters());
																	aud.play(aud.afraid());
																	new Thread(() -> aud.play(aud.busters())).start();
																	menu.clearConsole();
																	System.out.println(txt.busters());
																	userInput.nextLine();
																	menu.clearConsole();
																	System.out.println(txt.credits());
																	System.out.println(txt.bustersCredit());
																	r.restartNewGame();

																} else {
																	menu.clearConsole();
																	System.out.println(txt.wrong());
																}
															} else {
																menu.clearConsole();
																System.out.println(txt.wrong());
															}
														} else {
															menu.clearConsole();
															System.out.println(txt.wrong());
														}

													} else {
														System.out.println(txt.safe());
													}

												} else if (paintingChoice
														.equals(Night3Constants.NIGHT3_PAINTING_MENU_BACK_TO_DINING)) {
													menu.clearConsole();
													System.out.println(txt.extinguish());
													break;
												}
											}
										} else if (e.getChase() == 1) {
											quickDeath(e, a);
										}
									}

									else if (diningChoice.equals(Night3Constants.NIGHT3_DINING_MENU_CAMERA)) {
										menu.clearConsole();

										if (e.getChase() == 1) {
											quickDeath(e, a);
										}
										if (!e.isDiningRoomFound()) {
											camera(e, 2);
										} else {
											System.out.println(txt.alreadyFound());
										}
									}

									else {
										menu.clearConsole();

										cameraEnd(e);

										if (e.getChase() == 1) {
											e.setChase(3);
											e.setDiningRoomFound(true);

											System.out.println(txt.run());
											System.out.println(txt.foyer());
										}
										break;

									}
								}

							} else if (foyerChoice.equals(Night3Constants.NIGHT3_FOYER_MENU_BASEMENT)) {
								menu.clearConsole();

								if (e.getChase() == 3) {
									e.setChase(0);
									System.out.println(txt.escaped());
								}

								if (e.getChase() == 4) {
									quickDeath(e, a);
								}

								new Thread(() -> aud.play(aud.doorShort())).start();
								System.out.println(txt.basement());

								while (!a.isBasement()) {
									String basementChoice = (String) menu
											.getChoiceFromOptions(Night3Constants.NIGHT3_BASEMENT_MENU_OPTIONS);

									if (basementChoice.equals(Night3Constants.NIGHT3_BASEMENT_MENU_LOOK_AROUND)) {
										menu.clearConsole();

										if (e.getChase() == 1) {
											quickDeath(e, a);
										}
										System.out.println(txt.hateIt());

									}

									else if (basementChoice.equals(Night3Constants.NIGHT3_BASEMENT_MENU_CAMERA)) {
										menu.clearConsole();

										if (e.getChase() == 1) {
											quickDeath(e, a);
										}
										if (!e.isBasementFound()) {
											camera(e, 1);
										} else {
											System.out.println(txt.alreadyFound());
										}
									}

									else if (basementChoice.equals(Night3Constants.NIGHT3_BASEMENT_MENU_FOYER)) {
										menu.clearConsole();
										if (e.getChase() == 1) {
											e.setChase(4);
											e.setBasementFound(true);
											System.out.println(txt.run());
										}
										
										cameraEnd(e);
										System.out.println(txt.foyer());
										break;
									}
								}

							} else if (foyerChoice.equals(Night3Constants.NIGHT3_FOYER_MENU_HALLWAY)) {
								menu.clearConsole();

								if (e.getChase() == 3 || e.getChase() == 4) {
									e.setChase(0);
									System.out.println(txt.escaped());
								}
								break;

							} else if (foyerChoice.equals(Night3Constants.NIGHT3_FOYER_MENU_DOG)) {
								menu.clearConsole();
								if (e.getChase() == 3 || e.getChase() == 4) {
									quickDeath(e, a);
								}
								if (!e.isNight3FoundDog()) {
									System.out.println(txt.lola());
									e.setDogCount(e.getDogCount() + 1);
									e.setNight3FoundDog(true);
								} else {
									System.out.println(txt.nap());
								}
							}
						}
					}
				}
			}
		}
	}

	public int random() {
		int range = 3;
		return (int) (Math.random() * range) + 1;
	}

	public void camera(Events e, int roomAssignment) {
		menu.clearConsole();
		System.out.println(txt.picture());
		aud.play(aud.camera());
		menu.clearConsole();
		int random = random();

		if (random == roomAssignment) {
			System.out.println(h.face());
			aud.play(aud.laugh());
			menu.clearConsole();
			new Thread(() -> aud.play(aud.dread())).start();
			System.out.println(txt.appeared());
			e.setChase(1);
			e.setCameraCount(e.getCameraCount() + 1);

		} else {
			System.out.println(txt.nothing());
		}
	}

	public void cameraEnd(Events e) {
		if (e.getCameraCount() == 5) {
			if ((e.getDogCount() < 3 && !e.isGoodGuess()) || (e.getDogCount() < 2)) {
				menu.clearConsole();
				System.out.println(h.face());
				aud.play(aud.laugh());
				menu.clearConsole();
				System.out.println(txt.ending1());
				aud.play(aud.yell());
				userInput.nextLine();
				menu.clearConsole();
				System.out.println(txt.credits());
				r.restartNewGame();
				
			} else if ((e.getDogCount() >= 2 && e.isGoodGuess()) || (e.getDogCount() == 3 && !e.isGoodGuess())) {
				menu.clearConsole();
				System.out.println(h.face());
				aud.play(aud.dread());
				menu.clearConsole();
				System.out.println(txt.ending2());
				userInput.nextLine();
				menu.clearConsole();
				System.out.println(txt.credits());
				r.restartNewGame();
			}
		}
	}

	public void quickDeath(Events e, Areas a) {
		menu.clearConsole();
		System.out.println(h.face());
		aud.play(aud.yell());
		System.out.println(txt.quickDeath());
		r.restartGame(e, a);
	}
}