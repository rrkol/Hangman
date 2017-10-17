import java.util.*;

public class HangmanGameplay {
	
	String gameWord;
	char[] wordDisplay;
	
	public void run() {
		while(true)
		{
			System.out.println("New game? Y/N");
			Scanner sc = new Scanner(System.in);
			String response = sc.nextLine();
			
			if (response.equalsIgnoreCase("Y"))
			{
				HangmanLexicon hangmanLex = new HangmanLexicon();
				Random rand = new Random();
				gameWord = hangmanLex.getWord(rand.nextInt(10));
				playGame();
			}
			else if (response.equalsIgnoreCase("N"))
			{
				break;
			}
			else
			{
				System.out.println("I do not understand.");
			}
		}
	}
	
	public String getGuess() {
		String guess;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a letter: ");
		guess = sc.nextLine();
		
		if (!validateGuess(guess)) {
			guess = getGuess();
		}
		
		return guess;
	}
	
	public void initWordDisplay() {
		wordDisplay = new char[gameWord.length()];
		for (int i = 0; i < gameWord.length(); i++) {
			wordDisplay[i] = '_';
		}
	}
	
	public void playGame() {
		int guessesLeft = 8;
		initWordDisplay();
		
		while (guessesLeft > 0)
		{
			String gameDisplay = new String(wordDisplay);
			
			System.out.println("The following is your word:");
			System.out.println(gameDisplay);
			String guess = getGuess();
			
			if (processGuess(guess)) {
				System.out.println("Letter was found.");
			}
			else {
				guessesLeft--;
				System.out.println("Incorrect guess.");
			}
				
			if (validateSolution()) { 
				System.out.println("You win!");
				break;
			}
		}
		
		if (guessesLeft == 0) {
			System.out.println("You lose...the word was " + gameWord);
		}
		
	}
	
	public boolean processGuess(String guessLetter) {
		boolean found = false;
		
		for (int i = 0; i < gameWord.length(); i++) {
			if (Character.toString(gameWord.charAt(i)).equalsIgnoreCase(guessLetter)) {
				found = true;
				
				wordDisplay[i] = gameWord.charAt(i);
			}
		}
		
		return found;
	}
	
	public boolean validateGuess(String guessLetter) {
		boolean valid = true;
		if (guessLetter.length() > 1 || !Character.isLetter(guessLetter.charAt(0))) {
			System.out.println("Invalid Guess. Please try again.");
			valid = false;
		}
		
		return valid;
	}
	
	public boolean validateSolution() {
		boolean solved = true;
		for (char c : wordDisplay) {
			if (c == '_') {
				solved = false;
				break;
			}
		}
		
		return solved;
	}
}
