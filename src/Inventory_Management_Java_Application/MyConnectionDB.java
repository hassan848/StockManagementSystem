package Inventory_Management_Java_Application;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hassan
 */
public class MyConnectionDB {
    private static String serverName = "localhost";
    private static String username = "root";
    private static String dbName = "users";
    private static int PortNumber = 3306;
    private static String password = "";
    
    public static Connection getConnection(){
        Connection MyConnectionDB = null;
        
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(serverName);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDatabaseName(dbName);
        dataSource.setPortNumber(PortNumber);
        
        try {
            MyConnectionDB = dataSource.getConnection();
            System.out.println("connected");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return MyConnectionDB;
    }
    
}
