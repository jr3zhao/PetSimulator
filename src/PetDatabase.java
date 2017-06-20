import java.io.*;
public class PetDatabase{
   private int numPet;
   private Pet list[];
   private final int MAX = 3;
   
   public PetDatabase(int userNum){
      list = new Pet[MAX];
      loadPet(userNum);
      
   }
   
   public int getNumPet(){
      return numPet;
   }
   
   public Pet[] getPetList(){
      return list;
   }
   
   public boolean loadPet(int userNum){
      String tempName, type;
      int tempID, tempEnergy, tempHygiene, tempHunger, tempMood;
      try{
         BufferedReader in = new BufferedReader(new FileReader("petData" + userNum + ".txt"));
      
      numPet = Integer.parseInt(in.readLine());
      in.readLine();
      
      for(int i = 0;i<numPet;i++){
            
            //Read in stats
      
         type = in.readLine();
         tempID = Integer.parseInt(in.readLine());
         tempName = in.readLine();
         tempEnergy = Integer.parseInt(in.readLine());
         tempHygiene = Integer.parseInt(in.readLine());
         tempHunger = Integer.parseInt(in.readLine());
         tempMood = Integer.parseInt(in.readLine());
            
            //Check Type
         if (type.equals("Cat1")){
            list[i] = new Cat1(tempID, tempName, tempEnergy, tempHygiene, tempHunger, tempMood);
         } 
         else if (type.equals("Cat2")){
            list[i] = new Cat2(tempID, tempName, tempEnergy, tempHygiene, tempHunger, tempMood);
         } 
         else {
            list[i] = new Cat3(tempID, tempName, tempEnergy, tempHygiene, tempHunger, tempMood);
         }
            //
            
         //end of for loop
         }
         
         in.close();   
         
       return true;  
      }
      catch (IOException iox){
         System.out.println("Error Loading File");
         return false;
      }
   
   }
   
   public void savePet(int userNum){
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter("petData" + userNum + ".txt", false));
         
         out.write(String.valueOf(numPet));
         out.newLine();
         out.newLine();
         
         for(int i = 0;i<numPet;i++){
      
            //Determine the type
         if (list[i] instanceof Cat1){
            out.write("Cat1");
            out.newLine();
         } 
         else if (list[i] instanceof Cat2){
            out.write("Cat2");
            out.newLine();
         } 
         else {
            out.write("Cat3");
            out.newLine();
         }
          
            
            //Save all stats
         out.write(String.valueOf(list[i].getPetID()));
         out.newLine();
         out.write(list[i].getName());
         out.newLine();
         out.write(String.valueOf(list[i].getEnergy()));
         out.newLine();
         out.write(String.valueOf(list[i].getHygiene()));
         out.newLine();
         out.write(String.valueOf(list[i].getHunger()));
         out.newLine();
         out.write(String.valueOf(list[i].getMood()));
         out.newLine();
         
      }
         out.close();
         
         
      }
      catch(IOException iox){
         System.out.println("Error Writing File");
      }
   }
   
   public void sortByPetID(){
      //insertion sort
      Pet tempPet;
      for (int i = 1; i < numPet; i++){
         for (int j = i; j > 0; j--){
            if (list[i].compareTo(list[i-1]) < 0){
               tempPet = list[i];
               list[i] = list[i-1];
               list[i-1] = tempPet;
            }
         }
      }
   }
   
   public Pet findPet(int id){
      return list[id];
   }
   
   public boolean buyCat1(String name){

      if(numPet < MAX){
         list[numPet] = new Cat1(numPet, name);
         numPet++;
         return true;
      }
      else{
         System.out.println("MAX PET");
         return false;
      }
   }
   
   public boolean buyCat2(String name){

      if(numPet < MAX){
         list[numPet] = new Cat2(numPet, name);
         numPet++;
         return true;
      }
      else{
         System.out.println("MAX PET");
         return false;
      }
   }
   public boolean buyCat3(String name){

      if(numPet < MAX){
         list[numPet] = new Cat3(numPet, name);
         numPet++;
         return true;
      }
      else{
         System.out.println("MAX PET");
         return false;
      }
   }

   public boolean okToBuy(int n){
      if((list[0] instanceof Cat1 || list[1] instanceof Cat1 || list[2] instanceof Cat1) && n == 0) return false;
      else if((list[0] instanceof Cat2 || list[1] instanceof Cat2 || list[2] instanceof Cat2) && n == 1) return false;
      else if((list[0] instanceof Cat3 || list[1] instanceof Cat3 || list[2] instanceof Cat3) && n == 2) return false;
      else return true;
   }
   
}