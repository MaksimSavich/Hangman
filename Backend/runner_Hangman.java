package Backend;
import java.io.*;
import java.util.*;



class runner_Hangman{
  public static void main (String str[]) throws IOException {
    ArrayList<String> techList = new ArrayList<String>();
    HangmanGame game;
    Scanner scan = new Scanner(System.in);
    
    HangmanGame.displayGameTitle();

    System.out.println("\nPress \"ENTER\" to begin the game!");
    String beginPhrase = scan.nextLine();  
    String charInput = new String();

      while(beginPhrase.equals("")){
        game = new HangmanGame(techList);
        EventHandler.refreshConsole(game);

        while(game.getMistakes() < 8 && !game.checkSuccess()){
          System.out.println("Input only a single character:");
          charInput = scan.nextLine();

          charInput = EventHandler.onlyLettersCheck(game, charInput, scan);
          charInput = EventHandler.alreadyUsedLetter(game, charInput, scan);
          game.charCheck(charInput);
          
          EventHandler.refreshConsole(game);

        }

        EventHandler.refreshConsole(game);
        if(game.checkSuccess()){
          System.out.println("\nYou Won! You found the word: " + game.getWordToGuess() + ".");
        } else {
          System.out.println("\nYou Lost! The word you were searching for was: " + game.getWordToGuess() + ".");
        }
        
        System.out.println("\nWould you like to play again? [Press ENTER to continue | Type \"QUIT\" to end the game]");
        
        String contPhrase = scan.nextLine();
        if(contPhrase.equals("QUIT")){
          beginPhrase = contPhrase;
        }
        game.endGame();
    }
    scan.close();
  }
}