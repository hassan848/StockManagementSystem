/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserDBTesting;

import Inventory_Management_Java_Application.MyConnectionDB;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author hassan
 */
public class insertProductTesting {

    /**
     * @param args the command line arguments
     */
    public insertProductTesting(){}
 
    
    public byte[] getMyImage(){
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT `productImage` FROM `products` WHERE `productID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, 1);
            rs = st.executeQuery();
            
            Image myPic;
            while(rs.next()){
                System.out.println(rs.getBytes("productImage"));
                System.out.println("ok");
                myPic = new ImageIcon(rs.getBytes("productImage")).getImage();
                System.out.println(myPic);
                return rs.getBytes("productImage");
                
                //ImageIcon pic = new ImageIcon(new ImageIcon(rs.getBytes("productImage"))).getImage()
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(insertProductTesting.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] pic = null;
        return pic;
      
    }
    
    public static void main(String[] args) {
        String Pname = "iphone case";
        int Rstock = 50;
        double price = 8.99;
        String description = "A brand new case for the brand new Iphone";
        int productTypeID = 1;
        
        String imagePath = "src\\Images\\phoneCase.png";
        Path myPath = Paths.get(imagePath);
        byte[] imageP;
        byte[] imageNull = null;
        try {
            imageP = Files.readAllBytes(myPath);
            System.out.println(imageP);
            
            Classes.productsPage newProduct = new Classes.productsPage(1, Pname, Rstock, price, description, productTypeID, imageNull);
            Classes.productsPage.insertProduct(newProduct);
            
            //Classes.productsPage.deleteProduct(2);
            
        } catch (IOException ex) {
            Logger.getLogger(insertProductTesting.class.getName()).log(Level.SEVERE, null, ex);
        }
        //insertProductTesting testimg = new insertProductTesting();
        //testimg.getImage();
    }
    
}
