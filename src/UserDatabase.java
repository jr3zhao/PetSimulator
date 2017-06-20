import java.io.*;
import java.util.*;

/*
   Class: UserDatabase
   Date: Dec. 31st
*/

public class UserDatabase{
   
   //fields
   ArrayList<User> userList = new ArrayList<User>(); // array that stores basic infomation of all 
   int numUser; // number of existing User
   User curUser; // User who is currently playing game
   
   // constructor
   public UserDatabase(){
      numUser = 0; // numUser is initialized to be 0
      loadUser(); // loads user information when a UserDatabase is initialized
   }
   
   // loadUser() - loads information of all users for a txt file
   public void loadUser(){
      try{
         BufferedReader in = new BufferedReader(new FileReader("Users.txt"));
         
         String line = in.readLine();
         
         while(line != null){ //exit when reaches the end
            int id = Integer.parseInt(line);
            String name = in.readLine();
            String password = in.readLine();
            int exp = Integer.parseInt(in.readLine());
            int gold = Integer.parseInt(in.readLine());
//             System.out.println(id);
//             System.out.println(name);
//             System.out.println(password);
//             System.out.println(exp);
//             System.out.println(gold);
            
            in.readLine(); // reads the empty line
            line = in.readLine();
            User user = new User(id, name, password, exp, gold); // creates a User object
            userList.add(user); // adds the User object to the array
            numUser++;
         }
         in.close();// close the input stream
      }
      catch(IOException iox){
         System.out.println("Error accessing data");// outputs error message when file doesn't exist
      }
   }
   
   // saveUser() - saves information of all user to the same txt file where the data is accessed
   public void saveUser(){
      try{
         sortByUserID(); // sort according to user ID before saving
         BufferedWriter out = new BufferedWriter(new FileWriter("Users.txt"));
         
         // save infomation for each user using a for loop
         for (int i = 0; i < numUser; i++){
            User user = userList.get(i);
            out.write(String.valueOf(user.getUserID()));
            out.newLine();
            out.write(user.getName());
            out.newLine();
            out.write(user.getPassword());
            out.newLine();
            out.write(String.valueOf(user.getExp()));
            out.newLine();
            out.write(String.valueOf(user.getGold()));
            out.newLine();
            out.newLine(); // writes a empty line between information of users
         }
         out.close(); // close the output stream
      }
      catch(IOException iox){
         System.out.println("Error saving file");
      }
   }
   
   // login(String, String) - sets curUser and returns 1 when login is successful
   //                       - returns 2 when user doesn't exist
   //                       - returns 0 when password is incorrect
   public int login(String name, String password){
      User user = searchUser(name);
      if(user == null){
         return 2;
      }else{
         if(user.checkLogin(password)){
            curUser = user;
            curUser.loadPet();
            return 1;
         }else{
            return 0;
         }
      }
   }
   
   // newUser(String, String) - creates a new User if the User name does not exist
   public boolean newUser(String name, String password){
      if(searchUser(name) != null){
         return false;
      }
      else{
         User user = new User(numUser, name, password);
         userList.add(user);
         numUser++;
         curUser = user;
         return true;
      }
   }
   
   // sortByGold() - sort the array of the users by gold owned in descending order
   public void sortByGold(){
      for (int i = 1; i < numUser; i++){
      
      User temp = userList.get(i);
      int j = i;
      
      while (j > 0 &&(userList.get(j-1).compareGold(temp) < 0)){
        userList.remove(j);
        userList.add(j,userList.get(j-1));
        j--;
      }
      userList.remove(j);
      userList.add(j,temp);
    }
   }
   
   // sortByName() - sort the array of the users by name in alphabetical order
   public void sortByName(){
      for (int i = 1; i < numUser; i++){
      
      User temp = userList.get(i);
      int j = i;
      
      while (j > 0 &&(userList.get(j-1).compareName(temp) > 0)){
        userList.remove(j);
        userList.add(j,userList.get(j-1));
        j--;
      }
      userList.remove(j);
      userList.add(j,temp);
    }
   }
   
   // sortByLevel() - sort the array of the users by level in descending order
   public void sortByLevel(){
      for (int i = 1; i < numUser; i++){
      
      User temp = userList.get(i);
      int j = i;
      
      while (j > 0 &&(userList.get(j-1).compareLevel(temp) < 0)){
        userList.remove(j);
        userList.add(j,userList.get(j-1));
        j--;
      }
      userList.remove(j);
      userList.add(j,temp);
    }
   }
   
   // sortByUserID() - sort the array of the users by id in ascending order
   public void sortByUserID(){
      for (int i = 1; i < numUser; i++){
      
      User temp = userList.get(i);
      int j = i;
      
      while (j > 0 &&(userList.get(j-1).compareID(temp) > 0)){
        userList.remove(j);
        userList.add(j,userList.get(j-1));
        j--;
      }
      userList.remove(j);
      userList.add(j,temp);
    }
   }
   
   // searchUser(String) - search the User using name, and returns the User object found
   //                    - returns null if the name does not exist

   private User searchUser(String name){
      
      boolean found = false;
      
      for(int i = 0; i < numUser && !found; i++){
         if(name.equals(userList.get(i).getName())){
            found = true;
            return userList.get(i);
         }
      }
      
      return null;
   }
   
   public void printALlInfo(){
      Scanner sc = new Scanner(System.in);
                  System.out.println("1. Sort By Level   2. Sort By Gold   3. Sort By Name");
                  System.out.print("Enter choice: ");
                  int choice = sc.nextInt();
                  switch(choice){
                     case 1: 
                        sortByLevel();
                        break;
                     case 2:
                        sortByGold();
                        break;
                     case 3:
                        sortByName();
                        break;
                     default: 
                        System.out.println("Invalid Input");
                  }
      for (User user:userList){
         System.out.println(user+ "\n");
      }
   }
}