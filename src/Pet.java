public abstract class Pet{
   protected int petID, energy, hygiene, hunger, mood;
   protected String nickName;
   
   //Constructor for new Pet
   public Pet(int ID, String name){
      petID = ID;
      nickName = name;
      energy = 100;
      hygiene = 100;
      hunger = 100;
      mood = 100;
   }
   
   //Constructor for existing Pet
   public Pet(int ID, String name, int pEnergy, int pHygiene, int pHunger, int pMood){
      petID = ID;
      nickName = name;
      energy = pEnergy;
      hygiene = pHygiene;
      hunger = pHunger;
      mood = pMood;
   }
   
   public String toString(){
      return "Pet Info: \n" + "Pet ID: " + petID + "\nNickname: " + nickName + "\nEnergy: " + energy + "\nHygiene: " + hygiene + "\nHunger: " + hunger + "\nMood: " + mood;
   }
   
   //Accessor
   public int getPetID(){
      return petID;
   }
   
   public String getName(){
      return nickName;
   }
   
   public int getEnergy(){
      return energy;
   }
   
   public int getHygiene(){
      return hygiene;
   }
   
   public int getHunger(){
      return hunger;
   }
   
   public int getMood(){
      return mood;
   }
   
   
   //Mutator
   public void setEnergy(int value){
      if(value > 100){
         energy = 100;
      }
      else if(value < 0) energy = 0;
      else{
         energy = value;
      }
   }
   public void setHygiene(int value){
      if(value > 100){
         hygiene = 100;
      }
      else if(value < 0) hygiene = 0;
      else{
         hygiene = value;
      }
   }
   public void setHunger(int value){
      if(value > 100){
         hunger = 100;
      }
      else if(value < 0) hunger = 0;
      else{
         hunger = value;
      }
   }
   public void setMood(int value){
      if(value > 100){
         mood = 100;
      }
      else if(value < 0) mood = 0;
      else{
         mood = value;
      }
   }
   public void nameChange(String newName){
      nickName = newName;
   }
   
   public int compareTo(Pet other){
      return getPetID() - other.getPetID();
   }
   
   public abstract String getNormGIF();
   public abstract String getEatGIF();
   public abstract String getBathGIF();
   public abstract String getSleepGIF();
   
}