package ui.grafico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import logic.VisualizarGraficoController;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
			pnGrafico.setLayout(new BorderLayout(0, 0));
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
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selected = (String) comboBox.getSelectedItem();
					mostarGrafico(selected);
				}
			});
			comboBox.setModel(new DefaultComboBoxModel<String>(controller.getComboBox()));
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
			comboBox.setBackground(Color.WHITE);
		}
		return comboBox;
	}

	protected void mostarGrafico(String selected) {
		String chartTitle = "";
		JFreeChart barchar = ChartFactory.createBarChart(chartTitle, "", "Cantidad", createDataset(selected),
				PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot p = barchar.getCategoryPlot();
		p.setRangeGridlinePaint(Color.black);
		ChartPanel panel = new ChartPanel(barchar);
		pnGrafico.removeAll();
		pnGrafico.add(panel, BorderLayout.CENTER);
		pnGrafico.setVisible(false);
		pnGrafico.setVisible(true);
	}

	private CategoryDataset createDataset(String selected) {
		return controller.createDataset(selected);
//		final String ingresos = "INGRESOS";
//		final String gastos = "GASTOS";
//		final String balance = "BALANCE";
//		final String fiat = "FIAT";
//		final String audi = "AUDI";
//		final String ford = "FORD";
//		final String speed = "Speed";
//		final String millage = "Millage";
//		final String userrating = "User Rating";
//		final String safety = "safety";
//		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//		dataset.addValue(1.0, fiat, speed);
//		dataset.addValue(-3.0, fiat, userrating);
//		dataset.addValue(5.0, fiat, millage);
//		dataset.addValue(5.0, fiat, safety);
//
//		dataset.addValue(5.0, audi, speed);
//		dataset.addValue(6.0, audi, userrating);
//		dataset.addValue(10.0, audi, millage);
//		dataset.addValue(4.0, audi, safety);
//
//		dataset.addValue(4.0, ford, speed);
//		dataset.addValue(2.0, ford, userrating);
//		dataset.addValue(3.0, ford, millage);
//		dataset.addValue(6.0, ford, safety);
//
//		return dataset;
	}
}
