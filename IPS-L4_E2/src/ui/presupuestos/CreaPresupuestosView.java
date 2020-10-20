package ui.presupuestos;

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

import logic.CreaPresupuestoController;
import logic.dto.Producto;

@SuppressWarnings("serial")
public class CreaPresupuestosView extends JDialog {

	private JFrame frmPresupuesto;
	private JPanel pnListas;
	private JScrollPane spCatalogo;
	private JScrollPane spPresupuesto;
	private JPanel pnButtons;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel pnCatProductos;
	private JPanel pnPreProductos;
	private JTextField textField;
	private JTextField txtPrecioTotal;
	private JPanel pnInfo;
	private JLabel lblCatalogo;
	private JLabel lblProductosPresupuesto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaPresupuestosView window = new CreaPresupuestosView();
					window.frmPresupuesto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private CreaPresupuestoController presController = new CreaPresupuestoController();
	private JButton btnAlex;
	private JPanel pnFiltrar;
	private JComboBox<String> cbxTipos;
	private JSpinner spnPrecio;
	private JPanel pnBtns;
	private JRadioButton rdbtnMin;
	private JRadioButton rdbtnMax;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton;
	private JPanel pnTablaInfo;
	private JLabel lblName1;
	private JLabel lblNewLabel;
	private JLabel lblPrecio;
	private JPanel pnTablaInfoCatalogo;
	private JLabel lblName1_1;
	private JLabel lblNewLabel_1;
	private JLabel lblPrecio_1;
	private JLabel lblNewLabel_2;

	/**
	 * Create the application.
	 */
	public CreaPresupuestosView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPresupuesto = new JFrame();
		frmPresupuesto.setTitle("Presupuesto");
		frmPresupuesto.setName("Presupuesto\r\n");
		frmPresupuesto.getContentPane().setBackground(Color.WHITE);
		frmPresupuesto.getContentPane().add(getPnListas(), BorderLayout.CENTER);
		frmPresupuesto.getContentPane().add(getPnButtons(), BorderLayout.SOUTH);
		frmPresupuesto.getContentPane().add(getPnInfo(), BorderLayout.NORTH);
		frmPresupuesto.setBounds(100, 100, 958, 720);
		frmPresupuesto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// a�ade a la lista catalogo todos los productos
		addCatalogo();
		cbxTipos.setSelectedIndex(0);
	}

	/**
	 * a�ade a la lista catalogo todos los productos cada uno de estos va a estar
	 * compuesto por un panel CatalogoPanel y estos se a�adel al panel
	 * correspondiente
	 */
	private void addCatalogo() {
		List<Producto> productos = presController.getProductos();
		for (Producto p : productos) {
			pnCatProductos.add(new CatalogoPanel(p, pnCatProductos, this, presController));
		}
	}

	private JPanel getPnListas() {
		if (pnListas == null) {
			pnListas = new JPanel();
			pnListas.setBackground(Color.WHITE);
			pnListas.setLayout(new GridLayout(0, 2, 3, 3));
			pnListas.add(getSpCatalogo());
			pnListas.add(getSpPresupuesto());
		}
		return pnListas;
	}

	private JScrollPane getSpCatalogo() {
		if (spCatalogo == null) {
			spCatalogo = new JScrollPane();
			spCatalogo.setViewportView(getPnCatProductos());
		}
		return spCatalogo;
	}

	private JScrollPane getSpPresupuesto() {
		if (spPresupuesto == null) {
			spPresupuesto = new JScrollPane();
			spPresupuesto.setViewportView(getPnPreProductos());
		}
		return spPresupuesto;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.WHITE);
			pnButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnButtons.add(getBtnAlex());
			pnButtons.add(getTxtPrecioTotal());
			pnButtons.add(getTextField());
			pnButtons.add(getBtnAceptar());
			pnButtons.add(getBtnCancelar());
		}
		return pnButtons;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("CREAR PRESUPUESTO");
			btnAceptar.setEnabled(false);
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearPresupuesto();
				}
			});
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnAceptar.setForeground(Color.WHITE);
			btnAceptar.setBackground(new Color(50, 205, 50));
			btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnAceptar;
	}

	/**
	 * metodo para crear el presupuesto y pasar a la siguiente ventana
	 */
	protected void crearPresupuesto() {
		presController.crearPresupuesto();
		// se a�adiria c�digo aqu� para ir a la pesta�a siguiente
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("CANCELAR");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBackground(new Color(255, 0, 0));
		}
		return btnCancelar;
	}

	private JPanel getPnCatProductos() {
		if (pnCatProductos == null) {
			pnCatProductos = new JPanel();
			pnCatProductos.setBackground(Color.WHITE);
			pnCatProductos.setForeground(Color.BLACK);
			pnCatProductos.setLayout(new GridLayout(0, 1, 3, 3));
		}
		return pnCatProductos;
	}

	private JPanel getPnPreProductos() {
		if (pnPreProductos == null) {
			pnPreProductos = new JPanel();
			pnPreProductos.setBackground(Color.WHITE);
			pnPreProductos.setLayout(new GridLayout(0, 1, 3, 3));
		}
		return pnPreProductos;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textField.setBackground(Color.WHITE);
			textField.setEditable(false);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setText("0,0");
			textField.setColumns(10);
		}
		return textField;
	}

	private JTextField getTxtPrecioTotal() {
		if (txtPrecioTotal == null) {
			txtPrecioTotal = new JTextField();
			txtPrecioTotal.setBackground(Color.WHITE);
			txtPrecioTotal.setEditable(false);
			txtPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtPrecioTotal.setText("Precio total:");
			txtPrecioTotal.setColumns(10);
		}
		return txtPrecioTotal;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setLayout(new GridLayout(0, 2, 3, 3));
			pnInfo.add(getLblCatalogo());
			pnInfo.add(getLblProductosPresupuesto());
			pnInfo.add(getPnFiltrar());
			pnInfo.add(getLblNewLabel_2());
			pnInfo.add(getPnTablaInfo());
			pnInfo.add(getPnTablaInfoCatalogo());
		}
		return pnInfo;
	}

	private JLabel getLblCatalogo() {
		if (lblCatalogo == null) {
			lblCatalogo = new JLabel("Productos cat\u00E1logo");
			lblCatalogo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblCatalogo.setBackground(Color.WHITE);
		}
		return lblCatalogo;
	}

	private JLabel getLblProductosPresupuesto() {
		if (lblProductosPresupuesto == null) {
			lblProductosPresupuesto = new JLabel("Productos a comprar");
			lblProductosPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblProductosPresupuesto.setBackground(Color.WHITE);
		}
		return lblProductosPresupuesto;
	}

	public JFrame getFrame() {
		return frmPresupuesto;
	}

	public JTextField getTxtTotal() {
		return textField;
	}

	public JPanel getPnPresupProducts() {
		return pnPreProductos;
	}

	public JButton getBtnCreate() {
		return btnAceptar;
	}

	/**
	 * metodo que a�ade un producto al presupuesto
	 * 
	 * @param producto
	 */
	public void addToPresupuesto(Producto producto, CatalogoPanel cg) {
		pnPreProductos.add(new ProductosPanel(producto, pnPreProductos, this, presController, cg));
		// hago esto para que se muestren los cambios en el panel
		pnPreProductos.setVisible(false);
		pnPreProductos.setVisible(true);
	}

	private JButton getBtnAlex() {
		if (btnAlex == null) {
			btnAlex = new JButton("DemoAlex");
			btnAlex.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ProductosTransporte preparados = new ProductosTransporte(presController.getPresupueso());
					preparados.setVisible(true);
					preparados.setLocationRelativeTo(null);
				}
			});
		}
		return btnAlex;
	}

	private JPanel getPnFiltrar() {
		if (pnFiltrar == null) {
			pnFiltrar = new JPanel();
			pnFiltrar.setLayout(new GridLayout(0, 4, 1, 1));
			pnFiltrar.add(getCbxTipos());
			pnFiltrar.add(getSpnPrecio());
			pnFiltrar.add(getPnBtns());
			pnFiltrar.add(getBtnNewButton());
		}
		return pnFiltrar;
	}

	private JComboBox<String> getCbxTipos() {
		if (cbxTipos == null) {
			cbxTipos = new JComboBox<String>();
			cbxTipos.setModel(new DefaultComboBoxModel<String>(presController.getTipos()));
			cbxTipos.setBackground(Color.WHITE);
		}
		return cbxTipos;
	}

	private JSpinner getSpnPrecio() {
		if (spnPrecio == null) {
			spnPrecio = new JSpinner();
			spnPrecio.setBackground(Color.WHITE);
			spnPrecio.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		}
		return spnPrecio;
	}

	private JPanel getPnBtns() {
		if (pnBtns == null) {
			pnBtns = new JPanel();
			pnBtns.setBackground(Color.WHITE);
			pnBtns.setLayout(new GridLayout(0, 2, 0, 0));
			pnBtns.add(getRdbtnMin());
			pnBtns.add(getRdbtnMax());
		}
		return pnBtns;
	}

	private JRadioButton getRdbtnMin() {
		if (rdbtnMin == null) {
			rdbtnMin = new JRadioButton("menor");
			buttonGroup.add(rdbtnMin);
			rdbtnMin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return rdbtnMin;
	}

	private JRadioButton getRdbtnMax() {
		if (rdbtnMax == null) {
			rdbtnMax = new JRadioButton("mayor");
			rdbtnMax.setSelected(true);
			buttonGroup.add(rdbtnMax);
			rdbtnMax.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return rdbtnMax;
	}

	private JButton getBtnNewButton() {
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
					if (tipo.equals("Sin definir") || (precio <= 0 && maxMin.equals("menor"))) {
						JOptionPane.showMessageDialog(null, "Las opciones de filtrar son incorrectas");
					}
					List<Producto> filtrada = presController.filtra(precio, tipo, maxMin);
					ponerProductos(filtrada);
				}
			});
			btnNewButton.setBackground(Color.WHITE);
		}
		return btnNewButton;
	}

	protected void ponerProductos(List<Producto> filtrada) {
		pnCatProductos.removeAll();
		for (Producto p : filtrada) {
			pnCatProductos.add(new CatalogoPanel(p, pnCatProductos, this, presController));
		}
		pnCatProductos.setVisible(false);
		pnCatProductos.setVisible(true);
	}

	private JPanel getPnTablaInfo() {
		if (pnTablaInfo == null) {
			pnTablaInfo = new JPanel();
			pnTablaInfo.setLayout(new GridLayout(0, 4, 0, 0));
			pnTablaInfo.add(getLblName1());
			pnTablaInfo.add(getLblNewLabel());
			pnTablaInfo.add(getLblPrecio());
		}
		return pnTablaInfo;
	}

	private JLabel getLblName1() {
		if (lblName1 == null) {
			lblName1 = new JLabel("Nombre");
			lblName1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblName1.setBackground(Color.WHITE);
			lblName1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblName1;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Tipo");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}

	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio");
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrecio;
	}

	private JPanel getPnTablaInfoCatalogo() {
		if (pnTablaInfoCatalogo == null) {
			pnTablaInfoCatalogo = new JPanel();
			pnTablaInfoCatalogo.setLayout(new GridLayout(0, 4, 0, 0));
			pnTablaInfoCatalogo.add(getLblName1_1());
			pnTablaInfoCatalogo.add(getLblNewLabel_1());
			pnTablaInfoCatalogo.add(getLblPrecio_1());
		}
		return pnTablaInfoCatalogo;
	}

	private JLabel getLblName1_1() {
		if (lblName1_1 == null) {
			lblName1_1 = new JLabel("Nombre");
			lblName1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblName1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblName1_1.setBackground(Color.WHITE);
		}
		return lblName1_1;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Tipo");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_1.setBackground(Color.WHITE);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblPrecio_1() {
		if (lblPrecio_1 == null) {
			lblPrecio_1 = new JLabel("Precio");
			lblPrecio_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrecio_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblPrecio_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
		}
		return lblNewLabel_2;
	}
}
