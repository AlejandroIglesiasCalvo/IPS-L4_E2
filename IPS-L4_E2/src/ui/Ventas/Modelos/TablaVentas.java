package ui.Ventas.Modelos;

import javax.swing.JTable;

public class TablaVentas extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TablaVentas() {
		setModel(new TablaVentasModel());
		setRowHeight(25);
		setAutoCreateRowSorter(true);
		setAlignmentX(CENTER_ALIGNMENT);
	}
}
