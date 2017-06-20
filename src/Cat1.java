public class Cat1 extends Pet{
   private String favoriteFood = "Fish";
   private String favoriteHygiene = "Cat Shampoo";
   private String favoriteVitamin = "Vitamin C";
   
   private String norm = "cat1_norm.gif";
   private String eat = "cat1_eat.gif";
   private String bath = "cat1_bath.gif";
   private String sleep = "cat1_sleep.gif";
   
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
   
   public static final int COST = 100;
   
   public Cat1 (int ID, String name){
      super(ID, name);
   }
   //Constructor for existing Pet
   public Cat1 (int ID, String name, int pEnergy, int pHygiene, int pHunger, int pMood){
      super(ID, name, pEnergy, pHygiene, pHunger, pMood);
   }   

}