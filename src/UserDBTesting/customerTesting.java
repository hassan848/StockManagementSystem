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
public class customerTesting {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int id = 1;
        String fName = "Hamza";
        String Lname = "Noor";
        String cNumber = "07808166771";
        String email = "hamza786@hotmail.com";
        String postcode = "IG1 3JJ";
        
        Classes.customersPage customer = new Classes.customersPage(id,fName,Lname,cNumber,email,postcode);
        Classes.customersPage.insertCustomer(customer);
    }
    
}
