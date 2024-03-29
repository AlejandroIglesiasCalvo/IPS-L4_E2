package ui.presupuestos.modelos;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import logic.dto.Cliente;

public class ClientesTablaModel extends AbstractTableModel {

	private static final long serialVersionUID = -5675393178142862583L;


	private String[] columnNames = { "DNI","Nombre", "Apellido", "Direccion", "Telefono"};
	protected List<Cliente> clientes;
	protected Class<?>[] types = new Class[] { String.class, String.class, String.class, String.class, String.class};

	public ClientesTablaModel() {
		
		clientes = new LinkedList<Cliente>();
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
		return clientes.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Cliente c = clientes.get(row);
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(2);
		switch(col) {
		case 0:
			return c.getID();
		case 1:
			return c.getNombre();
		case 2:
			return c.getApellidos();
		case 3:
			return c.getDireccion();
		case 4:
			return c.getTelefono();
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

	public Cliente getValueAtRow(int row) {
		Cliente c = clientes.get(row);
		return c;
	}

	public void clearRows() {
		clientes.clear();
		fireTableDataChanged();
	}

	public void addRow(Cliente c) {
		clientes.add(c);
		fireTableRowsInserted(clientes.size() - 1, clientes.size() - 1);
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
