/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.awt.Component;
import java.awt.Image;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author frank
 */
//public class TextAreaRenderer implements TableCellRenderer{
public class TextAreaRenderer extends DefaultTableCellRenderer {
    private JTextArea textArea = new JTextArea();
    private static final int picHeight = 177;
    private static final int picWidth = 120;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       
        table.getTableHeader().setReorderingAllowed(false);
        
        Component comp = null;
        
        if (column == 4){
            TableColumn genreColumn = table.getColumnModel().getColumn(column);
            JComboBox genreBox = new JComboBox();
            genreBox.addItem("Action");
            genreBox.addItem("Adult");        
            genreBox.addItem("Sci-Fi");
            genreBox.setSelectedItem(value);
            genreColumn.setCellEditor(new DefaultCellEditor(genreBox));
            comp = genreBox;
        }
        
        if ( column == 5){
            TableColumn mpaaColumn = table.getColumnModel().getColumn(column);
            JComboBox mpaaBox = new JComboBox();        
            mpaaBox.addItem("G");
            mpaaBox.addItem("PG");
            mpaaBox.addItem("PG-13");
            mpaaBox.addItem("NC-17");   
            mpaaBox.addItem("R");
            mpaaBox.setSelectedItem(value);
            mpaaColumn.setCellEditor(new DefaultCellEditor(mpaaBox));
            comp = mpaaBox;            
        }
        
        if (column == 8) {
            textArea.setText(value.toString());
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            table.getColumnModel().getColumn(column).setCellEditor(new TextAreaEditor());
            comp = textArea;
        }
        
        if (column == 9) {
            TableColumn languageColumn = table.getColumnModel().getColumn(column);
            JComboBox languageBox = new JComboBox();
            languageBox.addItem("Deutsch");
            languageBox.addItem("Englisch");
            languageBox.setSelectedItem(value);
            languageColumn.setCellEditor(new DefaultCellEditor(languageBox));
            comp = languageBox;
        }
        
        if (column == 0) {
            TableColumn imageColumn = table.getColumnModel().getColumn(column);
            imageColumn.setPreferredWidth(120);
            imageColumn.setMinWidth(120);
            imageColumn.setMaxWidth(120);
            JLabel lbl; //= ((JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));
            ImageIcon movieIcon;
            movieIcon = new ImageIcon((String)value);
            Image image = movieIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(picWidth, picHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
            lbl = new JLabel(new ImageIcon(newimg));
            table.getColumnModel().getColumn(column).setResizable(false);
            table.setRowHeight(lbl.getIcon().getIconHeight());
            comp =  lbl;
        }
        
        if (comp == null) {
            comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
        return comp;
    }
    
}
