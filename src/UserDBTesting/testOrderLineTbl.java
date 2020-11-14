/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserDBTesting;

/**
 *
 * @author hassan
 */
public class testOrderLineTbl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int PID = 2;
        int qty = 32;
        double price = 32.45;
        
        Classes.makeOrder.insertOrderLineTable(PID, PID, qty, price);
    }
    
}
