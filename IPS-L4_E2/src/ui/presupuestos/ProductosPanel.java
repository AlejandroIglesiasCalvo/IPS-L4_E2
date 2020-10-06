package ui.presupuestos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logic.PresupuestoController;
import logic.dto.Producto;


public class ProductosPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblDescription;
	private JPanel container;
	private JButton btnEliminiar;
	private Producto producto;
	private CreaPresupuestosView creaPresupuesto;
	private PresupuestoController presController;


	public ProductosPanel(Producto p, JPanel container, CreaPresupuestosView creaPresupuesto, PresupuestoController presController) {
		setBackground(Color.WHITE);
		this.container = container;
		this.producto = p;
		this.creaPresupuesto = creaPresupuesto;
		this.presController = presController;
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getLblDescription());
		getLblDescription().setText(p.getNombre() + "-" + p.getTipo() + "-" + p.getPrecio());
		add(getBtnEliminar());
	}

	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("New label");
			lblDescription.setBackground(Color.WHITE);
			lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDescription;
	}
	private JButton getBtnEliminar() {
		if (btnEliminiar == null) {
			btnEliminiar = new JButton("ELIMINAR");
			btnEliminiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarProducto();
				}
			});
			btnEliminiar.setToolTipText((String) null);
			btnEliminiar.setForeground(Color.WHITE);
			btnEliminiar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnEliminiar.setBackground(Color.RED);
		}
		return btnEliminiar;
	}

	protected void eliminarProducto() {
		creaPresupuesto.getTxtTotal().setText(presController.updateTotalEliminarProduct(producto));
		container.remove(this);
	}
}
