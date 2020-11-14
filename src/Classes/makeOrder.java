/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Inventory_Management_Java_Application.MyConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hassan
 */
public class makeOrder {
    
    private int orderID;
    private int customerID;
    
    private double totalPrice;
    
    private String orderDate;
    private String orderTime;
    
    
    public makeOrder(int OID, int CID, String date, String time, double ttlPrice){
        this.orderID = OID;
        this.customerID = CID;
        this.orderDate = date;
        this.orderTime = time;
        this.totalPrice = ttlPrice;
    }
    
    public int getOrderID(){
        return this.orderID;
    }
    
    public int getCustomerID(){
        return this.customerID;
    }
    
    public String getOrderDate(){
        return this.orderDate;
    }
    
    public String getOrderTime(){
        return this.orderTime;
    }
    
    
    //get customer Name from customer ID
    public static String customerName(int CID){
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT `firstName`, `lastName` FROM `customers` WHERE `CustomerID` = ?";
        
        String customerName = "";
        
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, CID);
            rs = st.executeQuery();
            
            if(rs.next()){
                customerName = rs.getString("firstName");
                customerName = customerName + " " + rs.getString("lastName");
            }
            
            return customerName;
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customerName;
    }
    
    
    //get customer Email from customer ID
    public static String getCustomerEmail(int CID){
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT `Email` FROM `customers` WHERE `CustomerID` = ?"; 
        String email = "";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, CID);
            rs = st.executeQuery();
            
            if(rs.next()){
                email = rs.getString("Email");
            }
            
            return email;
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return email;
    }
    
    
    
    //insert into orders table in DB
    public static void insertOrdersTable(makeOrder order){
        PreparedStatement st;
        
        String query = "INSERT INTO `orders`(`customerID`, `dateCreated`, `timeCreated`, `totalPrice`) VALUES (?,?,?,?)";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, order.customerID);
            st.setString(2, order.orderDate);
            st.setString(3, order.orderTime);
            st.setDouble(4, order.totalPrice);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Insert into orderline table 
    public static void insertOrderLineTable(int ordersID, int productID, int quantity, double productPrice){
        PreparedStatement st;
        PreparedStatement updateQuantity;
        
        String query = "INSERT INTO `orderLine`(`ordersID`, `productID`, `quantity`, `productPrice`) VALUES (?,?,?,?)";
        String query2 = "UPDATE products set remainingStock = remainingStock - ? WHERE productID = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, ordersID);
            st.setInt(2, productID);
            st.setInt(3, quantity);
            st.setDouble(4, productPrice);
            
            if(st.executeUpdate() !=0){
                
                updateQuantity = MyConnectionDB.getConnection().prepareStatement(query2);
                updateQuantity.setInt(1, quantity);
                updateQuantity.setInt(2, productID);
                updateQuantity.executeUpdate();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //get productID
    public static int getProductIDfromName(String productName){
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT `productID` FROM `products` WHERE `productName` = ?";
        int PID=0;
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, productName);
            rs = st.executeQuery();
            
            if(rs.next()){
                PID = rs.getInt("productID");
            }
            return PID;
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return PID;
    }
    
    
    
    //get the last order ID
    public static int getLastOrderID(){
        PreparedStatement st;
        ResultSet rs;
        
        int lastID = 0;
        String query = "SELECT MAX(`ordersID`) FROM `orders`";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            
            if(rs.next()){
                lastID = rs.getInt(1);
            }
            
            return lastID;
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lastID;
    }
    
    
    //get productsID from OrderLine using OrderID
    public static ArrayList<Integer> getProductsIDList(int ordersID){
        PreparedStatement st;
        ResultSet rs;
        
        ArrayList<Integer> productsIDList = new ArrayList<>();
        
        String query = "SELECT `productID` FROM `orderline` WHERE `ordersID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, ordersID);
            rs = st.executeQuery();
            
            while(rs.next()){
                productsIDList.add(rs.getInt(1));
                
            }
            
            return productsIDList;
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productsIDList;
    }
    
    
    //get ProductName from ProductsTable Using productsID
    public static String getProductsNameList(int productsID){
        PreparedStatement st;
        ResultSet rs;
        
        String productName = "";
      
        
        String query = "SELECT `productName` FROM `products` WHERE `productID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, productsID);
            rs = st.executeQuery();
            
            while(rs.next()){
                productName = rs.getString(1);
            }
            
            return productName;
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productName;
    }
    
    
    
    //get quantity from OrderLineTable using OrdersID
    public static ArrayList<Integer> getProductQuantity(int ordersID){
        PreparedStatement st;
        ResultSet rs;
        
        ArrayList<Integer> productsQtyList = new ArrayList<>();
        
        String query = "SELECT `quantity` FROM `orderline` WHERE `ordersID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, ordersID);
            rs = st.executeQuery();
            
            while(rs.next()){
                productsQtyList.add(rs.getInt(1));
            }
            
            return productsQtyList;
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productsQtyList;
    }
    
    
    
    //get productPrice for each product from orderline using orderID
    public static ArrayList<Double> getProductPrice(int ordersID){
        PreparedStatement st;
        ResultSet rs;
        
        ArrayList<Double> productsPriceList = new ArrayList<>();
        
        String query = "SELECT `productPrice` FROM `orderline` WHERE `ordersID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, ordersID);
            rs = st.executeQuery();
            
            while(rs.next()){
                productsPriceList.add(rs.getDouble(1));
            }
         
            return productsPriceList;
            
        } catch (SQLException ex) {
            Logger.getLogger(makeOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productsPriceList;
    }
    
}
