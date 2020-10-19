/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Inventory_Management_Java_Application.MyConnectionDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hassan
 */
public class customersPage {
    private int ID;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String email;
    private String postcode;
    
    public customersPage(int id,String fName,String lastName,String cNumber,String email, String postcode){
        this.ID = id;
        this.firstName = fName;
        this.lastName = lastName;
        this.contactNumber = cNumber;
        this.email = email;
        this.postcode = postcode;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public String getContactNumber(){
        return this.contactNumber;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getPostcode(){
        return this.postcode;
    }
    
    public static void insertCustomer(customersPage customer){
        PreparedStatement st;
        
        String query = "INSERT INTO `customers`(`firstName`, `lastName`, `contactNumber`, `Email`,`postcode`) VALUES (?,?,?,?,?)";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, customer.firstName);
            st.setString(2, customer.lastName);
            st.setString(3, customer.contactNumber);
            st.setString(4, customer.email);
            st.setString(5, customer.postcode);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(customersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void deleteCustomer(int id){
        PreparedStatement st;
        
        String query = "DELETE FROM `customers` WHERE `CustomerID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, id);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(customersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public static void editCustomer(customersPage customer){
        PreparedStatement st;
        
        String query = "UPDATE `customers` SET `firstName`=?,`lastName`=?,`contactNumber`=?,`Email`=?,`postcode`=? WHERE `CustomerID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, customer.firstName);
            st.setString(2, customer.lastName);
            st.setString(3, customer.contactNumber);
            st.setString(4, customer.email);
            st.setString(5, customer.postcode);
            
            st.setInt(6, customer.ID);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(customersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
