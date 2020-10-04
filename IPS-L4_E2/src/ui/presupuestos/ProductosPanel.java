package ui.presupuestos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ProductosPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblDescription;
	private JPanel container;
	private ResourceBundle texts;
	private Locale locale;
	private JButton btnAadir;


	public ProductosPanel(JPanel container, Locale locale) {
		this.locale = locale;
		texts = ResourceBundle.getBundle("rcs/text", locale);
		setBackground(Color.WHITE);
		this.container = container;
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getLblDescription());
		getLblDescription().setText("no implementado todavía");
		add(getBtnAdd_1());
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
	private JButton getBtnAdd_1() {
		if (btnAadir == null) {
			btnAadir = new JButton("ELIMINAR");
			btnAadir.setToolTipText((String) null);
			btnAadir.setForeground(Color.WHITE);
			btnAadir.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAadir.setBackground(Color.RED);
		}
		return btnAadir;
	}
}
