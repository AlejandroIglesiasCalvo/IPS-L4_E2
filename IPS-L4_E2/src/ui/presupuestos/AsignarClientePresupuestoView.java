package ui.presupuestos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.CreaClienteController;
import logic.CreaPresupuestoController;
import logic.dto.Cliente;
import ui.clientes.CrearClientesView;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AsignarClientePresupuestoView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JList<Cliente> list;
	private JPanel panel;
	private JButton btnAsignarCliente;
	private JButton btnCancelar;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private DefaultListModel<Cliente> model;
	
	private CreaClienteController controller = new CreaClienteController();
	private CreaPresupuestosView presView;
	private CreaPresupuestoController presController;
	private JButton btnNuevoCliente;
	private CrearClientesView cCV;
	AsignarClientePresupuestoView win;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AsignarClientePresupuestoView dialog = new AsignarClientePresupuestoView();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AsignarClientePresupuestoView(CreaPresupuestoController cPC, CreaPresupuestosView cPV) {
		this.presController = cPC;
		this.presView = cPV;
		setTitle("Seleccion de Cliente");
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
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JList<Cliente> getList() {
		if (list == null) {
			list = new JList<>();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					getBtnAsignarCliente().setEnabled(true);
				}
			});
			list.setModel(getModel());
		}
		return list;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panel.add(getBtnNuevoCliente());
			panel.add(getBtnAsignarCliente());
			panel.add(getBtnCancelar());
		}
		return panel;
	}
	private JButton getBtnAsignarCliente() {
		if (btnAsignarCliente == null) {
			btnAsignarCliente = new JButton("Asignar Cliente");
			btnAsignarCliente.setEnabled(false);
			btnAsignarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index =  getList().getSelectedIndex();
					Cliente c = getList().getModel().getElementAt(index);
					System.out.println(c);
					presController.setCliente(c);
					JOptionPane.showMessageDialog(null, "Cliente asignado al presupuesto");
					dispose();
				}
			});
		}
		return btnAsignarCliente;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
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
	
	private ListModel<Cliente> getModel() {
		if(this.model == null) {
			this.model = new DefaultListModel<>();
			fillClientesModel();						
		}
		return model;
	}
	
	public void fillClientesModel() {
		this.model.clear();
		List<Cliente> l = controller.showClientes();
		l.stream().forEach(c -> this.model.addElement(c));
	}

	private JButton getBtnNuevoCliente() {
		if (btnNuevoCliente == null) {
			btnNuevoCliente = new JButton("Nuevo Cliente");
			btnNuevoCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cCV = new CrearClientesView(win);
					cCV.setModal(true);
					cCV.setVisible(true);
				}
			});
		}
		return btnNuevoCliente;
	}
}
