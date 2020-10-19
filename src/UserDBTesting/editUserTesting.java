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
public class editUserTesting {

    public static void main(String[] args) {
        // TODO code application logic here
        String username = "XDnoobz";
        String name = "Adam Johnson";
        String number = "0780813300";
        String email = "adam@hotamail.com";
        String newUsername = "Adam_5453";
        
        Classes.UsersPage updateUser = new Classes.UsersPage(newUsername,username,name,number,email,"level1");
        Classes.UsersPage.editUser(updateUser);
    }
    
}
