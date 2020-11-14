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
public class sendingEmailTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        String directory = "Invoice\\" + "2020-11-07" + "\\";
        String fileOrderName = "Order " + 35;
        String fileName = directory+fileOrderName+".docx";
        
        Classes.sendingEmail.sendMail("hassanaslam786@hotmail.co.uk", fileName);
        
    }
     
}
