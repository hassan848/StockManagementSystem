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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hassan
 */
public class manageOrdersTab {
    
    int customerID;
    String customerName;
    
    int ProductTypeID;
    String typeName;
    
    public manageOrdersTab(){}
    
    public manageOrdersTab(int CID, String cName, int typeID, String typeName){
        this.customerID = CID;
        this.customerName = cName;
        
        this.ProductTypeID = typeID;
        this.typeName = typeName;
    }
    
    
    public HashMap<String, Integer> getAllCustomers(){
        PreparedStatement st;
        ResultSet rs;
        String query = "SELECT `CustomerID`, `firstName`, `lastName` FROM `customers`";
        
        HashMap<String, Integer> map = new HashMap<>();
        
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            
            manageOrdersTab allCustomers;
            
            while(rs.next()){
                String customerFname = rs.getString(2)+" "+rs.getString(3)+", ID:"+rs.getInt(1);
                allCustomers = new manageOrdersTab(rs.getInt(1), customerFname,0,"");
                
                map.put(allCustomers.customerName, allCustomers.customerID);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(manageOrdersTab.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return map;
    }
    
    
    
    
    public HashMap<String, Integer> getProductTypes(){
        PreparedStatement st;
        ResultSet rs;
        String query = "SELECT `productTypeID`, `productType` FROM `producttypes`";
        
        HashMap<String, Integer> map = new HashMap<>();
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            
            manageOrdersTab allProductTypes;
            
            while(rs.next()){
                allProductTypes = new manageOrdersTab(1,"",rs.getInt(1), rs.getString(2));
                
                map.put(allProductTypes.typeName, allProductTypes.ProductTypeID);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(manageOrdersTab.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return map;
        
    }
    
}
