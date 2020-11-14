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
public class ProductTypes {
    
    private int productTypeID;
    private String productTypeName;
    
    public ProductTypes(int typeID, String typeName){
        this.productTypeID = typeID;
        productTypeName = typeName;
    }
    
    
    
    //get product types using ArrayList
    public ArrayList<ProductTypes> getTypeName(){
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT * FROM `producttypes`";
        
        ArrayList<ProductTypes> productTypeList = new ArrayList<>();
        
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            
            ProductTypes productType;
            
            while(rs.next()){
                productType = new ProductTypes(rs.getInt(1), rs.getString(2));
                
                productTypeList.add(productType);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productTypeList;
    }
    
}
