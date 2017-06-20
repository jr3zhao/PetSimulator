/*
   Class: User
   Date: Dec. 28th
*/

import java.util.*;

public class User{
   
   // fields
   private int userID;
   private String name;
   private String password;
   private int level;
   private int exp;
   private int gold;
   PetDatabase pDatabase;
   private Pet curPet;
   
   // constructors
   
   // contructing an User object of a new user given id, name, and password
   // the exp, level, and gold is set to default
   public User(int id, String n, String p){
      userID = id;
      name = n;
      password = p;
      level = 1;
      exp = 0;
      gold = 500;
      
   }
   
   //contructing an User object of a existing user
   // all fields except level are passed by the parameter
   // level is updated by updateLevel() method
   public User(int id, String n, String p, int e, int g){
      userID = id;
      name = n;
      password = p;
      updateLevel();
      exp = e;
      gold = g;

   }
   
   // accessor and mutator
   public int getUserID(){
      return userID;
   }
   public void setUserID(int id){
      userID = id;
   }
   public String getName(){
      return name;
   }
   public void setName(String n){
      name = n;
   }
   public String getPassword(){
      return password;
   }
   public void setPassword(String p){
      password = p;
   }
   public int getExp(){
      return exp;
   }
   public void setExp(int e){
      exp = e;
   }
   
   // level is updated before it's accessed
   public int getLevel(){
      updateLevel();
      return level;
   }
   public void setLevel(int l){
      level = l;
   }
   public int getGold(){
      return gold;
   }
   
   // set gold to the a value greater than or equal to 0 and returns true
   // when the value is < 0, returns false
   public boolean setGold(int g){
      if(g<0) return false;
      gold = g;
      return true;
   }
   public Pet getCurPet(){
      return curPet;
   }
   public void setCurPet(Pet pet){
      curPet = pet;
      System.out.println(curPet);
   }
   
   // methods
   
   // checkLogin(String) - checks whether the entered password matches the User's password and returns a boolean
   public boolean checkLogin(String p){
      if(password.equals(p)){
         return true;
      }else{
         return false;
      }
   }
   
   // loadPet() - loads information of the user's pet from it's txt file
   public void loadPet(){
      pDatabase = new PetDatabase(userID);
      
      //selectPet();
      
   }
   
   public void selectPet(){
      /*Scanner sc = new Scanner(System.in);
      int id = sc.nextInt();
      setCurPet(pDatabase.findPet(id));*/
      curPet = pDatabase.findPet(0);//id as of now.
   }
   
   // compare different properties of user (ID, Name, Level, and Gold)
   // returns int values
   public int compareID(User other){
      return userID - other.userID;
   }
   public int compareName(User other){
      return name.toLowerCase().compareTo(other.name.toLowerCase());
   }
   public int compareLevel(User other){
      updateLevel();
      other.updateLevel();
      return level - other.level;
   }
   public int compareGold(User other){
      return gold - other.gold;
   }
   
   // updates the level of the user based on the exp
   public void updateLevel(){
      level = updateLevel(exp, 1);
   }
   
   // returns the level of the user
   private int updateLevel(int e, int l){
      if(e < 20){
         return l;
      }else{
         return updateLevel((e - l*20), l+1);
      }
   }
   
   public String toString(){
      return "User: " + name + "\nLevel: " + level + "\nGold: " + gold;
   }
}