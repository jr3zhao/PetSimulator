import java.util.*;
public class RockPaperScissors {
  private static int compMove;
  private static int playerScore = 0;
  private static int compScore = 0;
  private final int GOLDREWARDED = 200;
  private String name;
  //Constructor
  public RockPaperScissors(){
  }
  
  //Accessor
  public int getGoldRewarded(){
   return GOLDREWARDED;
  }
  
  //Check valid input
  public boolean validMove(int input){
    if (input < 1 || input > 3){
      return false;
    } else {
      return true;
    }
  }
  
  public boolean play(){
    playerScore = compScore = 0;
    Scanner sc = new Scanner (System.in);
    System.out.println("Rock = 1 Paper = 2 Scissors = 3");
    int input = sc.nextInt();
    if (validMove(input)){
      compMove = (int)(Math.random()*3)+1;
      //Rock = 1 Paper = 2 Scissors = 3
      if (input == 1 && compMove == 3){
        playerScore ++;
        return true;
      } else if (input == 3 && compMove == 1){
        compScore ++;
        return true;
      } else if (compMove - input == 1){
        compScore ++;
        return true;
      } else if (input - compMove == 1){
        playerScore ++;
        return true;
      } else {
        System.out.println("Tie");
        return true;
      }
    } else {
      return false;
    }
  }
  
  public boolean declareWinner(){
    if (compScore > playerScore){
      System.out.println("AI win");
      return false;
    } else if (compScore < playerScore){
      System.out.println("You win");
      return true;
    } else {
      System.out.println("Tie");
      return false;
    }
  }
  
  public void outputScore(){
    System.out.println("CompScore: " + compScore);
    System.out.println("PlayerScore: " + playerScore);
  }
}