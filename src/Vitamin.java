public class Vitamin extends Product{
   
   public Vitamin(int pID, String pName, int pCost, int pValue){
     super(pID, pName, pCost, pValue);
   }
   
   //Accessor
   public int getProductID(){
      return productID;
   }
   
   public String getProductName(){
      return productName;
   }
   
   public int getProductCost(){
      return productCost;
   }
   
   public int getProductValue(){
      return productValue;
   }
   
}