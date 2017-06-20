import java.io.*;
public class ProductDatabase{
   private int numProduct;
   private Product list[];
   
   public void loadProduct(){
      String tempName, type;
      int tempID, tempCost, tempValue;
      try{
         BufferedReader in = new BufferedReader(new FileReader("productData.txt"));
         numProduct = Integer.parseInt(in.readLine());
         list = new Product[numProduct];
         for (int i = 0; i < numProduct; i ++){
            
            //Read in stats
            in.readLine();
            type = in.readLine();
            tempID = Integer.parseInt(in.readLine());
            tempName = in.readLine();
            tempCost = Integer.parseInt(in.readLine());
            tempValue = Integer.parseInt(in.readLine());
            
            //Check Type
            if (type.equals("Food")){
               list[i] = new Food(tempID, tempName, tempCost, tempValue);
            } 
            else if (type.equals("Hygienic")){
               list[i] = new Hygienic(tempID, tempName, tempCost, tempValue);
            } 
            else {
               list[i] = new Vitamin(tempID, tempName, tempCost, tempValue);
            }
            
            //
            
         }//end of for loop
         in.close();   
      }
      catch (IOException iox){
         System.out.println("Error Loading File");
      }
   
   }

   public void listProductType(String type){
      if (type.equals("Food")){
         for (int i = 0; i < numProduct; i ++){
            if (list[i] instanceof Food){
               System.out.println(list[i]);
            }
         }
      } 
      else if (type.equals("Hygienic")){
         for (int i = 0; i < numProduct; i ++){
            if (list[i] instanceof Hygienic){
               System.out.println(list[i]);
            }
         }      } 
      else {
         for (int i = 0; i < numProduct; i ++){
            if (list[i] instanceof Vitamin){
               System.out.println(list[i]);
            }
         }      
      }
   }

}