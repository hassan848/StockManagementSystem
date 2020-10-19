/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserDBTesting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hassan
 */
public class editProductTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String Pname = "Samsung Galaxy s10+ case";
        int Rstock = 10;
        double price = 29.99;
        String description = "A carbon fibre case for the new Samsung Galaxy S10+";
        int productTypeID = 1;
        
        String imagePath = "src\\Images\\samsung_case.png";
        Path myPath = Paths.get(imagePath);
        byte[] imageP;
        
        try {
            imageP = Files.readAllBytes(myPath);
            
            Classes.productsPage editProduct = new Classes.productsPage(1, Pname, Rstock, price, description, productTypeID, imageP);
            Classes.productsPage.editProduct(editProduct, true);
            
        } catch (IOException ex) {
            Logger.getLogger(editProductTesting.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
