package Backend;
import java.io.*;
import java.util.*;



class runner_Hangman{
    public static void main (String str[]) throws IOException {
        ArrayList<String> techList = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);

        //displays the game title
        HangmanGame.displayGameTitle();

        System.out.println("\nPress \"ENTER\" to begin the game!");
        String beginPhrase = scan.nextLine();
        String charInput = new String();

        //constructs a HangmanGame object "game"
        HangmanGame game = null;

        //asks user if they want to use my wordlist or their own
        if(beginPhrase.equals("")){
            System.out.println("Type \"YES\" to use your own wordlist otherwise press \"ENTER\" to continue]");
            if (scan.nextLine().equals("YES")){
                System.out.println("Input raw .txt file link containing words split line by line");
                game = new HangmanGame(techList, scan.nextLine());
            } else{
                game = new HangmanGame(techList);
            }
        }

        //while loop that essentially runs the game using all the methods
        while(beginPhrase.equals("")){


            EventHandler.refreshConsole(game);

            //if success or the user ran out of mistakes then exit
            while(game.getMistakes() < 8 && !game.checkSuccess()){
                System.out.println("Input only a single character:");
                charInput = scan.nextLine();

                charInput = EventHandler.onlyLettersCheck(game, charInput, scan);
                charInput = EventHandler.alreadyUsedLetter(game, charInput, scan);
                game.charCheck(charInput);

                EventHandler.refreshConsole(game);

            }

            EventHandler.refreshConsole(game);

            //tell the user if the won or lost and what the word was
            if(game.checkSuccess()){
                System.out.println("\nYou Won! You found the word: " + game.getWordToGuess() + ".");
            } else {
                System.out.println("\nYou Lost! The word you were searching for was: " + game.getWordToGuess() + ".");
            }

            System.out.println("\nWould you like to play again? [Press ENTER to continue | Type \"QUIT\" to end the game]");

            //allow the user to quit the game
            game.endGame(scan.nextLine().equals("QUIT"));
        }
        scan.close();
    }
}