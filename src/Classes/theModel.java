/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hassan
 */
public class theModel extends DefaultTableModel{
    
     private Object[][] jTableRow;
    private String[] jTableColumn;
    
    public theModel(Object[][]dataInRows, String[] columnHeading){
        this.jTableRow = dataInRows;
        this.jTableColumn = columnHeading;
    }
    
    public Class getColumnClass(int column)
    {
       
        if(column == 5){
            return Icon.class; 
        }else{
            return getValueAt(0, column).getClass();
        }
    }
    
    @Override
    public int getRowCount() {
        return this.jTableRow.length;
    }

    @Override
    public int getColumnCount() {
        return this.jTableColumn.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return this.jTableRow[i][i1];
    }
    
    @Override
    public String getColumnName(int col){
        
        return this.jTableColumn[col];
        
    }
    
    
    
   
}
