package ui.almacen.modelos.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListDataListener;
import javax.swing.table.TableCellEditor;

import logic.dto.Pedido;
import ui.almacen.modelos.PedidosTablaModel;

public class ComboEditor extends AbstractCellEditor implements TableCellEditor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Component combo;
	JTable t;
	int r;
	int c= 2;
	
	public ComboEditor (JTable t) {
		this.t = t;
		/*combo = new JComboBox<String>();
		combo.setModel(new DefaultComboBoxModel<>());
		combo.addItem("solicitado");
		combo.addItem("recibido");
		combo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> c = (JComboBox<String>)e.getSource();
				String s = (String)c.getSelectedItem();
				if(s.equals("recibido")) {
					c.setEnabled(false);
					//aqui hace cosas;
					System.out.println("cosas");
					stopCellEditing();
				}
				
			}
		});
		combo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				stopCellEditing();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});*/
		
	}	

	/*@Override
	public Object getCellEditorValue() {
		
		return combo.getSelectedItem();
	}*/

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		boolean s = ((PedidosTablaModel)t.getModel()).getState(row);
		
		r = row;
		column = c;
		

		
		/*if(s){
			combo.setSelectedIndex(0);
		}else {
			combo.setSelectedIndex(1);
		}*/
		if(s) {
			combo = new JComboBox<>();
			JComboBox<String> cb =(JComboBox<String>) combo;
			cb.setModel(new DefaultComboBoxModel<>());
			cb.addItem("solicitado");
			cb.addItem("recibido");
			
			
			cb.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> combo = (JComboBox<String>)e.getSource();
					String s = (String) combo.getSelectedItem();
					t.setValueAt(s, r, c);
					((PedidosTablaModel)t.getModel()).changeState(row, s);
					/*
					 * Metemos aqui el codigo de edicion de tabla
					 * 
					 * 
					 */
					((PedidosTablaModel) t.getModel()).getView().getController().updatePedidoState(s, row);
					
					stopCellEditing();
					
				}
			});
		}else {
			combo = new JLabel();
			((JLabel)combo).setText("recibido");
		}
		
		combo.addFocusListener(new FocusListener() {			
			@Override
			public void focusLost(FocusEvent e) {stopCellEditing();	}			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub				
			}
		});		
		
		
		if(isSelected) {
			combo.setBackground(table.getSelectionBackground());
		}else {
			combo.setBackground(Color.WHITE);
		}
		
		return combo;
	}

	@Override
	public Object getCellEditorValue() {
		if(combo instanceof JComboBox<?>) {
			return ((JComboBox<String>)combo).getSelectedItem();
		}else {
			return ((JLabel)combo).getText();
		}
	}

}
