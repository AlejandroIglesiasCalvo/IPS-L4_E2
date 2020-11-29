package ui.almacen.modelos;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import logic.dto.Cliente;
import logic.dto.Producto;
import logic.dto.Producto_Almacen;

public class InventarioTablaModel extends AbstractTableModel {

	private static final long serialVersionUID = -5675393178142862583L;


	private String[] columnNames = { "ID Producto","Nombre", "Tipo", "Precio/Unidad", "Unidades"};
	protected List<Producto_Almacen> productos;
	protected Class<?>[] types = new Class[] { String.class, String.class, String.class, Double.class, Integer.class};

	public InventarioTablaModel() {
		
		productos = new LinkedList<Producto_Almacen>();
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
		return productos.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Producto_Almacen c = productos.get(row);
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(2);
		switch(col) {
		case 0:
			return c.getID();
		case 1:
			return c.getNombre();
		case 2:
			return c.getTipo();
		case 3:
			return c.getPrecio();
		case 4:
			return c.getStock();
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

	public Producto_Almacen getValueAtRow(int row) {
		Producto_Almacen c = productos.get(row);
		return c;
	}

	public void clearRows() {
		productos.clear();
		fireTableDataChanged();
	}

	public void addRow(Producto_Almacen c) {
		productos.add(c);
		fireTableRowsInserted(productos.size() - 1, productos.size() - 1);
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
