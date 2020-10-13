package ui.presupuestos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import logic.EntregaController;
import logic.dto.Presupuesto;
import logic.dto.Venta;

public class EntregasUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel pnlAbajo;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JPanel pnlCentro;
	private JPanel pnlFecha;
	private JPanel pnlHora;
	private JLabel lblFecha;
	private JLabel lblHora;
	private EntregaController ec;
	private JSpinner spnDia;
	private JSpinner spnMes;
	private JSpinner spnAño;
	private JSpinner spnHoras;
	private JSpinner spnMinutos;

	/**
	 * Create the frame.
	 */
	public EntregasUI(Presupuesto presupuesto, Venta venta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getPnlAbajo(), BorderLayout.SOUTH);
		contentPane.add(getPnlCentro(), BorderLayout.CENTER);
		//
		ec = new EntregaController(presupuesto, venta);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Seleccion de decha apra la entrega");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}

	private JPanel getPnlAbajo() {
		if (pnlAbajo == null) {
			pnlAbajo = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlAbajo.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlAbajo.add(getBtnAceptar());
			pnlAbajo.add(getBtnCancelar());
		}
		return pnlAbajo;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Calcelar");
		}
		return btnCancelar;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aceptarEntrega();
				}
			});
		}
		return btnAceptar;
	}

	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel();
			pnlCentro.setLayout(null);
			pnlCentro.add(getPnlFecha());
			pnlCentro.add(getPnlHora());
		}
		return pnlCentro;
	}

	private JPanel getPnlFecha() {
		if (pnlFecha == null) {
			pnlFecha = new JPanel();
			pnlFecha.setBounds(10, 88, 622, 30);
			pnlFecha.setLayout(new GridLayout(0, 4, 0, 0));
			pnlFecha.add(getLblFecha());
			pnlFecha.add(getSpnDia());
			pnlFecha.add(getSpnMes());
			pnlFecha.add(getSpnAño());
		}
		return pnlFecha;
	}

	private JPanel getPnlHora() {
		if (pnlHora == null) {
			pnlHora = new JPanel();
			pnlHora.setBounds(10, 118, 622, 30);
			pnlHora.setLayout(new GridLayout(0, 3, 0, 0));
			pnlHora.add(getLblHora());
			pnlHora.add(getSpnHoras());
			pnlHora.add(getSpnMinutos());
		}
		return pnlHora;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha (dd/mm/aaaa)");
		}
		return lblFecha;
	}

	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora (hh:mm)");
		}
		return lblHora;
	}

	private JSpinner getSpnDia() {
		if (spnDia == null) {
			spnDia = new JSpinner();
		}
		return spnDia;
	}

	private JSpinner getSpnMes() {
		if (spnMes == null) {
			spnMes = new JSpinner();
		}
		return spnMes;
	}

	private JSpinner getSpnAño() {
		if (spnAño == null) {
			spnAño = new JSpinner();
		}
		return spnAño;
	}

	private JSpinner getSpnHoras() {
		if (spnHoras == null) {
			spnHoras = new JSpinner();
		}
		return spnHoras;
	}

	private JSpinner getSpnMinutos() {
		if (spnMinutos == null) {
			spnMinutos = new JSpinner();
		}
		return spnMinutos;
	}

	protected void aceptarEntrega() {
		Boolean valida = ec.comprobarFechaYHora((Integer) spnAño.getValue(), (Integer) spnMes.getValue(),
				(Integer) spnDia.getValue(), (Integer) spnHoras.getValue(), (Integer) spnMinutos.getValue());
		if (valida) {
			ec.Asignacion();
		} else {
			JOptionPane.showConfirmDialog(this, "Error", "Fecha no valida", ERROR, ERROR);
		}
	}
}
