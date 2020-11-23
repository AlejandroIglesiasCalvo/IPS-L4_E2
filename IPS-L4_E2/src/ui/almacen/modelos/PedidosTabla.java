package ui.almacen.modelos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.ListDataListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import ui.almacen.VisualizarPedidosView;
import ui.almacen.modelos.components.ComboEditor;
import ui.almacen.modelos.components.ComboRenderer;

public class PedidosTabla extends JTable {

	private static final long serialVersionUID = 8890762715412718599L;
	
	

	public PedidosTabla(VisualizarPedidosView v) {
		setModel(new PedidosTablaModel(v));
		setRowHeight(25);
		setAutoCreateRowSorter(true);
		
		setAlignmentX(CENTER_ALIGNMENT);
		/*JComboBox<String> combo = new JComboBox<>();
		
		combo.addItem("Solicitado");
		combo.addItem("Entregado");*/
		
		getColumn("Estado").setCellEditor(new ComboEditor(this));
		TableCellEditor t = getColumn("Estado").getCellEditor();
		System.out.println();	
		//getColumn("Estado").setCellRenderer(new ComboRenderer());
	}

}
