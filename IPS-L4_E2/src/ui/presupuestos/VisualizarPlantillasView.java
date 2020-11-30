package ui.presupuestos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.CreaPresupuestoController;
import logic.PlantillasController;
import logic.dto.Plantilla;
import ui.presupuestos.modelos.PlantillasTabla;
import ui.presupuestos.modelos.PlantillasTablaModel;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class VisualizarPlantillasView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JScrollPane scrollPane;
	PlantillasTabla tabla;
	PlantillasController cont;
	private JPanel panel;
	private JButton btnSeleccionarPlantilla;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			VisualizarPlantillasView dialog = new VisualizarPlantillasView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public VisualizarPlantillasView(CreaPresupuestoController p, CreaPresupuestosView pv) {
		this.cont = new PlantillasController(p, pv);
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
			contentPanel.add(getJScrollPane());
			contentPanel.add(getPanel(), BorderLayout.SOUTH);
			
		
	}
	
	private JScrollPane getJScrollPane() {
		if(this.scrollPane == null) {
			this.scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTabla());
			
		}
		return this.scrollPane;
	}
	
	private PlantillasTabla getTabla() {
		if(this.tabla == null) {
			this.tabla = new PlantillasTabla();
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					getBtnSeleccionarPlantilla().setEnabled(true);			
				}
			});
			fillModel();
			
		}
		return tabla;
	}
	
	private void fillModel() {
		PlantillasTablaModel model = (PlantillasTablaModel)tabla.getModel();
		for(Plantilla p : cont.getPlantillas()) {
			model.addRow(p);
		}
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel.add(getBtnSeleccionarPlantilla());
			panel.add(getBtnCancelar());
		}
		return panel;
	}
	private JButton getBtnSeleccionarPlantilla() {
		if (btnSeleccionarPlantilla == null) {
			btnSeleccionarPlantilla = new JButton("Seleccionar Plantilla");
			btnSeleccionarPlantilla.setEnabled(false);
			btnSeleccionarPlantilla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PlantillasTablaModel m = (PlantillasTablaModel)getTabla().getModel();
					Plantilla p = m.getValueAtRow(getTabla().getSelectedRow());
					//System.out.println(p.getNombre());
					cont.añadirProductos(p.getProductos());
					JOptionPane.showMessageDialog(null, "Plantilla cargada con exito");
					dispose();
				}
			});
		}
		return btnSeleccionarPlantilla;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}
}
