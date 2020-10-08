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

import logic.CreaPresupuestoController;
import logic.dto.Producto;
import java.awt.Dimension;


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
	private CreaPresupuestoController presController;


	public ProductosPanel(Producto p, JPanel container, CreaPresupuestosView creaPresupuesto, CreaPresupuestoController presController) {
		setBackground(Color.WHITE);
		this.container = container;
		this.producto = p;
		this.creaPresupuesto = creaPresupuesto;
		this.presController = presController;
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getLblDescription());
		getLblDescription().setText(p.getNombre() + "-" + p.getTipo() + "-" + p.getPrecio());
		add(getBtnEliminar());
		this.setVisible(true);
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
			btnEliminiar.setForeground(Color.BLACK);
			btnEliminiar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnEliminiar.setBackground(Color.ORANGE);
		}
		return btnEliminiar;
	}

	/**
	 * metodo que elimina un producto del presupuesto, mira si despues de eliminar el producto
	 * queda alguno y si no es así desabilita el botón de crear presupuesto
	 */
	protected void eliminarProducto() {
		creaPresupuesto.getTxtTotal().setText(presController.updateTotalEliminarProduct(producto));
		//para que no se pueda crear un presupuesto, si no tienes
		//productos en el
		if(!presController.hasProductosEnCompra()) {
			creaPresupuesto.getBtnCreate().setEnabled(false);
		}
		container.remove(this);
		//hago esto para que se muestren los cambios en el panel
		container.setVisible(false);
		container.setVisible(true);
	}
}
