package ui.presupuestos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import logic.EntregaController;
import logic.Repartidores;
import logic.dto.Presupuesto;
import logic.dto.Producto;
import logic.dto.Repartidor;

import java.awt.GridLayout;

@SuppressWarnings("serial")
public class ProductosTransporte extends JFrame {

	private JPanel contentPane;
	private EntregaController ec;
	private JLabel lbltitulo;
	private JPanel pnlSur;
	private JButton btnAceptar;
	private JPanel pnlCentro;
	private JPanel pnlProductos;
	private JPanel pnlRepartidor;
	private JScrollPane scrollTienda;

	private int llevar = 0;
	private JList<String> listTienda;
	private Presupuesto presupuesto;
	private JButton btnLLevar;
	private JLabel lblLLevar;
	private JScrollPane scrollRepartidor;
	private JList<String> listRepartidores;
	private Repartidores r;

	/**
	 * Create the frame.
	 */
	public ProductosTransporte(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
		r = new Repartidores();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLbltitulo(), BorderLayout.NORTH);
		contentPane.add(getPnlSur(), BorderLayout.SOUTH);
		contentPane.add(getPnlCentro(), BorderLayout.CENTER);

		ec = new EntregaController(presupuesto);// Trampas mientras no este el resto
	}

	private JLabel getLbltitulo() {
		if (lbltitulo == null) {
			lbltitulo = new JLabel("Preparando el envio");
			lbltitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lbltitulo;
	}

	private JPanel getPnlSur() {
		if (pnlSur == null) {
			pnlSur = new JPanel();
			pnlSur.add(getBtnAceptar());
		}
		return pnlSur;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Continuar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if ((listRepartidores.getSelectedIndex()) > -1) {
							EntregasUI entregas = new EntregasUI(ec.getPresupuesto(),r.getRepartidor(listRepartidores.getSelectedIndex()),llevar);
							entregas.setVisible(true);
							entregas.setLocationRelativeTo(null);
						}
					} catch (NullPointerException ef) {
						JOptionPane.showMessageDialog(null, "Seleccione un Repartidor");
					}
				}
			});
		}
		return btnAceptar;
	}

	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel();
			pnlCentro.setLayout(new GridLayout(0, 1, 0, 0));
			pnlCentro.add(getPnlProductos());
			pnlCentro.add(getPnlRepartidor());
		}
		return pnlCentro;
	}

	private JPanel getPnlProductos() {
		if (pnlProductos == null) {
			pnlProductos = new JPanel();
			pnlProductos.setLayout(new GridLayout(0, 3, 0, 0));
			pnlProductos.add(getScrollTienda());
			pnlProductos.add(getBtnLLevar());
			pnlProductos.add(getLblLLevar());

		}
		return pnlProductos;
	}

	private JPanel getPnlRepartidor() {
		if (pnlRepartidor == null) {
			pnlRepartidor = new JPanel();
			pnlRepartidor.setLayout(new GridLayout(0, 1, 0, 0));
			pnlRepartidor.add(getScrollRepartidor());
		}
		return pnlRepartidor;
	}

	private JScrollPane getScrollTienda() {
		if (scrollTienda == null) {
			scrollTienda = new JScrollPane();
			scrollTienda.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollTienda.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollTienda.setViewportView(getListTienda());

		}
		return scrollTienda;
	}

	private JList<String> getListTienda() {
		if (listTienda == null) {
			listTienda = new JList<String>();
			listTienda.setBounds(10, 63, 644, 261);
			listTienda.setModel(modelListTienda());
		}
		return listTienda;
	}

	private DefaultListModel<String> modelListTienda() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (Producto or : getListaProductosTienda())
			model.addElement(or.getNombre());
		return model;
	}

	private List<Producto> getListaProductosTienda() {
		return presupuesto.getProductos();
	}

	private JButton getBtnLLevar() {
		if (btnLLevar == null) {
			btnLLevar = new JButton("Añadir a repartir (SOLO UN USO)");
			btnLLevar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						if ((listTienda.getSelectedIndex()) > -1) {
							llevar = listTienda.getSelectedIndices().length;
							lblLLevar.setText(String.valueOf(llevar));
							listTienda.remove(listTienda.getSelectedIndex());
							scrollTienda.setEnabled(false);
							listTienda.setEnabled(false);
							btnLLevar.setEnabled(false);
						}

					} catch (NullPointerException ef) {
						JOptionPane.showMessageDialog(null, "Seleccione un producto");
					}
				}

			});
		}
		return btnLLevar;
	}

	private JLabel getLblLLevar() {
		if (lblLLevar == null) {
			lblLLevar = new JLabel("");
		}
		return lblLLevar;
	}

	private JScrollPane getScrollRepartidor() {
		if (scrollRepartidor == null) {
			scrollRepartidor = new JScrollPane();
			scrollRepartidor.setViewportView(getListRepartidores());
		}
		return scrollRepartidor;
	}

	private JList<String> getListRepartidores() {
		if (listRepartidores == null) {
			listRepartidores = new JList<String>();
			listRepartidores.setModel(modelRepartidores());
		}
		return listRepartidores;
	}

	private DefaultListModel<String> modelRepartidores() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (Repartidor or : getListaRepartidores())
			model.addElement(or.getNombre());
		return model;
	}

	private List<Repartidor> getListaRepartidores() {
		return r.getRepartidores();
	}
}
