package ui.almacen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataBase.GestionInventario;
import logic.InventarioController;
import logic.dto.Producto_Almacen;
import ui.almacen.modelos.InventarioTabla;
import ui.almacen.modelos.InventarioTablaModel;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class VisualizarInventarioView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private InventarioTabla tabla;
	
	private InventarioController controller = new InventarioController();
	private JPanel pnComboBox;
	private JComboBox comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarInventarioView dialog = new VisualizarInventarioView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarInventarioView() {
		setTitle("Visualizar Inventario");
		setBounds(100, 100, 1265, 814);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			contentPanel.add(getScrollPane(), BorderLayout.CENTER);
		}
		contentPanel.add(getPnComboBox(), BorderLayout.NORTH);
	}

	public JScrollPane getScrollPane() {
		if(scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
			
			
		}
		return scrollPane;
	}
	
	public InventarioTabla getTable() {
		if(tabla == null) {
			tabla = new InventarioTabla();
			fillModel(null);
		}
		return tabla;
	}
	
	public void fillModel(String filter) {
		InventarioTablaModel model = (InventarioTablaModel)getTable().getModel();
		model.clearRows();
		List<Producto_Almacen> p = controller.getStock();
		
		if(filter != null && !filter.equals("") ) {
			ArrayList<Producto_Almacen> re = new ArrayList<>();
			Stream<Producto_Almacen> s = p.stream().filter(ch -> ch.getTipo().equals(filter));
			s.forEach(var -> re.add(var));
			p = re	;		
		}
		p.stream().forEach(pa -> model.addRow(pa));
		
		
	}
	private JPanel getPnComboBox() {
		if (pnComboBox == null) {
			pnComboBox = new JPanel();
			pnComboBox.setLayout(new GridLayout(0, 10, 0, 0));
			pnComboBox.add(getComboBox());
		}
		return pnComboBox;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"","Cocina", "Oficina"}));
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fillModel((String)comboBox.getSelectedItem());
				}
			});
		}
		return comboBox;
	}
}
