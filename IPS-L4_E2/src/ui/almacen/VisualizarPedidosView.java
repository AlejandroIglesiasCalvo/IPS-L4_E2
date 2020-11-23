package ui.almacen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.PedidosController;
import logic.dto.Pedido;
import logic.dto.PedidoFecha;
import ui.almacen.modelos.PedidosTabla;
import ui.almacen.modelos.PedidosTablaModel;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class VisualizarPedidosView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PedidosTabla tabla;
	private JScrollPane scrollPane;
	private PedidosController controller = new PedidosController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarPedidosView dialog = new VisualizarPedidosView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarPedidosView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getScrollPane(), BorderLayout.CENTER);
		
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTabla());
		}
		return scrollPane;
	}
	
	private PedidosTabla getTabla() {
		if(this.tabla == null) {
			tabla = new PedidosTabla(this);
			fillTabla();
		}
		return tabla;
	}
	
	private void fillTabla() {
		PedidosTablaModel m = (PedidosTablaModel) tabla.getModel();
		for(PedidoFecha p : controller.getListaPedidos()) {
			m.addRow(p);
		}
		validate();
	}
	
	public PedidosController getController() {
		return controller;
	}
}
