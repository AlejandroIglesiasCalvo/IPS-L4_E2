package ui.almacen.modelos;

import javax.swing.JTable;

public class InfoPedidoTabla extends JTable {

	private static final long serialVersionUID = 8890762715412718599L;

	public InfoPedidoTabla() {
		setModel(new InfoPedidoTablaModel());
		setRowHeight(25);
		setAutoCreateRowSorter(true);
		setAlignmentX(CENTER_ALIGNMENT);
	}

}
