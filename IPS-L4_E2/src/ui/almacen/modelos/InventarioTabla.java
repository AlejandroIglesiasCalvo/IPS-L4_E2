package ui.almacen.modelos;

import javax.swing.JTable;

public class InventarioTabla extends JTable {

	private static final long serialVersionUID = 8890762715412718599L;

	public InventarioTabla() {
		setModel(new InventarioTablaModel());
		setRowHeight(25);
		setAutoCreateRowSorter(true);
		setAlignmentX(CENTER_ALIGNMENT);
	}

}
