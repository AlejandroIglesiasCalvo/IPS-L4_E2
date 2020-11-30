package ui.presupuestos.modelos;

import javax.swing.JTable;

public class PlantillasTabla extends JTable {

	private static final long serialVersionUID = 8890762715412718599L;

	public PlantillasTabla() {
		setModel(new PlantillasTablaModel());
		setRowHeight(25);
		setAutoCreateRowSorter(true);
		setAlignmentX(CENTER_ALIGNMENT);
	}

}
