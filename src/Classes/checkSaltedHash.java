/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import static Classes.saltedHash.byteArrayToHexString;
import static Classes.saltedHash.generateSalt;
import Inventory_Management_Java_Application.MyConnectionDB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hassan
 */
public class checkSaltedHash {
    private String passToHash;
    private String hashedPass;
    private String username;
    
    public checkSaltedHash(String password, String username) throws NoSuchAlgorithmException{
        this.passToHash = password;
        this.username = username;
        this.hashedPass = createSaltHash();
        
    }
    
    public String createSaltHash() throws NoSuchAlgorithmException{
        MessageDigest newDigest = MessageDigest.getInstance("SHA-256");
        newDigest.reset();
        String saltHex = getSaltFromDB();
        
        if(saltHex==null){
            return null;
        }else{
            
            byte[] salt = hexStringToByteArray(saltHex);

            //this.saltToHash = byteArrayToHexString(salt);

            newDigest.update(salt);
            byte[] newHash = newDigest.digest(this.passToHash.getBytes());

            //passToHash.hashedPass = byteArrayToHexString(newHash);
            //this.hashedPass = byteArrayToHexString(newHash);
            return byteArrayToHexString(newHash);
        }    
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
    
    public static byte[] hexStringToByteArray(String slt) {
        int len = slt.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(slt.charAt(i), 16) << 4)
                    + Character.digit(slt.charAt(i+1), 16));
        }
        return data;
    }
    
    public String getSaltFromDB(){
        String saltHex = null;
        PreparedStatement st;
        ResultSet rs;
        String query = "SELECT `salt` FROM `userslog` WHERE `Username` = ?";
        
        try {
            st = MyConnectionDB.getConnection().prepareStatement(query);
            st.setString(1, this.username);
            rs = st.executeQuery();
            if(rs.next()){
                saltHex = rs.getString("salt");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(checkSaltedHash.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saltHex;
    }
    
    public String getHash(){
        return this.hashedPass;
    }
}
