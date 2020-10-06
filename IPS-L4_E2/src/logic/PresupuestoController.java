package logic;

import java.util.List;

import logic.dto.Producto;
import ui.presupuestos.CatalogoPanel;
import ui.presupuestos.CreaPresupuestosView;


public class PresupuestoController {
	private CreaPresupuestosView view;
	
	public PresupuestoController(CreaPresupuestosView v) {
		this.view = v;
		this.initView();
	}
	
	private void initView() {
		setProductos();
		//Abre la ventana
		this.view.getFrame().setVisible(true);
	}

	private void setProductos() {
//		List<Producto> productos = this.model.getProductos();
//		for(Producto p: productos) {
//			CatalogoPanel pn = new CatalogoPanel(p,this.view.getPnCateg());
//			
//			this.view.getPnCateg().add(pn);
//		}
		
	}
	
	
}
