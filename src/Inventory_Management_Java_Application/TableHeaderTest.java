/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory_Management_Java_Application;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author hassan
 */
public class TableHeaderTest {
    public static void main(String[] args)
    {
        String[] headers = {
            "column 1", "column 2", "column 3", "column 4"
        };
        String[][] data = new String[4][4];
        for(int i = 0; i < data.length; i++)
            for(int j = 0; j < data[0].length; j++)
            {
                data[i][j] = "item " + (i * data[0].length + j + 1);
            }
        JTable table = new JTable(data, headers);
        for(int i = 0; i < headers.length; i++)
        {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new HeaderRenderer());
        }
        table.setShowGrid(false);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JScrollPane(table));
        f.setSize(400,300);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}

class HeaderRenderer extends JLabel implements TableCellRenderer
{
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean hasFocus,
                                                   boolean isSelected,
                                                   int row,
                                                   int col)
    {
        setText(value.toString());
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        return this;
    }
}

