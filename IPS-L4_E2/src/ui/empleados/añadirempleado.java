package ui.empleados;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logic.TrabajadorController;

public class añadirempleado extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel pnlSur;
	private JButton btnCancelar;
	private JButton btnAñadir;
	private JPanel panel;
	private JPanel pnlNombreApellidos;
	private JPanel pnlDniTlfPuesto;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JTextField txtNombre;
	private JLabel lblDni;
	private JLabel lblTelefono;
	private JLabel lblPuesto;
	private JComboBox cbPuesto;
	private JTextField txtDni;
	private JTextField txtTlf;
	private JPanel pnlHorario;
	private JLabel lblEntrada;
	private JLabel lblSalida;
	private JSpinner spnEntrada;
	private JSpinner spnSalida;
	TrabajadorController tc;
	private JSpinner spnEntradaMinutos;
	private JSpinner spnSalidaMinutos;

	/**
	 * Create the frame.
	 */
	public añadirempleado() {
		tc = new TrabajadorController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 437);
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
			lblTitulo = new JLabel("Añadir empleado");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}

	private JPanel getPnlSur() {
		if (pnlSur == null) {
			pnlSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlSur.add(getBtnCancelar());
			pnlSur.add(getBtnAñadir());
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

	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("Añadir");
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobar();
				}
			});
		}
		return btnAñadir;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getPnlNombreApellidos());
			panel.add(getPnlDniTlfPuesto());
			panel.add(getPnlHorario());
		}
		return panel;
	}

	private JPanel getPnlNombreApellidos() {
		if (pnlNombreApellidos == null) {
			pnlNombreApellidos = new JPanel();
			pnlNombreApellidos.setBounds(10, 53, 651, 51);
			pnlNombreApellidos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnlNombreApellidos.add(getLblNombre());
			pnlNombreApellidos.add(getTxtNombre());
			pnlNombreApellidos.add(getLblApellidos());
			pnlNombreApellidos.add(getTxtApellidos());
		}
		return pnlNombreApellidos;
	}

	private JPanel getPnlDniTlfPuesto() {
		if (pnlDniTlfPuesto == null) {
			pnlDniTlfPuesto = new JPanel();
			pnlDniTlfPuesto.setBounds(10, 125, 651, 62);
			pnlDniTlfPuesto.add(getLblDni());
			pnlDniTlfPuesto.add(getTxtDni());
			pnlDniTlfPuesto.add(getLblTelefono());
			pnlDniTlfPuesto.add(getTxtTlf());
			pnlDniTlfPuesto.add(getLblPuesto());
			pnlDniTlfPuesto.add(getCbPuesto());
		}
		return pnlDniTlfPuesto;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
		}
		return lblNombre;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
		}
		return lblApellidos;
	}

	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setColumns(10);
		}
		return txtApellidos;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("Dni:");
		}
		return lblDni;
	}

	private JLabel getLblTelefono() {
		if (lblTelefono == null) {
			lblTelefono = new JLabel("Telefono:");
		}
		return lblTelefono;
	}

	private JLabel getLblPuesto() {
		if (lblPuesto == null) {
			lblPuesto = new JLabel("Puesto:");
		}
		return lblPuesto;
	}

	private JComboBox getCbPuesto() {
		if (cbPuesto == null) {
			cbPuesto = new JComboBox();
			cbPuesto.setModel(new DefaultComboBoxModel(new String[] { "Transporte", "Ventas", "Almacén" }));
		}
		return cbPuesto;
	}

	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setColumns(10);
		}
		return txtDni;
	}

	private JTextField getTxtTlf() {
		if (txtTlf == null) {
			txtTlf = new JTextField();
			txtTlf.setColumns(10);
		}
		return txtTlf;
	}

	private JPanel getPnlHorario() {
		if (pnlHorario == null) {
			pnlHorario = new JPanel();
			pnlHorario.setBounds(10, 206, 651, 76);
			pnlHorario.add(getLblEntrada());
			pnlHorario.add(getSpnEntrada());
			pnlHorario.add(getSpnEntradaMinutos());
			pnlHorario.add(getLblSalida());
			pnlHorario.add(getSpnSalida());
			pnlHorario.add(getSpnSalidaMinutos());
		}
		return pnlHorario;
	}

	private JLabel getLblEntrada() {
		if (lblEntrada == null) {
			lblEntrada = new JLabel("Hora de entrada:");
		}
		return lblEntrada;
	}

	private JLabel getLblSalida() {
		if (lblSalida == null) {
			lblSalida = new JLabel("Hora de salida: ");
		}
		return lblSalida;
	}

	private JSpinner getSpnEntrada() {
		if (spnEntrada == null) {
			spnEntrada = new JSpinner();
			spnEntrada.setModel(new SpinnerNumberModel(8, 8, 22, 1));
		}
		return spnEntrada;
	}

	private JSpinner getSpnSalida() {
		if (spnSalida == null) {
			spnSalida = new JSpinner();
			spnSalida.setModel(new SpinnerNumberModel(8, 8, 22, 1));
		}
		return spnSalida;
	}

	private void comprobar() {
		if (txtNombre.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Nombre mal");
		}
		if (txtApellidos.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Apellidos mal");
		}
		if (txtDni.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Dni mal");
		}
		if (txtTlf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Telefono mal");
		}
		String puesto = "";
		if (cbPuesto.getSelectedIndex() == 0) {
			puesto = "REPARTIDOR";
		}
		if (cbPuesto.getSelectedIndex() == 1) {
			puesto = "VENTAS";
		}
		if (cbPuesto.getSelectedIndex() == 2) {
			puesto = "ALMACEN";
		}
		if (tc.addTrabajador(txtNombre.getText(), txtApellidos.getText(), txtTlf.getText(),
				spnEntrada.getValue().toString() + "." + spnEntradaMinutos.getValue().toString(),
				spnSalida.getValue().toString() + "." + spnSalidaMinutos.getValue().toString(), puesto,
				txtDni.getText())) {
			JOptionPane.showMessageDialog(this, "Listo");
			cerrar();
		} else {
			JOptionPane.showMessageDialog(this, "Error");
		}
	}

	private JSpinner getSpnEntradaMinutos() {
		if (spnEntradaMinutos == null) {
			spnEntradaMinutos = new JSpinner();
			spnEntradaMinutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		}
		return spnEntradaMinutos;
	}

	private JSpinner getSpnSalidaMinutos() {
		if (spnSalidaMinutos == null) {
			spnSalidaMinutos = new JSpinner();
			spnSalidaMinutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		}
		return spnSalidaMinutos;
	}
}
