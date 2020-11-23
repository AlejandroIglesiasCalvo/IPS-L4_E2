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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.VentasController;
import logic.dto.Venta;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollVentas;
	private JList<String> listVentas;
	private List<Venta> Ventas;
	private VentasController vc;
	private LocalDateTime inicio, fin;
	private JPanel pnlNorte;
	private JLabel lblTitulo;
	private JPanel pnlLabels;
	private JLabel lblId;
	private JLabel lblFecha;
	private JLabel lblTotal;
	private JButton btnDetalles;

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
		contentPane.add(getPnlNorte(), BorderLayout.NORTH);

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
			model.addElement("" + or.getID() + "--------------------------------------------------" + or.getFecha()
					+ "----------------------------------------------------" + or.getTotal() + "");
		model.trimToSize();
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
			listVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listVentas.setValueIsAdjusting(true);
			listVentas.setBounds(10, 63, 644, 261);
			listVentas.setModel(modelListVentas());
		}
		return listVentas;
	}

	private JPanel getPnlNorte() {
		if (pnlNorte == null) {
			pnlNorte = new JPanel();
			pnlNorte.setLayout(new BorderLayout(0, 0));
			pnlNorte.add(getLblTitulo_1(), BorderLayout.NORTH);
			pnlNorte.add(getBtnDetalles(), BorderLayout.WEST);
			pnlNorte.add(getPnlLabels(), BorderLayout.SOUTH);
		}
		return pnlNorte;
	}

	private JLabel getLblTitulo_1() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Hisotrial de ventas");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}

	private JPanel getPnlLabels() {
		if (pnlLabels == null) {
			pnlLabels = new JPanel();
			pnlLabels.setLayout(new GridLayout(0, 3, 0, 0));
			pnlLabels.add(getLblId());
			pnlLabels.add(getLblFecha());
			pnlLabels.add(getLblTotal());
		}
		return pnlLabels;
	}

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("Id de la venta");
		}
		return lblId;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha de la venta");
		}
		return lblFecha;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total de la venta (€)");
		}
		return lblTotal;
	}

	private JButton getBtnDetalles() {
		if (btnDetalles == null) {
			btnDetalles = new JButton("Detalles de la Venta");
			btnDetalles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					DetallesVenta dv = new DetallesVenta(Ventas.get(listVentas.getSelectedIndex()));
					dv.setVisible(true);
					dv.setLocationRelativeTo(null);
				}
			});
		}
		return btnDetalles;
	}
}
