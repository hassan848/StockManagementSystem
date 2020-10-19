/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author hassan
 */
public class saltedHash {
    //static String hashAlgorithm = "SHA-256";
    private String passToHash;
    private String saltToHash;
    private String hashedPass;
   
    public saltedHash(String data) throws NoSuchAlgorithmException{
        this.passToHash = data;
        this.hashedPass = createSaltHash();
        
    }
    
    public String createSaltHash() throws NoSuchAlgorithmException{
        MessageDigest newDigest = MessageDigest.getInstance("SHA-256");
        newDigest.reset();
        byte[] salt = generateSalt();
        
        this.saltToHash = byteArrayToHexString(salt);
    
        newDigest.update(salt);
        byte[] newHash = newDigest.digest(this.passToHash.getBytes());
        
        //passToHash.hashedPass = byteArrayToHexString(newHash);
        //this.hashedPass = byteArrayToHexString(newHash);
        return byteArrayToHexString(newHash);
    }
    
    private final static char[] hexadecimalArray = "0123456789ABCDEF".toCharArray();
    
    public static String byteArrayToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexadecimalArray[v >>> 4];
            hexChars[j * 2 + 1] = hexadecimalArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static byte[] generateSalt(){
        byte[] newByte = new byte[20];
        SecureRandom randomSalt = new SecureRandom();
        randomSalt.nextBytes(newByte);
        return newByte;
    }
    
    public String getSalt(){
        return this.saltToHash;
    }
    public String getHash(){
        return this.hashedPass;
    }
}
