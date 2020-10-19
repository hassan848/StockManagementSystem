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
public class saltHashTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        String password = "Hello World";
        Classes.saltedHash hashMyPass = new Classes.saltedHash(password);
        //Classes.saltedHash.createSaltHash(hashMyPass);
        //String saltHex = 
        //System.out.println(hashedPass);
        System.out.println(hashMyPass.getHash());
        System.out.println(hashMyPass.getSalt());
    }
    
}
