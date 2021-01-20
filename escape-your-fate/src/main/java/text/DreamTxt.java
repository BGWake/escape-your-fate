package text;

public class DreamTxt {
	
	public String dreamStart() {
		return "\nIt's that thing again... You can't move. Are you dreaming? You feel completely paralyzed as it"
				+ "\nhovers over you. Suddenly, a voice speaks to you."
				+ "\n\n\"I'm curious, so tell me. What do you think I am?\""
				+ "\nType your answer.";
		
	}
	public String closeGuess() {
		return "\n\"I'm somewhat impressed, that's pretty close. However, you're still off. I thought I taught"
				+ "\nyou better than that.\"";
	}
	public String goodGuess() {
		return "\n\"That's a good guess, so I'll help you out and give you a hint. The final number for the"
				+ "\ncombination to the safe is the answer to life, the universe, and everything.\"";
	}
	public String badGuess() {
		return "\n\"I'm disappointed. That answer is wrong, not even close. I thought I taught you better than that.\"";
	}
	public String enter() {
		return "\nPress enter to continue...";
	}
}
