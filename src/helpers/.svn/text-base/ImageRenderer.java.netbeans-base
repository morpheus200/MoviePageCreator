/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author frank
 */
public class ImageRenderer extends DefaultTableCellRenderer {
    
    private static final int picHeight = 177;
    private static final int picWidth = 120;

    
  @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        JLabel lbl = ((JLabel)super.getTableCellRendererComponent(table, value, 
                isSelected, hasFocus, row, column)); 
        ImageIcon movieIcon;
        movieIcon = new ImageIcon((String)value);
        Image image = movieIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(picWidth, picHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        lbl = new JLabel(new ImageIcon(newimg));
        table.getColumnModel().getColumn(column).setResizable(false);
        table.setRowHeight(lbl.getIcon().getIconHeight());
        return lbl;

      }
   
}
