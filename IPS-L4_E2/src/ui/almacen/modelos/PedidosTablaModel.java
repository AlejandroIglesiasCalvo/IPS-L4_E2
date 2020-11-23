package ui.almacen.modelos;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import logic.dto.Cliente;
import logic.dto.Pedido;
import logic.dto.PedidoFecha;
import logic.dto.Producto;
import logic.dto.ProductoPedido;
import logic.dto.Producto_Almacen;
import ui.almacen.VisualizarPedidosView;

public class PedidosTablaModel extends AbstractTableModel {

	private static final long serialVersionUID = -5675393178142862583L;


	private String[] columnNames = { "ID Presupuesto","Fecha", "Estado"};
	protected List<PedidoFecha> pedidos;
	protected Class<?>[] types = new Class[] { String.class, String.class, String.class};
	protected VisualizarPedidosView view;

	public PedidosTablaModel(VisualizarPedidosView view) {
		
		pedidos = new LinkedList<PedidoFecha>();
		this.view = view;
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if(column == 2) {
			return true;
		}
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
		return pedidos.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		PedidoFecha c = pedidos.get(row);
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(2);
		switch(col) {
		case 0:
			return c.getID();
		case 1:
			return c.getDate().toString();// c.getDate();
		case 2:
			//System.out.println("aqui");
			return c.getEstado();
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

	public Object getValueAtRow(int row) {
		//PedidoFecha c = productos.get(row);
		return null;
	}
	
	public boolean getState(int row) {
		PedidoFecha p = pedidos.get(row);
		
		
		return (p.getEstado().equals("solicitado"));
	}

	public void clearRows() {
		pedidos.clear();
		fireTableDataChanged();
	}

	public void addRow(Pedido c) {
		pedidos.add(new PedidoFecha(c, LocalDateTime.now()));
		fireTableRowsInserted(pedidos.size() - 1, pedidos.size() - 1);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int colIndex) {
		if (rowIndex < 0 || rowIndex >= getRowCount())
			throw new IllegalArgumentException("row out of bounds");
		if (colIndex < 0 || colIndex >= getColumnCount())
			throw new IllegalArgumentException("column out of bounds");
		
		PedidoFecha p = pedidos.get(rowIndex);
		switch (colIndex) {
		case 2: {
			p.setEstado((String)aValue);
			fireTableCellUpdated(rowIndex, colIndex);
			//llamar el update del estado en la base
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + colIndex);
		}
		
		fireTableRowsUpdated(rowIndex, rowIndex);

	}
	
	public void changeState(int row, String state) {
		pedidos.get(row).setEstado(state);
	}
	
	public VisualizarPedidosView getView() {
		return view;
		
	}
}
