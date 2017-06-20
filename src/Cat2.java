public class Cat2 extends Pet{
   private String favoriteFood = "Shrimp";
   private String favoriteHygiene = "Cat Bodywash";
   private String favoriteVitamin = "Vitamin T";
   
   private String norm = "cat2_norm.gif";
   private String eat = "cat2_eat.gif";
   private String bath = "cat2_bath.gif";
   private String sleep = "cat2_sleep.gif";
   
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

   
   public Cat2 (int ID, String name){
      super(ID, name);
   }
   //Constructor for existing Pet
   public Cat2(int ID, String name, int pEnergy, int pHygiene, int pHunger, int pMood){
      super(ID, name, pEnergy, pHygiene, pHunger, pMood);
   }   
}