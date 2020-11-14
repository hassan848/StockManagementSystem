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
public class getOrderNReceipt {
    
    private int orderID;
    private String customerName;
    private String dateCreated;
    private String timeCreated;
    private Double totalPrice;
    
    public getOrderNReceipt(){};
    
    public getOrderNReceipt(int orderID, String customerName, String Date, String Time, Double totalPrice){
        this.orderID = orderID;
        this.customerName = customerName;
        this.dateCreated = Date;
        this.timeCreated = Time;
        this.totalPrice = totalPrice;
    }
    
    public int getOrderID(){
        return this.orderID;
    }
    
    public String getCustomerName(){
        return this.customerName;
    }
    
    public String getDate(){
        return this.dateCreated;
    }
    
    public String getTime(){
        return this.timeCreated;
    }
    
    public Double getTotalPrice(){
        return this.totalPrice;
    }
    
    //get customerName using customerID and return String name to allOrders object List
    public static String getCustomerName(int CID){
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT `firstName`, `lastName` FROM `customers` WHERE `CustomerID` = ?";
        
        String customerName="";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, CID);
            rs = st.executeQuery();
            
            while(rs.next()){
                customerName = rs.getString(1)+" "+rs.getString(2);
            }
            
            return customerName;
            
        } catch (SQLException ex) {
            Logger.getLogger(getOrderNReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerName;
    }
    
    
    
    
    //get all orders and return to viewOrders page
    public static ArrayList<getOrderNReceipt> allOrders(){
        PreparedStatement st;
        ResultSet rs;
        
        ArrayList<getOrderNReceipt> allOrdersList = new ArrayList<>();
        String customerName = "";
        
        String query = "SELECT `ordersID`, `customerID`, `dateCreated`, `timeCreated`, `totalPrice` FROM `orders`";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            
            getOrderNReceipt allOrdersObject;
            
            while(rs.next()){
                customerName = getCustomerName(rs.getInt("customerID"));
                allOrdersObject = new getOrderNReceipt(rs.getInt(1), customerName, rs.getString(3), rs.getString(4), rs.getDouble(5));
                
                allOrdersList.add(allOrdersObject);
            }
            
            return allOrdersList;
            
        } catch (SQLException ex) {
            Logger.getLogger(getOrderNReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allOrdersList;
    }
    
    
    
    //delete order from orders then do the same in orderLineID
    public static void deleteOrder(int orderID){
        PreparedStatement st;
        String query = "DELETE FROM `orders` WHERE `ordersID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, orderID);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(getOrderNReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
        deleteOrderLineID(orderID);
    }
    
    
    //delete orderLines where orderID
    public static void deleteOrderLineID(int orderID){
        PreparedStatement st;
        String query = "DELETE FROM `orderline` WHERE `ordersID` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setInt(1, orderID);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(getOrderNReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
