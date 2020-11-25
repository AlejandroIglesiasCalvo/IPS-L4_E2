package ui.Ventas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.dto.Venta;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class DetallesVenta extends JFrame {

	private JPanel contentPane;
	private Venta v;
	private JLabel lblTitulo;
	private JButton btnCerrar;
	private JPanel pnlDetalles;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblTotal;
	private JTextField txtTotal;
	private JLabel lblTransporte;
	private JTextField txtFechaTransporte;
	private JLabel lblPresupuesto;
	private JTextField txtFechaPresupuesto;

	/**
	 * Create the frame.
	 * 
	 * @param venta
	 */
	public DetallesVenta(Venta venta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getBtnCerrar(), BorderLayout.SOUTH);
		contentPane.add(getPnlDetalles(), BorderLayout.CENTER);
		this.v = venta;
	}

	private void cerrar() {
		this.dispose();
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Detalles de la venta");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}

	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
		}
		return btnCerrar;
	}
	private JPanel getPnlDetalles() {
		if (pnlDetalles == null) {
			pnlDetalles = new JPanel();
			pnlDetalles.setLayout(new GridLayout(0, 2, 0, 0));
			pnlDetalles.add(getLblId());
			pnlDetalles.add(getTxtId());
			pnlDetalles.add(getLblFecha());
			pnlDetalles.add(getTxtFecha());
			pnlDetalles.add(getLblTotal());
			pnlDetalles.add(getTxtTotal());
			pnlDetalles.add(getLblTransporte());
			pnlDetalles.add(getTxtFechaTransporte());
			pnlDetalles.add(getLblPresupuesto());
			pnlDetalles.add(getTxtFechaPresupuesto());
		}
		return pnlDetalles;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("Id:");
		}
		return lblId;
	}
	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setColumns(10);
		}
		return txtId;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
		}
		return lblFecha;
	}
	private JTextField getTxtFecha() {
		if (txtFecha == null) {
			txtFecha = new JTextField();
			txtFecha.setEditable(false);
			txtFecha.setColumns(10);
		}
		return txtFecha;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total:");
		}
		return lblTotal;
	}
	private JTextField getTxtTotal() {
		if (txtTotal == null) {
			txtTotal = new JTextField();
			txtTotal.setEnabled(false);
			txtTotal.setEditable(false);
			txtTotal.setColumns(10);
		}
		return txtTotal;
	}
	private JLabel getLblTransporte() {
		if (lblTransporte == null) {
			lblTransporte = new JLabel("fecha del transporte:");
		}
		return lblTransporte;
	}
	private JTextField getTxtFechaTransporte() {
		if (txtFechaTransporte == null) {
			txtFechaTransporte = new JTextField();
			txtFechaTransporte.setEditable(false);
			txtFechaTransporte.setColumns(10);
		}
		return txtFechaTransporte;
	}
	private JLabel getLblPresupuesto() {
		if (lblPresupuesto == null) {
			lblPresupuesto = new JLabel("Fecha de creacion del presupuesto:");
		}
		return lblPresupuesto;
	}
	private JTextField getTxtFechaPresupuesto() {
		if (txtFechaPresupuesto == null) {
			txtFechaPresupuesto = new JTextField();
			txtFechaPresupuesto.setEditable(false);
			txtFechaPresupuesto.setColumns(10);
		}
		return txtFechaPresupuesto;
	}
}
