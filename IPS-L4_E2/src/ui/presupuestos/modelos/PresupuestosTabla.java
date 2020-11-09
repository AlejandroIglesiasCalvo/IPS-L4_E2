package ui.presupuestos.modelos;

import javax.swing.JTable;

public class PresupuestosTabla extends JTable {

	private static final long serialVersionUID = 8890762715412718599L;

	public PresupuestosTabla() {
		setModel(new PresupuestosTablaModel());
		setRowHeight(25);
		setAutoCreateRowSorter(true);
		setAlignmentX(CENTER_ALIGNMENT);
	}

}
