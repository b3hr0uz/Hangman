import java.io.*;
import java.util.*;
public class HangMan {
	
	public static void main(String[] args) throws IOException{		
		HangManGame();
	}
	
	@SuppressWarnings({ "resource" })
	static void HangManGame() throws IOException {
		
		// Hangman implementation goes below.
		String[] words = new String[183719];
		readWords(words);
		// Selecting a random word from the dictionary.
		String word = words[(int)(Math.random()* words.length)]; 
		System.out.println(word);
		
		// Initializing the Scanner for user input.
		Scanner userInput = new Scanner(System.in);
		
		// Initializing a temporary string for showing the asterisks and comparing it with selected word.
		String guessedWord = "";
		for (int i = 0; i < word.length(); i++)
			guessedWord += "*";
		
		// Setting counter for displaying the number of misses.
		int missesCount = 0;
		
		// Creating the condition of the end of the game
		while(!guessedWord.equals(word)) {
			System.out.println("(Guess) Enter a letter in word");
			System.out.print(guessedWord + " > ");
			
			// Handling guessed character format.
			String guessedChar  = userInput.nextLine();
			guessedChar = guessedChar.toLowerCase();
			
			// Checking guessed character length and lack of number and sign.
			while(guessedChar.length() != 1 || guessedChar.charAt(0) < 'a' || guessedChar.charAt(0) > 'z'){	
			    System.out.println("Invalid Input (Length exceeds one or contains number or sign). Enter a single alphabetic letter:");
			    guessedChar = userInput.nextLine();
			}
			
			// Initializing hitFlag for missedCount.
			boolean hitFlag = false;
			// Checking the word for correct guessed character and replacing asterisk(s) with it.
			for (int i = 0; i < word.length(); i++) {
				if(guessedChar.charAt(0) == word.charAt(i)) {
					// Setting flag for miss count.
					hitFlag = true;
					// Initializing String Builder
			        StringBuilder strBuilder = new StringBuilder(guessedWord);
			        // Replace character at the specified position
			        strBuilder.setCharAt(i, guessedChar.charAt(0));
			        guessedWord = strBuilder.toString();			 
				}
			}
			// Increasing the number of miss counts per miss.
			// Already found letters do not count toward misses because they are basically redundant hits.
			if(!hitFlag)
				missesCount++ ;
		}
		// Reveling the word and displaying number of misses.
		System.out.println("The word is " + word + ". You missed " + missesCount + " times.");
		
		// Asking if user wants to continue with another word or quit the game.
		System.out.print("Do you want to guess for another word?\nEnter y or n > ");
		// Asking user for input regarding restarting or ending the game.
		String notGameOver  = userInput.nextLine();
		notGameOver = notGameOver.toLowerCase();
		// Handling invalid input by user.
		while(notGameOver.length() != 1 && notGameOver.charAt(0) != 'y' && notGameOver.charAt(0) != 'n'){	
			System.out.println("Invalid Input. Enter Y or N:");
			notGameOver = userInput.nextLine();
			}
		
		// Final if-else statement regarding recalling Hangman.
		if(notGameOver.charAt(0) == 'y')
			HangManGame();
		else
			System.out.println("Game Over!");
	}
	
	// Storing the words from dictionary.txt in an array.
	public static void readWords(String[] words)throws IOException {
		File dict = new File("dictionary.txt");
		try(Scanner in = new Scanner(dict);){
			int i = 0;
			while(in.hasNext()) {
				words[i++] = in.next();
			}
		}
	}

}
