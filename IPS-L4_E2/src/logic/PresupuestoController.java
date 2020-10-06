package logic;

import java.util.List;

import dataBase.dto.PresupuestosModel;
import dataBase.dto.Producto;
import ui.presupuestos.CatalogoPanel;
import ui.presupuestos.CreaPresupuestosView;


public class PresupuestoController {
	private PresupuestosModel model;
	private CreaPresupuestosView view;
	
	public PresupuestoController(PresupuestosModel m, CreaPresupuestosView v) {
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
			CatalogoPanel pn = new CatalogoPanel(p,this.view.getPnCateg());
			
			this.view.getPnCateg().add(pn);
		}
		
	}
	
	
}
