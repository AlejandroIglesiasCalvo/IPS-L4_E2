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
}
