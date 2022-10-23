import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(" Welcome to Hangman!");

		while (true) { // game will run until system terminates
			playgame();

		}
	}

	public static boolean playgame() throws FileNotFoundException {

		Scanner InputAnsForPlayAgain = new Scanner(System.in);//scanner to get input about play again or not
		Scanner UserInput = new Scanner(System.in);

		double Scores = 0.0;
		int Setlives = 0;
		double reward = 0.0;

		System.out.println("1 or 2 players?");
		String playersPreferrence = UserInput.nextLine();// 
		String randomWord;
		if (playersPreferrence.equals("1")) {

			Scanner wordgenerator = new Scanner(
					new File("C:\\Users\\Rakib Mojumder\\OneDrive\\Desktop\\wordlistM.txt"));//getting wordlist 

			Scanner Keyboard = new Scanner(System.in); // Scanner for user input
			//Scanner Input = new Scanner(System.in); //

			ArrayList<String> Wordlist = new ArrayList<>(); // creating an ArrayList to put the wordlist
			while (wordgenerator.hasNext()) {

				Wordlist.add(wordgenerator.nextLine().toLowerCase());
			} // putting the words on the ArrayList

			Random random = new Random(); // Creating random obj from Random class

			randomWord = Wordlist.get(random.nextInt(Wordlist.size()));
		} else {
			System.out.println("Player 1, please enter a secret word:");
			Scanner Givetheword = new Scanner(System.in);// scanner , to input a secret word
			randomWord = Givetheword.nextLine().toLowerCase();
			//make the word lowercase so that user can input all their guess lowercase
			//System.out.println("");
			System.out.println("Ready for player 2! Good luck!");
		}
		System.out.println("Input Defficulty level from 1 to 4:-");
		String inputedDifficulty = UserInput.next();
		if (inputedDifficulty.matches("1")) {

			Setlives = 5;// set lives depending on difficulty level
			reward = 2.0; // set reward depending on difficulty level to that it can use to count scores
		}

		else if (inputedDifficulty.matches("2")) {
			Setlives = 4;
			reward = 2.5;
		} else if (inputedDifficulty.matches("3")) {
			Setlives = 3;
			reward = (10 / 3);
		} else if (inputedDifficulty.matches("4")) {
			Setlives = 2;
			reward = 5.0;
		} else {//if invalid  difficulty level set the difficulty level 1
			Setlives = 5;
			reward = 2.0;
			System.out.println("Opps!!... wrong Defficulty level");
			System.out.println("Difficulty level 1 (Automatic Setup)");
		}

		//System.out.println(randomWord);

		char[] RandomWordInChar = randomWord.toCharArray();// change the secret word from string to char 

		char[] Playerguesses = new char[RandomWordInChar.length];
		System.out.println("This is a " + RandomWordInChar.length + " Lettere's word");

		for (int i = 0; i < RandomWordInChar.length; i++) {
			Playerguesses[i] = '?';

		}
		int warning = 0; // declare variable warning so that it can use to count warning 
		boolean finished = false;
		int lives = Setlives;

		while (finished == false) {
			System.out.println("Please enter your guess:- ");
			String inputedletter = UserInput.next();

			// check for valid input
			
			if (Character.isDigit(inputedletter.charAt(0))) {// if the guessed char is an number
				System.out.println("Error Input - Try again");
				warning++; //

				
				if (warning <= 2) { //let the user input invalid input without costing any lives until it becomes three 
					System.out.println(" Lives left: " + lives);
					System.out.println("worning number :" + warning);
					continue;
					
				}

			}

			boolean found = false;

			if (!Character.isDigit(inputedletter.charAt(0))) {// if the guessed char is a letter
				for (int i = 0; i < RandomWordInChar.length; i++) {
					if (inputedletter.charAt(0) == RandomWordInChar[i]) {
						Playerguesses[i] = RandomWordInChar[i];
						found = true;
					}
				}
			}

			if ((!found) || (warning == 3)) {
				//if guessed char is not on the secret word or the player put 3 invalid input it will cost on lives
				
				lives--;

				System.out.println("Wrong Letter");
				System.out.println("worning number : " + warning);
				System.out.println(" Lives left: " + lives);

				warning++;

			}

			boolean done = true; //check if the guess is done
			for (int i = 0; i < Playerguesses.length; i++) {
				if (Playerguesses[i] == '?') {
					System.out.print(" _ ");
					done = false;
					warning = 0;// set the worning 0
				} else {
					System.out.print(" " + Playerguesses[i]);
				}
			}
			System.out.println("\n" + "Lives Left: " + lives);
			// drawHangman(lives);
			// check if the game ends
			if (done) {
				System.out.println("Congratulations..!! You WIN..");
				finished = true;
				Scores = lives * reward;
				System.out.println("The Score is " + Scores);
				System.out.println();
				System.out.println("The word was " + randomWord);
				System.out.println("Would you like to play again?");
				System.out.println("         (Yes/No)   ");
			}
			if (lives <= 0) {

				System.out.println("You lose..!! better luck next time..");
				System.out.println("|********|");
				System.out.println("|        0");
				System.out.println("|     ---||---");
				System.out.println("|        ||");
				System.out.println("|       //\\");

				System.out.println();
				System.out.println("The word was " + randomWord);
				finished = true;
				Scores = (lives * reward);
				System.out.println("The Score is " + Scores);
				System.out.println("Would you like to play again?");
				System.out.println("         (Yes/No)   ");

			}
		}
		String InputedAnsForPlayAgain = UserInput.next().toLowerCase();

		if (InputedAnsForPlayAgain.matches("yes")) {
			

			return true;

		} else {
			System.out.println("looged out");

			System.exit(0);// if no , let the system terminates 

		}
		
		return finished;

	}
}
