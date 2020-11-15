package ui.Ventas.Modelos;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import logic.dto.Venta;

public class TablaVentasModel extends AbstractTableModel {

	private static final long serialVersionUID = -5675393178142862583L;

	private String[] columnNames = { "Id de la venta", "Fecha", "Precio total" };
	protected List<Venta> ventas;
	protected Class<?>[] types = new Class[] { String.class, String.class, String.class };

	public TablaVentasModel() {

		ventas = new LinkedList<Venta>();
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
		return ventas.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Venta c = ventas.get(row);
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(2);
		switch (col) {
		case 0:
			return c.getID();
		case 1:
			return c.getFecha();
		case 2:
			return c.getTotal();
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

	public Venta getValueAtRow(int row) {
		Venta c = ventas.get(row);
		return c;
	}

	public void clearRows() {
		ventas.clear();
		fireTableDataChanged();
	}

	public void addRow(Venta c) {
		ventas.add(c);
		fireTableRowsInserted(ventas.size() - 1, ventas.size() - 1);
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