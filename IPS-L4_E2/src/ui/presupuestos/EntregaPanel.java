package ui.presupuestos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logic.CreaPresupuestoController;
import logic.VisualizadorEntregasController;
import logic.dto.Producto;
import logic.dto.ProductoCarrito;
import logic.dto.Transporte;

import java.awt.Dimension;


public class EntregaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblNameTrasportista;
	private JPanel container;
	private JButton btnModificar;
	private ProductoCarrito producto;
	private CreaPresupuestosView creaPresupuesto;
	private CreaPresupuestoController presController;
	private JLabel lblEntrega;
	private JLabel lblEstado;
	private Transporte trasporte;
	private JPanel pnEntregas;
	private VisualizadorEntregasController veController;
	private VisualizadorEntregasView veView;


	public EntregaPanel(Transporte t, JPanel pnEntregas, VisualizadorEntregasView visualizadorEntregasView,
			VisualizadorEntregasController veController) {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 4, 3, 3));
		add(getLblNameTrasportista());
		add(getLblEntrega());
		add(getLblEstado());
		add(getBtnModificar());
		this.setVisible(true);
		this.trasporte = t;
		this.pnEntregas = pnEntregas;
		this.veController = veController;
		this.veView = visualizadorEntregasView;
		getLblNameTrasportista().setText(t.getRepartidor().getNombre());
		getLblEntrega().setText(t.getID() + "");
		this.getLblEstado().setText(t.getEstado());
		if(t.getEstado().equals("RETRASADO")) {
			getLblEstado().setForeground(Color.RED);
		}
		if(t.getEstado().equals("FINALIZADO")) {
			getBtnModificar().setVisible(false);
		}
	}

	private JLabel getLblNameTrasportista() {
		if (lblNameTrasportista == null) {
			lblNameTrasportista = new JLabel("New label");
			lblNameTrasportista.setBackground(Color.WHITE);
			lblNameTrasportista.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNameTrasportista.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNameTrasportista;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modificarEstado();
				}
			});
			btnModificar.setToolTipText((String) null);
			btnModificar.setForeground(Color.BLACK);
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnModificar.setBackground(Color.LIGHT_GRAY);
		}
		return btnModificar;
	}

	protected void modificarEstado() {
		if(!this.trasporte.getEstado().equals("RETRASADO")) {
			veController.updateEstado(trasporte);
			refresh();
		}else {
			EntregasUI entregas = new EntregasUI(trasporte,veController,this);
			entregas.setModal(true);
			entregas.setVisible(true);
			entregas.setLocationRelativeTo(this);
		}
	}
	
	
	public void refresh() {
		JOptionPane.showMessageDialog(this, "Se ha cambiado el estado de la entrega");
		veView.refresh();
	}

	/**
	 * metodo que elimina un producto del presupuesto, mira si despues de eliminar el producto
	 * queda alguno y si no es as� desabilita el bot�n de crear presupuesto
	 */
	
	private JLabel getLblEntrega() {
		if (lblEntrega == null) {
			lblEntrega = new JLabel("New label");
			lblEntrega.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntrega.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblEntrega;
	}
	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("New label");
			lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblEstado;
	}
}
