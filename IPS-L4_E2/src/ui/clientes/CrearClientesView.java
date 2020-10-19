package ui.clientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import logic.CreaClienteController;
import logic.CreaPresupuestoController;
import logic.dto.Cliente;
import ui.presupuestos.AsignarClientePresupuestoView;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CrearClientesView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JLabel lblDNI;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblTelefono;
	private JPanel pnTextFields;
	private Cliente cliente;
	private JButton btnCancelar;
	private JButton btnCrearCliente;
	
	private CreaClienteController controller = new CreaClienteController();
	private AsignarClientePresupuestoView asigC;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			CrearClientesView dialog = new CrearClientesView();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public CrearClientesView(AsignarClientePresupuestoView asig) {
		this.asigC = asig;
		setTitle("Crear Nuevo Cliente");
		setResizable(false);
		setBounds(100, 100, 758, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getPnTextFields());
		contentPanel.add(getBtnCancelar());
		contentPanel.add(getBtnCrearCliente());
		
	}
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setBounds(93, 25, 480, 37);
			txtDNI.setColumns(10);
		}
		return txtDNI;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(93, 92, 480, 37);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setBounds(93, 158, 480, 37);
			txtApellidos.setColumns(10);
		}
		return txtApellidos;
	}
	private JTextField getTxtTelefono() {
		if (txtTelefono == null) {
			txtTelefono = new JTextField();
			txtTelefono.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					getBtnCrearCliente().setEnabled(true);
				}
			});
			
			txtTelefono.setBounds(93, 226, 480, 37);
			txtTelefono.setColumns(10);
		}
		return txtTelefono;
	}
	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI:");
			lblDNI.setBounds(45, 27, 49, 29);
			lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblDNI;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 92, 68, 29);
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(10, 159, 84, 31);
			lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblApellidos;
	}
	private JLabel getLblTelefono() {
		if (lblTelefono == null) {
			lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(15, 226, 68, 25);
			lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblTelefono;
	}
	private JPanel getPnTextFields() {
		if (pnTextFields == null) {
			pnTextFields = new JPanel();
			pnTextFields.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Introduzca los datos del nuevo cliente ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnTextFields.setBounds(53, 93, 603, 287);
			pnTextFields.setLayout(null);
			pnTextFields.add(getTxtDNI());
			pnTextFields.add(getTxtNombre());
			pnTextFields.add(getTxtApellidos());
			pnTextFields.add(getTxtTelefono());
			pnTextFields.add(getLblDNI());
			pnTextFields.add(getLblNombre());
			pnTextFields.add(getLblApellidos());
			pnTextFields.add(getLblTelefono());
		}
		return pnTextFields;
	}
	
	private void crearNuevoCliente() {
		
		long dni = Long.valueOf(getTxtDNI().getText());
	
		String nombre = getTxtNombre().getText();
		String apellidos = getTxtApellidos().getText();
		int telefono = Integer.valueOf(getTxtTelefono().getText());
		this.cliente = new Cliente(dni,nombre,apellidos,telefono);
		controller.CrearNuevoCliente(cliente);
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelar.setBounds(611, 439, 112, 32);
		}
		return btnCancelar;
	}
	private JButton getBtnCrearCliente() {
		if (btnCrearCliente == null) {
			btnCrearCliente = new JButton("Crear Cliente");
			btnCrearCliente.setEnabled(false);
			btnCrearCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					crearNuevoCliente();			
					resetUi();
					asigC.fillClientesModel();
					JOptionPane.showMessageDialog(null, "Cliente añadido con exito");
				}
			});
			btnCrearCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCrearCliente.setBounds(473, 439, 128, 32);
		}
		return btnCrearCliente;
	}
	
	private void resetUi() {
		this.cliente = null;
		getTxtDNI().setText("");
		getTxtNombre().setText("");
		getTxtApellidos().setText("");
		getTxtTelefono().setText("");
		getBtnCrearCliente().setEnabled(false);
	}
}
