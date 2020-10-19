/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Inventory_Management_Java_Application.MyConnectionDB;
import java.security.NoSuchAlgorithmException;
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
public class UsersPage {
    
    private String username;
    private String password;
    private String fullName;
    private String contactNumber;
    private String email;
    private String userType;
    
    public UsersPage(String user_name,String pass_word){
        this.username = user_name;
        this.password = pass_word;
    }
    
    public UsersPage(String user_name, String pass_word, String full_name, String contact_number, String email, String user_type){
        this.username = user_name;
        this.password = pass_word;
        this.fullName = full_name;
        this.contactNumber = contact_number;
        this.email = email;
        this.userType = user_type;
        
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    
    public String getFullname(){
        return this.fullName;
    }
    
    public void setFullname(String newName){
        this.fullName = newName;
    }
    
    public String getContactNumber(){
        return this.contactNumber;
    }
    
    public void setContactNumber(String newNumber){
        this.contactNumber = newNumber;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    
    public String getUserType(){
        return this.userType;
    }
    
    public void setUserType(String newUserType){
        this.userType = newUserType;
    }
    
    public static void insertUser(UsersPage newUser) throws NoSuchAlgorithmException{
        
        PreparedStatement st;
        
        saltedHash hashMyPass = new saltedHash(newUser.password);
        
        String query = "INSERT INTO `userslog`(`Username`, `salt`, `UserPassword`, `FullName`, `ContactNumber`, `Email`, `UserType`) VALUES (?,?,?,?,?,?,?)";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, newUser.username);
            st.setString(2, hashMyPass.getSalt());
            st.setString(3, hashMyPass.getHash());
            st.setString(4, newUser.fullName);
            st.setString(5, newUser.contactNumber);
            st.setString(6, newUser.email);
            st.setString(7, newUser.userType);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void deleteUser(String userName){
        PreparedStatement st;
        String query = "DELETE FROM `userslog` WHERE `Username` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, userName);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void editUser(UsersPage existingUser){
        PreparedStatement st;
        String query = "UPDATE `userslog` SET `Username`=?,`FullName`=?,`ContactNumber`=?,`Email`=?,`UserType`=? WHERE `Username` = ?";
        
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, existingUser.username);
            st.setString(2, existingUser.fullName);
            st.setString(3, existingUser.contactNumber);
            st.setString(4, existingUser.email);
            st.setString(5, existingUser.userType);
            st.setString(6, existingUser.password);
            
            st.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void editUserPassword(UsersPage existingUser) throws NoSuchAlgorithmException{
        PreparedStatement st;
        String query = "UPDATE `userslog` SET `salt`=?,`UserPassword`=? WHERE `Username` = ?";
        
        saltedHash hashMyPass = new saltedHash(existingUser.password);
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, hashMyPass.getSalt());
            st.setString(2, hashMyPass.getHash());
            st.setString(3, existingUser.username);
            
            st.executeUpdate();
           // System.out.println(existingUser.username);
           // System.out.println(hashMyPass.getSalt());
           // System.out.println(hashMyPass.getHash());
        } catch (SQLException ex) {
            Logger.getLogger(UsersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
