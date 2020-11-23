package ui.Ventas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.dto.Venta;

public class DetallesVenta extends JFrame {

	private JPanel contentPane;
	private Venta v;

	/**
	 * Create the frame.
	 * 
	 * @param venta
	 */
	public DetallesVenta(Venta venta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.v = venta;
	}

	private void cerrar() {
		this.dispose();
	}
}
