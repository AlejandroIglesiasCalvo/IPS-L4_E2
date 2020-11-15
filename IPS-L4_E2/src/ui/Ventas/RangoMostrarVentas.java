package ui.Ventas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logic.gestionFechas;

public class RangoMostrarVentas extends JFrame {

	private JPanel contentPane;

	private gestionFechas inicio, fin;
	private JLabel lblTitulo;
	private JPanel pnlSur;
	private JButton btnCancelar;
	private JButton btnAcepatar;
	private JPanel pnlFin;
	private JLabel lblInicio;
	private JLabel lblFin;
	private JPanel pnlInicio;
	private JPanel panel;
	private JSpinner añoInicio;
	private JSpinner mesInicio;
	private JSpinner diaInicio;
	private JSpinner añoFin;
	private JSpinner mesFin;
	private JSpinner diaFin;

	/**
	 * Create the frame.
	 */
	public RangoMostrarVentas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getPnlSur(), BorderLayout.SOUTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Seleccione el rango del cual desea mostrar las ventas");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}

	private JPanel getPnlSur() {
		if (pnlSur == null) {
			pnlSur = new JPanel();
			pnlSur.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnlSur.add(getBtnCancelar());
			pnlSur.add(getBtnAcepatar());
		}
		return pnlSur;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
		}
		return btnCancelar;
	}

	private void cerrar() {
		this.dispose();
	}

	private JButton getBtnAcepatar() {
		if (btnAcepatar == null) {
			btnAcepatar = new JButton("Buscar");
			btnAcepatar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					if (busqueda(añoInicio.getValue(), mesInicio.getValue(), diaInicio.getValue(), añoFin.getValue(),
//							mesFin.getValue(), diaFin.getValue())) {
					inicio = new gestionFechas((Integer) añoInicio.getValue(), (Integer) mesInicio.getValue(),
							(Integer) diaInicio.getValue(), 00, 00);
					fin = new gestionFechas((Integer) añoFin.getValue(), (Integer) mesFin.getValue(),
							(Integer) diaFin.getValue(), 00, 00);
					ListarVentas listaVentas = new ListarVentas(inicio.getFecha(), fin.getFecha());
					listaVentas.setVisible(true);
					listaVentas.setLocationRelativeTo(null);
					cerrar();
					// }
				}

			});
		}
		return btnAcepatar;
	}

	private JPanel getPnlFin() {
		if (pnlFin == null) {
			pnlFin = new JPanel();
			pnlFin.setBounds(0, 234, 617, 67);
			pnlFin.setBorder(null);
			pnlFin.setLayout(new GridLayout(0, 4, 0, 0));
			pnlFin.add(getLblFin());
			pnlFin.add(getAñoFin());
			pnlFin.add(getMesFin());
			pnlFin.add(getDiaFin());
		}
		return pnlFin;
	}

	private JLabel getLblInicio() {
		if (lblInicio == null) {
			lblInicio = new JLabel("Inicio:");
		}
		return lblInicio;
	}

	private JLabel getLblFin() {
		if (lblFin == null) {
			lblFin = new JLabel("Fin:");
		}
		return lblFin;
	}

	private JPanel getPnlInicio() {
		if (pnlInicio == null) {
			pnlInicio = new JPanel();
			pnlInicio.setBounds(0, 177, 617, 57);
			pnlInicio.setLayout(new GridLayout(0, 4, 0, 0));
			pnlInicio.add(getLblInicio());
			pnlInicio.add(getAñoInicio());
			pnlInicio.add(getMesInicio());
			pnlInicio.add(getDiaInicio());
		}
		return pnlInicio;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getPnlInicio());
			panel.add(getPnlFin());
		}
		return panel;
	}

	private JSpinner getAñoInicio() {
		if (añoInicio == null) {
			añoInicio = new JSpinner();
		}
		return añoInicio;
	}

	private JSpinner getMesInicio() {
		if (mesInicio == null) {
			mesInicio = new JSpinner();
			mesInicio.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		}
		return mesInicio;
	}

	private JSpinner getDiaInicio() {
		if (diaInicio == null) {
			diaInicio = new JSpinner();
			diaInicio.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		}
		return diaInicio;
	}

	private JSpinner getAñoFin() {
		if (añoFin == null) {
			añoFin = new JSpinner();
		}
		return añoFin;
	}

	private JSpinner getMesFin() {
		if (mesFin == null) {
			mesFin = new JSpinner();
			mesFin.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		}
		return mesFin;
	}

	private JSpinner getDiaFin() {
		if (diaFin == null) {
			diaFin = new JSpinner();
			diaFin.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		}
		return diaFin;
	}

}