package ui.almacen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataBase.GestionInventario;
import logic.InventarioController;
import logic.dto.Producto_Almacen;
import ui.almacen.modelos.InventarioTabla;
import ui.almacen.modelos.InventarioTablaModel;

import javax.swing.JScrollPane;

public class VisualizarInventarioView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private InventarioTabla tabla;
	
	private InventarioController controller = new InventarioController();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarInventarioView dialog = new VisualizarInventarioView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarInventarioView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			contentPanel.add(getScrollPane(), BorderLayout.CENTER);
		}
	}

	public JScrollPane getScrollPane() {
		if(scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
			
			
		}
		return scrollPane;
	}
	
	public InventarioTabla getTable() {
		if(tabla == null) {
			tabla = new InventarioTabla();
			fillModel();
		}
		return tabla;
	}
	
	public void fillModel() {
		InventarioTablaModel model = (InventarioTablaModel)getTable().getModel();
		model.clearRows();
		List<Producto_Almacen> p = controller.getStock();
		
		p.stream().forEach(pa -> model.addRow(pa));
		
	}

}
