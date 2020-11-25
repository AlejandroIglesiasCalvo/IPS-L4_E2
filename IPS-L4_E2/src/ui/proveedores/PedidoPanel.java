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
import javax.swing.border.LineBorder;

public class PedidoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblProducto;

	private ImageIcon picture = null;
	private JLabel lblPrecio;
	private JPanel container;
	private ProductoPedido p;
	private CreaPedidosView creaPedido;
	private CreaPedidosController pedController;
	private JLabel lblUds;
	

	public PedidoPanel(ProductoPedido p, JPanel container, CreaPedidosView creaPedido, CreaPedidosController pedController) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.p = p;
		this.creaPedido = creaPedido;
		this.pedController = pedController;
		setBackground(Color.WHITE);
		this.container = container;
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getLblProducto());
		add(getLblUds());
		add(getLblPrecio());
		getLblPrecio().setText(p.getPrecio()*p.getUnidades()+"");
		getLblUds().setText(p.getUnidades()+"");
		getLblProducto().setText(p.getNombre());
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

	
	private JLabel getLblUds() {
		if (lblUds == null) {
			lblUds = new JLabel("");
			lblUds.setBackground(Color.WHITE);
			lblUds.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblUds.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblUds;
	}
}
