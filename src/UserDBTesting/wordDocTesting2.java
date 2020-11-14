/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserDBTesting;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author hassan
 */
public class wordDocTesting2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
       
       ArrayList<Integer> listOfProductID = Classes.makeOrder.getProductsIDList(16);
       ArrayList<String> productsNameList = new ArrayList<>();
       //getProductName
       for(int y = 0; y<listOfProductID.size();y++){
           productsNameList.add(Classes.makeOrder.getProductsNameList(listOfProductID.get(y)));
       }
       
       //ArrayList<Classes.productsPage> listOfProducts = products.productsFromProductTypeID(productType);
       ArrayList<Integer> listOfProductQty = Classes.makeOrder.getProductQuantity(16);
       
       ArrayList<Double> listOfProductPrice = Classes.makeOrder.getProductPrice(16);
       
       for(int i=0;i<listOfProductID.size();i++){
           System.out.print(productsNameList.get(i));
           System.out.print(" - "+listOfProductQty.get(i));
           System.out.println(" - "+ listOfProductPrice.get(i));
           
       }
       
//       String str = "ur mum";
//       System.out.println(str.length());
//       
//       while(str.length()<25){
//           str = str + " ";
//       }
//       System.out.println(str.length()); 

        for(int a=0; a<productsNameList.size(); a++){
            System.out.print(productsNameList.get(a).length());
            while(productsNameList.get(a).length()<25){
                //System.out.println(productsNameList.get(a));
                productsNameList.set(a, productsNameList.get(a) + " ");
            }
            System.out.println(" "+productsNameList.get(a).length());
                
        }
        
    }
    
}
