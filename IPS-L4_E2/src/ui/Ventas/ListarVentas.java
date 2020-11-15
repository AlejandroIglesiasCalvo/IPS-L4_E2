package ui.Ventas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.VentasController;
import logic.dto.Venta;
import ui.Ventas.Modelos.TablaVentas;
import ui.Ventas.Modelos.TablaVentasModel;

public class ListarVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollVentas;
	private List<Venta> Ventas;
	private VentasController vc;
	private LocalDateTime inicio, fin;
	private JPanel panel;
	private JLabel lblTitulo;
	private JPanel pnlTipos;
	private JLabel lblId;
	private JLabel lblFecha;
	private JLabel lblPrecio;
	private TablaVentas tablaVentas;

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
		contentPane.add(getScrollVentas(), BorderLayout.CENTER);
		contentPane.add(getTablaVentas(), BorderLayout.SOUTH);
		contentPane.add(getPanel(), BorderLayout.NORTH);

	}

	private JScrollPane getScrollVentas() {
		if (scrollVentas == null) {
			scrollVentas = new JScrollPane();
			scrollVentas.add(tablaVentas);
		}
		return scrollVentas;
	}

	private DefaultListModel<String> modelListVentas() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (Venta or : getListaProductoCarritosTienda())
			model.addElement("Id: " + or.getID() + "  Fecha: " + or.getFecha() + "  Total: " + or.getTotal() + "€");
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

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblTitulo_1(), BorderLayout.NORTH);
			panel.add(getPnlTipos(), BorderLayout.SOUTH);
		}
		return panel;
	}

	private JLabel getLblTitulo_1() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Hisotrial de ventas");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}

	private JPanel getPnlTipos() {
		if (pnlTipos == null) {
			pnlTipos = new JPanel();
			pnlTipos.setLayout(new GridLayout(0, 3, 0, 0));
			pnlTipos.add(getLblId());
			pnlTipos.add(getLblFecha());
			pnlTipos.add(getLblPrecio());
		}
		return pnlTipos;
	}

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("Id de la venta");
		}
		return lblId;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
		}
		return lblFecha;
	}

	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio total");
		}
		return lblPrecio;
	}

	private JTable getTablaVentas() {
		if (tablaVentas == null) {
			tablaVentas = new TablaVentas();
		}
		return tablaVentas;
	}

	public void fillPresupuestosModel() {
		TablaVentasModel m = (TablaVentasModel) this.tablaVentas.getModel();

		List<Venta> lista = getListaProductoCarritosTienda();

		for (int i = 0; i < lista.size(); i++) {
			m.addRow(lista.get(i));
		}

	}

}
