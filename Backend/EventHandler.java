package Backend;
import java.util.Scanner;

public class EventHandler {
    
    public static void refreshConsole(HangmanGame game){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        HangmanGame.displayGameTitle();
        game.drawing();
        game.displayCharSpaces();
    }
    public static String onlyLettersCheck(HangmanGame game, String charInput, Scanner scan){

        while(!charInput.matches("[A-Za-z]")){
            EventHandler.refreshConsole(game);

            if(charInput.length() != 1){
                System.out.println("Only input one letter!");
            }else{
                System.out.println("Only input letters!");
            }
            charInput = scan.nextLine();
          }
          return charInput;
    }
    public static String alreadyUsedLetter(HangmanGame game, String charInput, Scanner scan){
        while(game.charCheck(charInput) == 0){
            EventHandler.refreshConsole(game);
            System.out.println("That letter has already been used");
            charInput = scan.nextLine();
            charInput = onlyLettersCheck(game, charInput, scan);
          }
          return charInput;
    }

}
