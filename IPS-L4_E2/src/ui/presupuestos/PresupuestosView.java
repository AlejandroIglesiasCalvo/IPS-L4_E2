package ui.presupuestos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PresupuestosView {

	private JFrame frame;
	private JPanel pnListas;
	private JScrollPane spCatalogo;
	private JScrollPane spPresupuesto;
	private JPanel pnButtons;
	private JButton btnSiguiente;
	private JButton btnCancelar;
	private JPanel pnCatProductos;
	private JPanel pnPreProductos;
	private JTextField textField;
	private JTextField txtPrecioTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PresupuestosView window = new PresupuestosView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PresupuestosView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().add(getPnListas(), BorderLayout.CENTER);
		frame.getContentPane().add(getPnButtons(), BorderLayout.SOUTH);
		frame.setBounds(100, 100, 831, 571);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JPanel getPnListas() {
		if (pnListas == null) {
			pnListas = new JPanel();
			pnListas.setBackground(Color.WHITE);
			pnListas.setLayout(new BoxLayout(pnListas, BoxLayout.X_AXIS));
			pnListas.add(getSpCatalogo());
			pnListas.add(getSpPresupuesto());
		}
		return pnListas;
	}
	private JScrollPane getSpCatalogo() {
		if (spCatalogo == null) {
			spCatalogo = new JScrollPane();
			spCatalogo.setViewportView(getPnCatProductos());
		}
		return spCatalogo;
	}
	private JScrollPane getSpPresupuesto() {
		if (spPresupuesto == null) {
			spPresupuesto = new JScrollPane();
			spPresupuesto.setViewportView(getPnPreProductos());
		}
		return spPresupuesto;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.WHITE);
			pnButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnButtons.add(getTxtPrecioTotal());
			pnButtons.add(getTextField());
			pnButtons.add(getBtnSiguiente());
			pnButtons.add(getBtnCancelar());
		}
		return pnButtons;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("SIGUIENTE");
			btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setBackground(new Color(50, 205, 50));
			btnSiguiente.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnSiguiente;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("CANCELAR");
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBackground(new Color(255, 0, 0));
		}
		return btnCancelar;
	}
	private JPanel getPnCatProductos() {
		if (pnCatProductos == null) {
			pnCatProductos = new JPanel();
			pnCatProductos.setBackground(Color.WHITE);
			pnCatProductos.setForeground(Color.BLACK);
		}
		return pnCatProductos;
	}
	private JPanel getPnPreProductos() {
		if (pnPreProductos == null) {
			pnPreProductos = new JPanel();
			pnPreProductos.setBackground(Color.WHITE);
		}
		return pnPreProductos;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textField.setBackground(Color.WHITE);
			textField.setEditable(false);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setText("0,0");
			textField.setColumns(10);
		}
		return textField;
	}
	private JTextField getTxtPrecioTotal() {
		if (txtPrecioTotal == null) {
			txtPrecioTotal = new JTextField();
			txtPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPrecioTotal.setText("Precio total:");
			txtPrecioTotal.setColumns(10);
		}
		return txtPrecioTotal;
	}
}
