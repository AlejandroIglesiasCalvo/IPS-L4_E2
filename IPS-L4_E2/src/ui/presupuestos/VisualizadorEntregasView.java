package ui.presupuestos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import logic.CreaPresupuestoController;
import logic.VisualizadorEntregasController;
import logic.dto.Producto;
import logic.dto.ProductoCarrito;
import logic.dto.Transporte;

@SuppressWarnings("serial")
public class VisualizadorEntregasView extends JFrame {

	private JPanel frmEntregas;

	private CreaPresupuestoController presController = new CreaPresupuestoController();
	private VisualizadorEntregasController veController = new VisualizadorEntregasController();
	private JPanel panel;
	private JPanel pnInfo;
	private JLabel lblEntregas;
	private JPanel pnInfoTabla;
	private JLabel lblTransportista;
	private JLabel lblEntrega;
	private JLabel lblEstado;
	private JScrollPane spEntregas;
	private JPanel pnEntregas;

	/**
	 * Create the application.
	 */
	public VisualizadorEntregasView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEntregas = new JPanel();
		setContentPane(frmEntregas);
		frmEntregas.setName("Entregas");
		frmEntregas.setBackground(Color.WHITE);
		frmEntregas.setLayout(new BorderLayout(0, 0));
		frmEntregas.add(getPanel());
		setBounds(100, 100, 722, 440);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addEntregas();
	}

	private void addEntregas() {
		List<Transporte> entregas = veController.getTransporte();
		for(Transporte t : entregas) {
			pnEntregas.add(new EntregaPanel(t, pnEntregas, this, veController));
		}
	}

	public JPanel getFrame() {
		return frmEntregas;
	}

	public void refresh() {
		pnEntregas.removeAll();
		for(Transporte t : veController.getTransporte()) {
			pnEntregas.add(new EntregaPanel(t, pnEntregas, this, veController));
		}
		pnEntregas.setVisible(false);
		pnEntregas.setVisible(true);
		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPnInfo_1(), BorderLayout.NORTH);
			panel.add(getSpEntregas_1(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JPanel getPnInfo_1() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(Color.WHITE);
			pnInfo.setLayout(new GridLayout(2, 0, 3, 3));
			pnInfo.add(getLblEntregas_1());
			pnInfo.add(getPnInfoTabla_1());
		}
		return pnInfo;
	}
	private JLabel getLblEntregas_1() {
		if (lblEntregas == null) {
			lblEntregas = new JLabel("Entregas");
			lblEntregas.setFont(new Font("Tahoma", Font.BOLD, 22));
		}
		return lblEntregas;
	}
	private JPanel getPnInfoTabla_1() {
		if (pnInfoTabla == null) {
			pnInfoTabla = new JPanel();
			pnInfoTabla.setBackground(Color.WHITE);
			pnInfoTabla.setLayout(new GridLayout(0, 4, 3, 3));
			pnInfoTabla.add(getLblTransportista_1());
			pnInfoTabla.add(getLblEntrega_1());
			pnInfoTabla.add(getLblEstado_1());
		}
		return pnInfoTabla;
	}
	private JLabel getLblTransportista_1() {
		if (lblTransportista == null) {
			lblTransportista = new JLabel("Transportista");
			lblTransportista.setHorizontalAlignment(SwingConstants.CENTER);
			lblTransportista.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTransportista;
	}
	private JLabel getLblEntrega_1() {
		if (lblEntrega == null) {
			lblEntrega = new JLabel("Entrega");
			lblEntrega.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntrega.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblEntrega;
	}
	private JLabel getLblEstado_1() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado");
			lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblEstado;
	}
	private JScrollPane getSpEntregas_1() {
		if (spEntregas == null) {
			spEntregas = new JScrollPane();
			spEntregas.setRowHeaderView(getPnEntregas());
		}
		return spEntregas;
	}
	private JPanel getPnEntregas() {
		if (pnEntregas == null) {
			pnEntregas = new JPanel();
			pnEntregas.setBackground(Color.WHITE);
			pnEntregas.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnEntregas;
	}
}
