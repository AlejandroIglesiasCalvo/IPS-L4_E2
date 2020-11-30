package ui.presupuestos.modelos;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import logic.dto.Cliente;
import logic.dto.Plantilla;

public class PlantillasTablaModel extends AbstractTableModel {

	private static final long serialVersionUID = -5675393178142862583L;


	private String[] columnNames = { "Nombre", "Precio"};
	protected List<Plantilla> plantillas;
	protected Class<?>[] types = new Class[] { String.class, Double.class};

	public PlantillasTablaModel() {
		
		plantillas = new LinkedList<>();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public String getColumnName(int index) {
		return columnNames[index];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return plantillas.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Plantilla c = plantillas.get(row);
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(2);
		switch(col) {
		case 0:
			return c.getNombre();
		case 1:
			return c.getPrecio();
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

	public Plantilla getValueAtRow(int row) {
		Plantilla c = plantillas.get(row);
		return c;
	}

	public void clearRows() {
		plantillas.clear();
		fireTableDataChanged();
	}

	public void addRow(Plantilla c) {
		plantillas.add(c);
		fireTableRowsInserted(plantillas.size() - 1, plantillas.size() - 1);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int colIndex) {
		if (rowIndex < 0 || rowIndex >= getRowCount())
			throw new IllegalArgumentException("row out of bounds");
		if (colIndex < 0 || colIndex >= getColumnCount())
			throw new IllegalArgumentException("column out of bounds");
		
		fireTableRowsUpdated(rowIndex, rowIndex);

	}


}
