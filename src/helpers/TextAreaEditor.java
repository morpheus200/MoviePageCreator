/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author frank
 */
public class TextAreaEditor extends AbstractCellEditor implements TableCellEditor{

    
    private JTextArea textArea = new JTextArea();
    
    @Override
    public Object getCellEditorValue() {
        return textArea.getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            textArea.setText(value.toString());
            return textArea;
    }
    
}
