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

	private JLabel lblName;
	private JPanel container;
	private JButton btnAadir;
	private Producto producto;
	private CreaPresupuestosView creaPresupuesto;
	private CreaPresupuestoController presController;
	private JLabel lblTipo;
	private JLabel lblPrecio;

	public CatalogoPanel(Producto p, JPanel container, CreaPresupuestosView creaPresupuesto,
			CreaPresupuestoController presController) {
		setMinimumSize(new Dimension(450, 70));
		setMaximumSize(new Dimension(32767, 70));
		setBackground(Color.WHITE);
		this.container = container;
		this.producto = p;
		this.creaPresupuesto = creaPresupuesto;
		this.presController = presController;
		setLayout(new GridLayout(0, 4, 0, 0));
		add(getLblName());
		add(getLblTipo());
		add(getLblPrecio());
		add(getBtnAdd());
		getLblName().setText(p.getNombre());
		getLblTipo().setText(p.getTipo());
		getLblPrecio().setText(Double.toString(p.getPrecio()));
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("New label");
			lblName.setBackground(Color.WHITE);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblName;
	}

	private JButton getBtnAdd() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
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
		creaPresupuesto.addToPresupuesto(producto,this);
		this.btnAadir.setEnabled(false);
	}
	
	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("");
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTipo;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("");
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrecio;
	}
	
	public JButton getBtnAniadir() {
		return this.btnAadir;
	}
}
