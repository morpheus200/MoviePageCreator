/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author frank
 */
public class NFOTableModel extends DefaultTableModel{
    
    private final String[] columnNames = {"Pic","Publisher","Title", 
        "Sorttitle", "Genre","MPAA","Runtime","Year","Plot","Language"};
 
    
    @Override 
    public int getColumnCount() { 
        return columnNames.length; 
    } 
    
    
    @Override 
    public String getColumnName(int index) { 
        return columnNames[index]; 
    } 

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        /*if(columnIndex == 8) {
            return String.class;
        }*/
      if (columnIndex == 4 || columnIndex == 5)
      {
        return JComboBox.class;
      }
      else
      {
        return super.getColumnClass(columnIndex);
      }
    }


    @Override
    public boolean isCellEditable(int row, int col)  
        { return true; }  
    
    
   /* @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        return value;
    }
    private String value = "";
    
    @Override
    public void setValueAt(Object aValue, int row, int col) {  
        //rowData[row][col] = value;  
        //fireTableCellUpdated(row, col);  
        value = aValue.toString();
    }*/

    
    
}

