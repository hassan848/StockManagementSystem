/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
/**
 *
 * @author hassan
 */
public class generateWordReceipt {
    
    public static void createDocument(String date, String time, int orderID, String cName, int CID, double totalPrice, String email){
        
        checkIfFolderExist(date);
        
        String directory = "Invoice\\" + date + "\\";
        String fileOrderName = "Order " + orderID;
        String fileName = directory+fileOrderName+".docx";
        
        String companyName = "\t\t\t\tElectronics Phones & Repairs. Ltd.";
        String Location = "\t\t\t\t\tLondon, England";
        String PhoneNumber = "\t\t\t\t\t0208133771";
        String invoiceName = "\t\t\t\t\tInvoice";
        
        String dateSpace = "\t\t\t\t\t"+date;
        String timeSpace = "\t\t\t\t\t"+time;
        
        //create a String Array
        String[] Final1 = {companyName, Location, PhoneNumber, invoiceName, dateSpace, timeSpace};
        //String Final = companyName+Location+PhoneNumber+invoiceName;
        
        
        XWPFDocument document = new XWPFDocument();
        
        try{
            FileOutputStream output = new FileOutputStream(new File(fileName));
            
            XWPFParagraph paraTit = document.createParagraph();
            //paraTit.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun paraTitRun = paraTit.createRun();
            paraTitRun.setFontSize(10);
            paraTitRun.setFontFamily("Courier New");
            
            //First Section
            for(int i=0; i<Final1.length; i++){
                paraTitRun.setText(Final1[i]);
                paraTitRun.addBreak();
            }
            
            //Second Section
            String customerName = "\t\t\t\tCustomer Name: "+cName;
            String customerID = "\t\t\t\t\tCustomerID: "+CID;
            String receiptID = "\t\t\t\t\tReceipt ID: "+orderID;
            String orderNo = "\t\t\t\t\tOrder No:   "+orderID;
            
            paraTitRun.addBreak();
            String[] Final2 = {customerName, customerID, receiptID, orderNo};
            for(int x = 0; x < Final2.length; x++){
                paraTitRun.setText(Final2[x]);
                paraTitRun.addBreak();
            }
            
            paraTitRun.addBreak();
            String servedBy = "\t\t\t\tServed By: Admin";
            paraTitRun.setText(servedBy);
            paraTitRun.addBreak();
            
            paraTitRun.addBreak();
            paraTitRun.addBreak();
            String firstTableHeader = "\t-------------------------------------------------------------------";
            paraTitRun.setText(firstTableHeader);
            paraTitRun.addBreak();
            String headings = "\t\tProducts\t\t\t\tQuantity\t\tPrice";
            paraTitRun.setText(headings);
            
            paraTitRun.addBreak();
            String secondTableHeader = "\t\t--------------------------------------------------";
            paraTitRun.setText(firstTableHeader);
            paraTitRun.addBreak();
            
            //table_header = "\n\n\t\t--------------------------------------------------\n\t\t\tProducts\t\tQuantity\t\tPrice\n\t\t--------------------------------------------------"
            //Final = ComapanyName + Location + PhoneNumber + basic + date + Time + '\n' + CustomerName + customerID + receiptID + orderID +'\n' + table_header
            
            //get Products ID
            ArrayList<Integer> listOfProductID = Classes.makeOrder.getProductsIDList(orderID);
            //get ProductsName using productID
            ArrayList<String> productsNameList = new ArrayList<>();
            
            for(int y = 0; y<listOfProductID.size();y++){
                productsNameList.add(Classes.makeOrder.getProductsNameList(listOfProductID.get(y)));
            }
            
            //adjust product Names to fit onto word documents
            for(int a=0; a<productsNameList.size(); a++){
                while(productsNameList.get(a).length()<25){
                    productsNameList.set(a, productsNameList.get(a) + " ");
                }
            }
            
            //get products Quantity List
            ArrayList<Integer> listOfProductQty = Classes.makeOrder.getProductQuantity(orderID);
            //get products Price List
            ArrayList<Double> listOfProductPrice = Classes.makeOrder.getProductPrice(orderID);
            
            for(int i=0;i<listOfProductID.size();i++){
                paraTitRun.setText("\t\t"+productsNameList.get(i)+"\t\t"+listOfProductQty.get(i)+"\t\t"+"£"+listOfProductPrice.get(i));
                paraTitRun.addBreak();
                //System.out.print(" - "+listOfProductQty.get(i));
                //System.out.println(" - "+ listOfProductPrice.get(i));
            }
            
            paraTitRun.addBreak();
            paraTitRun.setText(firstTableHeader);
            paraTitRun.addBreak();
            String totalPriceHeading = "\t\tTotal Price:\t\t\t\t    \t\t£"+totalPrice;
            paraTitRun.setText(totalPriceHeading);
            paraTitRun.addBreak();
            paraTitRun.setText(firstTableHeader);
                  
            document.write(output);
            output.close();
            
            
        }catch(Exception e){
            e.printStackTrace();
        
        }
        
        try {
            sendEmail(email, fileName);
        } catch (Exception ex) {
            Logger.getLogger(generateWordReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public static void checkIfFolderExist(String date){
        
        File file = new File("Invoice\\"+date+"\\");
        if(!file.exists()){
            if(file.mkdir()){
                //System.out.println("Folder created");
            }
        }
    }
    
    public static void sendEmail(String recipient, String fileName) throws Exception{
        Classes.sendingEmail.sendMail(recipient, fileName);
    }
    
    
    
    public static void openReceiptFile(String date, int orderID){
        
        String directory = "Invoice\\" + date + "\\";
        String fileOrderName = "Order " + orderID;
        String fileName = directory+fileOrderName+".docx";
        
        try{
            
            File file = new File(fileName);
            
            if(file.exists()){
                
                Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+fileName);
                pro.waitFor();
                
                
            }else{
                
            }
            
            
        }catch(Exception e) {
            
        }
        
    }
    
}
