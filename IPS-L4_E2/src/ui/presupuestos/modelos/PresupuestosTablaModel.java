package ui.presupuestos.modelos;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import logic.AceptarPresupuestoController;
import logic.dto.Cliente;
import logic.dto.Presupuesto;


public class PresupuestosTablaModel extends AbstractTableModel {

	private static final long serialVersionUID = -5675393178142862583L;


	private String[] columnNames = { "ID Presupuesto","Cliente", "Fecha"};
	protected List<Presupuesto> presupustos;
	protected List<Cliente> clientes;
	protected Class<?>[] types = new Class[] { String.class, String.class, LocalDateTime.class};
	
	private AceptarPresupuestoController g = new AceptarPresupuestoController();

	public PresupuestosTablaModel() {
		
		presupustos = new LinkedList<>();
		clientes = new LinkedList<>();
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
		return presupustos.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Presupuesto p = presupustos.get(row);
		Cliente c  = g.getClienteById(p.getDNI_Cliente());
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(2);
		switch(col) {
		case 0:
			return p.getID_Presupuesto();
		case 1:
			return c.getNombre() + " " + c.getApellidos();
		case 2:
			return p.getFecha();
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

	public Presupuesto getValueAtRow(int row) {
		Presupuesto p = presupustos.get(row);
		return p;
	}

	public void clearRows() {
		presupustos.clear();
		fireTableDataChanged();
	}

	public void addRow(Presupuesto p, Cliente c) {
		presupustos.add(p);
		clientes.add(c);
		fireTableRowsInserted(presupustos.size() - 1, presupustos.size() - 1);
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
