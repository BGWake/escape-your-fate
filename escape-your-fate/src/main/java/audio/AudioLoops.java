package audio;

public class AudioLoops {

	Audio aud = new Audio();

	private boolean drip = false;
	private boolean clockTicking = false;
	private boolean dread = false;

	private Thread clockTickingThread = new Thread(() -> {
		while (isClockTicking()) {
			aud.play(aud.clockTicking());
		}
	});
	private Thread dreadThread = new Thread(() -> {
		while (isDread()) {
			aud.play(aud.dread());
		}
	});

	public boolean isDrip() {
		return drip;
	}

	public void setDrip(boolean drip) {
		this.drip = drip;
	}

	public boolean isClockTicking() {
		return clockTicking;
	}

	public void setClockTicking(boolean clockTicking) {
		this.clockTicking = clockTicking;
	}

	public boolean isDread() {
		return dread;
	}

	public void setDread(boolean dread) {
		this.dread = dread;
	}

	public Thread getClockTickingThread() {
		return clockTickingThread;
	}

	public Thread getDreadThread() {
		return dreadThread;
	}
}
