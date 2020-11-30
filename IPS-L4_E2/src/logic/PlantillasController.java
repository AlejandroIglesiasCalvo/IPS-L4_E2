package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataBase.DataBase;
import logic.dto.Plantilla;
import logic.dto.Producto;
import logic.dto.ProductoCarrito;
import logic.dto.Venta;
import logic.dto.producto_venta;
import ui.presupuestos.CreaPresupuestosView;

public class PlantillasController {
	DataBase db;
	public CreaPresupuestoController presController;
	public CreaPresupuestosView creaPresupuesto;
	
	

	public PlantillasController(CreaPresupuestoController preController, CreaPresupuestosView creaPresupuesto) {

		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		//ventas = db.getGestionVentas().getVentas();
		this.presController = preController;
		this.creaPresupuesto = creaPresupuesto;
	}


	public List<Plantilla> getPlantillas() {
		return db.getGestionCreaPresupuesto().getPlantillas();
	}

	public List<producto_venta> getProductosMontadosYTransportados(String id) {
		List<producto_venta> resultado = db.getGestionTransporte().getProducto_venta();
		List<producto_venta> filtro = new ArrayList<>();
		for (producto_venta p : resultado) {
			if (id.equals(String.valueOf(p.getId_venta()))) {
				filtro.add(p);
			}
		}
		return filtro;
	}
	
	public void añadirProductos( List<ProductoCarrito> productos ) {
		
		for(ProductoCarrito p : productos) {
			for(int i = 0; i < p.getUnidades(); i++) {
				System.out.println(p.getNombre());
				creaPresupuesto.getTxtTotal().setText(presController.updateTotalAddProduct(new Producto(p.getID(), p.getNombre(), p.getTipo(), p.getPrecio())));
				creaPresupuesto.updatePresupuesto();
			}
			//creaPresupuesto.getTxtTotal().setText(presController.updateTotalAddProduct(new Producto(iD, nombre, tipo, precio)));
			//creaPresupuesto.updatePresupuesto();
		}
		creaPresupuesto.getBtnCreate().setEnabled(true);
		
	}

}
