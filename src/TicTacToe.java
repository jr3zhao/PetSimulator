import java.util.*;

public class TicTacToe{
  
  private char[][] board; 
  private char currentPlayerMark;
  int playerWon;
  private String name = "TicTacToe";
  private final int GOLDREWARDED = 300;
  
  Scanner sc=new Scanner(System.in);
  
  public TicTacToe() {
    board = new char[3][3];
    currentPlayerMark = 'x';
    setBoard();
  }
  
  
  // Set the board empty.
  public void setBoard() {
    
    // Loop through rows
    for (int i = 0; i < 3; i++) {
      
      // Loop through columns
      for (int j = 0; j < 3; j++) {
        board[i][j] = ' ';
      }
    }
  }
  
  
  // Print the board
  public void printBoard() {
    System.out.println("-------------");
    
    for (int i = 0; i < 3; i++) {
      System.out.print("| ");
      for (int j = 0; j < 3; j++) {
        System.out.print(board[i][j] + " | ");
      }
      System.out.println();
      System.out.println("-------------");
    }
  }
  
  
  // Check if the board is full.
  public boolean isBoardFull() {
    boolean isFull = true;
    
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          isFull = false;
        }
      }
    }
    
    return isFull;
  }
  
// Check if the marks on these three places are equal (and not empty).
  private boolean checkRowCol(char c1, char c2, char c3) {
    return ((c1 != ' ') && (c1 == c2) && (c2 == c3));
  }
  
  // Check if any of the players has won
  public boolean checkForWin() {
    boolean isWon=false;
    
    
    for (int i = 0; i < 3; i++) {
      if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
        // Loop through rows to check if there is a winner
        isWon=true;
      }else if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
        // Loop through columns to check if there is a winner 
        isWon=true;
      }
    }
    
    // Check the two diagonals to see if there is a winner
    if((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true)){
      isWon=true;
    }
    return isWon;
  }
  
  // switch the turn and change the current player's mark.
  public void switchTurn() {
    if (currentPlayerMark == 'x') {
      currentPlayerMark = 'o';
    }
    else {
      currentPlayerMark = 'x';
    }
  }
  
  
  // Places a mark at the spot with specified row and col indice, and current player mark.
  public boolean placeMark(int row, int col) {
    
    // Loop through to check if the player's decision is valid.
    if ((row >= 0) && (row < 3)) {
      if ((col >= 0) && (col < 3)) {
        if (board[row][col] == ' ') {
          //If the move is valid, place a player mark and return true.
          board[row][col] = currentPlayerMark;
          return true;
        }
      }
    }
    //If not valid, return false
    return false;
  }
  
  //declare the winner
  public void declareWinner(){
    if (currentPlayerMark=='x'){
      System.out.println("Congratulations! You won the game!");
    }
    else{
      System.out.println("Sorry you just lost the game. Try again.");
    }
  }
  
  //play 
  public boolean play(){
    printBoard();
    
    int row,col;
    
    while(!isBoardFull() || ! checkForWin()){
      System.out.println("Row & Column to place your mark: ");
      System.out.println("Enter row(0-2): ");
      row=sc.nextInt();
      System.out.println("Enter col(0-2): ");
      col=sc.nextInt();
      if(placeMark(row, col)){
        printBoard();
        if (checkForWin()){
          declareWinner();
          if (currentPlayerMark=='x'){
            return true;
          }
        }
        System.out.println("Switch");
        switchTurn();
      }else{
        System.out.println("Invalid decision");
      }
    }
    
    return false;
  }
}