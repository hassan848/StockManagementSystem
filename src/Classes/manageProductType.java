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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hassan
 */
public class manageProductType {
    
    private int productTypeID;
    private String name;
    
    public manageProductType(){};
    
    public manageProductType(int PTID, String name){
        this.productTypeID = PTID;
        this.name = name;
    }
    
    public int getID(){
        return this.productTypeID;
    }
    
    public String getName(){
        return this.name;
    }
    
    public HashMap<String, Integer> getAllProductTypes(){
        PreparedStatement st;
        ResultSet rs;
        String query = "SELECT `productTypeID`, `productType` FROM `producttypes`";
        
        HashMap<String, Integer> map = new HashMap<>();
        
        try {
            
            st = MyConnectionDB.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            
            manageProductType productType;
            
            while(rs.next()){
                productType = new manageProductType(rs.getInt(1),rs.getString(2));
                
                map.put(productType.name, productType.productTypeID);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(manageProductType.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return map;
        
    }
    
    
    
    public int getAllProductTypesSearch(String searchField){
        PreparedStatement st;
        ResultSet rs;
        String query = "SELECT `productTypeID`, `productType` FROM `producttypes` WHERE productType LIKE '%"+searchField+"%' ";
        
        //HashMap<Integer, String> map = new HashMap<>();
        int x = -1;
        
        try {
            
            st = MyConnectionDB.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            
            manageProductType productType;
            
            while(rs.next()){
                productType = new manageProductType(rs.getInt(1),rs.getString(2));
                x = productType.productTypeID;
                //map.put(productType.productTypeID, productType.name);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(manageProductType.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
        
    }
    
    public static ArrayList<manageProductType> getTypeList(){
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT * FROM `producttypes`";
        
        ArrayList<manageProductType> productTypeList = new ArrayList<>();
        
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            
            manageProductType productType;
            
            while(rs.next()){
                productType = new manageProductType(rs.getInt(1), rs.getString(2));
                
                productTypeList.add(productType);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productTypeList;
    }
    
    
    //Insert a new productType
    public static void addNewProductType(String productType){
        PreparedStatement st;
        
        String query = "INSERT INTO `producttypes`(`productType`) VALUES (?)";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, productType);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(manageProductType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   //Delete ProductType
    public static void deleteProductType(int productTypeID){
        PreparedStatement st;
        String query = "DELETE FROM `producttypes` WHERE `productTypeID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, productTypeID);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(manageProductType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
    //edit ProductType Name
    public static void editTypeName(int typeID, String Name){
        PreparedStatement st;
        String query = "UPDATE `producttypes` SET `productType`=? WHERE `productTypeID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, Name);
            st.setInt(2, typeID);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(manageProductType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    
    
}
