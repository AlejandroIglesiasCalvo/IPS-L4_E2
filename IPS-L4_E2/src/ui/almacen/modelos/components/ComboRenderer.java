package ui.almacen.modelos.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class ComboRenderer extends  DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String v  = (String) value;
		
		
		if(v.equals("solicitado")){
			setText(v);			
		}else if(v.equals("recibido")){
			setText(v);		
		}else {		
			System.out.printf("fallo de formato %s \n", v);
		}
		
		if(isSelected) {
			setBackground(table.getSelectionBackground());
		}else {
			setBackground(Color.RED);
		}
		
		return this;
	}

}
