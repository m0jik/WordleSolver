# Wordle Solver README

This is the readme file for my Wordle solver project. It's just a fun little side project of mine!

## Table of Contents

- [Getting Started](#getting-started)
    - [Requirements](#requirements)
    - [Quick Start](#quick-start)
- [Running the Program](#running-the-program)
- [Using the Terminal](#using-the-terminal)
    - [First Step](#first-step)
    - [Typing a Guess](#typing-a-guess)
    - [Handling Incorrectly Placed Letters](#handling-incorrectly-placed-letters)
    - [Handling Incorrect Letters](#handling-incorrect-letters)
    - [Choosing Your Next Guess](#choosing-your-next-guess)
- [An Example](#an-example)
    - [Attempt One](#attempt-one)
    - [Attempt Two](#attempt-two)
    - [Attempt Three](#attempt-three)
    - [Attempt Four](#attempt-four)
    - [Attempt Five](#attempt-five)
- [Troubleshooting](#troubleshooting)

## Getting Started

Please ensure that the requirements are met on your machine before running.

### Requirements

- VS Code (or some other IDE with a terminal for output)
    - <https://code.visualstudio.com/download>
- Java 21
    - <https://jdk.java.net/21/>
- Appropriate extensions/compilers installed on your IDE
- Wordle
    - <https://www.nytimes.com/games/wordle/index.html>
    - <https://wordlegame.org/>

### Quick Start

1. Ensure that Java 21 and some IDE with a terminal (I used VS Code but Eclipse could work, too) installed on your system.
2. Ensure that the program can compile (it's just a normal Java program that uses standard Java tools, so this shouldn't be an issue).
3. From the `text` directory, open the `possibleWords.txt` file so that you may view possible words as you run the program.

Check the notes below on how to properly use the program.

## Running the Program

To run the program, simply have the `WordleSolver.java` file open from the `src` directory and click `run` on whatever IDE you're using.

## Using the Terminal

Each input can simply be typed into the terminal followed by a press of the enter key. Since this program relies on the terminal, this portion of the guide will consist of the bulk of the program's use.

### First Step

The program will ask you if you'd like to try a word. Simply input `y` into the terminal in order to continue. In an active game of Wordle, input a guess.

### Typing a Guess

Each guess input **must** have exactly five characters. If a character in your guess is correct (i.e. the letter is green), that letter will be included in your terminal input. Otherwise, a `-` will be inputted as a character. After your five character input is typed into the terminal, press enter to continue to the next question.

### Handling Incorrectly Placed Letters

This part of the guide will handle letters that are in the correct word, but are not in the correct spot in your guess.

#### Part One

If your guess in your active Wordle game has letters that are in the word, but not in the correct spot (i.e. the letter is yellow), you will type each yellow letter as if they all made up one word (regardless of length) when prompted to `Type all yellow letters`. If you do not have any incorrectly placed letters, simply input `-`. After your input is typed out into the terminal, press enter.

#### Part Two

After all your yellow characters are inputted, the program will then ask for the location of each yellow character as seen in the guess on your active Wordle game (if applicable). You will asked to `Type the position of "[character]" (start with 0)`. You will then type that character's position in the form of an integer, starting with zero. For example, if you have a yellow `a` that is in the third position (i.e. directly in the middle), your input would then be `2`. Press enter. Continue with this for each character.

#### Tips

Include yellow letters from past guesses. This will help if the correct word for your game has repeat letters.
Include duplicate yellow letters. If you have a yellow `o` that is seen in both the second and fourth position, type them both into your input and be sure to note both of their positions when asked to input their separate positions.

### Handling Incorrect Letters

If you guess a letter and it isn't in the correct word (i.e. the letter is grey), simply type these letters as if they were one word (similarly to the yellow letters portion) when asked to `Type all grey letters` into the terminal. If there are no grey letters, type `-` and press enter.

#### Tip

Refer to the keyboard in your active Wordle game for this portion. You want to include **all** grey letters for your entire game, not just the ones found in whatever particular guess you are on.

### Choosing Your Next Guess

Now, go to the `possibleWords.txt` file mentioned earlier. You will now see a list of eligible words. Choose a word and make it your next guess in your active Wordle game. If that guess is wrong, input `y` into the terminal when asked `Would you like to try another word` and start again from the [Typing a Guess](#typing-a-guess) section.

## An Example

### Attempt One

Let's say `Coyly` is the correct word for the Wordle and the first guess you use in your active Wordle game is `Trade`.

1. When asked `Would you like to try another word`, you will input `y`.
2. When asked to `Type your guess`, you will input `-----`.
3. When asked to `Type all yellow letters`, you will input `-`.
4. When asked to `Type all grey letters`, you will input `ertad`.

#### Note

The order that letters are typed in for the yellow and grey letters section is not important, so long as all the letters are there.

### Attempt Two

Using the `possibleWords.txt` file as a reference, let's say your next guess is `flung`.

1. When asked `Would you like to try another word`, you will input `y`.
2. When asked to `Type your guess`, you will input `-----`.
3. When asked to `Type all yellow letters`, you will input `l`.
4. When asked to `Type the position of "l"`, you will input `1`.
5. When asked to `Type all grey letters`, you will input `ertadfung`.

### Attempt Three

Using the `possibleWords.txt` file as a reference, let's say your next guess is `skill`.

1. When asked `Would you like to try another word`, you will input `y`.
2. When asked to `Type your guess`, you will input `---l-`.
3. When asked to `Type all yellow letters`, you will input `l`.
4. When asked to `Type the position of "l"`, you will input `1`.
5. When asked to `Type all grey letters`, you will input `ertadfungski`.

### Attempt Four

Using the `possibleWords.txt` file as a reference, let's say your next guess is `lowly`.

1. When asked `Would you like to try another word`, you will input `y`.
2. When asked to `Type your guess`, you will input `-o-ly`.
3. When asked to `Type all yellow letters`, you will input `l`.
4. When asked to `Type the position of "l"`, you will input `1`.
5. When asked to `Type all grey letters`, you will input `ertadfungskiw`.

### Attempt Five

Using the `possibleWords.txt` file as a reference, let's say your next guess is `coyly`.

1. When asked `Would you like to try another word`, you will input `n`.
2. This will close the program, as you have won. Good job!

## Troubleshooting

- Inputting a guess into the terminal that isn't five characters long will cause the program to not work properly, so make sure your guesses are actually five characters long.
- Inputting a blank guess (i.e. a guess that is `null`) will also cause issues. Don't input empty guesses (input `-` if you have to).
- When asked to `Type the position of "[character]" (start with 0)`, inputting an integer that is not between 0 and 4, inclusive, will cause an error, so don't do that.
- If after running the program, `possibleWords.txt` is blank, try rerunning the program with the same inputs you made as before. If it is still blank, then ensure that your inputs correct, as incorrect inputs might lead the program to thinking that there is no correct word that could work with your inputs.