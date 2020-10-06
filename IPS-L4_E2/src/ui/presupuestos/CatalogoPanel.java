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



public class CatalogoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblDescription;
	private JPanel container;
	private JButton btnAadir;
	private Producto producto;
	private CreaPresupuestosView creaPresupuesto;
	private PresupuestoController presController;

	public CatalogoPanel(Producto p, JPanel container, CreaPresupuestosView creaPresupuesto, PresupuestoController presController) {
		setBackground(Color.WHITE);
		this.container = container;
		this.producto = p;
		this.creaPresupuesto = creaPresupuesto;
		this.presController = presController;
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getLblDescription());
		getLblDescription().setText(p.getNombre() + "-" + p.getTipo() + "-" + p.getPrecio());
		add(getBtnAdd());
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
	
	private JButton getBtnAdd() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00D1ADIR");
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirProducto();
				}
			});
			btnAadir.setToolTipText((String) null);
			btnAadir.setForeground(Color.WHITE);
			btnAadir.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAadir.setBackground(new Color(0, 128, 0));
		}
		return btnAadir;
	}

	protected void añadirProducto() {
		creaPresupuesto.getTxtTotal().setText(presController.updateTotalAddProduct(producto));	
		creaPresupuesto.getPnPresupProducts().add(new ProductosPanel(producto, container, creaPresupuesto, presController));
	}
	
}
