package ui.presupuestos;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class PresupuestosView {

	private JFrame frmPresupuesto;
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
	private JPanel pnInfo;
	private JLabel lblCatalogo;
	private JLabel lblProductosPresupuesto;

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
		frmPresupuesto = new JFrame();
		frmPresupuesto.setTitle("Presupuesto");
		frmPresupuesto.setName("Presupuesto\r\n");
		frmPresupuesto.getContentPane().setBackground(Color.WHITE);
		frmPresupuesto.getContentPane().add(getPnListas(), BorderLayout.CENTER);
		frmPresupuesto.getContentPane().add(getPnButtons(), BorderLayout.SOUTH);
		frmPresupuesto.getContentPane().add(getPnInfo(), BorderLayout.NORTH);
		frmPresupuesto.setBounds(100, 100, 831, 571);
		frmPresupuesto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JPanel getPnListas() {
		if (pnListas == null) {
			pnListas = new JPanel();
			pnListas.setBackground(Color.WHITE);
			pnListas.setLayout(new GridLayout(0, 2, 3, 3));
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
			pnCatProductos.setLayout(new GridLayout(0, 1, 3, 3));
		}
		return pnCatProductos;
	}
	private JPanel getPnPreProductos() {
		if (pnPreProductos == null) {
			pnPreProductos = new JPanel();
			pnPreProductos.setBackground(Color.WHITE);
			pnPreProductos.setLayout(new GridLayout(0, 1, 3, 3));
		}
		return pnPreProductos;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
			txtPrecioTotal.setBackground(Color.WHITE);
			txtPrecioTotal.setEditable(false);
			txtPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtPrecioTotal.setText("Precio total:");
			txtPrecioTotal.setColumns(10);
		}
		return txtPrecioTotal;
	}
	
	public JFrame getFrame() { return this.frmPresupuesto; }
	public JScrollPane getCatalogo() {return this.spCatalogo;}
	public JScrollPane getPresupuesto() {return this.spPresupuesto;}
	public JPanel getButtons() {return this.pnButtons;}
	public JButton getCancelar() {return this.btnCancelar;}
	public JButton getSiguiente() {return this.btnSiguiente;}
	public JPanel getPnCateg() {return this.pnCatProductos;}
	public JPanel getPnPres() {return this.pnPreProductos;}
	public JTextField getPrecio() {return this.txtPrecioTotal;}
	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setLayout(new GridLayout(0, 2, 3, 3));
			pnInfo.add(getLblCatalogo());
			pnInfo.add(getLblProductosPresupuesto());
		}
		return pnInfo;
	}
	private JLabel getLblCatalogo() {
		if (lblCatalogo == null) {
			lblCatalogo = new JLabel("Productos cat\u00E1logo");
			lblCatalogo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblCatalogo.setBackground(Color.WHITE);
		}
		return lblCatalogo;
	}
	private JLabel getLblProductosPresupuesto() {
		if (lblProductosPresupuesto == null) {
			lblProductosPresupuesto = new JLabel("Productos a comprar");
			lblProductosPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblProductosPresupuesto.setBackground(Color.WHITE);
		}
		return lblProductosPresupuesto;
	}
}
