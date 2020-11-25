package ui.proveedores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import logic.CreaPedidosController;
import logic.CreaPresupuestoController;
import logic.dto.Presupuesto;
import logic.dto.Producto;
import logic.dto.ProductoCarrito;
import logic.dto.ProductoPedido;
import logic.dto.Producto_Almacen;
import logic.dto.Repartidor;
import ui.Ventas.ListarVentas;
import ui.Ventas.RangoMostrarVentas;
import ui.empleados.añadirempleado;
import ui.presupuestos.CatalogoPanel;

@SuppressWarnings("serial")
public class CreaPedidosView extends JFrame {

	private JPanel frmPresupuesto;

	private CreaPedidosController pedController = new CreaPedidosController();
	private CreaPedidosView window;
	private JPanel panel;
	private JPanel pnListas;
	private JScrollPane spCatalogo;
	private JScrollPane spPresupuesto;
	private JPanel pnInfo;
	private JLabel lblCatalogo;
	private JLabel lblProductosPresupuesto;
	private JPanel pnFiltrar;
	private JComboBox<String> cbxTipos;
	private JSpinner spnPrecio;
	private JPanel pnBtns;
	private JRadioButton rdbtnMin;
	private JRadioButton rdbtnMax;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JPanel pnTablaInfo;
	private JLabel lblName1;
	private JLabel lblNewLabel;
	private JLabel lblPrecio;
	private JPanel pnTablaInfoCatalogo;
	private JLabel lblProducto;
	private JLabel lblUds;
	private JLabel lblNewLabel_1;
	private JPanel pnCatProductos;
	private JPanel pnPreProductos;
	private JPanel pnButtons;
	private JTextField textPrecioTotal;
	private JTextField textTotal;
	private JButton btnPedido;
	private JButton btnCancelar;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the application.
	 */
	public CreaPedidosView() {
		initialize();
		this.window = this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPresupuesto = new JPanel();
		setContentPane(frmPresupuesto);
		frmPresupuesto.setName("Pedido");
		frmPresupuesto.setBackground(Color.WHITE);
		frmPresupuesto.setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 958, 720);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frmPresupuesto.add(getPanel(), BorderLayout.CENTER);
		addCatalogoAlmacen();
		
	}

	private void addCatalogoAlmacen() {
		List<Producto_Almacen> productos = pedController.getProductos();
		for (Producto_Almacen p : productos) {
			pnCatProductos.add(new ProductPanel(p, pnCatProductos, this, pedController));
		}
	}

	/**
	 * metodo para crear el presupuesto y pasar a la siguiente ventana
	 */
	protected void crearPedido() {
		pedController.crearPedido();
		JOptionPane.showMessageDialog(this, "Pedido creado");
		pnPreProductos.removeAll();
		pnPreProductos.setVisible(false);
		pnPreProductos.setVisible(true);
		this.pedController = new CreaPedidosController();
		this.getTextTotal().setText("0,0");
		pnCatProductos.removeAll();
		addCatalogoAlmacen();
		pnCatProductos.setVisible(false);
		pnCatProductos.setVisible(true);
	}

	protected void cerrar() {
		this.dispose();
	}

	public JPanel getFrame() {
		return frmPresupuesto;
	}

	public JTextField getTxtTotal() {
		return textTotal;
	}

	public JPanel getPnPresupProducts() {
		return pnPreProductos;
	}

	public JButton getBtnCreate() {
		return btnPedido;
	}

	protected void ponerProductos(List<Producto_Almacen> filtrada) {
		pnCatProductos.removeAll();
		for (Producto_Almacen p : filtrada) {
			pnCatProductos.add(new ProductPanel(p, pnCatProductos, this, pedController));
		}
		pnCatProductos.setVisible(false);
		pnCatProductos.setVisible(true);
	}

	public void updatePedido() {
		pnPreProductos.removeAll();
		for (ProductoPedido p : pedController.getProductoesEnPedido()) {
			pnPreProductos.add(new PedidoPanel(p, pnPreProductos, this, pedController));
		}
		pnPreProductos.setVisible(false);
		pnPreProductos.setVisible(true);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPnListas_1());
			panel.add(getPnInfo_1(), BorderLayout.NORTH);
			panel.add(getPnButtons_1(), BorderLayout.SOUTH);
		}
		return panel;
	}

	private JPanel getPnListas_1() {
		if (pnListas == null) {
			pnListas = new JPanel();
			pnListas.setBackground(Color.WHITE);
			pnListas.setLayout(new GridLayout(0, 2, 3, 3));
			pnListas.add(getSpCatalogo_1());
			pnListas.add(getSpPresupuesto_1());
		}
		return pnListas;
	}

	private JScrollPane getSpCatalogo_1() {
		if (spCatalogo == null) {
			spCatalogo = new JScrollPane();
			spCatalogo.setViewportView(getPnCatProductos());
		}
		return spCatalogo;
	}

	private JScrollPane getSpPresupuesto_1() {
		if (spPresupuesto == null) {
			spPresupuesto = new JScrollPane();
			spPresupuesto.setViewportView(getPnPreProductos());
		}
		return spPresupuesto;
	}

	private JPanel getPnInfo_1() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setLayout(new GridLayout(0, 2, 0, 0));
			pnInfo.add(getLblCatalogo_1());
			pnInfo.add(getLblProductosPresupuesto_1());
			pnInfo.add(getPnFiltrar_1());
			pnInfo.add(getLblNewLabel_2_1());
			pnInfo.add(getPnTablaInfo_1());
			pnInfo.add(getPnTablaInfoCatalogo_1());
		}
		return pnInfo;
	}

	private JLabel getLblCatalogo_1() {
		if (lblCatalogo == null) {
			lblCatalogo = new JLabel("Productos catálogo");
			lblCatalogo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblCatalogo.setBackground(Color.WHITE);
		}
		return lblCatalogo;
	}

	private JLabel getLblProductosPresupuesto_1() {
		if (lblProductosPresupuesto == null) {
			lblProductosPresupuesto = new JLabel("Productos a pedir");
			lblProductosPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblProductosPresupuesto.setBackground(Color.WHITE);
		}
		return lblProductosPresupuesto;
	}

	private JPanel getPnFiltrar_1() {
		if (pnFiltrar == null) {
			pnFiltrar = new JPanel();
			pnFiltrar.setLayout(new GridLayout(0, 4, 1, 1));
			pnFiltrar.add(getCbxTipos_1());
			pnFiltrar.add(getSpnPrecio_1());
			pnFiltrar.add(getPnBtns_1());
			pnFiltrar.add(getBtnNewButton_1());
		}
		return pnFiltrar;
	}

	private JComboBox<String> getCbxTipos_1() {
		if (cbxTipos == null) {
			cbxTipos = new JComboBox<String>();
			cbxTipos.setModel(new DefaultComboBoxModel<String>(pedController.getTipos()));
			cbxTipos.setBackground(Color.WHITE);
		}
		return cbxTipos;
	}

	private JSpinner getSpnPrecio_1() {
		if (spnPrecio == null) {
			spnPrecio = new JSpinner();
			spnPrecio.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
			spnPrecio.setBackground(Color.WHITE);
		}
		return spnPrecio;
	}

	private JPanel getPnBtns_1() {
		if (pnBtns == null) {
			pnBtns = new JPanel();
			pnBtns.setBackground(Color.WHITE);
			pnBtns.setLayout(new GridLayout(0, 2, 0, 0));
			pnBtns.add(getRdbtnMin_1());
			pnBtns.add(getRdbtnMax_1());
		}
		return pnBtns;
	}

	private JRadioButton getRdbtnMin_1() {
		if (rdbtnMin == null) {
			rdbtnMin = new JRadioButton("menor");
			buttonGroup.add(rdbtnMin);
			rdbtnMin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return rdbtnMin;
	}

	private JRadioButton getRdbtnMax_1() {
		if (rdbtnMax == null) {
			rdbtnMax = new JRadioButton("mayor");
			buttonGroup.add(rdbtnMax);
			rdbtnMax.setSelected(true);
			rdbtnMax.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return rdbtnMax;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("FILTRAR");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					double precio = (double) spnPrecio.getValue();
					String tipo = (String) cbxTipos.getSelectedItem();
					String maxMin;
					if (rdbtnMax.isSelected()) {
						maxMin = "mayor";
					} else {
						maxMin = "menor";
					}
					if (precio <= 0 && maxMin.equals("menor")) {
						JOptionPane.showMessageDialog(null, "Las opciones de filtrar son incorrectas");
					}
					List<Producto_Almacen> filtrada = pedController.filtra(precio, tipo, maxMin);
					ponerProductos(filtrada);
				}
			});
			btnNewButton.setBackground(Color.WHITE);
		}
		return btnNewButton;
	}

	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
		}
		return lblNewLabel_2;
	}

	private JPanel getPnTablaInfo_1() {
		if (pnTablaInfo == null) {
			pnTablaInfo = new JPanel();
			pnTablaInfo.setLayout(new GridLayout(0, 4, 0, 0));
			pnTablaInfo.add(getLblName1_1());
			pnTablaInfo.add(getLblNewLabel_3());
			pnTablaInfo.add(getLblPrecio_1());
		}
		return pnTablaInfo;
	}

	private JLabel getLblName1_1() {
		if (lblName1 == null) {
			lblName1 = new JLabel("Nombre");
			lblName1.setHorizontalAlignment(SwingConstants.CENTER);
			lblName1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblName1.setBackground(Color.WHITE);
		}
		return lblName1;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Tipo");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setBackground(Color.WHITE);
		}
		return lblNewLabel;
	}

	private JLabel getLblPrecio_1() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio");
			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblPrecio;
	}

	private JPanel getPnTablaInfoCatalogo_1() {
		if (pnTablaInfoCatalogo == null) {
			pnTablaInfoCatalogo = new JPanel();
			pnTablaInfoCatalogo.setLayout(new GridLayout(0, 3, 0, 0));
			pnTablaInfoCatalogo.add(getLblProducto_1());
			pnTablaInfoCatalogo.add(getLblUds_1());
			pnTablaInfoCatalogo.add(getLblNewLabel_1_1());
		}
		return pnTablaInfoCatalogo;
	}

	private JLabel getLblProducto_1() {
		if (lblProducto == null) {
			lblProducto = new JLabel("Producto");
			lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
			lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblProducto.setBackground(Color.WHITE);
		}
		return lblProducto;
	}

	private JLabel getLblUds_1() {
		if (lblUds == null) {
			lblUds = new JLabel("Unidades");
			lblUds.setHorizontalAlignment(SwingConstants.CENTER);
			lblUds.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblUds.setBackground(Color.WHITE);
		}
		return lblUds;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Precio Total");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_1.setBackground(Color.WHITE);
		}
		return lblNewLabel_1;
	}

	private JPanel getPnCatProductos() {
		if (pnCatProductos == null) {
			pnCatProductos = new JPanel();
			pnCatProductos.setBackground(Color.WHITE);
			pnCatProductos.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnCatProductos;
	}

	private JPanel getPnPreProductos() {
		if (pnPreProductos == null) {
			pnPreProductos = new JPanel();
			pnPreProductos.setBackground(Color.WHITE);
			pnPreProductos.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnPreProductos;
	}

	private JPanel getPnButtons_1() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.WHITE);
			pnButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnButtons.add(getTextPrecioTotal());
			pnButtons.add(getTextTotal());
			pnButtons.add(getBtnPedido_1());
			pnButtons.add(getBtnCancelar_1());
		}
		return pnButtons;
	}

	private JTextField getTextPrecioTotal() {
		if (textPrecioTotal == null) {
			textPrecioTotal = new JTextField();
			textPrecioTotal.setText("Precio total:");
			textPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textPrecioTotal.setEditable(false);
			textPrecioTotal.setColumns(10);
			textPrecioTotal.setBackground(Color.WHITE);
		}
		return textPrecioTotal;
	}

	private JTextField getTextTotal() {
		if (textTotal == null) {
			textTotal = new JTextField();
			textTotal.setText("0,0");
			textTotal.setHorizontalAlignment(SwingConstants.CENTER);
			textTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textTotal.setEditable(false);
			textTotal.setColumns(10);
			textTotal.setBackground(Color.WHITE);
		}
		return textTotal;
	}

	private JButton getBtnPedido_1() {
		if (btnPedido == null) {
			btnPedido = new JButton("CREAR PEDIDO");
			btnPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearPedido();
				}
			});
			btnPedido.setForeground(Color.WHITE);
			btnPedido.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnPedido.setEnabled(false);
			btnPedido.setBackground(new Color(50, 205, 50));
			btnPedido.setAlignmentX(0.5f);
		}
		return btnPedido;
	}

	private JButton getBtnCancelar_1() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("CANCELAR");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnCancelar.setBackground(Color.RED);
		}
		return btnCancelar;
	}
}
