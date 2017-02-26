/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author frank
 */
public class TableCellListener implements PropertyChangeListener, Runnable{

    private JTable table;
    private Action action;
    private int row;
    private int column;
    private Object oldValue;
    private Object newValue;
    
    
    public TableCellListener(JTable table, Action action){
        this.table = table;
        this.action = action;
        this.table.addPropertyChangeListener(this);
    }
    
    private TableCellListener(JTable table, int row, int column, Object oldValue, Object newValue){
        this.table = table;
        this.row = row;
        this.column = column;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    
    public int getColumn(){
        return this.column;
    }
    
    public Object getNewValue(){
        return this.newValue;
    }
    
    public Object getOldValue(){
        return this.oldValue;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public JTable getTable(){
        return this.table;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("tableCellEditor".equals(evt.getPropertyName()))
        {
            if(this.table.isEditing()){
                processEditingStarted();
            } else {
                processEditingStopped();
            }
        }
    }
    
    private void processEditingStarted(){
        SwingUtilities.invokeLater(this);
    }
    
    private void processEditingStopped(){
        this.newValue = table.getModel().getValueAt(row, column);
        if(! this.newValue.equals(this.oldValue)){
            TableCellListener tcl = new TableCellListener(getTable(),
                    getRow(),getColumn(),getOldValue(),getNewValue());
            ActionEvent event = new ActionEvent(tcl,ActionEvent.ACTION_PERFORMED,"");
            action.actionPerformed(event);
        }
    }

    @Override
    public void run() {
        this.row = table.convertRowIndexToModel(this.table.getEditingRow());
        this.column = table.convertColumnIndexToModel(this.table.getEditingColumn());
        this.oldValue = table.getModel().getValueAt(this.row, this.column);
        this.newValue = null;
    }
    
}
