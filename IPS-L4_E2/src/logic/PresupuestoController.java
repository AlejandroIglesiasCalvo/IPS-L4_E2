package logic;

import java.util.List;

import logic.dto.PresupuestosModel;
import logic.dto.Producto;
import ui.presupuestos.CatalogoPanel;
import ui.presupuestos.PresupuestosView;


public class PresupuestoController {
	private PresupuestosModel model;
	private PresupuestosView view;
	
	public PresupuestoController(PresupuestosModel m, PresupuestosView v) {
		this.model = m;
		this.view = v;
		this.initView();
	}
	
	private void initView() {
		setProductos();
		//Abre la ventana
		this.view.getFrame().setVisible(true);
	}

	private void setProductos() {
		List<Producto> productos = this.model.getProductos();
		for(Producto p: productos) {
			this.view.getPnCateg().add(new CatalogoPanel(p,this.view.getPnCateg()));
		}
		
	}
	
}
