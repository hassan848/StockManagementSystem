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
public class updatePasswordTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        String password = "hassan848";
        String username = "hassan848";
        
        Classes.UsersPage editPass = new Classes.UsersPage(username, password, null, null, null, null);
        Classes.UsersPage.editUserPassword(editPass);
    }
    
}
