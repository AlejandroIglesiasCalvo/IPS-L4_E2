package ui.presupuestos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logic.dto.Producto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class CatalogoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblDescription;
	private JPanel container;
	private JButton btnAadir;
	private Producto producto;

	public CatalogoPanel(Producto p, JPanel container) {
		setBackground(Color.WHITE);
		this.container = container;
		this.producto = p;
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
		
	}
	
}
