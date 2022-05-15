package Backend;
import java.io.*;
import java.util.*;



class runner_Hangman{
  public static void main (String str[]) throws IOException {
    ArrayList<String> techList = new ArrayList<String>();
    Scanner scan = new Scanner(System.in);
    
    HangmanGame.displayGameTitle();

    System.out.println("\nPress \"ENTER\" to begin the game!");
    String beginPhrase = scan.nextLine();  
    String charInput = new String();
    HangmanGame game = new HangmanGame(techList);

    if(beginPhrase.equals("")){
      System.out.println("Type \"YES\" to use your own wordlist otherwise press \"ENTER\" to continue]");
      if (scan.nextLine().equals("YES")){
        System.out.println("Input raw .txt file link containing words split line by line");
        game = new HangmanGame(techList, scan.nextLine());
      } else{
        game = new HangmanGame(techList);
      }
    }
    

      while(beginPhrase.equals("")){


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
        
        game.endGame(scan.nextLine().equals("QUIT"));
    }
    scan.close();
  }
}