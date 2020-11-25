package ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.StoreInicio;
import ui.Ventas.RangoMostrarVentas;
import ui.empleados.añadirempleado;
import ui.grafico.VisualizarGrafico;
import ui.presupuestos.CreaPresupuestosView;
import ui.presupuestos.VisualizadorEntregasView;
import ui.proveedores.CreaPedidosView;

public class Inicio extends JFrame {

	/**
	 * Clase principal de la aplicacion, deben eliminarse las demas
	 * 
	 */
	// Clase para el intercambio entre pantallas de informacion
	private StoreInicio tmp;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCrearEmpleado;
	private JButton btnListarVentas;
	private JButton btnPresupuesto;
	private JButton btnVisEntrega;
	private JButton btnProveedor;
	private JButton btnGrafico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		tmp = new StoreInicio();
		setTitle("MueblesE2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(getBtnCrearEmpleado());
		contentPane.add(getBtnListarVentas());
		contentPane.add(getBtnPresupuesto());
		contentPane.add(getBtnVisEntrega());
		contentPane.add(getBtnProveedor());
		contentPane.add(getBtnGrafico());
	}

	private JButton getBtnCrearEmpleado() {
		if (btnCrearEmpleado == null) {
			btnCrearEmpleado = new JButton("Crear empleado");
			btnCrearEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirempleado añadirEmpleado = new añadirempleado();
					añadirEmpleado.setVisible(true);
					añadirEmpleado.setLocationRelativeTo(null);
				}
			});
		}
		return btnCrearEmpleado;
	}

	private JButton getBtnListarVentas() {
		if (btnListarVentas == null) {
			btnListarVentas = new JButton("ListarVentas");
			btnListarVentas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RangoMostrarVentas rangoVentas = new RangoMostrarVentas();
					rangoVentas.setVisible(true);
					rangoVentas.setLocationRelativeTo(null);
				}
			});
		}
		return btnListarVentas;
	}
	private JButton getBtnPresupuesto() {
		if (btnPresupuesto == null) {
			btnPresupuesto = new JButton("Crear presupuesto");
			btnPresupuesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CreaPresupuestosView presupuesto = new CreaPresupuestosView();
					presupuesto.setVisible(true);
					presupuesto.setLocationRelativeTo(null);
				}
			});
		}
		return btnPresupuesto;
	}
	private JButton getBtnVisEntrega() {
		if (btnVisEntrega == null) {
			btnVisEntrega = new JButton("Estado entregas");
			btnVisEntrega.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VisualizadorEntregasView viEntregas = new VisualizadorEntregasView();
					viEntregas.setVisible(true);
					viEntregas.setLocationRelativeTo(null);
				}
			});
		}
		return btnVisEntrega;
	}
	private JButton getBtnProveedor() {
		if (btnProveedor == null) {
			btnProveedor = new JButton("Crear pedido proveedor");
			btnProveedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CreaPedidosView viPedidos = new CreaPedidosView();
					viPedidos.setVisible(true);
					viPedidos.setLocationRelativeTo(null);
				}
			});
		}
		return btnProveedor;
	}
	private JButton getBtnGrafico() {
		if (btnGrafico == null) {
			btnGrafico = new JButton("Mostrar gráfico");
			btnGrafico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VisualizarGrafico viGraficos = new VisualizarGrafico();
					viGraficos.setVisible(true);
					viGraficos.setLocationRelativeTo(null);
				}
			});
		}
		return btnGrafico;
	}
}
