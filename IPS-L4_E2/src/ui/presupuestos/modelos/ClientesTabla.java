package ui.presupuestos.modelos;

import javax.swing.JTable;

public class ClientesTabla extends JTable {

	private static final long serialVersionUID = 8890762715412718599L;

	public ClientesTabla() {
		setModel(new ClientesTablaModel());
		setRowHeight(25);
		setAutoCreateRowSorter(true);
	}

}
