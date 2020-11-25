package ui.presupuestos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logic.EntregaController;
import logic.VisualizadorEntregasController;
import logic.gestionFechas;
import logic.dto.Presupuesto;
import logic.dto.Repartidor;
import logic.dto.Transporte;

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
	private JSpinner spnaño;
	private JSpinner spnHoras;
	private JSpinner spnMinutos;

	private Transporte transporte = null;
	private VisualizadorEntregasController veController;
	private EntregaPanel entregaPanel;

	/**
	 * Create the frame.
	 */
	// public EntregasUI(Presupuesto presupuesto, Venta venta) {
	public EntregasUI(Presupuesto presupuesto, Repartidor repartidor, int alli) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getPnlAbajo(), BorderLayout.SOUTH);
		contentPane.add(getPnlCentro(), BorderLayout.CENTER);
		//
		// ec = new EntregaController(presupuesto, venta);
		ec = new EntregaController(presupuesto);// Trampas mientras no este el resto
		ec.setRepartidor(repartidor);
		ec.setMontar(alli);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public EntregasUI(Transporte transporte, VisualizadorEntregasController veController, EntregaPanel entregaPanel) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getPnlAbajo(), BorderLayout.SOUTH);
		contentPane.add(getPnlCentro(), BorderLayout.CENTER);
		this.transporte = transporte;
		this.veController = veController;
		this.entregaPanel = entregaPanel;
		ec = new EntregaController();
		ec.setRepartidor(transporte.getRepartidor());
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
			spnDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		}
		return spnDia;
	}

	private JSpinner getSpnMes() {
		if (spnMes == null) {
			spnMes = new JSpinner();
			spnMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		}
		return spnMes;
	}

	private JSpinner getSpnAño() {

		if (spnaño == null) {
			spnaño = new JSpinner();
			spnaño.setModel(new SpinnerNumberModel(new Integer(2020), new Integer(2020), null, new Integer(1)));

		}

		return spnaño;

	}

	private JSpinner getSpnHoras() {
		if (spnHoras == null) {
			spnHoras = new JSpinner();
			spnHoras.setModel(new SpinnerNumberModel(8, 8, 24, 1));
		}
		return spnHoras;
	}

	private JSpinner getSpnMinutos() {
		if (spnMinutos == null) {
			spnMinutos = new JSpinner();
			spnMinutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		}
		return spnMinutos;
	}

	protected void aceptarEntrega() {
		gestionFechas fecha = new gestionFechas((Integer) spnaño.getValue(), (Integer) spnMes.getValue(),

				(Integer) spnDia.getValue(), (Integer) spnHoras.getValue(), (Integer) spnMinutos.getValue());
		Boolean valida = ec.comprobarFechaYHora((Integer) spnaño.getValue(), (Integer) spnMes.getValue(),

				(Integer) spnDia.getValue(), (Integer) spnHoras.getValue(), (Integer) spnMinutos.getValue());
		if (valida) {
			if (transporte == null && ec.Asignacion()) {
				JOptionPane.showMessageDialog(this, "Done");
			} else if (transporte != null && ec.ComprobarRepartidor()) {
				veController.setNuevaFechaEntrega(transporte, (Integer) spnaño.getValue(), (Integer) spnMes.getValue(),
						(Integer) spnDia.getValue(), (Integer) spnHoras.getValue(), (Integer) spnMinutos.getValue());
				entregaPanel.refresh();
			} else if (!fecha.no_es_domingo()) {
				JOptionPane.showMessageDialog(this, "Es domingo");

			} else {
				JOptionPane.showMessageDialog(this, "El repartidor no trabaja en ese horario, su horario es de:"
						+ ec.getRepartidor().getEntrada() + " a " + ec.getRepartidor().getSalida());
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Fecha no valida");
		}
	};
}
