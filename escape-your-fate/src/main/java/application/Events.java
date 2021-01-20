package application;

public class Events {

	private int flashlight = 0;
	private int dogCount = 0;
	private int cameraCount = 0;
	private int chase = 0;
	private int codeNight1 = 0;
	private int codeNight2 = 0;
	private int codeNight3 = 0;
	private int mirror = 0;

	private boolean bedroomFirst = false;
	private boolean guestRoomFirst = false;
	private boolean foyerFirst = false;
	private boolean diningFirst = false;
	private boolean bathroomFirst = false;
	private boolean paintingFirst = false;

	private boolean shower = false;
	private boolean camera = false;

	private boolean bedroomFound = false;
	private boolean bathroomFound = false;
	private boolean guestRoomFound = false;
	private boolean diningRoomFound = false;
	private boolean basementFound = false;

	private boolean night1FoundDog = false;
	private boolean night2FoundDog = false;
	private boolean night3FoundDog = false;

	private boolean secCodeNight1 = false;
	private boolean secCodeNight2 = false;

	private boolean firstNumber = false;
	private boolean secondNumber = false;
	private boolean thirdNumber = false;

	private boolean goodGuess = false;

	public boolean isFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(boolean firstNumber) {
		this.firstNumber = firstNumber;
	}

	public boolean isSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(boolean secondNumber) {
		this.secondNumber = secondNumber;
	}

	public boolean isThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(boolean thirdNumber) {
		this.thirdNumber = thirdNumber;
	}

	public int getFlashlight() {
		return flashlight;
	}

	public void setFlashlight(int flashlight) {
		this.flashlight = flashlight;
	}

	public int getChase() {
		return chase;
	}

	public void setChase(int chase) {
		this.chase = chase;
	}

	public int getDogCount() {
		return dogCount;
	}

	public void setDogCount(int dogCount) {
		this.dogCount = dogCount;
	}

	public int getCameraCount() {
		return cameraCount;
	}

	public void setCameraCount(int cameraCount) {
		this.cameraCount = cameraCount;
	}

	public int getCodeNight1() {
		return codeNight1;
	}

	public void setCodeNight1(int codeNight1) {
		this.codeNight1 = codeNight1;
	}

	public int getCodeNight2() {
		return codeNight2;
	}

	public void setCodeNight2(int codeNight2) {
		this.codeNight2 = codeNight2;
	}

	public int getCodeNight3() {
		return codeNight3;
	}

	public void setCodeNight3(int codeNight3) {
		this.codeNight3 = codeNight3;
	}

	public int getMirror() {
		return mirror;
	}

	public void setMirror(int mirror) {
		this.mirror = mirror;
	}

	public boolean isBedroomFirst() {
		return bedroomFirst;
	}

	public void setBedroomFirst(boolean bedroomFirst) {
		this.bedroomFirst = bedroomFirst;
	}

	public boolean isGuestRoomFirst() {
		return guestRoomFirst;
	}

	public void setGuestRoomFirst(boolean guestRoomFirst) {
		this.guestRoomFirst = guestRoomFirst;
	}

	public boolean isFoyerFirst() {
		return foyerFirst;
	}

	public void setFoyerFirst(boolean foyerFirst) {
		this.foyerFirst = foyerFirst;
	}

	public boolean isDiningFirst() {
		return diningFirst;
	}

	public void setDiningFirst(boolean diningFirst) {
		this.diningFirst = diningFirst;
	}

	public boolean isBathroomFirst() {
		return bathroomFirst;
	}

	public void setBathroomFirst(boolean bathroomFirst) {
		this.bathroomFirst = bathroomFirst;
	}

	public boolean isPaintingFirst() {
		return paintingFirst;
	}

	public void setPaintingFirst(boolean paintingFirst) {
		this.paintingFirst = paintingFirst;
	}

	public boolean isShower() {
		return shower;
	}

	public void setShower(boolean shower) {
		this.shower = shower;
	}

	public boolean isCamera() {
		return camera;
	}

	public void setCamera(boolean camera) {
		this.camera = camera;
	}

	public boolean isBedroomFound() {
		return bedroomFound;
	}

	public void setBedroomFound(boolean bedroomFound) {
		this.bedroomFound = bedroomFound;
	}

	public boolean isBathroomFound() {
		return bathroomFound;
	}

	public void setBathroomFound(boolean bathroomFound) {
		this.bathroomFound = bathroomFound;
	}

	public boolean isGuestRoomFound() {
		return guestRoomFound;
	}

	public void setGuestRoomFound(boolean guestRoomFound) {
		this.guestRoomFound = guestRoomFound;
	}

	public boolean isDiningRoomFound() {
		return diningRoomFound;
	}

	public void setDiningRoomFound(boolean diningRoomFound) {
		this.diningRoomFound = diningRoomFound;
	}

	public boolean isBasementFound() {
		return basementFound;
	}

	public void setBasementFound(boolean basementFound) {
		this.basementFound = basementFound;
	}

	public boolean isNight1FoundDog() {
		return night1FoundDog;
	}

	public void setNight1FoundDog(boolean night1FoundDog) {
		this.night1FoundDog = night1FoundDog;
	}

	public boolean isNight2FoundDog() {
		return night2FoundDog;
	}

	public void setNight2FoundDog(boolean night2FoundDog) {
		this.night2FoundDog = night2FoundDog;
	}

	public boolean isNight3FoundDog() {
		return night3FoundDog;
	}

	public void setNight3FoundDog(boolean night3FoundDog) {
		this.night3FoundDog = night3FoundDog;
	}

	public boolean isSecCodeNight1() {
		return secCodeNight1;
	}

	public void setSecCodeNight1(boolean secCodeNight1) {
		this.secCodeNight1 = secCodeNight1;
	}

	public boolean isSecCodeNight2() {
		return secCodeNight2;
	}

	public void setSecCodeNight2(boolean secCodeNight2) {
		this.secCodeNight2 = secCodeNight2;
	}

	public boolean isGoodGuess() {
		return goodGuess;
	}

	public void setGoodGuess(boolean goodGuess) {
		this.goodGuess = goodGuess;
	}
}
