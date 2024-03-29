package ui.presupuestos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import logic.AceptarPresupuestoController;
import logic.CreaClienteController;
import logic.dto.Cliente;
import logic.dto.Presupuesto;
import logic.dto.Venta;
import ui.clientes.CrearClientesView;
import ui.presupuestos.modelos.PresupuestosTabla;
import ui.presupuestos.modelos.PresupuestosTablaModel;

public class AceptarPresupuestosView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JList<String> list;
	private JPanel panel;
	private JButton btnCrearPresupuesto;
	private JButton btnCancelar;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private DefaultListModel<String> model;
	private PresupuestosTabla table;

	
	

	private CreaClienteController controller = new CreaClienteController();
	private CreaPresupuestosView presView;
	private AceptarPresupuestoController aceptPresController = new AceptarPresupuestoController();
	private CrearClientesView cCV;
	private AceptarPresupuestosView win;
	
	private List<Presupuesto> presupuestos;
	private List<Cliente> clientes;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AceptarPresupuestosView dialog = new AceptarPresupuestosView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AceptarPresupuestosView() {
//		this.presController = cPC;
//		this.presView = cPV;
		setTitle("Validacion de Presupuestos");
		setBounds(100, 100, 924, 766);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getScrollPane(), BorderLayout.CENTER);
		contentPanel.add(getPanel(), BorderLayout.SOUTH);
		contentPanel.add(getPanel_1(), BorderLayout.NORTH);
		contentPanel.add(getPanel_2(), BorderLayout.WEST);
		contentPanel.add(getPanel_3(), BorderLayout.EAST);
		this.win = this;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			//scrollPane.setViewportView(getList());
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JList<String> getList() {
		if (list == null) {
			list = new JList<>();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					getBtnCrearPresupuesto().setEnabled(true);
				}
			});
			list.setModel(getModel());
		}
		return list;
	}
	
	public PresupuestosTabla getTable() {
		if(table == null) {
			table = new PresupuestosTabla();
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					getBtnCrearPresupuesto().setEnabled(true);
				}
			});
			fillPresupuestosModel();
		}
		return table;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panel.add(getBtnCrearPresupuesto());
			panel.add(getBtnCancelar());
		}
		return panel;
	}
	private JButton getBtnCrearPresupuesto() {
		if (btnCrearPresupuesto == null) {
			btnCrearPresupuesto = new JButton("Crear Venta");
			btnCrearPresupuesto.setEnabled(false);
			btnCrearPresupuesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = getTable().getSelectedRow();
					Presupuesto p = ((PresupuestosTablaModel)getTable().getModel()).getValueAtRow(index);
					System.out.println(p);
					checkAndUpdateStock(p);
					createVenta(p);
					fillPresupuestosModel();
				}
			});
		}
		return btnCrearPresupuesto;
	}
	
	protected void checkAndUpdateStock(Presupuesto p) {
		if(!this.aceptPresController.checkStockInAlmacen(p)) {
			JOptionPane.showMessageDialog(this, "Se a creado un pedido al proveedor");
		}
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		}
		return btnCancelar;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getLabel_1());
		}
		return panel_1;
	}
	private JLabel getLabel_1() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel(" ");
		}
		return lblNewLabel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(1,0,15,0));
			panel_2.add(getLblNewLabel_1());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getLblNewLabel_1_1());
		}
		return panel_3;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("            ");
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("            ");
		}
		return lblNewLabel_1_1;
	}
	
	private DefaultListModel<String> getModel() {
		if(this.model == null) {
			this.model = new DefaultListModel<>();
			fillPresupuestosModel();						
		}
		return model;
	}
	
	public void fillPresupuestosModel() {
		PresupuestosTablaModel m = (PresupuestosTablaModel)this.table.getModel(); 
		m.clearRows();
		this.presupuestos = aceptPresController.getPresupuestosValidos();
		this.clientes = new ArrayList<Cliente>();
		System.out.println(presupuestos);
		presupuestos.stream().forEach(p -> {
			String dni = p.getDNI_Cliente();
			clientes.add(controller.getClienteById(dni));
		});
		
		System.out.println(clientes);
		
		
		for(int i = 0; i < presupuestos.size();i++) {
			m.addRow(presupuestos.get(i), clientes.get(i));
		}
		
	}
		
	private void createVenta(Presupuesto p) {
		Venta v = aceptPresController.crearVenta(p);
		JOptionPane.showMessageDialog(null, "Venta creada con exito");
		ProductosTransporte preparados = new ProductosTransporte(p, v);
		preparados.setVisible(true);

	}
}
