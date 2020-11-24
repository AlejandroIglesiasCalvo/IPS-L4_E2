package ui.grafico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.VisualizarGraficoController;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class VisualizarGrafico extends JFrame {

	private JPanel contentPane;
	private JPanel pnSeleccionGrafico;
	private JPanel pnGrafico;
	private JLabel lblMes;
	private JComboBox<String> comboBox;
	private VisualizarGraficoController controller = new VisualizarGraficoController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarGrafico frame = new VisualizarGrafico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VisualizarGrafico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 377);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnSeleccionGrafico(), BorderLayout.NORTH);
		contentPane.add(getPnGrafico(), BorderLayout.CENTER);
	}

	private JPanel getPnSeleccionGrafico() {
		if (pnSeleccionGrafico == null) {
			pnSeleccionGrafico = new JPanel();
			pnSeleccionGrafico.setBackground(Color.WHITE);
			pnSeleccionGrafico.setLayout(new GridLayout(1, 0, 3, 3));
			pnSeleccionGrafico.add(getLblMes());
			pnSeleccionGrafico.add(getComboBox());
		}
		return pnSeleccionGrafico;
	}
	private JPanel getPnGrafico() {
		if (pnGrafico == null) {
			pnGrafico = new JPanel();
			pnGrafico.setBackground(Color.WHITE);
		}
		return pnGrafico;
	}
	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("Seleccionar mes :");
			lblMes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblMes;
	}
	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(controller.getComboBox()));
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
			comboBox.setBackground(Color.WHITE);
		}
		return comboBox;
	}
}
