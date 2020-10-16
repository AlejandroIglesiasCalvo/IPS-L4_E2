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
	private CreaPresupuestoController presController;

	public CatalogoPanel(Producto p, JPanel container, CreaPresupuestosView creaPresupuesto,
			CreaPresupuestoController presController) {
		setMinimumSize(new Dimension(450, 70));
		setMaximumSize(new Dimension(32767, 70));
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

	/**
	 * metodo que a�ade le producto al presupuesto y hace que se pueda ver y hacer
	 * click al bot�n de crear presupuesto, porque si no tienes productos para hacer
	 * un presupuesto, no lo puedes crear vac�o
	 */
	protected void añadirProducto() {
		creaPresupuesto.getTxtTotal().setText(presController.updateTotalAddProduct(producto));
		creaPresupuesto.getBtnCreate().setEnabled(true);
		creaPresupuesto.addToPresupuesto(producto);
	}

}
