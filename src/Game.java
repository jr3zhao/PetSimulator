import java.util.*;

public final class Game{
   
   private static final int NUMGAME = 1;
   static RockPaperScissors rps = new RockPaperScissors();
   static TicTacToe t = new TicTacToe();
   static int gameChoice;
   static boolean result = false;
   static Scanner sc = new Scanner(System.in);
   
   public static boolean playGame(){
      System.out.print("Enter a game choice (1 for Rock Paper Scissors, 2 for Tic Tac Toe): ");
      gameChoice = sc.nextInt();
      result = false;

      if (gameChoice == 1){
       rps = new RockPaperScissors();
       t = new TicTacToe();
         for (int i = 0; i < NUMGAME; i ++){
            if (!rps.play()){
               System.out.println("Invalid input please try again!");
               i--;
            } 
            else {
               rps.outputScore();
            }
         }
         //declare the winner
         if(rps.declareWinner()) result = true;
      } 
      else if (gameChoice == 2){
         result=t.play();
      }
      else{
         System.out.println("Invalid input");
      }
   
      return result;
      
   }
}
  
