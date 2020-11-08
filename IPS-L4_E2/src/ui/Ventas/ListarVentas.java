package ui.Ventas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.VentasController;
import logic.dto.Venta;

public class ListarVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JScrollPane scrollVentas;
	private JList<String> listVentas;
	private List<Venta> Ventas;
	private VentasController vc;
	private LocalDateTime inicio, fin;

	/**
	 * Create the frame.
	 */
	public ListarVentas(LocalDateTime inicio, LocalDateTime fin) {
		vc = new VentasController();
		this.inicio = inicio;
		this.fin = fin;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getScrollVentas(), BorderLayout.CENTER);

	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Hisotrial de ventas");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}

	private JScrollPane getScrollVentas() {
		if (scrollVentas == null) {
			scrollVentas = new JScrollPane();
			scrollVentas.setViewportView(getListVentas());
		}
		return scrollVentas;
	}

	private DefaultListModel<String> modelListVentas() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (Venta or : getListaProductoCarritosTienda())
			model.addElement("Id: "+or.getID() + "  Fecha: " + or.getFecha() + "  Total: " + or.getTotal()+"€");
		return model;
	}

	private List<Venta> getListaProductoCarritosTienda() {
		Ventas = vc.getTodasLasVentas();
		List<Venta> filtrado = new ArrayList<>();
		for (Venta v : Ventas) {
			if (v.getFecha().isAfter(inicio) && v.getFecha().isBefore(fin)) {
				filtrado.add(v);
			}
		}
		return filtrado;

	}

	private JList<String> getListVentas() {
		if (listVentas == null) {
			listVentas = new JList<String>();
			listVentas.setBounds(10, 63, 644, 261);
			listVentas.setModel(modelListVentas());
		}
		return listVentas;
	}

}
