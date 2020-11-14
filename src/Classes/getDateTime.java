/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hassan
 */
public class getDateTime {
    
    private String date;
    private String time;
    
    public getDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
        String orderDate = dtf.format(now);
        String orderTime = dtf.format(now);
        
        this.date = orderDate.toString().substring(0,10);
        this.time = orderTime.toString().substring(11);
    }
    
    public String getDate(){
        return this.date;
    }
    
    public String getTime(){
        return this.time;
    }
    
    
}
