import java.io.*;
public abstract class Product{
   protected int productID, productCost, productValue;
   protected String productName;
   
   public Product(int pID, String pName, int pCost, int pValue){
      productID = pID;
      productName = pName;
      productCost = pCost;
      productValue = pValue;
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