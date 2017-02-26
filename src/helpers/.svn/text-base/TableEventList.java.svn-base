/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author frank
 */
public class TableEventList implements TableModelListener{
    
    @Override
    public void tableChanged(TableModelEvent e) {  
        int row = e.getFirstRow();  
        int column = e.getColumn();  
        TableModel model = (TableModel)e.getSource();  
        Object data = model.getValueAt(row, column);  
   
//now you have the data in the cell and the place in the grid where the   
   
//cell is so you can use the data as you want  
    } 
}
