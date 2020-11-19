package ui.proveedores;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logic.CreaPedidosController;
import logic.dto.ProductoPedido;
import logic.dto.Producto_Almacen;

public class ProductPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnDelete;
	private JButton btnAdd;
	private JLabel lblProducto;

	private ImageIcon picture = null;
	private JLabel lblPrecio;
	private JPanel container;
	private JButton btnRemove;
	private Producto_Almacen p;
	private CreaPedidosView creaPedido;
	private CreaPedidosController pedController;
	private JLabel lblTipo;
	

	public ProductPanel(Producto_Almacen p, JPanel container, CreaPedidosView creaPedido, CreaPedidosController pedController) {
		this.p = p;
		this.creaPedido = creaPedido;
		this.pedController = pedController;
		setBackground(Color.WHITE);
		this.container = container;
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getLblProducto());
		add(getLblTipo());
		add(getLblPrecio());
		add(getPnDelete());
		getLblPrecio().setText(p.getPrecio()+"");
		getLblProducto().setText(p.getNombre());
		getLblTipo().setText(p.getTipo());
		btnRemove.setEnabled(false);
	}

	private JPanel getPnDelete() {
		if (pnDelete == null) {
			pnDelete = new JPanel();
			pnDelete.setBackground(Color.WHITE);
			pnDelete.setLayout(new GridLayout(2, 1, 3, 3));
			pnDelete.add(getBtnAdd());
			pnDelete.add(getBtnRemove());
		}
		return pnDelete;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Añadir");
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(new Color(0, 128, 0));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirProducto();
				}
			});
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return btnAdd;
	}

	protected void añadirProducto() {
		creaPedido.getTxtTotal().setText(pedController.updateTotalAddProduct(p));
		creaPedido.getBtnCreate().setEnabled(true);
		creaPedido.updatePedido();
		btnRemove.setEnabled(true);
		
	}

	private JLabel getLblProducto() {
		if (lblProducto == null) {
			lblProducto = new JLabel("");
			lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
			lblProducto.setBackground(Color.WHITE);
		}
		return lblProducto;
	}
	
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("New label");
			lblPrecio.setBackground(Color.WHITE);
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrecio;
	}
	
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Eliminar");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarProducto();
				}
			});
			btnRemove.setBackground(Color.ORANGE);
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return btnRemove;
	}

	
	protected void eliminarProducto() {
		creaPedido.getTxtTotal().setText(pedController.updateTotalEliminarProducto(p));
		if(!pedController.hasProductosEnCompra()) {
			creaPedido.getBtnCreate().setEnabled(false);
		}
		if(!pedController.tieneEsteProducto(p)) {
			btnRemove.setEnabled(false);
		}
		creaPedido.updatePedido();
		container.setVisible(false);
		container.setVisible(true);
		
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("");
			lblTipo.setBackground(Color.WHITE);
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTipo;
	}
}
