package ui.almacen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.dto.Pedido;
import logic.dto.ProductoPedido;
import ui.almacen.modelos.InfoPedidoTabla;
import ui.almacen.modelos.InfoPedidoTablaModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class InfoPedidoView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private InfoPedidoTabla tabla;
	private Pedido p;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			InfoPedidoView dialog = new InfoPedidoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public InfoPedidoView(Pedido p) {
		this.p = p;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			scrollPane.setViewportView(getTabla());
		
		
		
	}
	
	private InfoPedidoTabla getTabla() {
		if(this.tabla == null) {
			this.tabla = new InfoPedidoTabla();
			fillModel();
		}
		return this.tabla;
	}
	
	private void fillModel() {
		for(ProductoPedido pp: this.p.getProductos()) {
			((InfoPedidoTablaModel) getTabla().getModel()).addRow(pp);
		}
	}
}
