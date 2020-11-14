/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Inventory_Management_Java_Application.MyConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hassan
 */
public class productsPage {
    
    private String productName;
    private int productID;
    private double productPrice;
    private int remainingStock;
    private String productDescription;
    private int productTypeID;
    private byte[] productImage;
    
    public productsPage(){}
    
    public productsPage(int PID, String name, int stock, double price, String pDescription, int productTypeID, byte[] image){
        this.productName = name;
        this.productID = PID;
        this.productPrice = price;
        this.remainingStock = stock;
        this.productDescription = pDescription;
        this.productTypeID = productTypeID;
        this.productImage = image;
    }
    
    public String getName(){
        return this.productName;
    }
    
    public int getPID(){
        return this.productID;
    }
    
    public double getProductPrice(){
        return this.productPrice;
    }
    
    public int getStock(){
        return this.remainingStock;
    }
    
    public String getProductDescription(){
        return this.productDescription;
    }
    
    public int getTypeID(){
        return this.productTypeID;
    }
    
    public byte[] getImageDB(){
        return this.productImage;
    }
    
    public static String getProductType(int productTypeID){
        PreparedStatement st;
        ResultSet rs;
        String query = "SELECT `productType` FROM `producttypes` WHERE `productTypeID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, productTypeID);
            rs = st.executeQuery();
            
            while(rs.next()){
                //System.out.println(rs);
                return rs.getString("productType");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(productsPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    //check if productname is already in database
    public static boolean checkProduct(String productName){
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT `productName` FROM `products` WHERE `productName` = ?";
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, productName);
            rs = st.executeQuery();
            
            if(rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(productsPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
    public static void insertProduct(productsPage newProduct){
        PreparedStatement st;
        
        String query = "INSERT INTO `products`(`productName`, `remainingStock`, `productPrice`, `productDescription`, `productImage`, `productTypeID`) VALUES (?,?,?,?,?,?)";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, newProduct.productName);
            st.setInt(2, newProduct.remainingStock);
            st.setDouble(3, newProduct.productPrice);
            st.setString(4, newProduct.productDescription);
            st.setBytes(5, newProduct.productImage);
            st.setInt(6, newProduct.productTypeID);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(productsPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public static void deleteProduct(int productID){
        PreparedStatement st;
        String query = "DELETE FROM `products` WHERE `productID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, productID);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(productsPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    public static void editProduct(productsPage newProduct, boolean isImageNew){
        PreparedStatement st;
        String query;
        
        if(isImageNew){
            query = "UPDATE `products` SET `productName`=?,`remainingStock`=?,`productPrice`=?,`productDescription`=?,`productImage`=?,`productTypeID`=? WHERE `productID`=?";
            
            try {
                st = MyConnectionDB.getConnection().prepareStatement(query);
                
                st.setString(1, newProduct.productName);
                st.setInt(2, newProduct.remainingStock);
                st.setDouble(3, newProduct.productPrice);
                st.setString(4, newProduct.productDescription);
                st.setBytes(5, newProduct.productImage);
                st.setInt(6, newProduct.productTypeID);
                
                st.setInt(7, newProduct.productID);
                
                st.executeUpdate();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(productsPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            query = "UPDATE `products` SET `productName`=?,`remainingStock`=?,`productPrice`=?,`productDescription`=?,`productTypeID`=? WHERE `productID`=?";
            
            try {
                st = MyConnectionDB.getConnection().prepareStatement(query);
                
                st.setString(1, newProduct.productName);
                st.setInt(2, newProduct.remainingStock);
                st.setDouble(3, newProduct.productPrice);
                st.setString(4, newProduct.productDescription);
                st.setInt(5, newProduct.productTypeID);
                
                st.setInt(6, newProduct.productID);
                
                st.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(productsPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    //show products based on the productTypeID
    public ArrayList<productsPage> productsFromProductTypeID(int productTypeID){
        
        PreparedStatement st;
        ResultSet rs;
        
        ArrayList<productsPage> productsTypeList = new ArrayList<>();
        
        String query = "SELECT `productID`, `productName`, `remainingStock`, `productPrice`, `productDescription`, `productImage` FROM `products` WHERE `productTypeID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, productTypeID);
            rs = st.executeQuery();
            
            productsPage productFromTypes;
            
            while(rs.next()){
                productFromTypes = new productsPage(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getDouble(4),rs.getString(5), 0,rs.getBytes(6));
                
                //adding objects to the list of products
                productsTypeList.add(productFromTypes);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(productsPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productsTypeList;
        
    }
        
    
    
    
    
}
