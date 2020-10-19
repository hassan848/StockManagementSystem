/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserDBTesting;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author hassan
 */
public class InsertUserTesting1DB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        String username = "ok";
        String password = "H4554n12";
        String fullname = "Aslam jeewa";
        String contactNumber = "07956282075";
        String email = "aslamjeewa@bendover.co.uk";
        String userType = "userLevel2";
        
        Classes.UsersPage newUser = new Classes.UsersPage(username, password, fullname, contactNumber, email, userType);
        Classes.UsersPage.insertUser(newUser);
    }
    
}
