/**Generates a scrambled word puzzle and a corresponding answer key
 * @author Chris Tran and Will Wei
 * @version February 20, 2016
 */
//Import needed classes
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class WordScrambler {
	
	public static void main(String[] args) throws Exception {
		// Ask for and enter the name of the text file
		String fileName = JOptionPane.showInputDialog("Please enter the name of your file! (e.g. Words.txt)");
		
		//Create a new Scanner to read from the file
		Scanner input = new Scanner(new File(fileName));
		
		//Initialize the ArrayLists to store the original words, the definitions, and the scrambled words
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> definitions = new ArrayList<String>();
		ArrayList<String> scrambledWords = new ArrayList<String>();
		
		//Keep track of the number of words and the longest word for future formatting
		int noOfLines = 0;
		int longestWordLength = 0;
		
		//Read in all the lines of the text file
		while(input.hasNextLine())
		{
			//Read in and keep track of the words and their respective definitions
			String word = input.next();
			words.add(word);
			definitions.add(input.nextLine());
			
			//Keep track of the longest word length
			if(word.length() > longestWordLength)
				longestWordLength = word.length();
			
			//Scramble each word and rescramble if the scrambled word is the same as the original word
			String scrambledWord;
			do
			{
				//Keep track of which letters are used and initialize the new word
				boolean[] letterUsed = new boolean[word.length()];
				scrambledWord = "";
				
				//Randomly place each letter of the word in order
				for(int letter = 0; letter < word.length(); letter++)
				{
					int letterPicked = (int)(Math.random()*word.length());
					//Repick the letter if it has already been used
					while(letterUsed[letterPicked])
						letterPicked = (int)(Math.random()*word.length());
					
					//Add the letter to the new word and set the letter as used
					scrambledWord += word.charAt(letterPicked);
					letterUsed[letterPicked] = true;
				}
			}while(scrambledWord.compareTo(word) == 0 && word.length() > 1);//Keep scrambling if the word is unscrambled, unless if it is just one letter long
			
			//Add the new word to the list of scrambled words and keep track of the number of words
			scrambledWords.add(scrambledWord);
			noOfLines++;
		}
		
		//Format the size of the blank to the size of the longest word
		String blank = "";
		for(int letter = 1; letter <= longestWordLength; letter++)
			blank += "_";
		
		//Close the input Scanner
		input.close();
		
		//Print out the word jumble
		for(int line = 0; line < noOfLines; line++)
		{
			System.out.printf("%d) %s [%s] clue: %s%n",line+1, blank, scrambledWords.get(line), definitions.get(line));
		}
		
		//Print out the answer key
		for(int line = 0; line < noOfLines; line++)
			System.out.printf("%d) %s%n",line+1, words.get(line));
		
	}

}
