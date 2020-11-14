/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserDBTesting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hassan
 */
public class insertOrderTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
        String orderDate = dtf.format(now);
        String orderTime = dtf.format(now);
        
        orderDate = orderDate.toString().substring(0,10);
        orderTime = orderTime.toString().substring(11);
        
        Classes.getDateTime dateNtime = new Classes.getDateTime();
        //System.out.println(dateNtime.getDate());
        //System.out.println(dateNtime.getTime());
        
        int customerID = 3;
        double totalPrice = 49.99;
        
        //Classes.makeOrder insertOrder = new Classes.makeOrder(0, customerID, dateNtime.getDate(), dateNtime.getTime(), totalPrice);
        //Classes.makeOrder.insertOrdersTable(insertOrder);
        
        String splitID = "Hassan Aslam, ID:200047";
        String getID = splitID.substring(splitID.lastIndexOf("ID:")).substring(3);
        int intGetID = Integer.valueOf(getID);
        
        System.out.println(intGetID);
        
    }
    
}
