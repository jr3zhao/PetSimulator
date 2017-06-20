public class Cat3 extends Pet{
   private String favoriteFood = "Bones";
   private String favoriteHygiene = "Apple Facewash";
   private String favoriteVitamin = "Vitamin B";
   
   private String norm = "cat3_norm.gif";
   private String eat = "cat3_eat.gif";
   private String bath = "cat3_bath.gif";
   private String sleep = "cat3_sleep.gif";
   
   public String getNormGIF(){
      return norm;
   }
   public String getEatGIF(){
      return eat;
   }
   public String getBathGIF(){
      return bath;
   }
   public String getSleepGIF(){
      return sleep;
   }

   
   public Cat3 (int ID, String name){
      super(ID, name);
   }
   //Constructor for existing Pet
   public Cat3(int ID, String name, int pEnergy, int pHygiene, int pHunger, int pMood){
      super(ID, name, pEnergy, pHygiene, pHunger, pMood);
   }      
}