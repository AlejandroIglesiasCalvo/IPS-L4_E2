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
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizarPedidosView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PedidosTabla tabla;
	private JScrollPane scrollPane;
	private PedidosController controller = new PedidosController();
	private JPanel panel;
	private JButton btnInfoPedido;

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
		contentPanel.add(getPanel(), BorderLayout.NORTH);
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
		for(Pedido p : controller.getListaPedidos()) {
			m.addRow(p);
		}
		validate();
	}
	
	public PedidosController getController() {
		return controller;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panel.add(getBtnInfoPedido());
		}
		return panel;
	}
	private JButton getBtnInfoPedido() {
		if (btnInfoPedido == null) {
			btnInfoPedido = new JButton("Informacion del Pedido");
			btnInfoPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = getTabla().getSelectedRow();
					Pedido p =((PedidosTablaModel)getTabla().getModel()).getValueAtRow(index);
					InfoPedidoView i = new InfoPedidoView(p);
					i.setVisible(true);
					i.setLocationRelativeTo(null);
				}
			});
		}
		return btnInfoPedido;
	}
}
