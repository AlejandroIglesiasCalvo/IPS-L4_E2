package ui.Ventas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.VentasController;
import logic.dto.Venta;
import logic.dto.producto_venta;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class DetallesVentaProductos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Venta v;
	private JLabel lblTitulo;
	private JButton tctCerrar;
	private JScrollPane scrollDetalles;
	private JList listDetalles;
	private VentasController vc;

	/**
	 * Create the frame.
	 * 
	 * @param v
	 */
	public DetallesVentaProductos(Venta v) {
		this.v = v;
		this.vc = new VentasController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getTctCerrar(), BorderLayout.SOUTH);
		contentPane.add(getScrollDetalles(), BorderLayout.CENTER);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Detalles de los productos:");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}

	private JButton getTctCerrar() {
		if (tctCerrar == null) {
			tctCerrar = new JButton("Cerrar");
			tctCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
		}
		return tctCerrar;
	}

	private void cerrar() {
		this.dispose();
	}

	private JScrollPane getScrollDetalles() {
		if (scrollDetalles == null) {
			scrollDetalles = new JScrollPane();
			scrollDetalles.setViewportView(getList_1());
		}
		return scrollDetalles;
	}

	private JList getList_1() {
		if (listDetalles == null) {
			listDetalles = new JList();
			listDetalles.setModel(modelDetallesVenta());
		}
		return listDetalles;
	}

	private DefaultListModel<String> modelDetallesVenta() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (producto_venta or : getListaDetallesVenta())
			model.addElement("" + or.getID() + "--------------------------------------------------Montado:" + or.getMontado()
					+ "----------------------------------------------------Transportado" + or.getTransportado() + "");
		model.trimToSize();
		return model;
	}

	private List<producto_venta> getListaDetallesVenta() {
		List<producto_venta> filtrado = vc.getProductosMontadosYTransportados(v.getID());
		return filtrado;

	}
}
