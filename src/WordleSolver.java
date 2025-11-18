package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This is a program that solves Wordle. 
 *
 * @author Austin Mueller
 *
 */
public final class WordleSolver {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordleSolver() {
    }

    /**
     * Static wordList variable.
     */
    private static ArrayList<String> wordList = new ArrayList<>();

    /**
     * Static int variable for the required length of each word guess.
     */
    private static final int WORD_GUESS_LENGTH = 5;

    /**
     * This method creates the wordList for the program to use later.
     *
     * @param br
     */
    public static void wordListCreator(BufferedReader br) {

        String line;
        try {
            line = br.readLine(); //Reads each line from the file
        } catch (IOException e) {
            System.err.print("Error reading line.");
            try {
                br.close();
            } catch (IOException e1) {
                System.err.println("Error closing reader.");
            }
            return;
        }
        while (line != null) {
            wordList.add(line); //Adds each word to the ArrayList
            try {
                line = br.readLine(); //Gets the next line
            } catch (IOException e) {
                System.err.print("Error reading line.");
            }

        }
    }

    /**
     * This takes a user input for the word guess, yellow characters, and grey
     * characters.
     *
     * @param ineligibleChars
     * @param unknownCharsPos
     * @param input
     * @return the word inputed as a guess
     */
    public static String guessAnalyzer(Set<Character> ineligibleChars,
            Map<Integer, Character> unknownCharsPos, Scanner input) {

        System.out.print(
                "Type your guess (replace unknown letters with \"-\"): ");
        String wordGuess = input.next(); //The word that the user guessed
        assert wordGuess.length() == WORD_GUESS_LENGTH : "Word guess must be 5 "
                + " characters long.";
        System.out.print(
                "Type all yellow letters (type \"-\" if there are none)? ");
        String unknown = input.next(); //String of yellow characters
        if (unknown.compareTo("-") != 0) {
            for (int k = 0; k < unknown.length(); k++) {
                System.out.print("Type the position of \"" + unknown.charAt(k)
                        + "\" (start with 0): ");
                int pos = input.nextInt(); //Position of the yellow character
                unknownCharsPos.put(pos, unknown.charAt(k)); //Adds yellow character
                //and its position in the guess to the map
            }
        }
        System.out.print(
                "Type all grey letters (type \"-\" if there are none): ");
        String ineligible = input.next(); //String of grey characters
        for (int n = 0; n < ineligible.length(); n++) {
            if (!unknownCharsPos.containsValue(ineligible.charAt(n))) { //If grey
                //character wasn't already inputed as yellow
                ineligibleChars.add(ineligible.charAt(n)); //Adds grey characters to set
            }
        }
        return wordGuess; //Returns the guessed word inputed at the beginning
    }

    /**
     * This method takes the word list and filters out words that cannot be
     * possible solutions.
     *
     * @param wordGuess
     * @param ineligibleChars
     * @param unknownCharsPos
     */
    public static void wordListSort(String wordGuess,
            Set<Character> ineligibleChars,
            Map<Integer, Character> unknownCharsPos) {
        int len = wordList.size(); //Size of the wordList ArrayList
        for (int u = 0; u < len; u++) {
            String word = wordList.remove(0); //Removes word from top of the list
            boolean wordEligible = true; //Boolean for if the word is a possible answer
            for (int i = 0; i < WORD_GUESS_LENGTH; i++) {
                char c = word.charAt(i); //Character at i for the word from the list
                if (unknownCharsPos.containsKey(i)) { //If any yellow characters were
                    //inputed as being at position i
                    if (word.indexOf(unknownCharsPos.get(i)) == -1
                            || (unknownCharsPos.get(i) == c)
                            || ineligibleChars.contains(c)
                            || (wordGuess.charAt(i) != c
                                    && wordGuess.charAt(i) != '-')) { //Conditions
                        //that eliminate the word as a possible answer
                        wordEligible = false;
                    }
                } else { //If any yellow characters were not inputed as being at
                    //position i
                    if ((ineligibleChars.contains(c)
                            || (wordGuess.charAt(i) != c
                                    && wordGuess.charAt(i) != '-'))) {
                        wordEligible = false;
                    }
                }
            }
            if (wordEligible) { //If the word is still a possible solution
                wordList.add(word); //Adds the word back to the list
            }
        }
        Collections.sort(wordList); //Finally, sorts the wordList alphabetically
    }

    /**
     * This method takes the now sorted wordList and prints it to a file.
     */
    public static void printer() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("text/possibleWords.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("List of Possible Words");
            printWriter.println("-------------------------");
            for (int w = 0; w < wordList.size(); w++) { //Prints out any remaining words
                printWriter.println((w + 1) + ". " + wordList.get(w));
            }
            printWriter.close();
        } catch (IOException e) {
            System.err.print("Error writing to file.");
        }
        System.out.println();
	    try (BufferedReader br = new BufferedReader(new FileReader("text/possibleWords.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); //Scanner input
        File file = new File("text/wordList.txt"); //File that is the list of
        //five letter words
        Set<Character> ineligibleChars = new HashSet<>(); //Set of grey character
        Map<Integer, Character> unknownCharsPos = new HashMap<>(); //Map for the
        //unknown characters and their positions
        BufferedReader br; //Buffered reader of the file
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.print("Error opening file.");
            input.close();
            return;
        }
        wordListCreator(br); //Creates the wordList
        String response = "y";
        while (response.compareTo("y") == 0) {
            String wordGuess = guessAnalyzer(ineligibleChars, unknownCharsPos,
                    input);
            wordListSort(wordGuess, ineligibleChars, unknownCharsPos); //Filters out
            //any words that are not possible solutions
            printer(); //Prints the now filtered wordList
            System.out.print("Would you like to try another word (y/n)? ");
            response = input.next(); //If the user wants to still use the program
        }

        input.close(); //Closes scanner input
        try {
            br.close(); //Closes the buffered reader
        } catch (IOException e) {
            System.err.println("Error closing reader.");
        }
    }
}
